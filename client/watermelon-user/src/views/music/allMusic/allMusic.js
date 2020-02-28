// -- 引入外部其他文件 --// -- 引入外部其他文件 --
import { getAllType, getMusicByType } from "@/api/music.js";
// -- 名字 --

const name = "AllMusic";

// -- 数据 --

const data = function() {
  return {
    typeList: []
  };
};

// -- 方法 --

const methods = {
  //页面内路由跳转
  toView() {
    this.$router.push("/musicInfo");
  },
  //点击类别获取该类别下音乐
  getMusic(param){
    getMusicByType(param).then(response => {
      const res = response.data;
      console.log(res);
    });
  }
};

// -- 页面加载完成 --
const created = function() {
  this.typeList = [];
  const param = { typename: "" };
  getAllType(param).then(response => {
    const res = response.data;
    if (res.code === 200) {
      const typeData = res.data;
      for (let i = 0; i < typeData.length; i++) {
        const type = {
          id: "",
          name: ""
        };
        type.id = typeData[i].id;
        type.name = typeData[i].typename;
        this.typeList.push(type);
      }
    } else {
      this.$message.warning("初始化页面失败!");
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
