import Vue from 'vue'
import App from './App.vue'
import router from './router'
import '../theme/index.css'
import '@/assets/css/common.css'
import '@/assets/css/iconfont.css'
import '@/nprogress' // permission control
import 'babel-polyfill'
import BaiduMap from 'vue-baidu-map'
import 'swiper/dist/css/swiper.min.css'
import 'swiper/dist/js/swiper.min'

Vue.config.productionTip = false
Vue.use(BaiduMap, {
  ak: 'ZlUsVcau7jGNOG6kn2Dz2CezmBqQR9q7'
})
require('promise.prototype.finally').shim();
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
