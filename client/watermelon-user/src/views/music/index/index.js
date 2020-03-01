// -- 引入外部其他文件 --
import { getAllPhont } from "@/api/file.js";
import { base64Convert } from "@/utils/base64Util.js";
import { getTenMusic, getRecommendMusic } from "@/api/music.js";
// -- 名字 --

const name = "index";

// -- 数据 --

const data = function() {
  return {
    //页面加载中
    loading: true,
    //轮播图
    urls: [],
    //最新歌单
    newMusics: [],
    //推荐歌单
    recommendMusics: []
  };
};

// -- 方法 --

const methods = {
  init() {
    //页面加载完成调用该方法加载轮播图
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
    //加载最新音乐
    getTenMusic().then(response => {
      const res = response.data;
      if (res.code === 200) {
        this.newMusics = [];
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
          this.newMusics.push(music);
        }
      }
    });
    //加载推荐音乐
    getRecommendMusic().then(response => {
      const res = response.data;
      if (res.code === 200) {
        this.recommendMusics = [];
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
          this.recommendMusics.push(music);
        }
      }
    });
    if (this.urls != [] && this.newMusics != [] && this.recommendMusics != []) {
      const timer = window.setTimeout((this.loading = false), 1000);
      window.clearTimeout(timer);
    }
  },
  //页面内路由跳转
  toView(musicId) {
    this.$router.push({ path: "/musicInfo", query: { id: musicId } });
  }
};

// -- 页面加载完成 --
const created = function() {
  this.init();
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
