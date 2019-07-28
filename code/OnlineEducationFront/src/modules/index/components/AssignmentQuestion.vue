<template>
    <el-card>
        <AssignmentJudge v-if="question.questionType === 'T_OR_F'" :judge="question" ></AssignmentJudge>
        <AssignmentSingle v-else-if="question.questionType === 'SINGLE_ANSWER'" :single="question"></AssignmentSingle>
        <AssignmentMulti v-else-if="question.questionType === 'MULTIPLE_ANSWER'" :multi="question"></AssignmentMulti>
        <AssignmentSub v-else-if="question.questionType === 'SUBJECTIVE'" :sub="question"></AssignmentSub>
    </el-card>
</template>

<script>
    import AssignmentJudge from "./AssignmentJudge";
    import AssignmentSingle from "./AssignmentSingle";
    import AssignmentMulti from "./AssignmentMulti";
    import AssignmentSub from "./AssignmentSub";
    export default {
        name: "AssignmentQuestion",
        components: {AssignmentSub, AssignmentMulti, AssignmentSingle, AssignmentJudge},
        props: {
            question: Object,
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>
