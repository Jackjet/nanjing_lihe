<template>
  <el-main>
    <el-col :span="24" class="center">
      <div style="padding-top: 20px;">
        <el-button type="primary" v-if="permissions.add" plain size='small' @click="handleAdd" icon="el-icon-plus">新增</el-button>
        <!-- <el-button type="danger" plain size='small' @click="handleDeletes" icon="el-icon-delete">删除</el-button> -->
      </div>
      <TableList :table-data='tableData' v-loading="isSubmitLoading" :table-selection="tableSelection" :table-label="tableHeader" :table-option="tableOpction" @onHandleSelectionChange="onHandleSelectionChange">
      </TableList>
    </el-col>
  </el-main>
</template>
<script>
import { providerFindAll, providerDelete, providerDeleteBatch, providerRelease } from '@/api/serviceProvider.js';
import TableList from '@/components/table/tableList'

export default {
  components: { TableList },
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
        { label: '标题', list: 'title' },
        { label: '创建人', list: 'createById' },
        { label: '创建时间', list: 'createTime' },
        { label: '发布人', list: 'updateById' },
        { label: '发布时间', list: 'updateTime' },
        {
          type: 'html',
          label: '状态',
          list: 'enable',
          code: (row) => {
            if (row.enable === '禁用') {
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
            label: '启用',
            key: 0,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.handleEnable(row, "启用")
            }
          },
          {
            label: '禁用',
            key: 0,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.handleEnable(row, "禁用")
            }
          },
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
            label: '详情',
            key: 0,
            type: 'text',
            icon: '',
            State: true,
            method: (row) => {
              this.handleDetail(row)
            }
          },
          // {
          //   label: '删除',
          //   key: 0,
          //   type: 'text',
          //   icon: '',
          //   State: true,
          //   method: (row) => {
          //     this.handleDetele(row)
          //   }
          // }
        ]
      },
      checkbox: ""
    }
  },
  created() {
    this.queryListFun();
  },
  methods: {
    queryListFun() {
      providerFindAll().then(res => {
        if (res.data.code === 1) {
          this.tableData = res.data.data.content;
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    // 启用禁用
    handleEnable(data, text) {
      providerRelease({ id: data.id, enable: text }).then(res => {
        if (res.data.code === 1) {
          this.$message.success(res.data.msg);
          this.queryListFun();
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    // 编辑
    handleEdit(data) {
      this.$router.push({
        path: '/serviceProviderDetails',
        query: {
          title: '编辑',
          type: 'edit',
          id: data.id
        }
      })
    },
    handleDetail(data) {
      this.$router.push({
        path: '/serviceProviderDetails',
        query: {
          title: '详情',
          type: 'detail',
          id: data.id
        }
      })
    },
    // 删除
    handleDetele(data) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        providerDelete({ id: data.id }).then(res => {
          if (res.data.code === 1) {
            this.$message.success('删除成功！');
            this.queryListFun();
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
        providerDeleteBatch({ ids: this.checkbox }).then(res => {
          if (res.data.code === 1) {
            this.$message.success('删除成功！');
            this.queryListFun();
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(() => { });
    },

    // 添加
    handleAdd(data) {
      if (this.tableData.length >= 6) {
        this.$message('最多添加六条数据');
      } else {
        this.$router.push({
          path: '/serviceProviderDetails',
          query: {
            title: '新增',
            type: 'add'
          }
        })
      }
    },
  }
}
</script>
