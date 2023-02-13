
import request from '@/utils/request'

// 操作查询
export function operationLogFindAll(params) {
    return request({
        url: '/webadmin/log/operationLog/findAll',
        method: 'get',
        params
    })
}

// 详情
export function operationLogFind(params) {
    return request({
        url: '/webadmin/log/operationLog/find',
        method: 'get',
        params
    })
}

// 导出
export function operationLogExport(params) {
    return request({
        url: '/webadmin/log/operationLog/export',
        method: 'get',
        params
    })
}

// 用户查询
export function userLogFindAll(params) {
    return request({
        url: '/webadmin/log/userLog/findAll',
        method: 'get',
        params
    })
}

// 详情
export function userLogFind(params) {
    return request({
        url: '/webadmin/log/userLog/find',
        method: 'get',
        params
    })
}

// 导出
export function userLogExport(params) {
    return request({
        url: '/webadmin/log/userLog/export',
        method: 'get',
        params
    })
}