// -- 引入外部其他文件 --
// -- 名字 --

const name = "index";

// -- 数据 --

const data = function() {
  var checkContent = (rule, value, callback) => {
    if (value === "") {
      callback(new Error("请输入内容"));
    } else if (value.length > 200) {
      callback(new Error("内容不能超过200字"));
    } else {
      if (this.ruleForm.content !== "") {
        this.$refs.ruleForm.validateField("content");
      }
      callback();
    }
  };
  var checkPhone = (rule, value, callback) => {
    //验证手机正则
    let reg = /^1[345789]\d{9}$/;
    if (value === "") {
      callback(new Error("请输入手机号"));
    } else if (!reg.test(value)) {
      callback(new Error("手机号格式不正确"));
    } else {
      if (this.ruleForm.phone !== "") {
        this.$refs.ruleForm.validateField("phone");
      }
      callback();
    }
  };
  return {
    ruleForm: {
      content: "",
      phone: ""
    },
    rules: {
      content: [{ validator: checkContent, trigger: "blur" }],
      phone: [{ validator: checkPhone, trigger: "blur" }]
    }
  };
};

// -- 方法 --

const methods = {
  submitForm(formName) {
    this.$refs[formName].validate(valid => {
      if (valid) {
        alert("submit!");
      } else {
        console.log("error submit!!");
        return false;
      }
    });
  },
  resetForm(formName) {
    this.$refs[formName].resetFields();
  }
};

// -- 页面加载完成 --
const created = function() {};

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
