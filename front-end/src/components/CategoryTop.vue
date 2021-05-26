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
      <h1 class="HT">每个种类销量第一</h1>
      <h5 class="subT">Top SALEs of ever category.</h5>
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

    <el-table
      :data="tableData"
      style="width: 100%"
      row-class-name='success-row'
    >
      <el-table-column label="序号"width="90px">
        <template slot-scope="scope">
          {{scope.$index+1}}
        </template>
      </el-table-column>
      <el-table-column prop="p_id" sortable label="产品id" width="180"> </el-table-column>
      <el-table-column prop="p_name" sortable label="产品名" width="180"> </el-table-column>
      <el-table-column prop="p_category" sortable label="类别"> </el-table-column>
      <el-table-column prop="cost"sortable  label="成本"> </el-table-column>
      <el-table-column prop="price" sortable label="单价"> </el-table-column>
      <el-table-column prop="s_vol" sortable label="销量"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "CategoryTop",
  data() {
    return {
      loading: false,
      tableData: []
    };
  },
  methods: {
    getData: function() {
      this.$refs.btn.loading = true;
      var self = this;
      this.$axios
        .get("/api/product/categorytop")
        .then(successResponse => {
          if (successResponse.status === 200) {
            self.tableData = successResponse.data['data'];
            this.$message({
              message: "成功",
              type: "success"
            });
            this.$refs.btn.loading = false;
            this.$refs.btn.type = "success";
          } else {
            this.$notify({
              title: "错误",
              message: successResponse.data.message,
              type: "error"
            });
            this.$refs.btn.loading = false;
            this.$refs.btn.type = "danger";
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

<style lang="scss" scoped>

</style>
