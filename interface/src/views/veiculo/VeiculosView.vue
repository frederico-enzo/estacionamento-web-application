<template>
  <div class="container-fluid p-4">
    <div class="row mb-4">
      <div class="col-md-10 text-start">
        <p class="fs-4 fw-semibold">Veiculos</p>
      </div>
      <div class="col-md-2 align-self-center">
        <div class="d-grid gap-2">
          <router-link type="button" class="btn btn-primary" to="/veiculo/formulario">Cadastrar</router-link>
        </div>
      </div>
    </div>

    <div v-if="List.length === 0" class="alert alert-secondary" role="alert">
      Nenhum veiculo encontrado
    </div>
    <div v-else>
      <table class="table table-striped table-hover rounded-3 shadow-sm">
        <thead class="table-light sticky-top shadow">
          <tr>
            <th class="text-center">ID</th>
            <th class="text-center">Placa</th>
            <th class="text-center">Modelo</th>
            <th class="text-center">Ano</th>
            <th class="text-center">Cor</th>
            <th class="text-center">Tipo</th>
            <th class="text-center">Status</th>
            <th class="text-center">Opções</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in List" :key="item.id" class="align-middle">
            <td class="text-center fw-semibold">{{ item.id }}</td>
            <td class="text-center">{{ item.placa }}</td>
            <td class="align-middle text-center">{{ item.modelo.nome }}</td>
            <td class="text-center">{{ item.ano }}</td>
            <td class="text-center">{{ item.cor }}</td>
            <td class="text-center">{{ item.tipo }}</td>

            <td class="text-center">
              <span v-if="item.ativo" class="badge bg-success">
                <i class="bi bi-check-circle-fill me-1"></i> Ativo
              </span>
              <span v-if="!item.ativo" class="badge bg-danger">
                <i class="bi bi-x-circle-fill me-1"></i> Inativo
              </span>
            </td>
            <td class="text-center">
              <BotoesAcoes listarRoute="veiculo.listar" editarRoute="veiculo.form.editar"
                toggleRoute="veiculo.form.toggle" :isAtivo="item.ativo" :id="item.id">
              </BotoesAcoes>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from 'vue'
import { Veiculo } from '@/model/Veiculo'
import { VeiculoClient } from '@/service/Veiculo.client'
import BotoesAcoes from '@/components/BotoesAcoes.vue'

export default defineComponent({
  name: 'VeiculosView',
  data() {
    return {
      List: new Array<Veiculo>()
    }
  },
  mounted() {
    this.findAll()
  },
  components: {
    BotoesAcoes
  },
  methods: {
    findAll() {
      const client = new VeiculoClient()
      client
        .listarAll()
        .then(sucess => {
          this.List = sucess
        })
        .catch(error => {
          console.log("Error!!")
        })
    }
  }
})
</script>

<style scoped>
.container-fluid {
  background-color: #f9fafb;
  border-radius: 12px;
  padding: 2rem;
}

.fs-4 {
  color: #0d6efd;
  font-weight: bold;
}

.table {
  border-radius: 8px;
  overflow: hidden;
}

.table-hover tbody tr:hover {
  background-color: #f1f5f9;
  transition: background-color 0.3s ease;
}

.table-light {
  background-color: #f8f9fa;
  font-weight: bold;
}

.sticky-top {
  position: sticky;
  top: 0;
  z-index: 2;
}

.badge {
  font-size: 0.9rem;
  padding: 0.4em 0.6em;
  border-radius: 6px;
}

.badge .bi {
  vertical-align: -0.125em;
  margin-right: 0.25rem;
}

.btn-primary {
  background-color: #0d6efd;
  border: none;
  border-radius: 8px;
  font-weight: bold;
  transition: all 0.3s ease-in-out;
}

.btn-primary:hover {
  background-color: #0a58ca;
}
</style>