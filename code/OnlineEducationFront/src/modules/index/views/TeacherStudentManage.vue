<template>
    <div>
        <div class="margintop">
            <h1 class="titlesytle">学生信息查看</h1>
            <div class="float-left">
                <el-input
                        class="padding"
                        v-model="search"
                        placeholder="请输入用户名"
                        prefix-icon="el-icon-search"
                />
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
                        userId:"45112323",
                        userName:"kamen",
                        userCollege:"化学化工",
                        email:"1099@fg.co"
                    },
                    {
                        userId:"2144641",
                        userName:"student",
                        userCollege:"电子信息",
                        email:"1daswew9@fger.coq"
                    },
                    {
                        userId:"78089870",
                        userName:"zhujiao",
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

    .margintop {
        margin-top: 30px
    }

    .titlesytle {
        text-align: center;
        padding-top: 20px
    }
</style>
