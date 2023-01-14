<template>
  <div style="padding: 10px">
    <div>
      <el-input v-model="search" placeholder="请输入关键词" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
    </div>


    <el-table :data="tableData" stripe style="width: 100%" height="500">
      <el-table-column prop="headerImage" label="封面" sortable>
        <template #default="scope">
          <el-image :src="scope.row.headerImage" lazy>详细</el-image>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="steamAppid" label="steamID" sortable/>
      <el-table-column prop="name" label="游戏名" sortable/>
      <!--      <el-table-column prop="detailedDescription" label="详细描述" sortable/>-->
      <el-table-column label="评论" sortable>
        <template #default="scope">
          <el-button size="small" @click="showComment(scope.row)">详细</el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>

          <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>

          <!--          <el-button type="text" size="small">删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage4"
          :page-sizes="[5, 15, 25]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange">
      </el-pagination>
    </div>

    <div style="padding: 10px">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">删除</el-button>
      <!--      <el-button type="primary">导入</el-button>-->
      <el-button type="primary">导出</el-button>
    </div>

    <el-dialog v-model="dialogVisible" title="Tips" width="40%" :before-close="handleClose">
      <el-form label-width="120px" :model="form">
        <el-form-item label="用户ID">
          <el-input autosize v-model="form.authorSteamid" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="语言">
          <el-input autosize type="textarea" v-model="form.language" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="时间">
          <el-input autosize type="textarea" v-model="form.timestampCreated" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input autosize type="textarea" v-model="form.review" style="width: 80%"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确认</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="show_Com" title="详细描述" width="60%" :before-close="handleClose">
      <el-form label-width="120px" :model="form">
        <el-form-item>
          userID：{{form.authorSteamid}}<br/>
          语言：{{form.language}}<br/>
          内容：
          <el-input autosize type="textarea" v-model="form.review" style="width: 80%"></el-input><br/>
          评论时间：{{form.timestampCreated}}
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="show_check">确认</el-button>
        </span>
      </template>
    </el-dialog>

  </div>
</template>

<script>
import request from "../utils/request";
// import {ref} from 'vue'

export default {
  name: 'comment',
  components: {},
  data() {
    return {
      form: {},
      list_form: [],
      dialogVisible: false,
      show_Com: false,
      search: '',
      total: 0,
      currentPage: 1,
      activeName: '1',
      pageSize: 5,
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/app_comment", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
      request.get("/app", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        console.log(res)
        for (let i = 0, len = this.tableData.length; i < len; i++) {
          this.tableData[i]['headerImage'] = res.data.records[i]['headerImage']
          this.tableData[i]['name'] = res.data.records[i]['name']
        }
        // this.tableData.headerImage = res.data.records.headerImage
        // this.tableData.name = res.data.records.name
        this.total = res.data.total
      })
    },
    add() {
      this.form = {}
      this.dialogVisible = true;
    },
    save() {
      if (this.form.id) {
        request.put("/app_comment", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
        })
      } else {
        request.post("/app_comment", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
        })
      }
      this.dialogVisible = false;
    },
    show_check() {
      this.dialogVisible = false
      this.show_Com = false
    },
    showComment(row) {
      this.form = JSON.parse(JSON.stringify((row)))
      this.show_Com = true
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify((row)))
      this.dialogVisible = true
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.load()
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/app_comment/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()
      })
    }
  }
}
</script>
