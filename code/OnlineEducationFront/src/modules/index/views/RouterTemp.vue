<template>
    <router-view></router-view>
</template>

<script>
    export default {
        name: "RouterTemp",
        mounted() {
            this.$http.request({
                url: this.$store.getters.getCourseUrl + "info",
                method: "get",
                headers: this.$store.getters.authRequestHead
            }).then((response) => {
                console.log(response.data);
                this.$store.commit("setCourseInfo", response.data);
                console.log(this.$store.getters.getCourseInfo);
            }).catch((error) => {
                alert(error);
                console.log(error.response);
            })
        }
    }
</script>

<style scoped>

</style>
