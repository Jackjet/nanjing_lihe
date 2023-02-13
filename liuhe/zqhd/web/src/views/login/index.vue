<template>
  <div class="login-container">
    <div class="left">
      <p style="color:#ccc;font-size:1.4rem;">南京六合经开区园区信息化平台</p>
      <p style="color:white;font-size:2rem;font-weight:900;">让企业数据产生价值</p>
      <p style="color:#ccc;font-size:0.9rem;">优化区域经济布局  提升产业发展水平</p>
    </div>
    <div class="right">
      <div class="center">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label=" 短信登录" name="first">
            <el-form ref="phoneLogin" :model="phoneLogin" :rules="phoneRules" class="login-form">
              <el-form-item prop="phone" class="item user_name">
                <span class="svg-container">
                  <i class="el-icon-mobile"/>
                </span>
                <el-input size="mini" maxlength='20' v-model="phoneLogin.phone" placeholder="请输入手机号" type="text" autocomplete="off"/>
              </el-form-item>
              <el-form-item prop="smsCode" class="item user_name ask-code-box">
                <el-input size="mini" maxlength='20' v-model="phoneLogin.smsCode" type="text" autocomplete="off" placeholder="验证码"/>
                <el-button :disabled="phoneLogin.phone=== '' || inDjs" size="medium" type="primary" @click="askIdcode">{{ askCodeLabel }}</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="密码登录" name="second">
            <el-form v-show="!isPhone" ref="pwdLogin" :model="pwdLogin" :rules="pwdRules" class="login-form">
              <el-form-item prop="phone" class="item user_name">
                <span class="svg-container">
                  <i class="el-icon-user"/>
                </span>
                <el-input size="mini" maxlength='20' v-model="pwdLogin.phone" placeholder="请输入账号" autocomplete="off"/>
              </el-form-item>
            <el-form-item prop="password" class="item user_name">
              <span class="svg-container">
                <i class="el-icon-lock"/>
              </span>
              <el-input size="mini" maxlength='20' v-model="pwdLogin.password" @keyup.enter.native="login" placeholder="请输入密码" show-password autocomplete="new-password"/>
            </el-form-item>
            <el-form-item prop="verificationCode" style="background:transform" class="item user_name">
              <el-row >
                <el-col span='14' style="background:white">
                   <el-input size="mini" maxlength='20' v-model="pwdLogin.verificationCode" @keyup.enter.native="login" placeholder="请输入验证码" />
                </el-col>
                <el-col span='10'>
                  <el-image @click="changeCode" :src='theUrl' style="margin-left:20%;margin-top:5px;cursor:pointer"></el-image>
                </el-col> 
              </el-row>
            </el-form-item>
           </el-form>
          </el-tab-pane>
        </el-tabs>
        <!-- <el-button type="text" @click="toRegister">立即注册</el-button>
        <el-button type="text" @click="toChange">忘记密码？</el-button> -->
        <div>
          <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:5px; height: 45px; margin-left: 0px;background:#38a1fe" @click.native.prevent="login">
            登录
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { login, askCode ,sendSms,smsSignIn} from '@/api/user'
import Md5 from 'js-md5'

