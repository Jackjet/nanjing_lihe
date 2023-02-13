<!-- 个人信息页 -->
<template>
  <div class="my-info">
      <basicInfo ref="first"></basicInfo>
  </div>
</template>

<script>
import basicInfo from './basicInfo'
import setPwd from './setPwd'
import authentication from './authentication'
export default {
  components: { basicInfo, authentication, setPwd },
  data () {
    return {
      activeName: 'first'
    }
  },
  created () {},
  mounted () {
    this.handleClick()
  },
  methods: {
    handleClick () {
      this.$refs[this.activeName].init();
    }
  }
}
</script>
<style lang='scss' scoped>
.my-info {
  padding: 0 20px;
  height: 100%;
  background-color: #fff;
  border-radius: 5px;
  /deep/ .el-tabs__item {
    height: 60px;
    line-height: 60px;
    font-size: 20px;
    transition: 0.3s;
    &:hover {
      color: #000;
      font-size: 24px;
    }
  }
  /deep/ .is-active {
    color: #000;
    font-size: 24px;
  }
  /deep/ .el-tabs__active-bar {
    background-color: #000;
  }
}
</style>