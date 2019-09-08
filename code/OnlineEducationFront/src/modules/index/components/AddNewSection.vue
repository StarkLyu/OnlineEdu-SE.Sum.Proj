<template>
    <div>
        <el-tooltip effect="dark" content="添加小节" placement="top-start">
            <el-button
                    circle
                    icon="el-icon-plus"
                    size="small"
                    v-if="managerMode"
                    @click="showAddSection = true"
            ></el-button>
        </el-tooltip>
        <el-dialog :visible.sync="showAddSection">
            <h2 slot="title">添加节</h2>
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
            chapterId: Number,
            lastSection: Number,
        },
        data() {
            return {
                showAddSection: false,
                newTitle: "",
                newDescription: "",
                content:"添加第"+this.lastSection+"小节"
            }
        },
        methods: {
            addSection: function () {
                var that=this;
                that.$http.request({
                    url: '/api/courses/'+this.$store.getters.getCourseId+'/sections/'+this.chapterId+'/append',
                    method: "post",
                    headers:this.$store.getters.authRequestHead,
                    params:{
                        branchNo:this.lastSection,
                    },
                    data:{
                        title:this.newTitle,
                        description:this.newDescription,
                    },
                })
                    .then(function (response) {
                        console.log(response.data);
                        that.$message.success("添加节成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        that.$message.error("添加节失败");
                        that.$message.error(error.response.data);
                    });
                this.newTitle = "";
                this.newDescription="";
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
