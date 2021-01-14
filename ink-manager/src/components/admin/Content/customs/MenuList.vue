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
      //props参数
      listData: {
        currPage: 1,
        pageSize: 10,
        data: {}
        // objects: [],
        // pageSize: 0,
        // totalCount: 0,
        // totalPage: 0,
        // columnNames: []
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
        page: this.listData.currentPage,
        limit: this.listData.currentLimit,
      },
    }).then((data) => {
      // console.log("AdminList data:");
      // console.log(data);
      console.log(data.data);
      console.log(data.data.page.list);
      // this.listData.objects = data.data.page.list;
      // this.listData.pageSize = data.data.page.pageSize;
      // this.listData.totalCount = data.data.page.totalCount;
      // this.listData.totalPage = data.data.page.totalPage;
      // this.listData.columnNames = data.data.page.columnNames

      this.listData.data = data.data;
      // console.log(this.listData.columnNames)
      // this.page=data.data.page
      console.log(data.data.columnNames);
      console.log(data.data.page.columnNames);
      // console.log(this.page)
    });
  },
};
</script>
<style  scoped>
</style>