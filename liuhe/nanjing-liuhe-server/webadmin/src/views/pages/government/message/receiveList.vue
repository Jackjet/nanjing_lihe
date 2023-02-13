<template>
  <el-main>
    <el-col :span="24">
      <el-form :inline="true" class='el-InputForm'>
        <el-form-item label="状态">
          <el-select v-model="searchItem.status" size="small" clearable placeholder="请选择状态">
            <el-option label="已读" value="已读">
            </el-option>
            <el-option label="未读" value="未读">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="确认状态">
          <el-select v-model="searchItem.status" size="small" clearable placeholder="请选择状态">
            <el-option label="同意" value="同意">
            </el-option>
            <el-option label="拒绝" value="拒绝">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style='margin-left: 1%;'>
          <el-button size='small' @click="search" icon="el-icon-search">查询</el-button>
          <el-button size='small' @click="reset" icon="el-icon-refresh-left">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="center">
      <TableList :table-data='tableData' v-loading="isSubmitLoading" 
      :table-selection="tableSelection" 
      :table-label="tableHeader"
       :table-option="tableOpction"
        @onHandleSelectionChange="onHandleSelectionChange"
       >
      </TableList>
    </el-col>
    <el-col :span='24'>
      <pagination ref="page" :total="total" @pageChange="pageChange"></pagination>
    </el-col>
  </el-main>
</template>
<script>
import { receiveListFindAll } from '@/api/government/governmentMessage';
import TableList from '@/components/table/tableList'
import Pagination from '@/components/table/Pagination'

export default {
  components: { TableList, Pagination },
  data() {
    return {
      messageId:this.$route.query.id,
      checkbox:'',
      checkName:'',
      permissions: {
        
      },
      time: [],
      tableSelection: {
        key: true,
        type: 'selection',
        detaile: false
      },
      isSubmitLoading: false,
      tableData: [],
      tableHeader: [
        { label: '接收者', list: 'receiveName' },
        { label: '状态', list: 'status' },
        { label: '确认状态', list: 'state' },
        { label: '拒绝原因', list: 'content' }
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
            icon: '',
            State: true,
            method: (row) => {
              this.handleDetail(row)
            }
          }]
      },
      searchItem: {
      },
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
  },
  methods: {
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
      data = this.searchItem
      this.searchItem.messageId = this.messageId
      data.page = this.currentPage
      data.size = this.pageSize
      Object.entries(that.searchItem).map((item, index) => {
        that.lastItem[item[0]] = that.searchItem[item[0]]
      })
      try {
        that.isSubmitLoading = true
        const res = await receiveListFindAll(data)
        that.isSubmitLoading = false
        if (res.data.code === 1) {
          this.tableData = res.data.data.content
          this.tableData.forEach((v,i)=>{
            if(v.isSettled == 0){
              v.isSettled = '未入驻'
            }else{
              v.isSettled = '已入驻'
            }
          })
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
    // 详情
    handleDetail(data) {
      this.$router.push({
        path: '/dynamicApplyDetaile',
        query: {
          id: data.id,
          title: '详情',
          type: 'detail'
        }
      })
    },
     // 多选
    onHandleSelectionChange(val) {
      let str = ""
      let str1 = ""
      val.forEach(item => {
        str += item.id + ","
        str1 += item.enterpriseName + ","
      });
      this.checkbox = str.slice(0, -1);
      this.checkName = str1.slice(0, -1);
    },
    choose(){
      this.$router.push({
        path: '/governmentMessageDetails',
        query: {
          ids: this.checkbox,
          title: '新增',
          type: 'add',
          names:this.checkName
        }
      })
      
      console.log(this.checkbox)
    }
  }
}
</script>
