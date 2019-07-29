<template>
    <el-card>
        <div slot="header">
            <div class="float-left">
                <UserUnit size="middle"></UserUnit>
            </div>
            <div class="float-right">{{ response.createdAt }}</div>
            <div class="float-clear"></div>
        </div>
        <pre>{{ response.content }}</pre>
        <div class="float-clear"></div>
        <CourseForumResponse
                v-for="res in response.responses"
                :key="res.createdAt"
                :response="res"
        ></CourseForumResponse>
    </el-card>
</template>

<script>
    import UserUnit from "./UserUnit";
    export default {
        name: "CourseForumResponse",
        components: {UserUnit},
        props: {
            response: Object
        }
    }
</script>

<style scoped>

</style>
