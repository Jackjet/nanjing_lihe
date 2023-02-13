<template>
  <el-main>
    <el-col :span="24">
      <el-form :inline="true" class='el-InputForm'>
        <el-form-item label="企业名称">
          <el-input clearable size="small" placeholder="请输入企业名称" v-model="searchItem.enterpriseName"></el-input>
        </el-form-item>
        <el-form-item label="发布时间">
          <el-date-picker size="small" v-model="time" value-format="yyyy-MM-dd HH:mm:ss" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
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
      </div>
      <TableList :table-data='tableData' v-loading="isSubmitLoading" :table-selection="tableSelection" :table-label="tableHeader" :table-option="tableOpction">
      </TableList>
    </el-col>
    <el-col :span='24'>
      <pagination ref="page" :total="total" @pageChange="pageChange"></pagination>
    </el-col>
  </el-main>
</template>
<script>
import { findAll,deleteById } from '@/api/government/parkSettled';
import TableList from '@/components/table/tableList'
import Pagination from '@/components/table/Pagination'

export default {
  components: { TableList, Pagination },
  data() {
    return {
      dialogFormVisible:false,
      form:{
        name:''
      },
      permissions: {
        add: true,
      },
      time: [],
      tableSelection: {
        key: true,
        detaile: false
      },
      isSubmitLoading: false,
      tableData: [],
      tableHeader: [
        { label: '企业名称', list: 'enterpriseName' },
        { label: '申请时间', list: 'createTime' },
        { label: '跳转路径', list: 'url' },
      ],
      tableOpction: {
        label: '操作',
        width: '220px',
        value: 0,
        options: [
          {
            label: '详情',
            key: 0,
            type: 'text',
            icon: 'el-icon-tickets',
            State: true,
            method: (row) => {
              this.handleDetails(row)
            }
          },{
            label: '删除',
            key: 3,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.handleDelete(row)
            }
          },]
      },
      searchItem: {
       
      },
      lastItem: {
        name: '',
        serverName: '',
        resourceType: '',
      },
      total: 0,
      pageSize: '20',
      currentPage: '1',

    }
  },
  created() {
    this.fetchData()
    this.button()
  },
  methods: {
    // 重置
    reset() {
      const that = this
      that.searchItem.name = ''
      that.searchItem.serverName = ''
      that.searchItem.resourceType = ''
      Object.entries(that.searchItem).map((item, index) => {
        that.lastItem[item[0]] = that.searchItem[item[0]]
      })
    },
    // 按钮权限
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
    // 获取列表
    async fetchData(type) {
      const that = this
      let data = {}
      data = this.searchItem
      data.page = this.currentPage
      data.size = this.pageSize
      if (type !== 'page') {
        if (that.time !== null && that.time !== '' && that.time.length > 0) {
          data.startTime = this.time[0]
          data.endTime = this.time[1]
        } else {
          data.startTime = ''
          data.endTime = ''
        }
      }
      Object.entries(that.searchItem).map((item, index) => {
        that.lastItem[item[0]] = that.searchItem[item[0]]
      })
      try {
        that.isSubmitLoading = true
        const res = await findAll(data)
        console.log(res)
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
      if (that.lastItem.startTime === '' || that.lastItem.startTime === null) {
        that.time = []
      }
      Object.entries(that.searchItem).map((item, index) => {
        that.searchItem[item[0]] = that.lastItem[item[0]]
      })
      if (that.lastItem.startTime === '' || that.lastItem.startTime === null) {
        that.time = []
      }
      this.fetchData('page')
    },
    // 详情
    handleDetails (data) {
      this.$router.push({
        path: '/parkSettledDetail',
        query: {
          id: data.id,
          title: '详情',
          type: 'detail'
        }
      })
    },
    // 添加
    handleAdd() {
      this.$router.push({
        path: '/parkSettledDetail',
        query: {
          title: '新增',
          type: 'add'
        }
      })
    },
  }
}
</script>
