<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程作业</h1>
        </el-header>
        <el-main>
            <AssignmentSingle :SingleQuestion="SingleQuestion"></AssignmentSingle>
        </el-main>
    </div>
</template>

<script>
    import AssignmentSingle from "../components/AssignmentSingle"
    export default {
        name: "TeacherCourseAssign",

        data(){
            return{
                SingleQuestion:[
                    {
                        key:1,
                        title:"第一题：这是一道单选题，请选择下列选项。",
                        choices:[
                            {
                                tag:"A",
                                content:"这是第一个选项",
                            },
                            {
                                tag:"B",
                                content:"这是第二个选项",
                            },
                            {
                                tag:"C",
                                content:"这是第三个选项",
                            },
                            {
                                tag:"D",
                                content:"这是第四个选项",
                            },
                        ],
                        answer:"",
                    },
                    {
                        key:2,
                        title:"第二题：这是一道单选题，请选择下列选项。",
                        choices:[
                            {
                                tag:"A",
                                content:"这是第一个选项",
                            },
                            {
                                tag:"B",
                                content:"这是第二个选项",
                            },
                            {
                                tag:"C",
                                content:"这是第三个选项",
                            },
                            {
                                tag:"D",
                                content:"这是第四个选项",
                            },
                        ],
                        answer:"",
                    }
                ]
            }
        },

        components:{
            AssignmentSingle,
        }
    }
</script>

<style scoped>
    @import "/src/assets/div-layout.css";

    .titlesytle {
        text-align: center;
        padding-top: 20px
    }
</style>
