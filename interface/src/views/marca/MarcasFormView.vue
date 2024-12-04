<template>
    <div class="container-form">
      <div class="header text-center">
        <h2>{{ tituloFormulario }}</h2>
      </div>
  
      <AvisoComponent :ativo="mensagem.ativo" :sucesso="mensagem.status" :mensagem="mensagem.mensagem" />
  
      <div class="form-section">
        <div class="form-floating mb-3">
          <input 
            id="nome" 
            type="text" 
            :disabled="form === 'toggle'" 
            class="form-control" 
            v-on:keyup.enter="onSubmit" 
            v-model="marca.nome" 
          />
          <label for="nome">Nome da Marca</label>
        </div>
      </div>
  
      <div class="actions d-flex justify-content-center gap-3">
        <router-link class="btn btn-secondary" to="/marca">Voltar</router-link>
        <button
          v-if="form === undefined"
          class="btn btn-primary"
          @click="onClickCadastrar"
        >
          Cadastrar
        </button>
        <button
          v-if="form === 'editar'"
          class="btn btn-warning"
          @click="onClickEditar"
        >
          Editar
        </button>
        <button
          v-if="form === 'toggle' && marca.ativo"
          class="btn btn-danger"
          @click="onClickExcluir"
        >
          Desativar
        </button>
        <button
          v-if="form === 'toggle' && !marca.ativo"
          class="btn btn-success"
          @click="onClickAtivar"
        >
          Ativar
        </button>
      </div>
    </div>
  </template>
  

<script lang="ts">

import AvisoComponent from '@/components/AvisoComponent.vue'
import { defineComponent } from 'vue'
import { Marca } from "@/model/Marca"
import { MarcaClient } from "@/service/Marca.client"

export default defineComponent({
    name: 'MarcaFormulario',
    data(): any {
        return {
            marca: new Marca(),
            mensagem: {
                ativo: false as boolean,
                status: false as boolean,
                mensagem: '' as string
            }
        }
    },
    components: {
        AvisoComponent
    },
    computed: {
        id(): any {
            return this.$route.query.id
        },
        form(): any {
            return this.$route.query.form
        }
    },
    methods: {
        onClickCadastrar() {
            const client = new MarcaClient()
            client
                .cadastrarMarca(this.marca)
                .then(sucess => {
                    this.model = new Marca()
                    this.mensagem.mensagem = sucess
                    this.mensagem.status = true
                    this.mensagem.ativo = true
                })
                .catch(error => {
                    if (typeof (error.response.data) == 'object') {
                        this.mensagem.mensagem = Object.values(error.response.data)[0]
                    } else {
                        this.mensagem.mensagem = error.response.data
                    }
                    this.mensagem.status = false
                    this.mensagem.ativo = true
                })
        },
        findById(id: number) {
            const client = new MarcaClient()
            client
                .findById(id)
                .then(sucess => {
                    this.condutor = sucess
                })
                .catch(error => {
                    this.mensagem.mensagem = error.response.data
                    this.mensagem.status = false
                    this.mensagem.ativo = true
                })
        },
        onClickEditar() {
            const client = new MarcaClient()
            client
                .atualizarMarca(this.marca, this.marca.id)
                .then(sucess => {
                    this.mensagem.mensagem = sucess
                    this.mensagem.status = true
                    this.mensagem.ativo = true
                })
                .catch(error => {
                    this.mensagem.mensagem = error.response.data
                    this.mensagem.status = false
                    this.mensagem.ativo = true
                })
        },
        onClickExcluir() {
            if (confirm("Tem certeza que deseja desativar esse marca?")) {
                const client = new MarcaClient()
                client
                    .desativar(this.marca.id)
                    .then(sucess => {
                        this.condutor.ativo = false
                        this.mensagem.mensagem = sucess
                        this.mensagem.status = true
                        this.mensagem.ativo = true
                    })
                    .catch(error => {
                        this.mensagem.mensagem = error.response.data
                        this.mensagem.status = false
                        this.mensagem.ativo = true
                    })
            }
        },
    }
})
</script>

<style scoped>
.container-form {
  margin: 10vh auto;
  padding: 2rem;
  max-width: 600px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.header h2 {
  margin-bottom: 2rem;
  color: #333;
}

.form-section {
  margin-bottom: 2rem;
}

.btn {
  transition: all 0.3s ease-in-out;
}

.btn:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
</style>
