<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程公告</h1>
        </el-header>
        <el-main>
            <el-button class="addbotton" @click="addAnnounce" icon="el-icon-plus">
                发布公告
            </el-button>
            <el-button class="addbotton" @click="addSignIn" icon="el-icon-location">
                发布签到
            </el-button>
            <!--            公告面板-->
            <div>
                <el-collapse>
                    <el-collapse-item
                            v-for="announce in announcements"
                            :key="announce.issueDate">
                        <template slot="title">
                            <h2>{{ announce.title }}</h2>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <span class="float-right">{{ announce.issueDate }}</span>
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
<!--            发布签到对话框-->
            <el-dialog
                    :title="发布签到"
                    :visible.sync="signDialogVisible"
                    :lock-scroll="false"
                    top="5%">
                <el-form :model="signInForm" label-width="80px" ref="signInForm">
                    <el-form-item label="签到时间">
                        <el-col :span="11">
                            <el-date-picker placeholder="选择开始时间"
                                            type="datetime"
                                            v-model="signInForm.startDate"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="11">
                            <el-date-picker placeholder="选择结束时间"
                                            type="datetime"
                                            v-model="signInForm.endDate"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="选择地点">
                        <div style="width: 100%; height: 100%">
                            <baidu-map class="map"
                                       :center="center"
                                       scroll-wheel-zoom="true"
                                       zoom="15"
                                       @click="getClickInfo"
                                       @ready="handler">
                                <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
                                <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
                                                :showAddressBar="true"
                                                :autoLocation="true">
                                </bm-geolocation>
                                <bm-marker :position="{lng: center.lng, lat: center.lat}" :dragging="true" animation="BMAP_ANIMATION_BOUNCE" v-model="signPosition">
                                    <bm-label content="签到地点" :labelStyle="{color: 'black', fontSize : '15px'}" :offset="{width: -35, height: 30}"/>
                                </bm-marker>
                            </baidu-map>
                        </div>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="el-dialog__footer">
                <el-button @click.native="signDialogVisible=false">取消</el-button>
                <el-button type="primary" @click="createSign">
                    发布
                </el-button>
            </span>
            </el-dialog>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseAnnounce",

        data(){
            return{
                announcements:this.$store.getters.getCourseInfo.notices,

                dialogFormVisible:false,

                signDialogVisible:false,

                dialogStatus: "",

                textMap: {
                    update: "编辑公告",
                    create: "发布公告"
                },

                //发布公告数据
                editForm: {
                    title:"",
                    content:"",
                },

                signInForm:{
                    endDate: "",
                    startDate: "",
                },

                center: {lng: 116.404, lat: 39.915},
            }
        },

        methods:{
            // 显示新增页面
            addAnnounce(){
                this.dialogFormVisible = true;
                this.dialogStatus="create";
            },

            // 显示发布签到dialog
            addSignIn(){
                this.signDialogVisible=true;
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

            // 添加公告
            createData(){
                // var that=this;
                this.$http.request({
                    url: '/api/courses/'+this.$store.getters.getCourseId+'/notices/',
                    method: "post",
                    headers: this.$store.getters.authRequestHead,
                    data:{
                        content:this.editForm.content,
                        title:this.editForm.title,
                    }
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("添加公告成功");

                    })
                    .catch(function (error) {
                        console.log(error.response);
                        alert("请求失败");
                    });

                this.dialogFormVisible=false;
            },

            // 修改课程
            updateData(){
                alert("公告修改成功");
                this.dialogFormVisible=false;
            },

            // 发布签到
            createSign(){
                console.log(this.center);

                this.$http.request({
                    url: '/api/courses/'+this.$store.getters.getCourseId+'/signIns',
                    method: "post",
                    headers: this.$store.getters.authRequestHead,
                    data:{
                        endDate: this.signInForm.endDate,
                        latitude: this.center.lat,
                        longitude: this.center.lng,
                        startDate: this.signInForm.startDate,
                    }
                })
                    .then(function (response) {
                        console.log(response.data);
                        alert("发布签到成功");

                    })
                    .catch(function (error) {
                        console.log(error.response);
                        alert("请求失败");
                    });
            },

            // 点击的地方设为地图中心
            getClickInfo (e) {
                this.center.lng = e.point.lng;
                this.center.lat = e.point.lat;
            },

            handler({ BMap }) {
                // console.log(BMap, map);
                this.tools.analysisAddress(
                    [
                        { lng: 116.307852, lat: 40.057031 },
                        { lng: 116.307852, lat: 40.057031 }
                    ],
                    res => {
                        console.log(res);
                    }
                );
            },
        }
    }
</script>

<style scoped>
    .addbotton {
        margin-bottom: 30px;
    }

    .titlesytle {
        text-align: center;
        padding-top: 20px
    }

    .announce-font {
        font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
        white-space: pre-wrap;
        white-space: -moz-pre-wrap;
        /*white-space: -pre-wrap;*/
        white-space: -o-pre-wrap;
        word-wrap: break-word
    }

    .map {
        width: 100%;
        height: 400px;
    }
</style>
