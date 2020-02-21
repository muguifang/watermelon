<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <el-page-header @back="goBack" content="修改密码"></el-page-header>
        <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="原密码" prop="password">
            <el-input
              type="password"
              v-model="ruleForm.password"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPass">
            <el-input
              type="password"
              v-model="ruleForm.newPass"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input
              type="password"
              v-model="ruleForm.checkPass"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
              >提交</el-button
            >
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import { stripscript, validatePass } from "@/utils/validate";
import { updatePassword } from "@/api/user/login.js";
import router from "@/router";
export default {
  name: "updatePwd",
  inject: ["reload"],
  data() {
    var validatePassword = (rule, value, callback) => {
      this.ruleForm.password = stripscript(value);
      value = this.ruleForm.password;
      if (value === "") {
        callback(new Error("请输入原密码"));
      } else if (this.user.password != value) {
        callback(new Error("原密码输入不正确"));
      } else {
        callback();
      }
    };
    var validateNewPass = (rule, value, callback) => {
      this.ruleForm.newPass = stripscript(value);
      value = this.ruleForm.newPass;
      if (value === "") {
        callback(new Error("请输入新密码"));
      } else if (validatePass(value)) {
        callback(new Error("密码为6至20位数字Or字母"));
      } else {
        callback();
      }
    };
    var validateCheckPass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.newPass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        password: "",
        newPass: "",
        checkPass: ""
      },
      rules: {
        password: [{ validator: validatePassword, trigger: "blur" }],
        newPass: [{ validator: validateNewPass, trigger: "blur" }],
        checkPass: [{ validator: validateCheckPass, trigger: "blur" }]
      },
      user: {
        password: ""
      }
    };
  },
  mounted() {
    const user = JSON.parse(sessionStorage.getItem("user"));
    this.user.password = user.password;
  },
  methods: {
    goBack() {
      router.push("/index");
      this.reload();
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const param = {
            password: this.ruleForm.newPass
          };
          updatePassword(param).then(response => {
            console.log(response);
            const data = response.data;
            if (data.code === 200) {
              this.$message({
                message: "修改成功",
                type: "success"
              });
              // router.push("/login");
            } else {
              this.$message({
                message: data.msg,
                type: "warning"
              });
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style lang="scss" scoped>
.demo-ruleForm {
  width: 30%;
  margin: 110px auto;
  .el-form-item {
    padding-bottom: 30px;
    .el-button {
      padding: 12px 58px;
    }
  }
}
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
</style>
