import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import store from '@/store'
import '@/element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import http from '@/http/httpRequest.js'
import cloneDeep from 'lodash/cloneDeep'
import '@/echarts'
import '@/animejs'
// 全局挂载 http（axios）,使用的时候直接使用 this.$http 即可。
Vue.prototype.$http=http
//Vue.prototype.$bus= new Vue()
Vue.config.productionTip = false

// 保存整站vuex本地储存初始状态
window.SITE_CONFIG={}
window.SITE_CONFIG['storeState'] = cloneDeep(store.state)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
