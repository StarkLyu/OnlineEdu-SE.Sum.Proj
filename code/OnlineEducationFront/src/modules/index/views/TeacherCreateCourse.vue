<template>
    <div>
        <el-steps :active="active" finish-status="success">
            <el-step title="课程原型"></el-step>
            <el-step title="创建课程"></el-step>
        </el-steps>
        <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
        <div v-if="active===0">
            这是第一步
        </div>
        <div v-if="active===1">
            这是第二步
        </div>
    </div>
</template>

<script>
    export default {
        name: "TeacherCreateCourse",

        data() {
            return {
                active: 0
            };
        },

        methods:{
            next() {
                if (this.active++ > 1) this.active = 0;
            },
        }
    }
</script>

<style scoped>

</style>
