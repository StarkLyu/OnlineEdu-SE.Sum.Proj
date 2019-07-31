<template>
    <div>
        <el-header>
            <h1 class="titlesytle">主观题批改</h1>
        </el-header>
        <el-main>
<!--            主观题及答案展示-->
            <div v-for="question in allAnswers" :key="question.answerPrimaryKey.question.id">
                <div>
                    <h4>
                        Question: {{question.answerPrimaryKey.question.question}}
                    </h4>
                    <p>
                        Answer: {{question.answer}}
                    </p>
                </div>
<!--                展示图片-->
                <div v-for="image in question.imageUrl" :key="image">
                    <img v-if="image"
                         :src="'http://202.120.40.8:30382/online-edu/static/' + image + '?a=' + Math.random()"
                         class="avatar">
                </div>
<!--                打分和评语框-->
                <div style="width: 60%">
                    <el-input type="number" placeholder="请输入分数" v-model="question.grade"></el-input>
                    <el-input type="textarea" placeholder="请输入评语" v-model="question.comment"></el-input>
                </div>
            </div>
<!--            提交打分-->
            <el-button style="margin-top: 20px" @click="submitComments">提交</el-button>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCorrectSub",

        data(){
            return{
                questions:[],

                // 所有主观题
                allAnswers:[],
            }
        },

        methods:{
            // 获取所有该学生的答案
            showAllAnswers(){
                this.questions=this.$store.getters.getPaperAnswers.answers;

                for (var x=0; x<this.questions.length; x++) {
                    if (this.questions[x].answerPrimaryKey.question.questionType==='SUBJECTIVE'){
                        this.allAnswers.push(this.questions[x]);
                    }
                }
                console.log(this.allAnswers);
            },

            // 提交评分结果
            submitComments(){
                console.log(this.allAnswers);

                var that=this;
                this.$http.request({
                    url: '/api/courses/'+this.$store.getters.getCourseId+'/papers/'+this.$store.getters.getPaperId+'/answer/mark/'+this.$store.state.studentSelectId+this.$store.getters.getPaperAnswers.paperAnswerPrimaryKey.times,
                    method: "post",
                    headers:this.$store.getters.authRequestHead,
                    data:{

                    }
                })
                    .then(function (response) {
                        console.log(response.data);
                        that.oneStuAnswer=response.data;
                        // alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        alert("请求失败");
                    });
            }

        },

        mounted() {
            this.showAllAnswers();
        }
    }
</script>

<style scoped>
    .titlesytle {
        text-align: center;
        padding-top: 20px
    }
</style>
