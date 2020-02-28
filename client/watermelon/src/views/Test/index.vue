<template>
  <div>
    <el-upload
  class="upload-demo"
  ref="uploadMV"
  action="/api/file/upload"
  name="file"
  :data="{path: 'D:/music'}"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :file-list="fileList"
  :auto-upload="false">
  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUploadMV">上传到服务器</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload>
<el-upload
  class="upload-demo"
  ref="uploadMusic"
  action="/api/file/upload"
  name="file"
  :data="{path: 'D:/music'}"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :file-list="fileList"
  :auto-upload="false">
  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUploadMusic">上传到服务器</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        fileList: []
      };
    },
    methods: {
      submitUploadMV() {
        this.$refs.uploadMV.submit();
      },
      submitUploadMusic() {
        this.$refs.uploadMusic.submit();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      }
    }
  }
</script>