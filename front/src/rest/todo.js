import http from '@/util/common/http-commons';
var REST_PATH = '/todo';

export default {
  createTodo: function (payload) {
    console.log(payload);

    return new Promise((resolve, reject) => {
      http
        .post(REST_PATH, payload)
        .then((response) => {
          if (response.data === 'fail') {
            reject(`생성 중에 문제가 발생하였습니다`);
          } else {
            resolve(response.data);
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  getTodo: function (user_id) {
    return new Promise((resolve, reject) => {
      http
        .get(REST_PATH + '/' + user_id)
        .then((response) => {
          if (response.data === 'fail') {
            reject(`오늘의 할일을 불러오는 중 문제가 발생하였습니다`);
          } else {
            resolve(response.data.todoList);
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  updateTodo: function (todo) {
    return new Promise((resolve, reject) => {
      http
        .put(REST_PATH, todo)
        .then((response) => {
          if (response.data === 'success') {
            resolve('success');
          } else {
            reject('TODO 목록 동기화 중 문제가 발생하였습니다');
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  deleteTodo: function (todo_num) {
    return new Promise((resolve, reject) => {
      http
        .delete(REST_PATH + '/' + todo_num)
        .then((response) => {
          if (response.data === 'success') {
            resolve('success');
          } else {
            reject('TODO 삭제중 문제가 발생하였습니다');
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
