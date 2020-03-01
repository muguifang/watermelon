// -- 引入外部其他文件 --
import { base64Convert } from "@/utils/base64Util.js";
// -- 名字 --

const name = "detailsInfo";

// -- 数据 --

const data = function() {
  return {
    //页面资讯
    info: {}
  };
};

// -- 方法 --

const methods = {
  goBack() {
    this.$router.push("/musicInfomation");
  }
};

// -- 页面加载完成 --
const created = function() {
  const info = JSON.parse(this.$route.query.info);
  info.pic = URL.createObjectURL(base64Convert(info.pic));
  this.info = info;
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
