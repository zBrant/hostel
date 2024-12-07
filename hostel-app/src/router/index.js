import { createRouter, createWebHashHistory } from 'vue-router'
import DefaultView from "@/views/pages/HomeView.vue";

const routes = [
  {
    path: '/home',
    name: 'home',
    component: DefaultView,
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/pages/intern/AboutView.vue')
  },
  {
    path: '/sign-up',
    name: 'sign-up',
    component: () => import(/* webpackChunkName: "about" */ '@/views/authentication/SignUpView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '@/views/authentication/LoginView.vue')
  },
  {
    path: '/register-room',
    name: 'register-room',
    component: () => import(/* webpackChunkName: "about" */ '@/views/pages/intern/RegisterRoomView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
