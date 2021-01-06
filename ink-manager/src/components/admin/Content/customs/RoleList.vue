<!--  -->
<template>
  <div>
    <List :listData="listData"></List>
  </div>
</template>

<script>
export default {
  components: {},
  name: "RoleList",
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
        columnNames: {
          roleId: "roleId",
          roleName: "roleName",
          remark: "remark",
          createUserId: "createUserId",
          createTime: "createTime"
        },
      },
      // page:{}
    };
  },
  beforeMount() {
    this.$http({
      url: "core/sysrole/list",
      method: "post",
      params: {
        page: this.currentPage,
        limit: this.currentLimit,
      },
    }).then((data) => {
      // console.log("AdminList data:");
      // console.log(data);
      // console.log(data.data);
      console.log(data.data.page);
      this.listData.objects = data.data.page.list;
      this.listData.pageSize = data.data.page.pageSize;
      this.listData.totalCount = data.data.page.totalCount;
      this.listData.totalPage = data.data.page.totalPage;
      // this.page=data.data.page
      // console.log("page is?")
      // console.log(this.page)
    });
  },
};
</script>
<style  scoped>
</style>