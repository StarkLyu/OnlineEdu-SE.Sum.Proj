<template>
    <div>
        <div>
            <div class="divleft">
                <el-input
                        class="padding"
                        v-model="search"
                        placeholder="请输入用户名"
                        prefix-icon="el-icon-search"/>
            </div>
            <div class="divright">
                <el-button @click="handleAdd">新增</el-button>
            </div>
            <el-table :data="UserData.filter(data=>!search || data.userName.includes(search))"
                      class="usertable"
                      highlight-current-row="true">
                <el-table-column >
                    <el-table-column type="index">
                    </el-table-column>
                    <el-table-column
                            prop="userId"
                            label="用户编号"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="userName"
                            label="用户名"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="userRole"
                            label="用户身份"
                            min-width="25%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="passWord"
                            label="密码"
                            min-width="35%"
                    ></el-table-column>
                    <el-table-column
                            prop="email"
                            label="邮箱"
                            min-width="50%"
                    ></el-table-column>
                    <el-table-column
                            prop="userId"
                            label="操作"
                            min-width="40%">
                        <template slot-scope="scope">
                            <el-button type="button" @click="handleEdit(scope.$index, scope.row)">
                                修改
                            </el-button>
                            <el-button type="button" @click="handleDel(scope.$index, scope.row)">
                                删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </div>
        <!--编辑增加页面弹窗-->
        <el-dialog
                :title="textMap[dialogStatus]"
                :visible.sync="dialogFormVisible"
                :lock-scroll="false"
                top="5%">
            <el-form :model="editForm" label-width="80px" ref="editForm">
                <el-form-item label="用户编号">
                    <el-input type="text" v-model="editForm.userId"></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input type="text" v-model="editForm.userName"></el-input>
                </el-form-item>
                <el-form-item label="用户身份">
                    <el-radio-group v-model="editForm.userRole">
                        <el-radio label="老师/助教"></el-radio>
                        <el-radio label="学生"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="text" v-model="editForm.passWord"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input type="text" v-model="editForm.email"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="el-dialog__footer">
                <el-button @click.native="dialogFormVisible=false">取消</el-button>
                <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">添加</el-button>
                <el-button v-else type="primary" @click="updateData">修改</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "ManageUser",

        data(){
            return{
                search: '',

                UserData: [
                    {
                        userId:"45112323",
                        userName:"kamen",
                        userRole:"老师",
                        passWord:"123",
                        email:"1099@fg.co"
                    },
                    {
                        userId:"2144641",
                        userName:"student",
                        userRole:"学生",
                        passWord:"ewt",
                        email:"1daswew9@fger.coq"
                    },
                    {
                        userId:"78089870",
                        userName:"zhujiao",
                        userRole:"老师",
                        passWord:"edgdwwd",
                        email:"df633339@qq.com"
                    }
                ],

                dialogFormVisible:false,

                dialogStatus: "",

                textMap: {
                    update: "Edit",
                    create: "Create"
                },

                //编辑界面数据
                editForm: {
                    userId:"",
                    userName: "",
                    passWord:"",
                    email:"",
                    userRole:"",
                },
            }
        },

        methods:{
            handleDel:function(index,row){
                alert(row.userName+"已删除");
            },

            //显示编辑界面
            handleEdit: function(index, row) {
                this.dialogStatus = "update";
                this.dialogFormVisible = true;
                this.editForm = Object.assign({}, row);
            },

            //显示新增界面
            handleAdd: function() {
                this.dialogStatus = "create";
                this.dialogFormVisible = true;
                this.editForm = {
                    userId:"",
                    userName: "",
                    password:"111111",
                    email:"",
                }
            },

            createData(){
                alert("用户添加成功");
                this.dialogFormVisible=false;
            },

            updateData(){
                alert("用户修改成功");
                this.dialogFormVisible=false;
            }
        }
    }
</script>

<style scoped>
    .divleft {
        float: left;
    }

    .divright {
        float: right;
    }

    .padding {
        padding: 8px;
    }

    .usertable {
        width: 100%;
        font-size: 15px;
        padding-bottom:20px;
    }

</style>
