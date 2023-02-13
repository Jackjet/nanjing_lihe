<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label=" 选择企业" prop="enterprise">
          <el-select :disabled="type==='detail'" v-model="ruleForm.enterprise" placeholder="请选择企业">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
         <el-form-item label="链接地址">
          <el-input type='textarea' v-model="ruleForm.url" :disabled="type==='detail'" placeholder="输入内容"></el-input>
        </el-form-item>
        <div class="demo-drawer__footer drawer_footer">
          <el-button size="small" @click="goBack">取 消</el-button>
          <el-button size="small" type="primary" @click="submitForm('ruleForm')">保 存</el-button>
        </div>
      </el-form>
    </el-col>
  </el-main>
</template>
<script>
import { findById ,findAllEnterprise,save} from '@/api/government/parkSettled';
import WangEditor from "@/components/editor";
export default {
  components: { WangEditor },
  data() {
    return {
      title: this.$route.query.title,
      type: this.$route.query.type,
      isClear: false,
      ruleForm: {
       
      },
      searchItem:{

      },
      options:[

      ],
      rules: {
        enterprise: [
          { required: true, message: '请选择企业', trigger: 'blur' }
        ],
      },
      total: 0,
      pageSize: '100',
      currentPage: '1',
    }
  },
  mounted() {
    this.fetchData()
    if (this.type === 'detail') {
      this.dateilsDataFun();
    }
  },
  methods: {
    // 获取列表
    async fetchData(type) {
      const that = this
      let data = {}
      data = this.searchItem
      data.page = this.currentPage
      data.size = this.pageSize
      data.isSettled = 1
      data.enable = 1
      try {
        const res = await findAllEnterprise(data)
        console.log(res)
        if (res.data.code === 1) {
          this.options = []
          res.data.data.content.forEach((v,i)=>{
            let obj = {
              label:v.enterpriseName,
              value:v.id+'-'+v.enterpriseName+'-'+v.logo
            }
            this.options.push(obj)
          })
          this.total = res.data.data.totalElements
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    dateilsDataFun() {
      findById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.ruleForm = res.data.data;
          this.ruleForm.enterprise = this.ruleForm.enterpriseId+'-'+this.ruleForm.enterpriseName+'-'+this.ruleForm.logo
          console.log(this.ruleForm,imgName,this.fileList)

        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            let data = this.ruleForm
            data.enterpriseId = data.enterprise.split('-')[0]
            data.enterpriseName = data.enterprise.split('-')[1]
            data.logo = data.enterprise.split('-')[2]
            save(data).then(res => {
              if (res.data.code === 1) {
                this.$message.success('新增成功！');
              } else {
                this.$message.error(res.data.msg);
              }
            })
          setTimeout(()=>{
            this.goBack()
          },100)
        } else {
          return false;
        }
      });
    },
    goBack() {
      this.$router.push('/parkSettled')
    },
    changeEditor(val) {
      console.log(val)
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