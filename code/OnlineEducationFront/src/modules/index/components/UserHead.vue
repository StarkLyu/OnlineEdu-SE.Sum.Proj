<template>
    <div class="head-style">
        <el-avatar :size="80"></el-avatar>
        <h2>{{ $store.state.user.username }}</h2>
    </div>
</template>

<script>
    export default {
        name: "UserHead",
        data() {
            return {
                sno: "Stark"
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
