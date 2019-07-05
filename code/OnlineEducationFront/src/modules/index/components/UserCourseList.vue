<template>
    <div class="course-layout">
        <el-tabs type="border-card">
            <el-tab-pane>
                <span slot="label">全部课程</span>
                <CourseCard :course-info="courseList[0]"></CourseCard>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label">正在学习</span>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label">未开始</span>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label">已结束</span>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import CourseCard from "./CourseCard";
    export default {
        name: "UserCourseList",
        components: {CourseCard},
        data() {
            return {
                courseList: [
                    {
                        courseName: "软件工程",
                        courseTime1: "2019-07-02",
                        courseTime2: "2019-08-02",
                        courseTeacher: "JBoss"
                    }
                ]
            }
        }
    }
</script>

<style scoped>

    .course-layout {
        width: 1000px;
        margin-left: 20px;
        margin-top: 30px;
    }

</style>