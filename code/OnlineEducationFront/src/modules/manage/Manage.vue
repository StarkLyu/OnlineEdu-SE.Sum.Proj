<template>
    <div id="manage">
        <div class="nav">
            <ManageNav></ManageNav>
        </div>
        <div style="float: left; width: 80%">
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
    .nav {
        float: left;
        width: 15%;
    }

    .showbody {
        float: left;
        width: 85%;
    }
</style>
