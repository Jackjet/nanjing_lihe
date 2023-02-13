/*
 * @Author: your name
 * @Date: 2020-08-16 16:50:58
 * @LastEditTime: 2020-09-18 00:43:59
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \webadmin\src\api\user.js
 */
import request from '@/utils/request'



// 请求验证码图片
export function askCode(params) {
  return request({
    url: '/public/verifyCode',
    method: 'get',
    params
  })
}

// 请求验证码
export function sendSms(data) {
  return request({
    url: '/public/sendSms',
    method: 'post',
    data
  })
}

// 注册
export function register(data) {
  return request({
    url: '/web/user/register',
    method: 'post',
    data
  })
}

// 登录
export function login(params) {
  return request({
    url: '/zqhd/user/signIn',
    method: 'get',
    params
  })
}

// 短信登录
export function smsSignIn(params) {
  return request({
    url: '/zqhd/user/smsSignIn',
    method: 'get',
    params
  })
}


// 修改密码
export function resetPwd(data) {
  return request({
    url: '/web/user/updatePwd',
    method: 'put',
    data
  })
}

// 获取用户基本信息
export function userInfo(params) {
  return request({
    url: '/zqhd/user/findUserByToken',
    method: 'get',
    params
  })
}

// 验证账号密码
export function isTheUser(params) {
  return request({
    url: '/web/user/verificationAccountAndPassword',
    method: 'get',
    params
  })
}
// 修改手机号
export function resetPhone(data) {
  return request({
    url: '/web/user/updatePhone',
    method: 'put',
    data
  })
}
// 验证手机号是否存在
export function isPhone(data) {
  return request({
    url: '/web/user/retrievePassword',
    method: 'post',
    data
  })
}
// 根据手机号重置密码
export function resetPwdByPhone(data) {
  return request({
    url: '/web/user/resetPassword',
    method: 'post',
    data
  })
}

