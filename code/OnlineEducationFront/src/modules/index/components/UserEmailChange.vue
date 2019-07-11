<template>
    <div>
        <el-form :model="newEmailForm" :rules="newEmailRule" ref="emailForm">
            <el-form-item prop="newEmail">
                <el-input v-model="newEmailForm.newEmail">
                    <el-button slot="append" @click="startChange">确认修改</el-button>
                </el-input>
            </el-form-item>
        </el-form>
        <el-dialog
                :visible.sync="showConfirm"
                title="邮箱验证"
                width="500px"
        >
            <EmailConfirm confirm-type="email" ref="emailConfirm" @confirm-pass="confirmPass"></EmailConfirm>
            <div slot="footer">
                <el-button @click="cancelChange">取消</el-button>
                <el-button @click="submitConfirm">提交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import EmailConfirm from "./EmailConfirm";
    import formRules from "../rules.js"

    export default {
        name: "UserEmailChange",
        components: {EmailConfirm},
        props: {
            oldEmail: String,
        },
        data() {
            return {
                newEmailForm: {
                    newEmail: ""
                },
                newEmailRule: {
                    newEmail: formRules.emailRule
                },
                showConfirm: false,
            }
        },
        methods: {
            startChange: function () {
                this.$refs.emailForm.validate((value) => {
                    if (value) {
                        this.$http.request({
                            url: this.requestUrl,
                            method: "patch",
                            data: {
                                email: this.newEmailForm.newEmail
                            },
                            headers: this.requestHead
                        }).then(() => {
                            this.showConfirm = true;
                        }).catch((error) => {
                            alert("出错啦！");
                            console.log(error.response);
                        })
                    }
                })
            },
            cancelChange: function () {
                this.showConfirm = false;
                this.$refs['emailConfirm'].clear();
            },
            confirmPass: function () {
                alert("修改成功！");
                this.showConfirm = false;
            },
            submitConfirm: function() {
                this.$refs.emailConfirm.sendConfirmCode();
            }
        },
        computed: {
            requestUrl: function () {
                return "/api/users/" + this.$store.state.user.userInfo.id + "/email";
            },
            requestHead: function () {
                return this.$store.getters.authRequestHead
            }
        },
        created() {
            this.newEmailForm.newEmail = this.oldEmail;
        }
    }
</script>

<style scoped>

</style>