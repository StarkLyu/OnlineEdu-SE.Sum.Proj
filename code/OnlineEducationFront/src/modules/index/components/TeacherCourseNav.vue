<template>
    <div>
        <div id="TeacherCourseNav" class="nav">
            <el-row class="tac">
                <el-col :span="16">
                    <span>
                        <h3>{{courseTitle}}</h3>
                    </span>
                    <el-menu
                            class="el-menu-vertical-demo"
                            router="true">
                        <el-menu-item index="1" route="/course/manager/detail">
                            <i class="el-icon-view"></i>
                            <span slot="title">
                            课程详情
                        </span>
                        </el-menu-item>
                        <el-menu-item index="2" route="/course/manager/student">
                            <i class="el-icon-user"></i>
                            <span slot="title">
                            选课学生
                        </span>
                        </el-menu-item>
                        <el-menu-item index="3" route="/course/manager/edit">
                            <i class="el-icon-edit-outline"></i>
                            <span slot="title">
                            课程编辑
                        </span>
                        </el-menu-item>
                        <el-menu-item index="4" route="/course/manager/announcement">
                            <i class="el-icon-message"></i>
                            <span slot="title">
                            课程公告
                        </span>
                        </el-menu-item>
                        <el-menu-item index="5" route="/course/manager/BT">
                            <i class="el-icon-folder"></i>
                            <span slot="title">
                            课程资源
                        </span>
                        </el-menu-item>
                        <el-menu-item index="6" route="/course/manager/score">
                            <i class="el-icon-data-analysis"></i>
                            <span slot="title">
                            成绩管理
                        </span>
                        </el-menu-item>
                        <el-menu-item index="7" route="/course/manager/assignment">
                            <i class="el-icon-notebook-1"></i>
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
        name: "TeacherCourseNav",

        data(){
            return{
                courseTitle:"软件工程导论",
            }
        }
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
