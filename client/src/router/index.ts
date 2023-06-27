import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import TableCondutor from '../views/table-condutor.vue';
import TableMarca from '../views/table-marca.vue';
import TableModelo from '../views/table-modelo.vue';
import TableVeiculo from '../views/table-veiculo.vue';
import FormularioMarca from '../views/Formularios/formulario-marca.vue'
import FormularioModelo from '../views/Formularios/formulario-modelo.vue'
import FormularioMovimentacao from '../views/Formularios/formulario-movimentacao.vue'
import FormularioVeiculo from '../views/Formularios/formulario-veiculo.vue'
import FormularioCondutor from '../views/Formularios/formulario-condutor.vue'
import TableConfiguracao from '../views/table-configuracao.vue'
import FormularioConfiguracao from '../views/Formularios/formulario-configuracao.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/configuraçao',
    name: 'configuraçao',
    component: TableConfiguracao
  },
  {
    path: '/condutores',
    name: 'Condutores',
    component: TableCondutor
  },
  {
    path: '/marcas',
    name: 'Marcas',
    component: TableMarca
  },
  {
    path: '/marcas-form',
    name: 'formulario-marca',
    component: FormularioMarca
  },
  {
    path: '/modelos-form',
    name: 'formulario-modelos',
    component: FormularioModelo
  }, {
    path: '/estacionar-form',
    name: 'formulario-movimentacao',
    component: FormularioMovimentacao
  }, {
    path: '/veiculo-form',
    name: 'formulario-veiculo',
    component: FormularioVeiculo
  }, {
    path: '/condutor-form',
    name: 'formulario-condutor',
    component: FormularioCondutor
  },
  {
    path: '/configuracap-form',
    name: 'formulario-configuracao',
    component: FormularioConfiguracao
  },
  {
    path: '/modelos',
    name: 'Modelos',
    component: TableModelo
  },
  {
    path: '/veiculos',
    name: 'Veiculos',
    component: TableVeiculo
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
