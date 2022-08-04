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

      username: '',
      roomname: '',

      chat_list: [],
    };
  },

  getters: {
    getParticipants(state) {
      return state.participants;
    },

    getChatList(state) {
      return state.chat_list;
    },
  },

  mutations: {
    initSocket(state) {
      console.log('Web Socekt Init');
      state.ws = new WebSocket('ws://' + 'localhost:8334' + '/groupcall');

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
                state.username,
                state.roomname
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

    disposeAll(state) {
      for (var key in state.participants) {
        state.participants[key].dispose();
      }
    },

    setUserName(state, data) {
      state.username = data;
    },

    setRoomName(state, data) {
      state.roomname = data;
    },

    addChat(state, data) {
      state.chat_list.push(data);
    },

    sendChat(state, data) {
      for (var key in state.participants) {
        state.participants[key].rtcPeer.send(data.sender + ':' + data.message);
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

    register({ commit }, payload) {
      commit('setUserName', payload.username);
      commit('setRoomName', payload.roomname);

      var message = {
        id: 'joinRoom',
        name: payload.username,
        room: payload.roomname,
      };
      commit('sendMessage', message);
    },

    sendChat({ commit }, payload) {
      commit('addChat', payload);
      commit('sendChat', payload);
    },
  },
};
