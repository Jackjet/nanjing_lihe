<!-- 基本信息页 -->
<template>
  <div class="basicInfo">
    <el-form label-width="120px" class="customization-form" v-loading="loading">
      <el-form-item label="头像" :label-width="this.formLabelWidth">
        <img class="theLogo" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" />
      </el-form-item>
      <el-form-item label="昵称：" :label-width="this.formLabelWidth">
        <div>{{ ruleForm.liaisonName }}</div>
      </el-form-item>
      <el-form-item label="公司：" :label-width="this.formLabelWidth">
        <div>{{ ruleForm.enterpriseName }}</div>
      </el-form-item>
      <el-form-item label="邮箱：" :label-width="this.formLabelWidth">
        <div>{{ ruleForm.email }}</div>
      </el-form-item>
      <el-form-item label="电话： " :label-width="this.formLabelWidth">
        <div>{{ ruleForm.liaisonTel }}</div>
      </el-form-item>
      <!-- <el-form-item label="登录密码：" :label-width="this.formLabelWidth">
        <div>{{ ruleForm.unitType }}</div>
      </el-form-item> -->
      <el-form-item>
        <!-- <el-button style="margin-left: 20px;" size="mini" type="primary" @click="revise">修 改</el-button> -->
      </el-form-item>
    </el-form>
    <el-dialog
      title="用户验证"
      :visible.sync="isUser"
      width="40%" center>
      <el-form :model="phoneLogin" :rules="phoneRule" ref="phoneLogin" label-width="120px" class="customization-form" v-loading="loading1">
        <el-form-item label="用户名：" :label-width="this.formLabelWidth">
          <div>{{ ruleForm.liaisonName }}</div>
        </el-form-item>
        <el-form-item label="密码：" :label-width="this.formLabelWidth" prop="password">
          <el-input clearable maxlength="20" size="medium" v-model="phoneLogin.password" placeholder="请输入密码" show-password autocomplete="new-password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="theNext">下一步</el-button>
        <el-button  @click="closeOne">取 消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="用户验证"
      :visible.sync="changePhone"
      width="40%" center>
      <el-form :model="phoneChange" :rules="changeRule" ref="phoneLogin" label-width="120px" class="customization-form" v-loading="loading1">
        <el-form-item label="新手机号：" :label-width="this.formLabelWidth" prop="phone">
          <el-input maxlength='20' v-model="phoneChange.phone" placeholder="请输入新手机号" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="验证码：" prop="code" class="ask-code-box">
          <el-input maxlength='20' v-model="phoneChange.code" type="text" autocomplete="off" placeholder="验证码"/>
          <el-button :disabled="phoneChange.phone === '' || inDjs" size="medium" type="primary" @click="askCode">{{ btnLabel }}</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="toChange">提 交</el-button>
        <el-button  @click="closeTwo">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { userInfo, isTheUser, resetPhone } from '@/api/user'
