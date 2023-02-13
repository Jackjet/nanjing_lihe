import request from '@/utils/request'

// 分页查询
export function consultFindAll(params) {
    return request({
        url: '/webadmin/consult/findAll',
        method: 'get',
        params
    })
}

// 详情
export function consultFindById(params) {
    return request({
        url: '/webadmin/consult/findById',
        method: 'get',
        params
    })
}

// 回复消息
export function consultReplyMessage(data) {
    return request({
        url: '/webadmin/consult/replyMessage',
        method: 'put',
        data
    })
}

// 单项删除
export function rotationChartDelete(params) {
    return request({
        url: '/webadmin/home/rotationChart/delete',
        method: 'delete',
        params
    })
}

// 批量删除
export function rotationChartDeleteBatch(params) {
    return request({
        url: '/webadmin/home/rotationChart/deleteBatch',
        method: 'delete',
        params
    })
}