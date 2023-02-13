<!-- 数据资源 -->
<template>
  <div class="banxin data-resources">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/park' }">数据资源</el-breadcrumb-item>
      <el-breadcrumb-item>数据详情</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="resources-top-box model-css">
      <h3 class="clearfix">查询1978年以来职工工资总额和平均工资<i>接口</i></h3>
      <el-button type="success" size="mini">立即申请</el-button>
      <div class="resources-btn">
        <el-button size="mini">
          <i class="el-icon-star-on" :style="isFollow ? { color: '#f00' } : { color: '#000' }"></i>
          收藏
        </el-button>
        <el-button size="mini" icon="el-icon-edit">纠错</el-button>
      </div>
    </div>
    <div class="abstract-box model-css">
      <h4>
        摘要：易制毒化学品行业企业信息易制毒化学品行业企业信息易制毒化学品行业企业信息
      </h4>
      <el-row>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
        <el-col :span="8">
          <span class="the-abstract-key">资源代码</span>
          <span class="the-abstract-value">数据领域</span>
        </el-col>
      </el-row>
      <i>数据格式：数据</i>
    </div>
    <div class="interface-data-box model-css">
      <el-tabs v-model="activeName">
        <el-tab-pane label="接口信息" name="first" class="interface-data-first">
          <div>
            <h3>公共参数</h3>
            <p>接口地址： http://dotu.zizwfw.gov.cn/interfuce/guteway.do</p>
            <p>接口唯一标识： 331111zhweeiYN20210528113542045357SynReq</p>
            <p>支持格式：JSON</p>
            <p>请求方式：GET/POST</p>
            <p>接口描述：获取数据</p>
            <p>请求样例： http://doto.isrwfw.gov.an/interfaon/gotewey.do"opp_id”?”值”&k"intetface_id"”值”&"version"”值”&”charset”!"值”&”timestamp”?”值”&"otigin”:”值”&"sign”值"&”bix_content”"*值”</p>
          </div>
          <div>
            <h3>请求参数</h3>
            <TableList :table-data='tableData1' :table-label="tableHeader1"></TableList>
          </div>
          <div>
            <h3>json参数</h3>
            <TableList :table-data='tableData2' :table-label="tableHeader2"></TableList>
          </div>
          <div>
            <h3>返回参数</h3>
            <TableList :table-data='tableData3' :table-label="tableHeader3"></TableList>
          </div>
          <div>
            <h3>返回示例</h3>
            <p>{"result":{"total":4001,"pageSize":10,"list":[{"area":"510400", "action time":1517998615000,"action result":1,"ip": "219.144.188.75","behavior_type":"LOGOUT","account":"share_admin","platform":"PORTAL")]."pageNum":11),"code""000000","message":"OK"}</p>
          </div>
        </el-tab-pane>
        <el-tab-pane label="调用示例" name="second">
          <TableList :table-data='tableData4' :table-label="tableHeader4"></TableList>
        </el-tab-pane>
        <el-tab-pane label="数据预览" name="third">
          <TableList :table-data='tableData5' :table-label="tableHeader5"></TableList>
        </el-tab-pane>
        <el-tab-pane label="返回码" name="fourth">
          <h3>服务返回码</h3>
          <p>{"result":{"total":4001,"pageSize":10,"list":[{"area":"510400", "action time":1517998615000,"action result":1,"ip": "219.144.188.75","behavior_type":"LOGOUT","account":"share_admin","platform":"PORTAL")]."pageNum":11),"code""000000","message":"OK"}</p>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import Pagination from "@/components/table/Pagination";
import TableList from "@/components/table/tableList";
export default {
  components: { Pagination, TableList },
  data() {
    return {
      isFollow: true,
      activeName: 'first',
      tableHeader1: [
        { id: false, type: "", label: "名称", list: "createByName" },
        { id: false, type: "", label: "类型", list: "api" },
        { id: false, type: "", label: "是否必须", list: "contentMsg" },
        { id: false, type: "", label: "描述", list: "contentMsg" },
      ],
      tableData1: [
        {
          createByName: "1",
          api: "1",
          contentMsg: "1",
        },
        {
          createByName: "2",
          api: "2",
          contentMsg: "2",
        },
        {
          createByName: "3",
          api: "3",
          contentMsg: "3",
        },
      ],
      tableHeader2: [
        { id: false, type: "", label: "json参数名称", list: "createByName" },
        { id: false, type: "", label: "json参数类型", list: "api" },
        { id: false, type: "", label: "必填", list: "contentMsg" },
        { id: false, type: "", label: "json参数描述", list: "contentMsg" },
      ],
      tableData2: [
        {
          createByName: "a",
          api: "a",
          contentMsg: "a",
        },
        {
          createByName: "b",
          api: "b",
          contentMsg: "b",
        },
        {
          createByName: "c",
          api: "c",
          contentMsg: "c",
        },
      ],
      tableHeader3: [
        { id: false, type: "", label: "名称", list: "createByName" },
        { id: false, type: "", label: "类型", list: "api" },
        { id: false, type: "", label: "描述", list: "contentMsg" },
      ],
      tableData3: [],
      tableHeader4: [
        { id: false, type: "", label: "名称", list: "createByName" },
        { id: false, type: "", label: "类型", list: "api" },
        { id: false, type: "", label: "描述", list: "contentMsg" },
      ],
      tableData4: [],
      tableHeader5: [
        { id: false, type: "", label: "json参数名称", list: "createByName" },
        { id: false, type: "", label: "json参数类型", list: "api" },
        { id: false, type: "", label: "必填", list: "contentMsg" },
        { id: false, type: "", label: "json参数描述", list: "contentMsg" },
      ],
      tableData5: [],
    };
  },
  created() {
    console.log(this.$route.query.id);
  },
  mounted() {},
  methods: {
    aaa() {
      console.log(this.from);
    },
    seachDept() {
      console.log(this.bumen);
    },
    seachQuxian() {
      console.log(this.bumen);
    },
    doSideSeach(id) {
      this.bumen = id;
      this.bumenFlag = id;
    },
    seach() {
      console.log("toDoSeach");
    },
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
        background-color: #36ce63;
        border-radius: 5px;
        vertical-align: middle;
      }
    }
    .resources-btn {
      position: absolute;
      right: 20px;
      bottom: 20px;
      .el-button {
        padding: 5px 10px;
        color: #2d2d2d;
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
      border: 1px solid #dcdcdc;
      font-size: 14px;
      margin-right: -1px;
    }
    .the-abstract-key {
      width: 39%;
      background-color: #efefef;
    }
    .the-abstract-value {
      width: 60%;
    }
  }
  .interface-data-box {
    margin: 20px 0;
    position: relative;
    padding: 20px;
    h3 {
      padding: 30px 0 15px;
      font-size: 16px;
      font-weight: bold;
    }
    p {
      font-size: 14px;
    }
    .interface-data-first {
      .el-table {
        margin: 0 !important;
      }
    }
  }
}
</style>