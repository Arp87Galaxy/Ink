import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/Login/Login'

import Content from '@/components/Content/Content'
import Main from '@/components/Main'
import http from '@/http/httpRequest.js'
import store from '@/store'
Vue.use(VueRouter)

const routes = [
  
  {
    path: '/',
    component:Content
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
  },
  {
    path: '/admin',
    name: 'admin',
    component: Main,
    redirect: { path: '/admin/home' },
    children: [
      {
        path: '/admin/home',
        name: 'admin-home',
        component:Content,
        children:[
        ]
      },
    ]
  }
]
const router = new VueRouter({
  mode:'history',
  routes // (缩写) 相当于 routes: routes
})
//全局前置守卫
/**
 * token验证是否登录，获取菜单列表
 */
router.beforeEach((to, from, next) => {
  // ...
  console.log("全局前置守卫：token验证是否登录，获取菜单列表")
  console.log(to)
  console.log(from)
  http({
    url: "/core/sys/isLogined",
    method: "post",
  })
    .then((data) => {
      console.log(data.data);
        // throw new Error([data.data.message]);
        console.log(data.data.data == true)
        console.log(to.name!='login')
      if (data.data.data == true){
        store.commit("setUserStatus", 1);
        //todo 获取菜单列表
        http({
          url:'core/sys/menu/init',
          method:'post'
        }).then((data) => {
          console.log("菜单列表")
          console.log(data.data)
          window.sessionStorage.setItem("menuList",JSON.stringify(data.data.data.menuList || []))
          window.sessionStorage.setItem("perms",JSON.stringify(data.data.data.perms || []))
          if(to.name=='login'){
            next({name: "admin" })
          }else{
            next()
          }
        }).catch({

        })
        
      }else{
        console.log("1")
        store.commit("setUserStatus", 0);
        if(to.name!='login'){
          console.log("2")
          next({name: "login" })
        }else{
          console.log("3")
          next()
        } 
      }
    })
    .catch((e) => {
      alert(e);
      console.log(e);
      // router.go(0);
    });
})
// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
export default router