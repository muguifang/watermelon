// -- 引入外部其他文件 --
import { getMusicByConditions, returnBase64 } from "@/api/music.js";
import { base64Convert } from "@/utils/base64Util.js";
// -- 名字 --

const name = "searchMusic";

// -- 数据 --

const data = function() {
  return {
    activeName: "first",
    input: "",
    // input: this.$router.params.name,
    tableData: [],
    //音乐路径
    musicUrl: "",
    //音乐加载完成
    isLoad: false,
    isPlay: false,
    //当前音乐
    nowMu: "",
    //上一个播放按钮
    playBut: ""
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
          }
        }
      });
    }
  },
  //播放音乐
  playMusic(row, key) {
    const icon = document.getElementById(key);
    if (icon.className == "el-icon-video-play") {
      icon.className = "el-icon-video-pause";
    } else {
      icon.className = "el-icon-video-play";
    }
    const param = {
      path: row.musicPlay
    };
    if (this.nowMu != row.musicPlay) {
      this.isLoad = false;
      if (this.playBut != "") {
        const lasticon = document.getElementById(this.playBut);
        lasticon.className = "el-icon-video-play";
      }
    }
    if (!this.isLoad) {
      returnBase64(param).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.musicUrl = URL.createObjectURL(base64Convert(data.data));
          this.nowMu = param.path;
          this.playBut = key;
          this.isLoad = true;
        } else {
          this.$message({
            type: "error",
            message: "获取播放源失败"
          });
        }
      });
    }
    //自动播放 状态是true播放中
    //判断播放中 点击后将音乐暂停 状态false
    if (this.isPlay) {
      document.getElementById("music").pause();
      this.isPlay = false;
    } else {
      //判断暂停 点击播放 转台为true
      document.getElementById("music").play();
      this.isPlay = true;
    }
  },
  handleClick(tab, event) {
    console.log(tab, event);
  }
};

// -- 页面加载完成 --
const created = function() {
  this.input = this.$route.query.serach;
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
