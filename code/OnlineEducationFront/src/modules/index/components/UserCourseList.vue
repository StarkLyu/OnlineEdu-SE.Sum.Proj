<template>
    <div class="content-body-layout">
        <el-tabs type="border-card">
            <el-tab-pane v-if="isTeacher">
                <span slot="label">教授课程</span>
                <CourseCard
                        v-for="(course, index) in teachCourses"
                        :key="index"
                        :course-info="course"
                ></CourseCard>
            </el-tab-pane>
            <el-tab-pane v-if="isStudent">
                <span slot="label">学习课程</span>
                <CourseCard
                        v-for="(course, index) in learnCourses"
                        :key="index"
                        :course-info="course"
                ></CourseCard>
            </el-tab-pane>
            <el-tab-pane v-if="isStudent">
                <span slot="label">担任助教</span>
                <CourseCard
                        v-for="(course, index) in assistCourses"
                        :key="index"
                        :course-info="course"
                ></CourseCard>
            </el-tab-pane>
            <el-tab-pane v-if="isTeacher">
                <span slot="label">创建课程</span>
                <TeacherCreateCourse></TeacherCreateCourse>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import CourseCard from "./CourseCard";
    import { mapGetters } from "vuex"
    import TeacherCreateCourse from "./TeacherCreateCourse";

    export default {
        name: "UserCourseList",
        components: {TeacherCreateCourse, CourseCard},
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
            ...mapGetters([
                'assistCourses',
                'learnCourses',
                'teachCourses',
                'userRole',
                'isTeacher',
                'isStudent'
            ]),
        },
        mounted() {
            this.courseList = this.$store.state.user.userInfo.courses;
        }
    }
</script>

<style scoped>

</style>
