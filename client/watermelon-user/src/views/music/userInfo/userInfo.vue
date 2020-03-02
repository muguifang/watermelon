<template>
  <div id="collect-info">
    <div class="title-info">
      <el-divider content-position="left">个人信息</el-divider>
    </div>
    <template>
      <el-form
        :model="registForm"
        :rules="infoRules"
        ref="registForm"
        status-icon
      >
        <el-form-item prop="pic">
          <el-upload
            class="avatar-uploader"
            action="api/file/upload"
            name="photo"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img src="@/assets/photo.png" class="avatar" />
            <!-- <img v-if="imageUrl" :src="imageUrl" class="avatar"> -->
            <!-- <i v-else class="el-icon-plus avatar-uploader-icon"></i> -->
          </el-upload>
        </el-form-item>
        <el-form-item
          prop="username"
          label="账号:"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="registForm.username"
            autocomplete="off"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="realname"
          label="真实姓名:"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="registForm.realname"
            autocomplete="off"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item
          placeholder="请输入联系方式"
          prop="phone"
          label="联系方式:"
          :label-width="formLabelWidth"
        >
          <el-input v-model="registForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          placeholder="请输入邮箱"
          prop="email"
          label="邮箱:"
          :label-width="formLabelWidth"
        >
          <el-input v-model="registForm.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label-width="160px">
          <el-button @click="resetForm('registForm')">重 置</el-button>
          <el-button type="primary" @click="updateInfoForm('registForm')"
            >确 定</el-button
          >
        </el-form-item>
      </el-form>
    </template>
  </div>
</template>
<script src="./userInfo.js"></script>
<style lang="scss" scoped>
#collect-info {
  width: 50%;
  margin: 0 auto;
}
.title-info {
  width: 100%;
  margin: 0 auto;
  margin-bottom: 20px;
}
.el-divider {
  margin-top: 50px;
  margin-bottom: 50px;
  font-size: 22px;
}
.el-form {
  width: 50%;
  margin: 0 auto;
}
.el-form-item {
  // margin-left: 250px;
  margin-bottom: 25px;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
  margin-left: 200px;
}
</style>
