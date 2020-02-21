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
              >批量删除</el-button
            >
          </el-col>
        </el-row>
        <!-- 表格展示数据 -->
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="id" label="编号" width="80"> </el-table-column>
          <el-table-column prop="username" label="账号" width="150">
          </el-table-column>
          <el-table-column prop="password" label="密码" width="200">
          </el-table-column>
          <el-table-column prop="realname" label="姓名" width="150">
          </el-table-column>
          <el-table-column prop="phone" label="手机号" width="200">
          </el-table-column>
          <el-table-column prop="email" label="邮箱" width="250">
          </el-table-column>
          <el-table-column label="操作">
            <template>
              <el-button type="primary" size="mini" icon="el-icon-view"
                >查看</el-button
              >
              <el-button type="danger" size="mini" icon="el-icon-delete"
                >删除</el-button
              >
              <el-button type="success" size="mini" icon="el-icon-edit"
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
            :current-page="currentPage4"
            :page-sizes="[2, 5, 10, 15]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="50"
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
export default {
  name: "userIndex",
  inject: ["reload"],
  data() {
    return {
      tableData: [
        {
          id: "1",
          username: "wangxh",
          password: "123",
          realname: "王小虎",
          phone: "15133902212",
          email: "mus223@163.com"
        },
        {
          id: "2",
          username: "wangxh",
          password: "123",
          realname: "王小虎",
          phone: "15133902212",
          email: "mus223@163.com"
        },
        {
          id: "3",
          username: "wangxh",
          password: "123",
          realname: "王小虎",
          phone: "15133902212",
          email: "mus223@163.com"
        },
        {
          id: "4",
          username: "wangxh",
          password: "123",
          realname: "王小虎",
          phone: "15133902212",
          email: "mus223@163.com"
        },
        {
          id: "5",
          username: "wangxh",
          password: "123",
          realname: "王小虎",
          phone: "15133902212",
          email: "mus223@163.com"
        }
      ]
    };
  },
  methods: {
    goBack() {
      router.push("/index");
      this.reload();
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
