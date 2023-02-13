import request from '@/utils/request'


export function findAll(params) {
  return request({
    url: '/zqhd/home/content/search',
    method: 'get',
    params
  })
}


export function findByIdClass(params) {
  return request({
    url: '/zqhd/online/classroom/findById',
    method: 'get',
    params
  })
}

