<template>
    <div>
        <el-header>
            <h1 class="titlesytle">第一份作业</h1>
        </el-header>
        <el-main>
<!--            作业显示部分-->
            <div>
                <el-button @click="showSingleDialog">添加单选题</el-button>
                <el-button @click="showMultiDialog">添加多选题</el-button>
                <el-button @click="showJudgeDialog">添加判断题</el-button>
                <el-button>添加主观题</el-button>
                <div v-for="single in oneAssignment.quesitons" :key="single.key">
                    <div v-if="single.type==='single'">
                        <AssignmentSingle :single="single"></AssignmentSingle>
                        <el-button size="small">编辑</el-button>
                        <el-button size="small" type="danger">删除</el-button>
                    </div>
                </div>
                <div v-for="multi in oneAssignment.quesitons" :key="multi.key">
                    <div v-if="multi.type==='multi'">
                        <AssignmentMulti :multi="multi"></AssignmentMulti>
                        <el-button size="small">编辑</el-button>
                        <el-button size="small" type="danger">删除</el-button>
                    </div>
                </div>
                <div v-for="judge in oneAssignment.quesitons" :key="judge.key">
                    <div v-if="judge.type==='judge'">
                        <AssignmentJudge :judge="judge"></AssignmentJudge>
                        <el-button size="small">编辑</el-button>
                        <el-button size="small" type="danger">删除</el-button>
                    </div>
                </div>
            </div>
<!--            添加单选题弹窗部分-->
            <el-dialog :title="'单选题'"
                       :visible.sync="singleVisible"
                       top="5%">
                <el-form :model="singleEditForm" label-width="80px" ref="singleEditForm">
                    <el-form-item label="题目">
                        <el-input type="textarea" v-model="singleEditForm.title"></el-input>
                    </el-form-item>
                    <el-form-item
                            v-for="(choice, index) in singleEditForm.choices"
                            :label="'选项'+(index+1)"
                            :key="choice.key"
                            :prop="'choice.' + index + '.content'"
                            :rules="{required: true, message: '内容不能为空', trigger: 'blur'}">
                        <el-input v-model="choice.content"></el-input><el-button @click.prevent="removeChoiceSingle(choice)">删除</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="addChoiceSingle">新增选项</el-button>
                    </el-form-item>
                    <el-form-item label="答案">
                        <el-input v-model="singleEditForm.correctAnswer" placeholder="请输入大写字母ABCD……"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="el-dialog__footer">
                    <el-button @click.native="singleVisible=false">取消</el-button>
                    <el-button type="primary" @click="createSingleData">编辑</el-button>
                </span>
            </el-dialog>
<!--            添加多选题弹窗部分-->
            <el-dialog :title="'多选题'"
                       :visible.sync="multiVisible"
                       top="5%">
                <el-form :model="multiEditForm" label-width="80px" ref="multiEditForm">
                    <el-form-item label="题目">
                        <el-input type="textarea" v-model="multiEditForm.title"></el-input>
                    </el-form-item>
                    <el-form-item
                            v-for="(choice, index) in multiEditForm.choices"
                            :label="'选项'+(index+1)"
                            :key="choice.key"
                            :prop="'choice.' + index + '.content'"
                            :rules="{required: true, message: '内容不能为空', trigger: 'blur'}">
                        <el-input v-model="choice.content"></el-input><el-button @click.prevent="removeChoiceMulti(choice)">删除</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="addChoiceMulti">新增选项</el-button>
                    </el-form-item>
                    <el-form-item label="答案">
                        <el-input v-model="multiEditForm.correctAnswer" placeholder="请输入大写字母ABCD……"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="el-dialog__footer">
                    <el-button @click.native="multiVisible=false">取消</el-button>
                    <el-button type="primary" @click="createSingleData">编辑</el-button>
                </span>
            </el-dialog>
<!--            添加判断题弹窗部分-->
            <el-dialog :title="'多选题'"
                       :visible.sync="judgeVisible"
                       top="5%">
                <el-form :model="judgeEditForm" label-width="80px" ref="judgeEditForm">
                    <el-form-item label="题目">
                        <el-input type="textarea" v-model="multiEditForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="答案">
                        <el-input v-model="judgeEditForm.correctAnswer" placeholder="请输入正确或错误"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="el-dialog__footer">
                    <el-button @click.native="judgeVisible=false">取消</el-button>
                    <el-button type="primary" @click="createSingleData">编辑</el-button>
                </span>
            </el-dialog>
        </el-main>
        <el-footer>
            <el-button @click="handleCancel">取消</el-button>
            <el-button type="primary">提交</el-button>
        </el-footer>
    </div>
</template>

<script>
    import AssignmentSingle from "../components/AssignmentSingle"
    import AssignmentMulti from "../components/AssignmentMulti"
    import AssignmentJudge from "../components/AssignmentJudge"
    export default {
        name: "TeacherCourseOneAssignment",

        components:{
            AssignmentSingle,
            AssignmentMulti,
            AssignmentJudge,
        },

        data(){
            return{
                singleVisible: false,

                multiVisible: false,

                judgeVisible: false,

                singleEditForm:{
                    key:"",
                    title:"",
                    choices:[
                        {
                            tag:"",
                            content:"",
                        }
                    ],
                    correctAnswer:'',
                },

                multiEditForm:{
                    key:"",
                    title:"",
                    choices:[
                        {
                            tag:"",
                            content:"",
                        }
                    ],
                    correctAnswer:'',
                },

                judgeEditForm:{
                    key:"",
                    title:"",
                    correctAnswer:"",
                },

                oneAssignment:{
                    assignTitle:"作业标题",
                    quesitons:[
                        {
                            type:"single",
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
                            type:"multi",
                            key:2,
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
                            type:"judge",
                            key:3,
                            title:"第一题：这是一道判断题，请选择下列选项。",
                            answer:"",
                            correctAnswer:"正确",
                        },
                    ],

                },

            }
        },

        methods:{
            handleCancel(){
                this.$router.push("/course/manager/assignment");
            },

            showSingleDialog(){
                this.singleVisible=true;
                this.singleEditForm={
                    key:"",
                        title:"",
                        choices:[
                        {
                            tag:"",
                            content:"",
                        }
                    ],
                        correctAnswer:'',
                }
            },

            showMultiDialog(){
                this.multiVisible=true;
                this.multiEditForm={
                    key:"",
                    title:"",
                    choices:[
                        {
                            tag:"",
                            content:"",
                        }
                    ],
                    correctAnswer:'',
                }
            },

            showJudgeDialog(){
                this.judgeVisible=true;
                this.judgeEditForm={
                    key:"",
                    title:"",
                    correctAnswer:'',
                }
            },

            createSingleData(){
                alert("编辑成功");
                this.singleVisible=false;
            },

            removeChoiceSingle(item) {
                var index = this.singleEditForm.choices.indexOf(item)
                if (index !== -1) {
                    this.singleEditForm.choices.splice(index, 1)
                }
            },

            removeChoiceMulti(item) {
                var index = this.multiEditForm.choices.indexOf(item)
                if (index !== -1) {
                    this.multiEditForm.choices.splice(index, 1)
                }
            },

            addChoiceSingle() {
                this.singleEditForm.choices.push({
                    content: '',
                    tag:'',
                });
            },

            addChoiceMulti() {
                this.multiEditForm.choices.push({
                    content: '',
                    tag:'',
                });
            },
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
