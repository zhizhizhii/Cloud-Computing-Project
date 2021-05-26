// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import * as echarts from 'echarts';

Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios

Vue.use(ElementUI);

Vue.config.productionTip = false;


Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})

// 引用axios，并设置基础URL为后端服务api地址
//var axios = require('axios');

axios.defaults.baseURL = "http://127.0.0.1:8081/"
//设置全局，每次ajax请求携带cookies
// axios.defaults.withCredentials = true
// 将API方法绑定到全局

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
