<!--  -->
<template>
  <div class="sidebar">
    <el-menu
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <div v-for="menu in menuList" :key="menu.menuId">
        <el-submenu :index="menu.menuId + ''" v-if="menu.children.length != 0">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">{{ menu.name }}</span>
          </template>
          <el-menu-item
            :index="children.menuId + ''"
            v-for="children in menu.children"
            :key="children.menuId"
            @click="submenuClick(children)"
          >
            <!-- <i class="el-icon-s-order"></i> -->
            <span slot="title">{{ children.name }}</span>
          </el-menu-item>
        </el-submenu>
        <el-menu-item
          :index="menu.menuId + ''"
          v-if="menu.children.length == 0"
          @click="submenuClick(menu)"
        >
          <i class="el-icon-menu"></i>
          <span slot="title">{{ menu.name }}</span>
        </el-menu-item>
      </div>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "SideBar",
  data() {
    return {
      menuList: [],
    };
  },

  components: {},

  computed: {},

  //   mounted: {},

  methods: {
    submenuClick(menu) {
      if (!this.$store.getters.getClickMenu.includes(menu))
        this.$store.dispatch("addclickMenu", menu);
      this.$store.dispatch(
        "setActiveMenu",
        this.$store.getters.getClickMenu.indexOf(menu)
      );
      this.$store.dispatch("setListMenuRelativeUrl");

      if (menu.type == 1) {
        this.$store.dispatch("setActivePanelName", menu.menuPanel);
      } else {
        console.log("不是1类型菜单");
      }

      console.log(this.$store.getters.getClickMenu.indexOf(menu));
    },
  },
  mounted() {
    this.menuList = JSON.parse(window.sessionStorage.getItem("menuList"));
    console.log("Created data is :");
    console.log(this.$data);
    console.log(this.menuList[0].icon);
  },
};
</script>
<style  scoped>
.sidebar {
  height: 100%;
  background-color: #545c64;
}
.sidebar >>> .el-menu {
  border-right: 0px;
}
</style>