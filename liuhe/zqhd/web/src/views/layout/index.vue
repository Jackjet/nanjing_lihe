<!--
 * @Author: your name
 * @Date: 2020-09-10 19:00:17
 * @LastEditTime: 2020-09-27 21:35:33
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \webadmin\src\layout\index.vue
-->
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
  <div class="the-viewbox">
    <div style="position: fixed; left: 0; top: 0; z-index: 999;width: 100%; height: 60px;">
     
      <div style="height: 60px;">
        <my-aside class="the-aside"></my-aside>
      </div>
    </div>
    <el-scrollbar class='the-content-box'>
      <!-- <div style="width: 100%; height: 60px;"></div> -->
      <div class="the-right">
        <!-- 主页面 -->
          <div class="content">
            <div class="the-main">
              <router-view @reroute="reloadRote"></router-view>
            </div>
          </div>
      </div>
      <div style="background-image: linear-gradient(to right, #405671 , #1F3044);">
        <my-footer class="the-aside"></my-footer>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import MyAside from "@/views/layout/Aside";
import MyHeader from "@/views/layout/Header";
import MyFooter from "@/views/layout/footer";

export default {
  name: 'Layout',
  components: { MyAside, MyHeader, MyFooter, },
  data() {
    return {
      show: false,
      scroll: 0,
    }
  },
  mounted() {
    window.addEventListener('scroll', this.scrollD, true)
  },
  methods: {
    reloadRote () {
      console.log(this.$route)
    },
    scrollD() {
      const that = this
      that.scroll = document.documentElement.scrollTop
      // alert( that.scroll)
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
    background-color: #EBECF108;
    background-size: cover;
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    .the-aside {
      height: 100%;
    }
    .the-content-box {
      height: 100%;
      position: relative;

      /deep/ .el-scrollbar__wrap {
        overflow-x: hidden;
        .the-main {
          width: 100%;
          overflow-x: hidden;
        }
      }
    }
    .the-right {
      overflow: hidden;
      padding: 0;
      .the-header {
        height: 45px;
        width: 100%;
        border-bottom: 1px solid $color-HeaderBorder;
        box-sizing: border-box;
      }
    }
  }


</style>
