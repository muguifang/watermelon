// -- 引入外部其他文件 --
import { getMusicByConditions } from "@/api/music.js";
// -- 名字 --

const name = "searchMusic";

// -- 数据 --

const data = function() {
  return {
    activeName: "first",
    input: "",
    // input: this.$router.params.name,
    tableData: []
  };
};

// -- 方法 --

const methods = {
  //查询音乐
  query() {
    if (this.input == "") {
      this.$message({
        type: "info",
        message: "请输入音乐名称"
      });
    } else {
      this.tableData = [];
      const param = {
        musicname: this.input
      };
      getMusicByConditions(param).then(response => {
        const res = response.data;
        if (res.code === 200) {
          const tableList = res.data;
          for (let i = 0; i < tableList.length; i++) {
            const table = {
              id: tableList[i].id,
              musicName: tableList[i].musicName,
              musicPlay: tableList[i].musicPlay,
              mvPlay: tableList[i].mvPlay,
              playNum: tableList[i].playNum,
              typeName: tableList[i].typeName,
              insertDate: tableList[i].insertDate
            };
            this.tableData.push(table);
            console.log(this.tableData);
          }
        }
      });
    }
  },
  handleClick(tab, event) {
    console.log(tab, event);
  }
};

// -- 页面加载完成 --
const created = function() {
  //   this.query();
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
