<!--  -->
<template>
  <el-container class="main">
  <el-header class="header"><router-view name="navbar"></router-view></el-header>
  <el-container class="container">
    <el-aside width="200px"><router-view name="sidebar"></router-view></el-aside>
    <el-main class="el-main"><router-view name="content"></router-view></el-main>
  </el-container>
</el-container>
</template>

<script>
export default {
  name: "Main",
  data() {
    return {};
  },

  components: {},

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
}
.header{
  background-color: blue;
}
.header, .container {
  margin: 0 0 0 0 ;
  padding: 0 0 0 0;
}

</style>