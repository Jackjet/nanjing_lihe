<template>
  <el-main class="main">
    <div>
      <h4>{{ title }}</h4>
    </div>
    <el-col class="main-center">
      <el-form label-width="100px" class="demo-ruleForm">
        <el-form-item label="企业名称：">
          <span>{{detailsData.enterpriseName}}</span>
        </el-form-item>
        <el-form-item label="联系人：">
          <span>{{detailsData.liaisonName}}</span>
        </el-form-item>
        <el-form-item label="联系电话：">
          <span>{{detailsData.liaisonTel}}</span>
        </el-form-item>
        <el-form-item label="咨询时间：">
          <span>{{detailsData.createTime}}</span>
        </el-form-item>
        <el-form-item label="图片上传：">
          <ul class="img-list">
            <li v-for="(item,index) in imgList" :key="index">
              <img :src="dataImgUrl+item" alt="">
            </li>
          </ul>
        </el-form-item>
        <el-form-item label="所在位置：">
          <span>{{detailsData.address}}</span>
        </el-form-item>
        <el-form-item label="标题：">
          <span>{{detailsData.title}}</span>
        </el-form-item>
        <el-form-item label="描述：">
          <span>{{detailsData.leavingMessage}}</span>
        </el-form-item>
      </el-form>
      <el-form v-if="isShow" label-width="100px" class="demo-ruleForm">
        <el-form-item label="历史回复："></el-form-item>
        <el-form-item v-for="(item,index) in detailsData.replyList" :label="item.replyUserName+'：'" :key="index">
          <span style="display: inline-block;width:400px;">{{item.replyMessage}}</span><span style="margin-left:20px">{{item.createTime}}</span>
        </el-form-item>
      </el-form>
      <el-form label-width="100px">
        <el-form-item label="回复：">
          <el-input type="textarea" v-model="replyContent"></el-input>
        </el-form-item>
        <div class="demo-drawer__footer drawer_footer">
          <el-button size="small" @click="returnRouter">取 消</el-button>
          <el-button size="small" type="primary" @click="submitForm">保 存</el-button>
        </div>
      </el-form>
    </el-col>
  </el-main>
</template>
<script>
import { consultFindById, consultReplyMessage } from "@/api/consult.js";
export default {
  data() {
    return {
      title: "详情",
      imgList: [],
      detailsData: {},
      replyContent: "",
      isShow: false,
      dataImgUrl: process.env.VUE_APP_BASE_API
    }
  },
  mounted() {
    this.detailsContentFun();
  },
  methods: {
    detailsContentFun() {
      consultFindById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.detailsData = res.data.data;
          this.imgList = res.data.data.imagesPahts.split(',');
          if (res.data.data.replyList.length) {
            this.isShow = true;
          } else {
            this.isShow = false;
          }
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    submitForm() {
      if (this.replyContent) {
        consultReplyMessage({ id: this.$route.query.id, replyMessage: this.replyContent }).then(res => {
          if (res.data.code === 1) {
            this.$message.success(res.data.msg);
            // this.returnRouter();
            this.detailsContentFun();
          } else {
            this.$message.error(res.data.msg);
          }
        })
      } else {
        this.$message('没有回复内容无需提交');
      }
    },
    returnRouter() {
      this.$router.push('/consult');
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
    .demo-ruleForm {
      border: 1px solid #ccc;
      margin-bottom: 20px;
    }
    .img-list {
      display: flex;
      flex-wrap: wrap;
      margin: 0;
      padding: 0;
      list-style: none;
      li {
        width: 100px;
        height: 100px;
        margin-right: 10px;
        img {
          display: block;
          width: 100%;
          height: 100%;
        }
      }
    }
  }
  .demo-drawer__footer {
    margin-top: 80px;
    text-align: center;
  }
}
.upload-demo {
  display: flex;
}
.el-upload-list {
  margin-left: 20px;
}
</style>