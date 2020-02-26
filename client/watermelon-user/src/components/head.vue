<template>
  <div id="head-wrap">
    <div class="user-info">
      <el-link :underline="false">登录</el-link>
      <el-link :underline="false">注册</el-link>
      <el-link :underline="false" icon="el-icon-message">网站建议</el-link>
    </div>
    <div class="web-info">
      <img src="@/assets/xgImg.png" class="xgImg xg" />
      <label>音乐网</label>
      <div class="search-info">
        <el-input
          placeholder="请输入内容"
          style="width: 250px; display: inline-block;"
          v-model="input"
          clearable
        >
        </el-input>
        <el-button type="info" icon="el-icon-search">搜索</el-button>
      </div>
    </div>
    <div class="nav-wrap">
      <el-menu
        :default-active="routerPath == '/index' ? '/' : routerPath"
        :select="handleSelect()"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router
      >
        <el-menu-item
          v-for="item in routers"
          :key="item.id"
          :index="item.path"
          >{{ item.meta.name }}</el-menu-item
        >
      </el-menu>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    var routers = this.$root.$router.options.routes;
    return {
      routers,
      routerPath: "",
      input: ""
    };
  },
  methods: {
    handleSelect() {
      this.routerPath = this.$route.path;
    }
  },
  created() {
    this.handleSelect();
  }
};
</script>
<style lang="scss" scoped>
#head-wrap {
  margin-bottom: 20px;
}
.user-info {
  width: 100%;
  height: 45px;
  text-align: right;
  .el-link {
    padding-right: 20px;
    font-size: 18px;
  }
}
.web-info {
  .xgImg {
    width: 50px;
    position: absolute;
    top: 25px;
    left: 100px;
  }
  label {
    position: absolute;
    top: 35px;
    left: 160px;
    font-size: 30px;
  }
}
.search-info {
  position: relative;
  top: -16px;
  left: 890px;
  width: 22%;
  .el-input {
    padding-right: 10px;
  }
  .el-button {
    height: 40px;
  }
}
.nav-wrap {
  .el-menu {
    padding-left: 400px;
    .el-menu-item {
      margin-right: 50px;
      font-size: 18px;
    }
  }
}
</style>
