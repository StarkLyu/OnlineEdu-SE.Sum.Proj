(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ba042664"],{"14c0":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-header",[a("h1",{staticClass:"titlesytle"},[e._v("课程详情")])]),a("el-main",[a("div",{staticClass:"courseimg"},[a("el-upload",{staticClass:"avatar-uploader avatar",attrs:{action:e.uploadUrl,headers:e.uploadHeader,"show-file-list":!1,"before-upload":e.beforeAvatarUpload,"on-success":e.uploadSucceed,"on-error":e.uploadFail,"http-request":e.uploadProcess}},[e.imageURL?a("img",{staticClass:"avatar",attrs:{src:e.imageURL}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),a("div",{staticClass:"coursedes"},[a("p",[e._v("\n                    课程名称：\n                    "),a("span",{staticClass:"detail"},[e._v("\n                        "+e._s(e.CourseForm.courseTitle)+"\n                    ")])]),a("p",[e._v("\n                    授课教师：\n                    "),a("span",{staticClass:"detail"},[e._v("\n                        "+e._s(e.CourseForm.teacher.username)+"\n                    ")])]),a("p",[e._v("\n                    上课日期：\n                    "),a("span",{staticClass:"detail"},[e._v("\n                        "+e._s(e.CourseForm.startDate)+" 至 "+e._s(e.CourseForm.endDate)+"\n                    ")])]),a("p",[e._v("\n                    上课地点：\n                    "),a("span",{staticClass:"detail"},[e._v("\n                        "+e._s(e.CourseForm.location)+"\n                    ")])]),e._l(e.CourseForm.timeSlots,function(t){return a("div",{key:t.id},[a("p",[e._v("\n                        上课时间：\n                        "),a("span",{staticClass:"detail"},[e._v("\n                            "+e._s(t.day)+"——"+e._s(t.start)+"至"+e._s(t.end)+"\n                        ")])])])}),a("p",[e._v("\n                    课程状态：\n                    "),a("span",{staticClass:"detail"},[e._v("\n                        "+e._s(e.CourseForm.state)+"\n                    ")])]),a("el-button",{on:{click:e.showEditDialog}},[e._v("编辑课程详情")])],2),a("el-dialog",{attrs:{title:"编辑课程信息",visible:e.courseDialogVisible,"lock-scroll":!1,top:"5%"},on:{"update:visible":function(t){e.courseDialogVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"课程名"}},[a("el-input",{attrs:{type:"text"},model:{value:e.editForm.courseTitle,callback:function(t){e.$set(e.editForm,"courseTitle",t)},expression:"editForm.courseTitle"}})],1),a("el-form-item",{attrs:{label:"上课日程"}},[a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{placeholder:"选择开始时间",type:"date","value-format":"yyyy-MM-dd HH:mm:ss"},model:{value:e.editForm.startDate,callback:function(t){e.$set(e.editForm,"startDate",t)},expression:"editForm.startDate"}})],1),a("el-col",{staticClass:"line",attrs:{span:2}},[e._v("-")]),a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{placeholder:"选择结束时间",type:"date","value-format":"yyyy-MM-dd HH:mm:ss"},model:{value:e.editForm.endDate,callback:function(t){e.$set(e.editForm,"endDate",t)},expression:"editForm.endDate"}})],1)],1),a("el-form-item",{attrs:{label:"课时"}},e._l(e.editForm.timeSlots,function(t,s){return a("el-form-item",{key:t.day,attrs:{label:"时间段"+(s+1),rules:{required:!0,message:"内容不能为空",trigger:"blur"}}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.day,callback:function(a){e.$set(t,"day",a)},expression:"timeslot.day"}},e._l(e.options,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1),a("el-time-select",{attrs:{"picker-options":{start:"08:00",step:"00:30",end:"20:30"},placeholder:"选择开始时间"},model:{value:t.start,callback:function(a){e.$set(t,"start",a)},expression:"timeslot.start"}}),a("el-time-select",{attrs:{"picker-options":{start:"08:00",step:"00:30",end:"20:30"},placeholder:"选择结束时间"},model:{value:t.end,callback:function(a){e.$set(t,"end",a)},expression:"timeslot.end"}}),a("el-button",{on:{click:function(a){return a.preventDefault(),e.removeTimeslot(t)}}},[e._v("删除")])],1)}),1),a("el-form-item",[a("el-button",{on:{click:e.addTimeslot}},[e._v("新增时间段")])],1),a("el-form-item",{attrs:{label:"上课地点"}},[a("el-input",{attrs:{type:"text"},model:{value:e.editForm.location,callback:function(t){e.$set(e.editForm,"location",t)},expression:"editForm.location"}})],1),"update"===e.dialogStatus?a("el-form-item",{attrs:{label:"授课教师"}},[a("el-input",{attrs:{type:"text"},model:{value:e.editForm.courseTeacher,callback:function(t){e.$set(e.editForm,"courseTeacher",t)},expression:"editForm.courseTeacher"}})],1):e._e()],1),a("span",{attrs:{slot:"footer"},slot:"footer"},[a("el-button",{nativeOn:{click:function(t){e.courseDialogVisible=!1}}},[e._v("取消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.updateData}},[e._v("修改")])],1)],1)],1)],1)},o=[],l={name:"TeacherCourseDetail",data:function(){return{CourseForm:this.$store.getters.getCourseInfo,courseDialogVisible:!1,editForm:{id:0,courseTitle:"",startDate:"",endDate:"",location:"",courseTeacher:"",courseState:"",state:"",timeSlots:[{day:0,end:"",start:""}]},imageURL:this.$store.getters.getCourseImg,options:[{value:"0",label:"周日"},{value:"1",label:"周一"},{value:"2",label:"周二"},{value:"3",label:"周三"},{value:"4",label:"周四"},{value:"5",label:"周五"},{value:"6",label:"周六"}]}},methods:{beforeAvatarUpload:function(e){console.log(e);var t=e.size/1024<5e3;return t||this.$message.error("上传头像图片大小不能超过 5000KB!"),t},uploadProcess:function(e){var t=new FormData;t.append("avatar",e.file),this.$http.request({url:"/api/courses/"+this.$store.getters.getCourseId+"/avatar",method:"post",data:t,headers:{Authorization:"Bearer "+this.$store.state.user.accessToken,"Content-Type":"multipart/form-data"}}).then(function(e){console.log(e),alert("修改成功")}).catch(function(e){alert("修改失败"),console.log(e.response)})},uploadSucceed:function(e,t,a){alert("上传成功"),console.log(e)},uploadFail:function(e,t,a){alert("上传失败"),console.log(e)},showEditDialog:function(){this.editForm=Object.assign({},this.CourseForm),this.editForm.timeSlots=[{day:0,end:"",start:""}],this.courseDialogVisible=!0},updateData:function(){var e=this;this.$http.request({url:"/api/courses/"+this.editForm.id+"/modify",method:"put",headers:this.$store.getters.authRequestHead,data:{courseTitle:this.editForm.courseTitle,startDate:this.editForm.startDate,endDate:this.editForm.endDate,location:this.editForm.location,timeSlots:this.editForm.timeSlots}}).then(function(t){console.log(t.data),e.dialogFormVisible=!1,alert("修改课程信息成功")}).catch(function(e){console.log(e),alert("请求失败")})},removeTimeslot:function(e){var t=this.editForm.timeSlots.indexOf(e);-1!==t&&this.editForm.timeSlots.splice(t,1)},addTimeslot:function(){this.editForm.timeSlots.push({day:"",start:"",end:""})}}},r=l,i=(a("7472"),a("2877")),n=Object(i["a"])(r,s,o,!1,null,"41e7cfc0",null);t["default"]=n.exports},7472:function(e,t,a){"use strict";var s=a("bb7d"),o=a.n(s);o.a},bb7d:function(e,t,a){}}]);
//# sourceMappingURL=chunk-ba042664.a00b47f5.js.map