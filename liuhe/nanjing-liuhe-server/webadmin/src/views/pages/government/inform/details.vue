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
        <el-form-item label="配图文字">
          <el-input v-model="ruleForm.imagesContent" :disabled="type==='detail'" type='textarea' placeholder="输入配图文字"></el-input>
        </el-form-item>
        <el-form-item label="部门:" :label-width="this.formLabelWidth"  prop="organization">
          <el-cascader style="width: 100%;" placeholder="请选择部门" :props="props" :disabled="disabled" collapse-tags clearable v-model="ruleForm.organization" :options="organizationOptions" @change="handleChange" />
        </el-form-item>
        <el-form-item label="分类" prop="type">
          <el-select style="width:100%" v-model="ruleForm.type" :disabled="type==='detail'" placeholder="请选择分类">
            <el-option label="办事指南" value="办事指南">
            </el-option>
            <el-option label="政策法规" value="政策法规">
            </el-option>
            <el-option label="公示公告" value="公示公告">
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
import { organizationFindAll } from "@/api/organization.js";
import { save,findById,update } from '@/api/government/inform';
import WangEditor from "@/components/editor";
export default {
  components: { WangEditor },
  data() {
    return {
      title: this.$route.query.title,
      type: this.$route.query.type,
      props: { checkStrictly: true },
      organizationOptions: [],
      ruleForm: {
        imagesPaht:''
      },
      ruleForm1:{},
      rules: {
        title: [
          { required: true, message: '输入标题', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择分类', trigger: 'change' }
        ],
        imagesPaht: [
          { required: true, message: '请上传图片', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入详情', trigger: 'change' }
        ],
        organization: [
          { required: true, message: '请选择部门', trigger: 'change' }
        ],
      },
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/public/upload/image",
      headers: {
        Authorization:
          "token " + JSON.parse(sessionStorage.getItem("UserInfo")).token,
      },
    }
  },
  mounted() {
    this.findroles()
    if (this.type === 'edit' || this.type === 'detail') {
      this.dateilsDataFun();
    }
  },
  methods: {
    beforeFileUpload(file) {
      let str = ['image/png','image/x-icon','image/svg+xml','image/bmp','image/jpeg','image/jpg','image/gif']
      const isTRUE = str.indexOf(file.type)
      if (isTRUE == -1) {
        this.$message.error('上传图片只能是png,ico,svg,bpm,jpeg,jpg,gif 格式!');
      }
      return (isTRUE != -1);
    },  
     async findroles(data) {
      const that = this
      that.loading = true
      const response = await organizationFindAll()
      that.loading = false
      if (response.data.code === 1) {
        let arrData = []
        arrData.push(response.data.data)
        that.organizationOptions = this.getJsonTree(arrData, '')
      } else {
        // 添加上传失败后 回调失败信息
        that.$message.error(response.data.msg)
        return false
      }
    },
    getJsonTree(data, parentId) {
      let itemArr = []
      for (let i = 0; i < data.length; i++) {
        let node = data[i];
        if (node.parentId === parentId) {
          let newNode = {};
          newNode.value = node.id+'-'+node.name;
          newNode.label = node.name;
          if (node.children.length > 0) {
            newNode.children = this.getJsonTree(node.children, node.id);
          }
          itemArr.push(newNode);
        }
      }
      return itemArr;
    },
    handleAvatarSuccess(res, file) {
      this.ruleForm.imagesPaht = process.env.VUE_APP_BASE_API + file.response.fileDownloadUri
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      // if (!isJPG) {
      //   this.$message.error('上传头像图片只能是 JPG 格式!');
      // }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    dateilsDataFun() {
      findById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
            this.ruleForm = res.data.data;
            this.ruleForm.organization = this.ruleForm.organizationId+'-'+this.ruleForm.organizationName
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.type === 'add') {
            this.ruleForm.organizationId = this.ruleForm.organization[0].split('-')[0]
            this.ruleForm.organizationName = this.ruleForm.organization[0].split('-')[1]
            delete this.ruleForm.organization
            save(this.ruleForm).then(res => {
              if (res.data.code === 1) {
                this.$message.success('添加成功！');
              } else {
                this.$message.error(res.data.msg);
              }
            })
          } else {
            this.ruleForm.sortNumber = 1
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
        this.$router.push('/inform')
      },100)
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
  .w-e-toolbar{
  z-index: 99 !important;
}
.w-e-text-container {
  z-index: 30 !important;
}
</style>