<template>
    <el-card>
        <div slot="header">
            <strong>{{ forumTopic.title }}</strong>
            <div class="float-right">
                <UserUnit class="float-left" size="middle"></UserUnit>&nbsp;&nbsp;&nbsp;&nbsp;
                {{ forumTopic.createdAt }}
            </div>
            <el-button style="float:right;" @click="banForum(forumTopic.id)" v-if="isCourseTeacher">封贴</el-button>
        </div>
        <div>
            <pre>{{ forumTopic.content }}</pre>
        </div>
        <div class="float-right">
            <AddForumResponse :sec-no="forumTopic.secNo" :path="forumTopic.path"></AddForumResponse>
        </div>
        <div class="float-right">
            <el-switch v-model="showResponse" active-text="显示回复" active-color="#13ce66"></el-switch>
        </div>
        <div class="float-clear"></div>
        <div v-if="showResponse">
            <el-divider>回复</el-divider>
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
    import AddForumResponse from "./AddForumResponse"
    import UserUnit from "./UserUnit";
    import { mapGetters } from "vuex";

    export default {
        name: "CourseForumStart",
        components: {AddForumResponse, UserUnit, CourseForumResponse},
        props: {
            forumTopic: Object
        },
        data() {
            return {
                showResponse: false,
            }
        },
        methods: {
            banForum(id){
                alert("确定要封贴吗？");

                this.$http.request({
                    url: '/api/forums/'+id,
                    method: "delete",
                    headers:this.$store.getters.authRequestHead,
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        alert("请求失败");
                    });
            }
        },

        computed: {
            ...mapGetters([
                'isCourseTeacher',
            ]),
        }
    }
</script>

<style scoped>

</style>
