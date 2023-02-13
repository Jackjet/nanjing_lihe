<!-- 动态资讯 -->
<template>
  <div class="banxin customization">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>动态资讯</el-breadcrumb-item>
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
            <el-radio @change="fetchData" v-for='(v,i) in typeList' :key='i' v-model="searchItem.type" :label="v.value" border size="small">{{v.label}}</el-radio>
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
      <div class="search">
        <div>
          <el-input v-model="searchItem.title" clearable placeholder="请输入标题"></el-input>
          <i class="el-icon-search" @click="fetchData"></i>
        </div>
      </div>
      <div class="data-box">
        <el-col :span="24" class="center">
          <TableList
            :table-data="tableData"
            v-loading="isSubmitLoading"
            :table-label="tableHeader"
            :table-option="tableOptions"
          ></TableList>
        </el-col>
        <el-col :span="24">
          <pagination ref="apiPage" :total="total" @pageChange="apiPageChange"></pagination>
        </el-col>
      </div>
   </el-row> 
  </div>
</template>

<script>
import { findAll } from '@/api/dynamic'
import { findByType } from '@/api/type'
import TableList from '@/components/table/tableList.vue'
import Pagination from '@/components/table/Pagination.vue'
export default {
  components: { TableList, Pagination },
  data() {
    return {
      value1: '',
      searchItem:{},
      tableData: [
      ],
      typeList:'',
      isSubmitLoading: false,
      tableHeader: [
        {id: false, type: 'color', label: '图解', list: 'title'},
        {id: false, type: 'color', label: '来源', list: 'organizationName'},
        {id: false, type: 'color', label: '时间', list: 'createTime'}
      ],
      tableOptions:  {
          label: '操作',
          width: '200px',
          value: 0,
          options: [{
            label: '查看',
            key: 0,
            type: 'text',
            icon: 'el-icon-chat-line-square',
            State: true,
            method: (row) => {
              this.detail(row)
            }
          }
        ]
      },
      total: 0,
      pageSize: '20',
      currentPage: '1',
    }
  },
  created() {
    this.fetchData()
    this.fetchType()
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
        data.startDate = this.value1[0]
        data.endDate = this.value1[1]
      } else {
        data.startDate = ''
        data.endDate = ''
      }
      // if(data.organization && data.organization!=''){
      //   data.organizationId = data.organization[0].split('-')[0]
      //   data.organizationName = data.organization[0].split('-')[1]
      //   delete data.organization
      // }
      try {
        that.isSubmitLoading = true
        const res = await findAll(data)
        that.isSubmitLoading = false
        if (res.data.code === 1) {
          this.tableData = res.data.data.content
          this.total = res.data.data.totalElements
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    // 获取类型列表
    async fetchType(type) {
      const that = this
      let data = {}
      data.type = "动态资讯"
      try {
        that.isSubmitLoading = true
        const res = await findByType(data)
        that.isSubmitLoading = false
        if (res.data.code === 1) {

          this.typeList = res.data.data
          // this.tableData = res.data.data.content
          // this.total = res.data.data.totalElements
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    detail(row){
      this.$router.push({
        path:'dynamicDetaile',
        query:{
         id:row.id
        } 
      })
    }
  },
};
</script>
<style lang="scss" scoped>
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
.search{
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
  >div{
    display: flex;
    /deep/ .el-input__inner{
      border-right: 0;
      outline: 0 !important;
      border-radius: 4px 0 0 4px;
    }
    i{
      height:38px;
      line-height: 38px;
      text-align: center;
      width: 40px;
      border: 1px solid #DCDFE6;
      border-left: 0;
      border-radius:  0 4px 4px 0;
    }
  }
}
/deep/.el-input__inner{
  outline: 0 !important;
  border-color:0 !important;
}
</style>