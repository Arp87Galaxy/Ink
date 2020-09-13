import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Hello from '@/components/Hello'
Vue.use(VueRouter)

const routes = [
    { path: '/',redirect:'/home' },
    { path: '/home', name:'home',components:{
        helloworld:HelloWorld,
        hello:Hello
      }
    }
  ]

const router = new VueRouter({
    mode: 'history',
    routes
})
export default router