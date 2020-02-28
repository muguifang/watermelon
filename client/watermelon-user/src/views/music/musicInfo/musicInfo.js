// -- 引入外部其他文件 --
// -- 名字 --

const name = "index";

// -- 数据 --

const data = function() {
  return {
    activeName: "first",
    form: {
      content: ""
    }
  };
};

// -- 方法 --

const methods = {
  handleClick(tab, event) {
    console.log(tab, event);
  },
  goBack() {
    history.go(-1);
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
