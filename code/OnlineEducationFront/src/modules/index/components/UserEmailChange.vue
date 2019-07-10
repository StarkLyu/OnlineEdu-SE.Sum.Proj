<template>
    <div>
        <el-form :model="newEmailForm" :rules="newEmailRule">
            <el-form-item>
                <el-input v-model="newEmail">
                    <el-button slot="append" @click="startChange">确认修改</el-button>
                </el-input>
            </el-form-item>
        </el-form>
        <el-dialog
                :visible.sync="showConfirm"
                title="邮箱验证"
                width="500px"
        >
            <EmailConfirm confirm-type="email" ref="emailConfirm"></EmailConfirm>
            <div slot="footer">
                <el-button @click="cancelChange">取消</el-button>
                <el-button>提交</el-button>
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
                this.showConfirm = true;
            },
            cancelChange: function () {
                this.showConfirm = false;
                this.$refs['emailConfirm'].clear();
            }
        }
    }
</script>

<style scoped>

</style>