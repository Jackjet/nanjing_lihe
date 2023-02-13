import request from '@/utils/request'



export function findAll(params) {
  return request({
    url: '/zqhd/activity/findAll',
    method: 'get',
    params
  })
}

// 详情
export function findById(params) {
  return request({
      url: '/zqhd/home/important/notice/findById',
      method: 'get',
      params
  })
}
