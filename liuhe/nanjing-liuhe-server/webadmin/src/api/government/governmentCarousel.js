import request from '@/utils/request'

// 分页查询
export function findAll(params) {
    return request({
        url: '/webadmin/rotation/chart/findAll',
        method: 'get',
        params
    })
}

// 新增
export function save(data) {
    return request({
        url: '/webadmin/rotation/chart/save',
        method: 'post',
        data
    })
}

// 编辑
export function update(data) {
    return request({
        url: '/webadmin/rotation/chart/update',
        method: 'put',
        data
    })
}

// 详情
export function findById(params) {
    return request({
        url: '/webadmin/rotation/chart/findById',
        method: 'get',
        params
    })
}

// 更新状态
export function release(data) {
    return request({
        url: '/webadmin/rotation/chart/release',
        method: 'put',
        data
    })
}


// 单项删除
export function deleteById(params) {
    return request({
        url: '/webadmin/rotation/chart/delete',
        method: 'delete',
        params
    })
}

// 批量删除
export function deleteBatch(params) {
    return request({
        url: '/webadmin/rotation/chart/deleteBatch',
        method: 'delete',
        params
    })
}