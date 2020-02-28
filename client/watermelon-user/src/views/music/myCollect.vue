<template>
  <div id="collect-info">
    <div class="title-info">
      <el-divider content-position="left">我的收藏</el-divider>
    </div>
    <template>
      <el-table :data="tableData" height="450" border style="width: 60%;margin: 0 auto;">
        <el-table-column prop="musicname" label="音乐名称" width="500" align="center">
        </el-table-column>
        <el-table-column prop="insertdate" label="收藏时间" width="300" align="center">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <el-button icon="el-icon-star-on">取消收藏</el-button>
        </el-table-column>
      </el-table>
    </template>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData: [
          { musicname: '2016-05-03', insertdate: '王小虎' },
          { musicname: '2016-05-03', insertdate: '王小虎' },
          { musicname: '2016-05-03', insertdate: '王小虎' },
          { musicname: '2016-05-03', insertdate: '王小虎' },
          { musicname: '2016-05-03', insertdate: '王小虎' },
          { musicname: '2016-05-03', insertdate: '王小虎' },
          { musicname: '2016-05-03', insertdate: '王小虎' }
       ]
    }
  }
}
</script>
<style lang="scss" scoped>
#collect-info {
  width: 100%;
}
.title-info {
  width: 60%;
  margin: 0 auto;
  margin-bottom: 20px;
}
.el-divider {
  margin-top: 50px;
  margin-bottom: 50px;
  font-size: 22px;
}
</style>
