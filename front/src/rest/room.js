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
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  joinRoom: function (room_num, roomPw = 0) {
    let params = {
      roomPw: roomPw,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/' + room_num, params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  leaveRoom: function (room_num) {
    console.log('getters ', store.getters['getUserID']);
    let params = {
      // TODO : remove store code part later
      userId: store.getters['getUserID'],
    };

    return new Promise((resolve, reject) => {
      http
        .patch(REST_PATH + '/exit/' + room_num, params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};