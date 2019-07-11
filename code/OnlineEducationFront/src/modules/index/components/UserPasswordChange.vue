<template>
    <div>
        <el-button @click="showDialog = true">修改密码</el-button>
        <el-dialog
                :visible.sync="showDialog"
                title="修改密码"
                @close="cancelChange"
                width="500px"
        >
            <div v-if="changeStep === 0">
                <el-form
                        :model="changePass"
                        :rules="changeRules"
                        label-position="right"
                        label-width="80px"
                >
                    <el-form-item prop="oldPass" label="旧密码">
                        <el-input type="password" v-model="changePass.oldPass"></el-input>
                    </el-form-item>
                    <el-form-item prop="newPass" label="新密吗">
                        <el-input type="password" v-model="changePass.newPass"></el-input>
                    </el-form-item>
                    <el-form-item prop="confirmPass" label="确认密码">
                        <el-input type="password" v-model="changePass.confirmPass"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <div v-if="changeStep === 1">
                <EmailConfirm
                        confirmType="password"
                        ref="emailConfirm"
                        @confirm-pass="changeSuccess"
                ></EmailConfirm>
            </div>
            <div slot="footer">
                <el-button @click="cancelChange">取消</el-button>
                <el-button v-if="changeStep === 0" @click="nextStep">下一步</el-button>
                <el-button v-if="changeStep === 1" @click="sendConfirm">提交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import formRules from '../rules.js'
    import EmailConfirm from "./EmailConfirm";

    export default {
        name: "UserPasswordChange",
        components: {EmailConfirm},
        data() {
            let confirmPassValidator = (rule, value, callback) => {
                if (value !== this.changePass.newPass) {
                    callback(new Error("两次输入的新密码不符"))
                }
                else {
                    callback()
                }
            }

            return {
                showDialog: false,
                changeStep: 0,
                changePass: {
                    oldPass: "",
                    newPass: "",
                    confirmPass: ""
                },
                changeRules: {
                    oldPass: [
                        formRules.requireRule("旧密码不得为空")
                    ],
                    newPass: formRules.passwordRule,
                    confirmPass: [
                        formRules.requireRule("确认新密码不得为空"),
                        {validator: confirmPassValidator, trigger: "blur"}
                    ]
                }
            }
        },
        methods: {
            nextStep: function () {
                this.$http.request({
                    url: this.requestUrl,
                    method: "patch",
                    headers: this.requestHeader,
                    data: {
                        password: this.changePass.newPass
                    }
                }).then(() => {
                    this.changeStep++;
                }).catch((error) => {
                    alert("出错啦！");
                    console.log(error.response);
                })
            },
            cancelChange: function () {
                this.changePass.oldPass = "";
                this.changePass.newPass = "";
                this.changePass.confirmPass = "";
                this.showDialog = false;
                this.changeStep = 0;
                this.$refs['emailConfirm'].clear();
            },
            changeSuccess: function () {
                alert("修改成功！");
                this.cancelChange();
            },
            sendConfirm: function () {
                this.$refs.emailConfirm.sendConfirmCode();
            }
        },
        computed: {
            requestUrl: function () {
                return "/api/users/" + this.$store.state.user.userInfo.id + "/password";
            },
            requestHeader: function () {
                return this.$store.getters.authRequestHead;
            }
        }
    }
</script>

<style scoped>

</style>