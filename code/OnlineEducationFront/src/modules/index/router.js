import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import User from './views/User.vue'
import UserCourseList from './components/UserCourseList.vue'
import UserSetting from "./components/UserSetting";

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/home',
            name: 'home',
            component: Home
        },
        {
            path: '/viewcourses',
            name: 'viewCourses',
            component: () => import('./views/CourseHall.vue')
        },
        {
            path: '/user',
            name: 'user',
            component: User,
            children: [
                {
                    path: 'course',
                    name: 'userCourse',
                    component: UserCourseList
                },
                {
                    path: 'info',
                    name: 'userInfo',
                    component: UserSetting
                },
            ]
        },
        {
            path: '/course',
            name: 'course',
            component: () => import('./views/RouterTemp.vue'),
            children: [
                {
                    path: 'student',
                    name: 'courseStudent',
                    component: () => import('./views/Course.vue'),
                    children: [
                        {
                            path: 'welcome',
                            name: 'courseStudentWelcome',
                        },
                        {
                            path: 'info',
                            name: 'courseStudentInfo',
                            component: () => import('./components/UserCourseInfo.vue')
                        },
                        {
                            path: 'chapters',
                            name: 'courseStudentChapters',
                            component: () => import('./components/CourseChapters.vue')
                        },
                        {
                            path: 'homework',
                            name: 'courseStudentHomework',
                            component: () => import('./components/UserCoursePaperList.vue')
                        },
                        {
                            path: 'paper/:paperId',
                            name: 'courseStudentPaper',
                            props: true,
                            component: () => import('./components/UserCoursePaper')
                        },
                        {
                            path: 'resources',
                            name: 'courseStudentResources',
                        },
                        {
                            path: 'grade',
                            name: 'courseStudentGrade',
                        },
                        {
                            path: 'report',
                            name: 'courseStudentReport',
                        },
                        {
                            path: 'forum',
                            name: 'courseStudentForum',
                            component: () => import('./components/UserCourseForum')
                        }
                    ]
                },
                {
                    path: 'manager',
                    name: 'courseManager',
                    component: () => import('./components/TeacherCourseNav'),
                    children: [
                        {
                            path: 'detail',
                            name: 'TeacherCourseDetail',
                            component: () => import('./views/TeacherCourseDetail')
                        },
                        {
                            path: 'chapters',
                            name: 'TeacherCourseChapters',
                            component: () => import('./views/TeacherCourseChapters')
                        },
                        {
                            path: 'student',
                            name: 'TeacherStudenetManage',
                            component: () => import('./views/TeacherStudentManage')
                        },
                        {
                            path: 'score',
                            name: 'TeacherScoreManage',
                            component: () => import('./views/TeacherScoreManage')
                        },
                        {
                            path: 'announcement',
                            name: 'TeacherCourseAnnounce',
                            component: () => import('./views/TeacherCourseAnnounce')
                        },
                        {
                            path: 'assignment',
                            name: 'TeacherCourseAssign',
                            component: () => import('./views/TeacherCourseAssign'),
                        },
                        {
                            path:'questionBank',
                            name:'TeacherCourseQuestionBank',
                            component:() => import('./views/TeacherCourseQuestionBank')
                        },
                        {
                            path: 'bt',
                            name: 'TeacherCourseBT',
                            component: () => import('./views/TeacherCourseBT')
                        },
                    ]
                },
                {
                    path: 'info',
                    name: 'CourseInfo',
                    component: () => import('./views/CourseEntry.vue')
                }
            ]
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('./views/Login.vue')
        },
        {
            path: '/register',
            name: 'register',
            component: () => import('./views/Register.vue')
        },
        // {
        //     path: '/teachercoursedetail',
        //     name: 'TeacherCourseDetail',
        //     component: () => import('./views/TeacherCourseDetail')
        // },
        // {
        //     path: '/teachercoursemanage',
        //     name: 'TeacherCourseManage',
        //     component: () => import('./views/TeacherCourseManage')
        // },
        // {
        //     path: '/teacherstudentmanage',
        //     name: 'TeacherStudenetManage',
        //     component: () => import('./views/TeacherStudentManage')
        // },
        // {
        //     path: '/teacherscoremanage',
        //     name: 'TeacherScoreManage',
        //     component: () => import('./views/TeacherScoreManage')
        // }
    ]
})
