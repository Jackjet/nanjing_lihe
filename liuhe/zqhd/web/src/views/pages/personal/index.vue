<!-- 个人中心 主页 -->
<template>
  <div class="banxin the-my">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="20" type="flex">
      <el-col :span="4">
        <div class="my-menu">
          <div class="profile">
            <el-avatar :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
            <div class="the-name">{{ name }}</div>
            <el-divider></el-divider>
          </div>
          <el-menu :default-active="defaultActive" @open="handleOpen" @close="handleClose" router>
            <el-menu-item index="/theMy/myInfo">
              <i class="el-icon-menu"></i>
              <span slot="title">个人信息</span>
            </el-menu-item>
            <el-menu-item index="/theMy/myInterface">
              <i class="el-icon-document"></i>
              <span slot="title">我的消息</span>
            </el-menu-item>
            <el-menu-item index="/theMy/myData">
              <i class="el-icon-setting"></i>
              <span slot="title">企业档案</span>
            </el-menu-item>
            <el-menu-item index="/theMy/myNeeds">
              <i class="el-icon-setting"></i>
              <span slot="title">我的服务</span>
            </el-menu-item>
          </el-menu>
        </div>
      </el-col>
      <el-col :span="20" style="min-height: 500px;">
        <router-view></router-view>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data () {
    return {
      name: '',
      defaultActive: '',
    }
  },
  created () {
    this.defaultActive = this.$route.path
    this.name = JSON.parse(sessionStorage.getItem("UserInfo")).name
  },
  mounted () {},
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>
<style lang='scss' scoped>
.banxin{
  margin-top: 50px;
}
.the-my {
  .el-row {
    margin-bottom: 20px;
    .my-menu {
      background-color: #fff;
      height: 100%;
      border-radius: 5px;
      .profile {
        text-align: center;
        padding-top: 30px;
        font-size: 14px;
        font-weight: bold;
      }
    }
  }
}
</style>