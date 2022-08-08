const TOKEN_KEY = 'id_token_b310';
const ID_KEY = 'id_b310';

export const getToken = () => {
  return window.sessionStorage.getItem(TOKEN_KEY);
};

export const saveToken = (token) => {
  window.sessionStorage.setItem(TOKEN_KEY, token);
};

export const destroyToken = () => {
  window.sessionStorage.removeItem(TOKEN_KEY);
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
  getToken,
  saveToken,
  destroyToken,
  getID,
  saveID,
  destroyID,
};
