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
      <h1 class="HT">所有产品列表</h1>
      <h5 class="subT">All Products.
        <el-button ref="btn" size="small" class="btn" type="success" round @click="getData(currentPage)">重新获取数据</el-button>
        <el-button type="primary" size="small" icon="el-icon-search" round @click="gotolink1">搜索产品</el-button>
        <el-button type="primary" size="small" icon="el-icon-view"round  @click="gotolink2">类别筛选</el-button>
      </h5>
    </div>
    <el-table
      v-loading="table_loading"
      :data="tableData"
      style="width: 100%"
      row-class-name='success-row'
    >
      <el-table-column label="序号"width="90px">
        <template slot-scope="scope">
          {{scope.$index+1}}
        </template>
      </el-table-column>
      <el-table-column prop="p_id" sortable label="产品id" width="180"></el-table-column>
      <el-table-column prop="p_name" sortable label="产品名" > </el-table-column>
      <el-table-column prop="p_category" sortable label="类别" > </el-table-column>
      <el-table-column prop="cost" sortable label="成本"> </el-table-column>
      <el-table-column prop="price" sortable label="单价"> </el-table-column>
      <el-table-column label="操作" width="120px">
        <template slot-scope="scope">
          <router-link :to="{path:'purchase/',query:{id:scope.row.p_id,name:scope.row.p_name}}" target="_blank">查询历史订单</router-link>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex" class="row-bg" justify="center">
    <el-pagination
      @current-change="getData"
      background
      layout="prev, pager, next"
      :current-page="currentPage"
      :total="total">
    </el-pagination>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "AllProduct",
  data() {
    return {
      table_loading:false,
      currentPage:1,
      total:1,
      loading: false,
      tableData: []
    };
  },
  mounted() {this.getData(1)},
  methods: {
    gotolink1(){
      this.$router.replace('/searchProduct')
    },
    gotolink2(){
      this.$router.replace('/productByCategory')
    },
    getData: function(currentPage) {
      this.currentPage = currentPage;
      this.$refs.btn.loading = true;
      this.table_loading = true;
      var self = this;
      this.$axios
        .get("/api/product/getAllProduct?page_id=" + this.currentPage)
        .then(successResponse => {
          if (successResponse.status === 200) {
            this.total = successResponse.data['count'] /10;
            self.tableData = successResponse.data['data'];
            for (var i = 0; i < self.tableData.length; i++) {
              self.tableData[i].price /= 100;
              self.tableData[i].cost /= 100;
            }
            this.$message({
              message: "成功",
              type: "success"
            });
            this.$refs.btn.loading = false;
            this.$refs.btn.type = "success";
            this.table_loading = false;
          } else {
            this.$notify({
              title: "错误",
              message: successResponse.data.message,
              type: "error"
            });
            this.$refs.btn.loading = false;
            this.$refs.btn.type = "danger";
            this.table_loading = false;
          }
        })
        .catch(failResponse => {
          this.$message({
            message: "请求失败",
            type: "error"
          });
          this.$refs.btn.loading = false;
          this.$refs.btn.type = "danger";
          this.table_loading = false;
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.el-row {
  margin-top: 20px;
}
</style>
