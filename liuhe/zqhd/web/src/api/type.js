import request from '@/utils/request'



export function findByType(params) {
  return request({
    url: '/public/dictionary/findByType',
    method: 'get',
    params
  })
}
