<template>
  <div class="container w-50">
    <div class="row mt-5">
      <div class="col-md-12 text-center">
        <p v-if="form == undefined" class="fs-5">Cadastrar Condutor</p>
        <p v-if="form == 'editar'" class="fs-5">Editar Condutor</p>
        <p v-if="form == 'toggle' && condutor.ativo" class="fs-5">Desativar Condutor</p>
        <p v-if="form == 'toggle' && !condutor.ativo" class="fs-5">Ativar Condutor</p>
      </div>
      <div class="col-md-2"></div>
    </div>

    <AvisoComponent :ativo="mensagem.ativo" :sucesso="mensagem.status" :mensagem="mensagem.mensagem"></AvisoComponent>
    <div class="d-flex flex-column allien-center justify-content-center align-items-center gap-2">
      <div class="form-floating mb-3 col-6 ">
        <input id="nome" type="text" :disabled="form === 'toggle' ? '' : disabled" class="form-control border border-dark"
          v-on:keyup.enter="onClickCadastrar()" v-model="condutor.nome" />
        <label for="nome" class="form-label">Nome do Condutor</label>
      </div>
      <div class="form-floating mb-3 col-6">
        <input id="telefone" type="text" :disabled="form === 'toggle' ? '' : disabled" class="form-control border border-dark " v-maska
          v-on:keyup.enter="onClickCadastrar()" data-maska="(##) # ####-####" v-model="condutor.telefone" />
        <label for="telefone" class="form-label">Telefone</label>
      </div>
      <div class="form-floating  mb-3 col-6">
        <input id="cpf" type="text" :disabled="form === 'toggle' ? '' : disabled" class="form-control border border-dark " v-maska
          v-on:keyup.enter="onClickCadastrar()" data-maska="###.###.###-##" v-model="condutor.cpf" />
        <label for="cpf" class="form-label">CPF do Condutor</label>
      </div>
    </div>

    <div class="row d-flex justify-content-center">
      <div class="col-md-3">
        <div class="d-grid gap-2">
          <router-link type="button" class="btn btn-secondary" to="/condutores">Voltar
          </router-link>
        </div>
      </div>
      <div class="col-md-3">
        <div class="d-grid gap-2">
          <button v-if="form === undefined" type="button" class="btn btn-primary" @click="onClickCadastrar()">
            Cadastrar
          </button>
          <button v-if="form === 'editar'" type="button" class="btn btn-warning" @click="onClickEditar()">
            Editar
          </button>
          <button v-if="form === 'toggle' && condutor.ativo === true" type="button" class="btn btn-danger"
            @click="onClickExcluir()">
            Excluir
          </button>
          <button v-if="form === 'toggle' && condutor.ativo === false" type="button" class="btn btn-success"
            @click="onClickAtivar()">
            Ativar
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { MaskInput, vMaska } from "maska"
new MaskInput("[data-maska]")
import AvisoComponent from '@/components/AvisoComponent.vue'
import { defineComponent } from 'vue'
import { Condutor } from "@/model/Condutor"
import { CondutorClient } from "@/service/Condutor.client"
export default defineComponent({
  name: 'CondutorFormulario',
  data(): any {
    return {
      condutor: new Condutor(),
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
  mounted() {
    new MaskInput("[data-maska]")
    if (this.id !== undefined) {
      this.findById(Number(this.id))
    }
  },
  methods: {
    onClickCadastrar() {
      const condutorClient = new CondutorClient()
      condutorClient
        .cadastrarCondutor(this.condutor)
        .then(sucess => {
          this.condutor = new Condutor()
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
      const condutorClient = new CondutorClient()
      condutorClient
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
      const condutorClient = new CondutorClient()
      condutorClient
        .editarCondutor(this.condutor)
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
    onClickAtivar() {
      if (confirm("Tem certeza que deseja reativar esse condutor?")) {

        this.condutor.ativo = true
        const condutorClient = new CondutorClient()
        condutorClient
          .editarCondutor(this.condutor)
          .then(sucess => {
            this.mensagem.mensagem = "Condutor reativado com sucesso!"
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
    onClickExcluir() {
      if (confirm("Tem certeza que deseja desativar esse condutor?")) {
        const condutorClient = new CondutorClient()
        condutorClient
          .desativarCondutor(this.condutor.id)
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

<style lang="scss">
$theme-colors: (
  'dark': #111111,
  'primary': #515151,
  'secondary': #c8c8c8,
  'info': #a4a4a4,
  'success': #198754,
  'warning': #ffc107,
  'danger': #dc3545
);

@import '~bootstrap/scss/bootstrap.scss';
</style>