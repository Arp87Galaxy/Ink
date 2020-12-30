<template>
  <div class="login">
    <el-form
      :model="dataForm"
      status-icon
      label-width="80px"
      :rules="rules"
      ref="dataForm"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="dataForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="dataForm.password"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="cap_code">
        <el-row :gutter="40">
          <el-col :span="11">
            <el-input v-model="dataForm.cap_code"></el-input>
          </el-col>
          <el-col :span="11">
            <img :src="captcha" lazy :fit="fit" @click="getCaptcha()" />
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm('dataForm')"
          >登录</el-button
        >
        <el-button @click="resetForm('dataForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {setAuthToken} from '@/utils/index.js'
export default {
  name: "Login",
  data() {
    return {
      captcha: "",
      cap_count: 0,
      fit: "fill",
      dataForm: {
        username: "",
        password: "",
        cap_code: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 3,
            max: 11,
            message: "长度在 3 到 11 个字符",
            trigger: "blur",
          },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        cap_code: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
      },
      sizeForm: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
    };
  },
  methods: {
    getCaptcha() {
      console.log("getCaptcha");
      this.cap_count += 1;
      this.captcha = this.$http.adornUrl(
        "/captcha.jpg?cap_count=" + this.cap_count
      );
    },
    submitForm(formName) {
      console.log("submitForm");
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: "/core/sys/login",
            method: "post",
            data: {
              username: this.dataForm.username,
              password: this.dataForm.password,
              cap_code: this.dataForm.cap_code,
            },
          })
            .then((data) => {
              console.log(data.data);
              //刷新页面
              // this.$router.go(0)
              if (
                data.data.data !== "" &&
                data.data.data !== undefined &&
                data.data.data !== null
              ) {

                setAuthToken(data.data.data)
                this.$router.push({ path: "/main", name: "main" });
              } else {
                this.$router.go(0);
                throw new Error([data.data.message]);
              }
            })
            .catch((e) => {
              alert(e);
              console.log(e);
              this.$router.go(0);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
  created: function () {
    console.log("created");
    this.getCaptcha();
  },
  // beforeRouteEnter(to, from, next) {
  //   console.log("beforeEnter");
  //   next((vm) => {
  //     console.log("ok");
  //     console.log(vm);
  //     console.log(localStorage.getItem("userStatus"))
  //     if (localStorage.getItem("userStatus") == 1) {
  //       console.log("已登录");
  //       vm.$router.push({ path: "/main", name: "main" });
  //     }
  //   });
  // },
};
</script>

<style scoped>
.login {
  width: 26%;
  position: relative;
  top: 100px;
  left: 37%;
}
/* .captcha >>> .el-form-item__content {
  height: 500px;
} */  
.captcha >>> .el-input__inner {
  width: 100%;
}
.cap_img {
  width: 100%;
}
</style>