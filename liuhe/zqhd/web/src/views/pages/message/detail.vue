<!-- 数据资源 -->
<template>
  <div class="banxin data-resources">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/map' }">日常服务</el-breadcrumb-item>
      <el-breadcrumb-item>申请</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="box">
        <div class="tit">
            日常服务
            <span class="line"></span>
        </div>
        <el-form ref="form" :model="form" label-width="120px" style="margin-top:20px;">
          
            <el-form-item label="诉求标题：" >
                <el-input v-model="form.name"></el-input>
            </el-form-item>
          <el-form-item label="诉求内容：">
            <el-input type='textarea'  :rows="6" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="上传附件：" :label-width="this.formLabelWidth" prop="name">
            <el-upload class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="5"
              :on-exceed="handleExceed"
              :file-list="fileList">
              <el-button size="small" icon="el-icon-upload2">点击上传</el-button>
              <div slot="tip" class="el-upload__tip"><i class="el-icon-paperclip" style="color: #36CE63;"></i>(上传附件格式为：.txt;.doc;.jpg;.grf;.rm;.pdf；单个文件最大不超过5M)，最多不超过3个文件。</div>
            </el-upload>
          </el-form-item>
           <el-form-item style="text-align:center">
            <el-button style="margin-right:120px" type="primary" @click="onSubmit">我要申请</el-button>
          </el-form-item>
        </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
    };
  },
  created () {
  },
  computed: {
   
  },
  mounted () {
    
  },
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
  },
};
</script>
<style lang='scss' scoped>
.banxin{
  margin-top: 50px;
  padding: 0 100px;
  box-sizing: border-box;
  color: #8A8A8A;
  padding-bottom: 20px;
}
.tit{
  line-height: 50px;
  border-bottom: 2px solid #ccc;
  position: relative;
  color: black;
  .line{
    width: 100px;
    height: 2px;
    background: #F53535;
    position: absolute;
    left: 0;
    bottom: -2px;
    display: block;
  }
}

.box{
    background: white;
    padding: 30px;
    box-sizing: border-box;
}

</style>