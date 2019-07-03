<template>

    <div id="ManageNav">

        <div class="Mitem">
            <router-link to="/ManageUser">用户管理</router-link>
        </div>

        <div class="Mitem">
            <router-link to="/ManageCourse">课程管理</router-link>
        </div>



    </div>

</template>

<script>
    export default {
        name: "ManagNav"
    }
</script>

<style scoped>

    #ManageNav{

        border: 1px solid;
    }

    .Mitem{
        display: flex;
        align-items: center;
    }

</style>
