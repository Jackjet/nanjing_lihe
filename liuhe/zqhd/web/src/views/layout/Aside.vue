<!--
 * 侧边栏组件
-->
<template>
  <div class="left-nav scrollNav banxin">
    <div class="nav-logo">
      <img class="theLogo" src="../../assets/image/logo.png" />
      <span class="nav-title" v-show="titleShow">{{ title }}</span>
    </div>
    <el-menu
      class="el-menu-vertical-demo"
      :default-active="onRoutes"
      mode="horizontal"
      :unique-opened="true"
      :collapse="isCollapse"
      router
    >
      <template v-for="(item, index) in items">
        <template v-if="item.children && item.children.length > 0">
          <el-submenu :index="index" :key="index">
            <template slot="title">
              <span style="font-size: 15px" slot="title">{{ item.name }}</span>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item
            :index="item.path"
            v-if='item.path == "/login" && !isLogin'
            :key="index"
            @click="clickMenu(item)"
          >
            <i :class="item.icon"></i>
            <span style="font-size: 15px" slot="title">{{ item.name }}</span>
          </el-menu-item>
          <el-menu-item v-else-if='item.path == "/login" && isLogin'>
            <el-dropdown placement="bottom-start" class="avatar-container">
              <div class="avatar-wrapper">
                <span class="na">{{ name }}</span>
              </div>
              <el-dropdown-menu style="margin-top: 10px;" slot="dropdown" class="user-dropdown">
                <el-dropdown-item @click.native="toMy">
                  <span style="display:block;">个人中心</span>
                </el-dropdown-item>
                <el-dropdown-item divided @click.native="logout">
                  <span style="display:block;">退出登录</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-menu-item>
          <el-menu-item
            :index="item.path"
            v-else
            :key="index"
            @click="clickMenu(item)"
          >
            <i :class="item.icon"></i>
            <span style="font-size: 15px" slot="title">{{ item.name }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
    <!-- <el-input class="seach-box" clearable maxlength='100' size="medium" placeholder="请输入关键字查询" v-model="seachKey" suffix-icon="el-icon-search"></el-input> -->
  </div>
</template>
 
<script>
import bus from "@/utils/bus";

export default {
  name: "MyAside",
  data() {
    return {
      title: "六合经济开发区政企互动项目",
      name: 'admin',
      isLogin: false,
      // seachKey: '',
      items: [
        { id: "a0000", name: "首页", icon: "firstPage", path: "/homePage",icon:"icon iconfont icon-shouye" },
        {
          path: "/park",
          icon:"icon iconfont icon-yuanqu",
          name: "园区介绍",
          id: "ffbfa1599ff34cb8bbdbcdec6fe46b61",
        },
        {
          path: "/dynamic",
          icon:"icon iconfont icon-zixun",
          name: "动态资讯",
          id: "b6b8d3c1a18c4165b5070fdf91684815",
        },
        {
          path: "/policy",
          name: "政策体系",
          icon:"icon iconfont icon-zhengcezhipei",
          id: "81271ed483d54ddb814b8c72de9e7b04",
        },
        {
          path: "/service",
          name: "日常服务",
          icon:"icon iconfont icon-fuwu",
          id: "96050fae0d674f3998ee81a3556b3164",
        },
        {
          path: "/classroom",
          name: "线上课堂",
          icon:"icon iconfont icon-biaoqiankuozhan_shipin-178",
          id: "b73cd5e570604ffb81885bcd47467c15",
        },
        // {
        //   path: "/message",
        //   icon:"icon iconfont icon-liuyan",
        //   name: "留言板",
        //   id: "b73cd5e570604ffb81885bcd47467c68",
        // },
        {
          path: "/login",
          icon:"icon iconfont icon-dengludanse",
          name: "登录 / 注册",
          id: "b73cd5e570604ffb81885bcd47467c15",
        }
      ],
      titleShow: true,
      isCollapse: false,
    };
  },
  computed: {
    onRoutes() {
      return this.$route.path;
    },
  },
  created() {
    if(sessionStorage.getItem('UserInfo')){
      this.isLogin = true
      this.name = JSON.parse(sessionStorage.getItem('UserInfo')).name
    }else{
      this.isLogin = false
    }
    // 通过bus退出登录
    bus.$on('logout', () => {
      this.toDoLogout()
    })
  },
  mounted(){
  },
  methods: {
    // 点击侧边栏动作，获取路由存入面包屑
    clickMenu(info) {
      console.log(info.path)
    },
    toMy () {
      this.$router.push('/theMy')
    },
    logout() {
      const that = this
      that.$confirm("是否退出登录?", "提示", {
        type: "warning"
      }).then(async () => {
        that.toDoLogout()
      }).catch(() => {
        return false
      })
    },
    toDoLogout(){
       sessionStorage.removeItem("UserInfo")
      this.$router.push('/login')
    }
  },
};
</script>

<style lang="scss" scoped>
.icon {
  line-height: 15px;
}
.el-menu--horizontal>.el-menu-item.is-active{
  border: 0;
}
.left-nav {
  height: 60px;
  background: transparent;
  .seach-box {
    position: absolute;
    right: 20px;
    top: 10px;
    width: 200px;
    /deep/ .el-input__inner {
      // border-color: #7d7d7d;
      // background-color: #ffffff00;
      &:focus {
        // border-color: #ececec;
      }
    }
  }
  .nav-logo {
    z-index: 99;
    position: absolute;
    left: 100px;
    top: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    .theLogo {
      width: auto;
      height: 30px;
      margin-right: 15px;
    }
    .nav-title {
      color: $color-MenuText;
      font-weight: 900;
      font-size: 18px;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
    }
  }
  .el-menu-vertical-demo {
    border: none;
    margin-left: 335px;
    display: flex;
    justify-content: space-between;
    background-color: #ffffff00;
    // color: white;
    /deep/ .el-submenu__title {
      &:focus {
      }
    }
    .el-menu-item {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      i{
        font-size: 20px;
        color: white;
      }
      span {
        color: white;
        font-size: 16px;
        line-height: 20px;
        margin-top: 5px;
      }
      p{
        line-height: 20px;
        margin-top: 5px;
      }
      &:hover {
        // background-color: $color-MenuHoverBg;
        background: transparent;
        color: #30aff1;
        i{
          color: #30aff1;
        }
        span{
          color: #30aff1;
        }
      }
      &:focus {
        background: transparent;
        color: #30aff1 !important;
        i{
          color: #30aff1 !important;
        }
        span{
          color: #30aff1 !important;
        }
      }
    }
    .el-submenu {
      /deep/ .el-menu--inline {
        background-color: $color-MenuSubBg;
      }
      span {
        // color: $color-MenuText;
        font-size: 16px;
      }
      .el-menu-item {
        span {
          color: $color-MenuText;
          font-size: 14px;
        }
      }
    }
    .el-menu-item.is-active {
      color: white;
      span {
        color: white;
      }
    }
  }
}
.scrollNav{
  background: white !important;
  // border-bottom: 2px solid gray;
  box-shadow: 0px 15px 10px -15px gray;
  color: #909399 !important;
  .el-menu-item {
      i{
        font-size: 20px;
        color: #909399 !important;
      }
      span {
        color: #909399 !important;
        font-size: 16px;
        line-height: 20px;
        margin-top: 5px;
      }
      &:hover {
        color: #30aff1 !important;
        i{
          color: #30aff1 !important;
        }
        span{
          color: #30aff1 !important;
        }
      }
      &:focus {
        background: transparent;
        color: #30aff1 !important;
        i{
          color: #30aff1 !important;
        }
        span{
          color: #30aff1 !important;
        }
      }
    }
  .is-active{
    i{
      color: #30aff1 !important;
    }
    span{
      color: #30aff1 !important;
    }
  }
  .nav-title{
    color: black !important;
  }
}
</style>
