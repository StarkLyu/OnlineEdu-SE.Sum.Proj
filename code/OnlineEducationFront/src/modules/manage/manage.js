import Vue from 'vue'
import Manage from './Manage.vue'
import router from './router'
import store from '../index/store/store.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

new Vue({
    router,
    store,
    render: h => h(Manage)
}).$mount('#manage');
