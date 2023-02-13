<!-- 需求定制列表页 -->
<template>
  <div class="need-list banxin">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>详情</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="box">
      <div class="title">
        {{ruleForm.title}}
      </div>
      <div class="title2">
        发布时间：{{ruleForm.releaseTime}}    来源：{{ruleForm.organizationName}}
      </div>
      <p class="contentDetaile">
      </p>
      <!-- <p>
        附件：
      </p> -->
      <!-- <div class="file">
        <p>关于创新企业示范的通知.doc</p>
        <p>管理办法.doc</p>
      </div> -->
      <!-- <div class="tit">
        我要报名
        <span class="line"></span>
      </div> -->
      <!-- <el-form ref="form" :model="form" :rules="rules" label-width="120px" style="margin-top:20px;">
          <el-row>
            <el-col :span='12'>
              <el-form-item label="报名企业：" >
                <el-input v-model="form.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span='12'>
              <el-form-item label="联系人：">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span='12'>
              <el-form-item label="企业地址：" >
                <el-input v-model="form.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span='12'>
              <el-form-item label="联系电话：">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="报名简介：">
            <el-input type='textarea'  :rows="6" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="申请依据：" :label-width="this.formLabelWidth" prop="name">
            <el-upload class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="5"
              :on-exceed="handleExceed"
              :file-list="fileList">
              <el-button size="small" icon="el-icon-upload2">点击上传</el-button>
              <div slot="tip" class="el-upload__tip"><i class="el-icon-paperclip" style="color: #36CE63;"></i>最多上传5个附件，每个附件不大于10M，支持word、pdf、jpg等文件格式</div>
            </el-upload>
          </el-form-item>
           <el-form-item style="text-align:center">
            <el-button style="margin-right:120px" type="primary" @click="onSubmit">报名</el-button>
          </el-form-item>
        </el-form> -->
    </div>
  </div>
</template>
<script>

import { findById } from '@/api/important'


export default {
  data () {
    return {
      ruleForm:{},
      form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请选择分类', trigger: 'change' }
        ],
        stage: [
          { required: true, message: '请选择阶段', trigger: 'change' }
        ],
        organization: [
          { required: true, message: '请选择组织部门', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入详情  ', trigger: 'change' }
        ],
      },
    }
  },
  created () {},
  mounted () {
    this.dateilsDataFun()
  },
  methods: {
    dateilsDataFun() {
      findById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.ruleForm = res.data.data;
          this.ruleForm.organization = this.ruleForm.organizationId+'-'+this.ruleForm.organizationName
          let dom = document.getElementsByClassName('contentDetaile')[0]
          dom.innerHTML = this.ruleForm.content
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
  }
}
</script>
<style lang='scss' scoped>
b {
    font-weight: bold;
}
.banxin{
  margin-top: 50px;
  padding: 0 100px;
  box-sizing: border-box;
  color: #8A8A8A;
  padding-bottom: 20px;
}
.tit{
  line-height: 50px;
  border-bottom: 2px solid #ccc;
  position: relative;
  color: black;
  .line{
    width: 100px;
    height: 2px;
    background: #F53535;
    position: absolute;
    left: 0;
    bottom: -2px;
    display: block;
  }
}
.box{
  padding: 20px;
  box-sizing: border-box;
  background: white;
  color: black;
  margin-top: 20px;
  font-size: 14px;
  .title{
    font-size: 24px;
    text-align: center;
    width: 100%;
    color: black;
  }
  .title2{
    font-size: 14px;
    color: #878787;
    text-align: center;
    width: 100%;
    margin-top: 10px;
  }
  >.contentDetaile{
    margin-top: 20px;
    margin-bottom: 20px;
    img{
      width: 100%;
    }
    span{
      display: block;
      margin-top: 10px;
      text-indent: 24px;
    }
  }
  .file{
    margin-top: 20px;
    margin-bottom: 50px;
    color: #30AFF1;
    text-decoration: underline;
    p{
      margin-top: 5px;
      cursor: pointer;
    }
  }
}
</style>