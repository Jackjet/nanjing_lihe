<template>
  <div class="theHeader">
    <el-breadcrumb separator='/' class="the-breadcrumb">
      <el-breadcrumb-item style="cursor: pointer;" v-for="(item, index) in menuData" :index="index" :key="index">
        <span @click.prevent="clickMenu(item)">{{ item.title }}</span>
      </el-breadcrumb-item>
      <el-breadcrumb-item style="cursor: pointer;" v-for="(item, index) in routerData" :index="index" :key="index">
        <span @click.prevent="clickRouter(item)">{{ item.title }}</span>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <div class="right-menu">
      <el-dropdown placement="bottom-start" class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <svg t="1600311663297" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5329" width="35" height="40">
            <path d="M512 148.5c49.1 0 96.7 9.6 141.5 28.5 43.3 18.3 82.2 44.5 115.6 77.9 33.4 33.4 59.6 72.3 77.9 115.6 18.9 44.8 28.5 92.4 28.5 141.5s-9.6 96.7-28.5 141.5c-18.3 43.3-44.5 82.2-77.9 115.6-33.4 33.4-72.3 59.6-115.6 77.9-44.8 18.9-92.4 28.5-141.5 28.5s-96.7-9.6-141.5-28.5c-43.3-18.3-82.2-44.5-115.6-77.9-33.4-33.4-59.6-72.3-77.9-115.6-18.9-44.8-28.5-92.4-28.5-141.5s9.6-96.7 28.5-141.5c18.3-43.3 44.5-82.2 77.9-115.6s72.3-59.6 115.6-77.9c44.8-18.9 92.4-28.5 141.5-28.5m0-50C283.6 98.5 98.5 283.6 98.5 512S283.6 925.5 512 925.5 925.5 740.4 925.5 512 740.4 98.5 512 98.5z" p-id="5330" fill="#606266"></path>
            <path d="M512 401m-106 0a106 106 0 1 0 212 0 106 106 0 1 0-212 0Z" p-id="5331" fill="#606266"></path>
            <path d="M514.5 540.8c-105.1 0-195 68.8-231.6 166.2 28.2 27.9 60.4 50.1 95.9 65.9 40.2 18 83 27.1 127.1 27.1s86.9-9.1 127.1-27.1c38.9-17.4 73.8-42.3 103.8-74.1 1.4-1.5 2.7-3 4.1-4.4-39.2-90.6-125.8-153.6-226.4-153.6z" p-id="5332" fill="#606266"></path>
          </svg>
          <span class="na">{{ name }}</span>
        </div>
        <el-dropdown-menu style="margin-top: 10px;" slot="dropdown" class="user-dropdown">
          <el-dropdown-item @click.native="openDialog">
            <span style="display:block;">????????????</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">????????????</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog title="????????????" :visible.sync="dialogVisible" width="40%" :before-close="closeDialog">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" v-loading='loading' label-width="100px" style="width: 90%;">
        <el-form-item prop="oldPassword" label="?????????" :label-width="this.formLabelWidth">
          <el-input clearable autocomplete="off" v-model="ruleForm.oldPassword" placeholder="??????????????????"></el-input>
        </el-form-item>
        <el-form-item prop="newPassword" label="?????????" :label-width="this.formLabelWidth">
          <el-input clearable autocomplete="off" v-model="ruleForm.newPassword" placeholder="??????????????????"></el-input>
        </el-form-item>
        <el-form-item prop="quePassword" label="????????????" :label-width="this.formLabelWidth">
          <el-input clearable autocomplete="off" v-model="ruleForm.quePassword" placeholder="???????????????"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog">??? ???</el-button>
          <el-button type="primary" @click="edit('ruleForm')">??? ???</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import bus from '@/utils/bus'
