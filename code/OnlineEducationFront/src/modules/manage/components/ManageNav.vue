<template>
    <div id="ManageNav">
        <el-col :span="16">
            <h3>管理</h3>
            <el-menu
                    default-active="2"
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose">
                <el-menu-item index="1">
                    <i class="el-icon-menu"></i>
                    <span slot="title">
                        <router-link to="/ManageUser">
                            用户管理
                        </router-link>
                    </span>
                </el-menu-item>
                <el-menu-item index="2">
                    <i class="el-icon-setting"></i>
                    <span slot="title">
                        <router-link to="/ManageCourse">
                            课程管理
                        </router-link>
                    </span>
                </el-menu-item>
            </el-menu>
        </el-col>
    </div>
</template>

<script>
    export default {
        name: "ManagNav",

        methods:{
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>
    a {
        text-decoration: none;
        color: dimgrey;
    }

    a:hover {
        color: black;
    }

    h3 {
        text-align: center;
    }
</style>
