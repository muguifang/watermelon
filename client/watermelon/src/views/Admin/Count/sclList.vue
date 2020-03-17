<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <!-- 左上角的返回和页面标题 -->
        <el-page-header @back="goBack" content="按收藏量排名"></el-page-header>
        <el-row :gutter="16">
          <el-col :span="6">
            <div class="label-wrap title">
              <label for="">名称：</label>
              <div class="warp-content">
                <el-input
                  v-model="input"
                  placeholder="请输入音乐名称"
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
        </el-row>
        <!-- 表格展示数据 -->
        <el-table :data="pageData" border style="width: 100%">
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column label="排名" width="80">
            <template slot-scope="scope">
              <span>{{ scope.$index + 1 }}</span>
            </template>
          </el-table-column>
          <!-- <el-table-column prop="id" label="编号" width="80"> </el-table-column> -->
          <el-table-column prop="musicname" label="音乐名称" width="500">
          </el-table-column>
          <el-table-column prop="musicphoto" label="图片" width="250">
            <template slot-scope="scope">
              <img :src="scope.row.musicphoto" />
            </template>
          </el-table-column>
          <el-table-column prop="collectnum" label="收藏量"> </el-table-column>
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
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import "../../../styles/config.scss";
import { getAllMusicByCollect } from "@/api/music/sclList.js";
import { base64Convert } from "@/utils/base64Util.js";
export default {
  name: "typeIndex",
  inject: ["reload"],
  data() {
    return {
      tableData: [],
      input: "",
      //数据总条数
      sumNum: 0,
      //当前页码
      currentPage: 1,
      //每页条数
      pageSize: 4
    };
  },
  methods: {
    //点击查询按钮
    query() {
      const param = {
        musicname: this.input
      };
      getAllMusicByCollect(param).then(response => {
        const data = response.data;
        this.tableData = [];
        if (data.code === 200) {
          const tableList = data.data;
          this.sumNum = tableList.length;
          for (let i = 0; i < tableList.length; i++) {
            const table = {
              id: "",
              musicname: "",
              musicphoto: "",
              collectnum: ""
            };
            table.id = tableList[i].mId;
            table.musicname = tableList[i].mName;
            // table.musicphoto = tableList[i].pic;
            let base = tableList[i].pic;
            table.musicphoto = URL.createObjectURL(base64Convert(base));
            table.collectnum = tableList[i].num;
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
img {
  width: 150px;
  height: 80px;
  margin: 0 auto;
}
</style>
