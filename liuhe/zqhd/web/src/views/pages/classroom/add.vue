<template>
  <div class="banxin customization">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/typical' }">典型应用</el-breadcrumb-item>
      <el-breadcrumb-item>注册应用</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="customization-box">
      <div class="title">
        <h2>注册应用</h2>
      </div>
      <el-divider></el-divider>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="customization-form">
        <el-form-item label="成果类型：" :label-width="this.formLabelWidth">
          <div class="show">
              <el-radio-group v-model="ruleForm.appType">
              <el-radio label="0" >服务应用</el-radio>
              <el-radio label="1" >创新方案</el-radio>
              <el-radio label="2" >研究成果</el-radio>
              <el-radio label="3" >传播产品</el-radio>
            </el-radio-group>
          </div>
        </el-form-item>
        <el-form-item label="市辖区应用：" :label-width="this.formLabelWidth">
          <div class="show">
            <el-radio-group v-model="ruleForm.isShiQu">
              <el-radio label="1">是</el-radio>
              <el-radio label="0">否</el-radio>
            </el-radio-group>
            <el-checkbox-group v-model="ruleForm.areaIds" v-if="ruleForm.isShiQu == '1'">
              <el-checkbox  v-for="(item,index1) in shixiaqu" 
                            :label="item.areaId"   
                            :key="item.areaId">
                    {{item.areaName}}
              </el-checkbox>
            </el-checkbox-group>
          </div>
        </el-form-item>
        <el-form-item label="封面logo：" :label-width="this.formLabelWidth">
          <el-upload
            class="avatar-uploader"
            :action="uploadFileUrl"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="logo.imageUrl" :src="logo.imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <p style="color:#7E7E7E">建议：比例为100*100</p>
        </el-form-item>
        <el-form-item label="成果名称：" :label-width="this.formLabelWidth" prop="appName">
          <el-input clearable placeholder="请输入成果名称，字数不超过30个字。" maxlength="100" size="medium" v-model="ruleForm.appName"></el-input>
          <p style="color:#7E7E7E">注：成果名称提交后将不能修改。</p>
        </el-form-item>
        <el-form-item label="成果介绍：" :label-width="this.formLabelWidth" prop="appDescribe">
          <el-input clearable maxlength='500' minlength='10' type="textarea" size="medium" v-model="ruleForm.appDescribe" :rows="3" placeholder="请输入成果介绍，字数不超过500个字。" show-word-limit></el-input>
          <p style="color:#7E7E7E">注：数据成果介绍字符数，最少10个，最多500个。</p>
        </el-form-item>
        <el-form-item label="图片说明：" :label-width="this.formLabelWidth">
          <el-upload
            class="avatar-uploader1"
            :action="uploadFileUrl"
            :show-file-list="false"
            :on-success="handleAvatarSuccess1"
            :before-upload="beforeAvatarUpload">
            <img v-if="explain.imageUrl" :src="explain.imageUrl" class="avatar1">
            <i v-else class="el-icon-plus avatar-uploader-icon1"></i>
          </el-upload>
          <p style="color:#7E7E7E">建议：比例为928*522,10MB以内，格式仅限 jpg/png</p>
        </el-form-item>
        <el-form-item label="数据利用方：" :label-width="this.formLabelWidth" prop="dataUseName">
          <el-input clearable placeholder="请输入数据利用方，字数不超过50个字。" maxlength="100" size="medium" v-model="ruleForm.dataUseName"></el-input>
          <p style="color:#7E7E7E">例：世博村路300号</p>
        </el-form-item>
        <el-form-item label="数据利用方联系地址：" :label-width="this.formLabelWidth">
          <el-input clearable placeholder="请输入数据利用方联系地址，精确到门牌号" maxlength="100" size="medium" v-model="ruleForm.dataUseNameAddr"></el-input>
        </el-form-item>
        <el-form-item label="更新频度：" :label-width="this.formLabelWidth" prop="updateRate">
          <div class="show">
            <el-radio-group v-model="ruleForm.updateRate">
              <el-radio label="0" >实时</el-radio>
              <el-radio label="1" >每周</el-radio>
              <el-radio label="2" >每半年</el-radio>
              <el-radio label="3" >每年</el-radio>
              <el-radio label="4" >每天</el-radio>
              <el-radio label="5" >每月</el-radio>
              <el-radio label="6" >不定时更新</el-radio>
              <el-radio label="7" >静态数据</el-radio>
            </el-radio-group>
          </div>
        </el-form-item>
        <el-form-item label="数据领域：" :label-width="this.formLabelWidth" prop="dataDomain">
          <el-select v-model="ruleForm.dataDomain" style="width:100%" placeholder="请选择数据领域" clearable>
          <el-option
      v-for="(val, key, index) in dataDomainArr"
      :key="key"
      :label="val"
      :value="key"></el-option>
          
          </el-select>
        </el-form-item>
        <el-form-item label="成果形式：" :label-width="this.formLabelWidth" prop="appShape">
          <div class="show">
            <el-radio-group v-model="ruleForm.appShape">
              <el-radio label="0">WEB</el-radio>
              <el-radio label="1">移动应用</el-radio>
              <el-radio label="2">地图</el-radio>
              <el-radio label="3">文章</el-radio>
              <el-radio label="4">报道</el-radio>
              <el-radio label="5">其他</el-radio>
            </el-radio-group>
          </div>
        </el-form-item>
        <el-form-item label="数据来源：" :label-width="this.formLabelWidth">
          <el-table
            :data="selectTableData"
            style="width: 100%">
            <el-table-column
              label="序号"
              type="index"
              width="50">
            </el-table-column>
            <el-table-column
              prop="name"
              label="名称"
              width="180">
            </el-table-column>
            <el-table-column
              prop="type"
              label="数据类型"
              width="180" :formatter="resTypeFormatter">
            </el-table-column>
            <el-table-column
              prop="provOrgName"
              label="数据机构">
            </el-table-column>
          </el-table>
          <div style="margin-top:20px">
            <el-button plain icon="el-icon-collection-tag" @click="dialogVisible = true">点击可根据条件筛选并关联数据</el-button>
          </div>
        </el-form-item>
        <el-form-item label="成果链接地址：" :label-width="this.formLabelWidth">
          <el-input clearable placeholder="请填写成果链接地址,字数不超过200字" maxlength="100" size="medium" v-model="ruleForm.appURL"></el-input>
        </el-form-item>
        <el-form-item label="成果关键字：" :label-width="this.formLabelWidth">
          <el-input clearable placeholder="请填写成果关键字,字数不超过20字" maxlength="100" size="medium" v-model="ruleForm.appKeywords"></el-input>
          <p style="color:#7E7E7E">注：多个关键字之间以，隔开</p>
        </el-form-item>
        <!--
        <el-form-item label="国家主题分类：" :label-width="this.formLabelWidth" prop="name">
          <el-select v-model="ruleForm.value1" style="width:48%;margin-right:4%" placeholder="请选择选择国家主体分类" clearable>
            <el-option
              v-for="item in options1"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-select v-model="ruleForm.value2" style="width:48%" placeholder="请选择" clearable>
            <el-option
              v-for="item in options2"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        -->
        <el-form-item label="国家主题分类：" :label-width="this.formLabelWidth">
          <el-cascader
            v-model="subjectTmpArr"
            :options="subjectArr"></el-cascader>
        </el-form-item>
        <el-dialog
          title="申请使用数据资源的相关内容"
          :visible.sync="dialogVisible"
          width="70%"
          :before-close="handleClose">
          <el-row>
            <el-col span='7'>
              数据来源单位
            </el-col>
            <el-col span='7'>
              数据名称：
            </el-col>
            <el-col span='7'>
              数据类型：
            </el-col>
            <el-col span='3'></el-col>
          </el-row>
          <el-row style="margin-top:15px">
            <el-col span='7'>
               <el-select style="width:90%"  v-model="formInline.provOrgId" placeholder="请选择单位" clearable>
                <el-option
                  v-for="item in orgList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-col>
            <el-col span='7'>
               <el-input style="width:90%" v-model="formInline.name" placeholder="名称"></el-input>
            </el-col>
            <el-col span='7'>
               <el-select style="width:90%"  v-model="formInline.type" placeholder="请选择类型" clearable>
                <el-option
                  v-for="item in dataTypeList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <el-col span='3'>
              <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-col>
          </el-row>
          <div class="data-box model-css clearfix">
            <el-table
              :data="initTableData"
              border
              style="width: 100%;margin-top:30px;">
              <el-table-column
                label="序号"
                type="index"
                width="50">
              </el-table-column>
              <el-table-column
                prop="name"
                label="姓名"
                width="180">
              </el-table-column>
              <el-table-column
                prop="type"
                label="类型"
                width="180" :formatter="resTypeFormatter">
              </el-table-column>
              <el-table-column
                prop="provOrgName"
                label="机构">
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作"
                width="100">
                <template slot-scope="scope">
                  <el-button @click="handleClickAdd(scope.row)" type="text" size="small">添加</el-button>
                </template>
              </el-table-column>
            </el-table>
            <pagination ref="page" :total="total" @pageChange="pageChange" v-show="initTableData.length!=0"></pagination>
          </div>
          <div style="width:100%;margin-top:25px;min-height:100px;border:1px solid #d9d9d9;padding:15px;box-sizing:border-box;">
            <p style="color:blue">数据来源：</p>
            <el-tag
              v-for="tag in tags"
              :key="tag.id"
              closable
              type="info" @close="tagClose">
              {{tag.name}}
            </el-tag>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>
        <div class="demo-footer">
          <el-button size="medium" type="primary" @click="submitForm('ruleForm')">保 存</el-button>
          <el-button size="medium" @click="resetForm('ruleForm')">取 消</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import Pagination from '@/components/table/Pagination'
