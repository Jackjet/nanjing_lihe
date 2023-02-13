<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="消息标题" prop="title">
          <el-input v-model="ruleForm.title" placeholder="请输入消息标题"></el-input>
        </el-form-item>
        <el-form-item label="接收方" prop="receiveIds">
          <span v-if='ids' >{{names}}</span>
          <el-button v-else @click="choose">选择接收方</el-button>
          <el-input v-model="ruleForm.receiveIds" v-show='false' placeholder="请输入消息标题"></el-input>
        </el-form-item>
       <el-form-item label="消息内容" prop="content">
          <el-input type='textarea' v-model="ruleForm.content" placeholder="请输入消息内容"></el-input>
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
import { save } from '@/api/government/governmentMessage';
import WangEditor from "@/components/editor";
export default {
  components: { WangEditor },
  data() {
    return {
      title: this.$route.query.title,
      type: this.$route.query.type,
      ids: this.$route.query.ids,
      names: this.$route.query.names,
      isClear: false,
      ruleForm: {
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'change' }
        ],
        receiveIds: [
          { required: true, message: '请选择接收方', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入内容', trigger: 'change' }
        ],
      },
    }
  },
  mounted() {
    if(this.ids != ''){
      this.ruleForm.receiveIds = this.ids
    }
  },
  methods: {
    choose(data) {
      this.$router.push({
        path: '/messageReceive',
        query: {
          id: data.id
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
            announcementUpdate(this.ruleForm).then(res => {
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
      this.$router.push('/governmentMessage');
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
</style>