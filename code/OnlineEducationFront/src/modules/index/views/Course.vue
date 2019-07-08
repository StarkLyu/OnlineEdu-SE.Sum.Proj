<template>
    <el-container>
        <el-aside>
            <CourseHead></CourseHead>
            <UserCourseMenu></UserCourseMenu>
        </el-aside>
        <eld-main>
            <router-view></router-view>
        </eld-main>
    </el-container>
</template>

<script>
    import CourseHead from "../components/CourseHead";
    import UserCourseMenu from "../components/UserCourseMenu";
    export default {
        name: "Course",
        components: {UserCourseMenu, CourseHead}
    }
</script>

<style scoped>

</style>