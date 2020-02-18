<template>
  <div id="header-nav">
    <div>
      <p>欢迎您！{{ user.username }}</p>
    </div>
  </div>
</template>
<script>
import "../../../styles/config.scss";
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
    this.user.username = user.username;
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
  // background-color: #3fb08e;
  // box-shadow: 0 3px 16px 0 rgba(63, 176, 142, 0.1);
  background-color: #fff;
  box-shadow: 0 3px 16px 0 rgba(0, 0, 0, 0.1);
}
</style>
