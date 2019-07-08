<template>
    <div class="content-body-layout">
        <el-tabs type="border-card">
            <el-tab-pane>
                <span slot="label">课程公告</span>
                <CourseAnnouncement :announcements="announcements"></CourseAnnouncement>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label">基本信息</span>
                <CourseInfo :course-info="courseInfo" class="info-layout"></CourseInfo>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label">课程大纲</span>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import CourseAnnouncement from "./CourseAnnouncement";
    import CourseInfo from "./CourseInfo";
    export default {
        name: "UserCourseInfo",
        components: {CourseInfo, CourseAnnouncement},
        data() {
            return {
                announcements: [
                    {
                        title: "测试公告",
                        time: Date(),
                        content: "哈哈哈哈哈吼吼吼吼吼呵呵呵呵呵\n耶呵呵呵呵呵呵呵呵"
                    }
                ],
                courseInfo: {
                    courseId: "SE996",
                    courseName: "数据库没成绩导论",
                    courseTeacher: "JBoss",
                    courseAssistance: [
                        "ASSI-1",
                        "ASSI-2",
                        "ASSI-3"
                    ],
                    courseTime1: "2019-07-02",
                    courseTime2: "2019-08-02",
                    courseRoom: "软件学院大楼1111",
                    courseDes: "我我也爱喝的健康放假啊扩大解放咔叽打开"
                }
            }
        }
    }
</script>

<style scoped>
    .info-layout {
        width: 950px;
        margin-left: auto;
        margin-right: auto;
    }
</style>