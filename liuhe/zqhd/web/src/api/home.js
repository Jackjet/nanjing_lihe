import request from '@/utils/request'


export function findAllPolicy(params) {
  return request({
    url: '/zqhd/policy/findAll',
    method: 'get',
    params
  })
}

export function findAllActivity(params) {
  return request({
    url: '/zqhd/activity/findAll',
    method: 'get',
    params
  })
}

export function findAllChart(params) {
  return request({
    url: '/zqhd/home/rotation/chart/findAll',
    method: 'get',
    params
  })
}

export function findAllNotice(params) {
  return request({
    url: '/zqhd/home/important/notice/findAll',
    method: 'get',
    params
  })
}

export function findAllUsedurl(params) {
  return request({
    url: '/zqhd/home/commonly/usedurl/findAll',
    method: 'get',
    params
  })
}

export function findAllEnterprise(params) {
  return request({
    url: '/zqhd/home/settled/enterprise/findAll',
    method: 'get',
    params
  })
}
