import request from '@/utils/request'

// 分页查询
export function infoFindAll(params) {
    return request({
        url: '/webadmin/user/info/findAll',
        method: 'get',
        params
    })
}

// 详情
export function infoFind(params) {
    return request({
        url: '/webadmin/user/info/find',
        method: 'get',
        params
    })
}

// 启用禁用
export function infoUpdateEnable(data) {
    return request({
        url: '/webadmin/user/info/updateEnable',
        method: 'put',
        data
    })
}

//多项 启用禁用
export function infoUpdateAllEnable(data) {
    return request({
        url: '/webadmin/user/info/updateAllEnable',
        method: 'put',
        data
    })
}