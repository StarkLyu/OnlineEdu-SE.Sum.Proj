<template>
    <div class="content-body-layout">
        <el-tabs type="border-card">
            <el-tab-pane>
                <span slot="label">全部课程</span>
                <CourseCard
                        v-for="(course, index) in courseList"
                        :key="index"
                        :course-info="course"
                ></CourseCard>
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
            <el-tab-pane>

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
                        courseName: "数据库没成绩导论",
                        courseTime1: "2019-07-02",
                        courseTime2: "2019-08-02",
                        courseTeacher: "JBoss"
                    }
                ]
            }
        },
        computed: {
            userRole: function () {
                return this.$store.getters.userRole;
            },

        },
        mounted() {
            this.courseList = this.$store.state.user.userInfo.courses;
        }
    }
</script>

<style scoped>

</style>
