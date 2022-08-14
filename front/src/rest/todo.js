import http from '@/util/common/http-commons';
var REST_PATH = '/todo';

export default {
  createTodo: function (payload) {
    let params = {
      todoContent: payload.content,
      todoEndTime: payload.endTime,
      todoName: payload.name,
      todoProgress: payload.progress,
      todoStartTime: payload.startTime,
    };

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH, params)
        .then((response) => {
          if (response.data.message === 'success') {
            resolve(response.data);
          } else {
            reject(`[ERROR : createTodo]`);
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  getTodo: function () {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH)
        .then((response) => {
          if (response.status === 200) {
            resolve(response.data);
          } else {
            reject(`[ERROR : getTodo]`);
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
