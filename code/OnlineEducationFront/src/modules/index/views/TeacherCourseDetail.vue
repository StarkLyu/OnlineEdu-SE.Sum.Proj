<template>
    <div>
        <el-header>
            <h1 class="titlesytle">课程详情</h1>
        </el-header>
        <el-main>
            <div class="courseimg">
                <el-tooltip effect="dark" content="点击头像上传头像图片" placement="top-start">
                    <el-upload
                            class="avatar-uploader avatar"
                            :action="uploadUrl"
                            :headers="uploadHeader"
                            :show-file-list="false"
                            :before-upload="beforeAvatarUpload"
                            :http-request="uploadProcess">
                        <img v-if="imageURL" :src="imageURL" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-tooltip>
            </div>
            <div class="coursedes">
                <p>
                    课程名称：
                    <span class="detail">
                        {{CourseForm.courseTitle}}
                    </span>
                </p>
                <p>
                    授课教师：
                    <span class="detail">
                        {{CourseForm.teacher.username}}
                    </span>
                </p>
                <p>
                    上课日期：
                    <span class="detail">
                        {{CourseForm.startDate}} 至 {{CourseForm.endDate}}
                    </span>
                </p>
                <p>
                    上课地点：
                    <span class="detail">
                        {{CourseForm.location}}
                    </span>
                </p>
                <div v-for="timeslot in CourseForm.timeSlots" :key="timeslot.id">
                    <p>
                        上课时间：
                        <span class="detail">
                            {{timeslot.day}}  {{timeslot.start}}至{{timeslot.end}}
                        </span>
                    </p>
                </div>
                <p>
                    课程状态：
                    <span class="detail">
                        {{CourseForm.state}}
                    </span>
                </p>
                <el-button @click="showEditDialog">编辑课程详情</el-button>
            </div>
            <el-dialog :title="'编辑课程信息'"
                       :visible.sync="courseDialogVisible"
                       :lock-scroll="false"
                       top="5%">
                <el-form :model="editForm" label-width="80px" ref="editForm">
                    <el-form-item label="课程名">
                        <el-input type="text" v-model="editForm.courseTitle"></el-input>
                    </el-form-item>
                    <el-form-item label="上课日程">
                        <el-col :span="11">
                            <el-date-picker placeholder="选择开始时间"
                                            type="date"
                                            v-model="editForm.startDate"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="11">
                            <el-date-picker placeholder="选择结束时间"
                                            type="date"
                                            v-model="editForm.endDate"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                    </el-form-item>
                    <!--                课程的上课时间段-->
                    <el-form-item label="课时">
                        <el-form-item
                                v-for="(timeslot, index) in editForm.timeSlots"
                                :label="'时间段'+(index+1)"
                                :key="timeslot.day"
                                :rules="{required: true, message: '内容不能为空', trigger: 'blur'}">
