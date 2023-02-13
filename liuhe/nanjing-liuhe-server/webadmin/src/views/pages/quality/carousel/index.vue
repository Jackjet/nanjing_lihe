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
import TableList from '@/components/table/tableList'
import { rotationChartFindAll, rotationChartDelete, rotationChartDeleteBatch } from '@/api/carousel.js';
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
        { label: '轮播图标题', list: 'name' },
        { label: '创建人', list: 'createById' },
        { label: '创建时间', list: 'createTime' },
        { label: '修改人', list: 'updateById' },
        { label: '修改时间', list: 'updateTime' },
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
          // {
          //   label: '启用',
          //   key: 0,
          //   type: 'text',
          //   icon: '',
          //   State: true,
          //   method: (row) => {
          //     this.handleEnable(row, '启用')
          //   }
          // }, {
          //   label: '禁用',
          //   key: 0,
          //   type: 'text',
          //   icon: '',
          //   State: true,
          //   method: (row) => {
          //     this.handleEnable(row, '禁用')
          //   }
          // },
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
              this.handleDateils(row)
            }
          },
          // {
          //   label: '删除',
          //   key: 0,
          //   type: 'text',
          //   icon: '',
          //   State: true,
          //   method: (row) => {
          //     this.handleDelete(row)
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
    handleEnable(row, text) {
      if (row.enable === text) {
        if (row.enable === '启用') {
          this.$message('该用户已启用，无需再次启用');
        } else {
          this.$message('该用户已禁用，无需再次禁用');
        }
      } else {
        this.$confirm('此操作会影响用户网站, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // infoUpdateEnable({ id: row.id, enable: text }).then(res => {
          //   if (res.data.code === 1) {
          //     this.$message.success(res.data.msg);
          //     this.fetchData();
          //   } else {
          //     this.$message.error(res.data.msg);
          //   }
          // })

        }).catch(() => { });
      }
    },
    queryListFun() {
      rotationChartFindAll().then(res => {
        if (res.data.code === 1) {
          this.tableData = res.data.data.content;
        } else {
          this.$message.error(res.data.msg)
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
    handleDeletes() {
      this.$confirm('此操作将永久删除选中文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        rotationChartDeleteBatch({ ids: this.checkbox }).then(res => {
          if (res.data.code === 1) {
            this.$message.success('删除成功！');
            this.queryListFun();
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(() => { });

    },
    handleEdit(data) {
      this.$router.push({
        path: '/carouselDetails',
        query: {
          title: '编辑',
          type: 'edit',
          id: data.id
        }
      })
    },
    // 详情
    handleDateils(data) {
      this.$router.push({
        path: '/carouselDetails',
        query: {
          title: '详情',
          type: 'detail',
          id: data.id
        }
      })
    },
    // 删除
    handleDelete(data) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        rotationChartDelete({ id: data.id }).then(res => {
          if (res.data.code === 1) {
            this.$message.success('删除成功！');
            this.queryListFun();
          } else {
            this.$message.error(res.data.msg);
          }
        })
      }).catch(() => { });
    },
    // 添加
    handleAdd(data) {
      if (this.tableData.length >= 6) {
        this.$message('最多只能添加六条数据');
      } else {
        this.$router.push({
          path: '/carouselDetails',
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
