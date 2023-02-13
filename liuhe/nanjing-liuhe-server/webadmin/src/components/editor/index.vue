<template>
  <div id="editor" ref="editor"></div>
</template>
<script>
import Editor from 'wangeditor';
export default {
  model: {
    prop: 'desc',
    event: 'change'
  },
  props: {
    desc: {
      type: String,
      default: ""
    },
    isClear: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      editor: null,
      content: "",
      serveUrl: process.env.VUE_APP_BASE_API
    }
  },
  watch: {
    //判断用户是否清除编辑器缓存
    isClear(val) {
      if (val) {
        this.editor.txt.clear()
      }
    },
    //接收父组件传递过来的值
    desc(value) {
      //判断父组件传递过来的值跟当前编辑器内容是否一样
      if (value != this.editor.txt.html()) {
        this.editor.txt.html(this.desc)
      }
    }
  },
  mounted() {
    this.wangeditorFun();
  },
  methods: {
    wangeditorFun() {
      let that = this;
      // 创建示例
      this.editor = new Editor(this.$refs.editor);
      // 配置菜单栏，设置不需要的菜单
      this.editor.config.excludeMenus = [
        'video'
      ]
      // 配置 server 接口地址
      this.editor.config.uploadImgServer = this.serveUrl + '/public/upload/image';
      // 隐藏插入网络图片
      this.editor.config.showLinkImg = false;
      // formData 上传属性
      this.editor.config.uploadFileName = 'file';
      // 设置请求头
      this.editor.config.uploadImgHeaders = {
        Authorization: "token " + JSON.parse(sessionStorage.getItem('UserInfo')).token
      }
      // 图片上传
      this.editor.config.uploadImgHooks = {
        success: function (xhr, editor, result) {
          console.log(result)
        },
        fail: function (xhr, editor, result) {
          this.$message.error("插入图片失败");
        },
        error: function (xhr, editor) {
          this.$message.error("插入图片失败");
        },
        customInsert: function (insertImg, result, editor) {
          let url = that.serveUrl + result.fileDownloadUri;
          insertImg(url);
        }
      }
      //监听取值
      this.editor.config.onchange = (html) => {
        this.$emit('change', html)
      }
      //创建富文本
      this.editor.create()
    },
  }
}
</script>