<!--                            <el-input v-model="timeslot.day" placeholder="输入0-6，0代表周日"></el-input>-->
                            <el-select v-model="timeslot.day" placeholder="请选择">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                            <el-time-select
                                    v-model="timeslot.start"
                                    :picker-options="{start: '08:00',step: '00:30',end: '20:30'}"
                                    placeholder="选择开始时间">
                            </el-time-select>
                            <el-time-select
                                    v-model="timeslot.end"
                                    :picker-options="{start: '08:00',step: '00:30',end: '20:30'}"
                                    placeholder="选择结束时间">
                            </el-time-select>
                            <el-button @click.prevent="removeTimeslot(timeslot)">删除</el-button>
                        </el-form-item>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="addTimeslot">新增时间段</el-button>
                    </el-form-item>
                    <el-form-item label="上课地点">
                        <el-input type="text" v-model="editForm.location"></el-input>
                    </el-form-item>
                    <el-form-item label="授课教师" v-if="dialogStatus==='update'">
                        <el-input type="text" v-model="editForm.courseTeacher"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer">
                    <el-button @click.native="courseDialogVisible=false">取消</el-button>
                    <el-button type="primary" @click="updateData">修改</el-button>
                </span>
            </el-dialog>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "TeacherCourseDetail",

        data(){
            return{
                loading:true,

                CourseForm:[],

                courseDialogVisible:false,

                editForm: {
                    id:0,
                    courseTitle:"",
                    startDate:"",
                    endDate:"",
                    location:"",
                    courseTeacher:"",
                    courseState:"",
                    state:"",
                    timeSlots:[
                        {
                            day:0,
                            end:"",
                            start:"",
                        },
                    ]
                },

                imageURL: this.$store.getters.getCourseImg,

                options: [{
                    value: '0',
                    label: '周日'
                }, {
                    value: '1',
                    label: '周一'
                }, {
                    value: '2',
                    label: '周二'
                }, {
                    value: '3',
                    label: '周三'
                }, {
                    value: '4',
                    label: '周四'
                },{
                    value: '5',
                    label: '周五'
                }, {
                    value: '6',
                    label: '周六'
                },
                ],
            }
        },

        methods:{
            showCourse(){
                this.CourseForm=this.$store.getters.getCourseInfo;
                this.loading=false;
            },

            beforeAvatarUpload: function(file) {
                console.log(file);
                const isLt2M = file.size / 1024 < 5000;

                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 5000KB!');
                }
                return isLt2M;
            },

            uploadProcess: function(param) {
                let formData = new FormData();
                formData.append("avatar",param.file);
                this.$http.request({
                    url: "/api/courses/" + this.$store.getters.getCourseId+ "/avatar",
                    method: "post",
                    data: formData,
                    headers: {
                        'Authorization': "Bearer " + this.$store.state.user.accessToken,
                        'Content-Type': 'multipart/form-data'
                    },
                }).then((response) => {
                    this.$message.success("上传头像成功");
                    console.log(response);
                }).catch((error) => {
                    this.$message.error("上传头像失败");
                    console.log(error.response);
                })
            },

            // 显示编辑弹窗
            showEditDialog(){
                this.editForm = Object.assign({}, this.CourseForm);
                this.editForm.timeSlots=[
                    {
                        day:0,
                        end:"",
                        start:"",
                    },
                ];
                this.courseDialogVisible=true;
            },

            // 修改课程
            updateData(){
                var that=this;
                //编辑课程信息
                this.$http.request({
                    url: '/api/courses/'+this.editForm.id+'/modify',
                    method: "put",
                    headers: this.$store.getters.authRequestHead,
                    data:{
                        courseTitle:this.editForm.courseTitle,
                        startDate:this.editForm.startDate,
                        endDate:this.editForm.endDate,
                        location:this.editForm.location,
                        timeSlots:this.editForm.timeSlots,
                    }
                })
                    .then(function (response) {
                        console.log(response.data);

                        that.courseDialogVisible=false;
                        // alert("修改课程信息成功");
                        that.$message.success("修改课程信息成功");
                    })
                    .catch(function (error) {
                        console.log(error);
                        // alert("修改课程信息失败");
                        that.$message.error("修改课程信息失败");
                        that.$message.error(error.response.data);
                    });
            },

            // 移除时间段
            removeTimeslot(item) {
                var index = this.editForm.timeSlots.indexOf(item);
                if (index !== -1) {
                    this.editForm.timeSlots.splice(index, 1)
                }
            },

            // 添加时间段
            addTimeslot() {
                this.editForm.timeSlots.push({
                    day:'',
                    start:'',
                    end:'',
                });
            },
        },

        computed: {
            uploadUrl: function () {
                return "/api/users/" + this.$store.getters.getcourseId + "/avatar"
            },
            // imageUrl: function () {
            //     if (this.newUrl === "") {
            //         return this.$store.getters.userAvatarUrl;
            //     }
            //     else {
            //         return "http://202.120.40.8:30382/online-edu/static/" + this.newUrl
            //     }
            // },
            uploadHeader: function () {
                return {
                    'Authorization': "Bearer " + this.$store.state.user.accessToken,
                    'Content-Type': 'multipart/form-data'
                }
            }
        },

        mounted() {
            this.showCourse();
        }
    }
</script>

<style scoped>
    .titlesytle {
        text-align: center;
        padding-top: 20px
    }

    .courseimg {
        float: left;
        width: 25%;
    }

    .coursedes {
        float: right;
        width: 70%;
        font-size: large;
    }

    .detail {
        font-size: small;
    }

    .avatar-uploader {
        border: 1px dashed #d9d9d9;
        border-radius: 100%;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }

</style>
