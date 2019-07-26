<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程题库</h1>
        </el-header>
        <el-main>
<!--            作业显示部分-->
            <div>
                <el-button @click="showSingleDialog">添加单选题</el-button>
                <el-button @click="showMultiDialog">添加多选题</el-button>
                <el-button @click="showJudgeDialog">添加判断题</el-button>
                <el-button @click="showSubDialog">添加主观题</el-button>
<!--                所有题目展示-->
                <div v-for="question in questions" :key="question.id">
<!--                    <div style="float: right">-->
<!--                        <el-button size="small" @click="showQuestionEdit(question)">编辑</el-button>-->
<!--                        <el-button size="small" type="danger" @click="deleteQuestion">删除</el-button>-->
<!--                    </div>-->
                    <div v-if="question.questionType==='SINGLE_ANSWER'|| question.questionType==='MULTIPLE_ANSWER'">
                        <h4>
                            Question: {{question.content}}
                        </h4>
                        <div v-for="choice in question.options" :key="choice">
                            {{choice}}
                        </div>
                    </div>
                    <div v-else>
                        <h4>
                            Question: {{question.question}}
                        </h4>
                    </div>
                    <div style="color:red" v-if="question.questionType!=='SUBJECTIVE'">
                        <p>Answer: {{question.answer}}</p>
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
                    <el-upload
                            ref="upload"
                            action='#'
                            list-type="picture-card"
                            accept="image/jpeg,image/jpg,image/png"
                            multiple
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove"
                            :http-request="uploadImg"
                            :auto-upload="false">
                        <i class="el-icon-plus"></i>
                    </el-upload>
<!--                    <el-button size="small" type="success" @click="submitUpload">点击上传</el-button>-->
<!--                    <el-dialog :visible.sync="dialogVisible">-->
<!--                        <img width="100%" :src="dialogImageUrl" alt="">-->
<!--                    </el-dialog>-->
                </el-form>
                <span slot="footer" class="el-dialog__footer">
                    <el-button @click.native="singleVisible=false">取消</el-button>
                    <el-button type="primary" @click="createSingleData()">添加</el-button>
                </span>
                <span slot="footer" class="el-dialog__footer">
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
                    <el-button type="primary" @click="createMultiData">添加</el-button>
                </span>
            </el-dialog>
<!--            添加判断题弹窗部分-->
            <el-dialog :title="'判断题'"
                       :visible.sync="judgeVisible"
                       top="5%">
                <el-form :model="judgeEditForm" label-width="80px" ref="judgeEditForm">
                    <el-form-item label="题目">
                        <el-input type="textarea" v-model="judgeEditForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="答案">
                        <el-input v-model="judgeEditForm.correctAnswer" placeholder="请输入正确或错误"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="el-dialog__footer">
                    <el-button @click.native="judgeVisible=false">取消</el-button>
                    <el-button type="primary" @click="createJudgeData">添加</el-button>
                </span>
            </el-dialog>
<!--            添加主观题弹窗部分-->
            <el-dialog :title="'主观题'"
                       :visible.sync="subVisible"
                       top="5%">
                <el-form :model="subEditForm" label-width="80px" ref="subEditForm">
                    <el-form-item label="题目">
                        <el-input type="textarea" v-model="subEditForm.title"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="el-dialog__footer">
                    <el-button @click.native="subVisible=false">取消</el-button>
                    <el-button type="primary" @click="createSubData">添加</el-button>
                </span>
            </el-dialog>
        </el-main>
    </div>
</template>

