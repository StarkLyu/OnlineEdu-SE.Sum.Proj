<template>
    <div class="report-div">
        <h2>
            我的学习报告
        </h2>
        <p>
            我的成绩：<strong class="score-text">{{scoreText}}</strong>
        </p>
        <div class="study-chart" id="studychart"></div>
        <el-row>
            <el-col span="8">
                专注度：{{ concentrationText }}
            </el-col>
            <el-col span="8">
                努力度：{{ hardworkingText }}
            </el-col>
            <el-col span="8">
                总学习时长：{{ totalStudyTimeText }}
            </el-col>
        </el-row>
        <el-divider></el-divider>
        <div>
            我的论坛词云图：
            <img :src="wordMap">
        </div>
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
                //studyChart: null
            }
        },
        methods: {
            initChart: function () {
                let chart = echarts.init(document.getElementById("studychart"));
                chart.setOption({
                    title: {
                        text: "我的学习情况"
                    },
                    xAxis: {
                        type: "category"
                    }
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
                    return `${Math.trunc(this.totalStudyTime / 60)} h ${this.totalStudyTime % 60} s`;
                }
            }
        },
        created() {
            this.initChart();
        }
    }
</script>

<style scoped>
    .report-div {
        border-style: solid;
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
