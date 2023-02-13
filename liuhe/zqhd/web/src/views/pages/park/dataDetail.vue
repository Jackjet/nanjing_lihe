<!-- 数据资源 -->
<template>
  <div class="banxin data-resources">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/park' }">数据资源</el-breadcrumb-item>
      <el-breadcrumb-item>数据详情</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="resources-top-box model-css">
      <h3 class="clearfix">查询1978年以来职工工资总额和平均工资<i>数据</i></h3>
      <!-- <el-button  type="success" size='mini'>立即申请</el-button> -->
      <div class="resources-btn">
        <el-button size='mini'><i class="el-icon-star-on" :style="isFollow ? {color: '#f00'} : {color: '#000'}"></i> 收藏</el-button>
        <el-button size='mini' icon="el-icon-edit">纠错</el-button>
      </div>
    </div>
    <div class="abstract-box model-css">
      <h4>摘要：易制毒化学品行业企业信息易制毒化学品行业企业信息易制毒化学品行业企业信息</h4>
      <el-row>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span='8'>
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
      </el-row>
      <i>数据格式：数据</i><i style="margin: 0 10px;">|</i><i>文件下载：xls</i>
    </div>
    <div class="file-download-box model-css">
      <h3>文件下载</h3>
      <TableList :table-data='tableData1'
        :table-selection="tableSelection"
        :table-label="tableHeader1"
        :table-option="tableOpction">
      </TableList>
    </div>
    <div class="data-box model-css clearfix">
      <h3>数据项</h3>
      <TableList :table-data='tableData2'
        :table-selection="tableSelection"
        :table-label="tableHeader2">
      </TableList>
      <pagination ref="page" :total="total" @pageChange="pageChange"></pagination>
    </div>
  </div>
</template>

<script>
import Pagination from '@/components/table/Pagination'
import TableList from '@/components/table/tableList'
export default {
  components: { Pagination, TableList },
  data() {
    return {
      isFollow: true,
      tableSelection: {
        key: true,
        type: 'index',
        detaile: false
      },
      tableHeader1: [
        {id: false, type: '', label: '文件类型', list: 'createByName'},
        {id: false, type: '', label: '文件名称', list: 'api'},
        {id: false, type: '', label: '文件生成时间', list: 'contentMsg'},
      ],
      tableData1: [{
        createByName: '1',
        api: '1',
        contentMsg: '1',
      }, {
        createByName: '2',
        api: '2',
        contentMsg: '2',
      }, {
        createByName: '3',
        api: '3',
        contentMsg: '3',
      }],
      tableHeader2: [
        {id: false, type: '', label: '英文名称', list: 'createByName'},
        {id: false, type: '', label: '中文名称', list: 'api'},
        {id: false, type: '', label: '字段类型', list: 'contentMsg'},
        {id: false, type: '', label: '字段长度', list: 'contentMsg'},
        {id: false, type: '', label: '字段精度', list: 'contentMsg'},
        {id: false, type: '', label: '是否主键', list: 'contentMsg'},
      ],
      tableData2: [{
        createByName: 'a',
        api: 'a',
        contentMsg: 'a',
      }, {
        createByName: 'b',
        api: 'b',
        contentMsg: 'b',
      }, {
        createByName: 'c',
        api: 'c',
        contentMsg: 'c',
      }],
      tableOpction: {
        label: '操作',
        width: '180px',
        value: 0,
        options: [{
          label: '',
          key: 0,
          type: 'text',
          icon: 'el-icon-download',
          State: true,
          method: (row) => {
            console.log(row.api)
          }
        }]
      },
      total: 0,
      pageSize: '20',
      currentPage: '1',
    };
  },
  created () {
      console.log(this.$route.query.id)
  },
  mounted () {},
  methods: {
    aaa () {
      console.log(this.from)
    },
    seachDept () {
      console.log(this.bumen)
    },
    seachQuxian () {
      console.log(this.bumen)
    },
    doSideSeach (id) {
      this.bumen = id
      this.bumenFlag = id
    },
    seach () {
      console.log('toDoSeach')
    }
  },
};
</script>
<style lang='scss' scoped>
.data-resources {
  .model-css {
    background-color: #fff;
    border-radius: 5px;
  }
  .resources-top-box {
    position: relative;
    padding: 20px;
    h3 {
      height: 30px;
      line-height: 30px;
      font-size: 18px;
      font-weight: bold;
      i {
        font-weight: normal;
        font-size: 12px;
        margin-left: 10px;
        padding: 2px 10px;
        color: #fff;
        background-color: #4E9FFB;
        border-radius: 5px;
        vertical-align:middle;
      }
    }
    .resources-btn {
      position: absolute;
      right: 20px;
      bottom: 20px;
      .el-button {
        padding: 5px 10px;
        color: #2D2D2D;
        border-color: #dcdcdc;
      }
    }
  }
  .abstract-box {
    margin-top: 20px;
    position: relative;
    padding: 20px;
    .el-row {
      margin: 10px 0;
      .el-col {
        margin-bottom: -1px;
      }
    }
    h4 {
      font-size: 14px;
    }
    i {
      font-size: 12px;
    }
    span {
      display: block;
      float: left;
      text-align: center;
      height: 30px;
      line-height: 30px;
      border: 1px solid #DCDCDC;
      font-size: 14px;
      margin-right: -1px;
    }
    .the-abstract-key {
      width: 39%;
      background-color: #EFEFEF;
    }
    .the-abstract-value {
      width: 60%;
    }
  }
  .file-download-box {
    margin-top: 20px;
    position: relative;
    padding: 20px;
    h3 {
      font-size: 18px;
      font-weight: bold;
    }
  }
  .data-box {
    margin: 20px 0;
    position: relative;
    padding: 20px;
    h3 {
      font-size: 18px;
      font-weight: bold;
    }
  }
}
</style>