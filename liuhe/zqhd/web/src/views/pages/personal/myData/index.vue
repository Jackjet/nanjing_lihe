<!-- 我的接口 -->
<template>
  <div class="my-interface">
   <p class='welcome'>
         您好，欢迎来到政企互动平台（企业档案管）
   </p>
   <div class="info">
     <div class="img">
       <el-image :src="require('./../../../../assets/image/login_logo.png')"></el-image>
     </div>
     <div class="right">
       <p style="color:#505050;font-weight:900;font-size:18px">
         智慧神州（北京）科技有限公司
       </p>
       <p>
         <span>
           统一信用代码：91210800MA10UYYYXL
         </span>
         <span>
           从业人员：300-999人
         </span>
       </p>
       <p>
         <span>
            企业规模：中型
         </span>
         <span>
          联系人：粱强坤
         </span>
       </p>
       <p>
         <span>
            行业分类：绿化管理
         </span>
         <span>
           联系电话：17732279296
         </span>
       </p>
       <p>
         <span>
            成立日期：2001年1月2日
         </span>
         <span>
           邮箱：584364823@qq.com
         </span>
       </p>
     </div>
   </div>
   <div class="infoDetail">
     <p style="text-align:center;margin-bottom:30px">
       <el-radio-group v-model="radio" size="small" @change="fetchData">
          <el-radio-button label="工商主体信息"></el-radio-button>
          <el-radio-button label="法人信息"></el-radio-button>
          <el-radio-button label="投资人信息"></el-radio-button>
          <el-radio-button label="许可证信息"></el-radio-button>
        </el-radio-group>
     </p>
     <div class="gszt" v-if='radio == "工商主体信息"'>
       <p>
         <span>公司类型：有限责任公司（自然人投资或控股）</span>
         <span>注册资本：800万（万元）</span>
       </p>
       <p>
         <span>法定代表人：王小贺</span>
         <span>成立日期：2021-01-29</span>
       </p>
       <p>
         <span>营业期限：2016-01-16 至 2046-11-24</span>
         <span>登记机关：南京市工商局</span>
       </p>
       <p>
         <span>经营状态：正常营业</span>
         <span>统一信用代码：91210800MA10UYYYXL</span>
       </p>
       <p>
         <span>监管行业分类：工商</span>
       </p>
       <div class="logo">
         企业Logo： <el-image :src="require('./../../../../assets/image/login_logo.png')"></el-image>
       </div>
       <div class="content">
         <span>主体简介：</span>
         <span>
           国轩高科股份有限公司（以下简称“国轩高科”）系中国动力电池产业最早进入资本市场的民族企业，于2015年5月成功上市，股票代码002074，拥有新能源汽车动力电池、储能、输配电设备等业务板块，旗下包括合肥国轩高科动力能源有限公司、工研总院、资本中心和东源电器四大板块。
         </span>
       </div>
       <div style='margin-top:20px'>
         <span>经营场所：</span>
         <span>
           南京市六合区江北大道快速路与乙烯路交叉路口往东北约100米
         </span>
       </div>
       <div style='margin-top:20px'>
         <span>经营范围：</span>
         <span>
            “移动跨平台开发平台”、“智慧城市”等应用广泛且发展前景广阔的产品
         </span>
       </div>
     </div>
     <div class="frInfo" v-if='radio == "法人信息"'>
        <div>
          <span>法人姓名：王小贺</span>
          <span>证件类型：身份证</span>
          <span>证件号码：130623199008202123</span>
          <span>17732279296</span>
        </div>
     </div>
     <div class="tzrInfo" v-if='radio == "投资人信息"'>
        <el-col :span="24" class="center">
          <TableList
            :table-data="appTableData"
            v-loading="isSubmitLoading"
            :table-selection="appTableSelection"
            :table-label="appTableHeader"
          ></TableList>
        </el-col>
     </div>
     <div class="xkzInfo" v-if='radio == "许可证信息"'>
       <div class="list">
         <div>
          <div class="image">
            <el-image :src="require('./../../../../assets/image/xkz.png')"></el-image>
          </div>
          <p>
            安全生产许可证
          </p>
          <div>
            <p>许可证编号：91210800MA10UYYYXL</p>
            <p>发证日期：2020-03-13</p>
            <p>有效期： 2025-03-13</p>
          </div>
         </div>
         <div>
          <div class="image">
            <el-image :src="require('./../../../../assets/image/xkz.png')"></el-image>
          </div>
          <p>
            安全生产许可证
          </p>
          <div>
            <p>许可证编号：91210800MA10UYYYXL</p>
            <p>发证日期：2020-03-13</p>
            <p>有效期： 2025-03-13</p>
          </div>
         </div>
         <div>
          <div class="image">
            <el-image :src="require('./../../../../assets/image/xkz.png')"></el-image>
          </div>
          <p>
            安全生产许可证
          </p>
          <div>
            <p>许可证编号：91210800MA10UYYYXL</p>
            <p>发证日期：2020-03-13</p>
            <p>有效期： 2025-03-13</p>
          </div>
         </div>
         <div>
          <div class="image">
            <el-image :src="require('./../../../../assets/image/xkz.png')"></el-image>
          </div>
          <p>
            安全生产许可证
          </p>
          <div>
            <p>许可证编号：91210800MA10UYYYXL</p>
            <p>发证日期：2020-03-13</p>
            <p>有效期： 2025-03-13</p>
          </div>
         </div>
         <div>
          <div class="image">
            <el-image :src="require('./../../../../assets/image/xkz.png')"></el-image>
          </div>
          <p>
            安全生产许可证
          </p>
          <div>
            <p>许可证编号：91210800MA10UYYYXL</p>
            <p>发证日期：2020-03-13</p>
            <p>有效期： 2025-03-13</p>
          </div>
         </div>
         <div>
          <div class="image">
            <el-image :src="require('./../../../../assets/image/xkz.png')"></el-image>
          </div>
          <p>
            安全生产许可证
          </p>
          <div>
            <p>许可证编号：91210800MA10UYYYXL</p>
            <p>发证日期：2020-03-13</p>
            <p>有效期： 2025-03-13</p>
          </div>
         </div>
       </div>
     </div>
   </div>
  </div>
