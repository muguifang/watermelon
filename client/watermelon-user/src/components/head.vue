<template>
  <div id="head-wrap">
    <!-- 右上角信息 -->
    <div class="user-info">
      <el-link :underline="false" icon="el-icon-star-off" @click="toCollect()"
        >我的收藏</el-link
      >
      <img class="photo" src="@/assets/photo.png" />
      <el-link :underline="false" @click="getUserInfo()">用户名</el-link>
      <el-link :underline="false" @click="exit()">退出</el-link>
      <el-link :underline="false" @click="login()">登录</el-link>
      <el-link :underline="false" @click="regist()">注册</el-link>
      <el-link :underline="false" icon="el-icon-message" @click="toAdvice()"
        >网站建议</el-link
      >
    </div>
    <!-- logo 搜索框 -->
    <div class="web-info">
      <img src="@/assets/xgImg.png" class="xgImg xg" />
      <label>音乐网</label>
      <div class="search-info">
        <el-input
          placeholder="请输入内容"
          style="width: 250px; display: inline-block;"
          v-model="input"
          clearable
        >
        </el-input>
        <el-button type="info" icon="el-icon-search">搜索</el-button>
      </div>
    </div>
    <!-- 导航信息 -->
    <div class="nav-wrap">
      <el-menu
        :default-active="routerPath == '/index' ? '/' : routerPath"
        :select="handleSelect()"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router
      >
        <el-menu-item
          v-for="item in routers"
          :key="item.id"
          :index="item.path"
          v-show="item.hidden"
          >{{ item.meta.name }}</el-menu-item
        >
      </el-menu>
    </div>
    <!-- 登录弹窗 -->
    <el-dialog title="登录信息" :visible.sync="dialogLogin" width="30%">
      <el-form :model="loginForm" :rules="loginRules" ref="loginForm" status-icon>
        <el-form-item prop="username" label="账号:" :label-width="formLabelWidth">
          <el-input placeholder="请输入账号" v-model="loginForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码:" :label-width="formLabelWidth">
          <el-input placeholder="请输入密码" v-model="loginForm.password" type="password" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('loginForm')">重 置</el-button>
        <el-button type="primary" @click="submitLoginForm('loginForm')">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 注册弹窗 -->
    <el-dialog title="注册信息" :visible.sync="dialogRegist" width="30%">
      <el-form :model="registForm" :rules="registRules" ref="registForm" status-icon>
        <el-form-item prop="username" label="账号:" :label-width="formLabelWidth">
          <el-input placeholder="请输入账号" v-model="registForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码:" :label-width="formLabelWidth">
          <el-input placeholder="请输入密码" v-model="registForm.password" type="password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="checkPwd" label="确认密码:" :label-width="formLabelWidth">
          <el-input placeholder="请再次输入密码" v-model="registForm.checkPwd" type="password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="realname" label="真实姓名:" :label-width="formLabelWidth">
          <el-input placeholder="请输入真实姓名" v-model="registForm.realname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="phone" label="联系方式:" :label-width="formLabelWidth">
          <el-input placeholder="请输入联系方式" v-model="registForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="email" label="邮箱:" :label-width="formLabelWidth">
          <el-input placeholder="请输入邮箱" v-model="registForm.email" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('registForm')">重 置</el-button>
        <el-button type="primary" @click="submitRegistForm('registForm')">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 个人信息抽屉 -->
    <el-radio-group v-model="direction">
      <el-radio label="rtl" hidden="true">从右往左开</el-radio>
      <el-drawer
        title="我的信息"
        :visible.sync="drawer"
        :direction="direction"
        :before-close="handleClose">
        <el-form :model="registForm" :rules="infoRules" ref="registForm" status-icon>
          <el-form-item prop="pic">
            <el-upload
              class="avatar-uploader"
              action="api/file/upload"
              name="photo"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img src="@/assets/photo.png" class="avatar"/>
              <!-- <img v-if="imageUrl" :src="imageUrl" class="avatar"> -->
              <!-- <i v-else class="el-icon-plus avatar-uploader-icon"></i> -->
            </el-upload>
          </el-form-item>
          <el-form-item prop="username" label="账号:" :label-width="formLabelWidth">
            <el-input v-model="registForm.username" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item prop="realname" label="真实姓名:" :label-width="formLabelWidth">
            <el-input v-model="registForm.realname" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item placeholder="请输入联系方式" prop="phone" label="联系方式:" :label-width="formLabelWidth">
            <el-input v-model="registForm.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item placeholder="请输入邮箱" prop="email" label="邮箱:" :label-width="formLabelWidth">
            <el-input v-model="registForm.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label-width="160px">
            <el-button @click="resetForm('registForm')">重 置</el-button>
            <el-button type="primary" @click="updateInfoForm('registForm')">确 定</el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
    </el-radio-group>
  </div>
