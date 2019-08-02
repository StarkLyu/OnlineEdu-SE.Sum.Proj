<template>
    <div>
        <el-button type="primary" size="small" @click="showAdd">发布回复</el-button>
        <el-dialog :visible.sync="addResponse">
            <div slot="title">
                <h2>发布回复</h2>
            </div>
            <div>
                <el-form :model="addTopic" label-position="right" label-width="80px">
                    <el-form-item prop="content" label="添加内容">
                        <el-input type="textarea" :autosize="{minRows: 5}" v-model="addTopic.content"></el-input>
                    </el-form-item>
                    <!--                    增加图片-->
                    <el-form-item prop="file" ref="uploadElement">
                        <el-upload ref="upload"
                                   action="#"
                                   :multiple="true"
                                   list-type="picture-card"
                                   :auto-upload="false"
                                   :http-request="UploadImg"
                                   accept="image/png,image/gif,image/jpg,image/jpeg">
                            <i class="el-icon-plus"></i>
                        </el-upload>
                    </el-form-item>
                </el-form>
            </div>
            <div slot="footer">
                <el-button @click.native="addResponse=false">取消</el-button>
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
            },
            path: {
                default: ""
            }
        },
        data() {
            return {
                addResponse: false,
                addTopic: {
                    content: ""
                },

                formData: new FormData(),
            }
        },
        methods: {
            showAdd: function () {
                this.addResponse = true
            },

            commitAdd: function () {
                this.formData = new FormData();
                this.$refs.upload.submit();

                this.$http.request({
                    url: this.$store.getters.getCourseUrl + "sections/" + this.secNo + "/forums",
                    method: "post",
                    headers: this.$store.getters.authRequestHead,
                    data: {
                        content: this.addTopic.content,
                        path: this.path
                    }
                }).then((response) => {
                    console.log(response.data);
                    var tempId=response.data.id;
                    var that=this;
                    // 再上传图片
                    that.$http.request({
                        url: '/api/forums/' + tempId + "/images",
                        method: "post",
                        headers:{
                            'Authorization': "Bearer " + that.$store.state.user.accessToken,
                            'Content-Type': 'multipart/form-data'
                        },
                        data:that.formData,
                    })
                        .then(function (res) {
                            console.log(res.data);
                            alert("发布成功");
                            that.addResponse=false;
                        })
                        .catch(function (error2) {
                            console.log(error2.response);
                            alert("请求失败");
                        });
                }).catch((error) => {
                    alert(error);
                    console.log(error.response);
                })
            },

            // 上传图片
            UploadImg (file) {
                this.formData.append('images', file.file);
            },

            // 移除照片
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
        }
    }
</script>

<style scoped>

</style>
