<!-- 需求定制列表页 -->
<template>
  <div class="need-list banxin">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/dynamic' }">动态资讯</el-breadcrumb-item>
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
      <p v-if='downLoadList.length > 0'>
        附件：
      </p>
      <div class="file" v-if='downLoadList.length > 0'>
        <p v-for="(v,i) in downLoadList" :key='i' >
          <span @click="downLoad(v.url,v.name)">{{v.name}}</span>
        </p>
      </div>
      <div class="tit">
        我要报名
        <span class="line"></span>
      </div>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px" style="margin-top:20px;">
          <el-row>
            <el-col :span='12'>
              <el-form-item label="报名企业：" >
                <el-input disabled='true' v-model="form.enterpriseName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span='12'>
              <el-form-item label="联系人：" prop='liaisonName'>
                <el-input  v-model="form.liaisonName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span='12'>
              <el-form-item label="企业地址：" >
                <el-input disabled='true' v-model="form.address"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span='12'>
              <el-form-item label="联系电话：" prop='liaisonTel'>
                <el-input v-model="form.liaisonTel"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="报名简述：" prop='content'>
            <div class="tag">
              <el-tag v-for='(v,i) in tagList' :key='i' :type='v.type' @click="tagClick(v.id,v.descript)" >{{v.title}}</el-tag>
            </div>
            <el-input type='textarea'  :rows="6" v-model="form.content"></el-input>
          </el-form-item>
          <el-form-item label="申请依据：" :label-width="this.formLabelWidth" prop="name">
            <el-upload class="upload-demo" 
              :action="uploadFileUrl" 
              :headers="headers" 
              ref="upload" 
              :file-list="form.fileList"
             :on-change="handleChange" 
             :on-remove="handleRemove" 
             :before-upload="beforeFileUpload" 
             accept=".zip,.zipx,.rar,.tar,.tgz,.7z,.gz,.doc,.docx,.xls,.xlsx,.ppt,.pptx,.txt,.xml,.pdf,.png,.ico,.svg,.jpeg,.jpg,.gif"
             :on-error="handleFileError">
              <el-button size="small" type="primary" v-show='show'>点击上传</el-button>
              <div slot="tip" class="el-upload__tip"><i class="el-icon-paperclip" style="color: #36CE63;"></i>(上传附件单个文件最大不超过100M)，最多不超过3个文件。</div>
            </el-upload>
          </el-form-item>
           <el-form-item style="text-align:center">
            <el-button style="margin-right:120px" type="primary" @click="submitForm('form')">报名</el-button>
          </el-form-item>
        </el-form>
    </div>
  </div>
</template>
<script>

import { findById ,findUserByToken,save} from '@/api/dynamic'


export default {
  data () {
    return {
      ruleForm:{},
      tagList:[
        {
          id:'1',
          title:'安全',
          descript:'很安全很安全',
          type:''
        },
        {
          id:'2',
          title:'服务好',
          descript:'服务好服务好',
          type:''
        },
        {
          id:'3',
          title:'补贴高',
          descript:'补贴高补贴高补贴高',
          type:''
        }
      ],
      form: {
         content:'',
         activityId:this.$route.query.id 
      },
      show:true,
      rules: {
        liaisonName: [
          { required: true, message: '请输入联系人', trigger: 'change' }
        ],
        liaisonTel: [
          { required: true, message: '请输入联系电话', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入报名简述', trigger: 'change' }
        ]
      },
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/zqhd/public/upload/attachment",
      headers: {
        Authorization:
          "token " + JSON.parse(sessionStorage.getItem("UserInfo")).token,
      },
      fileList: [],
      downLoadList:[]
    }
  },
  created () {},
  mounted () {
    this.dateilsDataFun()
    this.findUser()
    this.tagContent()
  },
  methods: {
    tagClick(id,descript){
      this.tagList.forEach((v,i)=>{
        if(this.form.content.indexOf(v.descript) != -1){
          this.form.content = this.form.content.replace(v.descript+',','')
        }
        if(v.id == id){
          v.type = v.type == 'danger' ? '' : 'danger'
        }
      })
      this.tagContent()
    },
    tagContent(){
      let str = ''
      this.tagList.forEach((v,i)=>{
        if( v.type == 'danger'){
          str+=v.descript+','
        }
      })
      this.form.content = str+this.form.content
    },
    downLoad(url,name){
      let a = document.createElement("a");
      a.href = process.env.VUE_APP_BASE_API + url;
      a.setAttribute("download", name);
      a.click();
    },
    dateilsDataFun() {
      findById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.ruleForm = res.data.data;
          this.ruleForm.organization = this.ruleForm.organizationId+'-'+this.ruleForm.organizationName
          let dom = document.getElementsByClassName('contentDetaile')[0]
          dom.innerHTML = this.ruleForm.content
          console.log(JSON.parse(this.ruleForm.attachmentPath))
          if(this.ruleForm.attachmentPath){
              JSON.parse(this.ruleForm.attachmentPath).forEach((v,i)=>{
                let obj = {
                  name : v.name,
                  url:v.response.fileDownloadUri
                }
                this.downLoadList.push(obj)
              })
          }
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    findUser() {
      findUserByToken().then(res => {
        if (res.data.code === 1) {
          this.form.enterpriseName = res.data.data.enterpriseName;
          this.form.address = res.data.data.address;
          this.form.enterpriseId = res.data.data.id;
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    handleChange(file, fileList) {
      this.form.fileList = fileList
      if (this.form.fileList.length > 2) {
        this.show = false
      }else{
        this.show = true
      }
    },
    handleRemove(file, fileList) {
      this.form.fileList = fileList
      if (this.form.fileList.length > 2) {
        this.show = false
      }else{
        this.show = true
      }
    },
    handleFileSuccess(res, file) {
      this.$refs.image.clearValidate();
    },
    handleFileError(error) {
      this.$refs.upload.clearFiles();
      this.$message.error(JSON.parse(error.message).msg);
    },
    beforeFileUpload(file) {
      let str = ['zip','zipx','rar','tar','tgz','7z','gz','doc','docx','xls','xlsx','ppt','pptx','txt','xml','pdf','png','ico','svg','jpeg','jpg','gif']
      const isTRUE = str.indexOf(file.name.split('.')[1])
      if (isTRUE == -1) {
        this.$message.error('上传附件只能是 zip,zipx,rar,tar,tgz,7z,gz,doc,docx,xls,xlsx,ppt,pptx,txt,xml,pdf 格式!');
      }
      const isLt2M = file.size / 1024 / 1024 < 100;
      if (!isLt2M) {
        this.$message.error("上传大小不能超过 100MB!");
      }
      return (isTRUE != -1) && isLt2M ;
    },
    submitForm(formName) {
      console.log(JSON.stringify(this.form.fileList))
      this.form.attachmentPath = JSON.stringify(this.form.fileList)
      this.$refs[formName].validate((valid) => {
        if (valid) {
            save(this.form).then(res => {
              if (res.data.code === 1) {
                this.$message.success('报名成功！');
                // this.goBack();
              } else {
                this.$message.error(res.data.msg);
              }
            })
        } else {
          return false;
        }
      });
    },
  }
}
</script>
<style lang='scss' scoped>
.tag{
  margin-bottom: 20px;
  span{
    cursor: pointer;
    margin-right: 10px;
  }
}
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
      span{
        cursor: pointer;
      }
    }
  }
}
</style>