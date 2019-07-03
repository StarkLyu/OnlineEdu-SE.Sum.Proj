import Vue from 'vue'
import Index from './Index.vue'
import router from './router'



new Vue({
    router,
    render: h => h(Index)
}).$mount('#index');
