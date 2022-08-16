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
    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/confirmCode', params)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  getHashTags: function (user_id) {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH + '/hashtag?userId=' + user_id.trim())
        .then((response) => {
          if (response.data === 'fail') resolve([]);
          else resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  quitAccount: function (user_id) {
    return new Promise((resolve, reject) => {
      http
        .patch(REST_PATH + '/quit/' + user_id)
        .then((response) => {
          if (response.data === 'fail') resolve('회원 탈퇴에 실패했습니다');
          else resolve(true);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  checkNicknameDuplicated(user_nickname) {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH + '/checkNick?userNickname=' + user_nickname)
        .then((response) => {
          if (response.data === 'fail') reject('중복된 닉네임입니다');
          else resolve(true);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  modifyProfile(userData) {
    return new Promise((resolve, reject) => {
      http
        .put(REST_PATH + '/update', userData)
        .then((response) => {
          if (response.data === 'fail') reject('회원정보 수정 실패');
          else resolve(true);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  addUserHashTags(user_id, tags) {
    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/hashtag?userId=' + user_id, tags)
        .then((response) => {
          if (response.data === 'fail') reject('해쉬 태그 추가 실패');
          else resolve(true);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
