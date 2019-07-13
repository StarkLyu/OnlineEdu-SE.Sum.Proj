<template>
    <div>
        <CourseHallCard
                v-for="course in courseList"
                :key="course.courseId"
                :course-info="course"
        ></CourseHallCard>
    </div>
</template>

<script>
    import CourseHallCard from "../components/CourseHallCard";
    export default {
        name: "CourseHall",
        components: {CourseHallCard},
        data() {
            return {
                courseList: [
                    {
                        id: 1,
                        title: "数据库没成绩导论",
                        startDate: "2019-07-02",
                        endDate: "2019-08-02",
                        teacher: "JBoss",
                        imgUrl: "http://202.120.40.8:30382/online-edu/static/210-avatar/210-avatar.jpg",
                    },
                ]
            }
        }
    }
</script>

<style scoped>

</style>