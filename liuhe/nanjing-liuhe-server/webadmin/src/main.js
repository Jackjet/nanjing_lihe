import Vue from 'vue'
import App from './App.vue'
import router from './router'
import '../theme/index.css'
import '@/nprogress' // permission control
import 'babel-polyfill'
import echarts from 'echarts'
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
require('promise.prototype.finally').shim();
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
