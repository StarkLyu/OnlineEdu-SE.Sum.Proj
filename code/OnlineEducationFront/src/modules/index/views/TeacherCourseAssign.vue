<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程作业</h1>
        </el-header>
        <el-main>
            <el-table
                    :data="AssignData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                    style="width: 100%">
                <el-table-column
                        prop="date"
                        label="日期"
                        sortable
                        min-width="25%">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="作业名"
                        sortable
                        min-width="50%">
                </el-table-column>
                <el-table-column
                        min-width="40%">
                    <template slot="header">
                        <el-button type="primary" icon="el-icon-plus" @click="handleAdd">添加作业</el-button>
                    </template>
                    <template slot-scope="scope">
                        <el-button size="small"
                                   type="success"
                                   icon="el-icon-document-checked"
                                   @click="handleAnnounce(scope.$index, scope.row)">
                            发布
                        </el-button>
                        <el-button size="small"
                                   icon="el-icon-edit"
                                   @click="handleDetail(scope.$index, scope.row)">
                            编辑
                        </el-button>
                        <el-button size="small"
                                   type="danger"
                                   icon="el-icon-delete"
                                   @click="handleDelete(scope.$index, scope.row)">
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseAssign",

        data(){
            return{
                search:"",

                AssignData:[
                    {
                        date: '2016-05-02',
                        name: '第一份作业',
                    }, {
                        date: '2016-05-04',
                        name: '第二份作业',
                    }, {
                        date: '2016-05-01',
                        name: '第三份作业',
                    }, {
                        date: '2016-05-03',
                        name: '第四份作业',
                    }
                ],
            }
        },

        methods:{
            handleAdd(){
                alert("添加作业");
            },

            handleAnnounce:function(index, row){
                alert("发布"+row.name);
            },

            handleDetail:function(index, row){
                this.$router.push("/course/manager/oneassignment");
            },

            handleDelete:function (index, row) {
                alert(row.name+"已删除");
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
