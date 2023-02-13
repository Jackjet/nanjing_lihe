import request from '@/utils/request'

// 分页查询
export function rotationChartFindAll(params) {
    return request({
        url: '/webadmin/home/rotationChart/findAll',
        method: 'get',
        params
    })
}

// 新增
export function rotationChartSave(data) {
    return request({
        url: '/webadmin/home/rotationChart/save',
        method: 'post',
        data
    })
}

// 编辑
export function rotationChartUpdate(data) {
    return request({
        url: '/webadmin/home/rotationChart/update',
        method: 'put',
        data
    })
}

// 详情
export function rotationChartFindById(params) {
    return request({
        url: '/webadmin/home/rotationChart/findById',
        method: 'get',
        params
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