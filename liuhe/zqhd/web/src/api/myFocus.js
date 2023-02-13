/*
 * @Author: your name
 * @Date: 2020-08-16 16:50:58
 * @LastEditTime: 2020-09-27 11:06:31
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \web\src\api\user.js
 */
import request from '@/utils/request'
// 关注
export function focus(params) {
  return request({
    url: '/web/myFocusInfo/insert',
    method: 'post',
    data: params  
  })
}
export function unFocus(params) {
  return request({
    url: '/web/myFocusInfo/delete',
    method: 'post',
    data: params
  })
}