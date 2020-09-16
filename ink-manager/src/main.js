import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import store from '@/store'
import '@/element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import http from '@/http/httpRequest.js'

// 全局挂载 http（axios）,使用的时候直接使用 this.$http 即可。
Vue.prototype.$http=http
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
