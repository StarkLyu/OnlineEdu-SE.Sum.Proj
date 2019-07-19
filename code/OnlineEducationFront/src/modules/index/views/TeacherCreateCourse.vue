<template>
    <div>
        <el-steps :active="active" finish-status="success">
            <el-step title="课程原型"></el-step>
            <el-step title="创建课程"></el-step>
        </el-steps>
        <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
<!--        申请使用课程原型或创建-->
        <div v-if="active===0">
            <el-table :data="ProtoTable"  height="300px">
                <el-table-column type="index" width="80%">
                    <template slot-scope="scope">
                        <el-radio v-model="ProtoChoose" :label="scope.row.id">&nbsp;</el-radio>
                    </template>
                </el-table-column>
                <el-table-column property="title" label="原型名称" sortable></el-table-column>
                <el-table-column property="description" label="描述"></el-table-column>
            </el-table>
            <span slot="footer">
                <el-button type="primary" @click="showCreateDialog">创建课程原型</el-button>
                <el-button @click="applyProto">申请使用该原型</el-button>
            </span>
            <!--课程原型新增页面弹窗-->
            <el-dialog :title="'创建课程原型'"
                       :visible.sync="dialogFormVisible"
                       :lock-scroll="false"
                       top="5%">
                <!--            课程原型的基本信息-->
                <el-form :model="createForm" label-width="80px" ref="editForm">
                    <el-form-item label="原型名">
                        <el-input type="text" v-model="createForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="描述">
                        <el-input type="text" v-model="createForm.description"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer">
                <el-button @click.native="dialogFormVisible=false">取消</el-button>
                <el-button type="primary" @click="createProto">
                    创建
                </el-button>
            </span>
            </el-dialog>
        </div>
<!--        申请开课-->
        <div v-if="active===1">
            这是第二步
        </div>
    </div>
</template>

<script>
    export default {
        name: "TeacherCreateCourse",

        data() {
            return {
                active: 0,

                ProtoTable:[],

                ProtoChoose:"",

                dialogFormVisible:false,

                createForm: {
                    title: "",
                    description:"",
                }
            };
        },

        methods:{
            next() {
                if (this.active++ > 1) this.active = 0;
            },

            // 选择课程原型
            showAllProtos(){
                //显示所有课程原型
                var that=this;
                that.ProtoTable=[];

                this.$axios.request({
                    url: '/api/coursePrototypes/info/all',
                    method: "get",
                })
                    .then(function (response) {
                        console.log(response.data);

                        for (let x=0; x<response.data.length; x++){
                            if (response.data[x].state==='USING'){
                                that.dialogProtoForm.push(response.data[x]);
                            }
                        }
                    })
                    .catch(function (error) {
                        console.log(error.response);
                        alert("请求失败");
                    });
            },

            // 显示创建课程原型页面
            showCreateDialog(){
                this.dialogFormVisible=true;
                this.createForm={
                    id:"",
                    title: "",
                    description:"",
                };
            },

            // 创建课程原型
            createProto(){
                var that=this;

                this.$axios.request({
                    url: '/api/coursePrototypes/',
                    method: "post",
                    // headers: getHeader.requestHeader(),
                    data:{
                        title:this.createForm.title,
                        description:this.createForm.description,
                    }
                })
                    .then(function (response) {
                        console.log(response.data);

                        that.showAllProtos();
                        that.dialogFormVisible=false;
                    })
                    .catch(function (error) {
                        console.log(error);
                        // alert("请求失败");
                    });
            },

            // 申请使用课程原型
            applyProto(){
                var that=this;

                this.$axios.request({
                    url: '/api/coursePrototypes/',
                    method: "post",
                    // headers: getHeader.requestHeader(),
                    data:{
                        title:this.createForm.title,
                        description:this.createForm.description,
                    }
                })
                    .then(function (response) {
                        console.log(response.data);

                        that.showAllProtos();
                        that.dialogFormVisible=false;
                    })
                    .catch(function (error) {
                        console.log(error);
                        // alert("请求失败");
                    });
            },
        },

        mounted() {
            this.showAllProtos();
        }
    }
</script>

<style scoped>

</style>
