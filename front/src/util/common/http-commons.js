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
      // header token key 확인 필요
      config.headers.common['access-token'] = store.getters['getAccessToken'];
    }
    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

// content-type 변경 확인 필요
// http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";

export default http;
