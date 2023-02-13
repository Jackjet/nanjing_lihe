<template>
  <div class="login-container">
    <img class="login-logo" src="../../assets/image/login_logo.png" />
    <div class="center">
      <img class="login-img" src="../../assets/image/login_img.png" />
      <div class="title-container">
        <img class="theLogo" src="../../assets/image/logo.png" />
      </div>
      <el-form v-show="!isPhone" ref="havePhone" :model="havePhone" :rules="phoneRules" class="login-form">
        <el-form-item prop="phone" class="item user_name">
          <span class="svg-container">
            <i class="el-icon-mobile"/>
          </span>
          <el-input size="mini" maxlength='100' v-model="havePhone.phone" type="text" autocomplete="off" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item prop="code" class="item user_name ask-code-box">
          <el-input size="mini" maxlength='100' v-model="havePhone.code" type="text" autocomplete="off" placeholder="验证码"/>
          <el-button :disabled="havePhone.phone=== '' || inDjs" size="medium" type="primary" @click="askIdcode">{{ askCodeLabel }}</el-button>
        </el-form-item>
      </el-form>
      <el-form v-show="isPhone" ref="pwdChange" :model="pwdChange" :rules="pwdRules" class="login-form">
        <el-form-item prop="one" class="item user_name">
          <span class="svg-container">
            <i class="el-icon-lock"/>
          </span>
          <el-input size="mini" maxlength='20' v-model="pwdChange.one" placeholder="请输入新密码" type="text" show-password autocomplete="new-password"/>
        </el-form-item>
        <el-form-item prop="two" class="item user_name">
          <span class="svg-container">
            <i class="el-icon-lock"/>
          </span>
          <el-input size="mini" maxlength='20' v-model="pwdChange.two" placeholder="请再次输入新密码" type="text" show-password autocomplete="new-password"/>
        </el-form-item>
      </el-form>
      <div>
        <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:5px; height: 45px; margin-left: 0px;" @click.native.prevent="theNext">
          {{ btnLabel }}
        </el-button>
      </div>
      <el-button type="text" @click="toLogin">登录</el-button>
    </div>
    <div class="login-foot">
      <img src="../../assets/image/login_bottom.png">
      <el-divider direction="vertical"></el-divider>
      <div class="foot-text">
        <p><span>版权所有 神州数码控股有限公司</span><span style="margin-left: 20px;">技术服务热线 010-82707888-2</span> </p>
        <p>AII contents are Copyright © Digital China Holdings Limited .All rights reserved</p>
      </div>
    </div>
  </div>
</template>

<script>
import { resetPwdByPhone, isPhone } from '@/api/user'
import Md5 from 'js-md5'

export default {
  name: 'Login',
  components: {},
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.pwdChange.one) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      isPhone: false,
      inDjs: false,
      askCodeLabel: '获取验证码',
      btnLabel: '下一步',
      temporaryToken: '',
      havePhone: {
        phone: '',
        code: '',
      },
      pwdChange: {
        one: '',
        two: ''
      },
      loading: false,
      phoneRules: {
        phone: [{required: true, message: "请输入手机号", trigger: ['change', 'blur']}, , {
          pattern: /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[1|8|9]))\d{8}$/,
          message: '手机号格式不正确'
        }],
        code: [{required: true, message: "请输入验证码", trigger: ['change', 'blur']}],
      },
      pwdRules: {
        one: [{required: true, message: "请输入密码", trigger: ['change', 'blur']}, {
          pattern: /^[0-9A-Za-z_]{6,12}$/, //正则
          message: '请输入6-12位账号,字母数字下划线组合'
        }],
        two: [{ required: true, validator: validatePass, trigger: 'blur', }],
      },
    }
  },
  created() {},
  mounted () {},
  methods: {
    toLogin () {
      this.$router.push('/login')
    },
    theNext () {
      const that = this
      if (that.isPhone) {
        that.$refs.pwdChange.validate(valid => {
          if (valid) {
            that.toChange()
          } else {
            return false
          }
        })
      } else {
        that.$refs.havePhone.validate(valid => {
          if (valid) {
            that.askIsPhone()
          } else {
            return false
          }
        })
      }
    },
    toChange () {
      let that = this
      that.loading = true
      let data = {
        phone: that.havePhone.phone,
        token: that.temporaryToken,
        password: Md5(that.pwdChange.one),
      }
      resetPwdByPhone(data).then(rep => {
        if (rep.data.code === 1) {
          that.$message.success('密码重置成功')
          setTimeout(() => {
            that.$router.push('/login')
          }, 500)
        } else {
          that.$message.error(rep.data.msg)
          that.init()
        }
      }).finally(() => {
        that.loading = false
      })
    },
    init () {
      this.havePhone.phone = ''
      this.havePhone.code = ''
      this.$refs.havePhone.resetFields()
      this.btnLabel = '下一步'
      this.isPhone = !this.isPhone
      this.pwdChange.one = ''
      this.pwdChange.two = ''
      this.$refs.pwdChange.resetFields()
      this.temporaryToken = ''
    },
    askIsPhone () {
      let that = this
      that.loading = true
      isPhone({phone: that.havePhone.phone}).then(rep => {
        if (rep.data.code === 1) {
          that.btnLabel = '修 改'
          that.isPhone = !that.isPhone
          that.temporaryToken = rep.data.msg
        } else {
          that.$message.error(rep.data.msg)
        }
      }).finally(() => {
        that.loading = false
      })
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
  }
}
</script>
<style lang="scss" scoped>

