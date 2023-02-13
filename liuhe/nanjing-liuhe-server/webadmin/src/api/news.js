import request from '@/utils/request'

// 分页查询
export function announcementFindAll(params) {
    return request({
        url: '/webadmin/news/announcement/findAll',
        method: 'get',
        params
    })
}

// 新增
export function announcementSave(data) {
    return request({
        url: '/webadmin/news/announcement/save',
        method: 'post',
        data
    })
}

// 详情
export function announcementFindById(params) {
    return request({
        url: '/webadmin/news/announcement/findById',
        method: 'get',
        params
    })
}

// 编辑
export function announcementUpdate(data) {
    return request({
        url: '/webadmin/news/announcement/update',
        method: 'put',
        data
    })
}

// 单项删除
export function announcementDelete(params) {
    return request({
        url: '/webadmin/news/announcement/delete',
        method: 'delete',
        params
    })
}

// 批量删除
export function announcementDeleteBatch(params) {
    return request({
        url: '/webadmin/news/announcement/deleteBatch',
        method: 'delete',
        params
    })
}

// 是否启用
export function announcementRelease(data) {
    return request({
        url: '/webadmin/news/announcement/release',
        method: 'put',
        data
    })
}