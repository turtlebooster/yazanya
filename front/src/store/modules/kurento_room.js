import {
  onExistingParticipants,
  onNewParticipant,
  onParticipantLeft,
  receiveVideoResponse,
} from '@/util/kurento/OnMessageHandlers';

export const Room = {
  state() {
    return {
      ws: null,
      participants: {},
      isSocketConnected: false,

      chat_list: [],

      user: null, // current logined
      room: null,

      roomLeaveTriggerFlag: 0, // 0 : nomal, 1 : room closed, 2 : kicked
    };
  },

  getters: {
    getParticipants(state) {
      return state.participants;
    },

    getParticipantsCount(state) {
      return Object.keys(state.participants).length;
    },

    getChatList(state) {
      return state.chat_list;
    },

    // ------------------ Room Info ------------------------ //
    isEntered(state) {
      return state.room == null ? false : true;
    },

    getRoomName(state) {
      if (state.room != null) {
        return state.room.roomName;
      } else {
        return '';
      }
    },

    isPrivateRoom(state) {
      if (state.room != null) {
        return state.room.roomHasPw;
      } else {
        return true;
      }
    },

    isVideoEnabled(state) {
      if (state.room) {
        return state.room.roomVideo;
      }
      return true;
    },

    isAudioEnabled(state) {
      if (state.room) {
        return state.room.roomSound;
      }
      return true;
    },

    // ------------------ User Info ------------------------- //
    getNickname(state) {
      if (state.user != null) {
        return state.user.userNickname;
      }
      return '';
    },

    getRoomUserId(state) {
      if (state.user != null) {
        return state.user.userId;
      } else {
        return '';
      }
    },

    isRoomHost(state) {
      if (
        state.user != null &&
        state.room != null &&
        state.room.userNum == state.user.userNum
      ) {
        return true;
      }
      return false;
    },

    getLeaveTriggerFlag(state) {
      return state.roomLeaveTriggerFlag;
    },
  },

  mutations: {
    initSocket(state) {
      console.log('Web Socket Init');
      state.ws = new WebSocket(process.env.VUE_APP_WEBSOCKET);
      // state.ws = new WebSocket('ws://' + 'localhost:8334' + '/groupcall'); // for local test

      state.ws.onopen = () => {
        console.log('Web Socket Opened');
        state.isSocketConnected = true;

        state.ws.onmessage = function (message) {
          var parsedMessage = JSON.parse(message.data);
          console.info('Received message: ' + message.data);

          switch (parsedMessage.id) {
            case 'existingParticipants':
              onExistingParticipants(
                parsedMessage,
                state.participants,
                state.user.userNickname,
                state.room.roomNum,
                state.room.roomVideo,
                state.room.roomSound
              );
              break;
            case 'newParticipantArrived':
              onNewParticipant(parsedMessage, state.participants);
              break;
            case 'participantLeft':
              onParticipantLeft(parsedMessage, state.participants);
              break;
            case 'receiveVideoAnswer':
              receiveVideoResponse(parsedMessage, state.participants);
              break;
            case 'iceCandidate':
              state.participants[parsedMessage.name].rtcPeer.addIceCandidate(
                parsedMessage.candidate,
                function (error) {
                  if (error) {
                    console.error('Error adding candidate: ' + error);
                    return;
                  }
                }
              );
              break;
            default:
              console.error('Unrecognized message', parsedMessage);
          }
        };

        // join Room when socket is connected
        this.commit('joinRoom');
      };

      state.ws.onerror = () => {
        console.log('Web Socket Error');
        state.isSocketConnected = false;
      };
    },

    closeSocket(state) {
      if (state.isSocketConnected) {
        console.log('close Socket');
        state.isSocketConnected = false;
        state.ws.onclose = () => {};
        state.ws.close();
      }
    },

    sendMessage(state, message) {
      if (state.isSocketConnected) {
        var jsonMessage = JSON.stringify(message);
        console.log('Sending message: ' + jsonMessage);
        state.ws.send(jsonMessage);
      }
    },

    joinRoom(state) {
      var message = {
        id: 'joinRoom',
        name: state.user.userNickname,
        room: state.room.roomNum,
      };
      this.commit('sendMessage', message);
    },

    disposeAll(state) {
      for (var key in state.participants) {
        state.participants[key].dispose();
      }
    },

    setRoomInfo(state, data) {
      state.room = data;
    },

    setUserInfo(state, data) {
      state.user = data;
    },

    setParticipants(state, data) {
      state.participants = data;
    },

    setChatList(state, data) {
      state.chat_list = data;
    },

    addChat(state, data) {
      state.chat_list.push(data);
    },

    recieveData(state, data) {
      let recieved = data.split(',');
      console.log('recieved ', recieved);

      let message = '';
      switch (recieved[0]) {
        case 'chat':
          for (let i = 4; i < recieved.length; i++) {
            // recouple splited message
            message += recieved[i];
          }

          this.commit('addChat', {
            senderId: recieved[1],
            senderName: recieved[2],
            profile: recieved[3],
            message: message,
          });
          break;
        case 'kicked':
          if (recieved[1] === state.user.userNickname) {
            state.roomLeaveTriggerFlag = 2;
          }
          break;
        case 'closed':
          state.roomLeaveTriggerFlag = 1;
          break;
      }
    },

    sendChat(state, data) {
      console.log('chat sended ', data);
      for (var key in state.participants) {
        // "chat", senderId, senderName, profile, message
        state.participants[key].rtcPeer.send(
          'chat,' +
            data.senderId +
            ',' +
            data.senderName +
            ',' +
            data.profile +
            ',' +
            data.message
        );
      }
    },

    kickUser(state, username) {
      state.participants[state.user.userNickname].rtcPeer.send(
        'kicked,' + username
      );
    },
  },

  actions: {
    leaveRoom({ commit }) {
      commit('sendMessage', {
        id: 'leaveRoom',
      });
      commit('closeSocket');
      commit('disposeAll');
    },

    saveRoomInfo({ commit }, payload) {
      commit('setRoomInfo', payload);
    },

    saveUserInfo({ commit }, payload) {
      commit('setUserInfo', payload);
    },

    initRoom({ commit }) {
      commit('setChatList', []);
      commit('setParticipants', {});

      commit('setRoomInfo', null);
      commit('setUserInfo', null);
    },

    joinRoom({ commit }) {
      commit('initSocket');
    },

    sendChat({ commit }, payload) {
      commit('addChat', payload);
      commit('sendChat', payload);
    },

    toggleFilter({ commit }, flag) {
      var message = {
        id: flag ? 'filterOn' : 'filterOff',
      };
      commit('sendMessage', message);
    },
  },
};
