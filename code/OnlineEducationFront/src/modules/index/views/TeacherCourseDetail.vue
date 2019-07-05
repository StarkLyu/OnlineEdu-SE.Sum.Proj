<template>
    <div>
        <div class="nav">
            <TeacherCourseNav></TeacherCourseNav>
        </div>
        <div class="showbody">
            <div class="margintop">
                <h1 class="titlesytle">课程详情</h1>
                <div class="courseimg">
                    <img src="../../../assets/logo.png" height="200" width="200"/>
                </div>
                <div class="coursedes">
                    <p>
                        课程号：
                        <span>
                        {{CourseForm.courseId}}
                        </span>
                    </p>
                    <p>
                        课程名称：
                        <span>
                        {{CourseForm.courseName}}
                    </span>
                    </p>
                    <p>
                        授课教师：
                        <span>
                        {{CourseForm.courseTeacher}}
                    </span>
                    </p>
                    <p>
                        上课时间：
                        <span>
                        {{CourseForm.courseTime}}
                    </span>
                    </p>
                    <p>
                        上课地点：
                        <span>
                        {{CourseForm.courseRoom}}
                    </span>
                    </p>
                    <p>
                        上课助教：
                        <span>
                        {{CourseForm.courseAssistance}}
                    </span>
                    </p>
                    <p>
                        课程详细信息：
                        <span>
                        {{CourseForm.courseDes}}
                    </span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import TeacherCourseNav from "../components/TeacherCourseNav"
    export default {
        name: "TeacherCourseDetail",

        data(){
            return{
                CourseForm:{
                    courseId: "CS-524",
                    courseName: "java设计思想",
                    courseTime: "1-16周 12:00-14:00",
                    courseRoom: "东下院201",
                    courseTeacher: "zang",
                    courseAssistance:"张，刘，俄",
                    courseDes:"hwhfquowfeuoqoue",
                },
            }
        },

        components:{
            TeacherCourseNav,
        }
    }
</script>

<style scoped>
    @import "/src/assets/div-layout.css";

    .nav {
        float: left;
        width: 15%;
        margin-top: 20px;
    }

    .showbody {
        float: left;
        width: 85%;
    }

    .margintop {
        margin-top: 30px
    }

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
    }
</style>
