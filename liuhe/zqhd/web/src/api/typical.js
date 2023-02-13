/*
 * @Author: your name
 * @Date: 2020-08-16 16:50:58
 * @LastEditTime: 2020-09-27 11:06:31
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \web\src\api\user.js
 */
import request from '@/utils/request'
import qs from 'qs'
// 列表查询
export function findAll(params) {
  return request({
    url: '/web/typicalapplication/findAll',
    method: 'get',
    params: params,
    paramsSerializer: params => qs.stringify(params)
  })
}

// 添加
export function addTypical(params) {
  return request({
    url: '/web/typicalapplication/insert',
    method: 'post',
    data:params
  })
}

// 修改
export function update(params) {
  return request({
    url: '/web/typicalapplication/update',
    method: 'post',
    data:params
  })
}

// 详情
export function findById(params) {
  return request({
    url: '/web/typicalapplication/find',
    method: 'get',
    params:params
  })
}

// 成果类型
export function getTypicalAppCount(params) {
  return request({
    url: '/web/typicalapplication/getTypicalAppCount',
    method: 'get',
    params:params
  })
}
// 资源列表查询
export function findAllOpenInfo(params) {
  return request({
    url: '/web/archopeninfo/findDataByOrder',
    method: 'get',
    params: params
  })
}
// 部门列表查询
export function findAllOrg(params) {
  return request({
    url: '/web/system/organization/findByParentId',
    method: 'get',
    params: params
  })
}