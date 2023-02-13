<!-- 个人中心首页 -->
<template>
  <div class="my-firstpage">
    <div class="firstpage-top firstpage-box clearfix">
      <el-col :span="12">
        <i>关注资源</i> <span>123</span>
      </el-col>
      <el-col :span="12">
        <i>申请资源</i> <span>123</span>
      </el-col>
    </div>
    <div class="firstpage-center firstpage-box clearfix">
      <h3>我申请的资源</h3>
      <el-col :span="6">
        <div class="resources">
          <el-avatar :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
          <el-tooltip effect="dark" content="北京高职高专院校市级教师进修学校" placement="top">
            <h4>北京高职高专院校市级教师进修学校</h4>
          </el-tooltip>
          <span style="float: left;"><i class="el-icon-view"></i><b>3</b></span>
          <span style="float: right;"><i class="el-icon-star-off"></i><b>3</b></span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="resources">
          <el-avatar :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
          <el-tooltip effect="dark" content="北京高职高专院校市级教师进修学校" placement="top">
            <h4>北京高职高专院校市级教师进修学校</h4>
          </el-tooltip>
          <span style="float: left;"><i class="el-icon-view"></i><b>3</b></span>
          <span style="float: right;"><i class="el-icon-star-off"></i><b>3</b></span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="resources">
          <el-avatar :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
          <el-tooltip effect="dark" content="北京高职高专院校市级教师进修学校" placement="top">
            <h4>北京高职高专院校市级教师进修学校</h4>
          </el-tooltip>
          <span style="float: left;"><i class="el-icon-view"></i><b>3</b></span>
          <span style="float: right;"><i class="el-icon-star-off"></i><b>3</b></span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="resources">
          <el-avatar :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
          <el-tooltip effect="dark" content="北京高职高专院校市级教师进修学校" placement="top">
            <h4>北京高职高专院校市级教师进修学校</h4>
          </el-tooltip>
          <span style="float: left;"><i class="el-icon-view"></i><b>3</b></span>
          <span style="float: right;"><i class="el-icon-star-off"></i><b>3</b></span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="resources">
          <el-avatar :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
          <el-tooltip effect="dark" content="北京高职高专院校市级教师进修学校" placement="top">
            <h4>北京高职高专院校市级教师进修学校</h4>
          </el-tooltip>
          <span style="float: left;"><i class="el-icon-view"></i><b>3</b></span>
          <span style="float: right;"><i class="el-icon-star-off"></i><b>36546</b></span>
        </div>
      </el-col>
    </div>
    <div class="firstpage-bottom firstpage-box clearfix">
      <h3>我关注的资源</h3>
      <ul class="the-resources">
        <li>
          <div class="avatar-box">
            <el-avatar :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
          </div>
          <h3>查询1978年以来职工工资总额和平均工资</h3>
          <div><i>生活服务</i></div>
          <div style="margin-top: 10px;">
            <span>提供部门：市统计局</span>
            <span>浏览量：38次</span>
            <span>接入次数：2次</span>
          </div>
          <p>描述：医师资格证书核发事项...</p>
        </li>
        <li>
          <div class="avatar-box">
            <el-avatar :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
          </div>
          <h3>查询1978年以来职工工资总额和平均工资</h3>
          <div><i>生活服务</i></div>
          <div style="margin-top: 10px;">
            <span>提供部门：市统计局</span>
            <span>浏览量：38次</span>
            <span>接入次数：2次</span>
          </div>
          <p>描述：医师资格证书核发事项...</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data () {
    return {
    }
  },
  created () {},
  mounted () {},
  methods: {}
}
</script>
<style lang='scss' scoped>
.my-firstpage {
  position: relative;
  .firstpage-box {
    padding: 0 20px;
    background-color: #fff;
    border-radius: 5px;
  }
  .firstpage-top {
    position: relative;
    .el-col {
      padding: 20px 0;
      color: #36CE63;
      text-align: center;
      i {
        vertical-align: middle;
      }
      span {
        font-size: 30px;
        vertical-align: middle;
      }
    }
  }
  .firstpage-center {
    position: relative;
    margin-top: 20px;
    h3 {
      font-size: 18px;
      font-weight: bold;
      height: 50px;
      line-height: 60px;
    }
    .el-col {
      padding: 10px;
      .resources {
        padding: 10px 20px;
        height: 120px;
        border: 1px solid #ccc;
        text-align: center;
        .el-avatar {
          margin-top: 10px;
        }
        h4 {
          width: 90%;
          margin: 0 auto 10px;
          overflow: hidden;
          text-overflow:ellipsis;
          white-space: nowrap;
        }
      }
    }
  }
  .firstpage-bottom {
    position: relative;
    margin-top: 20px;
    h3 {
      font-size: 18px;
      font-weight: bold;
      height: 50px;
      line-height: 60px;
    }
    .the-resources {
      width: 90%;
      margin: 0 auto;
      li {
        position: relative;
        width: 100%;
        padding: 20px 0 20px 120px;
        box-sizing: border-box;
        border-bottom: 1px solid #DCDCDC;
        .avatar-box {
          position: absolute;
          left: 20px;
          top: 30px;
          width: 70px;
          height: 70px;
          border: 1px solid #ccc;
          text-align: center;
          span {
            margin: 10px auto;
          }
        }
        h3 {
          height: 30px;
          line-height: 30px;
          font-size: 18px;
        }
        i {
          font-size: 12px;
          padding: 2px 10px;
          color: #4E9FFB;
          border: 1px solid #4E9FFB;
          border-radius: 5px;
        }
        p {
          margin: 10px 0;
          font-size: 12px;
        }
        span {
          margin-top: 10px;
          font-size: 14px;
          color: #2D2D2D;
          margin-right: 30px;
        }
      }
    }
  }
}
</style>