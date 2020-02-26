<template>
  <div style="margin-bottom: 20px;">
    <div>
      <el-link :underline="false">登录</el-link>
      <el-link :underline="false">注册</el-link>
      <el-link icon="el-icon-message">网站建议</el-link>
    </div>
    <div>
      <el-input
        placeholder="请输入内容"
        style="width: 250px; display: inline-block;"
        v-model="input"
        clearable
      >
      </el-input>
      <el-button type="info" icon="el-icon-search"></el-button>
    </div>
    <div>
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
<style lang="scss" scoped></style>
