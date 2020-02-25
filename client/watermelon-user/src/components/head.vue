<template>
  <div>
    <el-menu
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
    >
      <el-submenu
        v-for="(item, index) in routers"
        :key="item.id"
        :index="index + ''"
      >
        <template slot="title">{{ item.meta.name }}</template>
        <el-menu-item
          v-for="subItem in item.children"
          :key="subItem.id"
          :index="subItem.path"
          >{{ subItem.meta.name }}</el-menu-item
        >
      </el-submenu>
    </el-menu>
  </div>
</template>
<script>
export default {
  data() {
    var routers = this.$root.$router.options.routes;
    return {
      routers
    };
  }
};
</script>
<style lang="scss" scoped></style>
