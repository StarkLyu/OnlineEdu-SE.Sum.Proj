<template>
    <div>
        <el-header>
            <h1 class="titlesytle">编辑课程信息</h1>
        </el-header>
        <el-main>
            <el-form ref="form" :model="CourseForm" label-width="80px" class="formstyle">
                <el-form-item label="课程号">
                    <el-input v-model="CourseForm.courseId"></el-input>
                </el-form-item>
                <el-form-item label="课程名称">
                    <el-input v-model="CourseForm.courseName"></el-input>
                </el-form-item>
                <el-form-item label="授课教师">
                    <el-input v-model="CourseForm.courseTeacher"></el-input>
                </el-form-item><el-form-item label="课程助教">
                <el-input v-model="CourseForm.courseAssistance"></el-input>
            </el-form-item>
                <el-form-item label="上课地点">
                    <el-select v-model="CourseForm.courseRoom" placeholder="请选择上课地点">
                        <el-option label="东上院211" value="shanghai"></el-option>
                        <el-option label="上院211" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="上课时间">
                    <el-col :span="11">
                        <el-time-picker placeholder="选择开始时间" v-model="CourseForm.courseTime1" style="width: 100%;"></el-time-picker>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                        <el-time-picker placeholder="选择结束时间" v-model="CourseForm.courseTime2" style="width: 100%;"></el-time-picker>
                    </el-col>
                </el-form-item>
                <el-form-item label="选择周数">
                    <el-checkbox-group v-model="CourseForm.courseWeek">
                        <el-checkbox label="1" name="type"></el-checkbox>
                        <el-checkbox label="2" name="type"></el-checkbox>
                        <el-checkbox label="3" name="type"></el-checkbox>
                        <el-checkbox label="4" name="type"></el-checkbox>
                        <el-checkbox label="5" name="type"></el-checkbox>
                        <el-checkbox label="6" name="type"></el-checkbox>
                        <el-checkbox label="7" name="type"></el-checkbox>
                        <el-checkbox label="8" name="type"></el-checkbox>
                        <el-checkbox label="9" name="type"></el-checkbox>
                        <el-checkbox label="10" name="type"></el-checkbox>
                        <el-checkbox label="11" name="type"></el-checkbox>
                        <el-checkbox label="12" name="type"></el-checkbox>
                        <el-checkbox label="13" name="type"></el-checkbox>
                        <el-checkbox label="14" name="type"></el-checkbox>
                        <el-checkbox label="15" name="type"></el-checkbox>
                        <el-checkbox label="16" name="type"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="CourseForm.courseDes"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">修改</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseManage",

        data(){
            return{
                CourseForm:{
                    courseId:"",
                    courseName:"",
                    courseTeacher:"",
                    courseAssistance:"",
                    courseTime1:"",
                    courseTime2:"",
                    courseWeek:[],
                    courseRoom:"",
                    courseDes:"",
                },
            }
        },

        methods:{
            onSubmit() {
                alert('submit!');
            }
        }
    }
</script>

<style scoped>
    @import "/src/assets/div-layout.css";

    .formstyle {
        margin-top: 20px;
        margin-right: 20px;
        width: 70%;
        padding-top: 10px;
        padding-bottom: 10px;
    }

    .titlesytle {
        text-align: center;
        padding-top: 20px;
        margin-top: 30px;
    }
</style>
