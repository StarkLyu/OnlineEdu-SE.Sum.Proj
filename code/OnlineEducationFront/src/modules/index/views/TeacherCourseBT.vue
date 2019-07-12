<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程资源</h1>
        </el-header>
        <el-main>
            <div class="float-left">
                <el-upload
                        class="upload-demo"
                        action="https://jsonplaceholder.typicode.com/posts/"
                        :on-change="handleChange"
                        :file-list="fileList">
                    <el-button size="small" type="primary">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">上传课程资源文件</div>
                </el-upload>
            </div>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseBT",

        data(){
            return{
                fileList: []
            }
        },

        methods: {
            handleChange(file, fileList) {
                this.fileList = fileList.slice(-3);
            }
        }
    }
</script>

<style scoped>
    @import "/src/assets/div-layout.css";

    .titlesytle {
        text-align: center;
        padding-top: 20px
    }
</style>
