import Vue from 'vue'
import VueRouter from 'vue-router'
import bus from '@/utils/bus'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/changePwd',
    component: () => import('@/views/login/changePwd'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/pages/404'),
    hidden: true
  },
  {
    path: '/',
    component: () => import('@/views/layout/index'),
    redirect: '/homePage',
    children: [{
      path: '/register',
      name: '注册',
      label: '用户注册页',
      component: () => import('@/views/login/register')
    }, {
      path: '/homePage',
      name: '首页',
      label: '默认主页',
      component: () => import('@/views/home/index')
    },
    {
      path: '/park',
      name: '园区介绍',
      label: '园区介绍',
      component: () => import('@/views/pages/park/index')
    },{
      path: '/searchList',
      name: '搜索列表',
      label: '搜索列表',
      component: () => import('@/views/pages/searchList/index')
    },{
      path: '/dynamic',
      name: '动态资讯',
      label: '动态资讯',
      component: () => import('@/views/pages/dynamic/index')
    }, {
      path: '/dynamicDetaile',
      name: '动态资讯详情页',
      label: '动态资讯详情页',
      component: () => import('@/views/pages/dynamic/detaile')
    }, {
      path: '/policy',
      name: '政策体系',
      label: '政策体系',
      component: () => import('@/views/pages/policy/index')
    }, {
      path: '/policyDetaile',
      name: '政策体系详情页',
      label: '政策体系详情页',
      component: () => import('@/views/pages/policy/detaile')
    },  
    , {
      path: '/importantDetaile',
      name: '重要通知详情页',
      label: '重要通知详情页',
      component: () => import('@/views/pages/important/detaile')
    },  
    // 线上课堂
    {
      path: '/classroom',
      name: '线上课堂',
      label: '线上课堂',
      component: () => import('@/views/pages/classroom/index')
    },{
      path: '/classroomDetail',
      name: '线上课堂详情',
      label: '线上课堂详情',
      component: () => import('@/views/pages/classroom/dataDetail')
    }, {
      path: '/service',
      name: '日常服务',
      label: '日常服务',
      component: () => import('@/views/pages/service/index')
    },{
      path: '/serviceDetail',
      name: '日常服务详情',
      label: '日常服务详情',
      component: () => import('@/views/pages/service/detail')
    }, {
      path: '/message',
      name: '留言板',
      label: '留言板',
      component: () => import('@/views/pages/message/index')
    } ,{
      path: '/theMy',
      name: '个人中心',
      label: '个人中心主页',
      component: () => import('@/views/pages/personal/index'),
      redirect: '/theMy/myInfo',
      children: [ {
        path: 'myInfo',
        name: '基本信息',
        component: () => import('@/views/pages/personal/myInfo/index'),
        label: '个人中心基本信息页',
      }, {
        path: 'myInterface',
        name: '我的接口',
        component: () => import('@/views/pages/personal/myInterface/index'),
        label: '个人中心我的接口页',
      },{
        path: 'myData',
        name: '我的数据',
        component: () => import('@/views/pages/personal/myData/index'),
        label: '个人中心我的数据页',
      }, {
        path: 'myNeeds',
        name: '我的需求',
        component: () => import('@/views/pages/personal/myNeeds/index'),
        label: '个人中心我的需求页',
      } ,{
        path: 'myNeedsDetaile',
        name: '我的接口详情页',
        component: () => import('@/views/pages/personal/myNeeds/detaile'),
        label: '个人中心我的接口详情页',
      }, ]
    }, {
      path: 'applyAgain',
      name: '再次申请',
      component: () => import('@/views/pages/personal/myInterface/applyAgain'),
      label: '我的接口再次申请',
    }, {
      path: 'about',
      name: '关于我们',
      label: '底部相关关于我们页面',
      component: () => import('@/views/pages/footPage/about'),
    }, {
      path: 'agreement',
      name: '开源协议',
      label: '底部相关开源协议页面',
      component: () => import('@/views/pages/footPage/agreement'),
    }, {
      path: 'clause',
      name: '服务条款',
      label: '底部相关服务条款页面',
      component: () => import('@/views/pages/footPage/clause'),
    }, {
      path: 'feedback',
      name: '用户反馈',
      label: '底部相关用户反馈页面',
      component: () => import('@/views/pages/footPage/feedback'),
    }, {
      path: 'statement',
      name: '法律声明',
      label: '底部相关法律声明页面',
      component: () => import('@/views/pages/footPage/statement'),
    }]
  }
]

const router = new VueRouter({
  routes
})
export default router
