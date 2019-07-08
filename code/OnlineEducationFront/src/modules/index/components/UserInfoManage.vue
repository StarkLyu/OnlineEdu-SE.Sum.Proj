<template>
    <div class="info-form">
        <el-form label-position="left" label-width="100px">
            <el-form-item label="真实姓名">
                <el-input v-model="userInfo.realName"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-radio-group v-model="userInfo.sex">
                    <el-radio label="M">男</el-radio>
                    <el-radio label="W">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="学校">
                <el-input v-model="userInfo.university"></el-input>
            </el-form-item>
            <el-form-item label="学号">
                <el-input v-model="userInfo.sno"></el-input>
            </el-form-item>
            <el-form-item label="年级">
                <el-select v-model="userInfo.grade">
                    <el-option
                            v-for="grade in gradeList"
                            :key="grade.value"
                            :value="grade.value"
                            :label="grade.label"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="专业">
                <el-input v-model="userInfo.major"></el-input>
            </el-form-item>
            <el-form-item>
                <div class="bottom-buttons">
                    <div class="float-left">
                        <el-button @click="submitInfo">提交</el-button>
                    </div>
                    <div class="float-right">
                        <el-button>重置</el-button>
                    </div>
                </div>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "UserInfoManage",
        props: {
            userdata: Object
        },
        data() {
            return {
                userInfo: {
                    realName: "",
                    sex: "",
                    university: "",
                    sno: "",
                    major: "",
                    grade: 1,
                },
                gradeList: [
                    {
                        value: 1,
                        label: "大一"
                    },
                    {
                        value: 2,
                        label: "大二"
                    },
                    {
                        value: 3,
                        label: "大三"
                    },
                    {
                        value: 4,
                        label: "大四"
                    },
                    {
                        value: 5,
                        label: "研一"
                    },
                    {
                        value: 6,
                        label: "研二"
                    }
                ]
            }
        },
        methods: {
            submitInfo: function () {
                this.$emit('submit-info', this.userInfo);
            }
        },
        mounted() {
            this.userInfo = this.userdata;
        }
    }
</script>

<style scoped>
    .info-form {
        width: 500px
    }

    .bottom-buttons {
        width: 200px;
        margin-left: auto;
        margin-right: auto;
    }
</style>