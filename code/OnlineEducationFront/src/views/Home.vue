<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <div>
      <router-link to="/ManageUser">教务员用户信息管理</router-link>
    </div>

    <div>
      <a href="/public/manage.html">前往manage.html</a>
    </div>

    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'home',
  components: {
  }
}
</script>
