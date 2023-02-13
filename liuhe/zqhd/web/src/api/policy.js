import request from '@/utils/request'



export function findAll(params) {
  return request({
    url: '/zqhd/policy/findAll',
    method: 'get',
    params
  })
}

// 获取企业信息
export function findUserByToken(params) {
  return request({
      url: '/zqhd/user/findUserByToken',
      method: 'get',
      params
  })
}

// 新增
export function save(data) {
  return request({
    url: '/zqhd/policy/enlist/save', 
    method: 'post',
    data
  })
}


// 详情
export function findById(params) {
  return request({
      url: '/zqhd/policy/findById',
      method: 'get',
      params
  })
}

