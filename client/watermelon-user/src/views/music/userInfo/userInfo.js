// -- 引入外部其他文件 --
// -- 名字 --

const name = "userInfo";

// -- 数据 --

const data = function() {
  return {
    //头像文件名称
    fileName: "",
    //cookie存储的用户信息
    u_info: "",
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
    //个人信息校验规则
    infoRules: {}
  };
};

// -- 方法 --

const methods = {
  //修改个人信息
  updateInfoForm(formName) {
    this.$refs[formName].validate(valid => {
      if (valid) {
        console.log("success");
      }
    });
  },
  //头像上传成功后
  handleAvatarSuccess(res, file) {
    console.log(file);
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
};

// -- 页面加载完成 --
const created = function() {
  this.query();
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
