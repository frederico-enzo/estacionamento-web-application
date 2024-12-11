<template>
  <div class="container-fluid p-4">
    <div class="row mt-5">
      <div class="col-md-12 text-center">
        <p v-if="form == undefined" class="fs-5">Cadastrar Condutor</p>
        <p v-if="form == 'editar'" class="fs-5">Editar Condutor</p>
        <p v-if="form == 'toggle' && condutor.ativo" class="fs-5">Desativar Condutor</p>
        <p v-if="form == 'toggle' && !condutor.ativo" class="fs-5">Ativar Condutor</p>
      </div>
    </div>
    <AvisoComponent :ativo="mensagem.ativo" :sucesso="mensagem.status" :mensagem="mensagem.mensagem"></AvisoComponent>
    <div class="d-flex flex-column align-items-center justify-content-center gap-3">
      <div class="form-floating mb-3 col-8">
        <input id="nome" type="text" :disabled="form === 'toggle' ? '' : disabled"
          class="form-control rounded-3 input-interativo" v-on:keyup.enter="onClickCadastrar()"
          v-model="condutor.nome" />
        <label for="nome" class="form-label">Nome do Condutor</label>
      </div>
      <div class="form-floating mb-3 col-8">
        <input id="telefone" type="text" :disabled="form === 'toggle' ? '' : disabled"
          class="form-control rounded-3 input-interativo" v-maska v-on:keyup.enter="onClickCadastrar()"
          data-maska="(##) # ####-####" v-model="condutor.telefone" />
        <label for="telefone" class="form-label">Telefone</label>
      </div>
      <div class="form-floating mb-3 col-8">
        <input id="cpf" type="text" :disabled="form === 'toggle' ? '' : disabled"
          class="form-control rounded-3 input-interativo" v-maska v-on:keyup.enter="onClickCadastrar()"
          data-maska="###.###.###-##" v-model="condutor.cpf" />
        <label for="cpf" class="form-label">CPF do Condutor</label>
      </div>
    </div>
    <div class="row d-flex justify-content-center gap-3">
      <div class="col-md-3">
        <div class="d-grid gap-2">
          <router-link type="button" class="btn btn-secondary btn-lg rounded-3 btn-back-interativo"
            to="/condutor">Voltar</router-link>
        </div>
      </div>
      <div class="col-md-3">
        <div class="d-grid gap-2">
          <button v-if="form === undefined" type="button" class="btn btn-primary btn-lg rounded-3 btn-interativo"
            @click="onClickCadastrar()">Cadastrar</button>
          <button v-if="form === 'editar'" type="button" class="btn btn-warning btn-lg rounded-3 btn-interativo"
            @click="onClickEditar()">Editar</button>
          <button v-if="form === 'toggle' && condutor.ativo === true" type="button"
            class="btn btn-danger btn-lg rounded-3 btn-interativo" @click="onClickExcluir()">Excluir</button>
          <button v-if="form === 'toggle' && condutor.ativo === false" type="button"
            class="btn btn-success btn-lg rounded-3 btn-interativo" @click="onClickAtivar()">Ativar</button>
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
        .addCondutor(this.condutor)
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
        .getCondutorById(id)
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
        .updateCondutor(this.condutor)
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
          .updateCondutor(this.condutor)
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
          .deactivateCondutor(this.condutor.id)
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
.container-fluid {
  margin-top: 15vh;
  width: 50vw;
  height: 50vh;
  background-color: #f7f7f8;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.input-interativo {
  transition: all 0.3s ease;
}

.input-interativo:focus {
  border-color: #0078d4;
  box-shadow: 0 0 5px rgba(0, 120, 212, 0.7);
  transform: scale(1.05);
}

.btn-back-interativo {
  border: none;
  border-radius: none;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.btn-back-interativo:hover {
  border-radius: none;
  background-color: #0078d4;
  ;
  transform: scale(1.05);
}

.btn-interativo {
  border: none;
  border-radius: none;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.btn-interativo:hover {
  border-radius: none;
  background-color: #009c56;
  transform: scale(1.05);
}

.v-mensagem {
  transition: opacity 0.3s ease-in-out;
}

.col-md-8 {
  width: 100%;
}

.mt-5 {
  margin-top: 2rem;
}
</style>
