<template>
    <div>
        <div class="login-square float-right">
            <div class="login-form">
                <h3>登录</h3>
                <el-form :model="loginInfo"
                         :rules="rules"
                         ref="loginInfo"
                >
                    <el-form-item prop="userName">
                        <el-input
                                placeholder="用户名"
                                suffix-icon="el-icon-user"
                                id="userId"
                                v-model="loginInfo.userName"
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
                    userName: "",
                    password: ""
                },
                rules: {
                    userName: [
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
                        this.$store.dispatch('login', this.loginInfo).then(() => {
                            this.$router.push('/user')
                        });
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