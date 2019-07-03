<template>

    <div>
        <div>
            <div style="float: left;">
                <el-input v-model="search" placeholder="请输入课程名" prefix-icon="el-icon-search"
                          style="width: 200px; padding: 8px; border: none; font-size: 25px; height: 30px"/>
            </div>
            <div style="float: right">
                <el-button style="padding: 8px;" @click="handleAdd">新增</el-button>
            </div>


            <el-table :data="CourseData.filter(data=>!search || data.coursename.includes(search))"
                      style="width: 100%; font-size: 20px; padding-top: 10px; padding-bottom:20px;;"
                      cell-style="border:1px solid;">
                <el-table-column >
                    <el-table-column
                            type="index"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="courseid"
                            label="课程号"
                            min-width="35%"
                    ></el-table-column>
                    <el-table-column
                            prop="coursename"
                            label="课程名"
                            min-width="40%"
                            sortable
                    ></el-table-column>
                    <el-table-column
                            prop="courseteacher"
                            label="授课教师"
                            min-width="35%"
                            sortable
                    ></el-table-column>
                    <el-table-column
                            prop="id"
                            label="操作">
                        <template slot-scope="scope">
                              <span v-if="scope.row.courseid!==''">
                                <el-button type="button" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
                                <el-button type="button" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                              </span>
                        </template>
                    </el-table-column>
                </el-table-column>
            </el-table>
        </div>
    </div>

</template>

<script>
    export default {
        name: "ManageCourse",

        data(){
            return{
                search:"",
                CourseData:[
                    {
                        courseid:"CS-23",
                        coursename:"ics",
                        courseteacher:"sheng",
                    }
                ]
            }
        },

        methods:{
            handleEdit:function(index,row){
                alert(index+"+"+row.coursename);
            },

            handleDel:function(index,row){
                alert(index+"+"+row.coursename);
            },

            handleAdd:function(){
                alert("增加啦！");
            }

        }
    }
</script>

<style scoped>
    .el-textarea__inner{
        font-family: "Heiti SC";
        font-size: 40px;
    }
</style>
