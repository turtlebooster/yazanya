import axios from 'axios';
import store from '../../store';

const http = axios.create({
  baseURL: process.env.VUE_APP_SERVER,
  headers: {
    'content-type': 'application/json; charset=UTF-8',
  },
});

http.interceptors.request.use(
  (config) => {
    const isLogined = store.getters['isAuthenticated'];
    if (isLogined) {
      config.headers.common['access-token'] = store.getters['getAccessToken'];
    }
    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

// Response interceptor for API calls
http.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    console.log(error);

    const {
      config,
      response: { status },
    } = error;

    const originalRequest = config;
    const refreshToken = store.getters['getRefreshToken'];

    if (status === 401) {
      let response = await axios({
        method: 'post',
        url: `${process.env.VUE_APP_SERVER}/user/auth`, // TODO : change
        headers: {
          'refresh-token': refreshToken,
        },
      });
      if (response.data === 'fail') {
        return Promise.reject('로그인이 해제되었습니다\n 다시 로그인해주세요');
      }
      const accessToken = response.data;
      store.commit('SET_ACCESS_TOKEN', accessToken);

      // set newly accesstoken
      originalRequest.headers = { 'access-token': accessToken };
      return axios(originalRequest);
    }
    return Promise.reject(error);
  }
);

// content-type 변경 확인 필요
// http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";

export default http;
