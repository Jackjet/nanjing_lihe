<template>
  <el-main>
    <el-col :span="24">
      <el-form :inline="true" class='el-InputForm'>
        <el-form-item label="企业名称">
          <el-input clearable size="small" placeholder="请输入报警名称" v-model="searchItem.name"></el-input>
        </el-form-item>
        <el-form-item label="企业类型">
          <el-select clearable size="small" v-model="searchItem.type" placeholder="请选择分类">
            <el-option label="公益讲堂" value="公益讲堂">
            </el-option>
            <el-option label="线下论坛" value="线下论坛">
            </el-option>
            <el-option label="政策解读" value="政策解读">
            </el-option>
            <el-option label="其他" value="其他">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input clearable size="small" placeholder="请输入联系人" v-model="searchItem.serverName"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input clearable size="small" placeholder="请输入联系方式" v-model="searchItem.serverName"></el-input>
        </el-form-item>
        <el-form-item label="入驻状态">
          <el-select v-model="searchItem.resourceType" size="small" clearable placeholder="请选择入驻状态">
            <el-option label="启用" value="启用"></el-option>
            <el-option label="禁用" value="禁用"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item style='margin-left: 1%;'>
          <el-button size='small' @click="search" icon="el-icon-search">查询</el-button>
          <el-button size='small' @click="reset" icon="el-icon-refresh-left">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="center">
      <TableList :table-data='tableData' v-loading="isSubmitLoading" :table-selection="tableSelection" :table-label="tableHeader" :table-option="tableOpction">
      </TableList>
    </el-col>
    <el-col :span='24'>
      <pagination ref="page" :total="total" @pageChange="pageChange"></pagination>
    </el-col>
    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="驳回原因" label-width="120px">
          <el-input type="textarea" v-model="form.name"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </el-main>
</template>
<script>
import { consultFindAll } from "@/api/consult.js"
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
        type: 'selection',
        detaile: false
      },
      isSubmitLoading: false,
      tableData: [],
      tableHeader: [
        { label: '企业名称', list: 'enterpriseName' },
        { label: '联系人', list: 'liaisonName' },
        { label: '联系电话', list: 'liaisonTel' },
        { label: '企业类型', list: 'phoneOrEmail' },
        { label: '申请时间', list: 'count' },
        { label: '附件', list: 'count' },
      ],
      tableOpction: {
        label: '操作',
        width: '220px',
        value: 0,
        options: [
          {
            label: '企业详情',
            key: 0,
            type: 'text',
            icon: 'el-icon-tickets',
            State: true,
            method: (row) => {
            }
          }, {
            label: '申请通过',
            key: 0,
            type: 'text',
            icon: 'el-icon-tickets',
            State: true,
            method: (row) => {
            }
          }, {
            label: '申请驳回',
            key: 0,
            type: 'text',
            icon: 'el-icon-tickets',
            State: true,
            method: (row) => {
              this.applyBack(row)
            }
          }]
      },
      searchItem: {
        name: '',
        serverName: '',
        resourceType: '',
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
        const res = await consultFindAll(data)
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
    // 申请驳回
    applyBack(row){
      console.log(`111111`)
      this.dialogFormVisible = true
    }
  }
}
</script>
