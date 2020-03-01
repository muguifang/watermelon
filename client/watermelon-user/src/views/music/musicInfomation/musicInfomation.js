// -- 引入外部其他文件 --
import { getAllInfomation } from "@/api/infomation.js";
// -- 名字 --

const name = "musuicInfomation";

// -- 数据 --

const data = function() {
  return {
    activeName: "1",
    tableData: []
  };
};

// -- 方法 --

const methods = {
  toInfoView(item) {
    this.$router.push({
      path: "/detailsInfo",
      query: { info: JSON.stringify(item) }
    });
  }
};

// -- 页面加载完成 --
const created = function() {
  //获取所有资讯
  getAllInfomation({ title: "" }).then(response => {
    const res = response.data;
    if (res.code === 200) {
      this.tableData = [];
      const data = res.data;
      for (let i = 0; i < data.length; i++) {
        const table = {
          id: "",
          title: "",
          pic: "",
          content: ""
        };
        table.id = data[i].id;
        table.title = data[i].title;
        table.pic = data[i].pic;
        table.content = data[i].content;
        this.tableData.push(table);
      }
    }
  });
};

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
