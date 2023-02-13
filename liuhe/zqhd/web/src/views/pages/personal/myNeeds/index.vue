<!-- 我的接口 -->
<template>
  <div class="my-interface">
    <el-radio-group v-model="radio" size="small" @change="fetchData">
      <el-radio-button label="全部"></el-radio-button>
      <el-radio-button label="待处理"></el-radio-button>
      <el-radio-button label="处理中"></el-radio-button>
      <el-radio-button label="待评价"></el-radio-button>
      <el-radio-button label="已完成"></el-radio-button>
    </el-radio-group>
    <div class="list">
      <div v-for='(v,i) in list' :key='i'>
        <div class="title">
          <el-image :src="require('./../../../../assets/image/question.png')"></el-image> {{v.title}}
        </div>
        <div class="time">
          {{v.createTime}}
        </div>
        <div class="content">
          {{v.content}}
        </div>
        <div class="detaile">
          <span @click="toDetail(v.id,v.status)">详情>></span>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import {findAll,findMyAllApi} from '@/api/myNeeds.js'
export default {
  data() {
      return {
        radio: '全部',
        list:[]
      }
    },
  created () {},
  mounted () {
    this.fetchData()
  },
  methods: {
    // 获取列表
    async fetchData() {
      const that = this
      let data = {}
      data.stage = this.radio
      data.enable = '启用'
      try {
        that.isSubmitLoading = true
        const res = await findAll(data)
        that.isSubmitLoading = false
        if(res.data.code === 1){
          this.list = res.data.data.content
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    toDetail(id,type){
      this.$router.push({
        path:'/theMy/myNeedsDetaile',
        query:{
          id:id,
          type:type
        }
      })
    }
  }
}
</script>
<style lang='scss' scoped>

.my-interface {
  padding: 20px;
  height: 100%;
  background-color: #fff;
  border-radius: 5px;
}
/deep/ .el-radio-button__inner{
  padding: 9px 40px;
}
.list{
  margin-top:25px;
  padding: 0 30px;
  >div{
    padding-bottom: 20px;
    border-bottom: 1px dashed #BEBEBE;
    .title{
      font-size: 18px;
      display: flex;
      align-items: center;
    }
    .time{
      color: #878787;
      font-size: 14px;
      margin-top: 10px;
    }
    .content{
      font-size: 14px;
      width: 80%;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      margin-top: 10px;
    }
    .detaile{
      span{
        cursor: pointer;
      }
      font-size: 12px;
      color: #0075C1;
      margin-top: 10px;
    }
    .el-image{
      width: 25px;
      height: 25px;
    }
  }
}

</style>