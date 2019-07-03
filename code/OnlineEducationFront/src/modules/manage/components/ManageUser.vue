<template>

    <div>
        <div>
            <el-input v-model="search" placeholder="请输入用户名" prefix-icon="el-icon-search"
                      style="width:50%; padding: 8px; border: none; font-size: 25px; height: 30px"/>

            <el-table :data="UserData.filter(data=>!search || data.username.includes(search))"
                      style="width: 100%; font-size: 20px; padding-top: 10px; padding-bottom:20px;;"
                        cell-style="border:1px solid;">
                <el-table-column >
                    <el-table-column
                            type="index"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="username"
                            label="用户名"
                            min-width="25%"
                            sortable
                    ></el-table-column>
                    <el-table-column
                            prop="password"
                            label="密码"
                            min-width="25%"
                    ></el-table-column>
                    <el-table-column
                            prop="email"
                            label="邮箱"
                            sortable
                    ></el-table-column>
                </el-table-column>
            </el-table>

        </div>

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
                        username:"kamen",
                        password:"123",
                        email:"1099@fg.co"
                    },
                    {
                        username:"admin",
                        password:"ewt",
                        email:"1daswew9@fger.coq"
                    }
                ],
            }
        }
    }
</script>

<style scoped>

</style>
