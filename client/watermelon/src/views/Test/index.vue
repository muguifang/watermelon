/* eslint-disable no-unused-vars */
<template>
  <div>
    <audio :src="src" controls="controls" />
  </div>
</template>
<script>
export default {
  data() {
    return {
      src: require("D:/mp3/薛之谦 - 像风一样.mp3")
    };
  },
  created() {
    console.log(this.src);
  }
};
</script>
