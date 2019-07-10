import Vue from 'vue'
import Manage from './Manage.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'

Vue.use(ElementUI);
Vue.prototype.$axios=axios;

new Vue({
    router,
    render: h => h(Manage)
}).$mount('#manage');
