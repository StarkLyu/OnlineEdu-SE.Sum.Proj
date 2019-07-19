export const SET_LOGIN_USER = 'SET_LOGIN_USER';

export function setLoginUser(username) {
    return {
        type: SET_LOGIN_USER,
        username
    }
}
