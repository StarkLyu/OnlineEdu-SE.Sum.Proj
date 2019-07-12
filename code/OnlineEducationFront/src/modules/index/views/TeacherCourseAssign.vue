<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程作业</h1>
        </el-header>
        <el-main>
            <AssignmentSingle :SingleQuestion="SingleQuestion"></AssignmentSingle>
            <AssignmentMulti :MultiQuestion="MultiQuestion"></AssignmentMulti>
            <AssignmentJudge :JudgeQuestion="JudgeQuestion"></AssignmentJudge>
        </el-main>
    </div>
</template>

<script>
    import AssignmentSingle from "../components/AssignmentSingle"
    import AssignmentMulti from "../components/AssignmentMulti"
    import AssignmentJudge from "../components/AssignmentJudge"
    export default {
        name: "TeacherCourseAssign",

        components:{
            AssignmentSingle,
            AssignmentMulti,
            AssignmentJudge,
        },

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
                        correctAnswer:"A",
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
                        correctAnswer:"C",
                    }
                ],

                MultiQuestion:[
                    {
                        key:1,
                        title:"第一题：这是一道多选题，请选择下列选项。",
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
                        answer:[],
                        correctAnswer:"AB",
                    },
                    {
                        key:2,
                        title:"第二题：这是一道多选题，请选择下列选项。",
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
                        answer:[],
                        correctAnswer:"BCD",
                    }
                ],

                JudgeQuestion:[
                    {
                        key:1,
                        title:"第一题：这是一道判断题，请选择下列选项。",
                        answer:"",
                        correctAnswer:"正确",
                    },
                    {
                        key:2,
                        title:"第二题：这是一道判断题，请选择下列选项。",
                        answer:"",
                        correctAnswer:"错误",
                    }
                ],
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
</style>
