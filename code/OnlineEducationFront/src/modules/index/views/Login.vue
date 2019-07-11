<template>
    <div>
        <div class="login-square float-right">
            <div class="login-form">
                <h3>登录</h3>
                <el-form :model="loginInfo"
                         :rules="rules"
                         ref="loginInfo"
                >
                    <el-form-item prop="username">
                        <el-input
                                placeholder="用户名"
                                suffix-icon="el-icon-user"
                                id="userId"
                                v-model="loginInfo.username"
                        ></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input
                                type="password"
                                placeholder="密码"
                                suffix-icon="el-icon-lock"
                                v-model="loginInfo.password"
                        ></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" size="medium">
                            <div class="login-text" @click="login" :loading="true">登录</div>
                        </el-button>
                    </el-form-item>
                    <el-form-item>
                        <div class="float-left">
                            <el-link :underline="false">忘记密码</el-link>
                        </div>
                        <div class="float-right">
                            <el-link :underline="false" href="/#/register">注册新用户</el-link>
                        </div>
                        <div class="float-clear"></div>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div style="clear:both"></div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                loginInfo: {
                    username: "",
                    password: ""
                },
                rules: {
                    username: [
                        {min: 3, max: 50, message: "用户名长度为3到50个字符", trigger: "blur"}
                    ],
                    password: [
                        {min: 6, max: 15, message: "密码长度为6到15个字符", trigger: "blur"}
                    ]
                }
            }
        },
        methods: {
            login: function () {
                this.$refs["loginInfo"].validate((valid) => {
                    if (valid) {
                        this.$http.request({
                            url: "/api/auth/signin",
                            method: "post",
                            data: {
                                username: this.loginInfo.username,
                                password: this.loginInfo.password
                            }
                        }).then((response) => {
                            console.log(response.data);
                            let getToken = response.data.accessToken;
                            this.$store.commit("loginSet", {
                                username: this.loginInfo.username,
                                accessToken: getToken
                            });
                            alert("登录成功");
                            //console.log(state);
                            this.$http.request({
                                url: "/api/users/info",
                                method: 'get',
                                headers: {
                                    "Authorization": "Bearer " + getToken
                                }
                            }).then((infoResponse) => {
                                this.$store.commit("infoSet", infoResponse.data);
                                //console.log(state);
                                if (infoResponse.data.roles[0].role === "ROLE_ADMIN") {
                                    localStorage.setItem("manageToken", getToken);
                                    window.location = "/manager";
                                }
                                this.$router.push('/user');
                            }).catch((error) => {
                                console.log(error.response);
                                if (error.response.data.status === 401) {
                                    alert("获取用户信息出错");
                                }
                                else {
                                    alert(error);
                                }
                            });
                            //dispatch("loadUserInfo");
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
                        /*this.$store.dispatch('login', this.loginInfo).then(() => {
                            this.$router.push('/user')
                        });*/
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .login-square {
        width: 350px;
        margin-top: 100px;
        margin-bottom: 100px;
        margin-right: 30px;
        border-style: solid;
        border-radius: 40px;
    }

    .login-form {
        margin: 40px;
    }

    .login-text {
        width: 230px
    }
</style>
