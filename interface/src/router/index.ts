import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue')
  },

  {
    path: '/condutores',
    name: 'condutores',
    component: () => import( '../views/condutor/CondutoresView.vue')
  },
  {
    path: '/condutor',
    name: 'condutor.listar',
    component: () => import( '../views/condutor/CondutorInfoView.vue')
  },
  {
    path: '/condutor/formulario',
    name: 'condutor.form',
    component: () => import( '../views/condutor/CondutoresFormView.vue'),
    children: [
    {
      path: '/condutor/formulario',
      name: 'condutor.form.editar',
      component: () => import( '../views/condutor/CondutoresFormView.vue'),
    },
    {
      path: '/condutor/formulario',
      name: 'condutor.form.toggle',
      component: () => import( '../views/condutor/CondutoresFormView.vue'),
    }]
  },

  {
    path: '/veiculos',
    name: 'veiculos',
    component: () => import( '../views/veiculo/VeiculosView.vue')
  },
  {
    path: '/veiculo',
    name: 'veiculo.listar',
    component: () => import( '../views/veiculo/VeiculoInfoView.vue')
  },
  {
    path: '/veiculo/formulario',
    name: 'veiculo.form',
    component: () => import( '../views/veiculo/VeiculosFormView.vue'),
    children: [
      {
        path: '/veiculo/formulario',
        name: 'veiculo.form.editar',
        component: () => import( '../views/veiculo/VeiculosFormView.vue'),
      },
      {
        path: '/veiculo/formulario',
        name: 'veiculo.form.toggle',
        component: () => import( '../views/veiculo/VeiculosFormView.vue'),
      }
    ]
  },

  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
