import Vue from 'vue'
import Manage from './Manage.vue'
import router from './router'
import Element from  'element-ui'

Vue.use(Element);

new Vue({
    router,
    render: h => h(Manage)
}).$mount('#manage');
