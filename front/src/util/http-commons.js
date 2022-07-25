import axios from "axios";
import store from "../store";

const http = axios.create({
  baseURL: "http://localhost:9999/B310",
  headers: { "content-type": "application/json" },
});

http.interceptors.request.use(
  (config) => {
    const isLogined = store.getters["isLogined"];
    if (isLogined) {
      // header token key 확인 필요
      config.headers.common["access-token"] = store.getters["getAccessToken"];
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
