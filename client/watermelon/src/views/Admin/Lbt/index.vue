<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <!-- 左上角的返回和页面标题 -->
        <el-page-header @back="goBack" content="首页轮播列表"></el-page-header>
        <!-- 第一行的新增或者搜索框 -->
        <el-row :gutter="16">
          <el-col :span="19">
            <div style="width: 100%;color:#fff;">
              1&nbsp;
            </div>
          </el-col>
          <el-col :span="3">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-delete"
              @click="deleteLbts(sels)"
              >批量删除</el-button
            >
          </el-col>
          <el-col :span="2">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-plus"
              @click="add()"
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
          <el-table-column prop="pic" label="图片" width="1000">
            <template slot-scope="scope">
              <img :src="scope.row.pic" />
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="deleteLbt(scope.$index, scope.row)"
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
            :page-sizes="[2, 3, 4]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="sumNum"
          >
          </el-pagination>
        </div>
        <!-- 新增弹窗 -->
        <el-dialog
          title="添加首页轮播图"
          :visible.sync="dialog_insertLbt"
          :append-to-body="true"
          width="30%"
        >
          <el-upload
            class="avatar-uploader"
            action="api/file/upload"
            name="file"
            :data="{ path: 'D:/img' }"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="cancelAdd()">取 消</el-button>
            <el-button type="primary" @click="addLbt()">确 定</el-button>
          </div>
        </el-dialog>
        <!-- 编辑弹窗 -->
        <el-dialog
          title="编辑首页轮播图"
          :close-on-click-modal="false"
          :close-on-press-escape="false"
          :show-close="false"
          :visible.sync="dialog_updateLbt"
          :append-to-body="true"
          width="30%"
        >
          <el-upload
            class="avatar-uploader"
            action="/api/file/upload"
            name="file"
            :data="{ path: 'D:/img' }"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="cancelUpdate()">取 消</el-button>
            <el-button type="primary" @click="updateLbt()">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import "../../../styles/config.scss";
import { addLbt } from "@/api/admin/lbt/addLbt.js";
import { getAllPhoto } from "@/api/admin/lbt/lbtList.js";
import { deleteLbt } from "@/api/admin/lbt/deleteLbt.js";
import { updateFile, deleteServerFile } from "@/api/file/file.js";
import { base64Convert } from "@/utils/base64Util.js";
export default {
  name: "zxIndex",
  inject: ["reload"],
  data() {
    return {
      //表格数据
      tableData: [],
      //新增弹窗标志
      dialog_insertLbt: false,
      //编辑弹窗标志
      dialog_updateLbt: false,
      //图片路径
      imageUrl: "",
      //数据总条数
      sumNum: 0,
      //当前页码
      currentPage: 1,
      //每页条数
      pageSize: 3,
      //多选框 选中的数据
      sels: [],
      //选中的id
      deleteIds: [],
      //表单中的数据
      dialogForm: {
        id: "",
        pic: "",
        insertdate: ""
      },
      fileName: ""
    };
  },
  methods: {
    //查询所有 【图片不显示】
    query() {
      getAllPhoto().then(response => {
        const data = response.data;
        this.tableData = [];
        if (data.code === 200) {
          const tableList = data.data;
          this.sumNum = tableList.length;
          for (let i = 0; i < tableList.length; i++) {
            const table = {
              id: "",
              pic: ""
            };
            table.id = tableList[i].id;
            let base = tableList[i].pic;
            table.pic = URL.createObjectURL(base64Convert(base));
            this.tableData.push(table);
          }
        }
      });
    },
    //打开新增弹窗
    add() {
      this.dialogForm.pic = "";
      this.dialog_insertLbt = true;
    },
    //新增轮播图
    addLbt() {
      if (this.fileName != "") {
        const param = {
          path: this.fileName
        };
        addLbt(param).then(response => {
          const data = response.data;
          if (data.code == 200) {
            this.dialog_insertLbt = false;
            this.$message({
              type: "success",
              message: "新增成功!"
            });
            this.query();
          }
        });
      } else {
        this.$message("请上传图片");
      }
    },
    //修改之查询
    update(index, row) {
      this.dialog_updateLbt = true;
      this.dialogForm.id = row.id;
      this.dialogForm.pic = row.pic;
      this.dialogForm.insertdate = row.insertdate;
      this.imageUrl = row.pic;
    },
    //修改之真正修改
    updateLbt() {
      const param = {
        id: this.dialogForm.id,
        name: this.fileName
      };
      updateFile(param).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.dialog_updateLbt = false;
          this.$message({
            message: "修改成功!",
            type: "success"
          });
          this.query();
        } else {
          this.$message("没有进行任何修改");
        }
      });
    },
    //修改的取消按钮取消上传文件时删除服务器图片
    cancelUpdate() {
      this.dialog_updateLbt = false;
      //删除文件
      if (this.fileName != "") {
        deleteServerFile(this.fileName).then(response => {
          const data = response.data;
          if (data.code === 200) {
            this.fileName = "";
            this.dialog_updateLbt = false;
            this.$message("已取消上传");
          }
        });
      } else {
        this.$message("已取消编辑");
      }
    },
    //新增的取消按钮
    cancelAdd() {
      this.dialog_insertLbt = false;
      if (this.fileName != "") {
        deleteServerFile(this.fileName).then(response => {
          const data = response.data;
          if (data.code === 200) {
            this.fileName = "";
            this.dialog_insertLbt = false;
            this.$message("已取消上传");
          }
        });
      } else {
        this.$message("已取消新增");
      }
    },
    //删除轮播图
    deleteLbt(index, row) {
      this.$confirm("确认将该图片从列表中删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          const ids = [];
          ids.push(row.id);
          deleteLbt(ids).then(response => {
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
    deleteLbts(sels) {
      if (sels.length > 0) {
        //将得到的数据中的id放到一个数组中
        sels.forEach(sel => {
          this.deleteIds.push(sel.id);
        });
        this.$confirm(
          "确认将选中的 " + sels.length + " 个轮播图从列表中删除?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        )
          .then(() => {
            deleteLbt(this.deleteIds).then(response => {
              const data = response.data;
              if (data.code == 200) {
                this.$message({
                  type: "success",
                  message: "批量删除成功!"
                });
                this.deleteIds = [];
                this.query();
              }
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消批量删除"
            });
            this.deleteIds = [];
            this.query();
          });
      } else {
        this.$message({
          message: "请选择要删除的首页轮播图",
          type: "warning"
        });
      }
    },
    //返回按钮的方法
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
    //图片上传后
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      this.fileName = file.response.data;
    },
    //图片上传前
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
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
img {
  width: 150px;
  height: 80px;
  margin: 0 auto;
}
.avatar-uploader {
  width: 178px;
  height: 178px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader:hover {
  border-color: #409eff;
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
</style>
