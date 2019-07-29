<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程作业</h1>
        </el-header>
        <el-main>
            <el-table
                    :data="AssignData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                    style="width: 100%">
                <el-table-column
                        prop="start"
                        label="开始时间"
                        sortable
                        min-width="25%">
                </el-table-column>
                <el-table-column
                        prop="end"
                        label="结束时间"
                        sortable
                        min-width="25%">
                </el-table-column>
                <el-table-column
                        prop="title"
                        label="作业名"
                        sortable
                        min-width="40%">
                </el-table-column>
                <el-table-column
                        min-width="40%">
                    <template slot="header">
                        <el-button type="primary" icon="el-icon-plus" @click="handleAdd">添加作业</el-button>
                    </template>
                    <template slot-scope="scope">
                        <el-button size="small"
                                   type="success"
                                   icon="el-icon-document-checked"
                                   @click="handleCorrection(scope.$index, scope.row)">
                            批改
                        </el-button>
                        <el-button size="small"
                                   icon="el-icon-edit"
                                   @click="handleEdit(scope.$index, scope.row)">
                            编辑
                        </el-button>
                        <el-button size="small"
                                   type="danger"
                                   icon="el-icon-delete"
                                   @click="handleDelete(scope.$index, scope.row)">
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
        <el-dialog :title="'作业'"
                   :visible.sync="AssignVisible"
                   :lock-scroll="false"
                   top="5%"
                   width="80%">
            <el-form :model="AssignEditForm" label-width="80px" ref="AssignEditForm">
                <el-form-item label="作业名">
                    <el-input type="text" v-model="AssignEditForm.title"></el-input>
                </el-form-item>
                <el-form-item label="作业时间">
                    <el-col :span="11">
                        <el-date-picker placeholder="选择开始时间"
                                        type="datetime"
                                        v-model="AssignEditForm.start"
                                        style="width: 100%;">
                        </el-date-picker>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                        <el-date-picker placeholder="选择结束时间"
                                        type="datetime"
                                        v-model="AssignEditForm.end"
                                        style="width: 100%;">
                        </el-date-picker>
                    </el-col>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input type="textarea" v-model="AssignEditForm.description"></el-input>
                </el-form-item>
            </el-form>
<!--            问题table-->
            <el-table :data="questions" ref="multipleTable" height="300px" @selection-change="handleSelectionChange">
                <el-table-column type="selection" min-width="10%"></el-table-column>
                <el-table-column property="questionType" label="题型" sortable min-width="20%"></el-table-column>
                <el-table-column property="question" label="题目" min-width="40%" show-overflow-tooltip="true"></el-table-column>
                <el-table-column property="score" label="分值" min-width="15%">
                    <template scope="scope">
                        <el-input placeholder="请输入分值" v-model="scope.row.score" type="number"></el-input>
                    </template>
                </el-table-column>
            </el-table>
            <span slot="footer" class="el-dialog__footer">
                <el-button @click.native="AssignVisible=false">取消</el-button>
                <el-button v-if="AssignStatus==='create'" type="primary" @click="createAssign">添加</el-button>
                <el-button v-else type="primary" @click="updateAssign">修改</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import Bus from '../bus.js'
    export default {
        name: "TeacherCourseAssign",

        data(){
            return{
                search:"",

                AssignData:this.$store.getters.getCourseInfo.papers,

                AssignVisible:false,

                AssignEditForm: {
                    description:"",
                    title:"",
                    start:"",
                    end:"",
                    questionFormList:[
                        {
                            questionId:"",
                            questionNumber:1,
                            score:0,
                        }
                    ],
                },

                textMap: {
                    update: "Edit",
                    create: "Create"
                },

                AssignStatus:"",

                questions:[],

                multipleSelection:[],
            }
        },

        methods:{
            // 显示增加作业弹窗
            handleAdd(){
                this.AssignEditForm=[];
                this.AssignStatus="create";
                this.AssignVisible=true;
            },

            // 批改作业
            handleCorrection:function(index, row){
                Bus.$emit('assignId', row.id);
                this.$router.push("/course/manager/correction");
            },

            // 显示编辑作业弹窗
            handleEdit:function(index, row){
                this.AssignEditForm = Object.assign({}, row);
                this.AssignStatus="update";
                this.AssignVisible=true;
            },

            // 删除作业
            handleDelete:function (index, row) {
                alert(row.title+"已删除");
            },

            // 新增一份作业
            createAssign(){
                // 修改问题格式
                var finalQuestion=[];
                for (let x=0; x<this.multipleSelection.length; x++){
                    var tempquestion={
                        questionId:"",
                        questionNumber:1,
                        score:0,
                    };
                    tempquestion.questionId=this.multipleSelection[x].id;
                    tempquestion.score=this.multipleSelection[x].score;
                    finalQuestion.push(tempquestion);
                }
                this.AssignEditForm.questionFormList=finalQuestion;
                console.log(this.AssignEditForm);
                this.$http.request({
                    url: '/api/courses/'+this.$store.getters.getCourseId+'/papers',
                    method: "post",
                    headers: this.$store.getters.authRequestHead,
                    data:
                        {
                            title:this.AssignEditForm.title,
                            description:this.AssignEditForm.description,
                            start:this.AssignEditForm.start,
                            end:this.AssignEditForm.end,
                            questionFormList:finalQuestion,
                        }
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error);
                        alert("请求失败");
                    });

                this.AssignVisible=false;
            },

            // 编辑作业
            updateAssign(){

            },

            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        },

        mounted() {
            // 初始化questions
            var questionAll=this.$store.getters.getCourseInfo.coursePrototype.questions;
            for (let x=0; x<questionAll.length; x++){
                var tempquestion={
                    id:"",
                    question:"",
                    questionType:"",
                    score:"",
                };
                tempquestion.id=questionAll[x].id;
                tempquestion.questionType=questionAll[x].questionType;
                tempquestion.question=questionAll[x].question;
                tempquestion.score=0;
                this.questions.push(tempquestion);
            }
            // console.log(this.questions);
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
