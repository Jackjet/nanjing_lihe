<template>
  <el-main>
    <el-col :span="24">
      <el-form :inline="true" class='el-InputForm'>
        <el-form-item label="标题">
          <el-input clearable size="small" placeholder="请输入标题" v-model="searchItem.title"></el-input>
        </el-form-item>
        <el-form-item label="分类">
          <el-select clearable size="small" v-model="searchItem.type" placeholder="请选择分类">
             <el-option label="办事指南" value="办事指南">
            </el-option>
            <el-option label="政策法规" value="政策法规">
            </el-option>
            <el-option label="公示公告" value="公示公告">
            </el-option>
            <el-option label="其他" value="其他">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="来源">
          <el-cascader :show-all-levels="false" style="width: 100%;" placeholder="请选择来源" :props="props" 
          :disabled="disabled" collapse-tags clearable v-model="searchItem.organization" :options="organizationOptions" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchItem.enable" size="small" clearable placeholder="请选择状态">
            <el-option label="启用" value="启用">
            </el-option>
            <el-option label="禁用" value="禁用">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发布时间">
          <el-date-picker size="small" v-model="time" value-format="yyyy-MM-dd HH:mm:ss" type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item style='margin-left: 1%;'>
          <el-button size='small' @click="search" icon="el-icon-search">查询</el-button>
          <el-button size='small' @click="reset" icon="el-icon-refresh-left">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="center">
      <div style="padding-top: 20px;">
        <el-button type="primary" v-if="permissions.add" plain size='small' @click="handleAdd" icon="el-icon-plus">新增</el-button>
        <el-button type="danger" v-if="permissions.delete" plain size='small' @click="handleDeletes" icon="el-icon-delete">删除</el-button>
      </div>
      <TableList :table-data='tableData' v-loading="isSubmitLoading" :table-selection="tableSelection" :table-label="tableHeader" :table-option="tableOpction" @onHandleSelectionChange="onHandleSelectionChange">
      </TableList>
    </el-col>
    <el-col :span='24'>
      <pagination ref="page" :total="total" @pageChange="pageChange"></pagination>
    </el-col>
  </el-main>
</template>
<script>
import { organizationFindAll } from "@/api/organization.js";
import { findAll,release,deleteById,deleteBatch,update } from '@/api/government/policy';
import TableList from '@/components/table/tableList'
import Pagination from '@/components/table/Pagination'

