<template>
    <div class="head-style">
        <el-avatar :size="80"></el-avatar>
        <h2>{{ $store.state.user.userName }}</h2>
    </div>
</template>

<script>
    export default {
        name: "UserHead",
        data() {
            return {
                userId: "Stark"
            }
        }
    }
</script>

<style scoped>
    .head-style {
        text-align: center;
        padding-top: 30px;
    }
</style>