// -- 引入外部其他文件 --
import tryaudio from "@/components/test/radio/try.mp3";
import audioStart from "@/components/publicEng/images/play.png";
import audioStop from "@/components/publicEng/images/pause.png";
// -- 名字 --

const name = "index";

const props = {
  params: {
    type: Object,
    default: () => ({
      message: "hello"
    })
  }
};

// -- 数据 --

const data = function() {
  return {
    progressing_audio: 0,
    music: tryaudio,
    playFlag: false,
    audioPlayShow: true,
    timer: "",
    "length-music": 0,
    second: 0,
    imgA: audioStart,
    imgB: audioStop
  };
};

// -- 方法 --

const methods = {
  controlAudio: function() {
    if (!this.params) {
      return false;
    } else {
      this.music = this.params.url;
      let audio0 = document.getElementById("audio");
      let leng = audio0.duration;
      if (this.audioPlayShow) {
        audio0.play();

        this.audioPlayShow = false;
        this.timer = setInterval(() => {
          this.progressing_audio =
            (this.progressing_audio / 100 + 0.1 / leng) * 100;
          this.second = this.second + 0.1;
          if (this.progressing_audio >= 100) {
            clearInterval(this.timer);
            this.progressing_audio = 0;
            this.second = 0;
          }
        }, 100);
      } else {
        audio0.pause();
        this.audioPlayShow = true;
        clearInterval(this.timer);
      }
    }
  }
};

// -- 页面加载完成 --
const created = function() {};

// -- 自动计算属性 --

const computed = {};

// -- 监听 --

const watch = {};

// == EXPORT ==

export default {
  name: name,

  props: props,

  data: data,

  computed: computed,

  watch: watch,

  methods: methods,

  created
};
