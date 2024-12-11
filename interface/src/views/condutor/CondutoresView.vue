<template>
  <div class="container-fluid p-4">
    <div class="row mb-4">
      <div class="col-md-10 text-start">
        <p class="fs-4 fw-semibold">Condutores</p>
      </div>
      <div class="col-md-2 align-self-center">
        <div class="d-grid gap-2">
          <router-link type="button" class="btn btn-primary" to="/condutores/formulario">Cadastrar</router-link>
        </div>
      </div>
    </div>

    <div v-if="List.length === 0" class="alert alert-secondary" role="alert">
      Nenhum condutor encontrado
    </div>

    <div v-else>
      <table class="table table-striped table-hover rounded-3 shadow-sm">
        <thead class="table-light sticky-top shadow">
          <tr>
            <th class="text-center">ID</th>
            <th class="text-center">Nome</th>
            <th class="text-center">CPF</th>
            <th class="text-center">Status</th>
            <th class="text-center">Opções</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in List" :key="item.id" class="align-middle">
            <td class="text-center fw-semibold">{{ item.id }}</td>
            <td class="text-center">{{ item.nome }}</td>
            <td class="align-middle text-center">{{ item.cpf }}</td>
            <td class="text-center">
              <span v-if="item.ativo" class="badge bg-success">
                <i class="bi bi-check-circle-fill me-1"></i> Ativo
              </span>
              <span v-if="!item.ativo" class="badge bg-danger">
                <i class="bi bi-x-circle-fill me-1"></i> Inativo
              </span>
            </td>
            <td class="text-center">
              <BotoesAcoes listarRoute="condutor.listar" editarRoute="condutor.form.editar" toggleRoute="condutor.form.toggle"
                :isAtivo="item.ativo" :id="item.id">
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
import BotoesAcoes from '@/components/BotoesAcoes.vue'
import { Condutor } from "@/model/Condutor"
import { CondutorClient } from "@/service/Condutor.client"

export default defineComponent({
  name: 'CondutoresView',
  data() {
    return {
      List: new Array<Condutor>()
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
      const condutorClient = new CondutorClient()
      condutorClient
        .getAllCondutores()
        .then(sucess => {
          this.List = sucess
        })
        .catch(error => {
          console.log("erro");
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