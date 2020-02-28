// -- 引入外部其他文件 --// -- 引入外部其他文件 --
// -- 名字 --

const name = "";

// -- 数据 --

const data = function() {
  return {};
};

// -- 方法 --

const methods = {
  //页面内路由跳转
  toView() {
    this.$router.push("/musicInfo");
  }
};

// -- 页面加载完成 --
const created = function() {};

// -- 自动计算属性 --

const computed = {};

// -- 监听 --

const watch = {};

// == EXPORT ==

export default {
  name: name,

  data: data,

  computed: computed,

  watch: watch,

  methods: methods,

  created
};
