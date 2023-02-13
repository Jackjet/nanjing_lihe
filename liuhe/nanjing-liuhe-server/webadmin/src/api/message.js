
import request from '@/utils/request'
// 列表
export function userMessageFinall(params) {
    return request({
        url: '/webadmin/message/messageUser/findAll',
        method: 'get',
        params
    })
}

// 详情
export function userMessagefind(params) {
    return request({
        url: '/webadmin/message/messageUser/find',
        method: 'get',
        params
    })
}

// 删除
export function userMessageDeleteAll(ids) {
    return request({
        url: '/webadmin/message/messageUser/deleteAll',
        method: 'delete',
        params: { ids }
    })
}

// 已读
export function updateStatus(data) {
    return request({
        url: '/webadmin/message/messageUser/updateAllStatus',
        method: 'put',
        data
    })
}

// 已读全部
export function updateStatusAll(data) {
    return request({
        url: '/webadmin/message/messageUser/updateStatusAll',
        method: 'put',
        data
    })
}

// 删除
export function userMessageDelete(id) {
    return request({
        url: '/webadmin/message/messageUser/delete',
        method: 'delete',
        params: { id }
    })
}
