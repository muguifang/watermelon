<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <!-- 左上角的返回和页面标题 -->
        <el-page-header @back="goBack" content="音乐类别列表"></el-page-header>
        <!-- 第一行的新增或者搜索框 -->
        <el-row :gutter="16">
          <el-col :span="7">
            <div class="label-wrap title">
              <label for="">名称：</label>
              <div class="warp-content">
                <el-input
                  v-model="input"
                  placeholder="请输入类别名称"
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
          <el-col :span="10">
            <div style="width: 100%;color:#fff;">
              1&nbsp;
            </div>
          </el-col>
          <el-col :span="3">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-delete"
              @click="deleteTypes(sels)"
              >批量删除</el-button
            >
          </el-col>
          <el-col :span="2">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-plus"
              @click="insertType()"
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
          <el-table-column prop="typename" label="音乐类别名称" width="1000">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="deleteType(scope.$index, scope.row)"
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
        <!-- 修改音乐类别弹窗 -->
        <el-dialog
          title="修改音乐类别"
          :visible.sync="dialog_updateType"
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
              prop="typename"
              label="类别名称:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.typename"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="closeInfo()">取 消</el-button>
            <el-button type="primary" @click="updateType()">确 定</el-button>
          </div>
        </el-dialog>
        <!-- 添加音乐类别弹窗 -->
        <el-dialog
          title="添加音乐类别"
          :visible.sync="dialog_addType"
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
              prop="typename"
              label="类别名称:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.typename"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="resetForm('dialogForm')">重 置</el-button>
            <el-button type="primary" @click="addType()">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import "../../../styles/config.scss";
import { getMusicType } from "@/api/music/typeList.js";
import { deleteType } from "@/api/music/deleteType.js";
import { updateType } from "@/api/music/updateType.js";
import { addType } from "@/api/music/addType.js";
export default {
  name: "typeIndex",
  inject: ["reload"],
  data() {
    return {
      //表格数据
      tableData: [],
      //搜索框
      input: "",
      //标签宽度
      formLabelWidth: 120,
      //控制修改弹窗的标志
      dialog_updateType: false,
      //控制新增弹窗
      dialog_addType: false,
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
        typename: ""
      },
      rules: {
        typename: [
          {
            required: true,
            message: "请输入音乐类别名称",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    //点击查询按钮时查询
    query() {
      const param = {
        typename: this.input
      };
      getMusicType(param).then(response => {
        const data = response.data;
        this.tableData = [];
        if (data.code === 200) {
          const tableList = data.data;
          this.sumNum = tableList.length;
          for (let i = 0; i < tableList.length; i++) {
            const table = {
              id: "",
              typename: ""
            };
            table.id = tableList[i].id;
            table.typename = tableList[i].typename;
            this.tableData.push(table);
          }
        }
      });
    },
    //新增按钮
    insertType() {
      this.reset();
      this.dialog_addType = true;
    },
    addType() {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.dialog_addType = false;
          addType(this.dialogForm).then(response => {
            const data = response.data;
            if (data.code == 200) {
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
      this.dialog_updateType = true;
      this.dialogForm.id = row.id;
      this.dialogForm.typename = row.typename;
    },
    //修改之真正修改
    updateType() {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.dialog_updateType = false;
          updateType(this.dialogForm).then(response => {
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
    deleteType(index, row) {
      this.$confirm("确认将【" + row.typename + "】从列表中删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          const ids = [];
          ids.push(row.id);
          deleteType(ids).then(response => {
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
    deleteTypes(sels) {
      if (sels.length > 0) {
        //将得到的数据中的id放到一个数组中
        sels.forEach(sel => {
          this.deleteIds.push(sel.id);
          this.deleteNames.push(sel.typename);
        });
        this.$confirm(
          "确认将【" + this.deleteNames + "】从音乐类别列表中删除?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        )
          .then(() => {
            deleteType(this.deleteIds).then(response => {
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
            this.query();
          });
      } else {
        this.$message({
          message: "请选择要删除的音乐类别",
          type: "warning"
        });
      }
    },
    //取消按钮
    closeInfo() {
      this.dialog_updateType = false;
      this.$message({
        type: "info",
        message: "已取消编辑"
      });
      this.query();
    },
    // 新增前清空内容
    reset() {
      this.dialogForm.id = "";
      this.dialogForm.typename = "";
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
}

//编辑弹窗的按钮
div.dialog-footer {
  text-align: center;
}
</style>
