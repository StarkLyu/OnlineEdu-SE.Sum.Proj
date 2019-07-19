<template>
    <el-collapse>
        <el-collapse-item
                v-for="(chapter, indexChapter) in chapters"
                :key="indexChapter"
        >
            <h2 slot="title">{{ chapter.title }}&nbsp;&nbsp;&nbsp;&nbsp;</h2>
            <el-button
                    slot="title"
                    circle
                    icon="el-icon-plus"
                    size="small"
                    v-if="managerMode"
                    @click="showAddChapter = true"
            ></el-button>
            <el-dialog :visible.sync="showAddChapter">

            </el-dialog>
            <div class="section-content">
                <el-collapse>
                    <CourseSectionUnit
                            v-for="(section, indexSection) in chapter.sections"
                            :key="indexSection"
                            :section-info="section"
                    ></CourseSectionUnit>
                </el-collapse>
            </div>
        </el-collapse-item>
    </el-collapse>
</template>

<script>
    import CourseSectionUnit from "./CourseSectionUnit";
    export default {
        name: "CourseChapters",
        components: {CourseSectionUnit},
        data() {
            return {
                chapters: [
                    {
                        title: "第一章",
                        sections: [
                            {
                                title: "第一节：哈哈哈",
                                description: "我我我是谁啊\n嘻嘻嘻哈哈哈",
                                resources: [
                                    {
                                        resourceType: "PPT",
                                        url: "",
                                        title: "第一节PPT"
                                    },
                                    {
                                        resourceType: "PPT",
                                        url: "",
                                        title: "第一节PPT"
                                    },
                                    {
                                        resourceType: "PPT",
                                        url: "",
                                        title: "第一节PPT"
                                    }
                                ],
                                papers: [
                                    {
                                        state: "NOT FINISH",
                                        title: "哈哈哈",
                                    },
                                    {
                                        state: "NOT START",
                                        title: "哈哈哈",
                                    },
                                    {
                                        state: "FINISHED",
                                        title: "哈哈哈",
                                    },
                                    {
                                        state: "NOT MARKED",
                                        title: "哈哈哈",
                                    },
                                    {
                                        state: "MARKED",
                                        title: "哈哈哈",
                                    }
                                ]
                            },
                            {
                                title: "第一节：哈哈哈",
                                description: "我我我是谁啊\n嘻嘻嘻哈哈哈",
                                resources: [
                                    {
                                        resourceType: "PPT",
                                        url: "",
                                        title: "第一节PPT"
                                    }
                                ],
                                papers: []
                            },
                            {
                                title: "第一节：哈哈哈",
                                description: "我我我是谁啊\n嘻嘻嘻哈哈哈",
                                resources: [
                                    {
                                        resourceType: "PPT",
                                        url: "",
                                        title: "第一节PPT"
                                    }
                                ],
                                papers: []
                            }
                        ]
                    },
                    {
                        title: "第一章",
                        sections: [
                            {
                                title: "第一节：哈哈哈",
                                description: "我我我是谁啊\n嘻嘻嘻哈哈哈",
                                resources: [
                                    {
                                        resourceType: "PPT",
                                        url: "",
                                        title: "第一节PPT"
                                    }
                                ],
                                papers: []
                            }
                        ]
                    },
                    {
                        title: "第一章",
                        sections: [
                            {
                                title: "第一节：哈哈哈",
                                description: "我我我是谁啊\n嘻嘻嘻哈哈哈",
                                resources: [
                                    {
                                        resourceType: "PPT",
                                        url: "",
                                        title: "第一节PPT"
                                    }
                                ],
                                papers: []
                            }
                        ]
                    }
                ],
                showAddChapter: false
            }
        },
        computed: {
            managerMode: function () {
                return true;
            }
        }
    }
</script>

<style scoped>
    .section-content {
        padding-left: 20px;
        padding-right: 20px;
    }
</style>
