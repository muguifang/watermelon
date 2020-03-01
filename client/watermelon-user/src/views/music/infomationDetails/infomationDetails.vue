<template>
  <div id="info-wrap">
    <el-page-header @back="goBack" content="资讯详情页面"></el-page-header>
    <h1>{{ info.title }}</h1>
    <img :src="info.pic" />
    <div class="content-info">
      {{ info.content }}
    </div>
  </div>
</template>
<script src="./infomationDetails.js"></script>
<style scoped>
#info-wrap {
  width: 50%;
  margin: 0 auto;
}
img {
  width: 833px;
  height: 450px;
  margin-bottom: 30px;
}
h1 {
  text-align: center;
}
.content-info {
  text-indent: 35px;
  font-size: 18px;
  margin-bottom: 30px;
}
</style>
