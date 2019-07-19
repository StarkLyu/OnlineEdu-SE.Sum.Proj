<template>
    <div class="paper-unit float-left">
        <el-badge :type="badgeType" :value="badgeText">
            <el-link>
                <i class="iconfont el-icon-my-homework"></i>
                {{ paperInfo.title }}
            </el-link>
        </el-badge>
    </div>
</template>

<script>
    export default {
        name: "PaperUnit",
        props: {
            paperInfo: {
                state: "",
                title: ""
            }
        },
        computed: {
            badgeType: function () {
                switch (this.paperInfo.state) {
                    case "NOT START":
                        return "danger";
                    case "NOT FINISH":
                        return "warning";
                    case "FINISHED":
                        return "primary";
                    case "NOT MARKED":
                        return "info";
                    case "MARKED":
                        return "success";
                    default:
                        return "danger";
                }
            },
            badgeText: function () {
                switch (this.paperInfo.state) {
                    case "NOT START":
                        return "未开始";
                    case "NOT FINISH":
                        return "未完成";
                    case "FINISHED":
                        return "已完成";
                    case "NOT MARKED":
                        return "未评分";
                    case "MARKED":
                        return "已评分";
                    default:
                        return "什么鬼";
                }
            },
        }
    }
</script>

<style scoped>
    .paper-unit {
        width: 120px;
        padding-left: 20px;
        padding-right: 20px;
        text-align: center;
    }
</style>
