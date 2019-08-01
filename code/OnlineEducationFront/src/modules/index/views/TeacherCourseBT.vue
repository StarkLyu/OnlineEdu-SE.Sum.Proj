<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程资源</h1>
        </el-header>
        <el-main>
            <div class="float-left">
                <el-upload
                        class="upload-demo"
                        ref="upload"
                        action=""
                        :http-request="uploadBT"
                        :before-upload="beforeUpload"
                        :on-preview="handlePreview"
                        :on-remove="handleRemove"
                        :limit="3"
                        :on-exceed="handleExceed"
                        :auto-upload="false"
                        :file-list="fileList">
                    <el-button slot="trigger" size="small" type="primary" style="margin-right: 10px">点击选择文件</el-button>
                    <el-button size="small" type="success" @click="submitUpload">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">上传课程资源，一次最多三个</div>
                </el-upload>
            </div>
<!--            展示所有资源-->
            <el-table :data="courseRes"
                      highlight-current-row="true">
                <el-table-column >
                    <el-table-column type="index">
                    </el-table-column>
                    <el-table-column
                            prop="title"
                            label="资源名称"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="resourceType"
                            label="资源类型"
                            min-width="25%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="id"
                            label="操作"
                            fixed="right"
                            min-width="20%">
                        <template slot-scope="scope">
                            <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">
                                删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseBT",

        data(){
            return{
                fileList: [],

                BTtype:"",

                courseRes:this.$store.getters.getCourseInfo.coursePrototype.resources,
            }
        },

        methods: {
            submitUpload() {
                this.$refs.upload.submit();
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },

            handlePreview(file) {
                console.log(file);
            },

            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },

            // 上传前校验格式
            beforeUpload(file) {
                let type = file.name.split('.');

                if (type[1] === 'pdf') {
                    this.BTtype='pdf';
                    return file;
                }
                else if(type[1]==='ppt' || type[1]==='pptx'){
                    this.BTtype='ppt';
                    return file;
                }
                else if(type[1]==='mp4'){
                    this.BTtype='video';
                }
                else{
                    this.$message.error('上传文件只能是pdf、ppt或视频！');
                    return false;
                }
            },

            // 上传文件
            uploadBT(file){
                // let type = file.name.split('.');
                //
                // if (type[1] === 'pdf') {
                //     this.BTtype==='pdf';
                // }
                // else if(type[1]==='ppt' || type[1]==='pptx'){
                //     return file
                // }

                console.log("正在上传文件");

                // 进度条
                // this.excelFlag = true;

                let res = new FormData();
                res.append('resource',file.file);

                var that=this;
                this.$http.request(
                    {
                        url: '/api/coursePrototypes/'+this.$store.getters.getCourseInfo.coursePrototype.id+"/"+this.BTtype+"/",
                        method: "post",
                        headers: {Authorization: "Bearer " + this.$store.state.user.accessToken ,'Content-Type':'multipart/form-data'},
                        data:res,
                    },
                    // {
                    //     onUploadProgress: (event) => {
                    //         // 监听上传进度
                    //         event.percent = event.loaded / event.total * 100;
                    //         this.excelUploadPercent=event.percent;
                    //         file.onProgress(event);
                    //     }
                    // }
                )
                    .then(function (response) {
                        console.log(response.data);

                        alert("上传成功");

                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },

        }
    }
</script>

<style scoped>
    .titlesytle {
        text-align: center;
        padding-top: 20px
    }
</style>
