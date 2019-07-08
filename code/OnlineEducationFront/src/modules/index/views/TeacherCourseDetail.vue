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
        </el-main>
    </div>
</template>

<script>
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
                    courseDes:"数据库管理已经从一种专门的计算机应用发展为现代计算环境的一个核心部分。因此，有关数据库系统的知识已经成为计算机教育中的一个必不可少的部分，也是软件工程专业学生必须掌握的基本技术。本课程主要讲述数据库管理的基本概念，包括数据库设计、数据库语言及数据库管理等多个方面。",
                },
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
    }
</style>
