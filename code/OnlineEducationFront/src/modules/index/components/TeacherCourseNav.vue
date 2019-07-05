<template>
    <div id="TeacherCourseNav">
        <el-row class="tac">
            <el-col :span="16">
                <h3>某课程</h3>
                <el-menu
                        class="el-menu-vertical-demo"
                        router="true">
                    <el-menu-item index="1" route="/teachercoursedetail">
                        <i class="el-icon-copy-document"></i>
                        <span slot="title">
                            课程详情
                        </span>
                    </el-menu-item>
                    <el-menu-item index="2" route="/teacherstudentmanage">
                        <i class="el-icon-copy-document"></i>
                        <span slot="title">
                            学生管理
                        </span>
                    </el-menu-item>
                    <el-menu-item index="3" route="/teachercoursemanage">
                        <i class="el-icon-copy-document"></i>
                        <span slot="title">
                            课程编辑
                        </span>
                    </el-menu-item>
                    <el-menu-item index="4" route="/teacherscoremanage">
                        <i class="el-icon-copy-document"></i>
                        <span slot="title">
                            成绩管理
                        </span>
                    </el-menu-item>
                    <el-menu-item index="5" route="">
                        <i class="el-icon-copy-document"></i>
                        <span slot="title">
                            作业管理
                        </span>
                    </el-menu-item>
                </el-menu>
            </el-col>
        </el-row>
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
</style>
