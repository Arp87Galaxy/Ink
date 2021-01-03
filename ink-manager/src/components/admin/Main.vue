<!--  -->
<template>
  <el-container class="main">
  <el-header class="header"><nav-bar></nav-bar></el-header>
  <el-container class="container">
    <el-aside width="200px"><side-bar></side-bar></el-aside>
    <el-main class="el-main"><router-view ></router-view></el-main>
  </el-container>
</el-container>
</template>

<script>
import NavBar from './NavBar/NavBar.vue';
import SideBar from './SideBar/SideBar.vue';
export default {
  name: "Main",
  data() {
    return {};
  },

  components: {NavBar, SideBar},

  // computed: {},

  // mounted: {},

  // methods: {},
};
</script>
<style scoped>
.el-main{
  padding: 0 0 0 0;
  margin: 0 0 0 0;
  border: 0px;
    overflow-x: hidden;
    overflow-y: hidden;
}
.header{
  background-color: blue;
}
.header, .container {
  margin: 0 0 0 0 ;
  padding: 0 0 0 0;
}

</style>