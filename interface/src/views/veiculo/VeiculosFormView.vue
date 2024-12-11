<template>
  <div class="container-fluid p-4">
    <div class="row mt-5">
      <div class="col-12 text-center">
        <p v-if="form == undefined" class="fs-5">Cadastrar Veículo</p>
        <p v-if="form == 'editar'" class="fs-5">Editar Veículo</p>
        <p v-if="form == 'toggle' && veiculo.ativo" class="fs-5">Desativar Veículo</p>
        <p v-if="form == 'toggle' && !veiculo.ativo" class="fs-5">Ativar Veículo</p>
      </div>
    </div>

    <AvisoComponent :ativo="mensagem.ativo" :sucesso="mensagem.status" :mensagem="mensagem.mensagem" />

    <div class="d-flex flex-column align-items-center justify-content-center gap-3">
      <div class="form-floating mb-3 col-12 col-md-8">
        <input id="placa" type="text" :disabled="form === 'toggle' ? '' : disabled"
          class="form-control rounded-3 input-interativo" v-maska v-model="veiculo.placa"
          data-maska="***-****" @change="formataPlaca()" v-on:keyup.enter="onClickCadastrar()" />
        <label for="placa" class="form-label">Placa do Veículo</label>
      </div>

      <div class="form-floating mb-3 col-12 col-md-8">
        <select id="modelo" :disabled="form === 'toggle' ? '' : disabled"
          class="form-select rounded-3 input-interativo" v-model="veiculo.modelo">
          <option value="" selected>Selecione um modelo</option>
          <option v-for="modelo in modelos" :value="modelo" :key="modelo.id">
            {{ modelo.nome }} - {{ modelo.marca.nome }}
          </option>
        </select>
        <label for="modelo" class="form-label">Modelo</label>
      </div>

      <div class="form-floating mb-3 col-12 col-md-8">
        <input id="ano" type="number" :disabled="form === 'toggle' ? '' : disabled"
          class="form-control rounded-3 input-interativo" v-model="veiculo.ano"
          v-on:keyup.enter="onClickCadastrar()" />
        <label for="ano" class="form-label">Ano</label>
      </div>

      <div class="form-floating mb-3 col-12 col-md-8">
        <select id="cor" :disabled="form === 'toggle' ? '' : disabled"
          class="form-select rounded-3 input-interativo" v-model="veiculo.cor">
          <option value="" selected>Selecione uma cor</option>
          <option v-for="cor in cores" :value="cor" :key="cor.id">{{ cor }}</option>
        </select>
        <label for="cor" class="form-label">Cor</label>
      </div>

      <div class="form-floating mb-3 col-12 col-md-8">
        <select id="tipo" :disabled="form === 'toggle' ? '' : disabled"
          class="form-select rounded-3 input-interativo" v-model="veiculo.tipo">
          <option value="" selected>Selecione um tipo</option>
          <option v-for="tipo in tipos" :value="tipo" :key="tipo.id">{{ tipo }}</option>
        </select>
        <label for="tipo" class="form-label">Tipo</label>
      </div>
    </div>

    <div class="d-flex justify-content-center gap-3 mt-4 flex-wrap">
      <router-link type="button" class="btn btn-secondary" to="/veiculo">Voltar</router-link>
      <button v-if="form === undefined" class="btn btn-primary" @click="onClickCadastrar()">Cadastrar</button>
      <button v-if="form === 'editar'" class="btn btn-warning" @click="onClickEditar()">Editar</button>
      <button v-if="form === 'toggle' && veiculo.ativo" class="btn btn-danger" @click="onClickExcluir()">Desativar</button>
      <button v-if="form === 'toggle' && !veiculo.ativo" class="btn btn-success" @click="onClickAtivar()">Ativar</button>
    </div>
  </div>
</template>

<style scoped>
.container-fluid {
  margin-top: 5vh;
  width: 100%;
  max-width: 600px;
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
  border-radius: 8px;
  transition: all 0.3s ease;
}

.btn-back-interativo:hover {
  background-color: #0078d4;
  transform: scale(1.05);
}

