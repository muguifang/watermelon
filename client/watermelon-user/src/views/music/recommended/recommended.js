// -- 引入外部其他文件 --// -- 引入外部其他文件 --
import { base64Convert } from "@/utils/base64Util.js";
import { getAllType, getMusicByType, getTenMusic } from "@/api/music.js";
// -- 名字 --

const name = "Recommended";

// -- 数据 --

const data = function() {
  return {
    //音乐类别
    typeList: [],
    //全部音乐
    allMusics: []
  };
};

// -- 方法 --

const methods = {
  //通过音乐类别获取音乐
  getMusicByTypeId(id) {
    getMusicByType({ typeId: id }).then(response => {
      const res = response.data;
      this.allMusics = [];
      const data = res.data;
      if (data != null && data.length > 0) {
        for (let i = 0; i < data.length; i++) {
          const music = {
            id: "",
            pic: "",
            star: "",
            name: ""
          };
          music.id = data[i].id;
          const img = data[i].musicphoto;
          music.pic = URL.createObjectURL(base64Convert(img));
          music.name = data[i].musicname;
          music.star = parseInt(data[i].recommend);
          this.allMusics.push(music);
        }
      } else {
        this.$message("该类别下无音乐!");
      }
    });
  },
  //页面初始化方法
  init() {
    //加载最新音乐
    getTenMusic().then(response => {
      const res = response.data;
      if (res.code === 200) {
        this.allMusics = [];
        const data = res.data;
        for (let i = 0; i < data.length; i++) {
          const music = {
            id: "",
            pic: "",
            star: "",
            name: ""
          };
          music.id = data[i].id;
          const img = data[i].musicphoto;
          music.pic = URL.createObjectURL(base64Convert(img));
          music.name = data[i].musicname;
          music.star = parseInt(data[i].recommend);
          this.allMusics.push(music);
        }
      }
    });
  },
  //页面内路由跳转
  toView(musicId) {
    this.$router.push({ path: "/musicInfo", query: { id: musicId } });
  }
};

// -- 页面加载完成 --
const created = function() {
  this.init();
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
