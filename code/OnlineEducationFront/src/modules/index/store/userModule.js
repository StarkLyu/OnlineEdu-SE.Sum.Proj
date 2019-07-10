import axios from 'axios'

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
    login: ({ state,commit }, loginInfo) => {
        axios.request({
            url: "/api/auth/signin",
            method: "post",
            data: {
                username: loginInfo.userName,
                password: loginInfo.password
            }
        }).then((response) => {
            console.log(response.data);
            commit("loginSet", {
                userName: loginInfo.userName,
                accessToken: response.data.body.accessToken
            });
            alert("登录成功");
            console.log(state);
            axios.request({
                url: "/api/users/info",
                method: 'get',
                headers: {
                    "Authorization": "Bearer " + state.accessToken
                }
            }).then((infoResponse) => {
                if (infoResponse.data.roles[0].role === "ROLE_ADMIN") {
                    window.location = "/manager"
                }
                commit("infoSet", infoResponse.data);
                console.log(state);
            }).catch((error) => {
                console.log(error.response);
                if (error.response.data.status === 401) {
                    alert("登录出错");
                }
            })
        }).catch((error) => {
            console.log(error.response);
            if (error.response.data.status === 401) {
                alert("用户名或密码错误");
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
        state.userName = loginData.userName;
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