import Md5 from 'js-md5'
export default {
  components: {},
  data () {
    return {
      loading: false,
      loading1: false,
      isUser: false,
      inDjs: false,
      changePhone: false,
      btnLabel: '获取验证码',
      ruleForm: {
        
      },
      phoneLogin: {
        password: ''
      },
      phoneRule: {
        password: [{required: true, message: "请输入密码", trigger: ['change', 'blur']}, {
          pattern: /^[0-9A-Za-z_]{6,12}$/, //正则
          message: '请输入6-12位新密码,字母数字下划线组合'
        }]
      },
      phoneChange: {
        phone: '',
        code: '',
      },
      changeRule: {
        phone: [{
          required: true,
          message: "请输入手机号",
          trigger: ['change', 'blur']
        }, {
          pattern: /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[1|8|9]))\d{8}$/,
          message: '手机号格式不正确'
        }],
        code: [{required: true, message: "请输入验证码", trigger: ['change', 'blur']}]
      }
    }
  },
  created () {},
  mounted () {},
  methods: {
    init () {
      this.askInfo()
    },
    askInfo () {
      const that = this
      try {
        that.loading = true
        userInfo({}).then(rep => {
          if (rep.data.code === 1) {
            switch (rep.data.data.unitType + '') {
              case '0':
                rep.data.data.unitType = '个人'
                break
              case '1':
                rep.data.data.unitType = '企业法人'
                break
              case '2':
                rep.data.data.unitType = '事业单位法人'
                break
              case '3':
                rep.data.data.unitType = '国家机关法人'
                break
              case '4':
                rep.data.data.unitType = '社会团体法人'
                break
            }
            let userType = '123456' + '' === '0' ? 'personal' : 'team'
            sessionStorage.setItem("userType", userType)
            that.ruleForm = rep.data.data
          } else {
            that.$message.error(rep.data.msg)
          }
        }).finally(() => {
          that.loading = false
        })
      } catch (even) {
        that.$message.error(even.msg)
      }
    },
    revise () {
      this.isUser = true
    },
    askCode () {
      this.$message.success('短信已发出')
      this.daojishi()
    },
    daojishi () {
      const that = this
      that.inDjs = true
      let shijian = 30
      let theDjs = setInterval(() => {
        that.btnLabel = shijian + 's'
        if (shijian <= 0) {
          that.inDjs = false
          that.btnLabel = '获取验证码'
          clearInterval(theDjs)
          return false
        }
        shijian--
      }, 1000);
    },
    closeOne () {
      this.isUser = false
      this.phoneLogin.password = ''
      this.$refs.phoneLogin.resetFields();
    },
    closeTwo () {
      this.changePhone = false
      this.phoneChange.phone = ''
      this.phoneChange.code = ''
      this.$refs.phoneChange.resetFields();
    },
    theNext () {
      this.$refs.phoneLogin.validate(valid => {
        if (valid) {
          that.isUser()
        } else {
          return false
        }
      })
    },
    isUser () {
      const that = this
      try {
        that.loading1 = true
        let data = {
          liaisonName: that.ruleForm.liaisonName,
          password: Md5(that.phoneLogin.password)
        }
        isTheUser(data).then(rep => {
          if (rep.data.code === 1) {
            that.closeOne()
            that.changePhone = true
          } else {
            that.$message.error(rep.data.msg)
          }
        }).finally(() => {
          that.loading1 = false
        })
      } catch (even) {
        that.$message.error(even.msg)
      }
    },
    toChange () {
      this.$refs.phoneChange.validate(valid => {
        if (valid) {
          that.change()
        } else {
          return false
        }
      })
    },
    change () {
      const that = this
      try {
        that.loading1 = true
        let data = {
          phone: that.phoneChange.phone,
          smsCode: that.phoneChange.code,
        }
        resetPhone(data).then(rep => {
          if (rep.data.code === 1) {
            that.$message.success('修改成功')
            that.closeTwo()
            that.init()
          } else {
            that.$message.error(rep.data.msg)
          }
        }).finally(() => {
          that.loading1 = false
        })
      } catch (even) {
        that.$message.error(even.msg)
      }
    }
  }
}
</script>
<style lang='scss' scoped>
.basicInfo {
  position: relative;
  padding-top: 50px;
  .el-button--primary {
    background-color: #354961;
    border-color: #354961;
    &:hover {
      background-color: #597291;
      border-color: #597291;
    }
  }
  .el-button--default {
    border-color: #354961;
    color: #354961;
  }
  .ask-code-box {
    width: 300px;
    margin-top: 20px;
    .el-button {
      position: absolute;
      left: 110%;
      top: 2px;
    }
  }

  .theLogo{
      width: 35px;
      border-radius: 50%;
      height: 35px;
  }

  .customization-form {
    width: 80%;
    margin: 0 auto;
    padding-bottom: 20px;
    /deep/ .el-form-item {
      margin-bottom: 0;
    }
    /deep/ .el-form-item__label {
      text-align: left;
      position: relative;
      &::before {
        position: absolute;
        left: -10px;
      }
    }
  }
}
</style>