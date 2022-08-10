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
  (error) => {
    const {
      config,
      response: { status },
    } = error;

    const originalRequest = config;
    const refreshToken = store.getters['getRefreshToken'];

    if (status === 401) {
      axios({
        method: 'post',
        url: `${process.env.VUE_APP_SERVER}/auth`, // TODO : change
        data: { refreshToken },
      }).then((response) => {
        const accessToken = response.data['access-token'];
        store.commit('SET_ACCESS_TOKEN', accessToken);

        // set newly accesstoken
        originalRequest.headers = { 'access-token': accessToken };
        return axios(originalRequest);
      });
    }
    return Promise.reject(error);
  }
);

// content-type 변경 확인 필요
// http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";

export default http;
