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
                ],
                teachCourses: [],
                learnCourses: [],
                assistCourses: [],
            }
        },
        methods: {
            getTeachCourses: function () {
                this.$http.request({
                    url: "/api/users/info/courses/teach",
                    method: "get",
                    headers: this.$store.getters.authRequestHead
                }).then((response) => {
                    this.teachCourses = response.data;
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                })
            },
            getAssistCourses: function () {
                this.$http.request({
                    url: "/api/users/info/courses/assist",
                    method: "get",
                    headers: this.$store.getters.authRequestHead
                }).then((response) => {
                    this.assistCourses = response.data;
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                })
            },
            getLearnCourses: function () {
                this.$http.request({
                    url: "/api/users/info/courses/learn",
                    method: "get",
                    headers: this.$store.getters.authRequestHead
                }).then((response) => {
                    this.learnCourses = response.data;
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                })
            }
        },
        computed: {
            ...mapGetters([
                'userRole'
                // 'isTeacher',
                // 'isStudent'
            ]),
            isTeacher: function() {
                if (this.userRole.indexOf("ROLE_TEACHING_ADMIN") !== -1) return true;
                else return false;
            },
            isStudent: function() {
                if (this.userRole.indexOf("ROLE_USER") !== -1) return true;
                else return false;
            }
        },
        mounted() {
            if (this.isTeacher) {
                this.getTeachCourses();
            }
            if (this.isStudent) {
                this.getLearnCourses();
                this.getAssistCourses();
            }
        }
    }
</script>

<style scoped>

</style>
