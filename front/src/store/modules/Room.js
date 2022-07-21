export const Room = {
  state: {
    ws: null,
  },

  actions: {
    initSocket(state) {
      console.log("ws init");
      state.ws = new WebSocket(
        "ws://" + "localhost:8334" + "/groupcall"
      );
    },

    sendMessage(state, message) {
      if (state.ws != null) {
        var jsonMessage = JSON.stringify(message);
        console.log("Sending message: " + jsonMessage);
        state.ws.send(jsonMessage);
      }
    },

    closeSocket(state) {
      if (state.ws != null) {
        console.log("ws closed");
        state.ws.close();
      }
    },
  },
};
