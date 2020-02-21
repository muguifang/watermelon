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
              >批量删除</el-button
            >
          </el-col>
          <el-col :span="2">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-plus"
              >新增</el-button
            >
          </el-col>
        </el-row>
        <!-- 表格展示数据 -->
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="id" label="编号" width="80"> </el-table-column>
          <el-table-column prop="title" label="标题" width="250">
          </el-table-column>
          <el-table-column prop="singWord" label="歌词" width="500">
          </el-table-column>
          <el-table-column prop="pic" label="图片" width="250">
          </el-table-column>
          <el-table-column label="操作">
            <template>
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
  name: "zxIndex",
  inject: ["reload"],
  data() {
    return {
      tableData: [
        {
          id: "1",
          title: "王小虎",
          singWord: "上海市",
          pic: "a图"
        },
        {
          id: "2",
          title: "王2虎",
          singWord: "上海市",
          pic: "ab图"
        },
        {
          id: "3",
          title: "王小虎",
          singWord: "上海市",
          pic: "c图"
        },
        {
          id: "4",
          title: "王小虎",
          singWord: "上海市",
          pic: "d图"
        },
        {
          id: "5",
          title: "王小虎",
          singWord: "上海市",
          pic: "e图"
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
