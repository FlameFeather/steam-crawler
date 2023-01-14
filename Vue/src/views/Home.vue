<template>
  <div style="padding: 10px">
    <div>
      <el-input v-model="search" placeholder="请输入关键词" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
    </div>


    <el-table :data="tableData" stripe style="width: 100%" height="500">
      <el-table-column prop="headerImage" label="封面" sortable>
        <template #default="scope">
          <el-image :src="scope.row.headerImage" :initial-index="1" lazy>详细</el-image>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="steamAppid" label="steamID" sortable/>
      <el-table-column prop="type" label="类型" sortable/>
      <el-table-column prop="name" label="游戏名" sortable/>
      <!--      <el-table-column prop="detailedDescription" label="详细描述" sortable/>-->
      <el-table-column label="详细描述" sortable>
        <template #default="scope">
          <el-button size="small" @click="showDescription(scope.row)">详细</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="supportedLanguages" label="支持语言" sortable/>
      <el-table-column prop="developers" label="开发商" sortable/>
      <el-table-column prop="releaseDate" label="发售日期" sortable/>
      <el-table-column prop="price" label="价格" sortable/>
      <!--      <el-table-column prop="pcRequirementsMinimum" label="最低配置" sortable/>-->
      <!--      <el-table-column prop="pcRequirementsRecommended" label="推荐配置" sortable/>-->
      <el-table-column label="配置" sortable>
        <template #default="scope">
          <el-button size="small" @click="showRequirements(scope.row)">详细</el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="Operations" width="200">
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
          :page-sizes="[6, 15, 25]"
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
        <el-form-item label="类型">
          <el-input v-model="form.type" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="游戏名">
          <el-input v-model="form.name" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="详细描述">
          <el-input autosize v-model="form.detailedDescription" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="支持语言">
          <el-input autosize type="textarea" v-model="form.supportedLanguages" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="封面">
          <el-input autosize type="textarea" v-model="form.headerImage" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="开发商">
          <el-input type="textarea" v-model="form.developers" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="发售日期">
          <el-input type="textarea" v-model="form.releaseDate" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input type="textarea" v-model="form.price" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="最低配置">
          <el-input autosize type="textarea" v-model="form.pcRequirementsMinimum" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="推荐配置">
          <el-input autosize type="textarea" v-model="form.pcRequirementsRecommended" style="width: 80%"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确认</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="show_Dec" title="详细描述" width="60%" :before-close="handleClose">
      <el-form label-width="120px" :model="form">
        <el-form-item>
          <el-input autosize type="textarea" v-model="form.detailedDescription" style="width: 80%"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="show_check">确认</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="show_Req" title="配置" width="30%" :before-close="handleClose">
      <div style="margin-left:15%" v-html="form.pcRequirementsMinimum"></div>
      <br/><br/>
      <div style="margin-left:15%" v-html="form.pcRequirementsRecommended"></div>
<!--      <el-form label-width="120px" :model="form">-->
<!--        <el-form-item label="最低配置">-->
<!--          <el-input autosize placeholder="无最低配置" type="textarea" v-model="form.pcRequirementsMinimum"-->
<!--                    style="width: 80%"></el-input>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <el-form label-width="120px" :model="form">-->
<!--        <el-form-item label="推荐配置">-->
<!--          <el-input autosize placeholder="无推荐配置" type="textarea" v-model="form.pcRequirementsRecommended"-->
<!--                    style="width: 80%"></el-input>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
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

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      srcList: [],
      form: {},
      dialogVisible: false,
      show_Dec: false,
      show_Req: false,
      search: '',
      total: 0,
      currentPage: 1,
      pageSize: 6,
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/app", {
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
    },
    add() {
      this.form = {}
      this.dialogVisible = true;
    },
    save() {
      if (this.form.id) {
        request.put("/app", this.form).then(res => {
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
      }
      else {
        request.post("/app", this.form).then(res => {
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
      this.show_Dec = false
      this.show_Req = false
    },
    showDescription(row) {
      this.form = JSON.parse(JSON.stringify((row)))
      this.show_Dec = true
    },
    showRequirements(row) {
      this.form = JSON.parse(JSON.stringify((row)))
      this.show_Req = true
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
      request.delete("/app/" + id).then(res => {
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
