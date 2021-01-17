<!--  -->
<template>
  <div>
    <List :url="url"></List>
  </div>
</template>

<script>
export default {
  components: {},
  name: "AdminList",
  data() {
    return {
      url:''
      // page:{}
    };
  },
  created() {
    this.url = this.$store.getters.getListMenuRelativeUrl
  },
  
};
</script>
<style  scoped>
</style>