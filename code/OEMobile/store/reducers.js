import {
    SET_LOGIN,
    SET_LOGOUT,
    SET_USERINFO
} from './actions';
import {combineReducers} from 'redux';

const user = {
    login: {
        username: "",
        accessToken: "",
        loginStatus: false,
    },
    userInfo: {
        assistCourses: [],
        learnCourses: [],
        teachCourses: [],
        email: "",
        grade: 0,
        major: "",
        realName: "",
        sex: "",
        sno: "",
        tno: "",
        id: "",
        tel: "",
        university: "",
        avatarUrl: "",
        roles: [
            {
                id: 0,
                role: "",
            }
        ]
    }
}

//const { loginInit, userInfoInit } = user;
const loginInit = {
    username: "",
    accessToken: "",
    loginStatus: false,
}

const userInfoInit = {
    assistCourses: [],
    learnCourses: [],
    teachCourses: [],
    email: "",
    grade: 0,
    major: "",
    realName: "",
    sex: "",
    sno: "",
    tno: "",
    id: "",
    tel: "",
    university: "",
    avatarUrl: "",
    roles: [
        {
            id: 0,
            role: "",
        }
    ]
}

function loginAction(state = loginInit, action) {
    switch (action.type) {
        case SET_LOGIN:
            return action.login;
        case SET_LOGOUT:
            return state;
        default:
            return state;
    }
}

function userInfoAction(state = userInfoInit, action) {
    switch (action.type) {
        case SET_USERINFO:
            return action.userInfo;
    }
    return state;
}

const OEMobileApp = combineReducers({
    login: loginAction,
    userInfo: userInfoAction
})

export default OEMobileApp;
