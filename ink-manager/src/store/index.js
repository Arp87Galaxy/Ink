import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)

const menu = {
  state: () => ({
    clickMenu: [],
    activeMenu: 0,
    activePanelName: 'HomePage',
    listMenuRelativeUrl: ""

  }),
  getters: {
    getListMenuRelativeUrl(state) {
      return state.listMenuRelativeUrl
    },
    getClickMenu(state) {
      return state.clickMenu
    },
    getActiveMenu(state) {
      return state.activeMenu
    },
    getActivePanelName(state) {
      return state.activePanelName
    }
  },
  mutations: {
    addclickMenu(state, menu) {
      state.clickMenu.push(menu)
    },
    setActiveMenu(state, index) {

      state.activeMenu = index
    },
    setActivePanelName(state, activePanelName) {
      state.activePanelName = activePanelName
    },
    setListMenuRelativeUrl(state){
      console.log("setListMenuRelativeUrl..........")
      state.listMenuRelativeUrl = state.clickMenu[state.activeMenu].url
    }
  },
  actions: {
    addclickMenu(context, menu) {
      context.commit('addclickMenu', menu)
    },
    setActiveMenu(context, index) {
      context.commit('setActiveMenu', index)
    },
    setActivePanelName(context, activePanelName) {
      context.commit('setActivePanelName', activePanelName)
    },
    setListMenuRelativeUrl(context){
      context.commit("setListMenuRelativeUrl")
    }
  },

}

const store = new Vuex.Store({
  //模块状态管理
  modules: {
    menu: menu
  },
  //基础状态管理
  state: {
    userStatus: 0,//用户状态
  },
  mutations: {
    setUserStatus(state, status) {
      state.userStatus = status
    },

  },
  actions: {
    setUserStatus(context, status) {
      context.commit("setUserStatus", status);
    },
  }
})
export default store