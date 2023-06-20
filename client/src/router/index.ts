import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import TableCondutor from '../views/table-condutor.vue';
import TableMarca from '../views/table-marca.vue';
import TableModelo from '../views/table-modelo.vue';
import TableVeiculo from '../views/table-veiculo.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/Estacionar',
    name: 'home',
    component: HomeView
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
