import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/Login/Login'
import NavBar from '@/components/NavBar/NavBar'
Vue.use(VueRouter)

const routes = [
    {path:'',redirect: { name: 'login' }},
    {   path: '/login',
        name:'login' ,
        components: {
            login:Login
        }
    },
    {   path: '/home',
        name:'home' ,
        components: {
            navbar:NavBar
            // sidebar:,
            // main:
        } 
    }
  ]
  
  // 3. 创建 router 实例，然后传 `routes` 配置
  // 你还可以传别的配置参数, 不过先这么简单着吧。
  export default new VueRouter({
    routes // (缩写) 相当于 routes: routes
  })