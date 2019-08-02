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
import BaiduMap from 'vue-baidu-map';

Vue.use(BaiduMap, {
    // ak 是在百度地图开发者平台申请的密钥
    ak: 'tZ8u8pMf9nhjWFRACvTHN3gDOZCXcGxx'
}),

axios.defaults.withCredentials = true;
axios.defaults.baseURL = "/online-edu";
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(VueAxios, axios);
Vue.prototype.$http = axios;

new Vue({
    router,
    store,
    render: h => h(Index)
}).$mount('#index');
