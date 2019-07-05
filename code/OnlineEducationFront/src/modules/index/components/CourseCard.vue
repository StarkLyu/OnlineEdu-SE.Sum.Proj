<template>
    <el-card shadow="hover">
        <div class="card-content">
            <div class="course-img float-left">
                <img src="../../../assets/logo.png" class="course-img">
            </div>
            <div>
                <div class="float-left course-title">
                    <strong>{{ courseInfo.courseName }}</strong>
                    <p>
                        <UserUnit :user="courseInfo.courseTeacher"></UserUnit>
                    </p>
                </div>
                <div class="float-right">
                    <div class="float-clear course-time">
                        <span>
                            <i class="el-icon-date"></i>
                            {{ courseInfo.courseTime1 }} ~ {{ courseInfo.courseTime2 }}
                        </span>
                        <p>
                            <el-progress type="line" :percentage="80"></el-progress>
                        </p>
                    </div>
                    <div class="enter-button">
                        <el-button>进入课程</el-button>
                    </div>
                </div>
            </div>
        </div>
    </el-card>
</template>

<script>
    import UserUnit from "./UserUnit";
    export default {
        name: "CourseCard",
        components: {UserUnit},
        props: {
            courseInfo: {
                courseName: String,
                courseTime1: String,
                courseTime2: String,
                courseTeacher: String,
            }
        },
    }
</script>

<style scoped>

    .course-img {
        width: 250px;
        height: 155px;
    }

    .course-title {
        padding-left: 40px;
        font-size: 23px;
    }

    .course-time {
        width: 200px;
        margin-top: 10px;
        font-size:14px;
    }

    .enter-button {
        margin-left: 50px;
        margin-top: 30px
    }
</style>