import {resTypeArr, statusArr, dataDomainArr, subjectArr} from '@/utils/const'
import {findAllAreaDictionary} from "@/api/areadictionary.js"
import {findAllOpenInfo, findAllOrg, addTypical} from "@/api/typical.js";
export default {
  components: { Pagination },
  data() {
    return {
      title: "新增",
      disabled: false,
      dialogVisible: false,
      uploadFileUrl: process.env.VUE_APP_BASE_API + '/webadmin/file/upload',
      dataDomainArr: dataDomainArr,
      subjectArr: subjectArr, 
      dataTypeList:[
        {
          label: '数据',
          value: '0'
        },
        {
          label: '接口',
          value: '1'
        }
      ],
      orgList:[],
      tags:[],
      formInline: {
        provOrgId: '',
        type: '',
        name: '',
        status: '1'
      },
      logo:{
        imageUrl: ''
      },
      explain:{
        imageUrl: ''
      },
      initTableData: [],
      selectTableData: [],
      shixiaqu:[],
      subjectTmpArr:['综合政务', '方针政策'],
      ruleForm: {
          appName: '',
          appType: "1",
          isShiQu: '0',
          areaIds:[],
          resourceIds:[],
          appDescribe: '',
          logoURL:'',
          exampleURL:'',
          dataUseName:'',
          dataUseNameAddr:'',
          updateRate:'1',
          dataDomain:'1',
          appShape:'1',
          appURL:'',
          appKeywords:'',
          subject:'',
          subjectSubclass:''
      },
      formLabelWidth: "160px",
      rules: {
        appName: [
          {
            required: true,
            message: "请输入成果名称",
            trigger: "change"
          },
        ],
        appDescribe: [
          {
            required: true,
            message: "请输入成果介绍",
            trigger: "change"
          }
        ],
        dataUseName: [
          {
            required: true,
            message: "请输入数据利用方",
            trigger: "change"
          }
        ]
      },
      total: 0,
      pageSize: '5',
      currentPage: '1'
    }
  },
  created() {
    this.findAllAreaDictionary()
    this.findAllOrg()
  },
  methods: {
    async findAllAreaDictionary(){
      const response = await findAllAreaDictionary()
        if (response.data.code === 1) {
            this.shixiaqu = response.data.data
        } else {
            that.$message.error(response.data.msg)
            return false
        }
    },
    async findAllOrg(){ 
      const response = await findAllOrg({parentId : 'headquarters'})
       if (response.data.code === 1) {
            this.orgList = response.data.data
        } else {
            that.$message.error(response.data.msg)
            return false
        }
    },
    async onSubmit(){
      const that = this
      let datas =  this.formInline
      datas.page = this.currentPage
      datas.size = this.pageSize
      const response = await findAllOpenInfo(datas)
       if (response.data.code === 1) {
          this.initTableData = response.data.data.content
          this.total =  response.data.data.totalElements
        } else {
            that.$message.error(response.data.msg)
            return false
        }
    },
    // 翻页
    pageChange(item) {
      let that = this
      this.pageSize = item.limit
      this.currentPage = item.page
      // Object.entries(that.SearchItem).map((item, index) => {
      //   that.SearchItem[item[0]] = that.lastItem[item[0]]
      // })
      this.onSubmit()
    },
    resTypeFormatter(row, column, cellValue, index){
      return resTypeArr[cellValue]
     },
    handleClickAdd(row){
      //查找有没有
       let inper = this.tags.find((item)=>{
                return row.id === item.id 
        })
        if(!inper){
          this.selectTableData.push(row)
          this.tags.push({
            id: row.id,
            name: row.name
          })
        }

    },
    tagClose(tag){
      let target = this.tags.indexOf(tag)
      /查找有没有
      let inper = this.selectTableData.find((item)=>{
          return target.id === item.id 
      })
      if(inper){
        this.selectTableData.splice(inper, 1)
      }
      this.tags.splice(target, 1)
    },
    handleAvatarSuccess(res, file) {
      this.logo.imageUrl = URL.createObjectURL(file.raw);
      this.ruleForm.logoURL = res.fileDownloadUri
    },
    handleAvatarSuccess1(res, file) {
      this.explain.imageUrl = URL.createObjectURL(file.raw);
      this.ruleForm.exampleURL = res.fileDownloadUri
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isPNG = file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2
      const fileTypeEnabled = isJPG || isPNG
      if (!fileTypeEnabled) {
        this.$message.error('上传头像图片只能是 JPG?PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return fileTypeEnabled && isLt2M
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    submitForm(ruleForm){
      let that = this
      this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            if(!that.ruleForm.logoURL){
                this.$message.error('请上传logo!')
                return false
            }
            if(that.ruleForm.isShiQu=='1' && that.ruleForm.areaIds.length==0){
                this.$message.error('请选择辖区!')
                return false
            }
            this.addTypical()
          }
        });
 
    },
    async addTypical(){
      let that = this
      let data = this.ruleForm
      data.resourceIds = this.selectTableData.map(item =>{return item.id})
      data.subject = this.subjectTmpArr[0]
      data.subjectSubclass = this.subjectTmpArr[1]
      const response = await addTypical(data)
       if (response.data.code === 1) {
            that.$message.success('保存成功')
            that.$router.push({ 
                    path: '/typical'
                  })
        } else {
            that.$message.error(response.data.msg)
            return false
        }
    },
    resetForm(ruleForm){
      let that = this
      that.$router.push({ 
        path: '/typical'
      })
    },
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
  },
};
</script>
<style lang="scss" scoped>
  /deep/ .el-dialog__header{
    border-bottom:2px solid #d9d9d9;
  }
  /deep/ .el-form-item{
    margin-bottom: 22px !important; 
  }
  /deep/ .avatar-uploader1 .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader1 .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon1 {
    font-size: 28px;
    color: #8c939d;
    width: 260px;
    height: 195px;
    line-height: 195px;
    text-align: center;
  }
  .avatar1 {
    width: 260px;
    height: 195px;
    display: block;
  }
  /deep/ .avatar-uploader .el-upload {
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
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
  }
/deep/ .show .el-radio__input{
  display: inline-block !important;
}
.customization {
  position: relative;
  .customization-box {
    background-color: #fff;
    border-radius: 5px;
    margin-bottom: 20px;
    padding: 0 20px;
  }
  .title {
    position: relative;
    h2 {
      text-align: center;
      font-size: 28px;
      line-height: 3;
      font-weight: bold;
    }
    .el-button {
      position: absolute;
      right: 0;
      bottom: 0;
    }
  }
  .customization-form {
    width: 80%;
    margin: 0 auto;
    padding-bottom: 20px;
    /deep/ .el-form-item__label {
      text-align: left;
      position: relative;
      &::before {
        position: absolute;
        left: -10px;
      }
    }
    /deep/ .el-radio__input {
      display: none;
    }
    .demo-footer {
      text-align: center;
      .el-button {
        padding: 10px 60px;
      }
      .el-button--primary {
        background-color: #354961;
        border-color: #354961;
        &:hover {
          background-color: #597291;
          border-color: #597291;
        }
      }
    }
  }
}
</style>