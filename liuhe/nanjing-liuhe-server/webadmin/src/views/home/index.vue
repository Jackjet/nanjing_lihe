<template>
  <div class="main-home">
    <div class="head">
      <span class="date">{{ year }}年{{ month }}月{{ day }}日</span>
      <span class="title">南京六合</span>
    </div>
    <div class="content">
      <div class="top" @mouseenter="showActive(1)" @mouseleave="showActive(0)" @click.stop="nav('/setUp')">
        <div class="title">系统设置</div>
        <img :src="src1" />
      </div>
      <div class="middle">
        <div class="item" @mouseenter="showActive(2)" @mouseleave="showActive(0)" @click.stop="nav('/government')">
          <div class="title">政企互动管理后台</div>
          <img :src="src2" />
        </div>
        <div class="item" @mouseenter="showActive(3)" @mouseleave="showActive(0)" @click.stop="nav('/quality')">
          <div class="title">质量小站管理后台</div>
          <img :src="src3" />
        </div>
      </div>
      <div class="bottom" @mouseenter="showActive(4)" @mouseleave="showActive(0)">
        <div class="title">水平考核管理后台</div>
        <img :src="src4" />
      </div>
    </div>
  </div>
</template>

<script>
let off1 = require("../../assets/image/off-1.png");
let on1 = require("../../assets/image/on-1.png");
let off2 = require("../../assets/image/off-2.png");
let on2 = require("../../assets/image/on-2.png");
let off3 = require("../../assets/image/off-3.png");
let on3 = require("../../assets/image/on-3.png");
let off4 = require("../../assets/image/off-4.png");
let on4 = require("../../assets/image/on-4.png");
export default {
  data() {
    return {
      year: "",
      month: "",
      day: "",
      src1: off1,
      src2: off2,
      src3: off3,
      src4: off4,
      active: 0,
    };
  },
  mounted() {
    this.year = new Date().getFullYear();
    this.month = new Date().getMonth() + 1;
    this.day = new Date().getDate();
  },
  methods: {
    showActive(index) {
      switch (index) {
        case 1:
          this.src1 = on1;
          break;
        case 2:
          this.src2 = on2;
          break;
        case 3:
          this.src3 = on3;
          break;
        case 4:
          this.src4 = on4;
          break;
        default:
          this.src1 = off1;
          this.src2 = off2;
          this.src3 = off3;
          this.src4 = off4;
      }
    },
    nav(path) {
      let typeArr = JSON.parse(sessionStorage.getItem('UserInfo')).roleNames;
      typeArr.forEach(item => {
        if (item === '系统管理员') {
          if (path === '/setUp') {
            sessionStorage.setItem('pageType', '1');
          } else if (path === '/government') {
            sessionStorage.setItem('pageType', '2');
          } else if (path === '/quality') {
            sessionStorage.setItem('pageType', '3');
          }
          this.$router.push(path);
        } else if (item === '政企互动' && path === '/government') {
          sessionStorage.setItem('pageType', '2');
          this.$router.push(path);
        } else if (item === '质量小站' && path === '/quality') {
          sessionStorage.setItem('pageType', '3');
          this.$router.push(path);
        } else {
          this.$message.error("该用户没有权限进入此系统");
        }
      })
    },
  },
};
</script>

<style lang="scss" scope>
.main-home {
  background: url("../../assets/image/back.gif");
  position: fixed;
  height: 100%;
  width: 100%;
  background-size: 100% 100%;
  -ms-overflow-style: none;
  .head {
    position: relative;
    margin: 2vh 5vh;
    color: white;
    .date {
      position: absolute;
      bottom: -5px;
      left: 0;
      font-size: 1vh;
    }
    .title {
      display: block;
      text-align: center;
      font-size: 4vh;
      font-family: "微软雅黑";
      line-height: 4vh;
      font-weight: 600;
      text-shadow: #1d85d6 0 0 10px;
      vertical-align: top;
    }
  }
  .content {
    padding: 16vh 3vh 3vh;
    height: 93vh;
    .top {
      width: 12vw;
      margin: 0 auto;
      cursor: pointer;
      .title {
        position: relative;
        margin: 0 auto;
        width: 10vw;
        height: 5vh;
        line-height: 5vh;
        background: url(../../assets/image/off-title.png) no-repeat;
        background-size: 100% 100%;
        font-size: 2vh;
        color: #fff;
        text-align: center;
      }
      img {
        width: 12vw;
        height: 25vh;
      }
      &:hover {
        .title {
          background: url(../../assets/image/on-title.png) no-repeat;
          background-size: 100% 100%;
        }
      }
    }
    .middle {
      display: flex;
      justify-content: space-evenly;
      margin-top: -14vh;
      .item {
        width: 20vw;
        margin: 0 auto;
        cursor: pointer;
        .title {
          position: relative;
          margin: 0 auto;
          width: 13vw;
          height: 5vh;
          line-height: 5vh;
          background: url(../../assets/image/off-title.png) no-repeat;
          background-size: 100% 100%;
          font-size: 2vh;
          color: #fff;
          text-align: center;
        }
        img {
          width: 20vw;
          height: 39vh;
        }
        &:hover {
          .title {
            background: url(../../assets/image/on-title.png) no-repeat;
            background-size: 100% 100%;
          }
        }
      }
    }
    .bottom {
      width: 20vw;
      margin: 0 auto;
      margin-top: -30vh;
      cursor: pointer;
      .title {
        position: relative;
        margin: 0 auto;
        width: 15vw;
        height: 5vh;
        line-height: 5vh;
        background: url(../../assets/image/off-title.png) no-repeat;
        background-size: 100% 100%;
        font-size: 2vh;
        color: #fff;
        text-align: center;
      }
      img {
        width: 20vw;
        height: 39vh;
      }
      &:hover {
        .title {
          background: url(../../assets/image/on-title.png) no-repeat;
          background-size: 100% 100%;
        }
      }
    }
  }
}
</style>
