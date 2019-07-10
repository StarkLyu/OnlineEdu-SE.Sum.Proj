<template>
    <div>
        <div v-if="loginStatus">
            <el-avatar></el-avatar>{{ userName }}
        </div>
        <div v-else>
            <el-avatar icon="el-icon-user-solid"></el-avatar>
            <el-link type="primary" href="/#/login">登录</el-link>/<el-link type="primary" href="/#/register">注册</el-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: "HeadSideUser",
        data() {
            return {
                userName: "STARK",
                loginStatus: false,
            }
        }
    }
</script>

<style scoped>

</style>