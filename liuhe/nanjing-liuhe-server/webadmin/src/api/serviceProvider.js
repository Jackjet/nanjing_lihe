import request from '@/utils/request'

// 分页查询
export function providerFindAll(params) {
    return request({
        url: '/webadmin/service/provider/findAll',
        method: 'get',
        params
    })
}

// 新增
export function providerSave(data) {
    return request({
        url: '/webadmin/service/provider/save',
        method: 'post',
        data
    })
}

// 详情
export function providerFindById(params) {
    return request({
        url: '/webadmin/service/provider/findById',
        method: 'get',
        params
    })
}

// 编辑
export function providerUpdate(data) {
    return request({
        url: '/webadmin/service/provider/update',
        method: 'put',
        data
    })
}

// 单项删除
export function providerDelete(params) {
    return request({
        url: '/webadmin/service/provider/delete',
        method: 'delete',
        params
    })
}

// 批量删除
export function providerDeleteBatch(params) {
    return request({
        url: '/webadmin/service/provider/deleteBatch',
        method: 'delete',
        params
    })
}

// 是否启用
export function providerRelease(data) {
    return request({
        url: '/webadmin/service/provider/release',
        method: 'put',
        data
    })
}