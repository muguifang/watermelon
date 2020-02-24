<template>
  <div>
    <el-dialog
      title="添加轮播图"
      :visible.sync="dialogInsertLbt"
      :modal-append-to-body="false"
      @close="close"
    >
      <el-form :model="form">
        <el-form-item label="图片" :label-width="formLabelWidth">
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            multiple
            :limit="3"
            :on-exceed="handleExceed"
            :file-list="fileList"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "insertLbt",
  data() {
    return {
      dialogInsertLbt: false,
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: 120,
      form: {},
      fileList: [
        {
          name: "food.jpeg",
          url: "C:/Users/g/Desktop/毕设/111.jpg"
        },
        {
          name: "food2.jpeg",
          url: "C:/Users/g/Desktop/毕设/222.jpg"
        }
      ]
    };
  },
  methods: {
    close() {
      this.dialogInsertLbt = false;
      //回调父组件
      this.$emit("close", false);
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      );
    }
  },
  //父组件向子组件传参
  props: {
    flag: {
      type: Boolean,
      default: false
    }
  },
  //监听传过来的参数
  watch: {
    flag: {
      handler(newValue) {
        this.dialogInsertLbt = newValue;
      }
    }
  }
};
</script>
<style lang="scss" scoped></style>
