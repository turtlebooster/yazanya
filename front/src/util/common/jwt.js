const ACCESS_TOKEN_KEY = 'id_access_token_b310';
const REFRESH_TOKEN_KEY = 'id_refresh_token_b310';
const ID_KEY = 'id_b310';

export const getAccessToken = () => {
  return window.sessionStorage.getItem(ACCESS_TOKEN_KEY);
};

export const saveAccessToken = (token) => {
  window.sessionStorage.setItem(ACCESS_TOKEN_KEY, token);
};

export const destroyAccessToken = () => {
  window.sessionStorage.removeItem(ACCESS_TOKEN_KEY);
};

export const getRefreshToken = () => {
  return window.sessionStorage.getItem(REFRESH_TOKEN_KEY);
};

export const saveRefreshToken = (token) => {
  window.sessionStorage.setItem(REFRESH_TOKEN_KEY, token);
};

export const destroyRefreshToken = () => {
  window.sessionStorage.removeItem(REFRESH_TOKEN_KEY);
};

// ----------------- put into token later ------------------ //
export const getID = () => {
  return window.sessionStorage.getItem(ID_KEY);
};

export const saveID = (id) => {
  return window.sessionStorage.setItem(ID_KEY, id);
};

export const destroyID = () => {
  return window.sessionStorage.removeItem(ID_KEY);
};

export default {
  getAccessToken,
  saveAccessToken,
  destroyAccessToken,
  getRefreshToken,
  saveRefreshToken,
  destroyRefreshToken,
  getID,
  saveID,
  destroyID,
};
