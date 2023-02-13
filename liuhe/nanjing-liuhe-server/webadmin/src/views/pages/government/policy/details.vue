<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标题" prop="title">
          <el-input v-model="ruleForm.title" :disabled="type==='detail'" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item label="来源" prop="organization">
          <el-cascader :show-all-levels="false" style="width: 100%;" placeholder="请选择来源" :props="props" :disabled="disabled" collapse-tags clearable v-model="ruleForm.organization" :options="organizationOptions"/>
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
        <el-form-item label="附件" ref="image">
          <el-upload
            class="upload-demo"
            :disabled="type==='detail'"
            ref="upload"
            :action="action" 
            :headers="headers"
            :on-change="handleChange"
            :before-upload="beforeFileUpload" 
             accept=".zip,.zipx,.rar,.tar,.tgz,.7z,.gz,.doc,.docx,.xls,.xlsx,.ppt,.pptx,.txt,.xml,.pdf,.png,.ico,.svg,.jpeg,.jpg,.gif"
            :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
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
import { save, update, findById } from '@/api/government/policy';
import { announcementSave, announcementFindById, announcementUpdate } from '@/api/news.js';
import WangEditor from "@/components/editor";
export default {
  components: { WangEditor },
  data() {
    return {
      title: this.$route.query.title,
      type: this.$route.query.type,
      props: { checkStrictly: true },
      organizationOptions: [],
      isClear: false,
      ruleForm: {
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'change' }
        ],
        organization: [
          { required: true, message: '请选择来源', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请选择分类', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入详情', trigger: 'change' }
        ],
      },
      action:process.env.VUE_APP_BASE_API+'/public/upload/attachment',
      headers: {
        Authorization:
          "token " + JSON.parse(sessionStorage.getItem("UserInfo")).token,
      },
      fileList: []
    }
  },
  mounted() {
    this.findroles()
    if (this.type === 'edit' || this.type === 'detail') {
      this.dateilsDataFun();
    }
  },
  methods: {
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
          newNode.value = node.id + '-' + node.name;
          newNode.label = node.name;
          if (node.children.length > 0) {
            newNode.children = this.getJsonTree(node.children, node.id);
          }
          itemArr.push(newNode);
        }
      }
      return itemArr;
    },
    handleChange(file, fileList) {
      this.fileList = fileList;
    },
    beforeFileUpload(file) {
      let str = ['zip','zipx','rar','tar','tgz','7z','gz','doc','docx','xls','xlsx','ppt','pptx','txt','xml','pdf','png','ico','svg','jpeg','jpg','gif']
      console.log(file.name.split('.')[1])
      const isTRUE = str.indexOf(file.name.split('.')[1])
      if (isTRUE == -1) {
        this.$message.error('上传附件只能是 zip,zipx,rar,tar,tgz,7z,gz,doc,docx,xls,xlsx,ppt,pptx,txt,xml,pdf 格式!');
      }
      // const isLt2M = file.size / 1024 / 1024 < 100;
      // if (!isLt2M) {
      //   this.$message.error("上传大小不能超过 100MB!");
      // }
      return (isTRUE != -1);
    },    
    changeEditor(val) {
      console.log(val)
    },
    dateilsDataFun() {
      findById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.ruleForm = res.data.data;
          this.ruleForm.organization = this.ruleForm.organizationId + '-' + this.ruleForm.organizationName
          this.fileList = JSON.parse(this.ruleForm.attachmentPath)
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log()
          this.ruleForm.attachmentPath = JSON.stringify(this.fileList)
          this.ruleForm.attachmentPath = JSON.stringify(this.fileList)
          let organization = typeof(this.ruleForm.organization) == 'string' ? this.ruleForm.organization : this.ruleForm.organization[this.ruleForm.organization.length-1]
          this.ruleForm.organizationId = organization.split('-')[0]
          this.ruleForm.organizationName = organization.split('-')[1]
          delete this.ruleForm.organization
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
      this.$router.push('/policy');
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
.w-e-toolbar {
  z-index: 99 !important;
}
.w-e-text-container {
  z-index: 30 !important;
}
</style>