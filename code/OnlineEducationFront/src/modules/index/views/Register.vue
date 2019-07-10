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
                <el-form
                        label-position="left"
                        label-width="80px"
                        :model="registerUser"
                        :rules="step1Rules"
                        ref="step1Form"
                >
                    <el-form-item label="用户名" prop="userName">
                        <el-input
                                type="text"
                                suffix-icon="el-icon-user"
                                placeholder="请在此创建你的用户名"
                                v-model="registerUser.username"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input
                                type="password"
                                suffix-icon="el-icon-lock"
                                placeholder="请在此创建密码"
                                v-model="registerUser.password"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="confirmPass">
                        <el-input
                                type="password"
                                suffix-icon="el-icon-lock"
                                placeholder="请重新输入你创建的密码"
                                v-model="registerUser.confirmPass"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                        <el-input
                                type="text"
                                suffix-icon="el-icon-message"
                                placeholder="请输入你的邮箱"
                                v-model="registerUser.email"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="tel">
                        <el-input type="text"
                                  suffix-icon="el-icon-phone-outline"
                                  placeholder="请输入你的手机号码"
                                  v-model="registerUser.tel"
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
        <div v-else-if="currentStep === 2">
            <div class="step-layout">
                <UserInfoManage :userdata="registerDetailInfo" @submit-info="submitStep3"></UserInfoManage>
            </div>
        </div>
        <div v-else-if="currentStep === 3"></div>
        <div class="center-layout return-link">
            <div class="return-offset">
                <el-link>已有账号？点此返回登录</el-link>
            </div>
        </div>
    </div>
</template>

<script>
    import UserInfoManage from "../components/UserInfoManage";
    export default {
        name: "Register",
        components: {UserInfoManage},
        data() {
            var checkConfirmPass = (rule, value, callback) => {
                if (value !== this.registerUser.password) {
                    callback(new Error("两次输入的密码不符"));
                }
                else {
                    callback();
                }
            }
            return {
                currentStep: 0,
                resendFlag: false,
                countDown: 60,
                registerUser: {
                    username: "",
                    password: "",
                    confirmPass: "",
                    email: "",
                    tel: ""
                },
                step1Rules: {
                    username: [
                        {required: true, message: "用户名不能为空", trigger: "change"},
                        {min: 3, max: 50, message: "用户名长度为3到50个字符", trigger: "change"}
                    ],
                    password: [
                        {required: true, message: "密码不能为空", trigger: "change"},
                        {min: 6, max: 15, message: "密码长度为6到15个字符", trigger: "blur"}
                    ],
                    confirmPass: [
                        {required: true, message: "确认密码不能为空", trigger: "change"},
                        {validator: checkConfirmPass, trigger: "change"}
                    ],
                    email: [
                        {required: true, message: "邮箱不能为空", trigger: "change"},
                        {type: "email", message: "邮箱格式有误", trigger: "blur"}
                    ],
                    tel: [
                        {required: true, message: "手机号不能为空", trigger: "change"},
                        {len: 11, message: "手机号必须为11位", trigger: "blur"}
                    ]
                },
                registerDetailInfo: {
                    realName: "",
                    sex: "",
                    university: "",
                    sno: "",
                    major: "",
                    grade: 1,
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
                /*this.$refs["step1Form"].validate((valid) => {
                    if (valid) {
                        this.nextStep();
                    }
                })*/
                this.nextStep();
            },
            submitStep2: function () {
                this.nextStep();
            },
            submitStep3: function(detailInfo) {
                let totalRegisterInfo = {
                    useramne: this.registerUser.username,
                    password: this.registerUser.password,
                    email: this.registerUser.email,
                    tel: this.registerUser.tel,
                    realName: detailInfo.realName,
                    sex: detailInfo.sex,
                    university: detailInfo.university,
                    sno: detailInfo.sno,
                    major: detailInfo.major,
                    grade: detailInfo.grade
                };

            }
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
