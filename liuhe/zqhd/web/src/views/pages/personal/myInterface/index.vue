<!-- 我的接口 -->
<template>
  <div class="my-interface">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="待办通知" name="first">
        <el-col :span="24" class="center">
          <TableList
            :table-data="apiTableData"
            v-loading="isSubmitLoading"
            :table-selection="apiTableSelection"
            :table-label="apiTableHeader"
          ></TableList>
        </el-col>
        <el-col :span="24">
          <pagination ref="apiPage" :total="apiTotal" @pageChange="apiPageChange"></pagination>
        </el-col>
      </el-tab-pane>
      <el-tab-pane label="回执消息" name="second">
        <el-col :span="24" class="center">
          <TableList
            :table-data="appTableData"
            v-loading="isSubmitLoading"
            :table-selection="appTableSelection"
            :table-label="appTableHeader"
          ></TableList>
        </el-col>
        <el-col :span="24">
          <pagination ref="appPage" :total="appTotal" @pageChange="appPageChange"></pagination>
        </el-col>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import TableList from '@/components/table/tableList.vue'
import Pagination from '@/components/table/Pagination.vue'
import {findAll,findMyAllApi} from '@/api/myInterface.js'

export default {
  components: { TableList, Pagination },
  data() {
      return {
        activeName: 'first',
        activeName1: 'first',
        apiTableSelection: {
        key: false,
        type: 'selection',
        detaile: false
        },
        appTableSelection:{
          key: false,
          type: 'selection',
          detaile: false
        },
        isSubmitLoading: false,
        apiTableData: [],
        appTableData:[],
        apiTableHeader: [
          {id: false, type: 'color', label: '通知', list: 'title'},
          {id: false, type: 'color', label: '来源', list: 'apiName'},
          {id: false, type: 'color', label: '状态', list: 'status'},
          {id: false, type: 'color', label: '时间', list: 'createTime'},
        ],
        appTableHeader:[
          {id: false, type: 'color', label: '通知', list: 'title'},
          {id: false, type: 'color', label: '来源', list: 'apiName'},
          {id: false, type: 'color', label: '状态', list: 'status'},
          {id: false, type: 'color', label: '时间', list: 'createTime'},
        ],
        dialogVisible: false,
        apiTotal: 0,
        apiPageSize: '20',
        apiCurrentPage: '1',
        appTotal: 0,
        appPageSize: '20',
        appCurrentPage: '1',
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
      data.page = this.apiCurrentPage,
      data.size = this.apiPageSize
      try {
        that.isSubmitLoading = true
        const res = await findAll(data)
        that.isSubmitLoading = false
        if(res.data.code === 1){
          this.apiTableData = res.data.data.content
          this.apiTableData.forEach((v,i)=>{
            v.apiName = '管理员'
          })
          this.apiTotal =  res.data.data.totalElements
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleClick1(tab, event) {
      console.log(tab, event);
    },
    apiDetail(row){
      this.dialogVisible = true
      console.log(11111111)
    },
    handleDetail(row){
      let that = this
      that.$router.push({ 
        path: '/dataDetail',
        query: {
          id: 'adsf'
        }
      })
    },
    again(row){
      let that = this
      that.$router.push({ 
        path: '/applyAgain'
      })
    },
    unfollow(row){
      
    }
  }
  
}
</script>
<style lang='scss' scoped>
/deep/.el-dialog__header{
    padding: 20px 20px 15px;
    border-bottom: 1px solid #eee;
    font-size: 14px;
    color: #333;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    background-color: #f8f8f8;
    border-radius: 2px 2px 0 0;
    
}
.info{
  padding:0 15px;
  padding-bottom: 20px;
  box-sizing: border-box;
  /deep/.el-row {
      display: flex;
      padding:20px 0;
      span{
        flex: 1;
      }
  }
  .top{
    margin-top: 20px;
    width: 100%;
    display: flex;
    .img{
      width: 107px;
      height: 107px;
      background: #fff;
      border: 1px solid #ededed;
      margin-right: 20px;
      img{
        width: 100%;
        height: 100%;
      }
    }
    .font{
      flex: 1;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      #fontTop{
        display: flex;
        justify-content: space-between; 
        position: relative;
        i{
          margin-left: 15px;
          margin-right: 5px;
          font-size: 18px;
        }
        .el-icon-star-on{
          color: orange;
        }
        .el-tag {
          position: absolute;
          right: 0;
          top: 40px;
          background-color: #ecf5ff;
          border: 1px solid #d9ecff;
          display: inline-block;
          height: 32px;
          padding: 0 10px;
          line-height: 30px;
          font-size: 12px;
          color: #409eff;
          border-radius: 4px;
          box-sizing: border-box;
          white-space: nowrap;
        }
      }
      #fontBot{
        display: flex;
        span{
          flex: 1;
        }
      }
    }  
  }
  .bot{
    margin-top: 20px;
    width: 100%;
    display: flex;
    .isAttention {
      width: 107px;
      height: 23px;
      border-radius: 1px;
      border: 1px solid #00aa71;
      margin-top: 17px;
      font-size: 11px;
      font-family: PingFangSC-Regular;
      font-weight: 400;
      line-height: 23px;
      cursor: pointer;
      text-align: center;
      margin-right: 20px;
      i{
        font-size: 14px;
        margin-right: 8px;
      }
    }
    p{
      flex: 1;
      margin-top: 17px;
    }
  }
  /deep/ .el-tabs{
     margin-top: 50px; 
  }
  /deep/ .el-tabs__item {
    font-size: 14px !important;
  }
}
.my-interface {
  padding: 20px;
  height: 100%;
  background-color: #fff;
  border-radius: 5px;
}
/deep/ .el-tabs__item {
  font-size: 20px !important;
}
/deep/ .center {
  padding-left: 0 !important;
  padding-right: 0 !important;
}
</style>