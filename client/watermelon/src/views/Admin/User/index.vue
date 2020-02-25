<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <!-- 左上角的返回和页面标题 -->
        <el-page-header @back="goBack" content="用户列表"></el-page-header>
        <!-- 第一行的新增或者搜索框 -->
        <el-row :gutter="16">
          <el-col :span="6">
            <div class="label-wrap title">
              <label for="">账号：</label>
              <div class="warp-content">
                <el-input
                  v-model="input"
                  placeholder="请输入账号"
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
          <el-col :span="13">
            <div style="width: 100%;color:#fff;">
              1&nbsp;
            </div>
          </el-col>
          <el-col :span="3">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-delete"
              @click="deleteUsers(sels)"
              >批量删除</el-button
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
          <el-table-column prop="username" label="账号" width="200">
          </el-table-column>
          <!-- <el-table-column prop="password" label="密码" width="200">
          </el-table-column> -->
          <el-table-column prop="realname" label="姓名" width="200">
          </el-table-column>
          <el-table-column prop="phone" label="手机号" width="250">
          </el-table-column>
          <el-table-column prop="email" label="邮箱" width="300">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <!-- <el-button type="primary" size="mini" icon="el-icon-view"
                >查看</el-button
              > -->
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="deleteUser(scope.$index, scope.row)"
                >删除</el-button
              >
              <el-button
                type="success"
                size="mini"
                icon="el-icon-edit"
                @click="resetPwd(scope.$index, scope.row)"
                >重置密码</el-button
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
        <!-- 重置密码弹窗 -->
        <!-- <el-dialog
          title="重置密码"
          :visible.sync="dialog_updatetUser"
          :append-to-body="true"
          width="30%"
        >
          <el-form> -->
        <!-- <el-form-item label="账号:" :label-width="formLabelWidth">
              <el-input
                style="width: 250px;"
                v-model="dialogForm.username"
                autocomplete="off"
              ></el-input>
            </el-form-item> -->
        <!-- <el-form-item label="新  密 码:" :label-width="formLabelWidth">
              <el-input
                type="password"
                style="width: 250px;"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="确认密码:" :label-width="formLabelWidth">
              <el-input
                type="password"
                style="width: 250px;"
                autocomplete="off"
              ></el-input>
            </el-form-item> -->
        <!-- <el-form-item label="姓名:" :label-width="formLabelWidth">
              <el-input
                style="width: 250px;"
                v-model="dialogForm.realname"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="电话:" :label-width="formLabelWidth">
              <el-input
                style="width: 250px;"
                v-model="dialogForm.phone"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="邮箱:" :label-width="formLabelWidth">
              <el-input
                style="width: 250px;"
                v-model="dialogForm.email"
                autocomplete="off"
              ></el-input>
            </el-form-item> -->
        <!-- </el-form> -->
        <!-- <div slot="footer" class="dialog-footer">
            <el-button @click="dialog_updatetUser = false">取 消</el-button>
            <el-button type="primary" @click="updateUser()">确 定</el-button>
          </div>
        </el-dialog> -->
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import "../../../styles/config.scss";
import "../../../styles/elementui.scss";
import { getUsers } from "@/api/user/userList.js";
import { deleteUser } from "@/api/user/deleteUser.js";
import { updatePassword } from "@/api/user/login.js";
export default {
  name: "userIndex",
  inject: ["reload"],
  data() {
    return {
      tableData: [],
      //搜索框
      input: "",
      dialog_updatetUser: false,
      //标签宽度
      formLabelWidth: 120,
      //数据总条数
      sumNum: 0,
      //当前页码
      currentPage: 1,
      //每页条数
      pageSize: 5,
      dialogForm: {
        id: "",
        username: "",
        password: "",
        realname: "",
        phone: "",
        email: ""
      },
      //选中的对象
      sels: [],
      //选中的id
      deleteIds: [],
      //选中的名字
      deleteNames: []
    };
  },
  methods: {
    //点击查看按钮查看单条数据
    // update() {
    //   this.dialog_updatetUser = true;
    // this.dialogForm.id = row.id;
    // this.dialogForm.username = row.username;
    // this.dialogForm.password = row.password;
    // this.dialogForm.realname = row.realname;
    // this.dialogForm.phone = row.phone;
    // this.dialogForm.email = row.email;
    //查询所有员工
    // this.manageList = [];
    // getEmps().then(response => {
    //   const data = response.data.data;
    //   for (let i = 0; i < data.length; i++) {
    //     const marage = {
    //       value: "",
    //       label: ""
    //     };
    //     marage.value = data[i].id;
    //     marage.label = data[i].name;
    //     this.manageList.push(marage);
    //   }
    // });
    // },
    //重置密码
    resetPwd(index, row) {
      this.$confirm("将该用户密码设置为【123asd】, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          const param = {
            oldPassword: row.password,
            newPassword: "123asd",
            confirmPassword: "123asd"
          };
          updatePassword(param).then(response => {
            const data = response.data;
            console.log(data);
            if (data.code === 200) {
              this.$message({
                message: "重置密码成功",
                type: "success"
              });
            } else {
              this.$message({
                message: data.msg,
                type: "warning"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消重置密码"
          });
        });
    },
    //删除用户
    deleteUser(index, row) {
      this.$confirm("确认将【" + row.realname + "】从用户表中删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          const ids = [];
          ids.push(row.id);
          deleteUser(ids).then(response => {
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
    deleteUsers(sels) {
      if (sels.length > 0) {
        //将得到的数据中的id放到一个数组中
        sels.forEach(sel => {
          this.deleteIds.push(sel.id);
          this.deleteNames.push(sel.realname);
        });
        this.$confirm(
          "确认将【" + this.deleteNames + "】从员工列表中删除?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        )
          .then(() => {
            deleteUser(this.deleteIds).then(response => {
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
          message: "请选择要删除的用户",
          type: "warning"
        });
      }
    },
    //点击查询按钮查询数据
    query() {
      getUsers(this.input).then(response => {
        const data = response.data;
        this.tableData = [];
        if (data.code === 200) {
          const tableList = data.data;
          this.sumNum = tableList.length;
          for (let i = 0; i < tableList.length; i++) {
            const table = {
              id: "",
              username: "",
              password: "",
              realname: "",
              phone: "",
              email: ""
            };
            table.id = tableList[i].id;
            table.username = tableList[i].username;
            table.password = tableList[i].password;
            table.realname = tableList[i].realname;
            table.phone = tableList[i].phone;
            table.email = tableList[i].email;
            this.tableData.push(table);
          }
        }
      });
    },
    //返回后刷新
    goBack() {
      router.push("/index");
      this.reload();
    },
    //改变每页条数
    handleSizeChange(val) {
      this.pageSize = val;
    },
    //改变当前页数
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
</style>
