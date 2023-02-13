<template>
  <div class="banxin data-policy">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>留言板</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="box">
      <div class="tit">
        留言板
        <span class="line"></span>
      </div>
      <div class="bot">
        <el-form ref="form" :model="form" label-width="120px" style="margin-top:20px;">
          <el-form-item label="留言标题" >
              <el-time-select
                v-model="value"
                :picker-options="{
                  start: '08:30',
                  step: '00:15',
                  end: '18:30'
                }"
                placeholder="选择时间">
              </el-time-select>
          </el-form-item>
          <el-form-item label="留言内容">
            <el-input type='textarea'  :rows="6" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="是否显示">
            <el-radio v-model="radio" label="1">是</el-radio>
            <el-radio v-model="radio" label="2">否</el-radio>
          </el-form-item>
          <div style='margin-top:20px;text-align:center'>
            <el-button>留言</el-button>
             <el-button type="primary">取消</el-button>
          </div>
        </el-form>
      </div>
      <div class="tit">
        我的留言列表
        <span class="line"></span>
      </div>
      <div>
      <div style="margin-top:25px;text-align:right">
        <el-button-group>
          <el-button>待回复</el-button>
          <el-button>已回复</el-button>
          <el-button>已完成</el-button>
        </el-button-group>
      </div>
      <div class="content">
        <p style="color:#2D2D2D;">
          向管理员请教资质审核需要什么材料？
        </p>
        <p style="color:#878787;font-size:14px;line-height:30px">
          发表时间：2021-05-21   19：26
        </p>
        <p style="font-size:14px;color:#2D2D2D">
          这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题，这里是我的问题
        </p>
        <div class="question">
          <p style="font-size:14px;color:#2D2D2D">您好，已经收到您的留言，会尽快帮你处理，请您耐心等待。详情可咨询：010-6598237</p>
          <p style="font-size:14px;color:#878787;margin-top:10px">回复人：管理员   回复时间：2021-05-21   09：24</p>
        </div>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      radio:1,
      form:{

      }
    };
  },
  created() {},
  mounted() {
    
  },
  methods: {
   
  },
};
</script>
<style lang='scss' scoped>
.banxin{
  margin-top: 50px;
  padding: 0 100px;
  box-sizing: border-box;
  color: #8A8A8A;
  padding-bottom: 20px;
}
.tit{
  line-height: 50px;
  border-bottom: 2px solid #ccc;
  position: relative;
  color: black;
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
.box{
  background: white;
  padding: 30px;
  box-sizing: border-box;
  .bot{
    
  }
  .question{
    padding: 20px;
    box-sizing: border-box;
    border: 1px solid #BEBEBE;
    margin-top: 20px;

  }
}
</style>
