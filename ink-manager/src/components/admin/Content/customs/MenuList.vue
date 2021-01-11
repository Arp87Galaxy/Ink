<!--  -->
<template>
  <div>
    <List :listData="listData"></List>
  </div>
</template>

<script>
export default {
  components: {},
  name: "MenuList",
  data() {
    return {
      currentPage: 1,
      currentLimit: 10,
      //props参数
      listData: {
        objects: [],
        pageSize: 0,
        totalCount: 0,
        totalPage: 0,
        columnNames: []
          // menuId: "menuId",
          // parentId: "parentId",
          // name: "name",
          // url: "url",
          // perms:"perms",
          // type: "type",
          // icon: "icon",
          // orderNum:"orderNum",
          // menuPanel:"menuPanel"
        
      },
      // page:{}
    };
  },
  beforeMount() {
    this.$http({
      url: "core/sys/menu/list",
      method: "post",
      params: {
        page: this.currentPage,
        limit: this.currentLimit,
      },
    }).then((data) => {
      // console.log("AdminList data:");
      // console.log(data);
      console.log(data.data);
      console.log(data.data.page);
      this.listData.objects = data.data.page.list;
      this.listData.pageSize = data.data.page.pageSize;
      this.listData.totalCount = data.data.page.totalCount;
      this.listData.totalPage = data.data.page.totalPage;
    this.listData.columnNames = data.data.columnNames


  // console.log(this.listData.columnNames)
      // this.page=data.data.page
      console.log( data.data.columnNames)
      
      // console.log(this.page)
    });
  },
};
</script>
<style  scoped>
</style>