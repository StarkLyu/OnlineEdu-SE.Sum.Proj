<template>
    <div id="manage">

        <p>这是manage.vue</p>

        <div style="float: left; margin-right: 20px; width: 10%">
            <ManageNav></ManageNav>
        </div>

        <div style="float: left;width: 80%">
            <router-view></router-view>
        </div>

    </div>
</template>

<script>
    import ManageNav from "./components/ManageNav"
    export default {
        name: "Manage",

        components:{
            ManageNav:ManageNav,
        },
    }
</script>

<style scoped>

</style>
