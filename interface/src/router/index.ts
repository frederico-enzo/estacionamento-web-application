import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue')
  },
  {
    path: '/condutor',
    name: 'condutor',
    component: () => import('../views/condutor/CondutoresView.vue')
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
        component: () => import('../views/condutor/CondutoresFormView.vue'),
      },
      {
        path: '/condutor/formulario',
        name: 'condutor.form.toggle',
        component: () => import('../views/condutor/CondutoresFormView.vue'),
      }]
  },

  {
    path: '/veiculo',
    name: 'veiculo',
    component: () => import('../views/veiculo/VeiculosView.vue')
  },
  {
    path: '/veiculo',
    name: 'veiculo.listar',
    component: () => import('../views/veiculo/VeiculoInfoView.vue')
  },
  {
    path: '/veiculo/formulario',
    name: 'veiculo.form',
    component: () => import('../views/veiculo/VeiculosFormView.vue'),
    children: [
      {
        path: '/veiculo/formulario',
        name: 'veiculo.form.editar',
        component: () => import('../views/veiculo/VeiculosFormView.vue'),
      },
      {
        path: '/veiculo/formulario',
        name: 'veiculo.form.toggle',
        component: () => import('../views/veiculo/VeiculosFormView.vue'),
      }
    ]
  },

  {
    path: '/marca',
    name: 'marca',
    component: () => import('../views/marca/MarcasView.vue')
  },
  {
    path: '/marca',
    name: 'marca.listar',
    component: () => import('../views/marca/MarcaInfoView.vue')
  },
  {
    path: '/marca/formulario',
    name: 'marca.form',
    component: () => import('../views/marca/MarcasFormView.vue'),
    children: [
      {
        path: '/marca/formulario',
        name: 'marca.form.editar',
        component: () => import('../views/marca/MarcasFormView.vue'),
      },
      {
        path: '/marca/formulario',
        name: 'marca.form.toggle',
        component: () => import('../views/marca/MarcasFormView.vue'),
      }
    ]
  },

  {
    path: '/modelo',
    name: 'modelo',
    component: () => import('../views/modelo/ModelosView.vue')
  },
  {
    path: '/modelo',
    name: 'modelo.listar',
    component: () => import('../views/modelo/ModeloInfoView.vue')
  },
  {
    path: '/modelo/formulario',
    name: 'modelo.form',
    component: () => import('../views/modelo/ModelosFormView.vue'),
    children: [
      {
        path: '/modelo/formulario',
        name: 'modelo.form.editar',
        component: () => import('../views/modelo/ModelosFormView.vue'),
      },
      {
        path: '/modelo/formulario',
        name: 'modelo.form.toggle',
        component: () => import('../views/modelo/ModelosFormView.vue'),
      }
    ]
  },

  {
    path: '/movimentacoes',
    name: 'movimentacoes',
    component: () => import('../views/movimentacao/MovimentacoesView.vue')
  },
  {
    path: '/movimentacoes/formulario',
    name: 'movimentacoes.form',
    component: () => import('../views/movimentacao/MovimentacoesFormView.vue'),
    children: [
      {
        path: '/movimentacoes/formulario',
        name: 'movimentacoes.form.editar',
        component: () => import('../views/movimentacao/MovimentacoesFormView.vue'),
      },
      {
        path: '/movimentacoes/formulario',
        name: 'movimentacoes.form.toggle',
        component: () => import('../views/movimentacao/MovimentacoesFormView.vue'),
      }
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
