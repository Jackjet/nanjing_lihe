import request from '@/utils/request'

// 分页查询
export function findAll(params) {
    return request({
        url: '/webadmin/home/settled/enterprise/findAll',
        method: 'get',
        params
    })
}

// 分页查询
export function findAllEnterprise(params) {
    return request({
        url: '/webadmin/enterprise/info/findAll',
        method: 'get',
        params
    })
}


// 详情
export function findById(params) {
    return request({
        url: '/webadmin/home/settled/enterprise/findById',
        method: 'get',
        params
    })
}

// 新增
export function save(data) {
    return request({
        url: '/webadmin/home/settled/enterprise/save',
        method: 'post',
        data
    })
}
// 单项删除
export function deleteById(params) {
    return request({
        url: '/webadmin/home/settled/enterprise/delete',
        method: 'delete',
        params
    })
}