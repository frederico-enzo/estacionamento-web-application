<template>
  <div class="container mt-5">
    <h1 class="text-center display-6">Gestão de Estacionamento</h1>
    <div class="text-center my-4">
      <router-link class="btn btn-primary fs-5" to="/movimentacoes/formulario">
        Registrar Entrada
      </router-link>
    </div>
    <div class="table-responsive w-50 mx-auto">
      <table class="table table-bordered text-center">
        <thead class="table-secondary">
          <tr>
            <th>Carro</th>
            <th>Moto</th>
            <th>Van</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ carro }}</td>
            <td>{{ moto }}</td>
            <td>{{ van }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="mt-4">
      <h2 class="fs-5">Movimentações Abertas</h2>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Vaga</th>
              <th>Placa</th>
              <th>Condutor</th>
              <th>Data Entrada</th>
              <th>Opções</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in movimentacoesList" :key="item.id">
              <td>{{ item.id }}</td>
              <td>{{ item.veiculo.tipo }}</td>
              <td>
                {{ item.veiculo.placa }}
                <span v-if="!item.veiculo.ativo" class="badge bg-danger">Desativado</span>
              </td>
              <td>
                {{ item.condutor.nome }}
                <span v-if="!item.condutor.ativo" class="badge bg-danger">Desativado</span>
              </td>
              <td>{{ formatDate(item.dataEntrada) }}</td>
              <td>
                <BotoesAcoes :id="item.id" confirmarRoute="movimentacoes.form.confirmar" listarRoute="movimentacoes.listar" />
              </td>
            </tr>
          </tbody>
        </table>
        <div v-if="movimentacoesList.length === 0" class="alert alert-secondary">
          Nenhuma vaga ocupada no momento.
        </div>
      </div>
    </div>
  </div>
</template>


<script lang="ts">

import BotoesAcoes from '@/components/BotoesAcoes.vue'
import { defineComponent } from 'vue'
import { Movimentacao } from '@/model/Movimentacao'
import { MovimentacaoClient } from '@/service/Movimentacao.client'
import { Tipo } from '@/model/Tipo'
import { ConfiguracaoClient } from '@/service/Configuracao.client'
import { Configuracao } from '@/model/Configuracao'

export default defineComponent({
  name: 'HomeView',
  data() {
    return {
      movimentacoesList: new Array<Movimentacao>(),
      carro: 0 as number,
      moto: 0 as number,
      van: 0 as number,
      configs: new Configuracao
    }
  },
  mounted() {
    this.findAll()
    this.getVagas()
  },
  components: {
    BotoesAcoes
  },
  methods: {
    getVagas() {
      const configClient = new ConfiguracaoClient()
      const movsClient = new MovimentacaoClient()
      configClient.getConfiguracao().then(sucess => {
        this.carro = sucess.qntdCarro
        this.moto = sucess.qntdMoto
        this.van = sucess.qntdVan

        movsClient.getVagas(Tipo.CARRO).then(sucTipo => {
          this.carro -= sucTipo.length
        })
        movsClient.getVagas(Tipo.MOTO).then(sucTipo => {
          this.moto -= sucTipo.length
        })
        movsClient.getVagas(Tipo.VAN).then(sucTipo => {
          this.van -= sucTipo.length
        })
      }).catch(error => {
        this.carro = 0
        this.van = 0
        this.moto = 0
      })
    },
    findAll() {
      const movimentacaoClient = new MovimentacaoClient()
      movimentacaoClient
        .listarAbertas()
        .then(sucess => {
          this.movimentacoesList = sucess
        })
        .catch(error => {
          console.error("Erro ao listar movimentações:", error);
        });
    },
    formatDate(dateString: string | number | Date) {
      const dateTime = new Date(dateString)
      const formattedDate = dateTime.toLocaleDateString()
      const formattedTime = dateTime.toLocaleTimeString([], {
        hour: '2-digit',
        minute: '2-digit'
      })
      return `${formattedDate} ${formattedTime}`
    }
  }
})
</script>

<style>
table {
  border-collapse: separate;
  border-spacing: 0;
  border: 1px solid #ddd;

  th, td {
    vertical-align: middle;
  }
}

.btn:hover {
  background-color: #0056b3;
}

.alert {
  text-align: center;
}
</style>