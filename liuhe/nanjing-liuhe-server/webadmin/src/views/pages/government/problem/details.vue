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
        <el-form-item label="分类" prop="type">
          <el-select style="width:100%" v-model="ruleForm.type" :disabled="type==='detail'" placeholder="请选择分类">
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
        <el-form-item label="详情" prop="content">
          <wang-editor v-if="type!=='detail'" v-model="ruleForm.content" :isClear="isClear" @change="changeEditor"></wang-editor>
          <div class="details-content" v-if="type==='detail'" v-html="ruleForm.content"></div>
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
import { save, update, findById } from '@/api/government/problem';
import WangEditor from "@/components/editor";
export default {
  components: { WangEditor },
  data() {
    return {
      title: this.$route.query.title,
      type: this.$route.query.type,
      isClear: false,
      ruleForm: {
        title: '',
        type: '',
        sortNumber: '',
        enable: '',
        imagesPaht: '',
        introduce: '',
        content: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请选择分类', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入详情', trigger: 'change' }
        ],
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
    changeEditor(val) {
      console.log(val)
    },
    dateilsDataFun() {
      findById({ id: this.$route.query.id }).then(res => {
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
            save(this.ruleForm).then(res => {
              if (res.data.code === 1) {
                this.$message.success('添加成功！');
                this.goBack();
              } else {
                this.$message.error(res.data.msg);
              }
            })
          } else {
            update(this.ruleForm).then(res => {
              if (res.data.code === 1) {
                this.$message.success('编辑成功！');
                this.goBack();
              } else {
                this.$message.error(res.data.msg);
              }
            })
          }
        } else {
          return false;
        }
      });
    },
    goBack() {
      setTimeout(() => {
        this.$router.push('/problem')
      }, 100)
    }
  }
}
</script>
<style lang="scss" scope>
/deep/ .el-scrollbar__view {
  z-index: 99;
}
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
  .details-content {
    border: 1px solid #e4e7ed;
    background-color: #f5f7fa;
    color: #c0c4cc;
    border-radius: 4px;
    padding: 0 15px;
  }
}
.upload-demo {
  display: flex;
}
.el-upload-list {
  margin-left: 20px;
}
  .w-e-toolbar{
  z-index: 99 !important;
}
.w-e-text-container {
  z-index: 30 !important;
}
</style>