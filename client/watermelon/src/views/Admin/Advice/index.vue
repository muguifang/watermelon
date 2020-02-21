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
          <el-table-column prop="username" label="用户名称" width="200">
          </el-table-column>
          <el-table-column prop="phone" label="联系方式" width="200">
          </el-table-column>
          <el-table-column prop="content" label="建议信息" width="500">
          </el-table-column>
          <el-table-column prop="insertdate" label="发表时间" width="200">
          </el-table-column>
          <el-table-column label="操作">
            <template>
              <el-button type="danger" size="mini" icon="el-icon-delete"
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
  name: "typeIndex",
  inject: ["reload"],
  data() {
    return {
      tableData: [
        {
          id: "1",
          username: "xiaohong",
          phone: "13120908879",
          content: "网站写的真好",
          insertdate: "2020-02-20"
        },
        {
          id: "2",
          username: "xiaohong",
          phone: "13120908879",
          content: "网站写的真好",
          insertdate: "2020-02-20"
        },
        {
          id: "3",
          username: "xiaohong",
          phone: "13120908879",
          content: "网站写的真好",
          insertdate: "2020-02-20"
        },
        {
          id: "4",
          username: "xiaohong",
          phone: "13120908879",
          content: "网站写的真好",
          insertdate: "2020-02-20"
        },
        {
          id: "5",
          username: "xiaohong",
          phone: "13120908879",
          content: "网站写的真好",
          insertdate: "2020-02-20"
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
