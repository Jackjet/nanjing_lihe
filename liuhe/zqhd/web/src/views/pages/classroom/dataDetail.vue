<!-- 数据资源 -->
<template>
  <div class="banxin data-resources">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb-box">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/classroom' }">线上课堂</el-breadcrumb-item>
      <el-breadcrumb-item>教程视频，{{ruleForm.title}}
      </el-breadcrumb-item>
    </el-breadcrumb>
    <div class="box">
      <div class="tit">
        线上课堂
        <span class="line"></span>
      </div>
      <div class="video"  @pointermove.prevent="handleMouseMove($event)"
         @pointerup.prevent="stopDragging"
         @pointerleave="handleMouseLeave"
         @pointerenter="handleMouseEnter"  ref="vcontainer">
        <video class="video__player" ref="v" @timeupdate="handleTimeUpdate" @ended="handleEnd">
            <source :src="videoSrc"/>
        </video>
        <div class="controller">
            <div class="controller__progress-wrapper">
                <div class="controller__progress" ref="p" @click="handleProgressClick($event)">
                    <div class="controller__progress controller__progress--passed"
                         :style="{width: videoProgressPercent}"></div>
                    <div class="controller__dot"
                         :style="{left: videoProgressPercent}"
                         @pointerdown="startDragging($event)">
                        <div class="controller__inner-dot"></div>
                    </div>
                </div>
            </div>
            <div class="controller__btn-wrapper">
                <div class="controller__btn" @click="togglePlaying">
                    <i class="el-icon-video-play" v-if="isPaused"></i>
                    <i class="el-icon-video-pause" v-else></i>
                </div>
                <!-- <div class="controller__btn" @click="stopPlaying">
                    <i class="icon iconfont icon-icon--"></i>
                </div> -->
                <div class="controller__btn" @click="toggleMute">
                    <i class="icon iconfont icon-shengyinjingyin" v-if="isMuted"></i>
                    <i class="icon iconfont icon-shengyin" v-else></i>
                </div>
                <div class="controller__timer">
                    {{videoTime}}
                </div>
                <div class="controller__btn controller__btn--fullscreen" @click="toggleFullscreen">
                    <i class="icon iconfont icon-full-screen"></i>
                </div>
            </div>
        </div>
      </div>
      <div class="title">
          {{ruleForm.title}}
      </div>
      <p class="title2">{{ruleForm.releaseTime}}</p>
      <div class="contentDetaile">
        <p> （一）贯彻落实民政工作的法律法规，拟订全区民政事业发展规划、制定年度工作计划并组织实施。</p>
　　   
      </div>
    </div>
  </div>
</template>

