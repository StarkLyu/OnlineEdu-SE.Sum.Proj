<template>
    <div>
        <el-row class="row-layout">
            <el-col :span="24">
                <h2>{{ courseInfo.courseName }}</h2>
            </el-col>
        </el-row>
        <el-row class="row-layout">
            <el-col :span="12">
                <div class="float-left">教师：</div>
                <UserUnit :user="courseInfo.courseTeacher" class="float-left"></UserUnit>
            </el-col>
            <el-col :span="12">
                <div class="float-left">助教：</div>
                <div v-for="(assistant, index) in courseInfo.courseAssistance"
                     :key="index"
                     class="float-left assi-unit"
                >
                    <UserUnit :user="assistant"></UserUnit>
                </div>
            </el-col>
        </el-row>
        <el-row class="row-layout">
            <el-col :span="12">
                时间：{{ courseInfo.courseTime1}} ~ {{ courseInfo.courseTime2 }}
            </el-col>
            <el-col :span="12">
                地点：{{ courseInfo.courseRoom }}
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="24">
                <h4>课程介绍</h4>
                <pre>{{ courseInfo.courseDes }}</pre>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import UserUnit from "./UserUnit";
    export default {
        name: "CourseInfo",
        components: {UserUnit},
        props: {
            courseInfo: {
                courseId: String,
                courseName: String,
                courseTeacher: String,
                courseAssistance: Array,
                courseTime1: String,
                courseTime2: String,
                courseWeek: Array,
                courseRoom: String,
                courseDes: String,
            }
        }
    }
</script>

<style scoped>
    .assi-unit {
        margin-right: 20px;
    }

    .row-layout {
        margin-bottom: 20px;
    }
</style>