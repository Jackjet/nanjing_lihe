<!-- 个人认证 -->
<template>
  <div class="personal">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="customization-form" v-loading="isSubmitLoading">
      <el-form-item label="用户名：" :label-width="this.formLabelWidth" prop="account">
        <el-input clearable maxlength="100" size="medium" v-model="ruleForm.account" placeholder="6-20个字符，第一个字符必须是字母，支持数字、字母下划线组合"></el-input>
      </el-form-item>
      <el-form-item :label="ruleForm.userType ? '企业名称：' : '真实姓名：'" :label-width="this.formLabelWidth" prop="userName">
        <el-input clearable maxlength="100" size="medium" v-model="ruleForm.userName" :placeholder="ruleForm.userType ? '请输入企业名称：' : '请输入真实姓名：'"></el-input>
      </el-form-item>
      <el-form-item :label="ruleForm.userType ? '社会信用代码：' : '证件信息：'" :label-width="this.formLabelWidth" prop="idCard">
        <el-select v-show="!ruleForm.userType" @change="typeChange" v-model="ruleForm.idType" style="width: 200px; margin-right: 20px;">
          <el-option key="1" label="身份证" value="1"></el-option>
          <el-option key="2" label="护照" value="2"></el-option>
          <el-option key="3" label="学生证" value="3"></el-option>
          <el-option key="4" label="军官证" value="4"></el-option>
        </el-select>
        <el-input :style="ruleForm.userType ? {width: '100%'} : {width: 'calc(100% - 220px)'}" clearable maxlength="100" size="medium" v-model="ruleForm.idCard"></el-input>
      </el-form-item>
      <el-form-item label="单位类型：" :label-width="this.formLabelWidth" prop="unitType">
        <el-select v-model="ruleForm.unitType" style="width: 100%;">
          <el-option
            v-for="item in unitTypeArr"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="手机号：" :label-width="this.formLabelWidth" prop="phone">
        <el-input clearable size="medium" v-model="ruleForm.phone" placeholder="请输入您的手机号码"></el-input>
      </el-form-item>
      <el-form-item label="" :label-width="this.formLabelWidth">
        <el-input clearable size="medium" style="width: 200px; margin-right: 20px;" v-model="phoneCode" placeholder="请输入短信验证码"></el-input>
        <el-button :disabled="ruleForm.phone === '' || inDjs" type="primary" size="medium" @click="askIdcode">{{ askCodeLabel }}</el-button>
      </el-form-item>
      <el-form-item label="设置密码：" :label-width="this.formLabelWidth" prop="password">
        <el-input clearable maxlength="100" size="medium" v-model="ruleForm.password" placeholder="请输入密码" show-password autocomplete="new-password"></el-input>
      </el-form-item>
      <el-form-item label="" :label-width="this.formLabelWidth" prop="password2">
        <el-input clearable maxlength="100" size="medium" v-model="ruleForm.password2" placeholder="请确认密码" show-password autocomplete="new-password"></el-input>
      </el-form-item>
      <el-form-item label="" :label-width="this.formLabelWidth" prop="verificationCode">
        <div style="display: flex;">
          <el-input clearable size="medium" style="width: 200px; margin-right: 20px;" v-model="ruleForm.verificationCode" placeholder="请输入验证码"></el-input>
          <span @click="changeImg"><el-image style="width: 100px; height: 40px" :src="theUrl" fit="fill"></el-image></span>
        </div>
      </el-form-item>
      <el-form-item label="" :label-width="this.formLabelWidth">
        <el-checkbox @change="changeType" v-model="ruleForm.userType">我是团体用户</el-checkbox>
      </el-form-item>
      <el-form-item label="" :label-width="this.formLabelWidth">
        <el-checkbox v-model="isAgree">我已阅读并同意 <el-button type="text" @click="showAgreement">《用户协议》</el-button></el-checkbox>
      </el-form-item>
      <div class="demo-footer">
        <el-button size="medium" :disabled="!isAgree" type="primary" @click="submitForm">注 册</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  components: {},
  data () {
    return {
      ruleForm: {
        account: "",
        userName: "",
        idType: "1",
        idCard: "",
        unitType: "0",
        phone: "",
        userType: false,
        password: "",
        password2: "",
        verificationCode: "",
        verificationCodeId: '',
      },
      formLabelWidth: "120px",
      rules: {
        account: [{
          required: true,
          message: "请输入用户名",
          trigger: ['change', 'blur'],
        }, {
          pattern: /^[0-9A-Za-z_]{6,12}$/,
          message: '请输入格式为6-12位,包含字母数字下划线组合'
        }],
        userName: [{
          required: true,
          message: "请输入真实姓名",
          trigger: ['change', 'blur'],
        }],
        idCard: [{
          required: true,
          message: "请输入证件信息",
          trigger: 'blur',
        }],
        unitType: [{
          required: true,
          message: "请选则单位类型",
          trigger: ['change', 'blur'],
        }],
        phone: [{
          required: true,
          message: "请输入手机号",
          trigger: ['change', 'blur'],
        }, {
          pattern: /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[1|8|9]))\d{8}$/,
          message: '手机号格式不正确'
        }],
        password: [{
          required: true,
          message: "请设置密码",
          trigger: ['change', 'blur'],
        }, {
          pattern: /^[0-9A-Za-z_]{6,12}$/,
          message: '请输入6-12位密码,字母数字下划线组合'
        }],
        verificationCode: [{
          required: true,
          message: "请输入图片验证码",
          trigger: ['change', 'blur'],
        }],
      },
    }
  },
  created () {},
  mounted () {},
  methods: {}
}
</script>
<style lang='scss' scoped>
.personal {
  position: relative;
  .customization-form {
    width: 80%;
    margin: 20px auto 0;
    padding-bottom: 20px;
    /deep/ .el-form-item__label {
      text-align: left;
      position: relative;
      &::before {
        position: absolute;
        left: -10px;
      }
    }
    /deep/ .el-radio__input {
      display: none;
    }
    /deep/ .el-icon-view {
      display: none;
    }
    .demo-footer {
      text-align: center;
      .el-button {
        padding: 10px 60px;
      }
      .el-button--primary {
        background-color: #354961;
        border-color: #354961;
        &:hover {
          background-color: #597291;
          border-color: #597291;
        }
      }
    }
  }
}
</style>