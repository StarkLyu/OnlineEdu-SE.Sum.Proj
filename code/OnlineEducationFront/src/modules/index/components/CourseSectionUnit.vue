<template>
    <el-collapse-item>
        <h3 slot="title">{{ sectionInfo.title }}&nbsp;&nbsp;&nbsp;&nbsp;</h3>
        <el-button
                slot="title"
                circle
                icon="el-icon-plus"
                size="small"
                v-if="managerMode"
        ></el-button>
        <el-divider>章节介绍</el-divider>
        <pre>{{ sectionInfo.description }}</pre>
        <el-divider>章节资源</el-divider>
        <div>
            <ResourceUnit
                    v-for="(resource, index) in sectionInfo.resources"
                    :key="index"
                    :resource-info="resource"
            ></ResourceUnit>
            <div class="float-clear"></div>
        </div>
        <el-divider>章节作业</el-divider>
        <div>
            <PaperUnit
                    v-for="(paper, index) in sectionInfo.papers"
                    :key="index"
                    :paper-info="paper"
            ></PaperUnit>
            <div class="float-clear"></div>
        </div>
        <el-divider></el-divider>
    </el-collapse-item>
</template>

<script>
    import ResourceUnit from "./ResourceUnit";
    import PaperUnit from "./PaperUnit";
    export default {
        name: "CourseSectionUnit",
        components: {PaperUnit, ResourceUnit},
        props: {
            sectionInfo: {
                title: "",
                description: "",
                resources: [],
                papers: []
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

</style>
