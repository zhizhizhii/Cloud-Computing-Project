<template>
  <div class="warper">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/allProduct' }">所有产品列表</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/product' }">产品销量TOP</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/total' }">每月销售</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/profit' }">每月利润</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/category' }">类别第一</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/gender' }">性别青睐</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/three' }">连续购买</el-breadcrumb-item>
      <el-breadcrumb-item></el-breadcrumb-item>
    </el-breadcrumb>
    <div>
      <h1 class="HT">每月销售利润统计</h1>
      <h5 class="subT">Sales profit of every MONTH.</h5>
      <el-button
        ref="btn"
        class="btn"
        type="success"
        round
        :loading="false"
        @click="getData"
      >查询</el-button
      >
    </div>
    <div v-loading="chart_loading" id="chartLineBox" style="width: 90%;height: 70vh;"> </div>
    <el-table
      :data="tableData"
      style="width: 100%"
      row-class-name="success-row"
    >
      <el-table-column label="序号"width="90px">
        <template slot-scope="scope">
          {{scope.$index+1}}
        </template>
      </el-table-column>
      <el-table-column prop="year" label="年份" sortable width="200"> </el-table-column>
      <el-table-column prop="month" label="月份" sortable width="200"> </el-table-column>
      <el-table-column prop="total" sortable label="总计销售金额"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "MonthProfit",
  mounted(){
    this.chartLine = echarts.init(document.getElementById('chartLineBox'));
    this.initEcharts();
  },
  data() {
    return {
      chart_loading:false,
      loading: false,
      date_array:[0],
      sale_array:[0],
      tableData: [],
    };
  },
  computed: {
    options() {
      let that = this;
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data:['销售金额']
        },
        color: ['#8AE09F'],
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: that.date_array,
          name: '日期',
          nameTextStyle: {
            color: '#FA6F53',
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: {
            lineStyle: {
              color: '#FA6F53',
            }
          }
        },
        yAxis: {
          name: '月销售利润',
          nameTextStyle: {
            color: '#FA6F53',
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: '#FA6F53',
            }
          },
          type: 'value'
        },
        series: [
          {
            name: '销售利润',
            data: that.sale_array,
            type: 'line',
            lineStyle: {
              normal: {
                color: '#8AE09F',
              }
            },
          }
        ]
      }
      return option;
    }
  },
  methods:
    {
      initEcharts:function(){
        this.options.series[0].data = this.sale_array;
        this.options.xAxis.data = this.date_array;
        let myChart = echarts.init(document.getElementById('chartLineBox'));
        myChart.setOption(this.options)
      },
      sortDownDate: function (a, b) {
        var s1 = a.year + '-' + a.month;
        var s2 = b.year + '-' + b.month;
        return Date.parse(s1) - Date.parse(s2);
      },
      getData: function() {
        this.$refs.btn.loading = true;
        this.chart_loading = true;
        var self = this;
        this.$axios
          .get("/api/profit/monthprofit")
          .then(successResponse => {
            if (successResponse.status === 200) {
              self.tableData = successResponse.data['data'];
              for (var i = 0; i < self.tableData.length; i++) {
                self.tableData[i].profit /= 100;
              }
              self.tableData.sort(self.sortDownDate);
              for(var j=0;j<self.tableData.length;j++){
                var s = self.tableData[j].year + '-' + self.tableData[j].month
                self.date_array.push(s);
                self.sale_array.push(self.tableData[j].profit);
              }
              self.initEcharts();
              this.$message({
                message: "成功",
                type: "success"
              });
              this.$refs.btn.loading = false;
              this.$refs.btn.type = "success";
              this.chart_loading = false;
            } else {
              this.$notify({
                title: "错误",
                message: successResponse.data.message,
                type: "error"
              });
              this.$refs.btn.loading = false;
              this.$refs.btn.type = "danger";
              this.chart_loading = false;
            }
          })
          .catch(failResponse => {
            this.$message({
              message: "请求失败",
              type: "error"
            });
            this.$refs.btn.loading = false;
            this.$refs.btn.type = "danger";
          });
      }
    }
};
</script>

<style lang="scss" scoped></style>
