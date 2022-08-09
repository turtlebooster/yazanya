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

      chat_list: [
        {
          senderId: 'zxcv',
          senderName: 'James',
          profile: 'https://placekitten.com/300/300',
          message: 'Lorem ipsum dolor sit amet,',
        },
      ],

      user: null, // current logined
      room: null,
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
        return state.room.roomPw == 0 ? false : true;
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
      return state.user.userNickname;
    },

    getRoomUserId(state) {
      if (state.user != null) {
        return state.user.userId;
      } else {
        return '';
      }
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

    addChat(state, data) {
      state.chat_list.push(data);
    },

    recieveData(state, data) {
      let recieved = data.split(',');
      console.log('recieved ', recieved);

      if (recieved[0] === 'chat') {
        let message = '';
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
  },

  actions: {
    leaveRoom({ commit }) {
      commit('sendMessage', {
        id: 'leaveRoom',
      });
      commit('disposeAll');
      commit('closeSocket');
    },

    saveRoomInfo({ commit }, payload) {
      commit('setRoomInfo', payload);
    },

    saveUserInfo({ commit }, payload) {
      commit('setUserInfo', payload);
    },

    joinRoom({ commit }) {
      commit('initSocket');
    },

    sendChat({ commit }, payload) {
      commit('addChat', payload);
      commit('sendChat', payload);
    },
  },
};
