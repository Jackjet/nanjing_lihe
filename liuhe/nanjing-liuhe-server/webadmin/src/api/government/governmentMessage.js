import request from '@/utils/request'

// 分页查询
export function findAll(params) {
    return request({
        url: '/webadmin/message/management/findAll',
        method: 'get',
        params
    })
}

export function receiveListFindAll(params) {
    return request({
        url: '/webadmin/message/status/findAll',
        method: 'get',
        params
    })
}

export function receiveFindAll(params) {
    return request({
        url: '/webadmin/enterprise/info/findAll',
        method: 'get',
        params
    })
}

// 新增
export function save(data) {
    return request({
        url: '/webadmin/message/management/save',
        method: 'post',
        data
    })
}
