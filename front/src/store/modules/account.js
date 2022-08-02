import jwt from '@/util/common/jwt';

export const Account = {
  state: {
    token: {
      accessToken: jwt.getToken(),
      // refreshToken : not added yet
    },
    isLogined: !!jwt.getToken(),
  },

  getters: {
    getAccessToken: function (state) {
      return state.token.accessToken;
    },
    isAuthenticated: function (state) {
      return state.isLogined;
    },
  },

  mutations: {
    // ADD, SET, REMOVE
    SET_ISLOGINED: function (state, flag) {
      state.isLogined = flag;
    },

    REMOVE_ACCESS_TOKEN: function (state) {
      state.token.accessToken = '';
      jwt.destroyToken();
    },

    SET_ACCESS_TOKEN: function (state, accessToken) {
      state.token.accessToken = accessToken;
      jwt.saveToken(accessToken);
    },
  },

  actions: {
    logout: function (context) {
      // for aync proess
      return new Promise((resolve) => {
        setTimeout(function () {
          context.commit('SET_ISLOGINED', false);
          context.commit('REMOVE_ACCESS_TOKEN');

          resolve({});
        }, 500);
      });
    },

    saveToken: function (context, payload) {
      context.commit('SET_ISLOGINED', true);
      context.commit('SET_ACCESS_TOKEN', payload.data['access-token']);
    },
  },
};
