<template>
  <div class="banxin data-policy">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>政策体系</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="box">
      <div class="tit">
        政策体系
        <span class="line"></span>
      </div>
      <div class="bot">
        <div class="left">
          <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
        </div>
        <div class="right">
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
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { findAll } from '@/api/policy'
import TableList from '@/components/table/tableList.vue'
import Pagination from '@/components/table/Pagination.vue'
export default {
  components: { TableList, Pagination },
  data() {
    return {
      radio2: '1',
      searchItem:{},
      value1: '',
      tableData: [
        {
          dataSourceName:'1',
          openLv:'1',
          authNum:'1',
        }
      ],
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
       data: [{
          label: '一级 1',
          children: [{
            label: '二级 1-1',
            children: [{
              label: '三级 1-1-1'
            }]
          }]
        }, {
          label: '一级 2',
          children: [{
            label: '二级 2-1',
            children: [{
              label: '三级 2-1-1'
            }]
          }, {
            label: '二级 2-2',
            children: [{
              label: '三级 2-2-1'
            }]
          }]
        }, {
          label: '一级 3',
          children: [{
            label: '二级 3-1',
            children: [{
              label: '三级 3-1-1'
            }]
          }, {
            label: '二级 3-2',
            children: [{
              label: '三级 3-2-1'
            }]
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
    };
  },
  created() {},
  mounted() {
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
      if (that.value1 !== null && that.value1 !== '' && that.value1.length > 0) {
        data.startDate = this.value1[0]
        data.endDate = this.value1[1]
      } else {
        data.startDate = ''
        data.endDate = ''
      }
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
    handleNodeClick(data) {
      console.log(data);
    },
    detail(row){
      this.$router.push({
        path:'policyDetaile',
        query:{
         id:row.id
        } 
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
  padding: 20px;
  box-sizing: border-box;
  background: white;
  color: black;
  font-size: 14px;
  .bot{
    width: 100%;
    margin-top: 20px;
    min-height: 600px;
    display: flex;
    .left{
      width: 180px;
      padding: 10px;
      box-sizing: border-box;
      height: 100%;
      min-height: 600px;
      border: 1px solid gray;
      margin-right: 20px;
    }
    .right{
      flex: 1;
      .type{
        padding:  20px;
        box-sizing: border-box;
        background: #F8F8F8;
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
    }
  }
}
</style>
