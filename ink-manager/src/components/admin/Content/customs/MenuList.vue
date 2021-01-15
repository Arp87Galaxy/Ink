<!--  -->
<template>
  <div>
    <List :url="url"></List>
  </div>
</template>

<script>
export default {
  components: {},
  name: "MenuList",
  data() {
    return {
      url:'core/sys/menu/list'
      // page:{}
    };
  },
  beforeMount() {
    // this.$http({
    //   url: "core/sys/menu/list",
    //   method: "post",
    //   params: {
    //     page: this.currentPage,
    //     limit: this.currentLimit,
    //   },
    // }).then((data) => {
    //   console.log("AdminList data:");
    //   console.log(data.data);
    //   this.listData= data.data;

    // });
  },
};
</script>
<style  scoped>
</style>