// -- 引入外部其他文件 --
import { stripscript, validatePass, validateName } from "@/utils/validate";
import { base64Convert } from "@/utils/base64Util.js";
import { login, register, getUserInfo } from "@/api/user.js";
import { saveCookie } from "@/utils/store.js";
import { getCookie } from "@/utils/store.js";
// -- 名字 --

const name = "LayoutHeader";

// -- 数据 --

const data = function() {
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
    //头像
    headpic: "",
    //用户名
    username: "",
    routers,
    //自动登录
    checked: false,
    //登录标志
    flag: false,
    routerPath: "",
    //搜索框
    input: "",
    //登录弹窗标志
    dialogLogin: false,
    //注册弹窗标志
    dialogRegist: false,
    //账号抽屉标志
    drawer: false,
    //个人信息抽屉标志
    drawerUser: false,
    //抽屉方向
    direction: "rtl",
    //抽屉遮罩
    modal: false,
    //头像路径
    imageUrl: "",
    //登录表单
    loginForm: {
      username: "",
      password: ""
    },
    //注册表单
    registForm: {
      username: "zxczxc",
      password: "zxc123",
      checkPwd: "zxc123",
      realname: "zhangsan",
      phone: "15894653236",
      email: "8956@163.com",
      pic: ""
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
    infoRules: {}
  };
};

// -- 方法 --

const methods = {
  // 登录表单提交按钮
  submitLoginForm(formName) {
    this.$refs[formName].validate(valid => {
      if (valid) {
        const param = {
          username: this.loginForm.username,
          password: this.loginForm.password
        };
        login(param).then(response => {
          const res = response.data;
          const name = res.data.username;
          this.headpic = URL.createObjectURL(base64Convert(res.data.headpic));
          const info = { name: name, p_bl: this.headpic };
          if (res.code === 200) {
            if (this.checked == true) {
              saveCookie("_u_i", JSON.stringify(res.data.id), 7);
              saveCookie("u_if", JSON.stringify(info), 7);
              this.username = name;
            } else {
              saveCookie("_u_i", JSON.stringify(res.data.id));
              saveCookie("u_if", JSON.stringify(info));
              this.username = name;
            }
            this.dialogLogin = false;
            //登录成功改为true
            this.flag = true;
          }
        });
      }
    });
  },
  //注册表单提交
  submitRegistForm(formName) {
    this.$refs[formName].validate(valid => {
      if (valid) {
        //封装注册用户参数
        const param = {
          username: this.registForm.username,
          password: this.registForm.password,
          realname: this.registForm.realname,
          phone: this.registForm.phone,
          email: this.registForm.email
        };
        register(param).then(response => {
          const res = response.data;
          if (res.code === 200) {
            this.$message({
              message: "注册成功!",
              type: "success"
            });
            this.dialogLogin = true;
            this.dialogRegist = false;
            this.loginForm.username = this.registForm.username;
            this.loginForm.password = this.registForm.password;
          }
        });
      }
    });
  },
  //跳转账号页面
  getZhInfo() {
    this.drawer = true;
  },
  //跳转个人信息页面
  getUserInfo() {
    this.drawer = false;
    this.$router.push("/userInfo");
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
  // handleClose(done) {
  //   this.$confirm("确认关闭？")
  //     .then(() => {
  //       done();
  //     })
  //     .catch(() => {});
  // },
  //退出登录
  exit() {
    this.$confirm("是否确认退出登录?", "提示", {
      cancelButtonText: "取消",
      confirmButtonText: "确定",
      type: "warning"
    })
      .then(() => {
        saveCookie("_u_i", JSON.stringify(null), 0);
        saveCookie("_u_if", JSON.stringify(null), 0);
        this.$message({
          type: "success",
          message: "注销成功!"
        });
        this.drawer = false;
        //删除保存的信息 游客身份返回首页
        // sessionStorage.removeItem("user");
        this.$router.push("/index");
        //注销登录改为false
        this.flag = false;
      })
      .catch(() => {
        this.$message({
          type: "info",
          message: "已取消退出"
        });
      });
  },
  //关闭登录弹窗之前
  close() {
    this.loginForm.username = "";
    this.loginForm.password = "";
    this.dialogLogin = false;
  },
  //导航信息跳转
  handleSelect() {
    this.routerPath = this.$route.path;
  },
  //跳转网站建议页面
  toAdvice() {
    this.drawer = false;
    this.$router.push("/webAdvice");
  },
  //跳转我的收藏页面
  toCollect() {
    this.drawer = false;
    this.$router.push("/myCollect");
  },
  //跳转音乐搜索页面
  toSearch() {
    // this.$router.push("/searchMusic");
    this.$router.push({ path: "/searchMusic", query: { serach: this.input } });
    this.input = "";
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
  },
  showInfo() {
    const _u_i = getCookie("_u_i");
    if (_u_i != "" && _u_i != null) {
      this.flag = true;
      const param = {
        userId: _u_i
      };
      getUserInfo(param).then(response => {
        const res = response.data;
        if (res.code === 200) {
          const data = res.data;
          this.username = data.username;
          this.headpic = URL.createObjectURL(base64Convert(data.headpic));
        }
      });
    }
    // } else {
    //   this.$message({
    //     type: "info",
    //     message: "登录失效"
    //   });
    // }
  }
};

// -- 页面加载完成 --
const created = function() {
  this.handleSelect();
  //页面头部信息展示
  this.showInfo();
};

// -- 自动计算属性 --

const computed = {};

// -- 监听 --

const watch = {};

// == EXPORT ==

export default {
  name: name,

  data: data,

  computed: computed,

  watch: watch,

  methods: methods,

  created
};
