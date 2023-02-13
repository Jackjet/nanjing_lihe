import request from '@/utils/request'

// 分页查询
export function findAll(params) {
    return request({
        url: '/webadmin/work/order/findAll',
        method: 'get',
        params
    })
}

// 编辑
export function reply(data) {
    return request({
        url: '/webadmin/work/order/reply',
        method: 'put',
        data
    })
}

// 详情
export function findById(params) {
    return request({
        url: '/webadmin/work/order/findById',
        method: 'get',
        params
    })
}
