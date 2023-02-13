<!-- 认证页面 -->
<template>
  <div>
    <team v-if="userType === 'team'"></team>
    <personal v-else></personal>
  </div>
</template>

<script>
import team from './team'
import personal from './personal'
export default {
  components: {team, personal},
  data () {
    return {
      userType: '11'
    }
  },
  created () {},
  mounted () {},
  methods: {
    init () {
      this.userType = sessionStorage.getItem("userType")
      console.log(sessionStorage.getItem("userType"))
    }
  }
}
</script>
<style lang='scss' scoped>
</style>