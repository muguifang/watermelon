<template>
  <div id="advice-info">
    <div class="title-info">
      <el-divider content-position="center">网站建议</el-divider>
    </div>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="建议内容" prop="content">
        <el-input type="text" v-model="ruleForm.content" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="phone">
        <el-input v-model.number="ruleForm.phone"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button style="margin-left: 90px;" type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    var checkContent = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入内容"));
      } else {
        if (this.ruleForm.content !== "") {
          this.$refs.ruleForm.validateField("content");
        }
        callback();
      }
    };
    var checkPhone = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入手机号"));
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
        content: [ { validator: checkContent, trigger: 'blur' } ],
        phone: [
          { validator: checkPhone, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>
<style lang="scss" scoped>
#advice-info {
  width: 30%;
  margin: 0 auto;
}
.title-info {
  width: 100%;
  height: 50px;
  margin: 0 auto;
  margin-bottom: 20px;
}
label {
  font-size: 22px;
  letter-spacing: 3px;
  margin-left: 10px;
}
.flag {
  width: 8px;
  height: 8px;
  border: 8px solid red;
  border-radius: 3px;
  float: left;
}
.el-form-item {
  width: 100%;
  margin-bottom: 30px;
}
.el-divider {
  margin-top: 50px;
  font-size: 22px;
}
</style>
