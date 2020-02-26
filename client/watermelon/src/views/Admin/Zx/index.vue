<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <!-- 左上角的返回和页面标题 -->
        <el-page-header @back="goBack" content="音乐资讯列表"></el-page-header>
        <!-- 第一行的新增或者搜索框 -->
        <el-row :gutter="16">
          <el-col :span="6">
            <div class="label-wrap title">
              <label for="">标题：</label>
              <div class="warp-content">
                <el-input
                  v-model="input"
                  placeholder="请输入标题名称"
                  style="width: 100%;"
                ></el-input>
              </div>
            </div>
          </el-col>
          <el-col :span="2">
            <el-button
              type="primary"
              style="width: 100%;margin: 17px auto;"
              icon="el-icon-search"
              @click="query()"
              >查询</el-button
            >
          </el-col>
          <el-col :span="11">
            <div style="width: 100%;color:#fff;">
              1&nbsp;
            </div>
          </el-col>
          <el-col :span="3">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-delete"
              @click="deleteZxs(sels)"
              >批量删除</el-button
            >
          </el-col>
          <el-col :span="2">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-plus"
              @click="insertZx()"
              >新增</el-button
            >
          </el-col>
        </el-row>
        <!-- 表格展示数据 -->
        <el-table
          :data="pageData"
          border
          style="width: 100%"
          @selection-change="selsChange"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column label="序号" width="80">
            <template slot-scope="scope">
              <span>{{ scope.$index + 1 }}</span>
            </template>
          </el-table-column>
          <!-- <el-table-column prop="id" label="编号" width="80"> </el-table-column> -->
          <el-table-column prop="title" label="标题" width="250">
          </el-table-column>
          <el-table-column prop="content" label="内容" width="500">
          </el-table-column>
          <el-table-column prop="pic" label="图片" width="250">
            <template slot-scope="scope">
              <img style="width: 80px; height: 80px;" :src="scope.row.pic" />
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="deleteZx(scope.$index, scope.row)"
                >删除</el-button
              >
              <el-button
                type="success"
                size="mini"
                icon="el-icon-edit"
                @click="update(scope.$index, scope.row)"
                >编辑</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <div class="block">
          <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[4, 5, 6, 7]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="sumNum"
          >
          </el-pagination>
        </div>
        <!-- 添加音乐资讯弹窗 -->
        <el-dialog
          title="添加音乐资讯"
          :visible.sync="dialog_addZx"
          :append-to-body="true"
          width="30%"
        >
          <el-form
            :model="dialogForm"
            status-icon
            :rules="rules"
            ref="dialogForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item
              prop="title"
              label="标题:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.title"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="content"
              label="内容:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.content"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="pic"
              label="图片:"
              :label-width="formLabelWidth + ''"
            >
              <!-- <el-input
                style="width: 250px;"
                v-model="dialogForm.pic"
                autocomplete="off"
              ></el-input> -->
              <el-upload
  class="avatar-uploader"
  action="/api/file/upload"
  name="lbt"
  :data='{path: "D:/img"}'
  :show-file-list="false"
  :on-success="handleAvatarSuccess"
  :before-upload="beforeAvatarUpload">
  <img v-if="imageUrl" :src="imageUrl" class="avatar">
  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="resetForm('dialogForm')">重 置</el-button>
            <el-button type="primary" @click="addZx()">确 定</el-button>
          </div>
        </el-dialog>
        <!-- 修改音乐资讯弹窗 -->
        <el-dialog
          title="修改音乐资讯"
          :visible.sync="dialog_updateZx"
          :append-to-body="true"
          width="30%"
        >
          <el-form
            :model="dialogForm"
            status-icon
            :rules="rules"
            ref="dialogForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item
              prop="title"
              label="标题:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.title"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="content"
              label="内容:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.content"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="pic"
              label="图片:"
              :label-width="formLabelWidth + ''"
            >
              <!-- <el-input
                style="width: 250px;"
                v-model="dialogForm.pic"
                autocomplete="off"
              ></el-input> -->
              <el-upload
  class="avatar-uploader"
  action="/api/file/upload"
  name="lbt"
  :data='{path: "D:/img"}'
  :show-file-list="false"
  :on-success="handleAvatarSuccess"
  :before-upload="beforeAvatarUpload">
  <img v-if="imageUrl" :src="imageUrl" class="avatar">
  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="closeInfo()">取 消</el-button>
            <el-button type="primary" @click="updateZx()">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import "../../../styles/config.scss";
