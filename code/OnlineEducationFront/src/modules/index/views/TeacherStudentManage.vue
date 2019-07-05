<template>
    <div>
        <div class="margintop">
            <h1 class="titlesytle">学生信息查看</h1>
            <div class="float-left">
                <el-input
                        class="padding"
                        v-model="search"
                        placeholder="请输入用户名"
                        prefix-icon="el-icon-search"/>
            </div>
            <el-table :data="UserData.filter(data=>!search || data.username.includes(search))" class="usertable">
                <el-table-column >
                    <el-table-column type="index">
                    </el-table-column>
                    <el-table-column
                            prop="userid"
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
                            prop="usercollege"
                            label="学院"
                            min-width="25%"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="email"
                            label="邮箱"
                            min-width="50%"
                    ></el-table-column>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "TeacherStudentManage",

        data(){
            return{
                search: '',

                UserData: [
                    {
                        userid:"45112323",
                        username:"kamen",
                        usercollege:"化学化工",
                        email:"1099@fg.co"
                    },
                    {
                        userid:"2144641",
                        username:"student",
                        usercollege:"电子信息",
                        email:"1daswew9@fger.coq"
                    },
                    {
                        userid:"78089870",
                        username:"zhujiao",
                        usercollege:"机动",
                        email:"df633339@qq.com"
                    }
                ],
            }
        }
    }
</script>

<style scoped>
    .usertable {
        width: 90%;
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