</template>

<script>

import {findAll,findMyAllData} from '@/api/myData.js'
import TableList from '@/components/table/tableList.vue'
export default {
  components: { TableList },
  data() {
      return {
        radio:'工商主体信息',
        appTableSelection:{
          key: false,
          type: 'selection',
          detaile: false
        },
        appTableData:[
          {
            name:'1',
            money:'1',
            bl:'1',
          }
        ],
        appTableHeader:[
          {id: false, type: 'color', label: '投资者姓名', list: 'name'},
          {id: false, type: 'color', label: '投资金额（万元）', list: 'money'},
          {id: false, type: 'color', label: '投资比例', list: 'bl'},
        ],
      }
    },
  created () {},
  mounted () {
  },
  methods: {
    
  }
  
}
</script>
<style lang='scss' scoped>
.info{
  padding: 30px;
  box-sizing: border-box;
  background: #F8F8F8;
  display: flex;
  align-items: center;
  .img{
    background: white;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100px;
    height: 100px;
    border: 1px solid #BEBEBE;
    background: white;
  }
  .right{
    margin-left: 30px;
    p{
      line-height: 30px;
      font-size: 14px;
      span:nth-child(1){
        width: 400px;
        display: inline-block;
      }
    }
  }
}
.xkzInfo{
  color: #505050;
  font-size: 14px; 
  .list{
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    >div{
      width: 30%;
      margin-bottom: 20px;
      display: flex;
      flex-direction: column;
      .image{
        padding: 5px;
        border-radius: 10px;
        border: 1px solid #BEBEBE;
        .el-image{
          width: 100%;
          height: 100%;
        }
      }
      >p{
        text-align: center;
        line-height: 60px;
      }
    }
  }
}
.infoDetail{
  margin-top: 20px;
  border: 1px solid #E5E5E5;
  padding: 30px;
  box-sizing: border-box;
}
.my-interface {
  padding: 20px;
  height: 100%;
  background-color: #fff;
  border-radius: 5px;
}
.welcome{
  text-align: right;
  color: #878787;
  font-size: 14px;
}
.frInfo{
  color: #505050;
  font-size: 14px;  
  div{
    display: flex;
    justify-content: space-between;
  }
} 
.gszt{
  color: #505050;
  font-size: 14px;  
  >p{
    line-height: 30px;
    
    span:nth-child(1){
      width: 500px;
      display: inline-block;
    }
  }
  .logo{
    .el-image{
      width: 100px;
      height: 30px;
      margin-left: 20px;
      padding-top: 10px;
    }
  }
  .content{
    display: flex;
    margin-top: 20px;
    span:nth-child(1){
      display: block;
      white-space: nowrap;
    }
  }
}
</style>