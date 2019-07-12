<template>
    <div>
        <el-header>
            <h1 class="titlesytle">学生信息查看</h1>
        </el-header>
        <el-main>
            <div class="float-left">
                <el-input class="padding"
                          v-model="search"
                          placeholder="请输入用户名"
                          prefix-icon="el-icon-search"/>
            </div>
<!--            学生信息显示table-->
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
                            prop="userCollege"
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
        </el-main>
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
                        sno:"45112323",
                        username:"kamen",
                        userCollege:"化学化工",
                        email:"1099@fg.co"
                    },
                    {
                        sno:"2144641",
                        username:"student",
                        userCollege:"电子信息",
                        email:"1daswew9@fger.coq"
                    },
                    {
                        sno:"78089870",
                        username:"zhujiao",
                        userCollege:"机动",
                        email:"df633339@qq.com"
                    }
                ],
            }
        },
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
