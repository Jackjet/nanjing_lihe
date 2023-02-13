<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-tabs type="card">
        <el-tab-pane label="企业基本情况">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-row>
              <el-col span='12'>
                <el-form-item label="企业名称" prop="name">
                  <el-input v-model="ruleForm.name" placeholder="输入标题"></el-input>
                </el-form-item>
              </el-col>
              <el-col span='12'>
                <el-form-item label="企业类型" prop="name">
                  <el-select clearable size="small" v-model="ruleForm.name" placeholder="请选择企业类型">
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
              </el-col>
            </el-row>
            <el-row>
              <el-col span='12'>
                <el-form-item label="主要负责人" prop="name">
                  <el-input v-model="ruleForm.name" placeholder="输入标题"></el-input>
                </el-form-item>
              </el-col>
              <el-col span='12'>
                <el-form-item label="主要负责人学历" prop="name">
                  <el-input v-model="ruleForm.name" placeholder="输入标题"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col span='12'>
                <el-form-item label="法人" prop="name">
                  <el-input v-model="ruleForm.name" placeholder="输入标题"></el-input>
                </el-form-item>
              </el-col>
              <el-col span='12'>
                <el-form-item label="法人学历" prop="name">
                  <el-select clearable size="small" v-model="ruleForm.name" placeholder="请选择企业类型">
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
              </el-col>
            </el-row>
            <el-row>
              <el-col span='12'>
                <el-form-item label="成本时间" prop="name">
                  <el-input v-model="ruleForm.name" placeholder="输入标题"></el-input>
                </el-form-item>
              </el-col>
              <el-col span='12'>
                <el-form-item label="经营状态" prop="name">
                  <el-select clearable size="small" v-model="ruleForm.name" placeholder="请选择企业类型">
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
              </el-col>
            </el-row>
            <el-row>
              <el-col span='12'>
                <el-form-item label="注册资本" prop="name">
                  <el-input v-model="ruleForm.name" placeholder="输入标题"></el-input>
                </el-form-item>
              </el-col>
              <el-col span='12'>
                <el-form-item label="实际到账资本" prop="name">
                  <el-input v-model="ruleForm.name" placeholder="输入标题"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="联系电话">
              <el-input v-model="ruleForm.createTime"></el-input>
            </el-form-item>
            <el-form-item label="附件" prop="imagesPaht" ref="image">
              <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="企业简介">
              <el-input v-model="ruleForm.createTime" type='textarea'></el-input>
            </el-form-item>
            <el-form-item label="发展规划">
              <el-input v-model="ruleForm.createTime" type='textarea'></el-input>
            </el-form-item>
            <el-form-item label="注册地址">
              <el-input v-model="ruleForm.createTime" type='textarea'></el-input>
            </el-form-item>
            <el-form-item label="实际经营地址">
              <el-input v-model="ruleForm.createTime" type='textarea'></el-input>
            </el-form-item>
            <div class="demo-drawer__footer drawer_footer">
              <el-button size="small" @click="goBack">取 消</el-button>
              <el-button size="small" type="primary" v-show="type!=='detail'" @click="submitForm('ruleForm')">保 存</el-button>
            </div>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="协议签订情况">协议签订情况</el-tab-pane>
        <el-tab-pane label="党群信息类">党群信息类</el-tab-pane>
        <el-tab-pane label="经济运营服务类（万元）">经济运营服务类（万元）</el-tab-pane>
        <el-tab-pane label="产业科技类">产业科技类</el-tab-pane>
        <el-tab-pane label="政务服务类">政务服务类</el-tab-pane>
      </el-tabs>

    </el-col>
  </el-main>
</template>
<script>
import { rotationChartSave, rotationChartFindById, rotationChartUpdate } from '@/api/carousel.js';
import WangEditor from "@/components/editor";
export default {
  components: { WangEditor },
  data() {
    return {
      title: this.$route.query.title,
      type: this.$route.query.type,
      isClear: false,
      ruleForm: {
        name: '',
        imagesPaht: '',
        sortNumber: '',
        enable: '禁用',
        url: '',
        content: ''
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
      imageUrl: '',
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
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    dateilsDataFun() {
      rotationChartFindById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.ruleForm = res.data.data;
          let imgName = res.data.data.imagesPaht.split('/');
          this.fileList = [{ name: imgName[imgName.length - 1] }]
          console.log(this.ruleForm, imgName, this.fileList)

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
.main .main-center {
  width: 100%;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
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