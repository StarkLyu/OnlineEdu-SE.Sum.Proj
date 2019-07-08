<template>
    <div>
        <div class="margintop">
            <h1 class="titlesytle">课程成绩管理</h1>
            <div class="float-left">
                <el-input
                        class="padding"
                        v-model="search"
                        placeholder="请输入用户名"
                        prefix-icon="el-icon-search"
                />
            </div>
            <div class="float-right">
                <el-button @click="handleAdd">导入成绩</el-button>
            </div>
            <el-table :data="UserData.filter(data=>!search || data.userName.includes(search))"
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
                            <el-button type="button" @click="handleEdit(scope.$index, scope.row)">
                                修改
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </div>
        <!--编辑增加页面弹窗-->
        <el-dialog
                :title="'成绩修改'"
                :visible.sync="dialogFormVisible"
                :lock-scroll="false"
                top="5%">
            <el-form :model="editForm" label-width="80px" ref="editForm">
                <el-form-item>
                    <h3>学生姓名</h3>
                    <span>
                            {{editForm.userName}}
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
                search: '',

                UserData: [
                    {
                        userId:"45112323",
                        userName:"张三",
                        userCollege:"化学化工",
                        score:98,
                    },
                    {
                        userId:"2144641",
                        userName:"李四",
                        userCollege:"电子信息",
                        score:48,
                    },
                    {
                        userId:"78089870",
                        userName:"王二",
                        userCollege:"机动",
                        score:80,
                    }
                ],

                dialogFormVisible:false,

                //编辑界面数据
                editForm: {
                    userName:"",
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
                this.dialogStatus = "update";
                this.dialogFormVisible = true;
                this.editForm = Object.assign({}, row);
            },

            // 提交修改后的成绩
            updateData(){
                alert("用户修改成功");
                this.dialogFormVisible=false;
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

    .margintop {
        margin-top: 30px
    }

    .titlesytle {
        text-align: center;
        padding-top: 20px
    }
</style>
