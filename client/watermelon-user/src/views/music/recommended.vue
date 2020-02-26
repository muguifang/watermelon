<template>
  <div>
    <audio :src="music" id="audio"></audio>
    <div class="audio-box bbb">
      <span class="time-font">
        {{
          second / 60 > 9 ? parseInt(second / 60) : "0" + parseInt(second / 60)
        }}:{{
          second % 60 >= 10
            ? parseInt(second % 60)
            : "0" + parseInt(second % 60)
        }}
      </span>
      <el-progress
        :percentage="progressing_audio"
        status="success"
        :stroke-width="4"
        style="margin-top:10px;margin-left:30px;float:left;width:414px;"
      ></el-progress>
      <img
        :src="audioPlayShow ? imgA : imgB"
        class="rong-circle-control"
        @click="controlAudio()"
      />
    </div>
  </div>
</template>
<script>
import tryaudio from "@/components/test/radio/try.mp3";
import audioStart from "@/components/publicEng/images/play.png";
import audioStop from "@/components/publicEng/images/pause.png";
export default {
  name: "",
  props: {
    params: {
      type: Object,
      default: () => ({
        message: "hello"
      })
    }
  },
  data() {
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
  },
  methods: {
    controlAudio: function() {
      if (!this.params) {
        return false;
      } else {
        console.log(this.params);
        this.music = this.params.url;
        let audio0 = document.getElementById("audio");
        let leng = audio0.duration;
        console.log(audio0.duratuion);
        if (this.audioPlayShow) {
          audio0.play();

          this.audioPlayShow = false;
          this.timer = setInterval(() => {
            console.log(1 / leng);
            this.progressing_audio =
              (this.progressing_audio / 100 + 0.1 / leng) * 100;
            this.second = this.second + 0.1;
            if (this.progressing_audio >= 100) {
              clearInterval(this.timer);
              this.progressing_audio = 0;
              this.second = 0;
            }
            console.log(this.progressing_audio);
          }, 100);
        } else {
          audio0.pause();
          this.audioPlayShow = true;
          clearInterval(this.timer);
        }
      }
    }
  }
};
</script>

<style>
.rong-circle-control {
  width: 56px;
  height: 56px;
  margin-left: 46px;
  float: left;
  /*background-color: red;*/
  border-radius: 50%;
}
.time-font {
  margin-top: 10px;
  font-size: 24px;
  font-family: PingFangSC-Regular;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 34px;
  float: left;
  margin-left: 0px;
}
.audio-box {
  height: 56px;
  width: 610px;
  padding: 20px 30px;
  margin: 40px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 14px 30px 0px rgba(154, 159, 182, 0.1);
  border-radius: 20px;
}
</style>
