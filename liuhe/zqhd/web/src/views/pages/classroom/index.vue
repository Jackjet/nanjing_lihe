<!-- 线上课堂 -->
<template>
  <div class="banxin data-resources">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>线上课堂</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="20" style="padding: 20px;box-sizing:border-box;background:white">
      <div class="tit">
        动态资讯
        <span class="line"></span>
      </div>
      <div class="type">
        <div>
          <span>分类：</span>
          <div >
            <el-radio @change="fetchData" v-model="searchItem.type" label="不限" border size="small">不限</el-radio>
            <el-radio @change="fetchData" v-model="searchItem.type" label="公益讲座" border size="small">公益讲座</el-radio>
            <el-radio @change="fetchData" v-model="searchItem.type" label="线下论坛" border size="small">线下论坛</el-radio>
            <el-radio @change="fetchData" v-model="searchItem.type" label="政策解读" border size="small">政策解读</el-radio>
            <el-radio @change="fetchData" v-model="searchItem.type" label="金融" border size="small">金融</el-radio>
            <el-radio @change="fetchData" v-model="searchItem.type" label="其他" border size="small">其他</el-radio>
          </div>
        </div>
        <div>
          <span>发布时间：</span>
          <div>
            <el-date-picker @change="fetchData"
              v-model="value1"
              value-format="yyyy-MM-dd HH:mm:ss"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </div>
        </div>
      </div>
      <div class="data-box">
       <div v-for="(v,i) in videoList" :key='i'>
          <div class="img">
             <el-image :src="v.imagePath" fit="cover"></el-image>
             <i class="el-icon-caret-right" @click="player(v.id,v.url)"></i>
          </div>
          <div class="font">
            <p>{{v.releaseTime}}</p>
            <p>{{v.title}}</p>
          </div>
       </div>
      </div>
   </el-row> 
  </div>
</template>

<script>
import Pagination from '@/components/table/Pagination'
import { findAll ,findById} from '@/api/classroom'

export default {
  components: { Pagination },
  data() {
    return {
      dialogVisible: false,
      searchItem:{
        type:''
      },
      value1: '',
      videoList:[
        {
          img:require('./../../../assets/image/banner.png'),
          date:'2021-05-06',
          time:'13：24',
          descript:'教程视频，关于做好疫苗接种工作',
          // url:require('./../../../assets/image/2.mp4')
        },
        {
          img:require('./../../../assets/image/banner.png'),
          date:'2021-05-06',
          time:'13：24',
          descript:'教程视频，关于做好疫苗接种工作',
          // url:require('./../../../assets/image/3.mp4')
        },
        {
          img:require('./../../../assets/image/banner.png'),
          date:'2021-05-06',
          time:'13：24',
          descript:'教程视频，关于做好疫苗接种工作'
        },
        {
          img:require('./../../../assets/image/banner.png'),
          date:'2021-05-06',
          time:'13：24',
          descript:'教程视频，关于做好疫苗接种工作'
        },
        {
          img:require('./../../../assets/image/banner.png'),
          date:'2021-05-06',
          time:'13：24',
          descript:'教程视频，关于做好疫苗接种工作'
        },
        {
          img:require('./../../../assets/image/banner.png'),
          date:'2021-05-06',
          time:'13：24',
          descript:'教程视频，关于做好疫苗接种工作'
        }
      ],
    };
  },
  created () {
   
  },
  mounted () {
    this.fetchData()    
  },
  
  methods: {
    // 获取列表
    async fetchData(type) {
      const that = this
      let data = {}
      data = JSON.parse(JSON.stringify(this.searchItem))
      data.page = this.currentPage
      data.size = this.pageSize
      data.enable = "启用"
      if(data.type == '不限'){
        delete data['type']
      }
      if (that.value1 !== null && that.value1 !== '' && that.value1.length > 0) {
        data.startTime = this.value1[0]
        data.endTime = this.value1[1]
      } else {
        data.startTime = ''
        data.endTime = ''
      }
      try {
        that.isSubmitLoading = true
        const res = await findAll(data)
        that.isSubmitLoading = false
        if (res.data.code === 1) {
          this.videoList = res.data.data.content
          this.total = res.data.data.totalElements
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    handleClose(){
      this.video.currentTime = 0;
      this.pauseVideo()
      this.dialogVisible = false
    },
    player(id,url){
      findById({ id: id }).then(res => {
        if (res.data.code === 1) {
            this.$router.push({
            path:'classroomDetail',
              query:{
                id:id,
                url:res.data.data.attachmentPath
              } 
            })
        } else {
          this.$message.error(res.data.msg);
        }
      })
     
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
.type{
  padding: 20px;
  box-sizing: border-box;
  background: #F8F8F8;
  margin-top: 20px;
  font-size: 14px;
  >div{
    >span{
      width: 100px;
      display: inline-block;
    }
    padding: 10px 0;
    display: flex;
    align-items: center;
    /deep/ .el-radio__input{
      display: none;
    }
  }
}
.data-box{
  margin-top: 20px;
  display: flex;
  flex-wrap: wrap;
  >div{
    width: 23%;
    margin: 0 1%;
    margin-bottom: 20px;
    .img{
      width: 100%;
      position: relative;
      .el-image{
        width: 100%;
        height: 180px;
      }
      i{
        position: absolute;
        left: 42%;
        top: 45%;
        font-size: 30px;
        color: white;
        text-align: center;
        line-height: 50px;
        width: 50px;
        height: 50px;
        display: block;
        background: rgba(1,1,1,.5);
        border-radius: 50%;
      }
    }
    .font{
      p:nth-child(2){
        width: 70%;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
    }
  }
}

</style>