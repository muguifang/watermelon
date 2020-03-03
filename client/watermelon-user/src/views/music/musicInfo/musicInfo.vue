<template>
  <div id="musicInfo-wrap">
    <el-page-header
      style="margin-bottom: 20px;"
      @back="goBack"
      content="音乐详情页面"
    ></el-page-header>
    <!-- 音乐信息 -->
    <div id="musicinfo-content clearfix">
      <img :src="musicInfo.musicphoto" />
      <div class="music-info">
        <p>{{ musicInfo.musicname }}</p>
        <el-divider></el-divider>
        <p>分类：{{ musicInfo.typeName }}</p>
        <p>点击量：{{ musicInfo.playnum == null ? 0 : musicInfo.playnum }}</p>
        <p>
          收藏量：{{ musicInfo.collectNum == null ? 0 : musicInfo.collectNum }}
        </p>
        <div class="recommendNum-info">
          <span>推荐指数：</span>
          <div v-for="o in musicInfo.recommend" :key="o" class="star">
            <i class="el-icon-star-off"></i>
          </div>
        </div>
        <el-button
          class="button"
          icon="el-icon-star-off"
          @click="collect(musicInfo)"
          >收藏</el-button
        >
        <el-button class="button" icon="el-icon-video-play" @click="playMusic()"
          >播放</el-button
        >
      </div>
    </div>
    <!-- 类别信息
    <el-card class="box-card">
      <div slot="header" class="card-info clearfix">
        <span>音乐类别</span>
      </div>
      <div v-for="o in 4" :key="o" class="text item">
        {{ "列表内容 " + o }}
      </div>
    </el-card> -->
    <!-- 音乐播放器 -->
    <!-- <div class="btn-audio">
      <audio
        style="width:80%;margin:0px 155px;"
        src="@/components/test/radio/try.mp3"
        controls="controls"
      />
    </div> -->
    <!-- 音乐播放器抽屉 -->
    <el-radio-group v-model="direction">
      <el-radio label="btt" hidden="true">从下往上开</el-radio>
      <el-drawer
        title="音乐播放器"
        :with-header="false"
        :visible.sync="drawer"
        :direction="direction"
        :modal="modal"
      >
        <div class="btn-audio">
          <audio
            style="width:80%;margin:0px 155px;"
            src="@/components/test/radio/try.mp3"
            controls="controls"
          />
        </div>
      </el-drawer>
    </el-radio-group>
    <!-- 视频播放器 -->
    <div style="width:74%;margin:370px 100px 100px 100px;">
      <video
        style="width:100%;"
        src="@/components/test/radio/try1.mp4"
        controls="controls"
      ></video>
    </div>
    <!-- 评论区 -->
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="评论" name="first">
        <div v-for="item in comments" :key="item.id" class="text item">
          <p>
            {{ item.username }}：{{ item.content }} （{{ item.insertDate }}）
          </p>
        </div>
        <el-form ref="form" :model="form" style="margin:14px;">
          <p style="font-weight:bold;font-size:18px;margin:0;">评论：</p>
          <el-form-item>
            <el-input type="textarea" v-model="form.content"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="float: right;margin:0 5px;" @click="submitForm()"
              >发表评论</el-button
            >
            <el-button
              style="float: right;margin:0 5px;"
              @click="resetForm('ruleForm')"
              >清空
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
  width: 60%;
  margin: 0 auto;
}
.musicinfo-content {
  width: 60%;
  float: left;
  margin-top: 30px;
}
img {
  width: 400px;
  height: 300px;
  float: left;
  margin-left: 100px;
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
  margin-left: 100px;
}
.btn-audio {
  width: 100%;
  margin: 100px auto;
}
</style>
