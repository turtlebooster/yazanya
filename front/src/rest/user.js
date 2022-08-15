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

  signOut: function () {
    console.log('sign out');
    return 2;
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
          if (response.data.message === 'success') {
            resolve(response.data.token);
          } else {
            reject('로그인 중 문제가 발생하였습니다');
          }
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
        .get(REST_PATH + '/profile/' + userid)
        .then((response) => {
          if (response.data == {}) {
            reject('유저 정보를 불러오는데 실패하였습니다');
          } else {
            resolve(response.data);
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  getUserList: function () {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH)
        .then((response) => {
          if (response.data == {}) {
            reject('유저 리스트 정보를 불러오는데 실패하였습니다');
          } else {
            resolve(response.data);
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  confirmEmail: function (email) {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH + '/confirmEmail?email=' + email)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  confirmCode: function (payload) {
    let params = {
      email: payload.email,
      code: payload.code,
    };
    console.log(params);
    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/confirmCode', params)
        .then((response) => {
          console.log(response.data);
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
