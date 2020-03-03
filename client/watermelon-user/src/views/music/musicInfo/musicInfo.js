// -- 引入外部其他文件 --
import { getMusicById, collectMusic } from "@/api/music.js";
import { getComments, insertComment } from "@/api/comment.js";
import { base64Convert } from "@/utils/base64Util.js";
import { getCookie } from "@/utils/store.js";
// -- 名字 --

const name = "index";

// -- 数据 --

const data = function() {
  return {
    activeName: "first",
    form: {
      content: ""
    },
    //音乐信息
    musicInfo: {},
    //cookie存储的用户信息
    u_info: "",
    //评论信息
    comments: [],
    //抽屉标志
    drawer: false,
    //抽屉方向
    direction: "btt",
    //抽屉是否带遮罩
    modal: false
  };
};

// -- 方法 --

const methods = {
  //音乐播放器抽屉
  playMusic() {
    this.drawer = true;
  },
  //收藏方法
  collect(item) {
    const _u_i = getCookie("_u_i");
    if (_u_i != "") {
      this.u_info = JSON.parse(_u_i);
    } else {
      this.u_info = null;
    }
    //判断是否登录
    if (this.u_info == null || this.u_info == "") {
      this.$message("登录后可收藏该歌曲!");
    } else {
      //发送收藏请求
      const param = {
        //音乐id
        id: item.id,
        //用户id
        u_id: this.u_info
      };
      collectMusic(param).then(response => {
        const res = response.data;
        if (res.code === 200) {
          this.$message({
            message: res.data,
            type: "success"
          });
        } else {
          this.$message({
            message: res.data,
            type: "warning"
          });
        }
      });
    }
  },
  //评论
  submitForm() {
    const _u_i = getCookie("_u_i");
    if (_u_i != "") {
      this.u_info = JSON.parse(_u_i);
    } else {
      this.u_info = null;
    }
    //登录后可以评论
    if (this.u_info == null || this.u_info == "") {
      this.$message("登录后可评论该歌曲!");
    } else {
      const param = {
        u_id: this.u_info,
        content: this.form.content,
        m_id: parseInt(this.$route.query.id)
      };
      insertComment(param).then(response => {
        const res = response.data;
        if (res.code === 200) {
          this.form.content = "";
          this.getComment();
        }
      });
    }
  },
  //通过音乐id获取音乐详情
  getMusic() {
    getMusicById(this.$route.query.id).then(response => {
      const res = response.data;
      const music = res.data;
      music.musicphoto = URL.createObjectURL(base64Convert(music.musicphoto));
      this.musicInfo = res.data;
    });
  },
  //获取该音乐评论
  getComment() {
    getComments(this.$route.query.id).then(response => {
      const res = response.data;
      if (res.code === 200) {
        if (res.data != null) {
          const data = res.data;
          this.comments = [];
          for (let i = 0; i < data.length; i++) {
            const comment = {
              id: "",
              username: "",
              content: "",
              insertDate: ""
            };
            comment.id = data[i].id;
            comment.username = data[i].username;
            comment.content = data[i].tcontent;
            comment.insertDate = data[i].insertdate;
            this.comments.push(comment);
          }
        }
      }
    });
  },
  handleClick(tab, event) {
    console.log(tab, event);
  },
  goBack() {
    history.go(-1);
  }
};

// -- 页面加载完成 --
const created = function() {
  this.getComment();
  this.getMusic();
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
