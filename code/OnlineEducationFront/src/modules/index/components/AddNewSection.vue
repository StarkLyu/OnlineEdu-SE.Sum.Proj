<template>
    <div>
        <el-button
                circle
                icon="el-icon-plus"
                size="small"
                v-if="managerMode"
                @click="showAddSection = true"
        ></el-button>
        <el-dialog :visible.sync="showAddSection">
            <h2 slot="title">添加章</h2>
            <el-input v-model="newTitle" placeholder="新节标题"></el-input>
            <el-input v-model="newDescription" placeholder="新节描述"></el-input>
            <div slot="footer">
                <el-button @click="addSection">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AddNewSection",
        props: {
            lastChapter: Number
        },
        data() {
            return {
                showAddSection: false,
                newTitle: "",
                newDescription: ""
            }
        },
        methods: {
            addSection: function () {
                alert("插入成功！");
                this.newTitle = "";
                this.showAddSection = false;
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
