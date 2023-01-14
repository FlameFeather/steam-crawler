import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import comment from "../views/comment.vue";
import img from "../views/img";

const routes = [
  {
    path: '/app',
    name: 'Home',
    component: Home
  },
  {
    path: '/app_comment',
    name: 'comment',
    component: comment
  },
  {
    path: '/app_img',
    name: 'img',
    component: img
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
