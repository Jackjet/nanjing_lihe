<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="公司名称">
          <el-input v-model="ruleForm.enterpriseName" disabled></el-input>
        </el-form-item>
        <el-form-item label="公司地址">
          <el-input v-model="address" disabled></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="ruleForm.liaisonName" disabled></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="ruleForm.phone" disabled></el-input>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input v-model="ruleForm.createTime" disabled></el-input>
        </el-form-item>
        <!-- <el-form-item label="所属行业">
          <el-input v-model="ruleForm.industry" disabled></el-input>
        </el-form-item> -->
        <div class="demo-drawer__footer drawer_footer">
          <el-button size="small" @click="goBack">返 回</el-button>
        </div>
      </el-form>
    </el-col>
  </el-main>
</template>
<script>
import { infoFind } from '@/api/zlxzUser.js';
export default {
  data() {
    return {
      title: '详情',
      ruleForm: {},
      address: ""
    }
  },
  mounted() {
    infoFind({ id: this.$route.query.id }).then(res => {
      if (res.data.code === 1) {
        this.ruleForm = res.data.data;
        this.address = res.data.data.city + res.data.data.province + res.data.data.area + res.data.data.address;
      } else {
        this.$message.error(res.data.msg)
      }
    })
  },
  methods: {
    goBack() {
      this.$router.push('/userList');
    }
  }
}
</script>
<style lang="scss" scope>
.main {
  display: flex;
  flex-direction: column;
  height: 100%;
  border-radius: 5px;
  padding: 20px;
  .main-center {
    padding: 20px;
    border-radius: 5px;
    width: 65%;
    margin: 0 auto;
  }
  .demo-drawer__footer {
    margin-top: 80px;
    text-align: center;
  }
}
.upload-demo {
  display: flex;
}
.el-upload-list {
  margin-left: 20px;
}
</style>