<template>
  <div class="container p-4 rounded shadow-sm bg-light">
    <div class="row mb-4">
      <div class="col text-start">
        <h4 class="text-primary">Detalhes do Condutor</h4>
      </div>
    </div>

    <div class="row mb-3">
      <div class="col-12 col-md-6">
        <p class="fw-bold text-secondary"><span class="dado">Placa: </span> {{ veiculo.placa }} </p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-12 col-md-6">
        <p class="fw-bold text-secondary"><span class="dado">Modelo: </span> {{ modelo.nome }} - {{ marca.nome }} </p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-12 col-md-6">
        <p class="fw-bold text-secondary"><span class="dado">Tipo: </span> {{ veiculo.tipo }}</p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-12 col-md-6">
        <p class="fw-bold text-secondary"><span class="dado">Cor: </span> {{ veiculo.cor }}</p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-12">
        <p class="fw-bold text-secondary"><span class="dado"> Status: </span>
          <span v-if="veiculo.ativo" class="badge text-bg-success">
            Ativo
          </span>
          <span v-if="!veiculo.ativo" class="badge text-bg-danger">
            Desativado
          </span>
        </p>
      </div>
    </div>

    <hr class="my-4">

    <div class="row mb-4">
      <div class="col text-start">
        <h5 class="text-primary">Histórico</h5>
      </div>
    </div>

    <div class="row mb-3">
      <div class="col-12 col-md-6">
        <p class="fw-bold text-secondary">Data do Cadastro: <span class="fw-normal text-dark">{{ formatDate(veiculo.cadastro) }}</span></p>
      </div>
      <div class="col-12 col-md-6">
        <p class="fw-bold text-secondary">Última Edição: <span class="fw-normal text-dark">{{ formatDate(veiculo.edicao) }}</span></p>
      </div>
    </div>

    <div class="row">
      <div class="col d-flex justify-content-end">
        <router-link class="btn btn-secondary" to="/veiculo">Voltar</router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { VeiculoClient } from '@/service/Veiculo.client'
import { Marca } from '@/model/Marca'
import { Modelo } from '@/model/Modelo'
import { Veiculo } from '@/model/Veiculo'
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'VeiculoInfo',
  data(): any {
    return {
      marca: new Marca(),
      modelo: new Modelo(),
      veiculo: new Veiculo()
    }
  },
  computed: {
    id(): any {
      return this.$route.query.id
    }
  },
  mounted() {
    if (this.id !== undefined) {
      this.findById(Number(this.id))
    }
  },
  methods: {
    formatDate(dateString: string | number | Date) {
      if (dateString != null) {
        const dateTime = new Date(dateString)
        const formattedDate = dateTime.toLocaleDateString()
        const formattedTime = dateTime.toLocaleTimeString([], {
          hour: '2-digit',
          minute: '2-digit'
        })
        return `${formattedDate} ${formattedTime}`
      } else {
        return '---'
      }
    },
    findById(id: number) {
      const veiculoClient = new VeiculoClient()
      veiculoClient
        .findById(id)
        .then(sucess => {
          this.veiculo = sucess
          this.modelo = sucess.modelo
          this.marca = sucess.modelo.marca
        })
        .catch(error => {
          this.mensagem.mensagem = error.response.data
          this.mensagem.status = false
          this.mensagem.ativo = true
        })
    }
  }
})
</script>

<style scoped lang="scss">
.container {
  background-color: #ffffff;
  border: 1px solid #dee2e6;
}

h4,
h5 {
  font-weight: bold;
}

p {
  margin-bottom: 0.5rem;
}

.badge {
  font-size: 1rem;
}

hr {
  border-top: 2px solid #dee2e6;
}

@media (max-width: 767px) {
  .container {
    padding: 1rem;
  }

  h4, h5 {
    font-size: 1.1rem;
  }

  .badge {
    font-size: 0.9rem;
  }
}
</style>
