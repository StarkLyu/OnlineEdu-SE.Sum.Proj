<template>
    <div>
        <div>
            <div class="divleft">
                <el-input
                        class="padding"
                        v-model="search"
                        placeholder="请输入课程名"
                        prefix-icon="el-icon-search"/>
            </div>
            <div class="divright">
                <el-button @click="handleAdd">新增</el-button>
            </div>
            <el-table :data="CourseData.filter(data=>!search || data.courseName.includes(search))"
                      class="coursetable"
                      highlight-current-row="true">
                <el-table-column >
                    <el-table-column type="index">
                    </el-table-column>
                    <el-table-column
                            prop="courseId"
                            label="课程号"
                            min-width="25%"
                            sortable
                    ></el-table-column>
                    <el-table-column
                            prop="courseName"
                            label="课程名"
                            min-width="30%"
                            sortable
                    ></el-table-column>
                    <el-table-column
                            prop="courseTime"
                            label="上课时间"
                            min-width="40%"
                            sortable
                    ></el-table-column>
                    <el-table-column
                            prop="courseRoom"
                            label="上课地点"
                            min-width="35%"
                            sortable
                    ></el-table-column>
                    <el-table-column
                            prop="courseTeacher"
                            label="授课教师"
                            min-width="25%"
                            sortable
                    ></el-table-column>
<!--                    显示课程状态的tab-->
                    <el-table-column
                            prop="courseState"
                            label="课程状态"
                            min-width="25%"
                            sortable>
                        <template slot-scope="scope">
                            <el-tag :type="scope.row.courseStateTemp">
                                {{scope.row.courseState}}
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="courseId"
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
        <!--课程编辑页面弹窗-->
        <el-dialog :title="textMap[dialogStatus]"
                   :visible.sync="dialogFormVisible"
                   :lock-scroll="false"
                   top="5%">
<!--            课程的基本信息-->
            <el-form :model="editForm" label-width="80px" ref="editForm">
                <el-form-item label="课程号">
                    <el-input type="text" v-model="editForm.courseId"></el-input>
                </el-form-item>
                <el-form-item label="课程名">
                    <el-input type="text" v-model="editForm.courseName"></el-input>
                </el-form-item>
                <el-form-item label="上课时间">
                    <el-input type="text" v-model="editForm.courseTime"></el-input>
                </el-form-item>
                <el-form-item label="上课地点">
                    <el-input type="text" v-model="editForm.courseRoom"></el-input>
                </el-form-item>
                <el-form-item label="授课教师">
                    <el-input type="text" v-model="editForm.courseTeacher"></el-input>
                </el-form-item>
                <el-form-item label="课程状态">
                    <el-radio-group v-model="editForm.courseState">
                        <el-radio label="待审核"></el-radio>
                        <el-radio label="未开始"></el-radio>
                        <el-radio label="进行中"></el-radio>
                        <el-radio label="已结束"></el-radio>
                        <el-radio label="未通过"></el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
<!--            编辑页面下的学生信息-->
            <div class="divleftmargin">
                <el-input type="text"
                          placeholder="请输入学生学号"
                          v-model="addStudent">
                </el-input>
            </div>
            <div class="divleft">
                <el-button @click="AddCourseStudent">
                    增加学生
                </el-button>
            </div>
            <el-table :data="StudentForm" height="300px">
                <el-table-column type="index"></el-table-column>
                <el-table-column property="userId" label="学号" sortable></el-table-column>
                <el-table-column property="userName" label="学生"></el-table-column>
                <el-table-column>
                    <template slot-scope="scope">
                        <el-button type="button" @click="DelStudent(scope.$index, scope.row)">
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <span slot="footer">
                <el-button @click.native="dialogFormVisible=false">取消</el-button>
                <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">
                    添加
                </el-button>
                <el-button v-else type="primary" @click="updateData">
                    修改
                </el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "ManageCourse",

        data(){
            return{
                search:"",

                CourseData: [
                    {
                        courseId: "CS-23",
                        courseName: "数据分析",
                        courseTime: "1-16周 8:00-10:00",
                        courseRoom: "东上院211",
                        courseTeacher: "sheng",
                        courseState:"待审核",
                        courseStateTemp:'warning',
                    },
                    {
                        courseId: "SE-9911",
                        courseName: "软件工程导论",
                        courseTime: "1-16周 16:00-18:00",
                        courseRoom: "上院211",
                        courseTeacher: "daf",
                        courseState:"进行中",
                        courseStateTemp:'success',
                    },
                    {
                        courseId: "CS-524",
                        courseName: "java设计思想",
                        courseTime: "1-16周 12:00-14:00",
                        courseRoom: "东下院201",
                        courseTeacher: "zang",
                        courseState:"未开始",
                        courseStateTemp:'primary',
                    },
                    {
                        courseId: "CS-524",
                        courseName: "java设计思想",
                        courseTime: "1-16周 12:00-14:00",
                        courseRoom: "东下院201",
                        courseTeacher: "zang",
                        courseState:"未通过",
                        courseStateTemp:'danger',
                    },
                    {
                        courseId: "CS-524",
                        courseName: "java设计思想",
                        courseTime: "1-16周 12:00-14:00",
                        courseRoom: "东下院201",
                        courseTeacher: "zang",
                        courseState:"已结束",
                        courseStateTemp:'info',
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
                    courseId:"",
                    courseName:"",
                    courseTime:"",
                    courseRoom:"",
                    courseTeacher:"",
                    courseState:"",
                },

                addStudent:"",

                // 课程的学生信息
                StudentForm: [
                    {
                        sno:"124321",
                        username:"刘鹏",
                    },
                    {
                        sno:"3521",
                        username:"达芙蓉",
                    },
                    {
                        sno:"351131",
                        username:"万格恩",
                    },
                ]
            }
        },

        methods:{
            handleDel:function(index,row){
                alert(row.courseName+"已删除");
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
                    id: "0",
                    bookname: "",
                    author:"",
                    stocks:0,
                }
            },

            // 添加课程
            createData(){
                alert("课程添加成功");
                this.dialogFormVisible=false;
            },

            // 修改课程
            updateData(){
                alert("课程修改成功");
                this.dialogFormVisible=false;
            },

            //课程添加学生
            AddCourseStudent(){
                alert(this.addStudent+" "+"该课程添加学生成功");
            },

            //课程删除学生
            DelStudent: function(index, row){
                alert(row.username+"已删除");
            }

        }
    }
</script>

<style>
    .divleft {
        float: left;
    }

    .divleftmargin{
        float: left;
        margin-right: 20px;
    }

    .divright {
        float: right;
    }

    .padding {
        padding: 8px;
    }

    .coursetable {
        width: 100%;
        font-size: 15px;
        padding-bottom:20px;
    }

</style>
