import Vue from 'vue'
// 引入 echarts 主模块。
import  echarts from 'echarts/lib/echarts';
// 引入提示框组件、标题组件、工具箱组件。
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
import 'echarts/lib/component/toolbox';

// 引入折线图。
import 'echarts/lib/chart/line';
//引入柱状图
import 'echarts/lib/chart/bar';
//引入饼图
import 'echarts/lib/chart/pie'
Vue.prototype.$echarts=echarts