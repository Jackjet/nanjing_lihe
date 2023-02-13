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
        path: '/home',
        component: () => import('@/views/home'),
        hidden: true
    },
    {
        path: '/404',
        component: () => import('@/views/pages/404'),
        hidden: true
    },
    // 系统设置
    {
        path: "/setUp",
        component: () => import('@/views/layout/index'),
        redirect: '/operationLogs',
        children: [
            // 用户管理相关路由
            {
                path: '/user',
                name: '列表',
                label: '用户列表页',
                component: () => import('@/views/pages/system/user/index')
            },
            {
                path: '/userAdd',
                name: '新增',
                label: '用户新增页',
                component: () => import('@/views/pages/system/user/add')
            },
            {
                path: '/userEdit',
                name: '编辑',
                label: '用户编辑页',
                component: () => import('@/views/pages/system/user/add')
            },
            {
                path: '/userDetail',
                name: '详情',
                label: '用户详情页',
                component: () => import('@/views/pages/system/user/add')
            },
            // 组织机构相关路由
            {
                path: '/organization',
                name: '列表',
                label: '组织机构列表页',
                component: () => import('@/views/pages/system/organization/index')
            },
            {
                path: '/organizationAdd',
                name: '新增',
                label: '组织机构新增页',
                component: () => import('@/views/pages/system/organization/add')
            },
            {
                path: '/organizationEdit',
                name: '编辑',
                label: '组织机构编辑页',
                component: () => import('@/views/pages/system/organization/add')
            },
            // 菜单管理相关路由
            {
                path: '/menus',
                name: '列表',
                label: '菜单列表页',
                component: () => import('@/views/pages/system/menus/index')
            },
            {
                path: '/menuAdd',
                name: '新增',
                label: '菜单新增功能页',
                component: () => import('@/views/pages/system/menus/addOrEdit')
            },
            {
                path: '/menuEdit',
                name: '编辑',
                label: '菜单编辑功能页',
                component: () => import('@/views/pages/system/menus/addOrEdit')
            },
            // 角色管理相关路由
            {
                path: '/role',
                name: '列表',
                label: '角色管理列表页',
                component: () => import('@/views/pages/system/role/index')
            },
            {
                path: '/roleAdd',
                name: '新增',
                label: '角色新增功能页',
                component: () => import('@/views/pages/system/role/addOrEdit')
            },
            {
                path: '/roleEdit',
                name: '编辑',
                label: '角色编辑功能页',
                component: () => import('@/views/pages/system/role/addOrEdit')
            },
            {
                path: '/roleUser',
                name: '用户列表',
                label: '角色绑定用户列表页',
                component: () => import('@/views/pages/system/role/users')
            },
            {
                path: '/rolePower',
                name: '权限分配',
                label: '角色权限分配页',
                component: () => import('@/views/pages/system/role/assignPremission')
            },
            // 日志管理相关路由
            {
                path: '/operationLogs',
                name: '列表',
                label: '操作日志列表页',
                component: () => import('@/views/pages/system/operationLogs')
            },
            {
                path: '/operationLogDetail',
                name: '详情',
                label: '操作日志详情页',
                component: () => import('@/views/pages/system/operationLogs/details')
            },
            {
                path: '/userLogs',
                name: '列表',
                label: '用户日志列表页',
                component: () => import('@/views/pages/system/userLogs/index')
            }, {
                path: '/userLogDetail',
                name: '详情',
                label: '用户日志详情页',
                component: () => import('@/views/pages/system/userLogs/details')
            },
            // 消息管理相关路由
            {
                path: '/messages',
                name: '列表',
                label: '消息列表页',
                component: () => import('@/views/pages/system/messages/index')
            }, {
                path: '/messageDetail',
                name: '详情',
                label: '消息详情页',
                component: () => import('@/views/pages/system/messages/details')
            },
            // 系统设置相关路由
            {
                path: '/dataBackup',
                name: '设置',
                label: '数据备份设置页',
                component: () => import('@/views/pages/system/systemSet/dataBackup')
            },
            {
                path: '/service',
                name: '详情',
                label: '服务器信息详情页',
                component: () => import('@/views/pages/system/systemSet/service')
            },
        ]
    },
    // 质量小站管理后台
    {
        path: '/quality',
        component: () => import('@/views/layout/index'),
        redirect: '/carousel',
        children: [
            {
                path: '/carousel',
                name: '列表',
                label: '轮播图管理列表',
                component: () => import('@/views/pages/quality/carousel')
            },
            {
                path: '/carouselDetails',
                name: '详情',
                label: '轮播图详情',
                component: () => import('@/views/pages/quality/carousel/details')
            },
            {
                path: '/serviceProvider',
                name: '列表',
                label: '服务商管理列表',
                component: () => import('@/views/pages/quality/serviceProvider')
            },
            {
                path: '/serviceProviderDetails',
                name: '详情',
                label: '服务商详情',
                component: () => import('@/views/pages/quality/serviceProvider/details')
            },
            {
                path: '/news',
                name: '列表',
                label: '新闻公告管理列表',
                component: () => import('@/views/pages/quality/news')
            },
            {
                path: '/newsDetails',
                name: '详情',
                label: '新闻公告详情',
                component: () => import('@/views/pages/quality/news/details')
            },
            {
                path: '/consult',
                name: '列表',
                label: '咨询管理列表',
                component: () => import('@/views/pages/quality/consult')
            },
            {
                path: '/consultDetails',
                name: '详情',
                label: '咨询详情',
                component: () => import('@/views/pages/quality/consult/details')
            },
            {
                path: '/userList',
                name: '用户管理',
                label: '用户管理列表',
                component: () => import('@/views/pages/quality/user')
            },
            {
                path: '/userListDetails',
                name: '详情',
                label: '用户详情',
                component: () => import('@/views/pages/quality/user/details')
            },
        ]
    },
    // 政企互动
    {
        path: '/government',
        component: () => import('@/views/layout/index'),
        redirect: '/dynamic',
        children: [
            // 动态资讯
            {
                path: '/dynamic',
                name: '列表',
                label: '动态资讯',
                component: () => import('@/views/pages/government/dynamic')
            },
            {
                path: '/dynamicApply',
                name: '列表',
                label: '报名企业',
                component: () => import('@/views/pages/government/dynamic/apply')
            },
            {
                path: '/dynamicApplyDetaile',
                name: '列表',
                label: '报名企业详情',
                component: () => import('@/views/pages/government/dynamic/applyDetails')
            },
            {
                path: '/dynamicDetails',
                name: '详情',
                label: '动态资讯详情',
                component: () => import('@/views/pages/government/dynamic/details')
            },
            // 政策体系
            {
                path: '/policy',
                name: '列表',
                label: '政策体系',
                component: () => import('@/views/pages/government/policy')
            },
            {
                path: '/policyApply',
                name: '列表',
                label: '报名企业',
                component: () => import('@/views/pages/government/policy/apply')
            },
            {
                path: '/policyApplyDetaile',
                name: '列表',
                label: '报名企业详情',
                component: () => import('@/views/pages/government/policy/applyDetails')
            },
            {
                path: '/policyDetails',
                name: '详情',
                label: '政策体系详情',
                component: () => import('@/views/pages/government/policy/details')
            },
            // 轮播图
            {
                path: '/governmentCarousel',
                name: '列表',
                label: '政企互动轮播图',
                component: () => import('@/views/pages/government/carousel')
            },
            {
                path: '/governmentCarouselDetails',
                name: '详情',
                label: '政企互动轮播图详情',
                component: () => import('@/views/pages/government/carousel/details')
            },
            // 重要通知 
            {
                path: '/inform',
                name: '列表',
                label: '重要通知',
                component: () => import('@/views/pages/government/inform')
            },
            {
                path: '/informDetails',
                name: '详情',
                label: '重要通知详情',
                component: () => import('@/views/pages/government/inform/details')
            },
            // 常用链接 
            {
                path: '/link',
                name: '列表',
                label: '常用链接',
                component: () => import('@/views/pages/government/link')
            },
            {
                path: '/linkDetails',
                name: '详情',
                label: '常用链接详情',
                component: () => import('@/views/pages/government/link/details')
            },
            // 常用问题 
            {
                path: '/problem',
                name: '列表',
                label: '常用问题',
                component: () => import('@/views/pages/government/problem')
            },
            {
                path: '/problemDetails',
                name: '详情',
                label: '常用问题详情',
                component: () => import('@/views/pages/government/problem/details')
            },
            // 联系人黄页 
            {
                path: '/contacts',
                name: '列表',
                label: '联系人黄页',
                component: () => import('@/views/pages/government/contacts')
            },
            {
                path: '/contactsDetails',
                name: '详情',
                label: '联系人黄页详情',
                component: () => import('@/views/pages/government/contacts/details')
            },
            // 线上课堂 
            {
                path: '/classroom',
                name: '列表',
                label: '线上课堂',
                component: () => import('@/views/pages/government/classroom')
            },
            {
                path: '/classroomDetails',
                name: '详情',
                label: '线上课堂详情',
                component: () => import('@/views/pages/government/classroom/details')
            },
            // 入驻企业
            {
                path: '/settled',
                name: '列表',
                label: '入驻企业',
                component: () => import('@/views/pages/government/settled')
            },
            {
                path: '/settledDetails',
                name: '详情',
                label: '入驻企业详情',
                component: () => import('@/views/pages/government/settled/details')
            },
            // 工单管理
            {
                path: '/workorder',
                name: '列表',
                label: '工单管理',
                component: () => import('@/views/pages/government/workorder')
            },
            {
                path: '/workorderDetails',
                name: '详情',
                label: '工单管理详情',
                component: () => import('@/views/pages/government/workorder/details')
            },
            // 园区入驻企业
            {
                path: '/parkSettled',
                name: '列表',
                label: '园区入驻企业',
                component: () => import('@/views/pages/government/parkSettled')
            },
            {
                path: '/parkSettledDetail',
                name: '详情',
                label: '园区入驻企业详情',
                component: () => import('@/views/pages/government/parkSettled/details')
            },
            // 企业档案 
            {
                path: '/enterprise',
                name: '列表',
                label: '企业档案',
                component: () => import('@/views/pages/government/enterprise')
            },
            {
                path: '/enterpriseDetails',
                name: '详情',
                label: '企业档案详情',
                component: () => import('@/views/pages/government/enterprise/details')
            },
            // 消息管理 
            {
                path: '/governmentMessage',
                name: '列表',
                label: '政企互动消息管理',
                component: () => import('@/views/pages/government/message')
            },
            {
                path: '/governmentMessageDetails',
                name: '详情',
                label: '政企互动消息管理详情',
                component: () => import('@/views/pages/government/message/details')
            },
            {
                path: '/messageReceive',
                name: '详情',
                label: '接收方管理',
                component: () => import('@/views/pages/government/message/receive')
            },
            {
                path: '/messageReceiveList',
                name: '详情',
                label: '查看接收方',
                component: () => import('@/views/pages/government/message/receiveList')
            },
        ]
    },
]

const router = new VueRouter({
    routes
})
export default router
