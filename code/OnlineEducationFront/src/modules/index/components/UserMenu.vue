<template>
    <el-menu class="menu-style" router>
        <el-menu-item index="/user/course">我的课程</el-menu-item>
        <el-menu-item index="/user/info">个人设置</el-menu-item>
        <el-menu-item>退出登录</el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "UserMenu"
    }
</script>

<style scoped>
    .menu-style {
        text-align: center;
    }
</style>