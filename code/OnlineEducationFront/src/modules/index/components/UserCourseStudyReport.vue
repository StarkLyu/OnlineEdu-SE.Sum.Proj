<template>
    <div class="report-div" ref="studyReport">
        <h2>
            我的学习报告
        </h2>
        <p>
            我的成绩：<strong class="score-text">{{scoreText}}</strong>
        </p>
        <div class="study-chart" id="studychart"></div>
        <el-row>
            <el-col :span="8">
                专注度：{{ concentrationText }} / 100
            </el-col>
            <el-col :span="8">
                努力度：{{ hardworkingText }} / 100
            </el-col>
            <el-col :span="8">
                总学习时长：{{ totalStudyTimeText }}
            </el-col>
        </el-row>
        <el-divider></el-divider>
        <h3>
            我的论坛词云图：
        </h3>
        <img :src="wordMap">
    </div>
</template>

<script>
    import echarts from "echarts";

    export default {
        name: "UserCourseStudyReport",
        //components: {ECharts},
        data() {
            return {
                score: -1,
                concentration: -1,
                hardworking: -1,
                totalStudyTime: -1,
                dailyStudyTime: [],
                wordMap: "",
                reportLoaded: false,
                wordMapLoaded: false,
                scoreLoaded: false
            }
        },
        methods: {
            initChart: function () {
                let dateList = [];
                dateList.push(this.$store.getters.getCourseInfo);
                let chart = echarts.init(document.getElementById("studychart"));
                chart.setOption({
                    title: {
                        text: "我的学习情况"
                    },
                    xAxis: {
                        type: "category"
                    }
                })
            },
            initReport: function () {
                let loading = this.$loading({
                    target: this.$refs["studyReport"],
                    text: "生成报告中",
                    fullscreen: false
                });
                let courseId = this.$store.getters.getCourseId;
                let authHeader = this.$store.getters.authRequestHead;
                this.$http.request({
                    url: `/api/courses/${courseId}/studyRecord/report`,
                    method: "get",
                    headers: authHeader
                }).then((response) => {
                    this.concentration = response.data.report.concentration;
                    this.hardworking = response.data.report.hardworking;
                    this.totalStudyTime = response.data.report.studyTime;
                    this.dailyStudyTime = response.data.studyTimes;
                    loading.close();
                }).catch((error) => {
                    loading.close();
                    this.$root.error(error);
                    console.log(error.response);
                });
                this.$http.request({
                    url: `/api/users/${this.$store.state.user.userInfo.id}/courses/${courseId}/forums/`,
                    method: "post",
                    headers: authHeader
                }).then((response) => {
                    this.wordMap = `http://202.120.40.8:30382/online-edu/static/${response.data}?a=${Date()}`;
                    console.log(response);
                }).catch((error) => {
                    //loading.close();
                    console.log(error.response);
                });
                this.$http.request({
                    url: `/api/courses/${courseId}/score`,
                    method: "get",
                    headers: authHeader
                }).then((response) => {
                    //loading.close();
                    this.score = response.data;
                }).catch((error) => {
                    //loading.close();
                    this.$root.error(error);
                    console.log(error);
                })
            }
        },
        computed: {
            scoreText: function () {
                if (this.score === -1) return "成绩还没出呢，再等等吧";
                else return this.score;
            },
            concentrationText: function () {
                if (this.concentration === -1) {
                    return "？？？";
                }
                else {
                    return this.concentration;
                }
            },
            hardworkingText: function () {
                if (this.hardworking === -1) {
                    return "？？？";
                }
                else {
                    return this.hardworking;
                }
            },
            totalStudyTimeText: function () {
                if (this.totalStudyTime === -1) {
                    return "？？？";
                }
                else {
                    return `${Math.trunc(this.totalStudyTime / 60)} h ${this.totalStudyTime % 60} min`;
                }
            }
        },
        created() {
            this.initReport();
            this.initChart();
        }
    }
</script>

<style scoped>
    .report-div {
        width: 950px;
        margin-left: auto;
        margin-right: auto;
    }

    .score-text {
        font-size: 20px;
        color: red;
    }

    .study-chart {
        width: 900px;
        margin-left: auto;
        margin-right: auto;
        height: 500px
    }
</style>
