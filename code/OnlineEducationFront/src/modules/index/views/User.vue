<template>
    <el-container>
        <el-aside>
            <UserHead></UserHead>
            <UserMenu></UserMenu>
        </el-aside>
        <eld-main>
            <router-view></router-view>
        </eld-main>
    </el-container>
</template>

<script>
    import UserHead from "../components/UserHead";
    import UserMenu from "../components/UserMenu";
    export default {
        name: "User",
        components: {UserMenu, UserHead}
    }
</script>

<style scoped>

</style>