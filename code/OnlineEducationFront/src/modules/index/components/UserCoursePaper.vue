<template>
    <el-card>
        <div slot="header">
            <div class="float-left">
                <strong class="title-font">{{ paperInfo.title }}</strong>
            </div>
            <div class="float-right">
                <DateRangeFormat class="float-right" :start="paperInfo.start" :end="paperInfo.end"></DateRangeFormat>
            </div>
            <div class="float-clear"></div>
            <div class="des-div">
                <pre>{{ paperInfo.description }}</pre>
            </div>
        </div>
        <el-tabs>
            <el-tab-pane>
                <span slot="label">客观题</span>
                <AssignmentQuestion
                        v-for="question in objQuestions"
                        :key="question.id"
                        :question="question"
                        v-modal="answer"
                ></AssignmentQuestion>
                <el-row>
                    <el-col>
                        <el-button @click="uploadAnswer('NOT_FINISH')">提交</el-button>
                    </el-col>
                    <el-col>
                        <el-button @click="getSavedAnswer">暂存</el-button>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label">主观题</span>
                <AssignmentSub
                        v-for="question in subjQuestions"
                        :key="question.id"
                        :sub="question"
                ></AssignmentSub>
            </el-tab-pane>
        </el-tabs>
    </el-card>
</template>

<script>
    import AssignmentQuestion from "./AssignmentQuestion";
    import { mapGetters } from "vuex";
    import AssignmentSub from "./AssignmentSub";
    import DateRangeFormat from "./DateRangeFormat";

    export default {
        name: "UserCoursePaper",
        components: {DateRangeFormat, AssignmentSub, AssignmentQuestion},
        props: {
            paperId: Number,
        },
        data() {
            return {
                paperInfo: {
                    questions: []
                },
                objQuestions: [],
                subjQuestions: [],
                answer: "A",
            }
        },
        methods: {
            uploadAnswer: function (state) {
                let answerList = [];
                for (let question of this.objQuestions) {
                    let answerUnit = {
                        answer: question.myAnswer,
                        questionId: question.id
                    };
                    answerList.push(answerUnit);
                }
                console.log(answerList);
                this.$http.request({
                    url: this.getPaperUrl,
                    method: "post",
                    headers: this.$store.getters.authRequestHead,
                    data: {
                        answerList,
                        state
                    },
                    withCredentials: true
                }).then((response) => {
                    alert("提交成功！");
                    console.log(response.data);
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                })
            },
            getSavedAnswer: function () {
                this.$http.request({
                    url: this.getPaperUrl,
                    method: "get",
                    headers: this.$store.getters.authRequestHead
                }).then((response) => {
                    console.log(response.data);
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                })
            }
        },
        computed: {
            ...mapGetters([
                "getPaperById"
            ]),
            getPaperUrl: function () {
                return this.$store.getters.getCourseUrl + "papers/" + this.paperInfo.id + "/answer";
            }
        },
        mounted() {
            this.paperInfo = this.$store.getters.getPaperById(this.paperId);
            for (let scanQues of this.paperInfo.questions) {
                let fetchQues = Object.assign({}, scanQues.paperWithQuestionsPrimaryKey.question, {
                    score: scanQues.score,
                    myAnswer: ""
                });
                if (fetchQues.questionType !== "SUBJECTIVE") {
                    this.objQuestions.push(fetchQues);
                }
                else {
                    this.subjQuestions.push(fetchQues);
                }
            }
            console.log(this.paperInfo);
        }
    }
</script>

<style scoped>
    .title-font {
        font-size: 25px;
    }

    .des-div {
        margin-top: 20px;
    }
</style>
