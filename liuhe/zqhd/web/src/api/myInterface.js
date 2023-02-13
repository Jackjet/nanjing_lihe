/*
 * @Author: your name
 * @Date: 2020-08-16 16:50:58
 * @LastEditTime: 2020-09-27 11:06:31
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \webadmin\src\api\user.js
 */
import request from '@/utils/request'

// 操作查询
export function findAll(params) {
  return request({
    url: '/zqhd/message/management/findAll',
    method: 'get',
    params
  })
}

// 操作我关注的接口
export function findMyAllApi(params) {
  return request({
    url: '/web/myFocusInfo/findMyAllApi',
    method: 'get',
    params
  })
}





