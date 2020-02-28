// -- 引入外部其他文件 --
// -- 名字 --

const name = "index";

// -- 数据 --

const data = function() {
  return {
    tableData: [
      { musicname: "2016-05-03", insertdate: "王小虎" },
      { musicname: "2016-05-03", insertdate: "王小虎" },
      { musicname: "2016-05-03", insertdate: "王小虎" },
      { musicname: "2016-05-03", insertdate: "王小虎" },
      { musicname: "2016-05-03", insertdate: "王小虎" },
      { musicname: "2016-05-03", insertdate: "王小虎" },
      { musicname: "2016-05-03", insertdate: "王小虎" }
    ]
  };
};

// -- 方法 --

const methods = {};

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
