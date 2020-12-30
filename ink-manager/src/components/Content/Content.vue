<!--  -->
<template>
  <div>
    <el-menu
      class="click-menu-board"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      v-if="getClickMenu.length != 0"
      :default-active="getActiveMenu"
    >
      <el-menu-item
        class="click-menu"
        :index="index + ''"
        v-for="(menu, index) in getClickMenu"
        :key="index"
      >
        <el-button @click="setActiveMenu(menu)">
          {{ menu.name }}
          <i class="el-icon-close" @click="removeActiveMenu(menu)"></i>
        </el-button>
      </el-menu-item>
    </el-menu>
    <div class="display-panel">
      <iframe
            src="http://localhost:9534/ink/druid/sql.html"
            width="100%" height="500px" frameborder="0" scrolling="yes">
      </iframe>
    </div>
  </div>
</template>

<script>

export default {
  name: "Content",
  data() {
    return {
      html: [],
    };
  },

  components: {},

  computed: {
    getClickMenu() {
      return this.$store.state.clickMenu;
    },
    getActiveMenu() {
      console.log(this.$store.state.activeMenu);
      return this.$store.state.activeMenu + "";
    },
  },

  // mounted(){},

  methods: {
    removeActiveMenu(menu) {
      this.getClickMenu.splice(this.getClickMenu.indexOf(menu), 1);
    },
    setActiveMenu(menu) {
      this.$store.commit("setActiveMenu", this.getClickMenu.indexOf(menu));
      console.log(this.getClickMenu.indexOf(menu));
    },
    activeMenu(menu) {
      console.log("active-menu");
      let activeIndex = this.$store.state.clickMenu.indexOf(menu);
      console.log(activeIndex);
      this.activeMenu = activeIndex;
    },
  },
  created() {
    this.$http({
      url: "/druid/sql.html",
      method: "get",
    }).then((data) => {
      console.log("druid");
      console.log(data.data);
      this.html.push(data.data);
    });
  },
};
</script>
<style  scoped>
.el-menu-item [class^="el-icon-"] {
  padding: 0 0 0 0;
  margin: 0 0 0 0;
  margin-right: 0px;
  font-size: 10px;
  line-height: 5px;
  height: 100%;
}
.click-menu-board >>> .el-menu-item {
  padding: 0 0 0 0;
  margin: 0 0 0 0;
}
.click-menu-board >>> .el-button {
  background-color: #545c64;
  font-size: 10px;
  color: white;
  border: 0px;
  border-radius: 0px;
  padding: 0 0 0 0;
  margin: 0 0 0 0;
  height: 60%;
  line-height: 5px;
}
.click-menu-board >>> .el-menu-item {
  height: 100%;
  line-height: 20px;
}
.click-menu-board {
  width: 100%;
  height: 20px;
}
</style>