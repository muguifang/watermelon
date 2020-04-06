<template>
  <div id="search-wrap">
    <div style="margin-top: 15px;">
      <el-input
        placeholder="请输入音乐名称"
        v-model="input"
        class="input-with-select"
      >
        <el-button
          slot="append"
          icon="el-icon-search"
          @click="query()"
        ></el-button>
      </el-input>
    </div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="歌曲" name="first">
        <template>
          <el-table :data="tableData" style="width: 99%;margin:0 auto;">
            <el-table-column label="音乐名称" width="580">
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{ scope.row.musicName }}</span>
                <!-- style="position: relative;right: -276px;cursor: pointer;" -->
                <i
                  :id="'playIcon' + scope.$index"
                  class="el-icon-video-play icon-position"
                  @click="playMusic(scope.row, 'playIcon' + scope.$index)"
                ></i>
              </template>
            </el-table-column>
            <el-table-column prop="playNum" label="播放量" width="180">
            </el-table-column>
            <el-table-column prop="typeName" label="音乐类别" width="180">
            </el-table-column>
            <el-table-column
              prop="insertDate"
              label="发布时间"
            ></el-table-column>
          </el-table>
        </template>
      </el-tab-pane>
    </el-tabs>
    <audio id="music" :src="musicUrl" controls autoplay v-show="false" />
  </div>
</template>
<script src="./searchMusic.js"></script>
<style lang="scss" scoped>
#search-wrap {
  width: 70%;
  min-height: 79vh;
  margin: 0 auto;
  border: 1px solid #dcd9d9;
  margin-top: -42px;
}
.el-input {
  width: 30%;
  margin-left: 330px;
}
.icon-position {
  left: 430px;
  cursor: pointer;
  display: block;
  position: absolute;
  top: 10px;
}
</style>
