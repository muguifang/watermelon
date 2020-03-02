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
        ></el-input>
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
    <el-dialog
      title="登录信息"
      :visible.sync="dialogLogin"
      width="30%"
      :before-close="close"
    >
      <el-form
        :model="loginForm"
        :rules="loginRules"
        ref="loginForm"
        status-icon
      >
        <el-form-item
          prop="username"
          label="账号:"
          :label-width="formLabelWidth"
        >
          <el-input
            placeholder="请输入账号"
            v-model="loginForm.username"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="password"
          label="密码:"
          :label-width="formLabelWidth"
        >
          <el-input
            placeholder="请输入密码"
            v-model="loginForm.password"
            type="password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="checked">7天自动登录</el-checkbox>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('loginForm')">重 置</el-button>
        <el-button type="primary" @click="submitLoginForm('loginForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <!-- 注册弹窗 -->
    <el-dialog title="注册信息" :visible.sync="dialogRegist" width="30%">
      <el-form
        :model="registForm"
        :rules="registRules"
        ref="registForm"
        status-icon
      >
        <el-form-item
          prop="username"
          label="账号:"
          :label-width="formLabelWidth"
        >
          <el-input
            placeholder="请输入账号"
            v-model="registForm.username"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="password"
          label="密码:"
          :label-width="formLabelWidth"
        >
          <el-input
            placeholder="请输入密码"
            v-model="registForm.password"
            type="password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="checkPwd"
          label="确认密码:"
          :label-width="formLabelWidth"
        >
          <el-input
            placeholder="请再次输入密码"
            v-model="registForm.checkPwd"
            type="password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="realname"
          label="真实姓名:"
          :label-width="formLabelWidth"
        >
          <el-input
            placeholder="请输入真实姓名"
            v-model="registForm.realname"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="phone"
          label="联系方式:"
          :label-width="formLabelWidth"
        >
          <el-input
            placeholder="请输入联系方式"
            v-model="registForm.phone"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item prop="email" label="邮箱:" :label-width="formLabelWidth">
          <el-input
            placeholder="请输入邮箱"
            v-model="registForm.email"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('registForm')">重 置</el-button>
        <el-button type="primary" @click="submitRegistForm('registForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <!-- 个人信息抽屉 -->
    <el-radio-group v-model="direction">
      <el-radio label="rtl" hidden="true">从右往左开</el-radio>
      <el-drawer
        title="我的信息"
        :visible.sync="drawer"
        :direction="direction"
        :before-close="handleClose"
      >
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
      </el-drawer>
    </el-radio-group>
  </div>
</template>
<script src="./head.js"></script>
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
