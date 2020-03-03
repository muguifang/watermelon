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
    //控制播放和暂停
    number: true
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
  //播放音乐
  playMusic(row) {
    this.musicUrl = row.musicPlay;
    const param = {
      path: this.musicUrl
    };
    returnBase64(param).then(response => {
      const data = response.data;
      if (data.code === 200) {
        this.musicUrl = URL.createObjectURL(base64Convert(data.data));
      } else {
        this.$message({
          type: "error",
          message: "获取播放源失败"
        });
      }
    });
    //获取audio元素
    const music = this.$refs.music;
    //控制播放和暂停
    if (this.number == true) {
      this.number = false;
      music.play();
    } else {
      this.number = true;
      music.pause();
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
