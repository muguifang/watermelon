<template>
  <div id="index-wrap">
    <div class="lbt-card">
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="url in urls" :key="url">
          <el-image :src="url"></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="latest-info">
      <div class="flag"></div>
      <label>最新歌单</label>
      <el-divider></el-divider>
      <div class="music-info">
        <el-row>
          <el-col
            :span="4"
            v-for="(o, index) in 10"
            :key="o"
            :offset="index > 0 ? 10 : 0"
          >
            <el-card :body-style="{ padding: '0px' }">
              <img src="@/assets/login.jpg" class="image" />
              <div style="padding: 14px;">
                <span>好听的音乐</span>
                <div class="bottom clearfix">
                  <div v-for="o in 5" :key="o" class="star">
                    <i class="el-icon-star-off"></i>
                  </div>
                  <el-button type="text" class="button">查看音乐</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="latest-info">
      <div class="flag"></div>
      <label>推荐歌单</label>
      <el-divider></el-divider>
      <div class="music-info">
        <el-row>
          <el-col
            :span="4"
            v-for="(o, index) in 10"
            :key="o"
            :offset="index > 0 ? 10 : 0"
          >
            <el-card :body-style="{ padding: '0px' }">
              <img src="@/assets/login.jpg" class="image" />
              <div style="padding: 14px;">
                <span>好听的音乐</span>
                <div class="bottom clearfix">
                  <div v-for="o in 5" :key="o" class="star">
                    <i class="el-icon-star-off"></i>
                  </div>
                  <el-button type="text" class="button">查看音乐</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
    <!-- <div class="latest-info">
      <div class="flag"></div>
      <label>推荐歌单</label>
      <el-divider></el-divider>
    </div> -->
  </div>
</template>
<script>
export default {
  name: "index",
  data() {
    return {
      urls: [
        "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
        "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
        "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
        "https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg",
        "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
        "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
        "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
        "https://s2.ax1x.com/2020/02/26/3NsfAK.jpg"
      ]
    };
  }
};
</script>
<style>
.index-wrap {
  width: 100%;
}
.latest-info {
  width: 80%;
  margin: 0 auto;
}
label {
  font-size: 22px;
  letter-spacing: 3px;
  margin-left: 10px;
}
.flag {
  width: 15px;
  height: 15px;
  border: 5px solid red;
  border-radius: 50%;
  float: left;
}
.lbt-card {
  width: 800px;
  margin: 0 auto;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.music-info {
  width: 100%;
}
.el-row {
  width: 100%;
  float: right;
}
.el-col {
  margin-right: 25px;
  margin-bottom: 30px;
  margin-left: 19px;
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
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
</style>
