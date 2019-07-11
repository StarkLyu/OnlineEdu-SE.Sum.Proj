import Vue from 'vue'
import Manage from './Manage.vue'
import router from './router'
import store from '../index/store/store.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'

axios.defaults.withCredentials = true;
axios.defaults.baseURL = "/online-edu";
Vue.use(ElementUI);
Vue.prototype.$axios=axios;

new Vue({
    router,
    store,
    render: h => h(Manage)
}).$mount('#manage');
