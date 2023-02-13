<template>
  <el-main>
    <el-col :span="24">
      <el-form :inline="true" class='el-InputForm'>
        <el-form-item label="企业名称">
          <el-input clearable size="small" placeholder="请输入企业名称" v-model="searchItem.enterpriseName"></el-input>
        </el-form-item>
        <el-form-item label="标题">
          <el-input clearable size="small" placeholder="请输入标题" v-model="searchItem.title"></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input clearable size="small" placeholder="请输入联系人" v-model="searchItem.liaisonName"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input clearable size="small" placeholder="请输入联系方式" v-model="searchItem.liaisonTel"></el-input>
        </el-form-item>
        <el-form-item label="工单状态">
          <el-select v-model="searchItem.status" size="small" clearable placeholder="请选择工单状态">
            <el-option label="待处理" value="待处理"></el-option>
            <el-option label="待评价" value="待评价"></el-option>
            <el-option label="已评价" value="已评价"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工单分类">
          <el-select clearable size="small" v-model="searchItem.orderType" placeholder="请选择分类">
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
        <el-form-item label="申请时间">
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
import { findAll } from '@/api/government/workorder';
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
        { label: '标题', list: 'title' },
        { label: '联系人', list: 'liaisonName' },
        { label: '联系电话', list: 'liaisonTel' },
        { label: '企业类型', list: 'enterpriseType' },
        { label: '申请时间', list: 'createTime' },
        { label: '工单状态', list: 'status' },
        // { label: '附件', list: 'attachmentPath' },
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
          }]
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
        path: '/workorderDetails',
        query: {
          id: data.id,
          title: '详情',
          type: 'detail'
        }
      })
    },
    // 申请驳回
    applyBack(row){
      this.dialogFormVisible = true
    }
  }
}
</script>
