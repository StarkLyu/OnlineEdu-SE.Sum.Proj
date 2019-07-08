<template>
    <div>
        <div id="TeacherCourseNav" class="nav">
            <el-row class="tac">
                <el-col :span="16">
                    <h3>某课程</h3>
                    <el-menu
                            class="el-menu-vertical-demo"
                            router="true">
                        <el-menu-item index="1" route="/course/manager/detail">
                            <i class="el-icon-copy-document"></i>
                            <span slot="title">
                            课程详情
                        </span>
                        </el-menu-item>
                        <el-menu-item index="2" route="/course/manager/student">
                            <i class="el-icon-copy-document"></i>
                            <span slot="title">
                            选课学生
                        </span>
                        </el-menu-item>
                        <el-menu-item index="3" route="/course/manager/edit">
                            <i class="el-icon-copy-document"></i>
                            <span slot="title">
                            课程编辑
                        </span>
                        </el-menu-item>
                        <el-menu-item index="4" route="/course/manager/annoucement">
                            <i class="el-icon-copy-document"></i>
                            <span slot="title">
                            课程公告
                        </span>
                        </el-menu-item>
                        <el-menu-item index="5" route="/course/manager/BT">
                            <i class="el-icon-copy-document"></i>
                            <span slot="title">
                            课程资源
                        </span>
                        </el-menu-item>
                        <el-menu-item index="6" route="/course/manager/score">
                            <i class="el-icon-copy-document"></i>
                            <span slot="title">
                            成绩管理
                        </span>
                        </el-menu-item>
                        <el-menu-item index="7" route="/course/manager/assignment">
                            <i class="el-icon-copy-document"></i>
                            <span slot="title">
                            课程作业
                        </span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </el-row>
        </div>
        <div class="showbody">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseNav"
    }
</script>

<style scoped>
    h3 {
        text-align: center;
    }

    .nav {
        float: left;
        width: 15%;
        margin-top: 20px;
    }

    .showbody {
        float: left;
        width: 85%;
    }
</style>
