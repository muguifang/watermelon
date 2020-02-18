/* eslint-disable no-unused-vars */
<template>
  <div id="login">
    <!-- 内容开始 -->
    <div class="login-warp">
      <!-- 登录注册切换 -->
      <!-- <ul class="menu-tab">
        <li
          :class="{ current: item.current }"
          v-for="item in menuTab"
          :key="item.id"
          @click="taggleMenu(item)"
        >
          {{ item.txt }}
        </li>
      </ul>-->
      <h1>西瓜音乐网后台管理系统</h1>
      <!-- 表单开始 -->
      <el-form
        :model="form"
        status-icon
        :rules="rules"
        ref="form"
        label-width="100px"
        class="login-form"
      >
        <!-- 用户名 -->
        <el-form-item prop="username">
          <label>用户名：</label>
          <el-input
            v-model="form.username"
            autocomplete="off"
            placeholder="请输入6-8位用户名"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <label>密码：</label>
          <el-input
            type="password"
            v-model="form.password"
            autocomplete="off"
            placeholder="请输入6-20位密码"
          ></el-input>
        </el-form-item>
        <!-- 登录、重置按钮 -->
        <el-form-item>
          <el-button type="primary" @click="submitForm('form')">登录</el-button>
          <el-button @click="resetForm('form')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import { stripscript, validatePass, validateName } from "@/utils/validate";
import { login } from "@/api/user/login.js";
import router from "@/router";
export default {
  name: "login",
  data() {
    // 用户名校验
    var validateUsername = (rule, value, callback) => {
      this.form.username = stripscript(value);
      value = this.form.username;
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else if (validateName(value)) {
        callback(new Error("用户名为6至8位数字Or字母"));
      } else {
        callback();
      }
    };
    // 密码校验
    var validatePassword = (rule, value, callback) => {
      this.form.password = stripscript(value);
      value = this.form.password;
      if (value === "") {
        callback(new Error("请输入密码"));
      } else if (validatePass(value)) {
        callback(new Error("密码为6至20位数字Or字母"));
      } else {
        callback();
      }
    };
    return {
      // 登录注册切换
      // menuTab: [
      //   { txt: "登录", current: true },
      //   { txt: "注册", current: false }
      // ],
      // 表单返回值
      form: {
        username: "xiaohong",
        password: "123asd"
      },
      // 校验规则
      rules: {
        username: [{ validator: validateUsername, trigger: "blur" }],
        password: [{ validator: validatePassword, trigger: "blur" }]
      }
    };
  },
  methods: {
    // 登录、注册按钮切换
    // taggleMenu(data) {
    //   this.menuTab.forEach(elem => {
    //     elem.current = false;
    //   });
    //   data.current = true;
    // },
    // 表单提交按钮
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const param = {
            username: this.form.username,
            password: this.form.password
          };
          login(param).then(response => {
            const data = response.data;
            if (data.code === 200) {
              this.$message({
                message: "登录成功",
                type: "success"
              });
              sessionStorage.setItem("user", JSON.stringify(data.data));
              router.push("/console");
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
    // login() {
    //   let repuestData = {
    //     username: this.form.username,
    //     password: this.form.password
    //   };
    //   this.$store
    //     .dispatch("app/login", repuestData)
    //     .then(response => {
    //       // 页面跳转
    //       this.$router.push({
    //         name: "Console"
    //       });
    //     })
    //     .catch(error => {});
    // },
    // 重置按钮
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style lang="scss" scoped>
#login {
  height: 100vh;
  background-image: url("../../assets/login.jpg");
  background-repeat: no-repeat;
  background-size: 100%;
}
.login-warp {
  width: 330px;
  height: 100%;
  margin: auto;
}
// .menu-tab {
//   text-align: center;
//   li {
//     display: inline-block;
//     width: 88px;
//     line-height: 36px;
//     font-size: 14px;
//     color: #fff;
//     border-radius: 2px;
//     cursor: pointer;
//   }
//   .current {
//     background-color: rgba(0, 0, 0, 0.1);
//   }
// }
h1 {
  color: #fff;
  font-size: 30px;
  margin-bottom: 50px;
  position: relative;
  top: 20%;
}
.login-form {
  position: relative;
  top: 15%;
  left: -3%;
  label {
    display: block;
    font-size: 20px;
    color: #fff;
    position: relative;
    top: 40px;
    left: -230px;
    float: right;
  }
  button {
    margin-top: 30px;
    width: 100px;
    height: 50px;
    font-size: 18px;
    margin-right: 10px;
  }
}
</style>
