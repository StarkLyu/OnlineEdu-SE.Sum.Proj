<template>
    <div class="content-body-layout">
        <el-tabs type="border-card">
            <el-tab-pane>
                <span slot="label">用户设置</span>
                <UserCoreInfoManage></UserCoreInfoManage>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label">基本信息</span>
                <UserInfoManage
                        :userdata="userDetailInfo"
                        @submit-info="uploadDetailInfo"
                        ref="userInfoManage"
                ></UserInfoManage>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import UserInfoManage from "./UserInfoManage";
    import UserCoreInfoManage from "./UserCoreInfoManage";
    export default {
        name: "UserSetting",
        components: {UserCoreInfoManage, UserInfoManage},
        methods:{
            uploadDetailInfo: function (userDetailInfo) {
                alert("开始上传");
                this.$http.request({
                    url: "/api/users/info/modify",
                    method: "post",
                    data: userDetailInfo,
                    headers: this.requestHead
                }).then(() => {
                    alert("修改成功！");
                    this.$store.dispatch("loadUserInfo");
                    this.$refs['userInfoManage'].uploadDetailInfo(this.userDetailInfo);
                }).catch((error) => {
                    console.log(error.response);
                    alert("出错啦");
                })
            }
        },
        computed:{
            userDetailInfo: function () {
                let temp = this.$store.state.user.userInfo;
                return {
                    realName: temp.realName,
                    sex: temp.sex,
                    tel: temp.tel,
                    university: temp.university,
                    sno: temp.sno,
                    major: temp.major,
                    grade: temp.grade,
                    email: temp.email
                }
            },
            requestHead: function () {
                return this.$store.getters.authRequestHead;
            }
        }
    }
</script>

<style scoped>
    .setting-layout {
        width: 1000px;
        margin-left: 25px;
        margin-top: 30px;
    }
</style>