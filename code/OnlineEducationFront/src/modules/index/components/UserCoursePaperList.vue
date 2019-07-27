<template>
    <el-table :data="papers">
        <el-table-column prop="title" label="标题"></el-table-column>
        <el-table-column prop="start" label="开始时间"></el-table-column>
        <el-table-column prop="end" label="结束时间"></el-table-column>
        <el-table-column prop="status" label="状态">
            <template slot-scope="scope">
                <el-tag :type="statusTagType(scope.row.status)">
                    {{ statusTagText(scope.row.status) }}
                </el-tag>
            </template>
        </el-table-column>
        <el-table-column label="链接">
            <el-link>
                进入作业
            </el-link>
        </el-table-column>
    </el-table>
</template>

<script>
    export default {
        name: "UserCoursePaperList",
        data() {
            return {
                papers: [
                    {
                        id: 0,
                        title: "第一次作业",
                        start: "2019-07-15",
                        end: "2019-07-25",
                        status: "NOT VIEWED"
                    }
                ]
            }
        },
        methods: {
            statusTagType: function (status) {
                if (status === "NOT VIEWED") return "danger";
                else if (status === "DOING") return "warning";
                else if (status === "SCORING") return "primary";
                else if (status === "SUCCESS") return "success";
            },
            statusTagText: function (status) {
                if (status === "NOT VIEWED") return "未查看";
                else if (status === "DOING") return "未完成";
                else if (status === "SCORING") return "已完成";
                else if (status === "SUCCESS") return "已批改";
            }
        }
    }
</script>

<style scoped>

</style>
