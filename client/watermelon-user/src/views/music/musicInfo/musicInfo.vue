<template>
  <div id="musicInfo-wrap">
    <el-page-header style="margin-bottom: 20px;" @back="goBack" content="音乐详情页面"></el-page-header>
    <!-- 音乐信息 -->
    <div id="musicinfo-content">
      <img src="@/assets/login.jpg" />
      <div class="music-info">
        <p>音乐名称-像鱼</p>
        <el-divider></el-divider>
        <p>分类：流行</p>
        <p>点击量：234</p>
        <p>收藏量：11</p>
        <div class="recommendNum-info">
          <span>推荐指数：</span>
          <div v-for="o in 5" :key="o" class="star">
            <i class="el-icon-star-off"></i>
          </div>
        </div>
        <el-button class="button" icon="el-icon-star-off">收藏</el-button>
      </div>
    </div>
    <!-- 类别信息 -->
    <el-card class="box-card">
      <div slot="header" class="card-info clearfix">
        <span>音乐类别</span>
      </div>
      <div v-for="o in 4" :key="o" class="text item">
        {{ "列表内容 " + o }}
      </div>
    </el-card>
    <!-- 音乐播放器 -->
    <div style="width:74%;margin:100px 0;">
      <audio style="width:100%;" src="@/components/test/radio/try.mp3" controls="controls" />
    </div>
    <!-- 视频播放器 -->
    <div style="width:74%;margin:50px 0;">
      <video style="width:100%;" src="@/components/test/radio/try1.mp4" controls="controls"></video>
    </div>
    <!-- 评论区 -->
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <!-- <el-tab-pane label="歌词" name="first">
        没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词
        没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词
        没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词
        没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词
        没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词没哟歌词
      </el-tab-pane> -->
      <el-tab-pane label="评论" name="first">
        <div v-for="o in 5" :key="o" class="text item">
          <p>用户名：评论内容    （评论时间）</p>
        </div>
        <el-form ref="form" :model="form" style="margin:14px;">
          <p style="font-weight:bold;font-size:18px;margin:0;">评论：</p>
          <el-form-item>
            <el-input type="textarea" v-model="form.content"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="float: right;margin:0 5px;" @click="submitForm('form')">发表评论</el-button>
            <el-button style="float: right;margin:0 5px;" @click="resetForm('ruleForm')">清空
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script src="./musicInfo.js"></script>
<style lang="scss" scoped>
#musicInfo-wrap {
  width: 65%;
  margin: 0 auto;
}
.musicinfo-content {
  width: 40%;
  float: left;
  margin-top: 30px;
}
img {
  width: 400px;
  height: 300px;
  float: left;
}
.el-divider {
  width: 90%;
  margin: 0 auto;
  margin-bottom: 10px;
}
.music-info {
  width: 400px;
  height: 300px;
  float: left;
  padding-top: 20px;
}
p {
  margin-left: 20px;
  margin-bottom: 10px;
  font-size: 18px;
  letter-spacing: 3px;
}
.recommendNum-info {
  width: 100%;
  margin-left: 20px;
  margin-bottom: 10px;
}
.star {
  display: inline;
}
.el-button {
  margin-top: 20px;
  margin-left: 20px;
}
.box-card {
  width: 26%;
  height: 300px;
}
.card-info {
  width: 30%;
  height: 100%;
}
.text {
  font-size: 16px;
}
.item {
  margin-bottom: 15px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.el-tabs {
  width: 74%;
  min-height: 300px;
  margin-bottom: 200px;
}
</style>
