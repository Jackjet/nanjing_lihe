import request from '@/utils/request'
/* 动态资讯 */
// 分页查询
export function findAll(params) {
    return request({
        url: '/webadmin/activity/enlist/findAll',
        method: 'get',
        params
    })
}

// 详情
export function findById(params) {
    return request({
        url: '/webadmin/activity/enlist/findById',
        method: 'get',
        params
    })
}

// 回复
export function update(data) {
    return request({
        url: '/webadmin/activity/enlist/update',
        method: 'put',
        data
    })
}

/* 政策体系 */
// 分页查询
export function policyFindAll(params) {
    return request({
        url: '/webadmin/policy/enlist/findAll',
        method: 'get',
        params
    })
}

// 详情
export function policyFindById(params) {
    return request({
        url: '/webadmin/policy/enlist/findById',
        method: 'get',
        params
    })
}

// 回复
export function policyUpdate(data) {
    return request({
        url: '/webadmin/policy/enlist/update',
        method: 'put',
        data
    })
}
