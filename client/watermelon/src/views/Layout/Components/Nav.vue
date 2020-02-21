<template>
  <div id="nav-wrap">
    <div id="xgImg">
      <svg-icon iconClass="xgImg" class="xgImg xg" />
      <label>音乐网</label>
    </div>
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
            <svg-icon :iconClass="item.meta.icon" :className="item.meta.icon" />
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
import "../../../styles/elementui.scss";
export default {
  name: "navMenu",
  data() {
    // var isCollapse = false;
    var routers = this.$root.$router.options.routes;
    return {
      // isCollapse,
      routers
    };
  },
  methods: {
    handleOpen() {},
    handleClose() {}
  },
  computed: {
    isCollapse: function() {
      return this.$store.state.isCollapse;
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
  @include webkit(transition, all 0.3s ease 0s);
  svg {
    font-size: 20px;
    margin-right: 15px;
  }
  .el-submenu {
    margin-left: -10px;
  }
  // .el-menu-item {
  //   text-align: center;
  // }
}
.el-menu {
  border: none;
}
svg {
  font-size: 16px;
  margin-left: 12px;
  margin-right: 16px;
  padding-bottom: 4px;
}
#xgImg {
  margin-top: 20px;
  margin-left: 35px;
  width: $navMenu;
  margin-bottom: 30px;
  color: #fff;
  label {
    margin-top: -30px;
  }
  svg {
    margin-bottom: -10px;
    font-size: 30px;
  }
}
.open {
  #nav-wrap {
    width: $navMenu;
  }
  #xgImg {
    width: $navMenu;
  }
}
.close {
  #nav-wrap {
    width: $navMenuMin;
  }
  #xgImg {
    width: $navMenuMin;
    margin-left: 10px;
    svg {
      width: 20px;
      margin-bottom: -10px;
      font-size: 30px;
    }
  }
}
</style>
