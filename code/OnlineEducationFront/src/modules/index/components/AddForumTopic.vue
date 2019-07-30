<template>
    <div>
        <el-button type="primary" size="small" @click="showAdd">发布帖子</el-button>
        <el-dialog :visible.sync="addResponse">
            <div slot="title">
                <h2>发布帖子</h2>
            </div>
            <div>
                <el-form :model="addTopic" label-position="right" label-width="80px">
                    <el-form-item prop="title" label="添加标题">
                        <el-input v-model="addTopic.title"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="添加内容">
                      <el-input type="textarea" :autosize="{minRows: 5}" v-model="addTopic.content"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <div slot="footer">
                <el-button>取消</el-button>
                <el-button type="primary" @click="commitAdd">添加</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AddForumTopic",
        props: {
            secNo: {
                default: 1
            }
        },
        data() {
            return {
                addResponse: false,
                addTopic: {
                    title: "",
                    content: ""
                }
            }
        },
        methods: {
            showAdd: function () {
                this.addResponse = true
            },
            commitAdd: function () {
                this.$http.request({
                    url: this.$store.getters.getCourseUrl + "sections/" + this.secNo + "/forums",
                    method: "post",
                    headers: this.$store.getters.authRequestHead,
                    data: this.addTopic
                }).then((response) => {
                    alert("发布成功！");
                    console.log(response.data);
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                })
            }
        }
    }
</script>

<style scoped>

</style>
