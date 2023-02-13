<template>
  <el-main>
    <el-col :span="24">
      <el-form :inline="true" class='el-InputForm'>
        <el-form-item label="公司名称">
          <el-input clearable size="small" placeholder="请输入姓名" v-model="searchItem.enterpriseName"></el-input>
        </el-form-item>

        <el-form-item label="联系电话">
          <el-input clearable size="small" placeholder="请输入手机号" v-model="searchItem.phone"></el-input>
        </el-form-item>

        <el-form-item label="状态">
          <el-select v-model="searchItem.enable" size="small" clearable placeholder="请选择状态">
            <el-option label="禁用" value="0"></el-option>
            <el-option label="启用" value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item style='margin-left: 1%;'>
          <el-button size='small' @click="search" icon="el-icon-search">查询</el-button>
          <el-button size='small' @click="reset" icon="el-icon-refresh-left">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="center">
      <div style="padding-top: 20px;">
        <!-- <el-button type="primary" v-if="permissions.add" plain size='small' @click="handleEnable(1)">批量启用</el-button>
        <el-button type="danger" v-if="permissions.add" plain size='small' @click="handleEnable(0)">批量禁用</el-button> -->
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
import TableList from '@/components/table/tableList'
import Pagination from '@/components/table/Pagination'
import { infoFindAll, infoUpdateEnable, infoUpdateAllEnable } from '@/api/zlxzUser.js';
export default {
  components: { TableList, Pagination },
  data() {
    return {
      permissions: {
        add: true,
      },
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
        { label: '联系方式', list: 'phone' },
        {
          type: 'html',
          label: '状态',
          list: 'enable',
          code: (row) => {
            if (row.enable === 0) {
              return '<span style="color:red;">禁用</span>'
            } else {
              return '<span style="color:#67C23A;">启用</span>'
            }
          }
        },
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
          }, {
            label: '启用',
            key: 0,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.handleEdit(row, 1)
            }
          }, {
            label: '禁用',
            key: 0,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.handleEdit(row, 0)
            }
          }]
      },
      searchItem: {
        enterpriseName: '',
        phone: '',
        enable: '',
      },
      lastItem: {
        enterpriseName: '',
        phone: '',
        enable: '',
      },
      total: 0,
      pageSize: '20',
      currentPage: '1',
      checkbox: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    // 重置
    reset() {
      const that = this
      that.searchItem.enterpriseName = ''
      that.searchItem.phone = ''
      that.searchItem.enable = ''
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
      Object.entries(that.searchItem).map((item, index) => {
        that.lastItem[item[0]] = that.searchItem[item[0]]
      })
      try {
        that.isSubmitLoading = true
        const res = await infoFindAll(data);
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
      Object.entries(that.searchItem).map((item, index) => {
        that.searchItem[item[0]] = that.lastItem[item[0]]
      })
      this.fetchData('page')
    },
    // 详情
    handleDetail(row) {
      this.$router.push({
        path: 'userListDetails',
        query: {
          id: row.id
        }
      })
    },
    handleEdit(row, type) {
      if (row.enable === type) {
        if (row.enable === 1) {
          this.$message('该用户已启用，无需再次启用');
        } else {
          this.$message('该用户已禁用，无需再次禁用');
        }
      } else {
        this.$confirm('此操作会影响用户登录网站, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          infoUpdateEnable({ id: row.id, enable: type }).then(res => {
            if (res.data.code === 1) {
              this.$message.success(res.data.msg);
              this.fetchData();
            } else {
              this.$message.error(res.data.msg);
            }
          })

        }).catch(() => { });
      }
    },
    // 多选
    onHandleSelectionChange(val) {
      val.forEach(item => {
        this.checkbox.push(item.id)
      });
    },
    handleEnable(type) {
      this.$confirm('此操作会影响用户登录网站, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        infoUpdateAllEnable({ ids: this.checkbox, enable: type }).then(res => {
          if (res.data.code === 1) {
            this.$message.success(res.data.msg);
            this.fetchData();
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(() => { });
    },
  }
}
</script>
