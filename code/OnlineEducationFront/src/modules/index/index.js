import Vue from 'vue'
import Index from './Index.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import '../../assets/div-layout.css'

Vue.use(ElementUI)

new Vue({
    router,
    render: h => h(Index)
}).$mount('#index');
