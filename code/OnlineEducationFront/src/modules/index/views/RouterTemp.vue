<template>
    <router-view></router-view>
</template>

<script>
    export default {
        name: "RouterTemp",
        created() {
            this.$http.request({
                url: this.$store.getters.getCourseUrl + "info",
                method: "get",
                headers: this.$store.getters.authRequestHead
            }).then((response) => {
                console.log(response.data);
                let identity = response.data.identity;
                this.$store.commit("setCourseInfo", response.data.course);
                this.$store.commit("setIdentity", identity);
                console.log(this.$store.getters.getCourseInfo);
                if (this.$route.path === "/course") {
                    if (identity === "VISITOR") {
                        this.$router.push('/course/info');
                    }
                    else if (identity === "STUDENT") {
                        this.$router.push('/course/student');
                    }
                    else {
                        this.$router.push('/course/manager');
                    }
                }
            }).catch((error) => {
                alert(error);
                console.log(error.response);
            })
        }
    }
</script>

<style scoped>

</style>
