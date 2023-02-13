<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="轮播图标题" prop="name">
          <el-input v-model="ruleForm.name" :disabled="type==='detail'" placeholder="输入标题"></el-input>
        </el-form-item>
        <el-form-item label="图片上传" prop="imagesPaht" ref="image">
          <el-upload class="upload-demo" :disabled="type==='detail'" :action="uploadFileUrl" :headers="headers" ref="upload" :file-list="fileList" :on-change="handleChange" :on-remove="handleRemove" :on-success="handleFileSuccess" :before-upload="beforeFileUpload" :on-error="handleFileError">
            <el-button size="small" type="primary" v-show="type!=='detail'">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <!-- <el-form-item label="序号" prop="sortNumber">
          <el-input v-model="ruleForm.sortNumber" :disabled="type==='detail'" placeholder="输入序号"></el-input>
        </el-form-item> -->
        <el-form-item label="是否启用" prop="enable">
          <el-select style="width:100%" v-model="ruleForm.enable" :disabled="type==='detail'" placeholder="请选择是否">
            <el-option label="启用" value="启用">
            </el-option>
            <el-option label="禁用" value="禁用">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="跳转地址">
          <el-input v-model="ruleForm.url" :disabled="type==='detail'" placeholder="跳转地址"></el-input>
        </el-form-item>
        <el-form-item label="创建人" v-if="type==='detail'">
          <el-input v-model="ruleForm.createById" disabled></el-input>
        </el-form-item>
        <el-form-item label="创建时间" v-if="type==='detail'">
          <el-input v-model="ruleForm.createTime" disabled></el-input>
        </el-form-item>
        <el-form-item label="发布时间" v-if="type==='detail'">
          <el-input v-model="ruleForm.updateTime" disabled></el-input>
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
import { rotationChartSave, rotationChartFindById, rotationChartUpdate } from '@/api/carousel.js';
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
        name: [
          { required: true, message: '请输入图片名称', trigger: 'blur' }
        ],
        imagesPaht: [
          { required: true, message: '请上传图片', trigger: 'change' }
        ],
        sortNumber: [
          { required: true, message: '请输入序号', trigger: 'blur' }
        ],
        enable: [
          { required: true, message: '请输入选择是否启用', trigger: 'blur' }
        ]
      },
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/public/upload/image",
      headers: {
        Authorization:
          "token " + JSON.parse(sessionStorage.getItem("UserInfo")).token,
      },
      fileList: []
    }
  },
  mounted() {
    if (this.type === 'edit' || this.type === 'detail') {
      this.dateilsDataFun();
    }
  },
  methods: {
    handleChange(file, fileList) {
      this.fileList = fileList.slice(-1);
    },
    handleRemove() {
      this.ruleForm.imagesPaht = "";
    },
    handleFileSuccess(res, file) {
      this.ruleForm.imagesPaht = file.response.fileDownloadUri;
      this.$refs.image.clearValidate();
    },
    handleFileError(error) {
      this.$refs.upload.clearFiles();
      this.$message.error(JSON.parse(error.message).msg);
    },
    beforeFileUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!isLt2M) {
        this.$message.error("上传大小不能超过 10MB!");
      }
      if (this.fileList.length > 1) {
        this.$message({
          message: "只能传1个附件!",
          type: "warning",
        });
      }
      return isLt2M;
    },
    dateilsDataFun() {
      rotationChartFindById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.ruleForm = res.data.data;
          let imgName = res.data.data.imagesPaht.split('/');
          this.fileList = [{ name: imgName[imgName.length - 1] }]
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.type === 'add') {
            rotationChartSave(this.ruleForm).then(res => {
              if (res.data.code === 1) {
                this.$message.success('添加成功！');
              } else {
                this.$message.error(res.data.msg);
              }
            })
          } else {
            rotationChartUpdate(this.ruleForm).then(res => {
              if (res.data.code === 1) {
                this.$message.success('编辑成功！');
              } else {
                this.$message.error(res.data.msg);
              }
            })
          }
          this.$router.push('/carousel')
        } else {
          return false;
        }
      });
    },
    goBack() {
      this.$router.push('/carousel')
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