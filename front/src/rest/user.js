import http from '@/util/common/http-commons';
var REST_PATH = '/user';

export default {
  signUp: function (payload) {
    let params = {
      userId: payload.id,
      userPw: payload.pw,
      userEmail: payload.email,
      userName: payload.name,
      userNickname: payload.nickname,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/regist', params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  login: function (payload) {
    let params = {
      userId: payload.id,
      userPw: payload.pw,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/login', params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  findId: function (payload) {
    let params = {
      userEmail: payload.email,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/findid', params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  findPw: function (payload) {
    let params = {
      userEmail: payload.email,
      userPw: payload.password,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/findpw', params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  getProfile: function (userid) {
    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/profile/' + userid)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
