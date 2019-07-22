<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程详情</h1>
        </el-header>
        <el-main>
            <div class="courseimg">
                <img src="../../../assets/logo.png" height="200" width="200"/>
            </div>
            <div class="coursedes">
<!--                <p>-->
<!--                    课程号：-->
<!--                    <span class="detail">-->
<!--                        {{CourseForm.id}}-->
<!--                    </span>-->
<!--                </p>-->
                <p>
                    课程名称：
                    <span class="detail">
                        {{CourseForm.courseTitle}}
                    </span>
                </p>
<!--                <p>-->
<!--                    授课教师：-->
<!--                    <span class="detail">-->
<!--                        {{CourseForm.courseTeacher}}-->
<!--                    </span>-->
<!--                </p>-->
<!--                <p>-->
<!--                    上课时间：-->
<!--                    <span class="detail">-->
<!--                        {{CourseForm.courseTime}}-->
<!--                    </span>-->
<!--                </p>-->
<!--                <p>-->
<!--                    上课地点：-->
<!--                    <span class="detail">-->
<!--                        {{CourseForm.courseRoom}}-->
<!--                    </span>-->
<!--                </p>-->
<!--                <p>-->
<!--                    上课助教：-->
<!--                    <span class="detail">-->
<!--                        {{CourseForm.courseAssistance}}-->
<!--                    </span>-->
<!--                </p>-->
<!--                <p>-->
<!--                    课程详细信息：-->
<!--                    <span class="detail">-->
<!--                        {{CourseForm.courseDes}}-->
<!--                    </span>-->
<!--                </p>-->
            </div>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseDetail",

        data(){
            return{
                CourseForm:this.$store.getters.getCourseInfo,
            }
        },
    }
</script>

<style scoped>
    @import "/src/assets/div-layout.css";

    .titlesytle {
        text-align: center;
        padding-top: 20px
    }

    .courseimg {
        float: left;
        width: 25%;
    }

    .coursedes {
        float: right;
        width: 70%;
        font-size: large;
    }

    .detail {
        font-size: small;
    }
</style>
