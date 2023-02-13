<!-- 数据资源 -->
<template>
  <div class="banxin data-resources">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>园区介绍</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="20" style="padding: 20px;box-sizing:border-box;background:white">
      <div class="tit">
        南京六合经开区园区
        <span class="line"></span>
      </div>
      <div class="enter">
        <el-image :src="require('./../../../assets/image/enter.png')" fit="cover"></el-image>
        <div class="enterInfo">
          <p>园区名称： 南京六合经开区园区</p>
          <p>主导产业： 医药制造业 专用设备制造业 食品制造业</p>
          <p>园区面积： 50平方公里</p>
          <p>联系人： 谢春生</p>
          <p>联系电话： +86-025-57150909、57675511</p>
          <p>通讯地址： 江苏省南京市六合区雄州南路268号 六合大厦27、28、29F</p>
          <p>运营商： 南京市经济技术开发区招商局</p>
          <p>入驻企业家数：10</p>
        </div>
        <div class="phone">
          <div>
            <i class="el-icon-phone"></i>
            <span>025-57675511</span>
          </div>
          <button>我要入驻</button>
        </div>
      </div>
      <div class="tit">
        园区详细介绍
        <span class="line"></span>
      </div>
      <div class="detaile">
        <p>六合经开区是1993年11月经省政府批准的省级经济开发区，批准规划面积4.8平方公里，
                  原址在六合县城北部，2000年迁至现址， 2001年底正式起步开发建设。2005年4月园区通过国土资源部审核确定的规划面积为10平方公里，
                  其中龙池片区6.5平方公里，雄州工业园片区3.5平方公里（雄州街道负责规划管理）。后经几轮的行政区划调整，
                  2008年园区社会事务职能划归龙池街道统一负责，实行“园区带街镇”管理模式。
                  现园区总规划面积为25平方公里，已完成22平方公里基础设施开发，园区已度过粗放型的框架建设</p>
        <p>六合经开区是1993年11月经省政府批准的省级经济开发区，批准规划面积4.8平方公里，
                  原址在六合县城北部，2000年迁至现址， 2001年底正式起步开发建设。2005年4月园区通过国土资源部审核确定的规划面积为10平方公里，
                  其中龙池片区6.5平方公里，雄州工业园片区3.5平方公里（雄州街道负责规划管理）。后经几轮的行政区划调整，
                  2008年园区社会事务职能划归龙池街道统一负责，实行“园区带街镇”管理模式。
                  现园区总规划面积为25平方公里，已完成22平方公里基础设施开发，园区已度过粗放型的框架建设</p>
      </div>
      <div class="tit">
        园区区域规划
        <span class="line"></span>
      </div>
      <div class="park">
        <el-image :src="require('./../../../assets/image/park.png')" fit="cover"></el-image>
      </div>
      <div class="tit">
        园区地图
        <span class="line"></span>
      </div>
      <!-- <div class="park">
        <el-image :src="require('./../../../assets/image/park.png')" fit="cover"></el-image>
      </div> -->
      <div style="width:100%;text-align:center;padding-top:20px">
        <el-image 
          style="width:40%;height:auto;"
          :src="url" 
          :preview-src-list="srcList"
          >
        </el-image>
      </div>
      <!-- <div class="map">
        <baidu-map :style="{height: mapHeight}" class="map" :mapClick="false" :center="center" :zoom="zoom" @ready="handler">
        <bm-navigation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" type="BMAP_NAVIGATION_CONTROL_ZOOM"></bm-navigation>
      </baidu-map>
      </div> -->
    </el-row>
  </div>
</template>

<script>
import Pagination from '@/components/table/Pagination'
export default {
  components: { Pagination },
  data() {
    return {
      url: require('./../../../assets/image/map.jpg'),
      srcList: [
        require('./../../../assets/image/map.jpg')
      ],
      BMap: '',
      map: '',
      mapHeight: '500px',
      center: {lng: '0', lat: '0'},
      zoom: 3,
      isOpen: false,
      follow: true,
      seachKey: '',
      from: {
        type: '11',
        attribute: [],
        area: [],
      },
      bumenFlag: '',
      areaTab: 'first',
      bumen: '',
      quxian: '',
      seach: {
        bumen: '',
        quxian: '',
        type: '',
        shuxing: '',
        zhuti: '',
      },
      total: 0,
      pageSize: '20',
      currentPage: '1',
    };
  },
  created () {
    // if (document.body.clientHeight > 600) {
    //   this.mapHeight = document.body.clientHeight - 200 + 'px'
    // }
  },
  mounted () {},
  methods: {
    handler ({BMap, map}) {
      console.log(BMap, map)
      this.BMap = BMap
      this.map = map
      this.center.lng = 118.82155
      this.center.lat = 32.32222
      this.zoom = 15
      map.enableScrollWheelZoom(true)
      map.setDisplayOptions({ poiIcon: false })
    },
    doOpen () {
      this.isOpen = !this.isOpen
    },
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
    },
    toDetail (id) {
      const that = this
      if (id === 'qwer') {
        that.$router.push({ 
          path: '/interfaceDetail',
          query: {
            id: id
          }
        })
      } else {
        that.$router.push({ 
          path: '/dataDetail',
          query: {
            id: id
          }
        })
      }
    }
  },
};
</script>
<style lang='scss' scoped>
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
.enter{
  width: 100%;
  height: 300px;
  padding: 20px 0;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  .el-image{
    width: 300px;
    height: 100%;
  }
  .enterInfo{
    flex: 1;
    padding-left: 30px;
    box-sizing: border-box;
    color: #505050;
    p{
      line-height: 30px;
      font-size: 14px;
    }
  }
  .phone{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    i{
      width: 20px;
      height: 20px;
      border-radius: 50%;
      border: 1px solid #303A4A;
      color: #303A4A;
      text-align: center;
      line-height: 20px;
      margin-right: 15px;
    }
    span{
      font-size: 20px;
      font-weight: 900;
    }
    button{
      width: 100px;
      margin-top: 10px;
      background: #0075C1;
      color: white;
      border: 0;
      outline: 0;
      padding: 8px 0;
      border-radius: 5px;
    }
  }
}
.detaile{
  p{
    padding: 20px;
    box-sizing: border-box;
    font-size: 14px;
    color: #505050;
    text-indent: 24px;
    line-height: 40px;
  }
}
.map{
  padding: 20px;
  box-sizing: border-box;
}
.park{
  padding: 20px;
  box-sizing: border-box;
  .el-image{
    width: 100%;
    height: 100%;
  }
}
</style>