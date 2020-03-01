<template>
  <div id="layout">
    <LayoutHeader />
    <LayoutMain />
  </div>
</template>
<script>
import LayoutHeader from "@/components/head/head.vue";
import LayoutMain from "@/components/main.vue";
export default {
  name: "Layout",
  components: { LayoutHeader, LayoutMain },
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
