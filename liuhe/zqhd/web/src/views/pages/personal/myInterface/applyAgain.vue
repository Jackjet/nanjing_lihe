<!-- 数据资源 -->
<template>
  <div class="banxin data-resources">
    <div class="top">
      <h1>资源申请流程</h1>
      <div>
        <span style="color:white;background:#00aa71"><i class="el-icon-user-solid"></i>资源申请</span>
        <i class="el-icon-caret-right"></i>
        <span><i class="el-icon-edit-outline"></i>提供单位处理</span>
        <i class="el-icon-caret-right"></i>
        <span><i class="el-icon-user"></i>资源申请</span>
      </div>
    </div>
    <div class="bot">
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
        <el-breadcrumb-item :to="{ path: 'theMy/myInterface' }">我的接口</el-breadcrumb-item>
        <el-breadcrumb-item>资源申请</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="info">
        <el-form ref="ruleForm" :rules="rules" :model="ruleForm" :label-width="labelWidth">
          <div class="tit">您申请的资源</div>
          <el-form-item label="资源名称">
            公共厕所保洁质量和服务要求
          </el-form-item>
          <el-form-item label="提供单位">
            市发展和改革委员会
          </el-form-item>
          <el-form-item label="描述">
            公共厕所保洁质量和服务要求
          </el-form-item>
          <div class="tit">申请说明</div>
          <el-form-item label="用途" prop='use'>
            <el-checkbox-group v-model="ruleForm.checkList">
              <el-checkbox-button  border label="系统应用"></el-checkbox-button>
              <el-checkbox-button  border label="用内部业务办理"></el-checkbox-button>
              <el-checkbox-button  border label="比对核查"></el-checkbox-button>
              <el-checkbox-button  border label="其他"></el-checkbox-button>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="使用期限">
            <el-date-picker
              v-model="value1"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
            <p class='remarks'>如不填则为申请永久使用</p>
          </el-form-item>
          <el-form-item label="说明">
            <el-input type="textarea" v-model="ruleForm.desc" rows='8'></el-input>
            <p class='remarks'>请填写本开放资源主要应用的系统（系统名称、简介）、应用场景、业务办理描述或其他</p>
          </el-form-item>
          <el-form-item label="联系人">
            sharetest
          </el-form-item>
          <el-form-item label="联系电话" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="电子邮箱">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="申请依据" prop="name">
              <el-upload class="upload-demo"
                action="https://jsonplaceholder.typicode.com/posts/"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                multiple
                :limit="5"
                :on-exceed="handleExceed"
                :file-list="fileList">
                <el-button size="small" icon="el-icon-upload2">上传资料</el-button>
              </el-upload>
            <p class='remarks'>最多上传5个附件，每个附件不大于10M，支持word、pdf、jpg等文件格式</p>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import Pagination from '@/components/table/Pagination'
export default {
  components: { Pagination },
  data() {
    return {
     labelWidth:'150px',
     ruleForm:{
      name:'',
      checkList: ['选中且禁用','复选框 A'],
      desc:''
     },
     value1:'',
     rules: {
          name: [
            { required: true, message: '请输入联系电话', trigger: 'blur' },
          ],
          use: [
            { required: true, message: '请选择用途', trigger: 'blur' },
          ],
        }
    };
  },
  created () {},
  mounted () {},
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    submitForm(ruleForm){
      let that = this
      that.$router.push({ 
        path: '/dataDetail',
        query: {
          id: 'adsf'
        }
      })
    }
  },
};
</script>
<style lang='scss' scoped>
.top{
  width: 100%;
  background: white;
  text-align: center;
  h1{
    font-size: 28px;
    line-height: 100px;
  }
  >div{
    display: flex;
    justify-content: center;
    align-items: center;
    padding-bottom: 35px;
    span{
      display: block;
      width: 148px;
      height: 40px;
      border-radius: 3px;
      line-height: 40px;
      background: #f5f9fa;
      display: flex;
      justify-content: center;
      align-items: center;
      i{
        margin-right: 15px
      }
    }
    >i{
      margin:0 20px;
      color: #00aa71;
      font-size: 24px;
    }
  }
}
.bot{
  width: 100%;
  // background: #fbfbfb;
  min-height: 500px;
  display: flex;
  flex-direction: column;
  align-items: center;
  .breadcrumb-box{
    width: 653px;
  }
  .info{
    width: 653px;
    min-height: 978px;
    margin-bottom: 20px;
    padding: 28px;
    background: #fff;
    box-shadow: 0 3px 9px 0 rgba(0,0,0,.08);
    border-radius: 6px;
    .tit{
      font-size: 22px;
      font-family: PingFangSC-Medium;
      font-weight: 500;
      color: #333;
      line-height: 20px;
      padding-left: 6px;
      border-left: 3px solid #00aa71;
      margin-bottom: 20px;
    }
    .remarks{
      font-size: 12px;
      color: #999;
    }
    /deep/ .el-form-item__label {
      padding-right: 30px;
      box-sizing: border-box;
    }
    /deep/ .el-checkbox__inner {
      display: none;
    }
    /deep/ .el-form-item__label, /deep/ .el-form-item__content{
      font-size: 16px !important;
    }
  }
}
</style>