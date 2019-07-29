<template>
<!--    判断题组件-->
    <div id="AssignmentJudge">
        <h4>
            {{judge.content}}
        </h4>
        <el-radio-group style="width: 80%" v-model="judge.myAnswer">
            <el-radio label="T">正确</el-radio>
            <el-radio label="F">错误</el-radio>
        </el-radio-group>
        <p style="color:red" v-if="showAnswer">
            正确答案为：( {{judge.correctAnswer}} )
        </p>
    </div>
</template>

<script>
    export default {
        name: "AssignmentJudge",

        props: {
            judge: Object,
            showAnswer: false,
        },
    }
</script>

<style scoped>

</style>
