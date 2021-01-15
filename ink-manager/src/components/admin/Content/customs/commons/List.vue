<!--  -->
<template>
  <div class="list-panel">
    <el-table
      highlight-current-row
      @current-change="rowCurrentChange"
      v-if="data.page"
      :data="
        data.page['list'].filter(
          (data) =>
            !search ||
            //user想办法根据object。getClass().getFiled()获取;
            //TODO
            data.username.toLowerCase().includes(search.toLowerCase())
        )
      "
      style="width: 100%"
    >
      <el-table-column type="index" width="50"> </el-table-column>
      <el-table-column
        v-for="(value, index) in data.page.columnNames"
        :label="value"
        :prop="value"
        :key="index"
      >
      </el-table-column>

      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            :scope="scope"
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"
          />
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="warning"
            @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div style="float: right">
      <el-button
        size="mini"
        type="primary"
        @click="handleAdd(scope.$index, scope.row)"
        >添加</el-button
      >
    </div>
    <div class="pagination">
      <el-pagination
        background
        @size-change="sizeChangeEvent"
        @current-change="currentPageChangeEvent"
        :page-sizes="pageSizes"
        :page-size="pageSize"
        :pager-count="pagerCount"
        :current-page="currentPage"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "List",
  props: ["url"],
  data() {
    return {
      search: "",
      pageSizes: [1, 2, 3, 4],
      pagerCount: 5,
      currentPage: 1,
      pageSize: 1,
      totalCount: 0,
      data: {},
    };
  },

  methods: {
    handleAdd(index, row) {
      console.log(index, row);
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    getPageData(url, currentPage, pageSize) {
      this.$http({
        url: url,
        method: "post",
        params: {
          page: currentPage,
          limit: pageSize,
        },
      }).then((data) => {
        console.log("AdminList data:");
        console.log(data.data);
        this.data = data.data;
        this.currentPage = data.data.page.currPage;
        this.pageSize = data.data.page.pageSize;
        this.totalCount = data.data.page.totalCount;
      });
    },
    sizeChangeEvent(currentPageSize) {
      console.log("sizeChangeEvent");
      console.log(currentPageSize);
      this.pageSize = currentPageSize;
      this.getPageData(this.url, this.currentPage, this.pageSize);
    },
    currentPageChangeEvent(currentPage) {
      console.log("currentPageChangeEvent");
      this.currentPage = currentPage;
      this.getPageData(this.url, this.currentPage, this.pageSize);
    },
    rowCurrentChange(currentRow,oldCurrentRow){
         console.log("rowCurrentChange")
      console.log(currentRow)
      console.log(oldCurrentRow)
    }
  },
  beforeMount() {
    console.log(" LIst  beforeMount");
    this.getPageData(this.url, this.currentPage, this.pageSize);
  },
};
</script>
<style  >
.list-panel {
  height: 100%;
  padding: 50px;
  background-color: rgba(0, 0, 0, 0.1);
}
.pagination {
  margin: 20px;
}
</style>