export default {
  name: 'Login',
  components: {},
  data() {
    return {
      activeName: 'second',
      isPhone: false,
      inDjs: false,
      askCodeLabel: '获取验证码',
      theUrl: '',
      pwdLogin: {
        phone: '',
        password: '',
        verificationCodeId: '',
        verificationCode: '',
      },
      phoneLogin: {
        phone: '',
        code: ''
      },
      loading: false,
      pwdRules: {
        phone: [{required: true, message: "请输入账号", trigger: ['change', 'blur']}, {
          pattern: /^[A-Za-z0-9]+$/, //正则
          message: '请输入6-12位账号,字母数字下划线组合'
        }],
        password: [{required: true, message: "请输入密码", trigger: ['change', 'blur']}, {
          pattern: /^[0-9A-Za-z_]{6,12}$/, //正则
          message: '请输入6-12位新密码,字母数字下划线组合'
        }],
        verificationCode: [{required: true, message: "请输入验证码", trigger: ['change', 'blur']}],
      },
      phoneRules: {
        phone: [{required: true, message: "请输入手机号", trigger: ['change', 'blur']}, , {
          pattern: /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[1|8|9]))\d{8}$/,
          message: '手机号格式不正确'
        }],
        smsCode: [{required: true, message: "请输入验证码", trigger: ['change', 'blur']}],
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
    changeCode(){
      this.creatNum()
      this.askCode()
    },
    creatNum () {
      this.pwdLogin.verificationCodeId = Math.ceil(Math.random() * 1000);
    },
    change () {
      this.isPhone = !this.isPhone
    },
    toRegister () {
      this.$router.push('/register')
    },
    toChange () {
      this.$router.push('/changePwd')
    },
    async askIdcode () {
      const that = this
      let data = {}
      data.phone = this.phoneLogin.phone
      data.smsToken = Md5(this.phoneLogin.phone+'tokenKey')
      console.log(data,this.phoneLogin.phone+'tokenKey')
      try {
        let res = await sendSms(data)
        if (res.data.code === 1) {

        } else {
          that.theUrl = '';
        }
      } catch (even) {
        that.$message.error(even.msg)
      }
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
    login() {
      const that = this
      if(this.activeName == 'first'){
        that.$refs.phoneLogin.validate(valid => {
          if (valid) {
            that.handleLogin()
          } else {
            return false
          }
        })
      }else{
        that.$refs.pwdLogin.validate(valid => {
          if (valid) {
            that.handleLogin()
          } else {
            return false
          }
        })
      }
    },
    async askCode () {
      const that = this
      try {
        let res = await askCode({verificationCodeId: that.pwdLogin.verificationCodeId})
        if (res.data.code === 1) {
          that.theUrl = 'data:image/png;base64,' + res.data.data;
        } else {
          that.theUrl = '';
        }
      } catch (even) {
        that.$message.error(even.msg)
      }
    },
    handleLogin(left) {
      console.log(111111)
      let that = this
      if(this.activeName == 'first'){
        let parms = Object.assign({}, this.phoneLogin)
        this.loading = true
        smsSignIn(parms).then(rep => {
          if (rep.data.code === 1) {
            sessionStorage.setItem("UserInfo", JSON.stringify(rep.data.data))
            that.$router.push('/')
          } else {
            that.$message.error(rep.data.msg)
            if(rep.data.msg == '登录失败：验证码无效'){
              this.pwdLogin.verificationCode = ''
              this.changeCode()
            }
          }
        }).finally(() => {
          that.loading = false
        })
      }else{
        let parms = Object.assign({}, this.pwdLogin)
        parms.password = Md5(parms.password)
        this.loading = true
        login(parms).then(rep => {
          if (rep.data.code === 1) {
            sessionStorage.setItem("UserInfo", JSON.stringify(rep.data.data))
            that.$router.push('/')
          } else {
            that.$message.error(rep.data.msg)
            if(rep.data.msg == '登录失败：验证码无效'){
              this.pwdLogin.verificationCode = ''
              this.changeCode()
            }
          }
        }).finally(() => {
          that.loading = false
        })
      }
    }
  }
}
</script>
<style lang="scss" scoped>

$bg: #fff;
$light_gray: #000;
$cursor: #000;
/deep/ .el-tabs__item{
  width: 100%;
  text-align: center;
  color: white;
}
/* reset element-ui css */
.login-container {
  position: relative;
  height: 100%;
  width: 100%;
  background: url('../../assets/image/loginBack.png');
  background-size: 100% 100%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  .left{
    width: 60%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    p{
      line-height: 3rem;
      letter-spacing: 0.2rem;
    }
    p:nth-child(2){
      letter-spacing: 0.6rem;
    }
  }
  .right{
    background: rgba(4,58,149,.3);
    border-radius: 0.6rem;
    border: 1px solid #043a95;
    width: 30rem;
    margin-right: 10%;
    height: 400px;
    padding: 1.3rem;
    box-sizing: border-box;
  }
  .center {
    background: #043a95;
    width: 100%;
    height: 100%;
    padding: 3rem 1.8rem;
    box-sizing: border-box;
  }
  /deep/ .el-form-item__content {
    line-height: 20px;
  }
  .login-form {
    position: relative;
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
      width: 60%;
      position: relative;
      .el-button {
        position: absolute;
        left: 110%;
        top: 2px;
      }
    }
  }
  /deep/ .el-button--primary {
    background: #38a1fe;
    border: none;
    &:focus {
      background: #30435a;
    }
    &:hover {
      background: #30435a;
    }
  }
  /deep/ .el-button--text {
    color: white;
    &:focus {
      color: #38a1fe;
    }
    &:hover {
      color: #38a1fe;
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
  // max-width: 360px;
}

/deep/ .el-checkbox__inner is-checked {
  background: #409EFF;
}

</style>
