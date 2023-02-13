/*
 * @Author: your name
 * @Date: 2020-08-12 16:07:56
 * @LastEditTime: 2020-10-12 00:38:16
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \webadmin\src\permission.js
 */

import router from './router'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style

NProgress.configure({ showSpinner: false }) // NProgress Configuration
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (to.path !== '/login') {
    if (sessionStorage.getItem("UserInfo")) { // 判断当前的token是否存在 ； 登录存入的token
      next();
    } else {
      next({path: '/login'})
    }
  } else {
    next();
  }
});
router.afterEach(() => {
  NProgress.done()
})
