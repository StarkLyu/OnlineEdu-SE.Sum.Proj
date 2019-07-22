<template>
<!--    判断题组件-->
    <div id="AssignmentJudge">
        <h4>
            {{judge.title}}
        </h4>
        <el-radio-group style="width: 80%" v-model="judge.answer">
            <el-radio label="正确"></el-radio>
            <el-radio label="错误"></el-radio>
        </el-radio-group>
        <p style="color:red">
            正确答案为：( {{judge.correctAnswer}} )
        </p>
<!--        <div v-for="judge in JudgeQuestion" :key="judge.key">-->
<!--            <div v-if="judge.type==='judge'">-->
<!--                <h4>-->
<!--                    {{judge.title}}-->
<!--                </h4>-->
<!--                <el-radio-group style="width: 80%" v-model="judge.answer">-->
<!--                    <el-radio label="正确"></el-radio>-->
<!--                    <el-radio label="错误"></el-radio>-->
<!--                </el-radio-group>-->
<!--                <p>-->
<!--                    正确答案为：( {{judge.correctAnswer}} )-->
<!--                </p>-->
<!--            </div>-->
<!--        </div>-->
    </div>
</template>

<script>
    export default {
        name: "AssignmentJudge",

        props: {
            judge: Array,
        },
    }
</script>

<style scoped>

</style>
