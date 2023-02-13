<template>
  <div class="theHeader banxin">
    <div class="right-menu">
      <div class="login" v-if="isLogin">
        <span @click="toRegister">注册</span>|<span @click="toLogin">登录</span>
      </div>
      <el-dropdown v-else placement="bottom-start" class="avatar-container">
        <div class="avatar-wrapper">
          <span class="na">{{ name }}</span>
        </div>
        <el-dropdown-menu style="margin-top: 10px;" slot="dropdown" class="user-dropdown">
          <el-dropdown-item @click.native="toMy">
            <span style="display:block;">个人中心</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog
      title="修改密码"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="closeDialog">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" v-loading='loading' label-width="100px" style="width: 90%;">
          <el-form-item prop="oldPassword" label="原密码" :label-width="this.formLabelWidth">
            <el-input clearable autocomplete="off" v-model="ruleForm.oldPassword" placeholder="请输入原密码"></el-input>
          </el-form-item>
          <el-form-item prop="newPassword" label="新密码" :label-width="this.formLabelWidth">
            <el-input clearable autocomplete="off" v-model="ruleForm.newPassword" placeholder="请输入新密码"></el-input>
          </el-form-item>
          <el-form-item prop="quePassword" label="确认密码" :label-width="this.formLabelWidth">
            <el-input clearable autocomplete="off" v-model="ruleForm.quePassword" placeholder="请确认密码"></el-input>
          </el-form-item>
        </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="edit('ruleForm')">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import bus from '@/utils/bus'
import Md5 from 'js-md5'
import {resetPassword} from '@/api/user'
export default {
  name: 'MyHeader',
  data() {
    return {
      name: 'admin',
      isLogin: false,
      dialogVisible: false,
      loading: false,
      ruleForm: {
        oldPassword: '',
        newPassword: '',
        quePassword: ''
      },
      rules: {
        oldPassword: [{
          required: true,
          message: '请输入原密码',
          trigger: 'blur'
        }],
        newPassword: [{
          required: true,
          message: '请输入新密码',
          trigger: 'blur'
        }, {
          pattern: /^[0-9A-Za-z_]{6,12}$/, //正则
          message: '请输入6-12位新密码,字母数字下划线组合'
        }],
        quePassword: [{
          required: true,
          message: '请确认密码',
          trigger: 'blur'
        }, {
          pattern: /^[0-9A-Za-z_]{6,12}$/, //正则
          message: '请输入6-12位新密码,字母数字下划线组合'
        }]
      },
      isCollapse: false
    }
  },
  created () {
    // 通过bus退出登录
    bus.$on('logout', () => {
      this.toDoLogout()
    })
    if (sessionStorage.getItem("UserInfo")) {
      this.isLogin = false
      this.name = JSON.parse(sessionStorage.getItem("UserInfo")).name
    } else {
      this.isLogin = true
    }
  },
  methods: {
    toMy () {
      this.$router.push('/theMy')
    },
    toRegister () {
      this.$router.push('/register')
    },
    toLogin () {
      this.$router.push('/login')
    },
    logout() {
      const that = this
      that.$confirm("是否退出登录?", "提示", {
        type: "warning"
      }).then(async () => {
        that.toDoLogout()
      }).catch(() => {
        return false
      })
    },
    toDoLogout () {
      sessionStorage.removeItem("UserInfo")
      this.isLogin = true
      this.$router.push('/')
    },
    openDialog () {
      this.dialogVisible = true
    },
    edit (ruleForm) {
      const that = this
      this.$refs["ruleForm"].validate(async (valid) => {
        if (valid) {
          if (that.ruleForm.newPassword !== that.ruleForm.quePassword) {
            return this.$message.error('新密码和确认密码输入不一致!')
          }
          const data = {
            id: JSON.parse(sessionStorage.getItem("UserInfo")).id,
            newPassword: Md5(that.ruleForm.newPassword),
            oldPassword: Md5(that.ruleForm.oldPassword)
          }
          try {
            let res = await resetPassword(data)
            if (res.data.code === 1) {
              that.$message.success('修改密码成功')
              that.closeDialog()
              that.toDoLogout()
            } else {
              that.$message.error(res.data.msg)
            }
          } catch (even) {
            that.$message.error(even.msg)
          }
        } else {
          return false
        }
      })
    },
    closeDialog () {
      this.ruleForm.oldPassword = ''
      this.ruleForm.newPassword = ''
      this.ruleForm.quePassword = ''
      this.$refs["ruleForm"].resetFields()
      this.dialogVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
.theHeader {
  height: 20px;
  line-height: 20px;
  position: relative;
  .right-menu {
    float: right;
    height: 100%;
    .login {
      color: #fff;
      span {
        padding: 0 5px;
        cursor: pointer;
        font-size: 14px;
      }
    }
    .avatar-container {
      height: 100%;
      width: auto;
      position: relative;
      .avatar-wrapper {
        color: #fff;
        font-size: 16px;
        height: 100%;
        .na {
          -webkit-user-select: none;
          user-select: none;
        }
      }
    }
  }
}
</style>
