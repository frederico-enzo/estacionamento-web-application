<template>
  <div class="container w-75 p-4 rounded shadow-sm bg-light">
      <div class="row mb-4">
          <div class="col text-start">
              <h4 class="text-primary">Detalhes do Marca</h4>
          </div>
      </div>

      <div class="row mb-3">
          <div class="col">
              <p class="fw-bold text-secondary">Nome: <span class="fw-normal text-dark">{{ marca.nome }}</span></p>
          </div>
     
      </div>
      <div class="row mb-4">
          <div class="col">
              <p class="fw-bold text-secondary">Status:
                  <span v-if="marca.ativo" class="badge bg-success">Ativo</span>
                  <span v-else class="badge bg-danger">Desativado</span>
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
          <div class="col">
              <p class="fw-bold text-secondary">Data do Cadastro: <span class="fw-normal text-dark">{{
                  formatDate(marca.cadastro) }}</span></p>
          </div>marca
          <div class="col">
              <p class="fw-bold text-secondary">Última Edição: <span class="fw-normal text-dark">{{
                  formatDate(marca.edicao) }}</span></p>
          </div>
      </div>

      <div class="row">
          <div class="col d-flex justify-content-end">
              <router-link class="btn btn-secondary" to="/marca">Voltar</router-link>
          </div>
      </div>
  </div>
</template>

  <script lang="ts">
  import { MarcaClient } from '@/service/Marca.client'
  import { Marca } from '@/model/Marca'
  import { defineComponent } from 'vue'
  
  export default defineComponent({
    name: 'MarcaInfo',
    data(): any {
      return {
        marca: new Marca(),
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
        const marcaClient = new MarcaClient()
        marcaClient
          .findById(id)
          .then(sucess => {
            this.marca = sucess
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
</style>
