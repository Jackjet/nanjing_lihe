<template>
  <el-main>
    <el-col :span="24">
      <el-form :inline="true" class='el-InputForm'>
        <el-form-item label="操作时间">
          <el-date-picker v-model="time" value-format="yyyy-MM-dd HH:mm:ss" type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作人">
          <el-input clearable size="small" placeholder="请输入操作人" v-model="SearchItem.createByName">
          </el-input>
        </el-form-item>
        <el-form-item label="操作类型">
          <el-input clearable size="small" placeholder="请输入操作类型" v-model="SearchItem.api">
          </el-input>
        </el-form-item>
        <el-form-item label="操作结果">
          <el-select clearable v-model="SearchItem.result" placeholder="请选择操作结果">
            <el-option key="0" label="失败" value="0">
            </el-option>
            <el-option key="1" label="成功" value="1">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style='margin-left: 1%;'>
          <el-button size='small' @click="SearchNoteList" icon="el-icon-search">查询</el-button>
          <el-button size='small' @click="reset" icon="el-icon-refresh-left">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="center">
      <div style="padding-top: 20px;">
        <el-button type="primary" plain size='small' @click="outUser" icon="el-icon-top-right">导出</el-button>
      </div>
      <TableList :table-data='tableData' v-loading="isSubmitLoading" @onHandleSelectionChange="handleSelectionChange" :table-selection="tableSelection" :table-label="tableHeader" :table-option="tableOpction">
      </TableList>
    </el-col>
    <el-col :span='24'>
      <pagination ref="page" :total="total" @pageChange="pageChange"></pagination>
    </el-col>
  </el-main>
</template>
<script>
import { userLogFindAll, userLogExport } from "@/api/operationLogs.js"
import TableList from '@/components/table/tableList'
import Pagination from '@/components/table/Pagination'
import { outExcel } from '@/utils/export'
export default {
  components: { TableList, Pagination },
  data() {
    return {
      tableSelection: {
        key: true,
        type: '',
        detaile: false
      },
      isSubmitLoading: false,
      tableData: [],
      tableHeader: [
        { id: false, type: '', label: '操作人', list: 'createByName' },
        { id: false, type: '', label: '操作类型', list: 'api' },
        { id: false, type: '', label: '操作内容', list: 'contentMsg' },
        { id: false, type: 'red', label: '操作结果', list: 'result' },
        { id: false, type: '', label: '来源IP', list: 'sourceIp' },
        { id: false, type: '', label: '操作时间', list: 'createTime' },
      ],
      tableOpction: {
        label: '操作',
        width: '180px',
        value: 0,
        options: [{
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
      time: [],
      SearchItem: {
        createByName: '',
        api: '',
        result: '',
        startTime: '',
        endTime: '',
      },
      lastItem: {
        createByName: '',
        api: '',
        result: '',
        startTime: '',
        endTime: '',
      },
      total: 0,
      pageSize: '20',
      currentPage: '1'
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    // 重置
    reset() {
      const that = this
      that.SearchItem.createByName = '',
        that.SearchItem.api = '',
        that.SearchItem.result = '',
        that.SearchItem.time = '',
        that.time = []
      Object.entries(that.SearchItem).map((item, index) => {
        that.lastItem[item[0]] = that.SearchItem[item[0]]
      })
    },
    // 获取列表
    async fetchData(type) {
      const that = this
      let data = {}
      data = this.SearchItem,
        data.page = this.currentPage,
        data.size = this.pageSize
      if (type !== 'page') {
        if (that.time !== null && that.time !== '' && that.time.length > 0) {
          data.startTime = this.time[0],
            data.endTime = this.time[1]
        } else {
          data.startTime = '',
            data.endTime = ''
        }
      }
      Object.entries(that.SearchItem).map((item, index) => {
        that.lastItem[item[0]] = that.SearchItem[item[0]]
      })
      try {
        that.isSubmitLoading = true
        const res = await userLogFindAll(data)
        that.isSubmitLoading = false
        if (res.data.code === 1) {
          res.data.data.content.map(function (v, k) {
            if (v.result === 0) {
              v.result = '失败'
            } else if (v.result === 1) {
              v.result = '成功'
            }
            if (v.contentMsg.length > 11) { v.contentMsg = v.contentMsg.substr(0, 11) + '...' }
          })
          this.tableData = res.data.data.content
          this.total = res.data.data.totalElements
        } else {
          this.$message.error(res.data.msg)
        }
      } catch (even) {
        this.$message.error(even.msg)
      }
    },
    // 搜索
    SearchNoteList() {
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
      Object.entries(that.SearchItem).map((item, index) => {
        that.SearchItem[item[0]] = that.lastItem[item[0]]
      })
      this.fetchData('page')
    },
    // 详情
    handleDetail(data) {
      const that = this
      that.$router.push({
        path: '/userLogDetail',
        query: {
          id: data.id
        }
      })
    },
    // 导出模板
    outUser() {
      if (this.tableData.length === 0) {
        this.$message.warning('没有数据，无需导出')
        return false
      }
      outExcel('请确认是否导出用户日志?', "/webadmin/log/userLog/export", this.SearchItem, '用户日志')
    },
  }
}
</script>
