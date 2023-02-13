<!-- 用户注册页 -->
<template>
  <div class="banxin register">
    <div class="customization-box">
      <div class="title">
        <h2>注 册</h2>
      </div>
      <el-divider></el-divider>
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
    <el-dialog
      title="用户协议"
      :visible.sync="dialogVisible"
      width="50%" center>
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="agree">同 意</el-button>
        <el-button @click="dialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="用户协议"
      :visible.sync="dialogToLogin"
      width="50%" center>
      <span>注册成功，请登录</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="toLogin">跳转登录页</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { askCode, register } from '@/api/user'
import Md5 from 'js-md5'
export default {
  components: {},
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      isSubmitLoading: false,
      disabled: false,
      dialogVisible: false,
      isAgree: false,
      inDjs: false,
      dialogToLogin: false,
      theUrl: '',
      phoneCode: '',
      askCodeLabel: '获取验证码',
      unitTypeArr: [{ value: '0', label: '个人' }],
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
        password2: [{
          required: true,
          validator: validatePass,
          trigger: 'blur',
        }],
        verificationCode: [{
          required: true,
          message: "请输入图片验证码",
          trigger: ['change', 'blur'],
        }],
      },
    }
  },
  created() {
    this.creatNum()
  },
  mounted () {
    this.askCode()
  },
  methods: {
    creatNum () {
      this.ruleForm.verificationCodeId = Math.ceil(Math.random() * 1000);
    },
    async askCode () {
      const that = this
      try {
        let res = await askCode({verificationCodeId: that.ruleForm.verificationCodeId})
        if (res.data.code === 1) {
          that.theUrl = 'data:image/png;base64,' + res.data.data;
        } else {
          that.theUrl = '';
        }
      } catch (even) {
        that.$message.error(even.msg)
      }
    },
    submitForm () {
      const that = this
      that.$refs.ruleForm.validate((valid) => {
        if (valid) {
          // 取值
          let data = JSON.parse(JSON.stringify(that.ruleForm))
          data.userType = data.userType ? '1' : '0'
          data.password = Md5(data.password)
          that.isSubmitLoading = true
          register(data).then((res) => {
            that.isSubmitLoading = false
            if (res.data.code === 1) {
              that.$message.success('注册成功')
              that.dialogToLogin = true
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }
      })
    },
    typeChange () {
      this.idCard = '';
    },
    askIdcode () {
      this.$message.success('短信已发出')
      this.daojishi()
    },
    daojishi () {
      const that = this
      that.inDjs = true
      let shijian = 30
      let theDjs = setInterval(() => {
        that.askCodeLabel = shijian + 's'
        if (shijian <= 0) {
          that.inDjs = false
          that.askCodeLabel = '获取验证码'
          clearInterval(theDjs)
          return false
        }
        shijian--
      }, 1000);
    },
    changeType () {
      this.unitTypeArr.splice(0)
      if (this.ruleForm.userType) {
        this.unitTypeArr = [
          { value: '1', label: '企业' },
          { value: '2', label: '事业单位' },
          { value: '3', label: '国家机关' },
          { value: '4', label: '社会团体' },
        ]
        this.ruleForm.unitType = '1'
      } else {
        this.unitTypeArr = [
          { value: '0', label: '个人' }
        ]
        this.ruleForm.unitType = '0'
      }
    },
    changeImg () {
      this.askCode()
    },
    showAgreement () {
      this.dialogVisible = true
    },
    agree () {
      this.isAgree = true
      this.dialogVisible = false
    },
    toLogin () {
      this.$router.push('/login')
    }
  },
};
</script>
<style lang="scss" scoped>
.register {
  position: relative;
  margin-top: 20px;
  .el-form-item {
    margin-bottom: 20px;
  }
  .customization-box {
    background-color: #fff;
    border-radius: 5px;
    margin-bottom: 20px;
    padding: 0 20px;
  }
  .title {
    position: relative;
    h2 {
      text-align: center;
      font-size: 28px;
      line-height: 3;
      font-weight: bold;
    }
    .el-button {
      position: absolute;
      right: 0;
      bottom: 0;
    }
  }
  .customization-form {
    width: 80%;
    margin: 0 auto;
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