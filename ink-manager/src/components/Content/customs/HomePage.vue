<!--  -->
<template>
  <div class="homepage">
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-progress :percentage="50"></el-progress>
        </div>
      </el-col>
      <el-col :span="12">
        <div id="homepage-chart" class="grid-content bg-purple">
          <div id="chart1"></div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <div style="width: 200px; height: 20px; background-color: red">
            <div
              class="count"
              style="width: 20px; height: 20px; background-color: green"
            ></div>
          </div>
        </div>
      </el-col>
      <el-col :span="12"><div class="grid-content bg-purple"></div></el-col>
    </el-row>
  </div>
</template>

<script>
//	这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//	例如：import 《组件名称》 from '《组件路径》';

export default {
  name: "HomePage",
  data() {
    return {
      articleCount: 0,
    };
  },
  methods: {
    drawChart() {
      this.$echarts.init(document.getElementById("chart1")).setOption({
        title: {
          text: "ECharts 入门示例",
        },
        tooltip: {},
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [5, 20, 36, 10, 10, 20],
          },
        ],
      });
    },
    animeDo() {
      console.log("this.articleCount");
      console.log(this.articleCount);

      let el = document.querySelector(".count");
      this.$anime({
        targets: [el, ".mixed-array-demo .el-02", ".mixed-array-demo .el-03"],
        translateX: 180,
      });


      this.$anime({
        targets: '.count',
        innerHTML: [0, this.articleCount],
        round: 1,
        easing: "linear",
      });
    },
  },
  //	import引入的组件需要注入到对象中才能使用
  created() {
    this.$http({
      url: "core/sys/article/list",
      method: "post",
      data: {
        page: "1",
        limit: "1",
      },
    })
      .then((data) => {
        console.log("Article data is:");
        console.log(data.data);
        console.log("totalcount");
        console.log(data.data.data.totalCount);
        this.articleCount = data.data.data.totalCount;
        this.$nextTick(() => {
          this.animeDo();
        });
      })
      .catch({});
  },
  mounted() {
    let homePageChart = window.getComputedStyle(
      document.getElementById("homepage-chart")
    ); //获取实时大小
    let chart1 = document.getElementById("chart1");

    chart1.style.width = homePageChart.width;
    chart1.style.height = homePageChart.height;

    this.drawChart();
  },
};
</script>
<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 360px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>