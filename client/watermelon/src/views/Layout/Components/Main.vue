<template>
  <div id="main-wrap">
    <router-view />
  </div>
</template>
<script>
import "../../../styles/config.scss";
export default {};
</script>
<style lang="scss" scoped>
#main-wrap {
  position: fixed;
  left: $navMenu;
  top: 75px;
  right: 0;
  bottom: 0;
  border: 30px solid #f8f6fa;
  border-bottom: none;
}
</style>
