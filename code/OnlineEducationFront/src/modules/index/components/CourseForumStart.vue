<template>
    <el-card>
        <div slot="header">
            <strong>{{ forumTopic.title }}</strong>
            <div class="float-right">
                <UserUnit class="float-left" size="middle"></UserUnit>&nbsp;&nbsp;&nbsp;&nbsp;
                {{ forumTopic.createdAt }}
            </div>
        </div>
        <div>
            <pre>{{ forumTopic.content }}</pre>
        </div>
        <div class="float-right">
            <AddForumTopic></AddForumTopic>
        </div>
        <div class="float-right">
            <el-switch v-model="showResponse" active-text="显示回复" active-color="#13ce66"></el-switch>
        </div>
        <div class="float-clear"></div>
        <div v-if="showResponse">
            <CourseForumResponse
                    v-for="response in forumTopic.responses"
                    :key="response.createdAt"
                    :response="response"
            ></CourseForumResponse>
        </div>
    </el-card>
</template>

<script>
    import CourseForumResponse from "./CourseForumResponse";
    import UserUnit from "./UserUnit";
    import AddForumTopic from "./AddForumTopic";
    export default {
        name: "CourseForumStart",
        components: {AddForumTopic, UserUnit, CourseForumResponse},
        props: {
            forumTopic: Object
        },
        data() {
            return {
                showResponse: false,
            }
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>
