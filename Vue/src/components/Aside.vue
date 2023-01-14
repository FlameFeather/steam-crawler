<template>
  <div>
    <el-menu
        style="width: 200px;min-height: calc(100vh - 50px)"
        default-active="app"
        router
        class="el-menu-vertical-demo">
      <el-sub-menu index="1">
        <template #title>
          <svg class="icon" width="20" height="20" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg"
               data-v-365b8594="">
            <path fill="currentColor"
                  d="M800 416a288 288 0 10-576 0c0 118.144 94.528 272.128 288 456.576C705.472 688.128 800 534.144 800 416zM512 960C277.312 746.688 160 565.312 160 416a352 352 0 01704 0c0 149.312-117.312 330.688-352 544z"></path>
            <path fill="currentColor"
                  d="M512 512a96 96 0 100-192 96 96 0 000 192zm0 64a160 160 0 110-320 160 160 0 010 320z"></path>
          </svg>
          <span>app管理</span>
        </template>
        <el-menu-item index="app">app</el-menu-item>
        <el-menu-item index="app_comment">app_comment</el-menu-item>
        <el-menu-item index="app_img">app_img</el-menu-item>
        <el-menu-item index="app_t">app_t</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <svg class="icon" width="20" height="20" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg"
               data-v-365b8594="">
            <path fill="currentColor"
                  d="M800 416a288 288 0 10-576 0c0 118.144 94.528 272.128 288 456.576C705.472 688.128 800 534.144 800 416zM512 960C277.312 746.688 160 565.312 160 416a352 352 0 01704 0c0 149.312-117.312 330.688-352 544z"></path>
            <path fill="currentColor"
                  d="M512 512a96 96 0 100-192 96 96 0 000 192zm0 64a160 160 0 110-320 160 160 0 010 320z"></path>
          </svg>
          <span>用户管理</span>
        </template>
        <el-menu-item index="user">用户</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>