<template>
    <el-collapse>
        <el-collapse-item
                v-for="(chapter, indexChapter) in chapters"
                :key="indexChapter"
        >
            <h2 slot="title">{{ chapter.title }}&nbsp;&nbsp;&nbsp;&nbsp;</h2>
            <AddNewChapter slot="title" :last-chapter="chapter.sectionPrimaryKey.secNo"></AddNewChapter>
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
    import AddNewChapter from "./AddNewChapter";
    export default {
        name: "CourseChapters",
        components: {AddNewChapter, CourseSectionUnit},
        data() {
            return {
                chapters: [
                    {
                        title: "第一章",
                        sectionPrimaryKey: {
                            secNo: 0,
                        },
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
                        sectionPrimaryKey: {
                            secNo: 0,
                        },
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
                        sectionPrimaryKey: {
                            secNo: 0,
                        },
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
                showAddChapter: false,
                addAfterChapter: {
                    id: 0,
                    title: "",
                }
            }
        },
    }
</script>

<style scoped>
    .section-content {
        padding-left: 20px;
        padding-right: 20px;
    }
</style>
