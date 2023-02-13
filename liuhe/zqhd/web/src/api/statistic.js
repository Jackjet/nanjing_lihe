/********** 数据统计页 ***************/
import request from '@/utils/request'

export function areaDataTop10(params) {
  return request({
    url: '/web/dataStatistics/areaDataTop10',
    method: 'get',
    params
  })
}

export function themeData(params) {
  return request({
    url: '/web/dataStatistics/themeData',
    method: 'get',
    params
  })
}

export function registerTypeAnalyse(params) {
  return request({
    url: '/web/dataStatistics/registerTypeAnalyse',
    method: 'get',
    params
  })
}

export function totalAll(params) {
    return request({
        url: '/web/dataStatistics/totalAll',
        method: 'get',
        params
    })
}

export function deptDataAll(params) {
    return request({
        url: '/web/dataStatistics/deptDataAll',
        method: 'get',
        params
    })
}

export function deptDataTop10(params) {
    return request({
        url: '/web/dataStatistics/deptDataTop10',
        method: 'get',
        params
    })
}