<script>
function secToTimer(originalSec) {
    const min = Math.floor(originalSec / 60);
    const sec = Math.floor(originalSec % 60);
    const minStr = min < 10 ? `0${min}` : String(min);
    const secStr = sec < 10 ? `0${sec}` : String(sec);
    return `${minStr}:${secStr}`;
}
import { findById } from '@/api/classroom'
export default {
  data() {
    return {
      id:this.$route.query.id,
      ruleForm:{},
      video: null,
      isPaused: true,
      isMuted: false,
      videoTime: '00:00 / 00:00',
      isDragging: false,
      isControlVisible: false,
      hidingEvent: null,
      videoProgress: 0,
      draggingStartX: 0,
      dotOffsetX: 0,
      progress: null,
    };
  },
  created () {
  },
  computed: {
      videoProgressPercent() {
          return `${this.videoProgress * 100}%`;
      },
  },
  mounted () {
    this.videoSrc = this.$route.query.url
    this.video = this.$refs.v;
    this.progress = this.$refs.p;
    this.playVideo()  
    this.dateilsDataFun()
  },
  methods: {
    dateilsDataFun() {
      findById({ id: this.$route.query.id }).then(res => {
        if (res.data.code === 1) {
          this.ruleForm = res.data.data;
          let dom = document.getElementsByClassName('contentDetaile')[0]
          dom.innerHTML = this.ruleForm.content
            
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    toggleFullscreen() {
        const isFullscreen = document.webkitIsFullScreen || document.fullscreen;
        if (isFullscreen) {
            const exitFunc = document.exitFullscreen || document.webkitExitFullscreen;
            exitFunc.call(document);
        } else {
            const element = this.$refs.vcontainer;
            const fullscreenFunc = element.requestFullscreen || element.webkitRequestFullScreen;
            fullscreenFunc.call(element);
        }
    },
    handleTimeUpdate() {
        this.videoTime = this.refreshTime();
        this.videoProgress = this.video.currentTime / this.video.duration;
    },
    refreshTime() {
        if (!this.video) {
            return `${secToTimer(0)} / ${secToTimer(0)}`;
        }
        const currTime = this.video.currentTime || 0;
        const duration = this.video.duration || 0;
        return `${secToTimer(currTime)} / ${secToTimer(duration)}`;
    },
    togglePlaying() {
        if (this.video.paused) {
            this.playVideo();
        } else {
            this.pauseVideo();
        }
    },
    stopPlaying() {
        this.video.currentTime = 0;
        this.pauseVideo();
    },
    toggleMute() {
        this.video.muted = !this.video.muted;
        this.isMuted = this.video.muted;
    },
    handleEnd() {
        this.pauseVideo();
    },
    playVideo() {
        this.isPaused = false;
        this.video.play();
    },
    pauseVideo() {
        this.isPaused = true;
        this.video.pause();
    },
    setProgress(x) {
        const progressRect = this.progress.getBoundingClientRect();
        let progressPercent = (x - progressRect.left) / progressRect.width;
        if (progressPercent < 0) {
            progressPercent = 0;
        } else if (progressPercent > 1) {
            progressPercent = 1;
        }
        this.video.currentTime = this.video.duration * progressPercent;
    },
    hideControlBar() {
        const isFullscreen = document.webkitIsFullScreen || document.fullscreen;
        console.log(isFullscreen,123)
        if (isFullscreen) {
            this.hideCursor();
        }
        this.isControlVisible = false;
    },
    showControlBar() {
        this.isControlVisible = true;
    },
    hideCursor() {
        document.body.style.cursor = 'none';
    },
    showCursor() {
        document.body.style.cursor = 'default';
    },
    handleProgressClick(event) {
        const clickX = event.clientX;
        this.setProgress(clickX);
    },
    startDragging(event) {
        this.pauseVideo();
        this.isDragging = true;
        this.draggingStartX = event.clientX;
        this.playVideo();

    },
    moveDragging(event) {
        if (this.isDragging) {
            const offsetX = event.clientX - this.draggingStartX;
            this.dotOffsetX = offsetX < 0 ? 0 : offsetX;
            this.setProgress(event.clientX);
        }
    },
    stopDragging() {
        this.isDragging = false;
        this.dotOffsetX = 0;
    },
    handleMouseMove(event) {
        this.showControlBar();
        this.showCursor();
        if (this.hidingEvent !== null) {
            clearInterval(this.hidingEvent);
        }
        this.hidingEvent = setInterval(this.hideControlBar, 3000);
        this.moveDragging(event);
    },
    handleMouseLeave() {
        this.hideControlBar();
        this.stopDragging();
    },
    handleMouseEnter() {
        this.showControlBar();
    },
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
}

.title{
    padding-top: 50px;
    padding-bottom: 20px;
    font-size: 24px;
    font-weight: 900;
    margin: 0 auto;
    color: black;
    width: 350px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.title2{
    padding-bottom: 20px;
    font-size: 14px;
    margin: 0 auto;
    color: #878787;
    text-align: center;
}
.contentDetaile{
    p{
        text-indent: 24px;
        margin-top: 20px;
    }
}

.video {
        position: relative;
        width: 80%;
        margin-left: 10%;
        margin-top: 20px;
    }

.video__player {
    width: 100%;
    height: 100%;
    display: flex;
}

.controller {
    flex-direction: column;
    height: 50px;
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.5);
}

.controller__btn-wrapper {
    position: relative;
    height: calc(100% - 5px);
    display: flex;
    align-items: center;
    color: #fff;
    padding: 0 18px;
}

.controller__btn {
    cursor: pointer;
    transition: 0.5s;
    margin: 0 20px;
    i{
      font-size: 24px;
      color: white;
    }
}

.controller__btn:hover {
  i{
    color: #409eff;
  }
}

.controller__timer {
    margin-left: 15px;
}

.controller__btn--fullscreen {
    position: absolute;
    right: 15px;
}
.controller__progress-wrapper {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.controller__progress {
    height: 5px;
    position: relative;
    width: calc(100% - 30px);
    border-radius: 100px;
    background: #dcdcdc;
    cursor: pointer;
}

.controller__progress--passed {
    position: absolute;
    top: 0;
    left: 0;
    background: #409EFF;
}

.controller__dot {
    position: absolute;
    z-index: 50;
    left: 0;
    top: -5px;
    width: 15px;
    height: 15px;
    border-radius: 50%;
    background-color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
}

.controller__inner-dot {
    width: 5px;
    height: 5px;
    border-radius: 50%;
    background-color: #409EFF;
}
</style>