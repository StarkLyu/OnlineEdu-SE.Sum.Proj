<template>
    <div>
        <el-header>
            <h1 class="titlesytle">作业批改</h1>
        </el-header>
        <el-main>
<!--            展示所有学生的答题情况-->
            <el-table :data="UserData.filter(data=>!search || data.username.includes(search))"
                      class="usertable"
                      stripe>
                <el-table-column >
                    <el-table-column type="index">
                    </el-table-column>
                    <el-table-column
                            prop="sno"
                            label="学号"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="username"
                            label="学生名"
                            min-width="35%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="score"
                            label="作业情况"
                            min-width="25%"
                    ></el-table-column>
                    <el-table-column
                            prop="sno"
                            label="操作"
                            min-width="40%">
                        <template slot-scope="scope">
                            <el-button type="button" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">
                                批改
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </el-main>
<!--        批改作业-->
        <el-dialog
                :title="'作业批改'"
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
    import Bus from '../bus.js'
    export default {
        name: "TeacherAssignCorrect",

        data(){
            return{
                assignId:"",

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
            // 展示所有学生的答题情况
            showStuAssigns(){

            },

            //显示编辑界面
            handleEdit: function(index, row) {
                this.dialogFormVisible = true;
                this.editForm = Object.assign({}, row);
            },

            // 提交批改结果
            updateData(){
                alert("作业批改完成");
                this.dialogFormVisible=false;
            },
        },

        mounted() {
            var vm = this;
            // 用$on事件来接收参数
            Bus.$on('assignId', (data) => {
                console.log(data);
                vm.assignId = data;
            });

            vm.showStuAssigns();
        }
    }
</script>

<style scoped>
    .titlesytle {
        text-align: center;
        padding-top: 20px
    }

    .usertable {
        width: 100%;
        font-size: 15px;
        padding-bottom:20px;
    }
</style>
