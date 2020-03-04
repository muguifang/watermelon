// -- 引入外部其他文件 --
import { getCookie } from "@/utils/store.js";
import { base64Convert } from "@/utils/base64Util.js";
import { getUserInfo, updateUserInfo } from "@/api/user.js";
import { deleteServerFile } from "@/api/file.js";
// -- 名字 --

const name = "userInfo";

// -- 数据 --

const data = function() {
  return {
    //原头像
    oldHead: "",
    //头像文件名称
    fileName: "",
    //cookie存储的用户信息
    u_info: "",
    //注册表单
    registForm: {
      username: "",
      password: "",
      checkPwd: "",
      realname: "",
      phone: "",
      email: "",
      pic: ""
    },
    formLabelWidth: "120px",
    //个人信息校验规则
    infoRules: {}
  };
};

// -- 方法 --

const methods = {
  query() {
    //cookie获取登录用户id
    const _u_i = getCookie("_u_i");
    if (_u_i != "" && _u_i != null) {
      //封装请求参数
      const param = {
        userId: _u_i
      };
      //发送请求获取用户信息
      getUserInfo(param).then(response => {
        const res = response.data;
        if (res.code === 200) {
          const data = res.data;
          this.registForm.username = data.username;
          this.registForm.realname = data.realname;
          this.registForm.phone = data.phone;
          this.registForm.email = data.email;
          this.registForm.pic = URL.createObjectURL(
            base64Convert(data.headpic)
          );
          this.oldHead = URL.createObjectURL(base64Convert(data.headpic));
        }
      });
    } else {
      this.$message({
        type: "info",
        message: "登录失效"
      });
    }
  },
  //修改个人信息
  updateInfoForm(formName) {
    this.$refs[formName].validate(valid => {
      if (valid) {
        //
        const param = {
          id: getCookie("_u_i"),
          phone: this.registForm.phone,
          email: this.registForm.email,
          headpic: null
        };
        if (this.fileName != "" && this.fileName != null) {
          param.headpic = "D:/img/" + this.fileName;
        }
        updateUserInfo(param).then(response => {
          const res = response.data;
          if (res.code === 200) {
            this.$message({
              showClose: true,
              message: "修改成功！",
              type: "success"
            });
            const _u_i = getCookie("_u_i");
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
        });
      }
    });
  },
  //头像上传成功后
  handleAvatarSuccess(res, file) {
    this.fileName = res.data;
    this.registForm.pic = URL.createObjectURL(file.raw);
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
  //取消修改方法
  cancel() {
    //参数
    deleteServerFile(this.fileName).then(response => {
      const res = response.data;
      if (res.code === 200) {
        this.registForm.pic = this.oldHead;
      }
    });
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
