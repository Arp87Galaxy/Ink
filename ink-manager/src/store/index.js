import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    userStatus: 0,
    clickMenu:[],
    activeMenu:'0'
  },
  mutations: {
    setUserStatus (state,status) {
      console.log("store:userStatus")
      state.userStatus=status
    },
    addclickMenu (state,menu) {
      console.log("store:addclickMenu")
      state.clickMenu.push(menu)
    },
    setActiveMenu (state,index) {
      console.log("store:activeMenu")
      state.activeMenu=index
    }
  }
})
export default store