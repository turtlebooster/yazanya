import jwt from '@/util/common/jwt';
import axios from 'axios';

export default {
  // add thumbnail
  addThumbnail: function (room_num, formdata) {
    return new Promise((resolve, reject) => {
      axios
        .post(
          process.env.VUE_APP_SERVER + '/room/Thumbnail/' + room_num,
          formdata,
          {
            headers: {
              'Content-Type': 'multipart/form-data',
              'access-token': jwt.getAccessToken(),
            },
          }
        )
        .then((response) => {
          if (response.data === 'success') {
            resolve(true);
          } else {
            reject('방 섬네일 업로드 중 문제가 발생하였습니다');
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // add profile image
  addProfileImage: function (user_id, formdata) {
    return new Promise((resolve, reject) => {
      axios
        .post(
          process.env.VUE_APP_SERVER + '/user/profile/' + user_id,
          formdata,
          {
            headers: {
              'Content-Type': 'multipart/form-data',
              'access-token': jwt.getAccessToken(),
            },
          }
        )
        .then((response) => {
          if (response.data === 'success') {
            resolve(true);
          } else {
            reject('프로필 사진 설정 중 문제가 발생하였습니다');
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
