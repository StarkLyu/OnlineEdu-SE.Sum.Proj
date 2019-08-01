<template>
    <div>
        <el-tooltip effect="dark" content="添加章" placement="top-start">
            <el-button
                    circle
                    icon="el-icon-plus"
                    size="small"
                    v-if="managerMode"
                    @click="showAddChapter = true"
            ></el-button>
        </el-tooltip>
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
                this.$http.request({
                    url: '/api/courses/'+this.$store.getters.getCourseId+'/sections/append',
                    method: "post",
                    headers:{Authorization: "Bearer " + this.$store.state.user.accessToken,'Content-Type':'text/plain'},
                    params:{
                        secNo:this.lastChapter,
                    },
                    data:this.newTitle,
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        alert("请求失败");
                    });
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
