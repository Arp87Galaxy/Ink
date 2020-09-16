<template>
  <div class="login">
    <el-form  :model="ruleForm" status-icon label-width="80px" :rules="rules" ref="ruleForm">
      <el-form-item label="用户名" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="cap_code">
        <el-row :gutter="40">
          <el-col :span="11">
            <el-input v-model="ruleForm.cap_code"></el-input>
          </el-col>
          <el-col :span="11">
            <el-image :src="captcha" :fit="fit"></el-image>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      captcha: "",
      fit: 'fill',
      ruleForm: {
        name: '',
        password: '',
        cap_code: '',
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 3,
            max: 11,
            message: "长度在 3 到 11 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
        ],
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
  },
  created: function () {
    this.captcha = this.$http.adornUrl("/captcha.jpg");
    // this.$http({
    //   url: "/captcha.jpg",
    //   method: "get",
    // }).then(data => {
    //     console.log(data.data)
    //     this.captcha=data.data
    //     // this.$router.replace({name: 'home'})
    //     console.log(window)
    // });
  },
}
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