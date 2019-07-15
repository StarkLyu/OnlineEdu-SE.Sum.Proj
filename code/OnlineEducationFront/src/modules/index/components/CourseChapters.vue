<template>
    <el-collapse>
        <el-collapse-item
                v-for="(chapter, indexChapter) in chapters"
                :key="indexChapter"
        >
            <h2 slot="title">{{ chapter.title }}</h2>
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
                ]
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