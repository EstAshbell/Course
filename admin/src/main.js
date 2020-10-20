import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'


Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;

/*
 * axios 拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log("请求：",config);
    return config;
},error => {});
axios.interceptors.response.use(function (response) {
    console.log("响应：",response);
    return response;
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
