<!-- 修改密码页 -->
<template>
  <div class="setPwd">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="customization-form">
        <el-form-item label="旧密码：" :label-width="this.formLabelWidth" prop="oldPwd">
          <el-input clearable maxlength="100" size="medium" v-model="ruleForm.oldPwd" placeholder="请输入旧密码"></el-input>
        </el-form-item>
        <el-form-item label="新密码：" :label-width="this.formLabelWidth" prop="newPwd">
          <el-input clearable maxlength="100" size="medium" v-model="ruleForm.newPwd" placeholder="请输入新密码"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码：" :label-width="this.formLabelWidth" prop="confirmPwd">
          <el-input clearable maxlength="100" size="medium" v-model="ruleForm.confirmPwd" placeholder="请确认新密码"></el-input>
        </el-form-item>
        <div class="demo-footer">
          <el-button v-loading="loading" size="medium" type="primary" @click="submit">提 交</el-button>
        </div>
      </el-form>
  </div>
</template>

<script>
import { resetPwd } from '@/api/user'
import Md5 from 'js-md5'
export default {
  components: {},
  data () {
    return {
      loading: false,
      ruleForm: {
        oldPwd: '',
        newPwd: '',
        confirmPwd: '',
      },
      rules: {
        oldPwd: [
          {
            required: true,
            message: "请输入旧密码",
            trigger: ["change", "blur"],
          },
        ],
        newPwd: [
          {
            required: true,
            message: "请输入新密码",
            trigger: ["change", "blur"],
          },
        ],
        confirmPwd: [
          {
            required: true,
            message: "请确认新密码",
            trigger: ["change", "blur"],
          },
        ],
      },
    }
  },
  created () {},
  mounted () {},
  methods: {
    init () {
      const that = this
      that.ruleForm.oldPwd = ''
      that.ruleForm.newPwd = ''
      that.ruleForm.confirmPwd = ''
      that.$refs.ruleForm.resetFields();
    },
    doChange () {
      let that = this
      let data = {
        password: Md5(that.ruleForm.oldPwd),
        newPassword: Md5(that.ruleForm.newPwd)
      }
      that.loading = true
      resetPwd(data).then(rep => {
        if (rep.data.code === 1) {
          that.$message.success('修改成功')
          that.init()
        } else {
          that.$message.error(rep.data.msg)
        }
      }).finally(() => {
        that.loading = false
      })
    },
    submit() {
      const that = this
      that.$refs.ruleForm.validate(valid => {
        if (valid) {
          that.doChange()
        } else {
          return false
        }
      })
    },
  }
}
</script>
<style lang='scss' scoped>
.setPwd {
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