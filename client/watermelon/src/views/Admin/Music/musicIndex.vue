<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <!-- 左上角的返回和页面标题 -->
        <el-page-header @back="goBack" content="音乐列表"></el-page-header>
        <!-- 第一行的新增或者搜索框 -->
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
              @click="deleteMusics(sels)"
              >批量删除</el-button
            >
          </el-col>
          <el-col :span="2">
            <el-button
              type="primary"
              style="width: 100%;float:right;margin: 17px auto;"
              icon="el-icon-plus"
              @click="insertMusic()"
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
          <el-table-column prop="musicname" label="音乐名称" width="200">
          </el-table-column>
          <el-table-column prop="musicphoto" label="音乐图片" width="200">
          </el-table-column>
          <el-table-column prop="musicplay" label="音乐播放" width="150">
          </el-table-column>
          <el-table-column prop="mvplay" label="MV播放" width="200">
          </el-table-column>
          <el-table-column prop="recommendStr" label="推荐星级" width="100">
          </el-table-column>
          <el-table-column prop="statusStr" label="状态" width="100">
          </el-table-column>
          <el-table-column prop="typename" label="音乐类别" width="100">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="deleteMusic(scope.$index, scope.row)"
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
        <!-- 添加音乐弹窗 -->
        <el-dialog
          title="添加音乐"
          :visible.sync="dialog_addMusic"
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
              prop="musicname"
              label="音乐名称:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.musicname"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="musicphoto"
              label="音乐图片:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.musicphoto"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="musicplay"
              label="音乐播放:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.musicplay"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="mvplay"
              label="MV 播放:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.mvplay"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="recommend"
              label="推荐星级:"
              :label-width="formLabelWidth + ''"
            >
              <el-select
                v-model="dialogForm.recommend"
                placeholder="请选择推荐星级"
              >
                <el-option label="一星" value="1"></el-option>
                <el-option label="二星" value="2"></el-option>
                <el-option label="三星" value="3"></el-option>
                <el-option label="四星" value="4"></el-option>
                <el-option label="五星" value="5"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              prop="status"
              label="音乐状态:"
              :label-width="formLabelWidth + ''"
            >
              <el-select v-model="dialogForm.status" placeholder="请选择状态">
                <el-option label="上架" value="1"></el-option>
                <el-option label="下架" value="2"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              prop="typeId"
              label="音乐类别:"
              :label-width="formLabelWidth + ''"
            >
              <el-select
                v-model="dialogForm.typeId"
                placeholder="请选择音乐类别"
              >
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="resetForm('dialogForm')">重 置</el-button>
            <el-button type="primary" @click="addMusic()">确 定</el-button>
          </div>
        </el-dialog>
        <!-- 编辑音乐弹窗 -->
        <el-dialog
          title="修改音乐信息"
          :visible.sync="dialog_updateMusic"
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
              prop="musicname"
              label="音乐名称:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.musicname"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="musicphoto"
              label="音乐图片:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.musicphoto"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="musicplay"
              label="音乐播放:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.musicplay"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="mvplay"
              label="MV 播放:"
              :label-width="formLabelWidth + ''"
            >
              <el-input
                style="width: 250px;"
                v-model="dialogForm.mvplay"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="recommend"
              label="推荐星级:"
              :label-width="formLabelWidth + ''"
            >
              <el-select
                v-model="dialogForm.recommend"
                placeholder="请选择推荐星级"
              >
                <el-option label="一星" value="1"></el-option>
                <el-option label="二星" value="2"></el-option>
                <el-option label="三星" value="3"></el-option>
                <el-option label="四星" value="4"></el-option>
                <el-option label="五星" value="5"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              prop="status"
              label="音乐状态:"
              :label-width="formLabelWidth + ''"
            >
              <el-select v-model="dialogForm.status" placeholder="请选择状态">
                <el-option label="上架" value="1"></el-option>
                <el-option label="下架" value="2"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              prop="typeId"
              label="音乐类别:"
              :label-width="formLabelWidth + ''"
            >
              <el-select
                v-model="dialogForm.typeId"
                placeholder="请选择音乐类别"
              >
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="closeInfo()">取 消</el-button>
            <el-button type="primary" @click="updateMusic()">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import "../../../styles/config.scss";