export default {
  components: { TableList, Pagination },
  data() {
    return {
      props: { checkStrictly: true },
      organizationOptions: [],
      permissions: {
        
      },
      time: [],
      time1: [],
      tableSelection: {
        key: true,
        type: 'selection',
        detaile: false
      },
      isSubmitLoading: false,
      tableData: [],
      tableHeader: [
        { label: '标题', list: 'title' },
        { label: '发布人', list: 'createById' },
        { label: '发布时间', list: 'releaseTime' },
        { label: '分类', list: 'type' },
        { label: '来源', list: 'organizationName' },
      ],
      tableOpction: {
        label: '操作',
        width: '220px',
        value: 0,
        options: [
          {
            label: '编辑',
            key: 0,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.handleEdit(row)
            }
          },
          {
            label: '启用',
            key: 1,
            type: 'text',
            icon: '',
            show:(row)=>{
              if(row.enable == '禁用'){
                return true
              }else{
                return false
              }
            },
            State: true,
            method: (row) => {
              this.handleEnable(row, "启用")
            }
          }, {
            label: '禁用',
            key: 2,
            type: 'text',
            icon: '',
            show:(row)=>{
              if(row.enable == '启用'){
                return true
              }else{
                return false
              }
            },
            State: true,
            method: (row) => {
              this.handleEnable(row, "禁用")
            }
          },
          {
            label: '删除',
            key: 0,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.handleDelete(row)
            }
          },{
            label: '报名企业',
            key: 0,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.apply(row)
            }
          }]
      },
      searchItem: {},
      lastItem: {
        title: '',
        type: '',
        enable: '',
      },
      total: 0,
      pageSize: '20',
      currentPage: '1',

    }
  },
  created() {
    this.button()
    this.fetchData()
    this.findroles()
  },
  methods: {
     // 组织机构列表
    async findroles(data) {
      const that = this
      that.loading = true
      const response = await organizationFindAll()
      that.loading = false
      if (response.data.code === 1) {
        let arrData = []
        arrData.push(response.data.data)
        that.organizationOptions = this.getJsonTree(arrData, '')
      } else {
        // 添加上传失败后 回调失败信息
        that.$message.error(response.data.msg)
        return false
      }
    },
    getJsonTree(data, parentId) {
      let itemArr = []
      for (let i = 0; i < data.length; i++) {
        let node = data[i];
        if (node.parentId === parentId) {
          let newNode = {};
          newNode.value = node.id+'-'+node.name;
          newNode.label = node.name;
          if (node.children.length > 0) {
            newNode.children = this.getJsonTree(node.children, node.id);
          }
          itemArr.push(newNode);
        }
      }
      return itemArr;
    },
    apply(row){
      this.$router.push({
        path: '/policyApply',
        query: {
          title: '报名企业',
          id: row.id
        }
      })
    },
    button(){
      let path = this.$route.path.replace('/','')
      let list = JSON.parse(sessionStorage.getItem('UserButtons'))
      list.forEach((v,i)=>{
        if(v.indexOf(path) != '-1'){
          let btn = v.replace(path+'_','')
          this.permissions[btn] = btn
        }
      })
    },
    // 添加
    handleAdd() {
      this.$router.push({
        path: '/policyDetails',
        query: {
          title: '新增',
          type: 'add'
        }
      })
    },
    // 重置
    reset() {
      const that = this
      that.searchItem.title = ''
      that.searchItem.type = ''
      that.searchItem.enable = ''
      this.time = []
      Object.entries(that.searchItem).map((item, index) => {
        that.lastItem[item[0]] = that.searchItem[item[0]]
      })
    },

    // 获取列表
    async fetchData(type) {
      const that = this
      let data = {}
      data = JSON.parse(JSON.stringify(this.searchItem))
      data.page = this.currentPage
      data.size = this.pageSize
      Object.entries(that.searchItem).map((item, index) => {
        that.lastItem[item[0]] = that.searchItem[item[0]]
      })
      if (that.time !== null && that.time !== '' && that.time.length > 0) {
        data.startTime = this.time[0]
        data.endTime = this.time[1]
      } else {
        data.startTime = null
        data.endTime = null
      }
      if(data.organization && data.organization!=''){
        data.organizationId = data.organization[data.organization.length-1].split('-')[0]
        data.organizationName = data.organization[data.organization.length-1].split('-')[1]
        delete data.organization
      }
      try {
        that.isSubmitLoading = true
        const res = await findAll(data)
        that.isSubmitLoading = false
        if (res.data.code === 1) {
          this.tableData = res.data.data.content
          this.total = res.data.data.totalElements
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },

    search() {
      this.currentPage = 1
      this.$refs.page.Page(1)
      this.fetchData()
    },

    // 翻页
    pageChange(item) {
      let that = this
      this.pageSize = item.limit
      this.currentPage = item.page
      if (that.lastItem.startDate === '' || that.lastItem.startDate === null) {
        that.time = []
      }
      Object.entries(that.searchItem).map((item, index) => {
        that.searchItem[item[0]] = that.lastItem[item[0]]
      })
      if (that.lastItem.startDate === '' || that.lastItem.startDate === null) {
        that.time = []
      }
      this.fetchData('page')
    },
    // 编辑
    handleEdit: function (data) {
      this.$router.push({
        path: '/policyDetails',
        query: {
          id: data.id,
          title: '编辑',
          type: 'edit'
        }
      })
    },
    // 详情
    handleDetail(data) {
      this.$router.push({
        path: '/policyDetails',
        query: {
          id: data.id,
          title: '详情',
          type: 'detail'
        }
      })
    },
    // 启用禁用
    handleEnable(data, text) {
      release({ id: data.id, enable: text }).then(res => {
        if (res.data.code === 1) {
          this.$message.success(res.data.msg);
          this.fetchData();
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    handleDelete(data) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteById({ id: data.id }).then(res => {
          if (res.data.code === 1) {
            this.$message.success('删除成功！');
            this.fetchData()
          } else {
            this.$message.error(res.data.msg);
          }
        })
      }).catch(() => { });
    },
    // 多选
    onHandleSelectionChange(val) {
      let str = ""
      val.forEach(item => {
        str += item.id + ","
      });
      this.checkbox = str.slice(0, -1);
    },
    // 批量删除
    handleDeletes() {
      this.$confirm('此操作将永久删除选中文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteBatch({ ids: this.checkbox }).then(res => {
          if (res.data.code === 1) {
            this.$message.success('删除成功！');
            this.fetchData()
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(() => { });
    },
  }
}
</script>
