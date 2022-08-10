import jwt from '@/util/common/jwt';

export const Account = {
  state: {
    token: {
      accessToken: jwt.getAccessToken(),
      refreshToken: jwt.getRefreshToken(),
    },
    isLogined: !!jwt.getAccessToken(),
    userID: jwt.getID(),
  },

  getters: {
    getAccessToken: function (state) {
      return state.token.accessToken;
    },
    getRefreshToken: function (state) {
      return state.token.refreshToken;
    },
    isAuthenticated: function (state) {
      return state.isLogined;
    },
    getUserID: function (state) {
      return state.userID;
    },
  },

  mutations: {
    // ADD, SET, REMOVE
    SET_ISLOGINED: function (state, flag) {
      state.isLogined = flag;
    },

    SET_ACCESS_TOKEN: function (state, accessToken) {
      state.token.accessToken = accessToken;
      jwt.saveAccessToken(accessToken);
    },

    REMOVE_ACCESS_TOKEN: function (state) {
      state.token.accessToken = '';
      jwt.destroyAccessToken();
    },

    SET_REFRESH_TOKEN: function (state, refreshToken) {
      state.token.refreshToken = refreshToken;
      jwt.saveRefreshToken(refreshToken);
    },

    REMOVE_REFRESH_TOKEN: function (state, refreshToken) {
      state.token.refreshToken = '';
      jwt.destroyRefreshToken(refreshToken);
    },

    SET_USER_ID: function (state, id) {
      state.userID = id;
      jwt.saveID(id);
    },

    REMOVE_USER_ID: function (state) {
      state.saveID = '';
      jwt.destroyID();
    },
  },

  actions: {
    logout: function (context) {
      // for aync proess
      return new Promise((resolve) => {
        setTimeout(function () {
          context.commit('SET_ISLOGINED', false);
          context.commit('REMOVE_ACCESS_TOKEN');
          context.commit('REMOVE_REFRESH_TOKEN');
          context.commit('REMOVE_USER_ID');
          resolve({});
        }, 500);
      });
    },

    login: function (context, payload) {
      // for aync proess
      context.commit('SET_ISLOGINED', true);
      context.commit('SET_ACCESS_TOKEN', payload['access-token']);
      context.commit('SET_REFRESH_TOKEN', payload['refresh-token']);
      context.commit('SET_USER_ID', payload['id']);
    },
  },
};
