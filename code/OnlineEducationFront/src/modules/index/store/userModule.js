import axios from 'axios'

 axios.defaults.withCredentials = true;

const state = {
    username: "",
    accessToken: "",
    loginStatus: false,
    userInfo: {
        email: "",
        grade: 0,
        major: "",
        realName: "",
        sex: "",
        sno: "",
        tno: "",
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

// getters
const getters = {
    authRequestHead: state => {
        return {
            Authorization: "Bearer " + state.accessToken
        }
    },
    userAvatarUrl: state => {
        if (state.userInfo.avatarUrl !== "") {
            return "http://202.120.40.8:30382/online-edu/static/" + state.userInfo.avatarUrl + "?a=" + Math.random();
        }
        else return "";
    },
    userRole: state => {
        return state.userInfo.roles[0].role;
    },
    assistCourses: state => {
        return state.userInfo.
    }
}

// actions
const actions = {

}

// mutations
const mutations = {
    loginSet (state, loginData) {
        state.username = loginData.username;
        state.accessToken = loginData.accessToken;
        state.loginStatus = true;
        console.log(state);
    },
    infoSet (state, infoData) {
        state.userInfo = infoData;
        console.log(state);
    },
    logOut (state) {
        state.username = "";
        state.accessToken = "";
        state.loginStatus = false;
        state.userInfo = {
            email: "",
            grade: 0,
            major: "",
            realName: "",
            sex: "",
            sno: "",
            tno: "",
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
}

export default {
    state,
    getters,
    actions,
    mutations
}
