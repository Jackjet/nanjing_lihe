<!-- 布局方式
---------------------------------
      |       Header           |
      |------------------------|
Aside |                        |
      |       Main             |
      |                        |
------|------------------------|
-->
<template>
  <el-container class="the-viewbox">
    <my-aside class="the-aside"></my-aside>
    <div class="the-right">
      <my-header class="the-header"></my-header>
      <!-- 主页面 -->
      <el-scrollbar class='the-content-box'>
        <div class="content" style="padding: 20px;">
          <div class="the-main" :style="{'min-height': clientHeight}">
            <router-view @reroute="reloadRote"></router-view>
          </div>
        </div>
      </el-scrollbar>
    </div>
  </el-container>
</template>

<script>
import MyAside from "@/views/layout/Aside";
import MyHeader from "@/views/layout/Header";

export default {
  name: 'Layout',
  components: {
    MyAside,
    MyHeader
  },
  data() {
    return {
      show: false,
      scroll: 0,
      clientHeight: '600px',
      delivery: false
    }
  },
  mounted() {
    this.clientHeight = (document.documentElement.clientHeight - 100) + 'px'
    window.addEventListener('scroll', this.scrollD, true)
  },
  methods: {
    reloadRote() {
      console.log(this.$route)
    },
    scrollD() {
      const that = this
      that.scroll = document.documentElement.scrollTop
      if (that.scroll > 50) {
        that.show = true
      } else if (that.scroll <= 50) {
        that.show = false
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.the-viewbox {
  position: relative;
  /* background: url('../../assets/image/mainBg.jpg'); */
  background-color: #ebecf108;
  background-size: cover;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  display: flex;

  .the-aside {
    height: 100%;
  }
  .the-right {
    height: 100%;
    width: 100%;
    flex: 1;
    overflow: hidden;
    margin: 0;
    padding: 0;
    position: relative;

    .the-header {
      height: 45px;
      width: 100%;
      border-bottom: 1px solid $color-HeaderBorder;
      box-sizing: border-box;
    }
    .the-content-box {
      height: calc(100% - 45px);
      width: 100%;
      position: relative;

      /deep/ .el-scrollbar__wrap {
        overflow-x: hidden;
        .the-main {
          box-shadow: 0 0 30px #e6e6e6;
          background-color: #ffffffd1;
          border-radius: 5px;
          width: 100%;
          overflow-x: hidden;
        }
      }
    }
  }
}
</style>
