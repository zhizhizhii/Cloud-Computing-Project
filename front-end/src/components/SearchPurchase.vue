<template>
  <div class="warper">
    <div v-title data-title="商品订单详情页">
    </div>
    <h3>商品订单详情页</h3>
    <div>
      <h1 class="HT">{{this.$route.query.name}}</h1>
      <h5 class="subT">{{this.$route.query.id}}
        <el-button ref="btn" size="small" class="btn" :loading="btn_loading" type="success" round @click="getData">重新获取数据</el-button></h5>
    </div>
    <div class="block">
      <el-date-picker
        v-model="filterDates"
        value-format="yyyy-MM-dd"
        type="daterange"
        range-separator="至"
        start-placeholder="开始月份"
        end-placeholder="结束月份">
      </el-date-picker>
    </div>
    <el-table
      v-loading="loading"
      :data="filtedData"
      style="width: 100%"
      row-class-name='success-row'
    >
      <el-table-column label="序号"width="90px">
        <template slot-scope="scope">
          {{scope.$index+1}}
        </template>
      </el-table-column>
      <el-table-column prop="o_id" sortable label="订单号" width="180">
      </el-table-column>
      <el-table-column prop="c_id" sortable label="用户ID"> </el-table-column>
      <el-table-column prop="c_name" sortable label="用户名" width="240"> </el-table-column>
      <el-table-column prop="c_sex" sortable label="用户性别"> </el-table-column>
      <el-table-column prop="o_time" sortable label="下单时间"> </el-table-column>
      <el-table-column prop="o_num" sortable label="数量"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "SearchPurchase",
  data() {
    return {
      p_id:'',
      loading: false,
      btn_loading: false,
      input:"0",
      filterType: '',
      filterDates:'',
      tableData: [
      ],
    };
  },
  mounted() { this.getData()
  },
  computed: {
    filtedData () {
      return this.tableData.filter((item) => {
        return !this.filterDates || (new Date(item.o_time) > new Date(this.filterDates[0]) && new Date(item.o_time) < new Date(this.filterDates[1]))
    })}
  },
  methods: {
    getData: function() {
      this.loading = true;
      this.btn_loading = true;
      this.p_id = this.$route.query.id;
      var self = this;
      this.$axios
        .get("/api/order/getPurchaseById?p_id=" + this.p_id)
        .then(successResponse => {
          if (successResponse.status === 200) {
            this.total = successResponse.data['count'] /10;
            self.tableData = successResponse.data['data'];
            for (var i = 0; i < self.tableData.length; i++) {
              if(self.tableData[i].c_sex==0){self.tableData[i].c_sex='男'}
              else{self.tableData[i].c_sex='女'}
              self.tableData[i].price /= 100;
              self.tableData[i].cost /= 100;
            }
            this.$message({
              message: "成功",
              type: "success"
            });
            this.loading = false;
            this.btn_loading =false;
            this.$refs.btn.type = "success";
          } else {
            this.$notify({
              title: "错误",
              message: successResponse.data.message,
              type: "error"
            });
            this.loading = false;
            this.btn_loading =false;
            this.$refs.btn.type = "danger";
          }
        })
        .catch(failResponse => {
          this.$message({
            message: "请求失败",
            type: "error"
          });
          this.loading = false;
          this.btn_loading =false;
          this.$refs.btn.type = "danger";
        });
    }
  }
};
</script>

<style lang="scss" scoped>
</style>
