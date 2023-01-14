<template>
  <div style="padding: 10px">
    <div>
      <el-input v-model="search" placeholder="请输入关键词" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
    </div>

    <el-table :data="tableData" stripe style="width: 100%" height="500">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="steamAppid" label="steamID" sortable/>
      <el-table-column prop="name" label="游戏名" sortable/>
      <!--      <el-table-column prop="detailedDescription" label="详细描述" sortable/>-->
      <el-table-column prop="headerImage" label="图片1" sortable>
        <template #default="scope">
          <el-image :src="scope.row.pit_img[0]" lazy>详细</el-image>
        </template>
      </el-table-column>
      <el-table-column prop="headerImage" label="图片2" sortable>
        <template #default="scope">
          <el-image :src="scope.row.pit_img[1]" lazy>详细</el-image>
        </template>
      </el-table-column>
      <el-table-column prop="headerImage" label="图片3" sortable>
        <template #default="scope">
          <el-image :src="scope.row.pit_img[2]" lazy>详细</el-image>
        </template>
      </el-table-column>
      <el-table-column prop="headerImage" label="图片4" sortable>
        <template #default="scope">
          <el-image :src="scope.row.pit_img[3]" lazy>详细</el-image>
        </template>
      </el-table-column>
      <el-table-column prop="headerImage" label="图片5" sortable>
        <template #default="scope">
          <el-image :src="scope.row.pit_img[4]" lazy>详细</el-image>
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

  </div>
</template>

<script>
import request from "../utils/request";

export default {
  name: 'img',
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
      request.get("/app_img", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
        console.log(this.tableData)
        for(let k=0;k<this.tableData['Target'].length();k++){
          let xx=this.tableData['Target'][k].picture.replace('[','')
          xx=xx.replace(']','')
          this.tableData['Target'][k]['pit_img']=[];
          this.tableData['Target'][k]['pit_img']=xx.split(',');
          while(this.tableData['Target'][k]['pit_img'].length()<4)
          {
            this.tableData['Target'][k]['pit_img'].append("https://img1.baidu.com/it/u=253980794,1584135832&fm=253&fmt=auto&app=138&f=JPEG?w=705&h=500")
          }
          for(let i=0;i<=4;i++)
          {
            this.tableData['Target'][k]['pit_img'][i].replace('\'','')
          }
        }
      })
    },
  }
}
</script>

<style scoped>

</style>