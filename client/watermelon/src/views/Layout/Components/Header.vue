<template>
  <div id="header-nav">
    <div class="header-icon" @click="navMenuState">
      <svg-icon iconClass="menu" className="menu" />
    </div>
    <div class="topInfo">
      <p>欢迎您！{{ user.username }}</p>
      <!-- <svg-icon iconClass="exit" class="exit" @click="exit()" /> -->
      <!-- <el-button type="button" @click="exit()" class="exit-btn">注销</el-button> -->
      <el-button @click="exit()" class="exit-btn">
        <svg-icon iconClass="exit" class="exit" />
      </el-button>
    </div>
  </div>
</template>
<script>
import "../../../styles/config.scss";
import router from "@/router";
export default {
  data() {
    return {
      user: {
        username: ""
      }
    };
  },
  mounted() {
    const user = JSON.parse(sessionStorage.getItem("user"));
    if (user === null) {
      this.$message({
        message: "请登录!",
        type: "warning"
      });
      router.push({ name: "Login" });
    }
    this.user.username = user.username;
  },
  methods: {
    exit() {
      this.$confirm("是否确认注销，返回登录页面?", "提示", {
        cancelButtonText: "取消",
        confirmButtonText: "确定",
        type: "warning"
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "注销成功!"
          });
          sessionStorage.removeItem("user");
          router.push({ name: "Login" });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消注销"
          });
        });
    },
    navMenuState() {
      this.$store.commit("SET_COLLAPSE");
    }
  }
};
</script>
<style lang="scss" scoped>
#header-nav {
  position: fixed;
  top: 0;
  right: 0;
  left: $navMenu;
  height: 75px;
  background-color: #3fb08e;
  @include webkit(box-shadow, 0 3px 16px 0 rgba(4, 34, 25, 0.1));
  @include webkit(transition, all 0.3s ease 0s);
}
.open {
  #header-nav {
    left: $navMenu;
  }
}
.close {
  #header-nav {
    left: $navMenuMin;
  }
}
.topInfo {
  width: 280px;
  height: 50px;
  float: right;
  line-height: 50px;
  p {
    position: relative;
    top: 0px;
    right: -40px;
    color: #fff;
    display: inline;
  }
}
.exit-btn {
  position: relative;
  left: 50px;
  top: 10px;
  border-radius: 10px;
  background-color: #3fb08e;
  border: none;
  font-size: 30px;
}
.header-icon {
  padding: 26px 32px;
  float: left;
  svg {
    margin-bottom: -8px;
    font-size: 25px;
    cursor: pointer;
  }
}
</style>
