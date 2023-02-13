<!-- 我的接口 -->
<template>
  <div class="my-interface">
    <div class="img">
      <el-image v-if='type == "待处理"'  :src="require('./../../../../assets/image/dcl.png')"></el-image>
      <el-image v-else-if = 'type == "处理中"' :src="require('./../../../../assets/image/clz.png')"></el-image>
      <el-image v-else-if = 'type == "待评价"' :src="require('./../../../../assets/image/dpj.png')"></el-image>
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
             accept="application/msword,text/plain,application/vnd.ms-excel"
             :on-error="handleFileError">
              <el-button size="small" type="primary" v-show='show'>点击上传</el-button>
              <div slot="tip" class="el-upload__tip"><i class="el-icon-paperclip" style="color: #36CE63;"></i>(上传附件格式为：.txt;.doc;.xls；单个文件最大不超过100M)，最多不超过3个文件。</div>
            </el-upload>
          </el-form-item>
          <el-form-item label="管理员回复：" v-if='type != "待处理"'>
            <el-input type='textarea'  :rows="6" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="评价" v-if='type == "待评价"'>
            <p class="rate"><span>办事效率</span><el-rate v-model="value1"></el-rate></p>
            <p class="rate"><span>服务态度</span><el-rate v-model="value2"></el-rate></p>
            <p class="rate"><span>专业能力</span><el-rate v-model="value3"></el-rate></p>
            <el-input type='textarea'  :rows="6" v-model="form.name"></el-input>
          </el-form-item>
           <el-form-item style="text-align:center">
            <el-button style="margin-right:120px" type="primary" @click="submitForm('form')">我要申请</el-button>
          </el-form-item>
        </el-form>
  </div>
</template>

<script>
export default {
  data() {
      return {
        type:this.$route.query.type,
        id:this.$route.query.id,
        value1:'',
        value2:'',
        value3:'',
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
        uploadFileUrl: process.env.VUE_APP_BASE_API + "/zqhd/public/upload/attachment",
        headers: {
          Authorization:
            "token " + JSON.parse(sessionStorage.getItem("UserInfo")).token,
        },
        fileList: []
        }
    },
  created () {},
  mounted () {
  },
  methods: {
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
      let str = ['text/plain','application/msword','application/pdf','application/vnd.ms-excel']
      const isTRUE = str.indexOf(file.type)
      if (isTRUE == -1) {
        this.$message.error('上传附件只能是 .txt;.doc;.xls 格式!');
      }
      const isLt2M = file.size / 1024 / 1024 < 100;
      if (!isLt2M) {
        this.$message.error("上传大小不能超过 100MB!");
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
  }
}
</script>
<style lang='scss' scoped>

.my-interface {
  padding: 20px;
  height: 100%;
  background-color: #fff;
  border-radius: 5px;
}

.img{
  text-align: center;
}

.rate{
  display: flex;
  align-items: center;
  >span{
    margin-right: 20px;
  }
}

</style>