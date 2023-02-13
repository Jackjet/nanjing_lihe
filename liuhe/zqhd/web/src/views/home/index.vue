<!--
 * 默认主页
-->
<template>
  <main class="main-box">
    <div class="banner-box">
      <div class="swiper-container swiper-container1">
          <div class="swiper-wrapper">
              <div class="swiper-slide" v-for="(item,index) in imgurl" :key="index">
                <el-image :src="item" fit="cover"></el-image>
              </div>
          </div>
          <!-- 如果需要分页器 -->
          <div class="swiper-pagination"></div>
      </div>
      <div class="banner-data">
       <input type="text" v-model='search'><span><i class="el-icon-search" @click="goSearch"></i></span>
      </div>
    </div>
    <div class="banxin">
      <div class="card">
        <div class="tit">
          <span>政策体系</span>
          <span class="more" @click="more('/policy')">更多 +</span>
        </div>
        <ul class="con">
          <li v-for="(v,i) in policyList" :key="i" @click="goHref('/policyDetaile',{id:v.id})">
            <span>
              <span class="black"></span>
              <span>{{v.title}}</span> 
            </span>
            <span>{{v.createTime}}</span>
          </li>
        </ul>
      </div>
      <div class="card">
        <div class="tit">
          <span>动态资讯</span>
          <span class="more"  @click="more('/dynamic')">更多 +</span>
        </div>
        <ul class="con">
          <li v-for="(v,i) in activityList" :key="i"  @click="goHref('/dynamicDetaile',{id:v.id})">
            <span>
              <span class="black"></span>
              <span>{{v.title}}</span> 
            </span>
            <span><span v-if="v.stage == '进行中'" class="load">{{v.stage}}</span>
                  <span v-else class="over">{{v.stage}}</span>
                  {{v.createTime}}</span>
          </li>
        </ul>
      </div>
    </div>
    
    <div class="importantList">
      <div class="title">
        <p>重要通知</p>
      </div>
      <div class="swiper-container swiper-container2">
        <div class="swiper-wrapper">
          <div class="swiper-slide"  v-for="(item,index) in noticeList" :key="index"   @click="goHref('/importantDetaile',{id:item.id})">
           <el-image :src="item.imagesPaht" fit="cover"></el-image>
            <div class="font">
              {{item.title}}
            </div>
          </div>
        </div>
        <!-- 如果需要导航按钮 -->
        <div class="swiper-button-prev"></div>
        <div class="swiper-button-next"></div>
      </div>
    </div>
    
    <div class="useLink">
        <div class="title">
          <p>常用链接</p>
        </div>
        <div class="swiper-container swiper-container3">
          <div class="swiper-wrapper">
            <div class="swiper-slide"  v-for="(item,index) in usedurlList" :key="index" @click="linkClick(item.url)">
              <el-image :src="item.imagesPaht"></el-image>
            </div>
          </div>
          <!-- 如果需要导航按钮 -->
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
      </div>
    </div>
    
    <div class="sell">
      <div class="title">
        <p>惠企服务</p>
      </div>
      <ul class="list">
        <li  v-for="(item,index) in sellList" :key="index" @click="goHref(item.href,item.query)">
          <i :class="item.icon"></i>
          <span class="tit">{{item.title}}</span>
          <!-- <span class="description">{{item.descript}}</span> -->
        </li>
        
      </ul>
    </div>
    <div class="flow">
      <div class="title">
        <p>园区入驻企业</p>
      </div>
      <div>
        <el-image v-for="(v,i) in enterpriseList" :key='i' :src="v.logo" fit="cover" @click="linkClick(v.url)"></el-image>
      </div>
    </div>
  </main>
</template>

