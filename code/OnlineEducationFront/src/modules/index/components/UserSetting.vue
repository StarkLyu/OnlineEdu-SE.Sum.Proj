<template>
    <div class="content-body-layout">
        <el-tabs type="border-card">
            <el-tab-pane>
                <span slot="label">用户设置</span>
                <UserCoreInfoManage></UserCoreInfoManage>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label">基本信息</span>
                <UserInfoManage></UserInfoManage>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import UserInfoManage from "./UserInfoManage";
    import UserCoreInfoManage from "./UserCoreInfoManage";
    export default {
        name: "UserSetting",
        components: {UserCoreInfoManage, UserInfoManage}
    }
</script>

<style scoped>
    .setting-layout {
        width: 1000px;
        margin-left: 25px;
        margin-top: 30px;
    }
</style>