(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2c4420e5"],{"03c3":function(e,t,s){},"0c73":function(e,t,s){},3251:function(e,t,s){"use strict";var o=s("03c3"),r=s.n(o);r.a},9729:function(e,t,s){"use strict";var o=s("9f1d"),r=s.n(o);r.a},"9bd3":function(e,t,s){"use strict";s.r(t);var o=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[e.isCourseTeacher?s("AddNewChapter",{attrs:{slot:"title","last-chapter":"0"},slot:"title"}):e._e(),s("el-collapse",e._l(e.chapters,function(t,o){return s("el-collapse-item",{key:o},[s("h2",{attrs:{slot:"title"},slot:"title"},[e._v(e._s(t.title)+"    ")]),e.isCourseTeacher?s("AddNewChapter",{attrs:{slot:"title","last-chapter":t.secNo},slot:"title"}):e._e(),s("div",{staticClass:"section-content"},[s("el-collapse",[e.isCourseTeacher?s("AddNewSection",{attrs:{chapterId:t.sectionPrimaryKey.secId,"last-section":"0"}}):e._e(),e._l(t.sectionBranchesList,function(e,o){return s("CourseSectionUnit",{key:o,attrs:{"section-info":e,chapterId:t.sectionPrimaryKey.secId}})})],2)],1)],1)}),1)],1)},r=[],i=s("cebc"),n=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("el-collapse-item",[s("h3",{attrs:{slot:"title"},slot:"title"},[e._v(e._s(e.sectionInfo.title)+"    ")]),e.isCourseTeacher?s("AddNewSection",{attrs:{slot:"title","last-section":e.sectionInfo.branchNo,chapterId:e.chapterId},slot:"title"}):e._e(),s("el-divider",[e._v("章节介绍")]),s("pre",[e._v(e._s(e.sectionInfo.description))]),s("el-divider",[e._v("章节资源")]),s("div",[e.isCourseTeacher?s("el-button",{staticStyle:{float:"right"},on:{click:e.showResourceDialog}},[e._v("添加资源")]):e._e(),e._l(e.sectionInfo.resources,function(e,t){return s("ResourceUnit",{key:t,attrs:{"resource-info":e}})}),s("div",{staticClass:"float-clear"})],2),s("el-divider",[e._v("章节作业")]),s("div",[e.isCourseTeacher?s("el-button",{staticStyle:{float:"right"},on:{click:e.showAssignDialog}},[e._v("添加作业")]):e._e(),e._l(e.sectionInfo.papers,function(e,t){return s("PaperUnit",{key:t,attrs:{"paper-info":e}})}),s("div",{staticClass:"float-clear"})],2),s("el-divider")],1),s("el-dialog",{attrs:{title:"选择资源",visible:e.resDialogVisible,"lock-scroll":!1,top:"5%"},on:{"update:visible":function(t){e.resDialogVisible=t}}},[s("el-table",{attrs:{data:e.resTable,height:"300px"}},[s("el-table-column",{attrs:{type:"index",width:"80%"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-radio",{attrs:{label:t.row.id},model:{value:e.resChoose,callback:function(t){e.resChoose=t},expression:"resChoose"}},[e._v(" ")])]}}])}),s("el-table-column",{attrs:{property:"title",label:"资源名称",sortable:""}}),s("el-table-column",{attrs:{property:"resourceType",label:"资源类型"}})],1),s("span",{attrs:{slot:"footer"},slot:"footer"},[s("el-button",{nativeOn:{click:function(t){e.resDialogVisible=!1}}},[e._v("取消")]),s("el-button",{attrs:{type:"primary"},on:{click:e.chooseResource}},[e._v("选择")])],1)],1),s("el-dialog",{attrs:{title:"选择作业",visible:e.assignDialogVisible,"lock-scroll":!1,top:"5%"},on:{"update:visible":function(t){e.assignDialogVisible=t}}},[s("el-table",{attrs:{data:e.assignTable,height:"300px"}},[s("el-table-column",{attrs:{type:"index",width:"80%"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-radio",{attrs:{label:t.row.id},model:{value:e.assignChoose,callback:function(t){e.assignChoose=t},expression:"assignChoose"}},[e._v(" ")])]}}])}),s("el-table-column",{attrs:{property:"title",label:"作业名称",sortable:""}})],1),s("span",{attrs:{slot:"footer"},slot:"footer"},[s("el-button",{nativeOn:{click:function(t){e.assignDialogVisible=!1}}},[e._v("取消")]),s("el-button",{attrs:{type:"primary"},on:{click:e.chooseAssign}},[e._v("选择")])],1)],1)],1)},a=[],l=(s("c5f6"),function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"resource-unit float-left"},[s("a",{attrs:{href:e.getFullUrl(e.resourceInfo.url),download:e.resourceInfo.url},on:{click:e.getResource}},[s("i",{class:e.symbolIcon}),e._v("\n        "+e._s(e.resourceInfo.title)+"\n    ")])])}),c=[],u={name:"ResourceUnit",props:{resourceInfo:{resourceType:String,url:String,title:String}},methods:{getResource:function(){"VIDEO"===this.resourceInfo.resourceType&&this.$emit("play-video",this.resourceInfo.url)},getFullUrl:function(e){return"http://202.120.40.8:30382/online-edu/static/"+e}},computed:{symbolIcon:function(){return"iconfont el-icon-my-"+this.resourceInfo.resourceType.toLowerCase()}}},d=u,p=(s("9729"),s("2877")),h=Object(p["a"])(d,l,c,!1,null,"ac9abdc8",null),f=h.exports,b=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"paper-unit float-left"},[s("el-badge",{attrs:{type:e.badgeType,value:e.badgeText}},[s("el-link",{on:{click:e.loadPaperPage}},[s("i",{staticClass:"iconfont el-icon-my-homework"}),e._v("\n            "+e._s(e.paperInfo.title)+"\n        ")])],1)],1)},g=[],m={name:"PaperUnit",props:{paperInfo:{id:0,state:"",title:""}},methods:{loadPaperPage:function(){this.$router.push({name:"courseStudentPaper",params:{paperId:this.paperInfo.id}})}},computed:{badgeType:function(){switch(this.paperInfo.state){case"NOT START":return"danger";case"NOT FINISH":return"warning";case"FINISHED":return"primary";case"NOT MARKED":return"info";case"MARKED":return"success";default:return"danger"}},badgeText:function(){switch(this.paperInfo.state){case"NOT START":return"未开始";case"NOT FINISH":return"未完成";case"FINISHED":return"已完成";case"NOT MARKED":return"未评分";case"MARKED":return"已评分";default:return"什么鬼"}}}},v=m,w=(s("3251"),Object(p["a"])(v,b,g,!1,null,"bb7abb72",null)),I=w.exports,C=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("el-tooltip",{attrs:{effect:"dark",content:"添加小节",placement:"top-start"}},[e.managerMode?s("el-button",{attrs:{circle:"",icon:"el-icon-plus",size:"small"},on:{click:function(t){e.showAddSection=!0}}}):e._e()],1),s("el-dialog",{attrs:{visible:e.showAddSection},on:{"update:visible":function(t){e.showAddSection=t}}},[s("h2",{attrs:{slot:"title"},slot:"title"},[e._v("添加节")]),s("el-input",{attrs:{placeholder:"新节标题"},model:{value:e.newTitle,callback:function(t){e.newTitle=t},expression:"newTitle"}}),s("el-input",{attrs:{placeholder:"新节描述"},model:{value:e.newDescription,callback:function(t){e.newDescription=t},expression:"newDescription"}}),s("div",{attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:e.addSection}},[e._v("确定")])],1)],1)],1)},_=[],T={name:"AddNewSection",props:{chapterId:Number,lastSection:Number},data:function(){return{showAddSection:!1,newTitle:"",newDescription:"",content:"添加第"+this.lastSection+"小节"}},methods:{addSection:function(){this.$http.request({url:"/api/courses/"+this.$store.getters.getCourseId+"/sections/"+this.chapterId+"/append",method:"post",headers:this.$store.getters.authRequestHead,params:{branchNo:this.lastSection},data:{title:this.newTitle,description:this.newDescription}}).then(function(e){console.log(e.data),alert("请求成功")}).catch(function(e){console.log(e.response),alert("请求失败")}),this.newTitle="",this.newDescription="",this.showAddSection=!1}},computed:{managerMode:function(){return!0}}},y=T,k=Object(p["a"])(y,C,_,!1,null,"41da8b1c",null),A=k.exports,S=s("2f62"),D={name:"CourseSectionUnit",components:{AddNewSection:A,PaperUnit:I,ResourceUnit:f},props:{sectionInfo:{branchNo:"",title:"",description:"",resources:[],papers:[],sectionBranchesPrimaryKey:{branchId:""}},chapterId:Number},data:function(){return{resDialogVisible:!1,assignDialogVisible:!1,resTable:this.$store.getters.getCourseInfo.coursePrototype.resources,assignTable:this.$store.getters.getCourseInfo.papers,resChoose:"",assignChoose:""}},methods:{showResourceDialog:function(){this.resTable=this.$store.getters.getCourseInfo.coursePrototype.resources,this.resDialogVisible=!0},showAssignDialog:function(){this.assignTable=this.$store.getters.getCourseInfo.papers,this.assignDialogVisible=!0},chooseResource:function(){this.$http.request({url:"/api/courses/"+this.$store.getters.getCourseId+"/sections/"+this.chapterId+"/"+this.sectionInfo.sectionBranchesPrimaryKey.branchId+"/resources/issue",method:"post",headers:this.$store.getters.authRequestHead,params:{resourceId:this.resChoose}}).then(function(e){console.log(e.data),alert("请求成功")}).catch(function(e){console.log(e),alert(e)}),this.resDialogVisible=!1},chooseAssign:function(){this.$http.request({url:"/api/courses/"+this.$store.getters.getCourseId+"/sections/"+this.chapterId+"/"+this.sectionInfo.sectionBranchesPrimaryKey.branchId+"/papers/issue",method:"post",headers:this.$store.getters.authRequestHead,params:{paperId:this.assignChoose}}).then(function(e){console.log(e.data),alert("请求成功")}).catch(function(e){console.log(e),alert(e)}),this.assignDialogVisible=!1}},computed:Object(i["a"])({},Object(S["b"])(["isCourseTeacher"]))},N=D,$=Object(p["a"])(N,n,a,!1,null,"27112611",null),O=$.exports,x=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("el-tooltip",{attrs:{effect:"dark",content:"添加章",placement:"top-start"}},[e.managerMode?s("el-button",{attrs:{circle:"",icon:"el-icon-plus",size:"small"},on:{click:function(t){e.showAddChapter=!0}}}):e._e()],1),s("el-dialog",{attrs:{visible:e.showAddChapter},on:{"update:visible":function(t){e.showAddChapter=t}}},[s("h2",{attrs:{slot:"title"},slot:"title"},[e._v("添加章")]),s("el-input",{attrs:{placeholder:"新章标题"},model:{value:e.newTitle,callback:function(t){e.newTitle=t},expression:"newTitle"}}),s("div",{attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:e.addChapter}},[e._v("确定")])],1)],1)],1)},R=[],P={name:"AddNewChapter",props:{lastChapter:Number},data:function(){return{showAddChapter:!1,newTitle:""}},methods:{addChapter:function(){this.$http.request({url:"/api/courses/"+this.$store.getters.getCourseId+"/sections/append",method:"post",headers:{Authorization:"Bearer "+this.$store.state.user.accessToken,"Content-Type":"text/plain"},params:{secNo:this.lastChapter},data:this.newTitle}).then(function(e){console.log(e.data),alert("请求成功")}).catch(function(e){console.log(e.response),alert("请求失败")}),this.newTitle="",this.showAddChapter=!1}},computed:{managerMode:function(){return!0}}},E=P,V=Object(p["a"])(E,x,R,!1,null,"4fe99e04",null),U=V.exports,j={name:"CourseChapters",components:{AddNewChapter:U,CourseSectionUnit:O,AddNewSection:A},data:function(){return{chapters:this.$store.getters.getCourseInfo.sectionList,showAddChapter:!1,addAfterChapter:{id:0,title:""}}},computed:Object(i["a"])({},Object(S["b"])(["isCourseTeacher"]))},K=j,M=(s("dd64"),Object(p["a"])(K,o,r,!1,null,"4cef7092",null));t["default"]=M.exports},"9f1d":function(e,t,s){},dd64:function(e,t,s){"use strict";var o=s("0c73"),r=s.n(o);r.a}}]);
//# sourceMappingURL=chunk-2c4420e5.734ef5c8.js.map