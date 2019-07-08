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
                        },
                        {
                            path: 'homework',
                            name: 'courseStudentHomework',
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
                            name: 'courseStudentForum'
                        }
                    ]
                },
                {
                    path: 'manager',
                    name: 'courseManager',
                    children: [

                    ]
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
        {
            path: '/teacher',
            name: 'teacher',
            children:[
                {
                    path: '/coursedetail',
                    name: 'CourseDetail',
                    component: () => import('./views/TeacherCourseDetail')
                },
                {
                    path: '/coursemanage',
                    name: 'TeacherCourseManage',
                    component: () => import('./views/TeacherCourseManage')
                },
                {
                    path: '/studentmanage',
                    name: 'TeacherStudenetManage',
                    component: () => import('./views/TeacherStudentManage')
                },
                {
                    path: '/scoremanage',
                    name: 'TeacherScoreManage',
                    component: () => import('./views/TeacherScoreManage')
                },
            ]
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
