<template>
  <div class="banxin data-policy">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>日常服务</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="box">
      <div class="tit">
        政策体系
        <span class="line"></span>
      </div>
      <div class="bot">
        <div class="left">
          <div :class="type == 1 ? 'choose' : ''" @click='changeClass(1)'>
           <i class="el-icon-user"></i>申请入驻
          </div>
          <div :class="type == 2 ? 'choose' : ''" @click='changeClass(2)'>
            <i class="el-icon-user"></i> 联系人黄页
          </div>
          <div :class="type == 4 ? 'choose' : ''" @click='changeClass(4)'>
            <i class="el-icon-user"></i> 日常服务
          </div>
          <div :class="type == 3 ? 'choose' : ''" @click='changeClass(3)'>
            <i class="el-icon-user"></i> 常见问题
          </div>
        </div>
        <div class="right">
          <div class="problom" v-if="type == 3">
            <div class="title">入驻申请审核不通过，常见原因有哪些？</div>
            <div class="date">2021-05-01</div>
            <div class="con">
              <p>
                1、问：e通卡在哪里购买？怎么收费？
              </p>
              <div>
                  目前市场上销售的易通卡主要分为两类，一类以押金形式销售的普通卡，押金30元/张，卡片不使用可办理退卡；另一类为纪念卡，售价不低于35元/张，有特定主题、限量发行、多种工艺，不同主题及工艺的纪念卡销售价格不等，纪念卡可长期使用不办理退卡。易通卡客服中心各网点、邮局各网点、BRT站点均有售卡。
              </div>
            </div>
            <div class="con">
              <p>
                2、 问：e通卡有哪些充值方式？
              </p>
              <div>
                  目前主要有以下充值方式：<br>
                  1）充值网点：邮局各网点、BRT各网点、客服中心各网点均可办理充值。<br>
                  2）自助终端：地铁各站点自助终端、农行自助终端。<br>
              </div>
            </div>
          </div>
          <div class="service" v-if="type == 4">
            <div v-for='(v,i) in serveList' :key='i' @click="goDetaile">
              <el-image :src="v.img" fit="cover"></el-image>
              <span>{{v.title}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type:"4",
      serveList:[
        {
          img:require('./../../../assets/image/partyService.png'),
          title:'党群服务'
        },
        {
          img:require('./../../../assets/image/moneyService.png'),
          title:'金融服务'
        },
        {
          img:require('./../../../assets/image/buildService.png'),
          title:'项目建设服务'
        },
        {
          img:require('./../../../assets/image/safeService.png'),
          title:'安全服务'
        },
        {
          img:require('./../../../assets/image/cityService.png'),
          title:'城市管理服务'
        },
        {
          img:require('./../../../assets/image/protectionService.png'),
          title:'环保服务'
        },
        {
          img:require('./../../../assets/image/scienceService.png'),
          title:'科技人才服务'
        },
        {
          img:require('./../../../assets/image/lifeService.png'),
          title:'全生命周期服务'
        }
      ]
    };
  },
  created() {},
  mounted() {
    if(this.$route.query.type){
      this.type = this.$route.query.type  
    }
  },
  methods: {
    changeClass(type){
      this.type = type
    },
    goDetaile(){
      this.$router.push({
        path:'serviceDetail'
      })
    }
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
  .bot{
    display: flex;
  }
  .left{
    margin-right: 30px;
    .choose{
      background: rgba(0, 117, 193, .1);
      border: 1px solid #0075C1;
      color: #0075C1;
    }
    >div{
      width: 150px;
      height: 35px;
      margin-top: 10px;
      border: 1px solid #E7E7E7;
      font-size: 14px;
      color: #242424;
      display: flex;
      align-items: center;
      cursor: pointer;
      i{
        width: 50px;
        text-align: center;
      }
    }
  }
  .right{
    flex: 1;
    .problom{
      width: 100%;
      padding: 0 30px;
      box-sizing: border-box;
      .title{
        font-size: 20px;
        color: black;
        text-align: center;
        line-height: 50px;
      }
      .date{
        font-size: 14px;
        color: #878787;
        text-align: center;
      }
      .con{
        margin-top: 20px;
        color: #2D2D2D;
        p{
          font-weight: 900;
        }
        div{
          line-height: 30px;
        }
      }
    }
    .service{
      width: 100%;
      padding: 0 30px;
      box-sizing: border-box;
      display: flex;
      flex-wrap: wrap;
      >div{
        margin-top: 20px;
        width: 23%;
        margin-right: 20px;
        height: 130px;
        box-shadow: gray 0px 0px 5px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        .el-image{
        }
      }
    }
  }
}
</style>
