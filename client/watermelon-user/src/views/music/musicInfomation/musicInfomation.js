// -- 引入外部其他文件 --

// -- 名字 --

const name = "musuicInfomation";

// -- 数据 --

const data = function() {
  return {
    activeName: "1",
    tableData: [
      {
        id: 1,
        title: "新资讯1",
        content: "没有什么内容哦哦哦！"
      },
      {
        id: 2,
        title: "新资讯2",
        content: "没有什么内容哦哦哦！"
      },
      {
        id: 3,
        title: "新资讯3",
        content: "没有什么内容哦哦哦！"
      },
      {
        id: 4,
        title: "新资讯4",
        content: "没有什么内容哦哦哦！"
      }
    ]
  };
};

// -- 方法 --

const methods = {
  toInfoView() {
    this.$router.push("/detailsInfo");
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
