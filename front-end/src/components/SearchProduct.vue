<template>
  <div class="warper">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/allProduct' }">返回所有产品列表</el-breadcrumb-item>
      <el-breadcrumb-item></el-breadcrumb-item>
    </el-breadcrumb>
    <div>
      <h1 class="HT">通过ID/名称搜索产品</h1>
      <h5 class="subT">Search Products by ID or Name.</h5>
    </div>
    <div style="width: 500px">
      <el-input placeholder="请输入精确ID/模糊搜索名称" v-model="input3" class="input-with-select">
        <el-button slot="append" icon="el-icon-search" @click="getData"></el-button>
      </el-input>
    </div>
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%"
      row-class-name='success-row'
    >
      <el-table-column label="序号" sortable width="90px">
        <template slot-scope="scope">
          {{scope.$index+1}}
        </template>
      </el-table-column>
      <el-table-column prop="p_id" sortable label="产品id" width="180"></el-table-column>
      <el-table-column prop="p_name" sortable label="产品名" width="300"> </el-table-column>
      <el-table-column prop="p_category" sortable label="类别" width="300"> </el-table-column>
      <el-table-column prop="cost" sortable label="成本"> </el-table-column>
      <el-table-column prop="price" sortable label="单价"> </el-table-column>
      <el-table-column label="序号" width="120px">
        <template slot-scope="scope">
          <router-link :to="{path:'purchase/',query:{id:scope.row.p_id,name:scope.row.p_name}}" target="_blank">查询历史订单</router-link>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "ProductByCategory",
  data() {
    return {
      input3:'',
      currentPage:1,
      currentCategory:'1',
      total:-1,
      loading: false,
      tableData: [
      ],
    };
  },

  methods: {
    getData: function(currentCategory) {
      this.loading = true;
      var self = this;
      this.$axios
        .get("/api/product/searchProduct?s=" + this.input3)
        .then(successResponse => {
          if (successResponse.status === 200) {
            self.tableData = successResponse.data['data'];
            for (var i = 0; i < self.tableData.length; i++) {
              self.tableData[i].price /= 100;
              self.tableData[i].cost /= 100;
            }
            this.$message({
              message: "成功",
              type: "success"
            });
           this.loading=false;
          } else {
            this.$notify({
              title: "错误",
              message: successResponse.data.message,
              type: "error"
            });
            this.loading=false;
          }
        })
        .catch(failResponse => {
          this.$message({
            message: "请求失败",
            type: "error"
          });
          this.loading=false;
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
