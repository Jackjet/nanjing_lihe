<template>
  <el-main>
    <el-col :span="24">
      <el-form :inline="true" class='el-InputForm'>
        <el-form-item label="咨询时间">
          <el-date-picker v-model="time" size="small" type="daterange" value-format="yyyy-MM-dd" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchItem.replyStatus" size="small" clearable placeholder="请选择状态">
            <el-option label="待回复" value="待回复"></el-option>
            <el-option label="已回复" value="已回复"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="联系人">
          <el-input clearable size="small" placeholder="请输入姓名" v-model="searchItem.liaisonName"></el-input>
        </el-form-item>

        <el-form-item label="联系方式">
          <el-input clearable size="small" placeholder="请输入手机号" v-model="searchItem.liaisonTel"></el-input>
        </el-form-item>

        <el-form-item style='margin-left: 1%;'>
          <el-button size='small' @click="search" icon="el-icon-search">查询</el-button>
          <el-button size='small' @click="reset" icon="el-icon-refresh-left">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="center">
      <!-- <div style="padding-top: 20px;">
        <el-button type="danger" v-if="permissions.add" plain size='small' @click="handleDetails" icon="el-icon-delete">删除</el-button>
      </div> -->
      <TableList :table-data='tableData' v-loading="isSubmitLoading" :table-selection="tableSelection" :table-label="tableHeader" :table-option="tableOpction" @onHandleSelectionChange="onHandleSelectionChange">
      </TableList>
    </el-col>
    <el-col :span='24'>
      <pagination ref="page" :total="total" @pageChange="pageChange"></pagination>
    </el-col>
  </el-main>
</template>
<script>
import { consultFindAll, rotationChartDeleteBatch } from "@/api/consult.js"
import TableList from '@/components/table/tableList'
import Pagination from '@/components/table/Pagination'

export default {
  components: { TableList, Pagination },
  data() {
    return {
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
        { label: '公司名称', list: 'enterpriseName' },
        { label: '联系人', list: 'liaisonName' },
        { label: '联系方式', list: 'liaisonTel' },
        { label: '咨询时间', list: 'createTime' },
        { label: '更新时间', list: 'updateTime' },
        { label: '状态', list: 'replyStatus' },
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
              this.handleDetail(row)
            }
          }]
      },
      searchItem: {
        liaisonName: '',
        liaisonTel: '',
        replyStatus: '',
      },
      lastItem: {
        liaisonName: '',
        liaisonTel: '',
        replyStatus: '',
      },
      total: 0,
      pageSize: '20',
      currentPage: '1',
      checkbox: "",
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    // 重置
    reset() {
      const that = this
      this.time = ""
      that.searchItem.liaisonName = ''
      that.searchItem.liaisonTel = ''
      that.searchItem.replyStatus = ''
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
          data.startDate = this.time[0]
          data.endDate = this.time[1]
        } else {
          data.startDate = ''
          data.endDate = ''
        }
      }
      Object.entries(that.searchItem).map((item, index) => {
        that.lastItem[item[0]] = that.searchItem[item[0]]
      })
      try {
        that.isSubmitLoading = true
        const res = await consultFindAll(data)
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
    // 编辑
    handleDetail: function (data) {
      const that = this
      that.$router.push({
        path: '/consultDetails',
        query: {
          id: data.id,
        }
      })
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
    handleDetails() {
      this.$confirm('此操作将永久删除选中项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        rotationChartDeleteBatch({ ids: this.checkbox }).then(res => {
          if (res.data.code === 1) {
            this.$message.success('删除成功！');
            this.fetchData();
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(() => { });
    }
  }
}
</script>
