import request from '@/utils/request'



export function findAll(params) {
  return request({
    url: '/zqhd/online/classroom/findAll',
    method: 'get',
    params
  })
}

// 详情
export function findById(params) {
  return request({
      url: '/zqhd/online/classroom/findById',
      method: 'get',
      params
  })
}