import { getZxList } from "@/api/admin/zx/zxList.js";
import { deleteZx } from "@/api/admin/zx/deleteZx.js";
import { updateZx } from "@/api/admin/zx/updateZx.js";
import { addZx } from "@/api/admin/zx/addZx.js";
import { base64ImgtoFile } from "@/utils/base64Util.js";
import { deleteServerFile } from "@/api/file/file.js";
export default {
  name: "zxIndex",
  inject: ["reload"],
  data() {
    return {
      fileName: "",
      //新增资讯图片路径
      imageUrl: "",
      //表格数据
      tableData: [],
      //搜索框
      input: "",
      //标签宽度
      formLabelWidth: 120,
      //控制修改弹窗的标志
      dialog_updateZx: false,
      //控制新增弹窗
      dialog_addZx: false,
      //数据总条数
      sumNum: 0,
      //当前页码
      currentPage: 1,
      //每页条数
      pageSize: 5,
      //选中的对象
      sels: [],
      //选中的id
      deleteIds: [],
      //选中的名字
      deleteNames: [],
      //表单数据
      dialogForm: {
        id: "",
        title: "",
        content: "",
        pic: "",
        insertdate: ""
      },
      rules: {
        title: [
          {
            required: true,
            message: "请输入资讯标题",
            trigger: "blur"
          }
        ],
        content: [
          {
            required: true,
            message: "请输入资讯内容",
            trigger: "blur"
          }
        ],
        pic: [
          {
            required: true,
            message: "请输入资讯图片",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    //查询按钮
    query() {
      const param = {
        title: this.input
      };
      getZxList(param).then(response => {
        const data = response.data;
        this.tableData = [];
        if (data.code === 200) {
          const tableList = data.data;
          this.sumNum = tableList.length;
          for (let i = 0; i < tableList.length; i++) {
            const table = {
              id: "",
              title: "",
              content: "",
              pic: "",
              insertdate: ""
            };
            table.id = tableList[i].id;
            table.title = tableList[i].title;
            table.content = tableList[i].content;
            table.pic = URL.createObjectURL(base64ImgtoFile(tableList[i].pic));
            table.insertdate = tableList[i].insertdate;
            this.tableData.push(table);
          }
        }
      });
    },
    //新增弹窗
    insertZx() {
      this.reset();
      this.dialog_addZx = true;
    },
    //新增确认按钮
    addZx() {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.dialog_addZx = false;
          addZx(this.dialogForm).then(response => {
            const data = response.data;
            if (data.code == 200) {
              this.imageUrl = "";
              this.$message({
                type: "success",
                message: "新增成功!"
              });
              this.query();
            }
          });
        } else {
          console.log("error submit!");
          return false;
        }
      });
    },
    //修改之查询
    update(index, row) {
      this.dialog_updateZx = true;
      this.dialogForm.id = row.id;
      this.dialogForm.title = row.title;
      this.dialogForm.content = row.content;
      this.dialogForm.pic = row.pic;
      this.dialogForm.insertdate = row.insertdate;
      this.imageUrl = row.pic;
    },
    //修改之真正修改
    updateZx() {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.dialog_updateZx = false;
          if(this.dialogForm.pic == this.imageUrl){
            this.dialogForm.pic = null;
          }else{
            this.dialogForm.pic = this.fileName;
          }
          updateZx(this.dialogForm).then(response => {
            const data = response.data;
            if (data.code == 200) {
              this.$message({
                type: "success",
                message: "修改成功!"
              });
              this.query();
            }
          });
        } else {
          console.log("error");
          return false;
        }
      });
    },
    //删除音乐类别
    deleteZx(index, row) {
      this.$confirm("确认将【" + row.title + "】从列表中删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          const ids = [];
          ids.push(row.id);
          deleteZx(ids).then(response => {
            const data = response.data;
            if (data.code == 200) {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              this.query();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    //将选中的数据存入数组
    selsChange(sels) {
      this.sels = sels;
    },
    //批量删除
    deleteZxs(sels) {
      if (sels.length > 0) {
        //将得到的数据中的id放到一个数组中
        sels.forEach(sel => {
          this.deleteIds.push(sel.id);
          this.deleteNames.push(sel.title);
        });
        this.$confirm(
          "确认将【" + this.deleteNames + "】从资讯列表中删除?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        )
          .then(() => {
            deleteZx(this.deleteIds).then(response => {
              const data = response.data;
              if (data.code == 200) {
                this.$message({
                  type: "success",
                  message: "删除成功!"
                });
                this.deleteIds = [];
                this.deleteNames = [];
                this.query();
              }
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
            this.deleteIds = [];
            this.deleteNames = [];
            this.query();
          });
      } else {
        this.$message({
          message: "请选择要删除的资讯",
          type: "warning"
        });
      }
    },
    //取消按钮
    closeInfo() {
      //删除文件
      if(this.fileName != ""){
        deleteServerFile(this.fileName).then(response => {
          const data = response.data;
        if (data.code === 200) {
          this.fileName = "";
      this.dialog_updateZx = false;
          this.$message("以取消上传");
        }
      });
      }
      this.query();
    },
    // 新增前清空内容
    reset() {
      this.dialogForm.id = "";
      this.dialogForm.title = "";
      this.dialogForm.content = "";
      this.dialogForm.pic = "";
    },
    //重置按钮
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //返回时刷新按钮
    goBack() {
      router.push("/index");
      this.reload();
    },
    //改变每页条数
    handleSizeChange(val) {
      this.pageSize = val;
    },
    //改变当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //文件上传成功的钩子
    handleAvatarSuccess(res, file) {
      this.fileName = file.response.data;
        this.imageUrl = URL.createObjectURL(file.raw);
      },
    //文件上传之前的钩子
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
  },
  //点击进来的时候查看那全部数据
  created: function() {
    this.query();
  },
  computed: {
    //自动计算页面数据展示
    pageData: function() {
      return this.tableData.slice(
        (this.currentPage - 1) * this.pageSize,
        this.currentPage * this.pageSize
      );
    }
  }
};
</script>
<style lang="scss" scoped>
#main-wrap {
  height: 100vh;
}
.main-content {
  width: 100%;
  height: 100%;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
.content {
  width: 100%;
  height: 100%;
  padding: 30px 30px 0 30px;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  text-align: left;
}
.label-wrap {
  margin: 20px auto;
  &.title {
    @include labelDom(left, 60, 40);
  }
}
//编辑弹窗 内容居中并且底部分隔
.el-form-item {
  margin-left: 50px;
  margin-bottom: 20px;
}

//编辑弹窗的按钮
div.dialog-footer {
  text-align: center;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
