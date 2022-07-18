import { createStore } from "vuex";

import jwt from "../util/jwt";
import http from "../util/http-commns";

export default createStore({
  state: {
    token: {
      accessToken: jwt.getToken(),
      // refreshToken : not added
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
    logout: function (state) {
      state.token.accessToken = "";
      state.isLogined = false;
      jwt.destroyToken();
    },

    login: function (state, payload = {}) {
      state.token.accessToken = payload.accessToken;
      state.isLogined = true;
      jwt.saveToken(payload.accessToken);
    },
  },

  actions: {
    logout: function (context) {
      // 비동기 처리
      return new Promise((resolve) => {
        setTimeout(function () {
          context.commit("logout");
          resolve({});
        }, 1000);
      });
    },

    register: function (context, payload) {
      let params = {
        userId: payload.id,
        userEmail: payload.email,
        uesrPw: payload.password,
        userName: payload.name,
        userNickname: payload.nickname,
      };

      return new Promise((resolve, reject) => {
        http
          .post("/user/regist", params)
          .then((response) => {
            resolve(response);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    login: function (context, payload) {
      let params = {
        userId: payload.id,
        userPw: payload.password,
      };

      return new Promise((resolve, reject) => {
        http
          .post("/user/login", params)
          .then((response) => {
            const { data } = response;
            context.commit("login", {
              accessToken: data["access-token"],
            });

            resolve(response);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    findId: function (context, payload) {
      let params = {
        userEmail: payload.email,
      };

      return new Promise((resolve, reject) => {
        http
          .post("/user/findid", params)
          .then((response) => {
            // TODO
            resolve(response);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    findPw: function (context, payload) {
      let params = {
        userEmail: payload.email,
        userPw: payload.password,
      };

      return new Promise((resolve, reject) => {
        http
          .post("/user/findpw", params)
          .then((response) => {
            // TODO
            resolve(response);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
  },
});
