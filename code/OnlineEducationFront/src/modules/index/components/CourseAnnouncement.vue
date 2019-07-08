<template>
    <el-collapse>
        <el-collapse-item
                v-for="announce in announcements"
                :key="announce.time">
            <template slot="title">
                <h2>{{ announce.title }}</h2>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <span class="float-right">{{ announce.time }}</span>
            </template>
            <pre class="announce-font">{{ announce.content }}</pre>
        </el-collapse-item>
    </el-collapse>
</template>

<script>
    export default {
        name: "CourseAnnouncement",
        props: {
            announcements: Array,
        },
    }
</script>

<style scoped>
    .announce-font {
        font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    }
</style>