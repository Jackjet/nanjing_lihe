<!-- 底部文件 -->
<template>
  <div class="banxin">
    <div class="footer-top">
      <div class="left">
        <p>
          <span>电子邮箱：nkzf.com</span>
          <span>快速导航</span>
          <span></span>
        </p>
        <p>
          <span>建设单位：江苏省中小企业服务中心</span>
          <span>关于我们</span>
          <span>隐私保护</span>
        </p>
        <p>
          <span>友情链接：江苏省社会用户实名认证和授权平台</span>
          <span>法律责任</span>
          <span>平台简介</span>
        </p>
      </div>
      <div class="right">
        <span style="font-size:20px">12359</span>
        <span style="font-size:16px">服务热线</span>
      </div>
    </div>
    <div class="footer-bot">
      Copyright© 2019 江苏省中小企业公共服务平台 All Right Reserved
      <el-image :src="require('./../../assets/image/police.png')" fit="cover"></el-image>
      闽公网安备 35010202000093号
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
    };
  },
  created () {},
  mounted () {},
  methods: {
    
  },
};
</script>
<style lang='scss' scoped>
.banxin{
  width: 100%;
  max-width:100%;
}
.footer-top {
  position: relative;
  color: #f2f2f2;
  padding: 30px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  border-bottom: 1px solid gray;
  .left{
    flex:1;
    p{
      line-height: 50px;
      span:nth-child(1){
        width: 400px;
        display: inline-block;
        margin-right: 50px;
      }
      span:nth-child(2){
        width: 100px;
        display: inline-block;
        margin-right: 50px;
      }
    }
  }
}
.footer-bot{
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #f2f2f2;
  padding: 15px 0;
  .el-image{
    margin: 0 10px;
  }
}
</style>