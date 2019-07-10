<template>
    <div>
        <el-avatar :size="18"></el-avatar>
        <span class="name-font">{{ user }}</span>
    </div>
</template>

<script>
    export default {
        name: "UserUnit",
        props: {
            user: String
        }
    }
</script>

<style scoped>
    .name-font {
        font-size: 14px;
    }
</style>