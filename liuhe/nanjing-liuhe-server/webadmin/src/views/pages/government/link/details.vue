<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标题" prop="title">
          <el-input v-model="ruleForm.title" :disabled="type==='detail'" placeholder="输入标题"></el-input>
        </el-form-item>
        <el-form-item label="链接地址" prop="url">
          <el-input v-model="ruleForm.url" :disabled="type==='detail'" placeholder="输入标题"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="type">
          <el-select clearable size="small" v-model="ruleForm.type" placeholder="请选择分类">
            <el-option label="公益讲堂" value="公益讲堂">
            </el-option>
            <el-option label="线下论坛" value="线下论坛">
            </el-option>
            <el-option label="政策解读" value="政策解读">
            </el-option>
            <el-option label="其他" value="其他">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图片上传" prop="imagesPaht" ref="image">
          <el-upload
            class="avatar-uploader"
            :action="uploadFileUrl"
            :headers='headers'
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeFileUpload" 
            accept='image/png,image/x-icon,image/svg+xml,image/bmp,image/jpeg,image/jpg,image/gif'
            >
            <img v-if="ruleForm.imagesPaht" :src="ruleForm.imagesPaht" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <div class="demo-drawer__footer drawer_footer">
          <el-button size="small" @click="goBack">取 消</el-button>
          <el-button size="small" type="primary" v-show="type!=='detail'" @click="submitForm('ruleForm')">保 存</el-button>
        </div>
      </el-form>
    </el-col>
  </el-main>
</template>
<script>
import { save,update,findById} from '@/api/government/link';
export default {
  data() {
    return {
      title: this.$route.query.title,
      type: this.$route.query.type,
      ruleForm: {
        name: '',
        imagesPaht: '',
        sortNumber: '',
        enable: '禁用',
        url: '',
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入链接地址', trigger: 'change' }
        ],
        imagesPaht: [
          { required: true, message: '请上传图片', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请选择分类', trigger: 'blur' }
        ]
      },
       uploadFileUrl: process.env.VUE_APP_BASE_API + "/public/upload/image",
      headers: {
        Authorization:
          "token " + JSON.parse(sessionStorage.getItem("UserInfo")).token,
      },
    }
  },
  mounted() {
    if (this.type === 'edit' || this.type === 'detail') {
      this.dateilsDataFun();
    }
  },
  methods: {
    dateilsDataFun() {
      findById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.ruleForm = res.data.data;
          let imgName = res.data.data.imagesPaht.split('/');
          this.fileList = [{ name: imgName[imgName.length - 1] }]
          console.log(this.ruleForm,imgName,this.fileList)

        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.type === 'add') {
            save(this.ruleForm).then(res => {
              if (res.data.code === 1) {
                this.$message.success('添加成功！');
              } else {
                this.$message.error(res.data.msg);
              }
            })
          } else {
            update(this.ruleForm).then(res => {
              if (res.data.code === 1) {
                this.$message.success('编辑成功！');
              } else {
                this.$message.error(res.data.msg);
              }
            })
          }
          this.goBack()
        } else {
          return false;
        }
      });
    },
    goBack() {
      setTimeout(()=>{
        this.$router.push('/link')
      },100)
    },
    handleAvatarSuccess(res, file) {
      this.ruleForm.imagesPaht = process.env.VUE_APP_BASE_API + file.response.fileDownloadUri
    },
    beforeFileUpload(file) {
      let str = ['image/png','image/x-icon','image/svg+xml','image/bmp','image/jpeg','image/jpg','image/gif']
      const isTRUE = str.indexOf(file.type)
      if (isTRUE == -1) {
        this.$message.error('上传图片只能是png,ico,svg,bpm,jpeg,jpg,gif 格式!');
      }
      return (isTRUE != -1);
    }, 
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
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>