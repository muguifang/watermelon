<template>
  <div id="information-wrap">
    <div class="title-info">
      <div class="flag"></div>
      <label>最新资讯</label>
    </div>
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item title="标题1" name="1">
        <div>
          与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
          <el-button type="text" class="button" @click="toInfoView"
            >查看音乐</el-button
          >
        </div>
      </el-collapse-item>
      <el-collapse-item title="标题4" name="2">
        <div>
          与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
        </div>
      </el-collapse-item>
      <el-collapse-item title="标题2" name="3">
        <div>
          与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
        </div>
      </el-collapse-item>
      <el-collapse-item title="标题3" name="4">
        <div>
          与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>
<script>
export default {
  name: "informationIndex",
  data() {
    return {
      activeName: "1",
      tableData: [
        {
          id: 1,
          title: "新资讯1",
          content: "没有什么内容哦哦哦！"
        },
        {
          id: 2,
          title: "新资讯2",
          content: "没有什么内容哦哦哦！"
        },
        {
          id: 3,
          title: "新资讯3",
          content: "没有什么内容哦哦哦！"
        },
        {
          id: 4,
          title: "新资讯4",
          content: "没有什么内容哦哦哦！"
        }
      ]
    };
  },
  methods: {
    toInfoView() {
      this.$router.push("/detailsInfo");
    }
  }
};
</script>
<style lang="scss" scoped>
.el-collapse {
  width: 70%;
  margin: 0 auto;
}
.title-info {
  width: 70%;
  margin: 0 auto;
  height: 50px;
  background-color: #ded9d9;
}
.flag {
  width: 15px;
  height: 15px;
  border: 5px solid red;
  border-radius: 50%;
  float: left;
  margin-top: 13px;
  margin-left: 10px;
}
label {
  font-size: 22px;
  letter-spacing: 3px;
  margin-left: 10px;
  height: 50px;
  line-height: 50px;
}
.el-collapse-item {
  font-size: 18px;
}
</style>
