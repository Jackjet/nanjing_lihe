<!-- 数据资源 -->
<template>
  <div class="banxin data-resources">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>搜索列表</el-breadcrumb-item>
    </el-breadcrumb>
    <div>
      <div class="search">
      <div>
        <input type="text" v-model='search'><span><i class="el-icon-search" @click="fetchData"></i></span>
      </div>
    </div>
    <el-row style="padding: 20px;box-sizing:border-box;background:white">
      <div class="tit">
        搜索列表
        <span class="line"></span>
      </div>
      <div class="list" v-if='searchList.length > 0'>
         <div  v-for="(item,index) in searchList" :key='index'>
         <p>
            <span>{{item.type}}</span>
            {{item.title}}
          </p>
          <div class="cont">
            <p :id="'content'+index" class="content" @click="goPage(item.id,item.type)">
            </p>
            <p style="color:#ccc">
              {{item.release_time}}
            </p>
          </div> 
        </div> 
      </div>
      <div class="listNull" v-else>
        抱歉，暂无内容
      </div>
    </el-row>
    </div>
    
  </div>
</template>

<script>
import Pagination from '@/components/table/Pagination'
import { findAll,findByIdClass } from '@/api/search'

export default {
  components: { Pagination },
  data() {
    return {
      search:this.$route.query.search,
      searchList:[],
      total: 0,
      pageSize: '20',
      currentPage: '1',
    };
  },
  created () {
    
  },
  mounted () {
    this.fetchData()
  },
  methods: {
    async fetchData(){
      const that = this
      let datas = {
        title:this.search,
        page : this.currentPage,
        size : this.pageSize
      }
      try {
        const res = await findAll(datas)
        if(res.data.code === 1){
          this.searchList = res.data.data
          console.log(this.searchList)
          setTimeout(()=>{
            this.searchList.forEach((v,i)=>{
              let dom = document.getElementById('content'+i)
              dom.innerHTML = v.content
            })
          },100)
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    goPage(id,type){
      let path = ''
      let query = ''
      if(type=='动态资讯'){
        path = '/dynamicDetaile'
        query={
          id:id
        }
      }else if(type=='政策体系'){
        path = '/policyDetaile'
        query={
          id:id
        }
      }else if(type=='重要通知'){
        path = '/importantDetaile'
        query={
          id:id
        }
      }else if(type=='常见问题'){
        path = '/service'
        query={
          type:3
        }
      }else if(type=='线上课堂'){
        let data = { id: id }
        console.log(data)
        findByIdClass({ id: id }).then(res => {
          if (res.data.code === 1) {
            this.$router.push({
                path : '/classroomDetail',
                query : {
                  id:id,
                  url:res.data.data.attachmentPath
                } 
            })
          } else {
            this.$message.error(res.data.msg);
          }
        })
      }
      if(type != '常用链接' && type != '线上课堂'){
        
        this.$router.push({
          path:path,
          query:query,
        })
      }
    }
  },
};
</script>
<style lang='scss' scoped>
.listNull{
  min-height: 300px;
  text-align: center;
  line-height: 300px;
  font-size: 30px;
  font-weight: 900;
}
.content{
  text-overflow: -o-ellipsis-lastline;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  line-clamp: 2;
  -webkit-box-orient: vertical;
  cursor: pointer;
}
.search{
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
  >div{
    border: 1px solid gray;
    border-radius: 10px;
    background: white;
    input{
      height: 40px;
      width: 300px;
      padding-left: 20px;
      box-sizing: border-box;
    }
    i{
      height: 40px;
      width: 40px;
      text-align: center;
      line-height: 40px;
      cursor: pointer;
    }
  }
}
.banxin{
  margin-top: 50px;
  padding: 0 100px;
  box-sizing: border-box;
}
.tit{
  line-height: 50px;
  border-bottom: 2px solid #ccc;
  position: relative;
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
.list{
  padding: 30px;
  box-sizing: border-box;
  >div{
    margin-top: 30px;
    border-bottom: 1px dashed #ccc;
    padding-bottom: 20px;
    >p{
      color: blueviolet;
      >span{
        background: blue;
        color: white;
        padding: 3px;
        box-sizing: border-box;
      }
    }
    .cont{
      padding-left: 75px;
      p{
        margin-top: 10px;
      }
    }
  }
}
</style>