import { getMusic } from "@/api/music/musicList.js";
import { deleteMusic } from "@/api/music/deleteMusic.js";
import { updateMusic } from "@/api/music/updateMusic.js";
import { addMusic } from "@/api/music/addMusic.js";
import { getMusicType } from "@/api/music/typeList.js";
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
      dialog_updateMusic: false,
      //控制新增弹窗
      dialog_addMusic: false,
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
        musicname: "",
        musicphoto: "",
        musicplay: "",
        mvplay: "",
        recommend: "",
        status: "",
        typeId: ""
      },
      //新增弹窗的音乐类别
      options: [],
      rules: {
        musicname: [
          {
            required: true,
            message: "请输入音乐名称",
            trigger: "blur"
          },
          {
            pattern: /[\u4e00-\u9fa5]{1,20}$/,
            message: "长度在 2 到 8 个汉字",
            trigger: "blur"
          }
        ],
        musicphoto: [
          { required: true, message: "请上传音乐图片", trigger: "blur" }
        ],
        musicplay: [{ required: true, message: "请上传音乐", trigger: "blur" }],
        mvplay: [{ required: true, message: "请上传Mv", trigger: "blur" }],
        recommend: [{ required: true, message: "请选择星级", trigger: "blur" }],
        status: [{ required: true, message: "请选择状态", trigger: "blur" }],
        typeId: [{ required: true, message: "请选择音乐类别", trigger: "blur" }]
      }
    };
  },
  methods: {
    //点击搜过框查询时
    query() {
      const param = {
        musicname: this.input
      };
      getMusic(param).then(response => {
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
              musicplay: "",
              mvplay: "",
              recommend: "",
              recommendStr: "",
              status: "",
              statusStr: "",
              typeId: "",
              typename: ""
            };
            table.id = tableList[i].id;
            table.musicname = tableList[i].musicName;
            table.musicphoto = tableList[i].musicPhoto;
            table.musicplay = tableList[i].musicPlay;
            table.mvplay = tableList[i].mvPlay;
            table.recommend = tableList[i].recommend;
            table.status = "" + tableList[i].STATUS;
            //将数据库中的数据取出来转换成文字
            debugger;
            if (table.status == 1) {
              table.statusStr = "上架";
            } else {
              table.statusStr = "下架";
            }
            if (table.recommend == 1) {
              table.recommendStr = "一星";
            } else if (table.recommend == 2) {
              table.recommendStr = "二星";
            } else if (table.recommend == 3) {
              table.recommendStr = "三星";
            } else if (table.recommend == 4) {
              table.recommendStr = "四星";
            } else if (table.recommend == 5) {
              table.recommendStr = "五星";
            }
            table.typeId = tableList[i].tId;
            table.typename = tableList[i].typeName;
            this.tableData.push(table);
          }
        }
      });
    },
    //添加音乐弹窗
    insertMusic() {
      this.reset();
      this.dialog_addMusic = true;
      this.initPage();
    },
    //添加音乐
    addMusic() {
      // const param = {
      //   id: "",
      //   musicname: this.dialogForm.musicname,
      //   musicphoto: this.dialogForm.musicphoto,
      //   musicplay: this.dialogForm.musicplay,
      //   mvplay: this.dialogForm.mvplay,
      //   recommend: this.dialogForm.recommend,
      //   status: this.dialogForm.status,
      //   typeId: this.dialogForm.typeId
      // };
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.dialog_addMusic = false;
          addMusic(this.dialogForm).then(response => {
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
          console.log("error submit!!");
          return false;
        }
      });
    },
    initPage() {
      const param = {
        typename: ""
      };
      getMusicType(param).then(response => {
        const data = response.data.data;
        for (let i = 0; i < data.length; i++) {
          const option = {
            value: "",
            label: ""
          };
          option.value = data[i].id;
          option.label = data[i].typename;
          this.options.push(option);
        }
      });
    },
    //修改之查询
    update(index, row) {
      debugger;
      this.dialog_updateMusic = true;
      this.dialogForm.id = row.id;
      this.dialogForm.musicname = row.musicname;
      this.dialogForm.musicphoto = row.musicphoto;
      this.dialogForm.musicplay = row.musicplay;
      this.dialogForm.mvplay = row.mvplay;
      this.dialogForm.recommend = row.recommend;
      this.dialogForm.status = row.status;
      this.dialogForm.typeId = row.typeId;
      this.initPage();
    },
    //修改之真正修改
    updateMusic() {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.dialog_updateMusic = false;
          updateMusic(this.dialogForm).then(response => {
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
    //删除音乐
    deleteMusic(index, row) {
      this.$confirm("确认将【" + row.musicname + "】从列表中删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          const ids = [];
          ids.push(row.id);
          deleteMusic(ids).then(response => {
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
    deleteMusics(sels) {
      if (sels.length > 0) {
        //将得到的数据中的id放到一个数组中
        sels.forEach(sel => {
          this.deleteIds.push(sel.id);
          this.deleteNames.push(sel.musicname);
        });
        this.$confirm(
          "确认将【" + this.deleteNames + "】从音乐列表中删除?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        )
          .then(() => {
            deleteMusic(this.deleteIds).then(response => {
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
          message: "请选择要删除的音乐",
          type: "warning"
        });
      }
    },
    //取消按钮
    closeInfo() {
      this.dialog_updateMusic = false;
      this.$message({
        type: "info",
        message: "已取消编辑"
      });
      this.query();
    },
    // 新增前清空内容
    reset() {
      this.dialogForm.id = "";
      this.dialogForm.musicname = "";
      this.dialogForm.musicphoto = "";
      this.dialogForm.musicplay = "";
      this.dialogForm.mvplay = "";
      this.dialogForm.recommend = "";
      this.dialogForm.status = "";
      this.dialogForm.typeId = "";
    },
    // 重置按钮
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
.el-form-item {
  margin-bottom: 20px;
  margin-left: 50px;
}
.el-form-item__content {
  margin-left: 50px;
}
.dialog-footer {
  text-align: center;
}
</style>
