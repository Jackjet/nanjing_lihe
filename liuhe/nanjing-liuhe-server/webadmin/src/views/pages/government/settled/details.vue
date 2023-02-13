<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form label-width="100px" class="demo-ruleForm">
        <el-form-item label="企业名称：">
          <span>7978978978</span>
        </el-form-item>
        <el-form-item label="联系人：">
          <span>7978978978</span>
        </el-form-item>
        <el-form-item label="联系电话：">
          <span>7978978978</span>
        </el-form-item>
        <el-form-item label="咨询时间：">
          <span>7978978978</span>
        </el-form-item>
        <el-form-item label="图片上传：">
          <span>7978978978</span>
        </el-form-item>
        <el-form-item label="所在位置：">
          <span>7978978978</span>
        </el-form-item>
        <el-form-item label="标题：">
          <span>7978978978</span>
        </el-form-item>
        <el-form-item label="描述：">
          <span>7978978978</span>
        </el-form-item>
      </el-form>
      <el-form label-width="100px" class="demo-ruleForm">
        <el-form-item label="历史回复："></el-form-item>
        <el-form-item label="管理员：">
          <span>7978978978</span><span style="margin-left:20px">2021-07-30</span>
        </el-form-item>
        <el-form-item label="公司：">
          <span>7978978978</span><span style="margin-left:20px">2021-07-30</span>
        </el-form-item>
      </el-form>
      <el-form label-width="100px">
        <el-form-item label="回复：">
          <el-input type="textarea" v-model="ruleForm.name"></el-input>
        </el-form-item>
        <div class="demo-drawer__footer drawer_footer">
          <el-button size="small">取 消</el-button>
          <el-button size="small" type="primary" v-show="optType !== 'info'" @click="submitForm('ruleForm')">保 存</el-button>
        </div>
      </el-form>
    </el-col>
  </el-main>
</template>
<script>
export default {
  data() {
    return {
      title: "详情",
      ruleForm: {
        name: ''
      },
      fileList: [{ name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100' }]
    }
  },
  methods: {

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
    .demo-ruleForm {
      border: 1px solid #ccc;
      margin-bottom: 20px;
    }
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