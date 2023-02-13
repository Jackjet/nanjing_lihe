/**
 * 常量
 */
export const appTypeArr = {
                            "0": "服务应用",
                            "1": "创新方案",
                            "2": "研究成果",
                            "3": "传播产品"
                          }

export const appShapeArr =  {
                            "0": "WEB",
                            "1": "移动应用",
                            "2": "地图",
                            "3": "文章",
                            "4": "报道",
                            "5": "其他"
                          }
                          
export const statusArr = {
                            "0": "未审核",
                            "1": "审核通过",
                            "2": "审核驳回"
                          }
export const statusErrorArr = {
                            "1": "待处理",
                            "2": "已纠错",
                            "3": "无需纠错"
                          }
export const dataDomainArr = {
                            "0": "经济建设",
                            "1": "资源环境",
                            "2": "教育科技",
                            "3": "道路交通",
                            "4": "社会发展",
                            "5": "公共安全",
                            "6": "文化休闲",
                            "7": "卫生健康",
                            "8": "民生服务",
                            "9": "机构团体",
                            "10": "城市建设",
                            "11": "信用服务"
                          }
 export const resTypeArr = {
                            "0": "数据",
                            "1": "接口"
                          }  
export const updateRateArr = {
                            "0" :"实时",
                            "1" :"每周",
                            "2" :"每半年",
                            "3" :"每年",
                            "4" :"每天",
                            "5" :"每月",
                            "6" :"不定时更新",
                            "7" :"静态数据"
                          }    
 export const subjectArr = [
  {
      "label": "综合政务",
      "value": "综合政务",
      "children": [
          {
              "label": "综合政务类",
              "value": "综合政务类"
          },
          {
              "label": "方针政策",
              "value": "方针政策"
          },
          {
              "label": "中共党务",
              "value": "中共党务"
          },
          {
              "label": "综合政务",
              "value": "综合政务"
          },
          {
              "label": "人大",
              "value": "人大"
          },
          {
              "label": "政协",
              "value": "政协"
          },
          {
              "label": "法院",
              "value": "法院"
          },
          {
              "label": "检察院",
              "value": "检察院"
          },
          {
              "label": "机构编制",
              "value": "机构编制"
          },
          {
              "label": "领导人",
              "value": "领导人"
          },
          {
              "label": "会议、会务",
              "value": "会议、会务"
          },
          {
              "label": "重大事件",
              "value": "重大事件"
          }
      ]
  },
  {
      "label": "经济管理",
      "value": "经济管理",
      "children": [
          {
              "label": "经济管理综合类",
              "value": "经济管理综合类"
          },
          {
              "label": "经济发展计划",
              "value": "经济发展计划"
          },
          {
              "label": "经济管理",
              "value": "经济管理"
          },
          {
              "label": "经济体制改革",
              "value": "经济体制改革"
          },
          {
              "label": "经贸管理",
              "value": "经贸管理"
          },
          {
              "label": "统计",
              "value": "统计"
          },
          {
              "label": "物价",
              "value": "物价"
          },
          {
              "label": "工商",
              "value": "工商"
          }
      ]
  },
  {
      "label": "国土资源、能源",
      "value": "国土资源、能源",
      "children": [
          {
              "label": "国土资源与能源综合类",
              "value": "国土资源与能源综合类"
          },
          {
              "label": "土地",
              "value": "土地"
          },
          {
              "label": "矿藏",
              "value": "矿藏"
          },
          {
              "label": "水资源",
              "value": "水资源"
          },
          {
              "label": "海洋",
              "value": "海洋"
          },
          {
              "label": "煤炭",
              "value": "煤炭"
          },
          {
              "label": "石油",
              "value": "石油"
          },
          {
              "label": "燃料、燃气",
              "value": "燃料、燃气"
          },
          {
              "label": "电力",
              "value": "电力"
          }
      ]
  },
  {
      "label": "工业、交通",
      "value": "工业、交通",
      "children": [
          {
              "label": "工交综合类",
              "value": "工交综合类"
          },
          {
              "label": "工业",
              "value": "工业"
          },
          {
              "label": "企业",
              "value": "企业"
          },
          {
              "label": "交通运输",
              "value": "交通运输"
          }
      ]
  },
  {
      "label": "信息产业",
      "value": "信息产业",
      "children": [
          {
              "label": "信息产业综合类",
              "value": "信息产业综合类"
          },
          {
              "label": "通信",
              "value": "通信"
          },
          {
              "label": "计算机",
              "value": "计算机"
          },
          {
              "label": "软件",
              "value": "软件"
          },
          {
              "label": "网络",
              "value": "网络"
          },
          {
              "label": "信息技术、信息系统",
              "value": "信息技术、信息系统"
          },
          {
              "label": "邮政",
              "value": "邮政"
          }
      ]
  },
  {
      "label": "城乡建设、环境保护",
      "value": "城乡建设、环境保护",
      "children": [
          {
              "label": "城乡建设与环境保护综合类",
              "value": "城乡建设与环境保护综合类"
          },
          {
              "label": "城乡规划",
              "value": "城乡规划"
          },
          {
              "label": "城乡建设",
              "value": "城乡建设"
          },
          {
              "label": "市政工程",
              "value": "市政工程"
          },
          {
              "label": "房地产",
              "value": "房地产"
          },
          {
              "label": "环境保护、治理",
              "value": "环境保护、治理"
          },
          {
              "label": "环境污染、监测",
              "value": "环境污染、监测"
          }
      ]
  },
  {
      "label": "农业、水利",
      "value": "农业、水利",
      "children": [
          {
              "label": "农业水利综合类",
              "value": "农业水利综合类"
          },
          {
              "label": "农业",
              "value": "农业"
          },
          {
              "label": "林业",
              "value": "林业"
          },
          {
              "label": "畜牧业",
              "value": "畜牧业"
          },
          {
              "label": "副业",
              "value": "副业"
          },
          {
              "label": "渔业",
              "value": "渔业"
          },
          {
              "label": "水利",
              "value": "水利"
          }
      ]
  },
  {
      "label": "财政",
      "value": "财政",
      "children": [
          {
              "label": "财政综合类",
              "value": "财政综合类"
          },
          {
              "label": "财政",
              "value": "财政"
          },
          {
              "label": "税务",
              "value": "税务"
          },
          {
              "label": "金融",
              "value": "金融"
          },
          {
              "label": "保险",
              "value": "保险"
          },
          {
              "label": "审计",
              "value": "审计"
          },
          {
              "label": "会计",
              "value": "会计"
          }
      ]
  },
  {
      "label": "商业、贸易",
      "value": "商业、贸易",
      "children": [
          {
              "label": "商贸综合类",
              "value": "商贸综合类"
          },
          {
              "label": "国内贸易",
              "value": "国内贸易"
          },
          {
              "label": "对外贸易",
              "value": "对外贸易"
          },
          {
              "label": "物流、仓储",
              "value": "物流、仓储"
          },
          {
              "label": "海关",
              "value": "海关"
          },
          {
              "label": "检验、检疫",
              "value": "检验、检疫"
          }
      ]
  },
  {
      "label": "旅游、服务业",
      "value": "旅游、服务业",
      "children": [
          {
              "label": "旅游、服务业综合类",
              "value": "旅游、服务业综合类"
          },
          {
              "label": "旅游",
              "value": "旅游"
          },
          {
              "label": "服务业",
              "value": "服务业"
          }
      ]
  },
  {
      "label": "气象、水文、测绘、地震",
      "value": "气象、水文、测绘、地震",
      "children": [
          {
              "label": "气象水文综合类",
              "value": "气象水文综合类"
          },
          {
              "label": "气象",
              "value": "气象"
          },
          {
              "label": "水文",
              "value": "水文"
          },
          {
              "label": "测绘",
              "value": "测绘"
          },
          {
              "label": "地震",
              "value": "地震"
          }
      ]
  },
  {
      "label": "对外事务",
      "value": "对外事务",
      "children": [
          {
              "label": "对外事务综合类",
              "value": "对外事务综合类"
          },
          {
              "label": "外交",
              "value": "外交"
          },
          {
              "label": "外事活动",
              "value": "外事活动"
          },
          {
              "label": "国际关系",
              "value": "国际关系"
          },
          {
              "label": "国际组织",
              "value": "国际组织"
          },
          {
              "label": "国际会议",
              "value": "国际会议"
          }
      ]
  },
  {
      "label": "政法、监察",
      "value": "政法、监察",
      "children": [
          {
              "label": "政法综合类",
              "value": "政法综合类"
          },
          {
              "label": "公安",
              "value": "公安"
          },
          {
              "label": "国家安全",
              "value": "国家安全"
          },
          {
              "label": "监察",
              "value": "监察"
          },
          {
              "label": "司法",
              "value": "司法"
          }
      ]
  },
  {
      "label": "科技、教育",
      "value": "科技、教育",
      "children": [
          {
              "label": "科教综合类",
              "value": "科教综合类"
          },
          {
              "label": "科技管理",
              "value": "科技管理"
          },
          {
              "label": "科研工作",
              "value": "科研工作"
          },
          {
              "label": "知识产权",
              "value": "知识产权"
          },
          {
              "label": "技术监督",
              "value": "技术监督"
          },
          {
              "label": "教育",
              "value": "教育"
          },
          {
              "label": "院校管理",
              "value": "院校管理"
          }
      ]
  },
  {
      "label": "文化、卫生、体育",
      "value": "文化、卫生、体育",
      "children": [
          {
              "label": "文体综合类",
              "value": "文体综合类"
          },
          {
              "label": "语言文字",
              "value": "语言文字"
          },
          {
              "label": "文学艺术",
              "value": "文学艺术"
          },
          {
              "label": "文物、考古",
              "value": "文物、考古"
          },
          {
              "label": "新闻出版",
              "value": "新闻出版"
          },
          {
              "label": "广播、电影、电视",
              "value": "广播、电影、电视"
          },
          {
              "label": "医药卫生管理",
              "value": "医药卫生管理"
          },
          {
              "label": "医疗保健",
              "value": "医疗保健"
          },
          {
              "label": "计划生育",
              "value": "计划生育"
          },
          {
              "label": "体育",
              "value": "体育"
          }
      ]
  },
  {
      "label": "军事、国防",
      "value": "军事、国防",
      "children": [
          {
              "label": "军事国防综合类",
              "value": "军事国防综合类"
          },
          {
              "label": "国防建设",
              "value": "国防建设"
          },
          {
              "label": "军事工作",
              "value": "军事工作"
          },
          {
              "label": "军队政治工作",
              "value": "军队政治工作"
          },
          {
              "label": "军事后勤工作",
              "value": "军事后勤工作"
          },
          {
              "label": "军事装备工作",
              "value": "军事装备工作"
          },
          {
              "label": "军事技术",
              "value": "军事技术"
          },
          {
              "label": "武警",
              "value": "武警"
          }
      ]
  },
  {
      "label": "劳动、人事",
      "value": "劳动、人事",
      "children": [
          {
              "label": "劳动人事综合类",
              "value": "劳动人事综合类"
          },
          {
              "label": "人事工作",
              "value": "人事工作"
          },
          {
              "label": "劳动就业",
              "value": "劳动就业"
          },
          {
              "label": "社会保障",
              "value": "社会保障"
          },
          {
              "label": "工资职称",
              "value": "工资职称"
          },
          {
              "label": "福利待遇",
              "value": "福利待遇"
          }
      ]
  },
  {
      "label": "民政、社区",
      "value": "民政、社区",
      "children": [
          {
              "label": "民政综合类",
              "value": "民政综合类"
          },
          {
              "label": "民政",
              "value": "民政"
          },
          {
              "label": "社区",
              "value": "社区"
          }
      ]
  },
  {
      "label": "文秘、行政",
      "value": "文秘、行政",
      "children": [
          {
              "label": "文秘行政综合类",
              "value": "文秘行政综合类"
          },
          {
              "label": "文秘工作",
              "value": "文秘工作"
          },
          {
              "label": "文种",
              "value": "文种"
          },
          {
              "label": "机要、保密",
              "value": "机要、保密"
          },
          {
              "label": "档案",
              "value": "档案"
          },
          {
              "label": "信访工作",
              "value": "信访工作"
          },
          {
              "label": "行政事务",
              "value": "行政事务"
          }
      ]
  },
  {
      "label": "综合党团",
      "value": "综合党团",
      "children": [
          {
              "label": "党团综合类",
              "value": "党团综合类"
          },
          {
              "label": "党派团体",
              "value": "党派团体"
          },
          {
              "label": "民族事务",
              "value": "民族事务"
          },
          {
              "label": "宗教",
              "value": "宗教"
          },
          {
              "label": "侨务工作",
              "value": "侨务工作"
          },
          {
              "label": "港澳台工作",
              "value": "港澳台工作"
          }
      ]
  },
  {
      "label": "综合类",
      "value": "综合类",
      "children": []
  }
]