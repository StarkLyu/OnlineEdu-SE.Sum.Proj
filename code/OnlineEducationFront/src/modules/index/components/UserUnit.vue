<template>
    <div>
        <el-avatar :size="18" :src="user.avatarUrl"></el-avatar>
        <span class="name-font">{{ user.username }}</span>
    </div>
</template>

<script>
    export default {
        name: "UserUnit",
        props: {
            user: {
                username: String,
                avatarUrl: String
            }
        }
    }
</script>

<style scoped>
    .name-font {
        font-size: 14px;
    }
</style>
