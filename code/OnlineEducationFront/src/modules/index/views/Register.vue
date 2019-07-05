<template>
    <div>
        <div class="step-line">
            <el-steps :active="currentStep">
                <el-step title="账号信息" icon="el-icon-user-solid"></el-step>
                <el-step title="邮箱验证" icon="el-icon-message"></el-step>
                <el-step title="详细信息" icon="el-icon-s-order"></el-step>
                <el-step title="注册完成" icon="el-icon-check"></el-step>
            </el-steps>
        </div>
        <div v-if="currentStep === 0">
            <div class="step-layout">
                <el-form label-position="left" label-width="80px">
                    <el-form-item label="用户名">
                        <el-input
                                type="text"
                                suffix-icon="el-icon-user"
                                placeholder="请在此创建你的用户名"
                                v-model="registerUser.userId"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input
                                type="password"
                                suffix-icon="el-icon-lock"
                                placeholder="请在此创建密码"
                                v-model="registerUser.passWord"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码">
                        <el-input
                                type="password"
                                suffix-icon="el-icon-lock"
                                placeholder="请重新输入你创建的密码"
                                v-model="registerUser.confirmPass"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱">
                        <el-input
                                type="text"
                                suffix-icon="el-icon-message"
                                placeholder="请输入你的邮箱"
                                v-model="registerUser.email"
                        ></el-input>
                    </el-form-item>
                    <el-form-item>
                        <div class="center-layout next-button">
                            <el-button type="primary" @click="submitStep1">下一步</el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div v-else-if="currentStep === 1">
            <div class="step-layout">
                <el-form>
                    <el-form-item>
                        <h3>系统已向你的邮箱{{ registerUser.email }}发送了一封邮件，请从中获取验证码，如果邮箱填写有误请点击“上一步”返回并重新输入</h3>
                    </el-form-item>
                    <el-form-item>
                        <el-input>
                            <el-button v-if="resendFlag" slot="append">重新发送</el-button>
                            <el-button v-else slot="append" disabled></el-button>
                        </el-input>
                    </el-form-item>
                    <el-form-item>
                        <div class="center-layout button-group-size">
                            <el-button type="primary" @click="lastStep">上一步</el-button>
                            <el-button type="primary" @click="submitStep2">下一步</el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div v-else-if="currentStep === 2"></div>
        <div v-else-if="currentStep === 3"></div>
        <div class="center-layout return-link">
            <div class="return-offset">
                <el-link>已有账号？点此返回登录</el-link>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data() {
            return {
                currentStep: 0,
                resendFlag: false,
                countDown: 60,
                registerUser: {
                    userId: "",
                    passWord: "",
                    confirmPass: "",
                    email: ""
                }
            }
        },
        methods: {
            nextStep: function() {
                this.currentStep++;
            },
            lastStep: function() {
                this.currentStep--;
            },
            submitStep1: function () {
                this.nextStep();
            },
            submitStep2: function () {
                this.nextStep();
            },

        }
    }
</script>

<style scoped>
    .step-line {
        width: 1000px;
        margin-left: auto;
        margin-right: auto;
    }

    .step-layout {
        width: 450px;
        margin-left: auto;
        margin-right: auto;
        margin-top: 50px;
    }

    .step1-size {
        width: 450px;
    }

    .step2-size {
        width: 900px;
    }

    .button-group-size {
        width: 180px;
    }

    .next-button {
        width: 100px;
    }

    .return-link {
        width: 300px;
        text-align: center;

    }

    /*.return-offset {
        margin-left:30px;
    }*/
</style>
