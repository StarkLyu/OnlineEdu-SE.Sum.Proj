import axios from 'axios'

axios.defaults.withCredentials = true;

const state = {
    userName: "jjj",
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
    }
}

// actions
const actions = {
    login: ({ state,commit,dispatch }, loginInfo) => {
        return new Promise((resolve, reject) => {
            axios.request({
                url: "/api/auth/signin",
                method: "post",
                data: {
                    username: loginInfo.username,
                    password: loginInfo.password
                }
            }).then((response) => {
                console.log(response.data);
                commit("loginSet", {
                    userName: loginInfo.username,
                    accessToken: response.data.accessToken
                });
                alert("登录成功");
                console.log(state);
                dispatch("loadUserInfo");
                resolve();
            }).catch((error) => {
                console.log(error.response);
                if (error.response.data.status === 401) {
                    alert("用户名或密码错误");
                }
                else {
                    alert(error);
                }
                reject();
            });
        })
    },
    loadUserInfo: ({ state, commit }) => {
        axios.request({
            url: "/api/users/info",
            method: 'get',
            headers: {
                "Authorization": "Bearer " + state.accessToken
            }
        }).then((infoResponse) => {
            commit("infoSet", infoResponse.data);
            console.log(state);
            if (state.user.userInfo.roles[0].role === "ROLE_ADMIN") {
                window.location = "/manager"
            }
        }).catch((error) => {
            console.log(error.response);
            if (error.response.data.status === 401) {
                alert("获取用户信息出错");
            }
            else {
                alert(error);
            }
        });
    },
}

// mutations
const mutations = {
    loginSet (state, loginData) {
        state.userName = loginData.username;
        state.accessToken = loginData.accessToken;
        state.loginStatus = true;
        console.log(state);
    },
    infoSet (state, infoData) {
        state.userInfo = infoData;
        console.log(state);
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