<script>
import Swiper from "swiper"
import { findAllPolicy, findAllActivity ,findAllChart,findAllNotice,findAllUsedurl,findAllEnterprise} from '@/api/home'
export default {
  name: "firstPage",
  data () {
    return {
      search:'',
      imgurl:['','','',''],
      policyList:[],
      activityList:[],
      noticeList:[],
      usedurlList:[],
      enterpriseList:[],
      sellList:[
        {
          icon:'icon iconfont icon-zixun',
          title:'申请入驻',
          descript:'企业申请入驻的线上入口',
          href:'/service',
          query:{type:1}
        },
        {
          icon:'icon iconfont icon-ic_business_enterprise24px',
          title:'日常服务',
          descript:'党群服务、金融服务、水电缴费、手续完善、以及其他类常见日常服务',
          href:'/service',
          query:{type:4}
        },
        {
          icon:'icon iconfont icon-contact-person',
          title:'联系黄页',
          descript:'常用联系方式、信息公开',
          href:'/service',
          query:{type:2}
        },
        {
          icon:'icon iconfont icon-peixun-',
          title:'在线课堂',
          descript:'政策、流程相关在线培训',
          href:'/classroom',
          query:'',
        },
        {
          icon:'icon iconfont icon-wenti',
          title:'常见问题',
          descript:'常见问题描述',
          href:'/service',
          query:{type:3}
        },
      ]
    }
  },
  created() {},
  mounted() {
    this.navInit()
    this.policyInit()
    this.activityInit()
    this.bannerImageInit()
    this.noticeInit()
    this.usedurlInit()
    this.enterpriseInit()
  },
  destroyed() {
    let dom = document.getElementsByClassName('el-scrollbar__wrap')[0]
    dom.removeEventListener('scroll', this.handleScroll)
  },
  methods: {
    async policyInit(){
      const that = this
      let datas = {
        enable:'启用'
      }
      try {
        const res = await findAllPolicy(datas)
        if(res.data.code === 1){
          this.policyList = res.data.data.content
          this.policyList.forEach((v,i)=>{
            v.createTime = v.createTime.split(' ')[0]
          })
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    async activityInit(){
      const that = this
      let datas = {         enable:'启用'       }
      try {
        const res = await findAllActivity(datas)
        if(res.data.code === 1){
          this.activityList = res.data.data.content
          this.activityList.forEach((v,i)=>{
            v.createTime = v.createTime.split(' ')[0]
          })
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    async bannerImageInit(){
      const that = this
      let datas = {         enable:'启用'       }
      try {
        const res = await findAllChart(datas)
        if(res.data.code === 1){
          this.imgurl = []
          res.data.data.forEach((v,i)=>{
            this.imgurl.push(v.imagesPaht)
          })
          setTimeout(()=>{
            this.bannerSwiper()
          },100)
          console.log(this.imgurl)
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    async noticeInit(){
      const that = this
      let datas = {         enable:'启用'       }
      try {
        const res = await findAllNotice(datas)
        if(res.data.code === 1){
          this.noticeList = res.data.data
          setTimeout(()=>{
            this.importantSwiper()
          },100)
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    async usedurlInit(){
      const that = this
      let datas = {         enable:'启用'       }
      try {
        const res = await findAllUsedurl(datas)
        if(res.data.code === 1){
          this.usedurlList = res.data.data.content
          setTimeout(()=>{
            this.linkSwiper()
          },100)
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    }, 
    async enterpriseInit(){
      const that = this
      let datas = {
        page : 1,
        size : 100
       }
      try {
        const res = await findAllEnterprise(datas)
        if(res.data.code === 1){
          this.enterpriseList = res.data.data.content
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    }, 
    more(path){
      this.$router.push({
        path:path
      })
    },
    navInit(){
      let dom1 = document.getElementsByClassName('left-nav')[0]
      dom1.classList.remove('scrollNav')
      let dom = document.getElementsByClassName('el-scrollbar__wrap')[0]
      dom.addEventListener("scroll",this.handleScroll); 
    },
    handleScroll() {
        //获取滚动时的高度
        let dom = document.getElementsByClassName('el-scrollbar__wrap')[0]
        let scrollTop = dom.scrollTop
        if (scrollTop > 300) {
          let dom = document.getElementsByClassName('left-nav')[0]
          dom.classList.add('scrollNav')
        }else{
          let dom = document.getElementsByClassName('left-nav')[0] //背景
          dom.classList.remove('scrollNav')
        }
    },  
    bannerSwiper(){
      new Swiper ('.swiper-container1', {
        autoplay: 5000,//可选选项，自动滑动
        pagination : '.swiper-pagination',
        paginationClickable :true,
      })
    },
    importantSwiper(){
      new Swiper ('.swiper-container2', {
        slidesPerView:5,
        prevButton:'.swiper-button-prev',
        nextButton:'.swiper-button-next',
      })
    },
    linkSwiper(){
      new Swiper ('.swiper-container3', {
        slidesPerView:6,
        prevButton:'.swiper-button-prev',
        nextButton:'.swiper-button-next',
      })
    },
    goSearch(){
      this.$router.push({
        path:'/searchList',
        query:{
          search:this.search
        }
      })
    },
    goHref(path,query){
      this.$router.push({
        path:path,
        query:query
      })
    },
    linkClick(url){
      // location.href = url
      window.open(url)
    }
  },
}
</script>
<style lang="scss" scoped>
.main-box {
  width: 100%;
  position: relative;
  background: white;
  .banner-box {
    width: 100%;
    position: relative;
    .el-image{
      width: 100%;
      height: 400px;
    }
    .banner-data {
      position: absolute;
      z-index: 99;
      bottom: 1%;
      right:1%;
      display: flex;
      width: 30%;
      height: 40px;
      padding: 20px 0;
      justify-content: center;
      input{
        width: 60%;
        border-radius: 10px 0 0 10px;
        background: rgba(255,255,255,.6);
        // opacity: .4;
        color: black;
        border: 0;
        outline: 0;
        padding-left: 20px;
      }
      span{
        border-radius:0 10px 10px 0;
        width: 60px;
        background: rgba(255,255,255,.6);
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 24px;
        color: white;
        i{
          // color: #FF8A8A8A;
          color: gray;
        }
      }
    }
  }
  .banxin{
    width: 100%;
    display: flex;
    padding: 20px 0;
    .card{
      flex: 1;
      margin: 0 5%;
      border-radius: 5px;
      padding: 20px;
      box-sizing: border-box;
      display: flex;
      flex-direction: column;
      .tit{
        display: flex;
        justify-content: space-between;
        font-size: 18px;
        padding-bottom: 10px;
        border-bottom: 1px solid #ccc;
        .more{
          cursor: pointer;
          color: #20588E;
          font-size: 16px;
        }
      }
      .con{
        flex: 1;
        overflow: auto;
        list-style-type: disc !important;
        li{
          cursor: pointer;
        }
        .load{
          border: 1px solid #70B603;
          color: #70B603;
          padding: 2px 5px;
          margin-right: 10px;
          font-size: 12px;
          position: relative;
        }
        .over{
          border: 1px solid #8A212D;
          color: #8A212D;
          font-size: 12px;
          padding: 2px 5px;
          margin-right: 10px;
          position: relative;
        }
       
        >li{
          padding-left: 20px;
          list-style-type: square !important;
          display: flex;
          justify-content: space-between;
          align-items: center;
          font-size: 14px;
          color: gray;
          padding: 10px 0;
          >span:nth-child(1){
            width: 50%;
            display: flex;
            align-items: center;
            .black{
              width: 3px;
              height: 3px;
              display: inline-block;
              background: black;
              border-radius: 50%;
              margin-right: 10px;
            }
            span:nth-child(2){
              flex: 1;
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
            }
          }
        }
      }
      .con::-webkit-scrollbar{
        width: 0;
      }
    }
    .swiper-container1{
      width: 100%;
      .swiper-wrapper{
        .swiper-slide{
          width: 100%;
          height: 100%;
          box-sizing: border-box;
          text-align: center;
        }
      }
    }
  }
  .title{
    font-size: 24px;
    padding: 20px 0;
    p{
      text-align: center;
    }
  }
  .importantList{
    background: #FFF3F3F3;
    padding-bottom: 20px;
    .swiper-container{
      height: 200px;
      width: 80%;
      margin-left: 10%;
      .swiper-wrapper{
        .swiper-slide{
          cursor: pointer;
          width: 100%;
          height: 100%;
          padding:10px;
          box-sizing: border-box;
          text-align: center;
          /deep/.el-image__inner{
            width: 100%;
            height: 150px;
            margin-bottom: 5px;
          }
          .font{
            width: 100%;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
            color: black;
          }
        }
      }
    }
  }
  .useLink{
    padding-bottom: 20px;
    .swiper-container{
      height: 200px;
      width: 80%;
      margin-left: 10%;
      .swiper-wrapper{
        .swiper-slide{
          cursor: pointer;
          width: 100%;
          height: 100%;
          padding:10px;
          box-sizing: border-box;
          text-align: center;
          /deep/.el-image__inner{
            width: 100%;
            height: 150px;
            margin-bottom: 5px;
          }
        }
      }
    }
  }
  .sell{
    background: #FFF3F3F3;
    padding-bottom: 20px;
    .list{
      width: 80%;
      margin-left: 10%;
      padding: 15px 0;
      flex: 1;
      display: flex;
      flex-wrap: wrap;
      li{
        cursor: pointer;
        width: 18%;
        margin: 10px;
        box-shadow: gray 0px 0px 5px;
        border-radius: 5px;
        background: white;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        font-size: 16px;
        box-sizing: border-box;
        position: relative;
        padding-top: 30px;
        padding-bottom: 20px;
        .icon{
          width: 50px;
          height: 50px;
          border-radius: 50%;
          background: #30aff1;
          color: white;
          font-size: 24px;
          text-align: center;
          line-height: 50px;
          position: absolute;
          top: -25px;

        }
        .tit{
          text-align: center;
          padding: 5px 0;
          font-weight: 900;
        }
        .description{
          padding: 5px 10px;
          box-sizing: border-box;
          font-size: 12px;
          color: #555555;
        }
      }
    }
  }
  .flow{
    >div:nth-child(1){
      text-align: center;
      padding-top: 45px;
    }
    >div:nth-child(2){
        width: 90%;
        padding-left: 6%;
        box-sizing: border-box;
        display: flex;
        flex-wrap: wrap;
        .el-image{
          width: 20%;
          margin:20px 2.5%;
          cursor: pointer;
        }
    }
  }
}
</style>
