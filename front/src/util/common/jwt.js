const TOKEN_KEY = 'id_token_b310';
const ID_KEY = 'id_b310';

export const getToken = () => {
  return window.localStorage.getItem(TOKEN_KEY);
};

export const saveToken = (token) => {
  window.localStorage.setItem(TOKEN_KEY, token);
};

export const destroyToken = () => {
  window.localStorage.removeItem(TOKEN_KEY);
};

// ----------------- put into token later ------------------ //
export const getID = () => {
  return window.localStorage.getItem(ID_KEY);
};

export const saveID = (id) => {
  return window.localStorage.setItem(ID_KEY, id);
};

export const destroyID = () => {
  return window.localStorage.removeItem(ID_KEY);
};

export default {
  getToken,
  saveToken,
  destroyToken,
  getID,
  saveID,
  destroyID,
};
