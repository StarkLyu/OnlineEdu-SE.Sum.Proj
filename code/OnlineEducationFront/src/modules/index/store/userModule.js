import axios from 'axios'

const state = {
    username: "",
    accessToken: "",
}

// getters
const getters = {

}

// actions
const actions = {
    login: ({ commit }, loginInfo) => {
        axios.request({
            url: "/api/auth/signin",
            method: "post",
            data: {
                username: loginInfo.username,
                password: loginInfo.password
            }
        }).then((response) => {
            commit("loginSet", {
                username: loginInfo.username,
                accessToken: response.data.accessToken
            });
            alert("登录成功");
        }).catch((error) => {
            if (error.response.data.status === 401) {
                alert("用户名或密码错误");
            }
            else {
                alert(error);
            }
        });
        return 1;
    },
    register: ({ commit }, registerInfo) => {
        axios.request({
            url: "/api/auth/signup",
            method: "post",
            data: registerInfo
        }).then((response) => {

        })
    }
}

// mutations
const mutations = {
    loginSet (state, loginData) {
        state.userName = loginData.username;
        state.accessToken = loginData.accessToken;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
