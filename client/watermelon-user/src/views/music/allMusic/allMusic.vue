<template>
  <div id="info-wrap">
    <el-card class="box-card">
      <div slot="header" class="card-info clearfix">
        <span>音乐类别</span>
        <el-button style="padding: 3px 0;" type="text">更多</el-button>
      </div>
      <div>
        <ul v-for="item in typeList" :key="item.id" class="text item">
          <li>
            <el-link
              type="primary"
              :underline="false"
              @click="getMusicByTypeId(item.id)"
              >{{ item.name }}</el-link
            >
          </li>
        </ul>
      </div>
    </el-card>
    <div class="music-info">
      <el-row>
        <el-col
          :span="5"
          v-for="(item, index) in allMusics"
          :key="item.id"
          :offset="index > 0 ? 10 : 0"
        >
          <el-card :body-style="{ padding: '0px' }">
            <img :src="item.pic" class="image" />
            <div style="padding: 14px;">
              <span>{{ item.name }}</span>
              <div class="bottom clearfix">
                <div v-for="o in item.star" :key="o" class="star">
                  <i class="el-icon-star-off"></i>
                </div>
                <el-button type="text" class="button" @click="toView(item.id)"
                  >查看音乐</el-button
                >
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script src="./allMusic.js"></script>
<style lang="scss" scoped>
.info-wrap {
  width: 80%;
  height: 100%;
  margin: 0 auto;
  .card-info {
    width: 30%;
    height: 100%;
  }
}
.box-card {
  width: 20%;
  height: 270px;
  margin-left: 200px;
  float: left;
}
.music-info {
  width: 100%;
  .el-row {
    width: 67%;
    float: right;
  }
  .el-col {
    margin-right: 10px;
    margin-bottom: 20px;
  }
}
.image {
  width: 100%;
}
.bottom {
  height: 44px;
  line-height: 44px;
}
.el-button {
  float: right;
}
.star {
  display: inline;
}
.text {
  font-size: 14px;
}
.item {
  margin-bottom: 18px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
</style>
