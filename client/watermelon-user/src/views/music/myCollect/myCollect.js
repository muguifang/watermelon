// -- 引入外部其他文件 --
import { getMyCollect, cancelCollect } from "@/api/music.js";
import { getCookie } from "@/utils/store.js";
// -- 名字 --

const name = "index";

// -- 数据 --

const data = function() {
  return {
    //cookie存储的用户信息
    u_info: "",
    tableData: []
  };
};

// -- 方法 --

const methods = {
  //查询我的收藏
  query() {
    this.tableData = [];
    const _u_i = getCookie("_u_i");
    if (_u_i != "") {
      this.u_info = JSON.parse(_u_i);
    } else {
      this.u_info = null;
    }
    getMyCollect(this.u_info).then(response => {
      const res = response.data;
      if (res.code === 200) {
        const data = res.data;
        for (let i = 0; i < data.length; i++) {
          const table = {
            id: data[i].id,
            musicname: data[i].mName,
            insertdate: data[i].insertdate
          };
          this.tableData.push(table);
        }
      }
    });
  },
  //取消收藏
  cancel(id) {
    cancelCollect(id).then(response => {
      const res = response.data;
      if (res.code === 200) {
        this.query();
      }
    });
  }
};

// -- 页面加载完成 --
const created = function() {
  this.query();
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
