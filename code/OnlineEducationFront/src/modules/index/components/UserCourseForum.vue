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
            <div>
                <CourseForumStart
                        v-for="one in section.topics"
                        :key="one.createdAt"
                        :forum-topic="one"
                ></CourseForumStart>
            </div>
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
        methods: {
            initForum() {
                this.sectionForum = this.$store.getters.getSectionList;
                for (let i in this.sectionForum) {
                    this.sectionForum[i].topics = []
                }
                this.$http.request({
                    url: this.$store.getters.getCourseUrl + "forums",
                    method: "get",
                    headers: this.$store.getters.authRequestHead
                }).then((response) => {
                    let forum = response.data;
                    forum.sort((a,b) => {
                        if (a.secNo === b.secNo) {
                            if (a.path < b.path) return -1;
                            else return 1;
                        }
                        else {
                            if (a.secNo < b.secNo) return -1;
                            else return 1;
                        }
                    });
                    console.log(forum);
                    let scanSecNum = 0;
                    let secLength = this.sectionForum.length;
                    let forumStack = [{}];
                    let pathLevel = 1;
                    for (let i of forum) {
                        i.responses = [];
                        let pathArr = i.path.split("/");
                        let pathLength = pathArr.length;
                        if (pathLength === 2) {
                            pathLevel = 2;
                            forumStack.shift();
                            forumStack.unshift(i);
                            for (; scanSecNum < secLength; ++scanSecNum) {
                                if (this.sectionForum[scanSecNum].secNo === i.secNo) {
                                    this.sectionForum[scanSecNum].topics.push(i);
                                    break;
                                }
                            }
                        }
                        else {
                            if (pathLength <= pathLevel) {
                                let popNum = pathLevel - pathLength + 1;
                                for (let j = 0; j < popNum; ++j) {
                                    forumStack.shift();
                                }
                                pathLevel = popNum;
                            }
                            forumStack[0].responses.push(i);
                            forumStack.unshift(i);
                            pathLevel = pathLength;
                        }
                    }
                    console.log(this.sectionForum);
                    this.$forceUpdate();
                })
            }
        },
        computed: {
            forumUpdate: function () {
                return this.$store.state.course.forumUpdate;
            }
        },
        watch: {
            forumUpdate: function (val) {
                alert("haha");
                if (val) this.initForum();
            }
        },
        mounted() {
            this.initForum();
        }
    }
</script>

<style scoped>
    .add-topic {
        padding-top: 15px;
        padding-left: 30px
    }
</style>
