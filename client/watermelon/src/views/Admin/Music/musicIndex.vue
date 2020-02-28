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
          <el-table-column prop="musicname" label="音乐名称" width="200">
          </el-table-column>
          <el-table-column prop="musicphoto" label="音乐图片" width="250">
            <template slot-scope="scope">
              <img :src="scope.row.musicphoto" />
            </template>
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
                size="mini"
                icon="el-icon-video-play"
                @click="playMusic(scope.row)"
                >播放音乐</el-button
              >
              <el-button
                size="mini"
                icon="el-icon-video-play"
                @click="playMusicMV(scope.row)"
                >播放 MV</el-button
              >
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
                @click="update(scope.row)"
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
            :page-sizes="[1, 2, 3, 4]"
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
            </el-form-item>
            <el-form-item
              prop="musicplay"
              label="音乐文件:"
              :label-width="formLabelWidth + ''"
            >
              <el-upload
                class="upload-demo"
                ref="uploadMusic"
                action="/api/file/upload"
                name="file"
                :before-upload="uploadMusicBefore"
                :on-success="successMusic"
                :data="{path: 'D:/music'}"
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="fileList"
                :auto-upload="false"
              >
                <el-button slot="trigger" size="small" type="primary"
                  >选取文件</el-button
                >
                <el-button
                  style="margin-left: 10px;"
                  size="small"
                  type="success"
                  @click="submitUploadMusic"
                  >上传到服务器</el-button
                >
                <div slot="tip" class="el-upload__tip">
                  只能上传mp3文件
                </div>
              </el-upload>
            </el-form-item>
            <el-form-item
              prop="mvplay"
              label="MV 文件:"
              :label-width="formLabelWidth + ''"
            >
              <el-upload
                class="upload-demo"
                ref="uploadMV"
                name="file"
                action="/api/file/upload"
                :before-upload="uploadMVBefore"
                :data="{path: 'D:/mv'}"
                :on-success="successMv"
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="fileList"
                :auto-upload="false"
              >
                <el-button slot="trigger" size="small" type="primary"
                  >选取文件</el-button
                >
                <el-button
                  style="margin-left: 10px;"
                  size="small"
                  type="success"
                  @click="submitUploadMV"
                  >上传到服务器</el-button
                >
                <div slot="tip" class="el-upload__tip">
                  只能上传avi文件
                </div>
              </el-upload>
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
            </el-form-item>
            <el-form-item
              prop="musicplay"
              label="音乐文件:"
              :label-width="formLabelWidth + ''"
            >
              <el-upload
                class="upload-demo"
                ref="uploadMusic"
                action="/api/file/upload"
                name="file"
                :before-upload="uploadMusicBefore"
                :on-success="successMusic"
                :data="{path: 'D:/music'}"
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="fileList"
                :auto-upload="false"
              >
                <el-button slot="trigger" size="small" type="primary"
                  >选取文件</el-button
                >
                <el-button
                  style="margin-left: 10px;"
                  size="small"
                  type="success"
                  @click="submitUploadMusic"
                  >上传到服务器</el-button
                >
                <div slot="tip" class="el-upload__tip">
                  只能上传mp3文件
                </div>
              </el-upload>
            </el-form-item>
            <el-form-item
              prop="mvplay"
              label="MV 文件:"
              :label-width="formLabelWidth + ''"
            >
              <el-upload
                class="upload-demo"
                ref="uploadMV"
                name="file"
                action="/api/file/upload"
                :before-upload="uploadMVBefore"
                :data="{path: 'D:/mv'}"
                :on-success="successMv"
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="fileList"
                :auto-upload="false"
              >
                <el-button slot="trigger" size="small" type="primary"
                  >选取文件</el-button
                >
                <el-button
                  style="margin-left: 10px;"
                  size="small"
                  type="success"
                  @click="submitUploadMV"
                  >上传到服务器</el-button
                >
                <div slot="tip" class="el-upload__tip">
                  只能上传avi文件
                </div>
              </el-upload>
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
        <!-- 播放音乐弹窗 -->
        <el-dialog
          title="播放音乐"
          :visible.sync="dialog_playMusic"
          :append-to-body="true"
          width="30%"
        >
          <div style="width:100%;">
            <audio style="width:100%;" :src="musicSrc" controls="controls" />
          </div>
        </el-dialog>
        <!-- 播放MV弹窗 -->
        <el-dialog
          title="播放 MV"
          :visible.sync="dialog_playMV"
          :append-to-body="true"
          width="30%"
        >
          <!-- 视频代码。。没找到 -->
          <div style="width:100%;">
            <video style="width:100%;" :src="mvSrc" controls="controls"></video>
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
import { base64Convert } from "@/utils/base64Util.js";
import { returnBase64 } from "@/api/music/dialogPlayMusic.js";
import { deleteServerFile } from "@/api/file/file.js";
export default {
  name: "typeIndex",
  inject: ["reload"],
  data() {
    return {
      //磁盘音乐名称
      musicName: "",
      //磁盘mv名称
      mvName: "",
      imageUrl: "",
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
      //播放音乐弹窗
      dialog_playMusic: false,
      //播放MV弹窗
      dialog_playMV: false,
      //数据总条数
      sumNum: 0,
      //当前页码
      currentPage: 1,
      //每页条数
      pageSize: 4,
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
        typeId: "",
        insertdate: "",
        playnum: ""
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
      },
      //图片名称
      fileName: "",
      //音乐路径
      musicSrc: "",
      //MV路径
      mvSrc: "",
      fileList: []
    };
  },
  methods: {
    //点击播放音乐按钮
    playMusic(row) {
      this.dialog_playMusic = true;
      this.musicSrc = row.musicplay;
      const param = {
        path: this.musicSrc
      };
      returnBase64(param).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.musicSrc = URL.createObjectURL(base64Convert(data.data));
        } else {
          this.$message({
            type: "error",
            message: "获取播放源失败"
          });
        }
      });
    },
    //点击播放MV按钮
    playMusicMV(row) {
      this.dialog_playMV = true;
      this.mvSrc = row.mvplay;
      const param = {
        path: this.mvSrc
      };
      returnBase64(param).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.mvSrc = URL.createObjectURL(base64Convert(data.data));
        } else {
          this.$message({
            type: "error",
            message: "获取播放源失败"
          });
        }
      });
    },
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
              typename: "",
              insrtdate: "",
              playnum: ""
            };
            table.id = tableList[i].id;
            table.musicname = tableList[i].musicName;
            // table.musicphoto = tableList[i].musicPhoto;
            let base = tableList[i].musicPhoto;
            table.musicphoto = URL.createObjectURL(base64Convert(base));
            table.musicplay = tableList[i].musicPlay;
            table.mvplay = tableList[i].mvPlay;
            table.recommend = tableList[i].recommend;
            table.status = "" + tableList[i].STATUS;
            //将数据库中的数据取出来转换成文字
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
            table.insertdate = tableList[i].insertDate;
            table.playnum = tableList[i].playNum;
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
      this.dialogForm.musicphoto = "D:/img/"+this.fileName;
      this.dialogForm.musicplay = "D:/music/"+this.musicName;
      this.dialogForm.mvplay = "D:/mv/"+this.mvName;
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          addMusic(this.dialogForm).then(response => {
            const data = response.data;
            if (data.code == 200) {
              this.dialog_addMusic = false;
              this.$message({
                type: "success",
                message: "新增成功!"
              });
              this.options = [];
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
    update(row) {
      this.dialog_updateMusic = true;
      this.dialogForm.id = row.id;
      this.dialogForm.musicname = row.musicname;
      this.dialogForm.musicphoto = row.musicphoto;
      this.imageUrl = row.musicphoto;
      this.dialogForm.musicplay = row.musicplay;
      // this.dialogForm.musicplay = this.musicSrc;
      this.dialogForm.mvplay = row.mvplay;
      // this.dialogForm.mvplay = this.mvSrc;
      this.dialogForm.recommend = row.recommend;
      this.dialogForm.status = row.status;
      this.dialogForm.typeId = row.typeId;
      this.dialogForm.playnum = row.playnum;
      this.dialogForm.insertdate = row.insertdate;
      this.initPage();
    },
    //修改之真正修改
    updateMusic() {
          debugger;
          if (this.dialogForm.musicphoto == this.imageUrl) {
            this.dialogForm.musicphoto = null;
          } else {
            this.dialogForm.musicphoto = "D:/img/"+this.fileName;
          }
          if( this.musicName != "" && this.mvName != ""){
            this.dialogForm.musicplay = "D:/music/"+this.musicName;
          this.dialogForm.mvplay = "D:/mv/"+this.mvName;
          }else{
             this.dialogForm.musicplay = null;
          this.dialogForm.mvplay = null;
          }
          updateMusic(this.dialogForm).then(response => {
            const data = response.data;
            if (data.code == 200) {
          this.dialog_updateMusic = false;
              this.$message({
                type: "success",
                message: "修改成功!"
              });
              this.fileList =[];
              this.imageUrl="";
              this.options = [];
              this.query();
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
          message: "请选择要删除的音乐",
          type: "warning"
        });
      }
    },
    //取消按钮
    // closeInfo() {
    //   this.dialog_updateMusic = false;
    //   this.$message({
    //     type: "info",
    //     message: "已取消编辑"
    //   });
    //   // this.reset();
    //   this.options = [];
    //   console.log(this.options);
    //   this.query();
    // },
    //取消按钮取消上传文件时删除服务器图片
    closeInfo() {
      this.dialog_updateMusic = false;
      //删除文件
      if (this.fileName != "") {
        deleteServerFile(this.fileName).then(response => {
          const data = response.data;
          if (data.code === 200) {
            this.fileName = "";
            this.dialog_updateMusic = false;
            this.$message("以取消上传");
          }
        });
      }
      this.options = [];
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
      this.dialogForm.playnum = "";
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
    },
    //上传至服务器
    submitUploadMV() {
        this.$refs.uploadMV.submit();
      },
      submitUploadMusic() {
        this.$refs.uploadMusic.submit();
      },
    //限制一个文件
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      );
    },
    //删除音乐之前
    beforeRemove(file) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      this.fileName = file.response.data;
    },
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
    },
    successMv(res){
      this.mvName = res.data;
    },
    successMusic(res){
      this.musicName = res.data;
    },
    //上传音乐之前
    uploadMusicBefore(file){
      const isMp3 = file.type === "audio/mp3";
      if (!isMp3) {
        this.$message.error("只能上传mp3文件!");
      }
    },
    uploadMVBefore(file){
      const isAvi = file.type === "video/avi";
      if (!isAvi) {
        this.$message.error("只能上传avi文件!");
      }
    }
    // removeFileMusic(){
    //   deleteServerFile(this.musicName).then(response =>{
    //     const data = response.data;
    //     if(data.code === 200){
    //       this.$message("以取消上传");
    //     }
    //   });
    // },
    // removeFileMV(){
    //   deleteServerFile(this.mvName).then(response =>{
    //     const data = response.data;
    //     if(data.code === 200){
    //       this.$message("以取消上传");
    //     }
    //   });
    // }
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
img {
  width: 150px;
  height: 80px;
  margin: 0 auto;
}
.avatar-uploader {
  width: 178px;
  height: 100px;
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
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 100px;
  display: block;
}
</style>
