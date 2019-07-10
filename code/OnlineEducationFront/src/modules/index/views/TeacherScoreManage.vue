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
            </div>
<!--            导入成绩，上传的组件-->
            <div class="float-right">
                <el-upload
                        class="upload-demo"
                        ref="upload"
                        action="https://jsonplaceholder.typicode.com/posts/"
                        :on-preview="handlePreview"
                        :on-remove="handleRemove"
                        :file-list="fileList"
                        :auto-upload="false">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传</el-button>
                    <div slot="tip" class="el-upload__tip">上传成绩</div>
                </el-upload>
            </div>
<!--            成绩显示-->
            <el-table :data="UserData.filter(data=>!search || data.username.includes(search))"
                      class="usertable"
                      stripe>
                <el-table-column >
                    <el-table-column type="index">
                    </el-table-column>
                    <el-table-column
                            prop="userId"
                            label="学号"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="userName"
                            label="学生名"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="userCollege"
                            label="学院"
                            min-width="25%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="score"
                            label="成绩"
                            min-width="25%"
                    ></el-table-column>
                    <el-table-column
                            prop="userId"
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
                top="5%">
            <el-form :model="editForm" label-width="80px" ref="editForm">
                <el-form-item>
                    <h3>学生姓名</h3>
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
                fileList: [
                    {
                        name: 'food.jpeg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    },
                    {
                        name: 'food2.jpeg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    }],

                search: '',

                UserData: [
                    {
                        sno:"45112323",
                        username:"张三",
                        userCollege:"化学化工",
                        score:98,
                    },
                    {
                        sno:"2144641",
                        username:"李四",
                        userCollege:"电子信息",
                        score:48,
                    },
                    {
                        sno:"78089870",
                        username:"王二",
                        userCollege:"机动",
                        score:80,
                    }
                ],

                dialogFormVisible:false,

                //编辑界面数据
                editForm: {
                    username:"",
                    score:"",
                },
            }
        },

        methods:{
            // 导入成绩
            handleAdd(){
                alert("导入成绩");
            },

            //显示编辑界面
            handleEdit: function(index, row) {
                this.dialogFormVisible = true;
                this.editForm = Object.assign({}, row);
            },

            // 提交修改后的成绩
            updateData(){
                alert("用户修改成功");
                this.dialogFormVisible=false;
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
        }
    }
</script>

<style scoped>
    @import "/src/assets/div-layout.css";
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

    .addbutton {
        float: right;
        margin-right:20%;
    }
</style>
