<template>
    <div id="AssignmentJudge">
        <div v-for="judge in JudgeQuestion" :key="judge.key">
            <h4>
                {{judge.title}}
            </h4>
            <el-radio-group style="width: 80%" v-model="judge.answer">
                <el-radio label="正确"></el-radio>
                <el-radio label="错误"></el-radio>
            </el-radio-group>
            <p>
                正确答案为：( {{judge.correctAnswer}} )
            </p>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AssignmentJudge",

        props: {
            JudgeQuestion: Array,
        },
    }
</script>

<style scoped>

</style>
