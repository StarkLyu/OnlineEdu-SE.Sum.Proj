<template>
    <el-upload
            class="avatar-uploader avatar"
            :action="uploadUrl"
            :headers="uploadHeader"
            :show-file-list="false"
            :before-upload="beforeAvatarUpload"
            :on-success="uploadSucceed"
            :on-error="uploadFail"
            :http-request="uploadProcess"
    >
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
</template>

<script>
    export default {
        name: "UserAvatarUpload",
        data() {
            return {

            };
        },
        methods: {
            beforeAvatarUpload: function(file) {
                console.log(file);
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            uploadProcess: function(param) {
                let formData = new FormData();
                formData.append("avatar",param.file);
                this.$http.request({
                    url: this.uploadUrl,
                    method: "post",
                    data: formData,
                    headers: this.uploadHeader
                }).then((response) => {
                    console.log(response);
                    alert("修改成功");
                    this.$http.request({
                        url: "/api/users/info",
                        method: 'get',
                        headers: {
                            "Authorization": "Bearer " + this.$store.state.user.accessToken
                        }
                    }).then((infoResponse) => {
                        this.$store.commit("infoSet", infoResponse.data);
                    }).catch((error) => {
                        console.log(error.response);
                        if (error.response.data.status === 401) {
                            alert("获取用户信息出错");
                        }
                        else {
                            alert(error);
                        }
                    });
                }).catch((error) => {
                    alert("修改失败");
                    console.log(error.response);
                })
            },
            uploadSucceed: function(response,file,filelist) {
                alert("上传成功");
                console.log(response);
            },
            uploadFail: function(error,file,filelist) {
                alert("上传失败");
                console.log(error);
            }
        },
        computed: {
            uploadUrl: function () {
                return "/api/users/" + this.$store.state.user.userInfo.id + "/avatar"
            },
            imageUrl: function () {
                return this.$store.getters.userAvatarUrl;
            },
            uploadHeader: function () {
                return {
                    'Authorization': "Bearer " + this.$store.state.user.accessToken,
                    'Content-Type': 'multipart/form-data'
                }
            }
        }
    }
</script>

<style scoped>
    .avatar-uploader {
        border: 1px dashed #d9d9d9;
        border-radius: 100%;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>