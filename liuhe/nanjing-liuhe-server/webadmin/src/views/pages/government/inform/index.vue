<template>
  <el-main>
    <el-col :span="24" class="center">
      <div style="padding-top: 20px;">
        <el-button type="primary" v-if="permissions.add" plain size='small' @click="handleAdd" icon="el-icon-plus">新增</el-button>
      </div>
      <TableList :table-data='tableData' v-loading="isSubmitLoading" :table-selection="tableSelection" :table-label="tableHeader" :table-option="tableOpction">
      </TableList>
    </el-col>
  </el-main>
</template>
<script>
import TableList from '@/components/table/tableList'
import { findAll,release,deleteById,update } from '@/api/government/inform';
export default {
  components: { TableList },
  data() {
    return {
      permissions: {},
      time: [],
      tableSelection: {
        key: true,
        detaile: false
      },
      isSubmitLoading: false,
      tableData: [],
      tableHeader: [
        { label: '标题', list: 'title' },
        { label: '创建人', list: 'createById' },
        { label: '创建时间', list: 'createTime' },
        { label: '发布人', list: 'updateById' },
        { label: '发布时间', list: 'releaseTime' },
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
          }]
      },
      checkbox: ""
    }
  },
  created() {
    this.queryListFun();
    this.button()
  },
  methods: {
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
    queryListFun() {
      findAll().then(res => {
        if (res.data.code === 1) {
          this.tableData = res.data.data.content;
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    // 启用禁用
    handleEnable(data, text) {
      release({ id: data.id, enable: text }).then(res => {
        if (res.data.code === 1) {
          this.$message.success(res.data.msg);
          this.queryListFun();
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    handleEdit(data) {
      this.$router.push({
        path: '/informDetails',
        query: {
          title: '编辑',
          type: 'edit',
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
        deleteById({ id: data.id }).then(res => {
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
      const that = this
      that.$router.push({
        path: '/informDetails',
        query: {
          title: '新增',
          type: 'add'
        }
      })
    },
  }
}
</script>
