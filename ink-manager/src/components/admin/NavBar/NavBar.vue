<template>
  <div class="navbar">
    <el-row style="height:100%">
      <el-col :span="20"> </el-col>

      <el-col :span="4" :offset="20">
        <el-dropdown placement="bottom" size="small">
          <el-button type="info" style="margin-top:10px" icon="el-icon-user-solid" ></el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-button type="info" @click="logout()" plain>注销</el-button>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import {deleteAuthToken} from '@/utils/index.js'
export default {
  name: "NavBar",
  data() {
    return {};
  },
  methods: {
    logout() {
      console.log("logout");
      this.$http({
        url: "/core/sys/logout",
        method: "post",
      }).then((data) => {
        console.log(data.data);
        // deleteAuthToken()
        //刷新页面
        this.$router.go(0)
      });
    },
  },
  created() {},
};
</script>

<style  scoped>
.navbar {
  height: 100%;
  background-color: black;
}
</style>