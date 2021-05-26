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
      <h1 class="HT">最受男性/女性青睐产品</h1>
      <h5 class="subT">Products most FAVORED by men / women.</h5>
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
    <h5 class="subTs">BEST sales of Men.</h5>
    <el-table
      :data="tableDataMale"
      style="width: 100%"
      row-class-name="success-row"
    >
      <el-table-column label="排名" width="70px">
        <template slot-scope="scope">
          {{scope.$index+1}}
        </template>
      </el-table-column>
      <el-table-column prop="p_id" label="产品id" width="180">
      </el-table-column>
      <el-table-column prop="p_name" label="产品名" width="180">
      </el-table-column>
      <el-table-column prop="p_category" label="类别"> </el-table-column>
      <el-table-column prop="cost" label="成本"> </el-table-column>
      <el-table-column prop="price" label="单价"> </el-table-column>
      <el-table-column prop="s_vol" label="销量"> </el-table-column>
    </el-table>
    <h5 class="subTs">BEST sales of Women.</h5>
    <el-table
      :data="tableDataFemale"
      style="width: 100%"
      row-class-name="women-row"
    >
      <el-table-column label="排名" width="70px">
        <template slot-scope="scope">
          {{scope.$index+1}}
        </template>
      </el-table-column>
      <el-table-column prop="p_id" label="产品id" width="180">
      </el-table-column>
      <el-table-column prop="p_name" label="产品名" width="180">
      </el-table-column>
      <el-table-column prop="p_category" label="类别"> </el-table-column>
      <el-table-column prop="cost" label="成本"> </el-table-column>
      <el-table-column prop="price" label="单价"> </el-table-column>
      <el-table-column prop="s_vol" label="销量"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Gender",
  data() {
    return {
      loading: false,
      tableDataMale: [],
      tableDataFemale: []
    };
  },
  methods: {
    indexFn(index) {
      index = (index + 1) + (this.page - 1) * this.per_page
      return index
    },
    getData: function() {
      this.$refs.btn.loading = true;
      var self = this;
      this.$axios
        .get("/api/purchase/genderprefer")
        .then(successResponse => {
          if (successResponse.status === 200) {
            var data = successResponse.data['data'];
            var maleData = new Array();
            var femaleData = new Array();
            for(var j = 0 ; j < data.length ; j++){
              if (data[j]['c_sex'] == 0)
              {maleData.push(data[j]);}
              else{
                femaleData.push(data[j]);
              }
            }
            var objMale = JSON.parse(JSON.stringify(maleData));
            var objfeMale = JSON.parse(JSON.stringify(femaleData));
            for(var k = 0 ; k < 3 ; k++){
              delete objMale[k]['c_sex'];
              delete objfeMale[k]['c_sex'];
            }
            self.tableDataMale = objMale;
            self.tableDataFemale = objfeMale;
            for (var i = 0; i < self.tableDataMale.length; i++) {
              self.tableDataMale[i].cost /= 100;
              self.tableDataMale[i].price /= 100;
              self.tableDataFemale[i].cost /= 100;
              self.tableDataFemale[i].price /= 100;
            }
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
.subTs {
  font-family: "Oswald", sans-serif;
}
</style>