</template>
<script>
import { stripscript, validatePass, validateName } from "@/utils/validate";
export default {
  data() {
    var routers = this.$root.$router.options.routes;
    // 登录用户名校验
    var validateUsername = (rule, value, callback) => {
      this.loginForm.username = stripscript(value);
      value = this.loginForm.username;
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else if (validateName(value)) {
        callback(new Error("用户名为6至8位数字Or字母"));
      } else {
        callback();
      }
    };
    // 登录密码校验
    var validatePassword = (rule, value, callback) => {
      this.loginForm.password = stripscript(value);
      value = this.loginForm.password;
      if (value === "") {
        callback(new Error("请输入密码"));
      } else if (validatePass(value)) {
        callback(new Error("密码为6至20位数字Or字母"));
      } else {
        callback();
      }
    };
    // 注册用户名校验
    var validateCountname = (rule, value, callback) => {
      this.registForm.username = stripscript(value);
      value = this.registForm.username;
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else if (validateName(value)) {
        callback(new Error("用户名为6至8位数字Or字母"));
      } else {
        callback();
      }
    };
    // 注册密码校验
    var validatePwd = (rule, value, callback) => {
      this.registForm.password = stripscript(value);
      value = this.registForm.password;
      if (value === "") {
        callback(new Error("请输入密码"));
      } else if (validatePass(value)) {
        callback(new Error("密码为6至20位数字Or字母"));
      } else {
        callback();
      }
    };
    // 注册密码二次校验
    var validateCheckPwd = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入确认密码"));
      } else if (this.registForm.password != value) {
        callback(new Error("两次密码不一致"));
      } else {
        callback();
      }
    };
    var validateRealname = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入真实姓名"));
      } else if (value.length > 10) {
        callback(new Error("姓名长度不得大于10位"));
      } else {
        callback();
      }
    };
    return {
      routers,
      routerPath: "",
      //搜索框
      input: "",
      //登录弹窗标志
      dialogLogin: false,
      //注册弹窗标志
      dialogRegist: false,
      //个人信息抽屉标志
      drawer: false,
      //抽屉方向
      direction: "rtl",
      //头像路径
      imageUrl: "",
      //登录表单
      loginForm: {
        username: "",
        password: ""
      },
      //注册表单
      registForm: {
        username: "",
        password: "",
        checkPwd: "",
        realname: "",
        phone: "",
        email: "",
        pic: "",
      },
      formLabelWidth: "120px",
      // 登录校验规则
      loginRules: {
        username: [{ validator: validateUsername, trigger: "blur" }],
        password: [{ validator: validatePassword, trigger: "blur" }]
      },
      // 注册校验规则
      registRules: {
        username: [{ validator: validateCountname, trigger: "blur" }],
        password: [{ validator: validatePwd, trigger: "blur" }],
        checkPwd: [{ validator: validateCheckPwd, trigger: "blur" }],
        realname: [{ validator: validateRealname, trigger: "blur" }]
      },
      //个人信息校验规则
      infoRules: {

      }
    };
  },
  methods: {
    // 登录表单提交按钮
    submitLoginForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log("success");
        }
        // if (valid) {
        //   const param = {
        //     username: this.form.username,
        //     password: this.form.password
        //   };
        //   login(param).then(response => {
        //     const data = response.data;
        //     if (data.code === 200) {
        //       this.$message({
        //         message: "登录成功",
        //         type: "success"
        //       });
        //       sessionStorage.setItem("user", JSON.stringify(data.data));
        //       router.push("/console");
        //     } else {
        //       this.$message({
        //         message: data.msg,
        //         type: "warning"
        //       });
        //     }
        //   });
        // } else {
        //   console.log("error submit!!");
        //   return false;
        // }
      });
    },
    //注册表单提交
    submitRegistForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log("success");
        }
      });
    },
    //跳转个人信息页面
    getUserInfo() {
      this.drawer = true;
    },
    //修改个人信息
    updateInfoForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log("success");
        }
      });
    },
    //关闭个人信息抽屉时
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    //退出登录
    exit() {
      this.$confirm("是否确认退出登录?", "提示", {
        cancelButtonText: "取消",
        confirmButtonText: "确定",
        type: "warning"
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "注销成功!"
          });
          //删除保存的信息 游客身份返回首页
          // sessionStorage.removeItem("user");
          this.$router.push("/index");
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消退出"
          });
        });
    },
    //导航信息跳转
    handleSelect() {
      this.routerPath = this.$route.path;
    },
    //跳转网站建议页面
    toAdvice() {
      this.$router.push("/webAdvice");
    },
    //跳转我的收藏页面
    toCollect() {
      this.$router.push("/myCollect");
    },
    //登录弹窗
    login() {
      this.dialogLogin = true;
    },
    //注册弹窗
    regist() {
      this.dialogRegist = true;
    },
    // 重置按钮
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //头像上传成功后
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    //上传之前
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
  created() {
    this.handleSelect();
  }
};
</script>
<style lang="scss" scoped>
#head-wrap {
  margin-bottom: 20px;
}
.user-info {
  width: 100%;
  height: 45px;
  text-align: right;
  .el-link {
    padding-right: 20px;
    font-size: 18px;
  }
}
.photo {
  width: 35px;
  height: 35px;
  display: inline;
  position: relative;
  top: 10px;
  left: -6px;
  border-radius: 50%;
}
.web-info {
  .xgImg {
    width: 50px;
    position: absolute;
    top: 25px;
    left: 100px;
  }
  label {
    position: absolute;
    top: 35px;
    left: 160px;
    font-size: 30px;
  }
}
.search-info {
  position: relative;
  top: -16px;
  left: 690px;
  width: 22%;
  .el-input {
    padding-right: 10px;
  }
  .el-button {
    height: 40px;
  }
}
.nav-wrap {
  .el-menu {
    padding-left: 400px;
    .el-menu-item {
      margin-right: 50px;
      font-size: 18px;
    }
  }
}
.el-form-item {
  margin-bottom: 30px;
}
.el-input {
  width: 70%;
}
.avatar-uploader {
  border: 1px dashed #eee;
  width: 120px;
  height: 120px;
  margin: auto;
  border-radius: 50%;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  border-radius: 50%;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
}
</style>
