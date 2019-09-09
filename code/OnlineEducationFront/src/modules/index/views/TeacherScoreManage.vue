<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程成绩管理</h1>
        </el-header>
        <el-main>
            <div class="float-left">
                <el-input class="padding"
                          v-model="search"
                          placeholder="请输入用户名"
                          prefix-icon="el-icon-search"/>
                <!--            导入成绩，上传的组件-->
                <el-upload
                        class="upload-demo"
                        ref="upload"
                        action="/api/users/bulkImport"
                        :http-request="uploadExcel"
                        :before-upload="beforeUpload"
                        :on-remove="handleRemove"
                        :on-preview="handlePreview"
                        :file-list="fileList"
                        :auto-upload="false">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传</el-button>
                    <div slot="tip" class="el-upload__tip">上传格式只能为xls或xlsx</div>
                </el-upload>
            </div>
<!--            成绩显示-->
            <el-table :data="UserData.filter(data=>!search || data.student.username.includes(search))"
                      class="usertable"
                      v-loading="loading"
                      stripe>
                <el-table-column >
                    <el-table-column type="index">
                    </el-table-column>
                    <el-table-column
                            prop="student.sno"
                            label="学号"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="student.username"
                            label="学生名"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="student.university"
                            label="学院"
                            min-width="25%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="score2"
                            label="成绩"
                            min-width="25%"
                    ></el-table-column>
                    <el-table-column
                            prop="student.sno"
                            label="操作"
                            min-width="40%">
                        <template slot-scope="scope">
                            <el-button type="button" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">
                                修改
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </el-main>
        <!--成绩编辑弹窗-->
        <el-dialog
                :title="'成绩修改'"
                :visible.sync="dialogFormVisible"
                :lock-scroll="false"
                v-loading="loading_2"
                top="5%">
            <el-form :model="editForm" label-width="80px" ref="editForm">
                <el-form-item label="学生">
                    <span>
                            {{editForm.username}}
                    </span>
                </el-form-item>
                <el-form-item label="成绩">
                    <el-input type="number" v-model="editForm.score"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="el-dialog__footer">
                <el-button @click.native="dialogFormVisible=false">取消</el-button>
                <el-button type="primary" @click="updateData">修改</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "TeacherScoreManage",

        data(){
            return{
                loading:true,

                loading_2:false,

                fileList: [],

                search: '',

                UserData: [],

                dialogFormVisible:false,

                //编辑界面数据
                editForm: [],
            }
        },

        methods:{
            showAllStudents(){
                var that=this;
                // 该课程所有学生
                this.$http.request({
                    url: '/api/courses/'+this.$store.getters.getCourseId+'/scoreList',
                    method: "get",
                    headers:this.$store.getters.authRequestHead,
                })
                    .then(function (response) {
                        console.log(response.data);

                        // 修改成绩显示
                        var UserScore=response.data.scoreMap;
                        for (let i=0; i<UserScore.length; i++) {
                            if (UserScore[i].score===-1){
                                UserScore[i].score2='暂无成绩';
                            }
                            else {
                                UserScore[i].score2=UserScore[i].score;
                            }
                        }
                        that.UserData=UserScore;
                        that.loading=false;
                        console.log(that.UserData);
                        // alert("请求成功");
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        // alert("显示学生成绩失败");
                    });
            },

            // 导入成绩
            handleAdd(){
                this.$message.info("导入成绩");
            },

            //显示编辑界面
            handleEdit: function(index, row) {
                this.dialogFormVisible = true;
                this.editForm = Object.assign({}, row);
                this.editForm.username=this.editForm.student.username;
                console.log(this.editForm);
            },

            // 提交修改后的成绩
            updateData(){
                var that=this;
                that.loading_2=true;
                //编辑课程信息
                this.$http.request({
                    url: '/api/courses/'+this.$store.getters.getCourseId+'/'+this.editForm.student.id+'/grade',
                    method: "put",
                    headers: this.$store.getters.authRequestHead,
                    params:{
                        grade:this.editForm.score,
                    }
                })
                    .then(function (response) {
                        console.log(response.data);

                        // alert("修改课程信息成功");
                        that.loading_2=false;
                        that.$message.success("修改学生成绩成功");
                        that.showAllStudents();
                        that.dialogFormVisible=false;

                    })
                    .catch(function (error) {
                        console.log(error);
                        // alert("修改课程信息失败");
                        that.loading_2=false;
                        that.$message.error("修改学生成绩失败"+error.response.data);
                    });
            },

            submitUpload() {
                this.$refs.upload.submit();
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },

            handlePreview(file) {
                console.log(file);
            },

            // 上传前校验格式
            beforeUpload(file) {
                let Xls = file.name.split('.');

                if (Xls[1] === 'xls' || Xls[1] === 'xlsx') {
                    return file
                } else {
                    this.$message.error('上传文件只能是 xls/xlsx 格式!');
                    return false;
                }
            },

            // 上传文件
            uploadExcel(file){
                console.log("正在上传文件");
                this.$message.info("正在上传文件");

                // 进度条
                // this.excelFlag = true;

                let param = new FormData();
                param.append('excel',file.file);

                var that=this;
                this.$http.request(
                    {
                        url: '/api/courses/'+this.$store.getters.getCourseId+'/bulkImportGrade',
                        method: "put",
                        headers:{
                            'Authorization': "Bearer " + that.$store.state.user.accessToken,
                            'Content-Type': 'multipart/form-data'
                        },
                        data:param,
                        // onUploadProgress: (event) => {
                        //     // 监听上传进度
                        //     if (event.lengthComputable) {
                        //         let val = (event.loaded / event.total * 100).toFixed(0);
                        //         that.excelFlag = true;
                        //         that.excelUploadPercent = parseInt(val);
                        //         // console.log(val);
                        //     }
                        // }
                    },
                )
                    .then(function (response) {
                        console.log(response.data);
                        that.$message.success('上传成功');
                        that.excelFlag=false;
                        that.showAllStudents();
                    })
                    .catch(function (error) {
                        console.log(error.response.data);
                        that.$message.error(error.response.data);
                        that.excelFlag=false;
                    });
            },
        },

        mounted() {
            this.showAllStudents();
        }
    }
</script>

<style scoped>
    .usertable {
        width: 100%;
        font-size: 15px;
        padding-bottom:20px;
    }

    .padding {
        padding: 8px;
    }

    .titlesytle {
        text-align: center;
        padding-top: 20px
    }
</style>
