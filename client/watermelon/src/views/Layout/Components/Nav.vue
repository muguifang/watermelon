<template>
  <div id="nav-wrap">
    <el-menu
      default-active="1-4-1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      :collapse="isCollapse"
      background-color="transparent"
      text-color="#fff"
      router
    >
      <!-- 一级菜单 -->
      <template v-for="(item, index) in routers">
        <el-submenu v-if="!item.hidden" :index="index + ''" :key="item.id">
          <template slot="title">
            <span slot="title">{{ item.meta.name }}</span>
          </template>
          <!-- 子级菜单 -->
          <el-menu-item
            v-for="subItem in item.children"
            :key="subItem.id"
            :index="subItem.path"
            >{{ subItem.meta.name }}</el-menu-item
          >
        </el-submenu>
      </template>
    </el-menu>
  </div>
</template>
<script>
import "../../../styles/config.scss";
export default {
  name: "navMenu",
  data() {
    var isCollapse = false;
    var routers = this.$root.$router.options.routes;
    return {
      isCollapse,
      routers
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
};
</script>
<style lang="scss" scoped>
#nav-wrap {
  position: fixed;
  top: 0px;
  left: 0;
  width: $navMenu;
  height: 100vh;
  background-color: #113c2c;
}
</style>
