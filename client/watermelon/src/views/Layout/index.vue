<template>
  <div id="layout" :class="navMenu ? 'close' : 'open'">
    <LayoutHeader />
    <LayoutMain />
    <LayoutNav />
  </div>
</template>
<script>
import LayoutHeader from "./Components/Header";
import LayoutMain from "./Components/Main";
import LayoutNav from "./Components/Nav";
export default {
  name: "Layout",
  components: { LayoutHeader, LayoutMain, LayoutNav },
  data() {
    return {};
  },
  methods: {},
  computed: {
    navMenu: function() {
      return this.$store.state.isCollapse;
    }
  }
};
</script>
<style lang="scss" scoped></style>
