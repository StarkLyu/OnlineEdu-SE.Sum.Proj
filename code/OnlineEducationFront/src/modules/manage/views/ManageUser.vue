<template>
    <div>
        <div>
            <div class="divleft">
                <el-input
                        class="padding"
                        v-model="search"
                        placeholder="请输入用户名"
                        prefix-icon="el-icon-search"/>
                <el-upload
                        class="upload-demo"
                        action="/api/users/bulkImport"
                        :http-request="uploadExcel"
                        :on-preview="handlePreview"
                        :on-progress="UploadProgress"
                        :on-remove="handleRemove"
                        :before-remove="beforeRemove"
                        :limit="3"
                        :on-exceed="handleExceed"
                        :file-list="fileList">
                    <el-button size="small" type="primary">点击上传用户信息</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传.xls或.xlsx文件</div>
                </el-upload>
                <el-progress v-if="excelFlag ===true" :percentage="excelUploadPercent" style="margin-top:10px;">
                </el-progress>
            </div>
            <div class="divright">
                <el-button @click="handleAdd">新增</el-button>
            </div>
            <el-table :data="UserData.filter(data=>!search || data.username.includes(search))"
                      class="usertable"
                      highlight-current-row="true">
                <el-table-column >
                    <el-table-column type="index">
                    </el-table-column>
                    <el-table-column
                            prop="sno"
                            label="用户编号"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="username"
                            label="用户名"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="role"
                            label="用户身份"
                            min-width="25%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="tel"
                            label="电话"
                            min-width="50%"
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
                            <span v-if="scope.row.role!=='管理员'">
                                <el-button type="button" @click="handleEdit(scope.$index, scope.row)">
                                    修改
                                </el-button>
                                <el-button type="button" @click="handleDel(scope.$index, scope.row)">
                                    删除
                                </el-button>
                            </span>
                            <span v-else>无法操作</span>
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
            <el-form :model="editForm" :rules="formRule" label-width="80px" ref="editForm">
                <el-form-item label="用户编号">
                    <el-input type="text" v-model="editForm.sno"></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input type="text" v-model="editForm.username"></el-input>
                </el-form-item>
                <el-form-item label="用户身份">
                    <el-radio-group v-model="editForm.role">
                        <el-radio label="教师"></el-radio>
                        <span v-if="editForm.role!=='教师' && editForm.role!=='管理员'">
                            <el-radio label="学生"></el-radio>
                        </span>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="电话" prop="tel">
                    <el-input type="text" v-model="editForm.tel"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
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
    import getHeader from "../managerRequestHeader.js";
    import Rules from "../../index/rules.js"

    export default {
        name: "ManageUser",

        data(){
            return{
                search: '',

                UserData: [],

                dialogFormVisible:false,

                dialogStatus: "",

                textMap: {
                    update: "Edit",
                    create: "Create"
                },

                //编辑界面数据
                editForm: {
                    id:"",
                    sno:"",
                    username: "",
                    email:"",
                    tel:"",
                    role:"学生",
                },

                // 校验规则
                formRule: {
                    tel: Rules.telRule,
                    email: Rules.emailRule,
                },

                fileList:[],

                excelFlag:false,

                excelUploadPercent:0,

            }
        },

        methods:{
            showAllUsers(){
                var that=this;
                this.$axios.request({
                    url: '/api/users/info/all',
                    method: "get",
                    headers: getHeader.requestHeader()
                })
                    .then(function (response) {
                        console.log(response.data);
                        // alert("请求成功");
                        that.UserData = response.data;

                        // 存储role
                        for (let index=0; index<that.UserData.length; index++)
                        {
                            var temprolearray=[];
                            temprolearray=response.data[index].roles;
                            // console.log(temprolearray[0].role);

                            for (let x=0; x<temprolearray.length; x++)
                            {
                                var temprole=temprolearray[x].role;
                                // console.log(temprolearray[x].role);

                                if (temprole==="ROLE_ADMIN"){
                                    that.UserData[index].role="管理员";
                                }
                                else if(temprole==="ROLE_TEACHING_ADMIN") {
                                    that.UserData[index].role="教师";
                                }
                                else{
                                    that.UserData[index].role="学生";
                                }
                            }
                        }

                    })
                    .catch(function (error) {
                        console.log(error);
                        // alert("请求失败");
                    })
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

            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },

            // 上传文件
            uploadExcel(file){
                let param = new FormData();
                param.append('excel',file.file);

                var that=this;
                this.$axios.request({
                    url: '/api/users/bulkImport',
                    method: "post",
                    headers: getHeader.requestHeader()+{'Content-Type':'multipart/form-data'},
                    data:param,
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("上传成功");
                        that.showAllUsers();

                    })
                    .catch(function (error) {
                        console.log(error);
                        // alert("请求失败");
                    });
            },

            // 进度条
            uploadProgress(file){
                this.excelFlag = true;
                this.excelUploadPercent = file.percentage.toFixed(0);
            },

            // 删除学生
            handleDel:function(index,row){
                alert(row.username+"已删除");
            },

            //显示编辑界面
            handleEdit: function(index, row) {
                this.dialogStatus = "update";
                this.dialogFormVisible = true;
                this.editForm = Object.assign({}, row);
                console.log(this.editForm);
            },

            //显示新增界面
            handleAdd: function() {
                this.dialogStatus = "create";
                this.dialogFormVisible = true;
                this.editForm = {
                    sno:"",
                    username: "",
                    email:"",
                    role:'学生',
                }
            },

            createData(){
                alert("用户添加成功");
                this.dialogFormVisible=false;
            },


            updateData(){
                var that=this;
                // 把学生修改为老师
                if (this.editForm.role==='教师'){
                    this.$axios.request({
                        url: '/api/auth/'+this.editForm.id+'/teachingAdmin',
                        method: "post",
                        headers: getHeader.requestHeader()
                    })
                        .then(function (response) {
                            console.log(response.data);
                            // alert("请求成功");
                            // that.showAllUsers();

                        })
                        .catch(function (error) {
                            console.log(error);
                            // alert("请求失败");
                        });
                }

                // 修改用户信息
                this.$axios.request({
                    url: '/api/users/'+this.editForm.id+'/info/modify',
                    method: "post",
                    headers: getHeader.requestHeader(),
                    data:{
                        email:this.editForm.email,
                        tel:this.editForm.tel,
                        sno:this.editForm.sno,
                    }
                })
                    .then(function (response) {
                        console.log(response.data);
                        // alert("请求成功");
                        that.showAllUsers();

                    })
                    .catch(function (error) {
                        console.log(error);
                        // alert("请求失败");
                    })

                this.dialogFormVisible=false;
            }
        },

        mounted() {
            this.showAllUsers();
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
