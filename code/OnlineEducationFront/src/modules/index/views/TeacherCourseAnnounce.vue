<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程公告</h1>
        </el-header>
        <el-main>
            <el-button class="addbotton" @click="addAnnounce" icon="el-icon-plus">
                发布公告
            </el-button>
            <!--            公告面板-->
            <div>
                <el-collapse>
                    <el-collapse-item
                            v-for="announce in announcements"
                            :key="announce.time">
                        <template slot="title">
                            <h2>{{ announce.title }}</h2>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <span class="float-right">{{ announce.time }}</span>
                        </template>
                        <div>
                            <el-button type="button" @click="handleEdit(announce)" icon="el-icon-edit">
                                编辑
                            </el-button>
                            <el-button type="button" @click="handleDel(announce)" icon="el-icon-delete">
                                删除
                            </el-button>
                        </div>
                        <div>
                            <pre class="announce-font" >{{ announce.content }}</pre>
                        </div>
                    </el-collapse-item>
                </el-collapse>
            </div>
            <!--发布、编辑公告弹窗-->
            <el-dialog
                    :title="textMap[dialogStatus]"
                    :visible.sync="dialogFormVisible"
                    :lock-scroll="false"
                    top="5%">
                <el-form :model="editForm" label-width="80px" ref="editForm">
                    <el-form-item label="标题">
                        <el-input type="text" v-model="editForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="内容">
                        <el-input type="textarea" v-model="editForm.content" autosize></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="el-dialog__footer">
                <el-button @click.native="dialogFormVisible=false">取消</el-button>
                <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">
                    添加
                </el-button>
                <el-button v-else type="primary" @click="updateData">
                    修改
                </el-button>
            </span>
            </el-dialog>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseAnouce",

        data(){
            return{
                announcements: [
                    {
                        title: "测试公告1",
                        time: Date(),
                        content: "浏览论坛的贴子（数据库的数据通过vue遍历在html页面上）\n" + "\n" + "点击帖子的标题、图片，可以查看详细的帖子（点击事件获取id）"
                    },
                    {
                        title: "测试公告2",
                        time: Date(),
                        content: "你不能直接改变 store 中的状态。改变 store 中的状态的唯一途径就是显式地提交 (commit) mutation。这样使得我们可以方便地跟踪每一个状态的变化，从而让我们能够实现一些工具帮助我们更好地了解我们的应用。"
                    }
                ],

                dialogFormVisible:false,

                dialogStatus: "",

                textMap: {
                    update: "编辑公告",
                    create: "发布公告"
                },

                //发布公告数据
                editForm: {
                    title:"",
                    time:Date,
                    content:"",
                },
            }
        },

        methods:{
            // 显示新增页面
            addAnnounce(){
                this.dialogFormVisible = true;
                this.dialogStatus="create";
            },

            // 删除
            handleDel(){
                alert("已删除");
            },

            //显示编辑界面
            handleEdit(announce) {
                this.dialogStatus = "update";
                this.dialogFormVisible = true;
                this.editForm = Object.assign({}, announce);
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
        }
    }
</script>

<style scoped>
    @import "/src/assets/div-layout.css";

    .addbotton {
        margin-bottom: 30px;
    }

    .titlesytle {
        text-align: center;
        padding-top: 20px
    }

    .announce-font {
        font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
        white-space: pre-wrap;
        white-space: -moz-pre-wrap;
        /*white-space: -pre-wrap;*/
        white-space: -o-pre-wrap;
        word-wrap: break-word
    }
</style>
