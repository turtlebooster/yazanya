import http from '@/util/common/http-commons';
import store from '@/store';
var REST_PATH = '/room';

export default {
  getRoomList: function (checkVideoOn, checkSoundOn, checkFull) {
    // video : 0(끔) / 1(켬) / 2(무관)
    // sound : 0(끔) / 1(켬) / 2(무관)
    // fullcheck : 0(무관) / 1 (정원이 안찬 방)
    let params = {
      video: checkVideoOn,
      sound: checkSoundOn,
      fullcheck: checkFull,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/filter', params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  getRoomInfo: function (room_num) {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH + '/' + room_num)
        .then((response) => {
          if (response.data.message === 'success') {
            resolve(response.data.room);
          } else {
            reject('방 정보를 서버로부터 가져오는데 실패하였습니다');
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  joinRoom: function (room_num, roomPw = '') {
    let params = {
      roomPw: roomPw,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/' + room_num, params)
        .then((response) => {
          switch (response.data) {
            case 'success':
              resolve(true);
              break;
            case 'failToForcedExitUser':
              reject('강제 퇴장당한 방에는 입장 할 수 없습니다');
              break;
            case 'failToFullRoom':
              reject('방 인원이 가득찼습니다');
              break;
            case 'failToPw':
              reject('비밀번호가 틀렸습니다');
              break;
            case 'alreadyParticipateUser':
              reject('이미 방에 입장중입니다');
              break;
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  leaveRoom: function (room_num) {
    let params = {
      // TODO : remove store code part later
      userId: store.getters['getUserID'],
    };

    return new Promise((resolve) => {
      http.patch(REST_PATH + '/exit/' + room_num, params).then((response) => {
        resolve(response);
      });
    });
  },

  getRoomRecommendList: function (tagArr) {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH + '/recommend?hashtagNum=' + tagArr)
        .then((response) => {
          resolve(response.data.roomList);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  getRoomHistoryList: function () {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH + '/history')
        .then((response) => {
          resolve(response.data.roomList);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  creatRoom: function (payload) {
    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH, payload)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  hasRoomPw: function (room_num) {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH + '/hasPw/' + room_num)
        .then((response) => {
          if (response.data === 'fail') {
            reject('존재하지 않는 방입니다');
          }
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  removeRoom: function (room_num) {
    return new Promise((resolve, reject) => {
      http
        .delete(REST_PATH + '/' + room_num)
        .then((response) => {
          if (response.data === 'fail') {
            reject('방 삭제 실패');
          }
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // /room/forceExit
  kickUser: function (user_name, room_num) {
    let params = {
      userNickname: user_name,
      roomNum: room_num + '',
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/forceExit', params)
        .then((response) => {
          if (response.data === 'success') {
            resolve(true);
          } else {
            reject('강퇴 중 서버연결에 문제가 생겼습니다.');
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
