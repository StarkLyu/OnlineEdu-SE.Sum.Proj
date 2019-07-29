<template>
    <el-collapse>
        <el-collapse-item v-for="section in sectionForum" :key="section.secNo" :title="section.title">
            <div slot="title">
                <div class="float-left">
                    <h2>{{ section.title }}</h2>
                </div>
                <div class="float-left add-topic">
                    <AddForumTopic :sec-no="section.secNo"></AddForumTopic>
                </div>
            </div>
            <CourseForumStart
                    v-for="topic in section.topics"
                    :key="topic.createdAt"
                    :forum-topic="topic"
            ></CourseForumStart>
        </el-collapse-item>
    </el-collapse>
</template>

<script>
    import CourseForumStart from "./CourseForumStart";
    import AddForumTopic from "./AddForumTopic";
    export default {
        name: "UserCourseForum",
        components: {AddForumTopic, CourseForumStart},
        data() {
            return {
                sectionForum: [
                    {
                        secNo: 1,
                        title: "第一章",
                        topics: [
                            {
                                content: "string",
                                courseId: 0,
                                createdAt: "2019-07-27T14:51:13.216Z",
                                id: "string",
                                imageUrls: [
                                    "string"
                                ],
                                likes: 0,
                                path: "string",
                                secNo: 1,
                                title: "string",
                                userId: 0,
                                responses: [
                                    {
                                        content: "string",
                                        courseId: 0,
                                        createdAt: "2019-07-27T14:51:13.216Z",
                                        id: "string",
                                        imageUrls: [
                                            "string"
                                        ],
                                        likes: 0,
                                        path: "string",
                                        secNo: 1,
                                        title: "string",
                                        userId: 0,
                                        responses: []
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        },
        created() {

        }
    }
</script>

<style scoped>
    .add-topic {
        padding-top: 15px;
        padding-left: 30px
    }
</style>
