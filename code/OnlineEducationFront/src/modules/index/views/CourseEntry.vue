<template>
    <div>
        <div class="float-left left-div">
            <img class="img-size" :src="courseInfo.imgUrl">
            <el-button class="entry-button" type="primary" @click="attendCourse">
                <h2>加入课程</h2>
            </el-button>
        </div>
        <div class="float-right right-div">
            <CourseInfo :course-info="courseInfo"></CourseInfo>
        </div>
    </div>
</template>

<script>
    import CourseInfo from "../components/CourseInfo";
    export default {
        name: "CourseEntry",
        components: {CourseInfo},
        props: {
            courseId: String
        },
        data() {
            return {
                courseInfo: {
                    imgUrl: "http://202.120.40.8:30382/online-edu/static/210-avatar/210-avatar.jpg",
                    id: Number,
                    title: "软件工程暑假加班",
                    teacher: "JBoss",
                    startDate: "2019-07-02",
                    endDate: "2019-08-02",
                    state: String
                }
            }
        },
        methods: {
            attendCourse: function () {
                let userId = this.$store.state.user.id;
                this.$http.request({
                    url: this.$store.getters.getCourseUrl + "pick",
                    method: "post",
                    params: {
                        id: userId
                    },
                    headers: this.$store.getters.authRequestHead
                }).then(() => {
                    alert("选课成功！");
                    this.$router.push("/course/student/info");
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                })
            }
        },
        mounted() {
            this.courseInfo = this.$store.getters.getCourseInfo;
        }
    }
</script>

<style scoped>
    .img-size {
        width: 400px;
        height: 247px;
    }

    .left-div {
        width: 400px;
    }

    .entry-button {
        width: 200px;
        margin-left: 100px;
        margin-right: auto;
        margin-top: 50px;
    }

    .right-div {
        width: 900px;
    }
</style>
