<template>
    <div>
        <div class="login-square float-right">
            <div class="login-form">
                <h3>登录</h3>
                <el-form>
                    <el-form-item>
                        <el-input placeholder="用户名" suffix-icon="el-icon-user"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input placeholder="密码" suffix-icon="el-icon-lock"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" size="medium">
                            <div class="login-text">登录</div>
                        </el-button>
                    </el-form-item>
                    <el-form-item>
                        <div class="float-left">
                            <el-link :underline="false">忘记密码</el-link>
                        </div>
                        <div class="float-right">
                            <el-link :underline="false">注册新用户</el-link>
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