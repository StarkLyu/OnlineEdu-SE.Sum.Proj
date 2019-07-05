<template>
    <div class="info-form">
        <el-form label-position="right" label-width="100px">
            <el-form-item label="用户名">
                <el-input disabled v-model="userInfo.userName"></el-input>
            </el-form-item>
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
            <el-form-item label="专业">
                <el-input v-model="userInfo.major"></el-input>
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="userInfo.tel"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="userInfo.email"></el-input>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "UserInfoManage",
        data() {
            return {
                userInfo: {
                    userName: "",
                    realName: "",
                    sex: "",
                    university: "",
                    sno: 0,
                    major: "",
                    tel: "",
                    email: ""
                }
            }
        }
    }
</script>

<style scoped>
    .info-form {
        width: 500px
    }
</style>