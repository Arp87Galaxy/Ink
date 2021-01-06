<!--  -->
<template>
  <div class="list-panel">
    <el-table
      :data="
        listData.objects
        .filter(
          (data) =>
            !search ||
            //user想办法根据object。getClass().getFiled()获取;
            //TODO
            data.username.toLowerCase().includes(search.toLowerCase())
        )
      "
      style="width: 100%"
    >
      <el-table-column
        v-for="(value, index) in listData.columnNames"
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
    <div style="float:right">
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
        :page-sizes="[10, 20, 30, 40]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="listData.totalCount"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "List",
  props: ["listData"],
  data() {
    return {
      search: "",
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