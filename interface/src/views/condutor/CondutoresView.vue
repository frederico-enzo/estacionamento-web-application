<template>
  <div class="container-fluid p-4">
    <div class="row mb-4">
      <div class="col-md-10 text-start">
        <p class="fs-4 fw-semibold">Condutores</p>
      </div>
      <div class="col-md-2 align-self-center">
        <div class="d-grid gap-2">
          <router-link type="button" class="btn btn-primary" to="/condutor/formulario">Cadastrar</router-link>
        </div>
      </div>
    </div>
    <div v-if="condutoresList.length == 0" class="alert alert-secondary" role="alert">
      Nenhum condutor encontrado
    </div>
    <div v-else>
      <table class="table table-striped table-hover rounded-3 shadow-sm">
        <thead class="table-light">
          <tr>
            <th class="text-center">ID</th>
            <th class="text-center">Nome</th>
            <th class="text-center">CPF</th>
            <th class="text-center">Status</th>
            <th class="text-center">Opções</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in condutoresList" :key="item.id" class="hover-shadow">
            <td class="align-middle text-center">{{ item.id }}</td>
            <td class="align-middle text-center">{{ item.nome }}</td>
            <td class="align-middle text-center">{{ item.cpf }}</td>
            <td class="align-middle text-center">
              <span v-if="item.ativo" class="badge bg-success">Ativo</span>
              <span v-if="!item.ativo" class="badge bg-danger">Inativo</span>
            </td>
            <td class="align-middle text-center">
              <BotoesAcoes listarRoute="condutor.listar" editarRoute="condutor.form.editar"
                toggleRoute="condutor.form.toggle" :isAtivo="item.ativo" :id="item.id"></BotoesAcoes>
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
      condutoresList: new Array<Condutor>()
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
        .lista()
        .then(sucess => {
          this.condutoresList = sucess
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
  background-color: #f7f7f8;
  border-radius: 10px;
  padding: 2rem;
}

.table {
  border-radius: 12px;
  background-color: #fff;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.table-light {
  background-color: #f5f5f5;
}

.table-striped tbody tr:nth-child(odd) {
  background-color: #f9f9f9;
}

.table-hover tbody tr:hover {
  background-color: #eef1f7;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.table th {
  border-bottom: 2px solid #ccc;
}

.alert-secondary {
  background-color: #e4e6eb;
  border-radius: 8px;
  font-size: 0.9rem;
}

.btn-primary {
  background-color: #0078d4;
  border: none;
  border-radius: 5px;
  font-weight: 500;
  transition: all 0.3s ease-in-out;
}

.btn-primary:hover {
  background-color: #005a9e;
}

.badge {
  font-size: 0.875rem;
  font-weight: 500;
  padding: 0.4rem 0.6rem;
  border-radius: 12px;
}

.badge.bg-success {
  background-color: #4CAF50;
}

.badge.bg-danger {
  background-color: #f44336;
}

.hover-shadow:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

.fs-4 {
  font-size: 1.25rem;
}

.fw-semibold {
  font-weight: 600;
}
</style>
