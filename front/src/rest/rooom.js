import http from '@/util/common/http-commons';
var REST_PATH = '/room';

export default {
  getRoomList(checkVideoOn, checkSoundOn, checkFull) {
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

  joinRoom(roomNum, hasPw, roomPw) {
    let params = {
      roomPw: hasPw ? roomPw : 0,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH + '/filter/' + roomNum, params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
