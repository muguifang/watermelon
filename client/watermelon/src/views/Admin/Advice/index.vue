<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <!-- 左上角的返回和页面标题 -->
        <el-page-header @back="goBack" content="建议信息列表"></el-page-header>
        <!-- 第一行的新增或者搜索框 -->
        <el-row :gutter="16">
          <el-col :span="6">
            <div class="label-wrap title">
              <label for="">电话：</label>
              <div class="warp-content">
                <el-input
                  v-model="input"
                  placeholder="请输入联系方式"
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
              @click="deleteAdvices(sels)"
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
          <el-table-column prop="id" label="编号" width="80"> </el-table-column>
          <el-table-column prop="username" label="用户名称" width="200">
          </el-table-column>
          <el-table-column prop="phone" label="联系方式" width="200">
          </el-table-column>
          <el-table-column prop="content" label="建议信息" width="500">
          </el-table-column>
          <el-table-column prop="insertdate" label="发表时间" width="200">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="delete (scope.$index, scope.row)"
                >删除</el-button
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
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import "../../../styles/config.scss";
import { getAdvice } from "@/api/admin/advice/adviceList.js";
import { deleteAdvice } from "@/api/admin/advice/deleteAdvice.js";
export default {
  name: "typeIndex",
  inject: ["reload"],
  data() {
    return {
      //表格数据
      tableData: [],
      //搜索框
      input: "",
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
      deleteNames: []
    };
  },
  methods: {
    //查询按钮
    query() {
      const param = {
        phone: this.input
      };
      getAdvice(param).then(response => {
        const data = response.data;
        this.tableData = [];
        if (data.code === 200) {
          const tableList = data.data;
          this.sumNum = tableList.length;
          for (let i = 0; i < tableList.length; i++) {
            const table = {
              id: "",
              username: "",
              phone: "",
              content: "",
              insertdate: ""
            };
            table.id = tableList[i].id;
            table.username = tableList[i].username;
            table.phone = tableList[i].phone;
            table.content = tableList[i].content;
            table.insertdate = tableList[i].insertdate;
            this.tableData.push(table);
          }
        }
      });
    },
    //删除按钮
    delete(index, row) {
      this.$confirm(
        "确认将【" + row.username + "】的建议从列表中删除?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          deleteAdvice(row.id).then(response => {
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
    deleteAdvices(sels) {
      if (sels.length > 0) {
        //将得到的数据中的id放到一个数组中
        sels.forEach(sel => {
          this.deleteIds.push(sel.id);
          this.deleteNames.push(sel.username);
        });
        this.$confirm(
          "确认将【" + this.deleteNames + "】的建议从列表中删除?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        )
          .then(() => {
            deleteAdvice(this.deleteIds).then(response => {
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
          message: "请选择要删除的用户",
          type: "warning"
        });
      }
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
</style>
