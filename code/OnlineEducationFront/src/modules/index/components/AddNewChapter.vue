<template>
    <div>
        <el-button
                circle
                icon="el-icon-plus"
                size="small"
                v-if="managerMode"
                @click="showAddChapter = true"
        ></el-button>
        <el-dialog :visible.sync="showAddChapter">
            <h2 slot="title">添加章</h2>
            <el-input v-model="newTitle" placeholder="新章标题"></el-input>
            <div slot="footer">
                <el-button @click="addChapter">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AddNewChapter",
        props: {
            lastChapter: Number
        },
        data() {
            return {
                showAddChapter: false,
                newTitle: "",
            }
        },
        methods: {
            addChapter: function () {
                alert("插入成功！");
                this.newTitle = "";
                this.showAddChapter = false;
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