.btn-interativo {
  border: none;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.btn-interativo:hover {
  background-color: #009c56;
  transform: scale(1.05);
}

.v-mensagem {
  transition: opacity 0.3s ease-in-out;
}

.mt-5 {
  margin-top: 2rem;
}
</style>


<script lang="ts">
import { MaskInput, vMaska } from "maska"
import AvisoComponent from '@/components/AvisoComponent.vue'
import { defineComponent } from 'vue'
import { Modelo } from '@/model/Modelo'
import { ModeloClient } from '@/service/Modelo.client'
import { Veiculo } from '@/model/Veiculo'
import { VeiculoClient } from '@/service/Veiculo.client'
import { Cor } from '@/model/Cor'
import { Tipo } from '@/model/Tipo'
export default defineComponent({
  name: 'VeiculoFormulario',
  data(): any {
    return {
      veiculo: new Veiculo(),
      modelos: [] as Modelo[],
      cores: Object.values(Cor),
      tipos: Object.values(Tipo),
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
    },
  },
  mounted() {
    new MaskInput("[data-maska]")
    if (this.id !== undefined) {
      this.findById(Number(this.id))
    }
    this.fetchModelos()
  },
  methods: {
    formataPlaca() {
      this.veiculo.placa = this.veiculo.placa.toUpperCase()
    },
    fetchModelos() {
      const modeloClient = new ModeloClient()
      modeloClient.listarAtivos().then(sucess => {
        this.modelos = sucess
      }).catch(error => {
        this.mensagem.mensagem = error.response.data
        this.mensagem.status = false
        this.mensagem.ativo = true
      })
    },
    onClickCadastrar() {
      const veiculoClient = new VeiculoClient()
      veiculoClient
        .cadastrar(this.veiculo)
        .then(sucess => {
          this.veiculo = new Veiculo()
          this.mensagem.mensagem = sucess
          this.mensagem.status = true
          this.mensagem.ativo = true
        })
        .catch(error => {
          if (typeof (error.response.data) == 'object') {
            this.mensagem.mensagem = Object.values(error.response.data)[0]
          } else {
            if (this.veiculo.modelo == "" || this.veiculo.cor == "" || this.veiculo.tipo == "") {
              this.mensagem.mensagem = "Selecione todas as opções!"
            } else {
              this.mensagem.mensagem = error.response.data
            }
          }
          this.mensagem.status = false
          this.mensagem.ativo = true
        })
    },
    findById(id: number) {
      const veiculoClient = new VeiculoClient()
      veiculoClient
        .findById(id)
        .then(sucess => {
          this.veiculo = sucess
        })
        .catch(error => {
          this.mensagem.mensagem = error.response.data[0]
          this.mensagem.status = false
          this.mensagem.ativo = true
        })
    },
    onClickEditar() {
      const veiculoClient = new VeiculoClient()
      veiculoClient
        .atualizar(this.veiculo)
        .then(sucess => {
          this.mensagem.mensagem = sucess
          this.mensagem.status = true
          this.mensagem.ativo = true
        })
        .catch(error => {
          if (this.veiculo.modelo == "" || this.veiculo.cor == "" || this.veiculo.tipo == "") {
            this.mensagem.mensagem = "Selecione todas as opções!"
          } else {
            this.mensagem.mensagem = error.response.data
          }
          this.mensagem.status = false
          this.mensagem.ativo = true
        })
    },
    onClickExcluir() {
      if (confirm("Tem certeza que deseja desativar esse veiculo?")) {
        const veiculoClient = new VeiculoClient()
        veiculoClient
          .desativar(this.veiculo.id)
          .then(sucess => {
            this.veiculo.ativo = false
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
    onClickAtivar() {
      if (confirm("Tem certeza que deseja reativar esse veiculo?")) {
        this.veiculo.ativo = true
        const veiculoClient = new VeiculoClient()
        veiculoClient
          .atualizar(this.veiculo)
          .then(sucess => {
            this.mensagem.mensagem = "Veículo reativado com sucesso!"
            this.mensagem.status = true
            this.mensagem.ativo = true
          })
          .catch(error => {
            this.mensagem.mensagem = error.response.data
            this.mensagem.status = false
            this.mensagem.ativo = true
          })
      }
    }
  }
})
</script>

