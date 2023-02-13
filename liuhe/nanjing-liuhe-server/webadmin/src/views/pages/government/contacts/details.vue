<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="姓名" prop="name">
          <el-input clearable size="small" placeholder="请输入姓名" v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="liaisonTel">
          <el-input clearable size="small" placeholder="请输入联系方式" v-model="ruleForm.liaisonTel"></el-input>
        </el-form-item>
        <el-form-item label="部门:" :label-width="this.formLabelWidth"  prop="organization">
          <el-cascader style="width: 100%;" placeholder="请选择部门" :props="props" :disabled="disabled" collapse-tags clearable v-model="ruleForm.organization" :options="organizationOptions" @change="handleChange" />
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
import { save, update,findById } from '@/api/government/contacts';
import WangEditor from "@/components/editor";
export default {
  components: { WangEditor },
  data() {
    return {
      title: this.$route.query.title,
      type: this.$route.query.type,
      isClear: false,
      props: { checkStrictly: true },
      organizationOptions: [],
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
        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ],
        liaisonTel: [
          { required: true, message: '请输入联系方式', trigger: 'change' }
        ],
        organization: [
          { required: true, message: '请选择部门', trigger: 'change' }
        ],
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
    changeEditor(val) {
      console.log(val)
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
      setTimeout(()=>{
        this.$router.push('/contacts')
      },100)
    }
  }
}
</script>
<style lang="scss" scope>
/deep/ .el-scrollbar__view{
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
</style>