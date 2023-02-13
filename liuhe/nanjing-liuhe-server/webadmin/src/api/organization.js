
import request from '@/utils/request'

// 查询
export function organizationFindAll(params) {
    return request({
        url: '/webadmin/system/organization/findAll',
        method: 'get',
        params
    })
}

// 插入
export function organizationInsert(data) {
    return request({
        url: '/webadmin/system/organization/insert',
        method: 'post',
        data
    })
}

// 更新
export function organizationUpdate(data) {
    return request({
        url: '/webadmin/system/organization/update',
        method: 'put',
        data
    })
}

// 更新
export function organizationUpdateSeq(data) {
    return request({
        url: '/webadmin/system/organization/updateSeq',
        method: 'put',
        data
    })
}

//删除
export function organizationDelete(id) {
    return request({
        url: '/webadmin/system/organization/delete',
        method: 'delete',
        params: { id }
    })
}