import Vue from 'vue'
import Index from './Index.vue'
import router from './router'
import store from './store/store.js'
import ElementUI from 'element-ui'
import axios from 'axios'
import VueAxios from 'vue-cli-plugin-axios'
import 'element-ui/lib/theme-chalk/index.css';
import '../../assets/div-layout.css'
import '../../assets/icon/iconfont.css'

axios.defaults.withCredentials = true;
//axios.defaults.baseURL = "http://localhost:8081/online-edu"
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(VueAxios, axios);
Vue.prototype.$http = axios;

new Vue({
    router,
    store,
    render: h => h(Index)
}).$mount('#index');