<script>
    // import AssignmentSingle from "../components/AssignmentSingle"
    // import AssignmentMulti from "../components/AssignmentMulti"
    // import AssignmentJudge from "../components/AssignmentJudge"
    // import AssignmentSub from "../components/AssignmentSub"

    export default {
        name: "TeacherCourseOneAssignment",

        components:{
            // AssignmentSingle,
            // AssignmentMulti,
            // AssignmentJudge,
            // AssignmentSub,
        },

        data(){
            return{
                singleVisible: false,

                multiVisible: false,

                judgeVisible: false,

                subVisible: false,

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
                    images:[],
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

                subEditForm:{
                    key:"",
                    title:"",
                },

                oneAssignment:{
                    assignTitle:"作业标题",
                    quesitons:[
                        {
                            type:"SINGLE_ANSWER",
                            key:1,
                            title:"这是一道单选题，请选择下列选项。",
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
                            type:"MULTIPLE_ANSWER",
                            key:2,
                            title:"这是一道多选题，请选择下列选项。",
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
                            type:"T_OR_F",
                            key:3,
                            title:"这是一道判断题，请选择下列选项。",
                            answer:"",
                            correctAnswer:"正确",
                        },
                        {
                            type:"SINGLE_ANSWER",
                            key:4,
                            title:"这是一道单选题，请选择下列选项。",
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
                            correctAnswer:"B",
                        },
                        {
                            type:"SUBJECTIVE",
                            key:5,
                            title:"这是一道主观题，请在文本框里填写或添加图片或添加附件。",
                            answer:"",
                        }
                    ],

                },

                questions:this.$store.getters.getCourseInfo.coursePrototype.questions,

            }
        },

        methods:{
            showAllQuestions(){
                this.$http.request({
                    url: this.$store.getters.getCourseUrl + "info",
                    method: "get",
                    headers: this.$store.getters.authRequestHead
                }).then((response) => {
                    console.log(response.data);
                    let identity = response.data.identity;
                    this.$store.commit("setCourseInfo", response.data.course);
                    this.$store.commit("setIdentity", identity);
                    console.log(this.$store.getters.getCourseInfo);
                    // if (identity === "VISITOR") {
                    //     this.$router.push('/course/info');
                    // }
                    // else if (identity === "STUDENT") {
                    //     this.$router.push('/course/student');
                    // }
                    // else {
                    //     this.$router.push('/course/manager');
                    // }
                    this.$router.push('/course/manager/questionBank');
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                });
            },


            // 显示各种题型的新建dialog
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

            showSubDialog(){
                this.subVisible=true;
                this.subEditForm={
                    key:"",
                    title:"",
                }
            },

            showQuestionEdit(question){
                if (question.questionType==='SINGLE_ANSWER'){
                    this.showSingleEditDialog(question);
                }
            },

            // 显示各种题型的编辑dialog
            showSingleEditDialog(single){
                this.singleVisible=true;
                this.singleEditForm = Object.assign({}, single);
            },

            showMultiEditDialog(multi){
                this.multiVisible=true;
                this.multiEditForm = Object.assign({}, multi);
            },

            showJudgeEditDialog(judge){
                this.judgeVisible=true;
                this.judgeEditForm = Object.assign({}, judge);
            },

            showSubEditDialog(sub){
                this.subVisible=true;
                this.subEditForm = Object.assign({}, sub);
            },

            // 新建各种题型
            createSingleData(){
                // this.$refs.upload.submit();

                var that=this;

                // console.log("正在上传文件");
                //
                // let questionParam = new FormData();
                // questionParam=this.uploadImg();

                var singleChoices=[];
                for (let x=0; x<that.singleEditForm.choices.length; x++){
                    singleChoices.push(that.singleEditForm.choices[x].content);
                }

                // questionParam.append('type',"single_answer");
                // questionParam.append('content',this.singleEditForm.title);
                // questionParam.append('options',singleChoices);
                // questionParam.append('answer',this.singleEditForm.correctAnswer);

                this.$http.request({
                    url: '/api/coursePrototypes/'+this.$store.getters.getCourseInfo.coursePrototype.id+'/questions/submit',
                    method: "post",
                    headers:{
                        'Authorization': "Bearer " + this.$store.state.user.accessToken,
                        'Content-Type': 'multipart/form-data'
                    },
                    data:{
                        // questionParam
                        type:"single_answer",
                        content:this.singleEditForm.title,
                        options:singleChoices,
                        answer:this.singleEditForm.correctAnswer,
                    },
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        // alert("请求失败");
                    });
                this.singleVisible=false;
            },

            createMultiData(){
                var that=this;

                var multiChoices=[];
                for (let x=0; x<that.multiEditForm.choices.length; x++){
                    multiChoices.push(that.multiEditForm.choices[x].content);
                }

                this.$http.request({
                    url: '/api/coursePrototypes/'+this.$store.getters.getCourseInfo.coursePrototype.id+'/questions/submit',
                    method: "post",
                    headers:this.$store.getters.authRequestHead,
                    data:{
                        type:"multiple_answer",
                        content:this.multiEditForm.title,
                        options:multiChoices,
                        answer:this.multiEditForm.correctAnswer,
                    },
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        // alert("请求失败");
                    });
                this.multiVisible=false;
            },

            createJudgeData(){
                this.$http.request({
                    url: '/api/coursePrototypes/'+this.$store.getters.getCourseInfo.coursePrototype.id+'/questions/submit',
                    method: "post",
                    headers:this.$store.getters.authRequestHead,
                    data:{
                        type:"t_or_f",
                        content:this.judgeEditForm.title,
                        answer:this.judgeEditForm.correctAnswer,
                    },
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        // alert("请求失败");
                    });
                this.judgeVisible=false;
            },

            createSubData(){
                this.$http.request({
                    url: '/api/coursePrototypes/'+this.$store.getters.getCourseInfo.coursePrototype.id+'/questions/submit',
                    method: "post",
                    headers:this.$store.getters.authRequestHead,
                    data:{
                        type:"subjective",
                        content:this.subEditForm.title,
                    },
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        // alert("请求失败");
                    });
                this.subVisible=false;
            },

            // 删除题目
            deleteQuestion(){
                alert("删除成功");
            },

            // 单选和多选移除选项
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

            // 单选和多选添加选项
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

            // 移除照片
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },

            // 预览照片
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

            submitUpload() {
                this.$refs.upload.submit();
            },

            // 上传图片
            uploadImg(file){
                console.log("正在上传文件");

                let param = new FormData();
                param.append('images',file.file);

                return param;
            }

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
