<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>

<style>
@import url("https://fonts.font.im/css?family=Oswald");
.warper {
  display: block;
  height: 50%;
  width: 76%;
  margin-left: 12%;
  margin-top: 7%;
  font-size: 200%;
}
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
.btn {
  margin-bottom: 40px;
}
.subT {
  font-family: "Oswald", sans-serif;
  margin-top: -50px;
}
.el-table .women-row {
  background: oldlace;
}
.input{
  margin-right: 40px;
}
</style>
