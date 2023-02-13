<!-- 数据资源 -->
<template>
  <div class="banxin data-resources">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/service' }">日常服务</el-breadcrumb-item>
      <el-breadcrumb-item>申请</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="box">
        <div class="tit">
            日常服务
            <span class="line"></span>
        </div>
        <el-form ref="form" :model="form" :rules='rules' label-width="120px" style="margin-top:20px;">
          
            <el-form-item label="诉求标题：" prop='title'>
                <el-input v-model="form.title"></el-input>
            </el-form-item>
          <el-form-item label="诉求内容：" prop='content'>
            <el-input type='textarea'  :rows="6" v-model="form.content"></el-input>
          </el-form-item>
          <el-form-item label="上传附件：" :label-width="this.formLabelWidth" prop="fileList">
            <el-upload class="upload-demo" 
            :action="uploadFileUrl" 
            :headers="headers" 
            ref="upload" 
            :file-list="form.fileList"
             :on-change="handleChange" 
             :on-remove="handleRemove" 
             :on-success="handleFileSuccess" 
             :before-upload="beforeFileUpload" 
             accept="application/msword,text/plain,application/pdf,application/vnd.ms-excel"
             :on-error="handleFileError">
              <el-button size="small" type="primary" v-show='show'>点击上传</el-button>
              <div slot="tip" class="el-upload__tip"><i class="el-icon-paperclip" style="color: #36CE63;"></i>(上传附件格式为：.txt;.doc;.pdf;.xls；单个文件最大不超过5M)，最多不超过3个文件。</div>
            </el-upload>
          </el-form-item>
           <el-form-item style="text-align:center">
            <el-button style="margin-right:120px" type="primary" @click="onSubmit">我要申请</el-button>
          </el-form-item>
        </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
        show:true,
        form: {
        
        },
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'change' }
          ],
          content: [
            { required: true, message: '请输入内容', trigger: 'change' }
          ],
          fileList: [
            { required: true, message: '请选择附件', trigger: 'change' }
          ],
        },
    };
  },
  created () {
  },
  computed: {
   
  },
  mounted () {
    
  },
  methods: {
    handleChange(file, fileList) {
      this.form.fileList = fileList
      console.log(this.fileList)
      if (this.form.fileList.length > 2) {
        this.show = false
      }else{
        this.show = true
      }
    },
    handleRemove(file, fileList) {
      this.form.fileList = fileList
      console.log(11111,this.fileList.length)
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
      let str = ['text/plain','application/msword','application/pdf','application/vnd.ms-excel']
      const isTRUE = str.indexOf(file.type)
      if (isTRUE == -1) {
        this.$message.error('上传附件只能是 .txt;.doc;.pdf;.xls 格式!');
      }
      const isLt2M = file.size / 1024 / 1024 < 5;
      if (!isLt2M) {
        this.$message.error("上传大小不能超过 5MB!");
      }
      
      return (isTRUE != -1) && isLt2M ;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            providerSave(this.ruleForm).then(res => {
              if (res.data.code === 1) {
                this.$message.success('添加成功！');
                this.goBack();
              } else {
                this.$message.error(res.data.msg);
              }
            })
        } else {
          return false;
        }
      });
    },
  },
};
</script>
<style lang='scss' scoped>
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
    background: white;
    padding: 30px;
    box-sizing: border-box;
}

</style>