import Md5 from 'js-md5'
import { resetPassword } from '@/api/user'
export default {
  name: 'MyHeader',
  data() {
    return {
      name: 'admin',
      dialogVisible: false,
      loading: false,
      menuData: [{
        title: '??????',
        path: '/homePage'
      }],
      routerData: [],
      ruleForm: {
        oldPassword: '',
        newPassword: '',
        quePassword: ''
      },
      rules: {
        oldPassword: [{
          required: true,
          message: '??????????????????',
          trigger: 'blur'
        }],
        newPassword: [{
          required: true,
          message: '??????????????????',
          trigger: 'blur'
        }, {
          pattern: /^[0-9A-Za-z_]{6,12}$/, //??????
          message: '?????????6-12????????????,???????????????????????????'
        }],
        quePassword: [{
          required: true,
          message: '???????????????',
          trigger: 'blur'
        }, {
          pattern: /^[0-9A-Za-z_]{6,12}$/, //??????
          message: '?????????6-12????????????,???????????????????????????'
        }]
      },
      isCollapse: false
    }
  },
  created() {
    // ??????sessionStorage???????????????????????????
    if (sessionStorage.getItem("theBreadcrumb1")) {
      bus.$emit('menuData', JSON.parse(sessionStorage.getItem("theBreadcrumb1")))
      this.menuData = JSON.parse(sessionStorage.getItem("theBreadcrumb1"))
    }
    if (sessionStorage.getItem("theBreadcrumb2")) {
      bus.$emit('routerData', JSON.parse(sessionStorage.getItem("theBreadcrumb2")))
      this.routerData = JSON.parse(sessionStorage.getItem("theBreadcrumb2"))
    }
    // ??????bus??????????????????
    bus.$on('menuData', msg => {
      if (JSON.stringify(this.menuData) !== JSON.stringify(msg)) {
        this.routerData.splice(0)
        sessionStorage.setItem("theBreadcrumb2", JSON.stringify(this.routerData))
        this.menuData = msg
        sessionStorage.setItem("theBreadcrumb1", JSON.stringify(this.menuData))
      }
    })
    bus.$on('routerData', msg => {
      this.routerData = msg
      sessionStorage.setItem("theBreadcrumb2", JSON.stringify(this.routerData))
    })
    // ??????bus??????????????????
    bus.$on('logout', () => {
      this.toDoLogout()
    })
    this.name = JSON.parse(sessionStorage.getItem("UserInfo")).name
  },
  methods: {
    // ??????????????????????????????
    clickMenu(item) {
      if (this.$route.path === item.path) {
        return false
      }
      this.routerData.splice(0)
      sessionStorage.setItem("theBreadcrumb2", JSON.stringify(this.routerData))
      this.$router.push(item.path)
    },
    // ???????????????????????????
    clickRouter(item) {
      if (this.$route.path === item.path) {
        return false
      }
      let theIndex = ''
      this.routerData.forEach((ele, index) => {
        if (item.path === ele.path) {
          theIndex = index
        }
      })
      if (this.routerData.length === (theIndex + 1)) {
        return false
      } else {
        this.routerData.splice(theIndex)
      }
      this.$router.push({
        'path': item.path,
        'query': item.data
      })
    },
    logout() {
      const that = this
      that.$confirm("???????????????????", "??????", {
        type: "warning"
      }).then(async () => {
        that.toDoLogout()
      }).catch(() => {
        return false
      })
    },
    toDoLogout() {
      sessionStorage.removeItem("UserButtons")
      sessionStorage.removeItem("UserMenus")
      sessionStorage.removeItem("UserInfo")
      this.$router.push('/login')
    },
    openDialog() {
      this.dialogVisible = true
    },
    edit(ruleForm) {
      const that = this
      this.$refs["ruleForm"].validate(async (valid) => {
        if (valid) {
          if (that.ruleForm.newPassword !== that.ruleForm.quePassword) {
            return this.$message.error('???????????????????????????????????????!')
          }
          const data = {
            id: JSON.parse(sessionStorage.getItem("UserInfo")).id,
            newPassword: Md5(that.ruleForm.newPassword),
            oldPassword: Md5(that.ruleForm.oldPassword)
          }
          try {
            let res = await resetPassword(data)
            if (res.data.code === 1) {
              that.$message.success('??????????????????')
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
    closeDialog() {
      this.ruleForm.oldPassword = ''
      this.ruleForm.newPassword = ''
      this.ruleForm.quePassword = ''
      this.$refs["ruleForm"].resetFields()
      this.dialogVisible = false
    }
  },
  watch: {
    // ??????????????????
    $route(to, from) {
      const that = this
      if (sessionStorage.getItem("theBreadcrumb2")) {
        that.routerData = JSON.parse(sessionStorage.getItem("theBreadcrumb2"))
      }
      // if (to.path === '/homePage') {
      //   return false
      // }
      let flag = false
      let theIndex = 0
      that.routerData.forEach((ele, index) => {
        if (to.path === ele.path) {
          theIndex = index
          flag = true
        }
      })
      if (flag) {
        that.routerData.splice(theIndex)
      }
      let toPathData = {}
      that.$router.options.routes.forEach(item => {
        if (item.path === '/setUp' || item.path === '/quality' || item.path === '/government') {
          item.children.forEach(sub => {
            if (sub.path === to.path) {
              toPathData.title = sub.name
              toPathData.path = sub.path
              toPathData.data = that.$route.query
            }
          })
        }
      })
      that.routerData.push(toPathData)
      sessionStorage.setItem("theBreadcrumb2", JSON.stringify(that.routerData))
    }
  }
}
</script>

<style lang="scss" scoped>
.theHeader {
  background-color: #fff;
  width: 100%;
  height: 100%;
  position: relative;
  display: flex;
  align-items: center; /* ???????????? */
  .the-breadcrumb {
    font-size: 13px;
    padding-left: 50px;
    /deep/ .el-breadcrumb__inner {
      color: $color-Breadcrumb;
      &:hover {
        color: $color-MenuHoverText;
      }
    }
  }
  .right-menu {
    position: absolute;
    right: 0;
    top: 0;
    height: 100%;
    width: 150px;

    .avatar-container {
      height: 100%;
      width: auto;
      position: relative;
      .avatar-wrapper {
        display: flex;
        align-items: center;
        color: #606266;
        font-size: 13px;
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
