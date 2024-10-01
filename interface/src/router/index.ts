import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue')
  },
  {
    path: '/condutor',
    name: 'condutor.listar',
    component: () => import('../views/condutor/CondutorInfoView.vue')
  },
  {
    path: '/condutor/formulario',
    name: 'condutor.form',
    component: () => import('../views/condutor/CondutoresFormView.vue'),
    children: [
    {
      path: '/condutor/formulario',
      name: 'condutor.form.editar',
      component: () => import( '../views/condutor/CondutoresFormView.vue'),
    },
    {
      path: '/condutor/formulario',
      name: 'condutor.form.toggle',
      component: () => import('../views/condutor/CondutoresFormView.vue'),
    }]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