$bg: #fff;
$light_gray: #000;
$cursor: #000;

/* reset element-ui css */
.login-container {
  position: relative;
  height: 100%;
  width: 100%;
  min-height: 550px;
  min-width: 1100px;
  background: url('../../assets/image/bg.jpg');
  background-size: 100% 100%;
  overflow: hidden;
  .login-logo {
    position: absolute;
    top: 20px;
    right: 20px;
    width: 100px;
    height: auto;
  }
  .center {
    background: url('../../assets/image/login_bg.jpg');
    width: 1000px;
    height: 500px;
    padding: 80px 40px;
    box-sizing: border-box;
    position: absolute;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    .login-img {
      height: 360px;
      width: auto;
      position: absolute;
      right: 50px;
      top: 60px;
    }
    .title-container {
      position: relative;
      max-width: 360px !important;
      text-align: center;
      padding-bottom: 40px;
      img {
        width: 260px;
      }
    }
  }
  /deep/ .el-form-item__content {
    line-height: 20px;
  }
  .login-form {
    position: relative;
    width: 500px;
    max-width: 100%;
    overflow: hidden;

    /deep/ .el-form-item {
      border: 1px solid rgb(192, 189, 189);
      background: #fff;
      border-radius: 5px;
      color: #000;
      position: relative;
    }
    .ask-code-box {
      width: 200px;
      .el-button {
        position: absolute;
        left: 110%;
        top: 2px;
      }
    }
  }
  /deep/ .el-button--primary {
    background: #384F6A;
    border: none;
    &:focus {
      background: #30435a;
    }
    &:hover {
      background: #30435a;
    }
  }
  /deep/ .el-button--text {
    color: #6480A2;
    &:focus {
      color: #67798d;
    }
    &:hover {
      color: #67798d;
    }
  }
  /deep/ .el-input {
    display: inline-block;
    height: 35px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 2px 5px 2px 15px;
      color: $light_gray;
      height: 35px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }
  .login-foot {
    position: absolute;
    left: 50%;
    bottom: 20px;
    transform: translate(-50%, 0);
    text-align: center;
    img {
      height: 40px;
      width: auto;
      vertical-align: middle;
    }
    .el-divider {
      height: 40px;
      width: 2px;
      margin: 0 20px;
      background-color: #6D8AAE;
    }
    .foot-text {
      vertical-align: middle;
      line-height: 1;
      display: inline-block;
      color: #6B7E97;
      font-size: 12px;
      text-align: left;
    }
  }
}


/deep/ .el-button--primary:hover, .el-button--primary:focus {
  background: #66B1FF;
}

.svg-container {
  padding: 6px 5px 6px 15px;
  color: #889aa4;
  vertical-align: middle;
  width: 30px;
  display: inline-block;
  box-sizing: border-box;
}


.tips {
  font-size: 14px;
  color: #000;
  cursor: pointer;

  :hover {
    color: #008080;
  }
}
.el-button,
.user_name {
  max-width: 360px;
}

/deep/ .el-checkbox__inner is-checked {
  background: #409EFF;
}

</style>
