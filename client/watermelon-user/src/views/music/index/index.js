// -- 引入外部其他文件 --
import { getAllPhont } from "@/api/file.js";
import { base64Convert } from "@/utils/base64Util.js";
// -- 名字 --

const name = "index";

// -- 数据 --

const data = function() {
  return { urls: [] };
};

// -- 方法 --

const methods = {
  //初始化首页
  initPage() {
    getAllPhont().then(response => {
      const res = response.data;
      if (res.code === 200) {
        this.urls = [];
        const data = res.data;
        //遍历结果集 将对应的base64串转文图片
        for (let i = 0; i < data.length; i++) {
          const url = base64Convert(data[i].pic);
          this.urls.push(URL.createObjectURL(url));
        }
      } else {
        this.$message({
          message: "页面加载失败!",
          type: "warning"
        });
        //加载失败跳转至404页面
      }
    });
  }
};

// -- 页面加载完成 --
const created = function() {
  //页面加载完成调用该方法
  this.initPage();
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
