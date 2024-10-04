<template>
  <div class="container w-50">
    <div class="row mt-5">
      <div class="col-md-12 text-center">
        <p v-if="form == undefined" class="fs-5">Cadastro de Veículo</p>
        <p v-if="form == 'editar'" class="fs-5">Editar Veículo</p>
        <p v-if="form == 'toggle' && veiculo.ativo" class="fs-5">Desativar Veículo</p>
        <p v-if="form == 'toggle' && !veiculo.ativo" class="fs-5">Ativar Veículo</p>
        <div class="col-md-2"></div>
      </div>

      <AvisoComponent :ativo="mensagem.ativo" :sucesso="mensagem.status" :mensagem="mensagem.mensagem"></AvisoComponent>

      <div class="row w-100 d-flex justify-content-center m-0 mb-2">
        <div class="mb-3 mt-3 w-50 text-start">
          <label for="placa" class="form-label">Placa do Veiculo</label>
          <input type="text" :disabled="form === 'toggle' ? '' : disabled" class="form-control" id="placa" v-maska
            v-on:keyup.enter="onClickCadastrar()" data-maska="***-****" v-model="veiculo.placa"
            v-on:change="formataPlaca()" />
        </div>
        <div class="mb-3 mt-3 w-50 text-start">
          <label for="categoria" class="form-label">Modelo</label>
          <select :disabled="form === 'toggle' ? '' : disabled" class="form-select" id="categoria"
            v-model="veiculo.modelo">
            <option value="" selected>Selecione um modelo</option>
            <option v-for="modelo in modelos" :value="modelo" :key="modelo.id">
              {{ modelo.nome }} - {{ modelo.marca.nome }}
            </option>
          </select>
        </div>
      </div>
      <div class="row w-100 d-flex justify-content-center m-0 mb-2">
        <div class="mb-3 mt-3 w-25 text-start">
          <label for="ano" class="form-label">Ano</label>
          <input type="number" id="ano" :disabled="form === 'toggle' ? '' : disabled" class="form-control"
            v-model="veiculo.ano" v-on:keyup.enter="onClickCadastrar()" />
        </div>
        <div class="mb-3 mt-3 w-25 text-start">
          <label for="categoria" class="form-label">Cor</label>
          <select :disabled="form === 'toggle' ? '' : disabled" class="form-select" id="categoria"
            v-model="veiculo.cor">
            <option value="" selected>Selecione uma cor</option>
            <option v-for="cor in cores" :value="cor" :key="cor.id">
              {{ cor }}
            </option>
          </select>
        </div>
        <div class="mb-3 mt-3 w-25 text-start">
          <label for="tipo" class="form-label">Tipo</label>
          <select :disabled="form === 'toggle' ? '' : disabled" class="form-select" id="tipo" v-model="veiculo.tipo">
            <option value="" selected>Selecione um tipo</option>
            <option v-for="tipo in tipos" :value="tipo" :key="tipo.id">
              {{ tipo  }}
            </option>
          </select>
        </div>
      </div>

      <div class="row d-flex justify-content-center">
        <div class="col-md-3">
          <div class="d-grid gap-2">
            <router-link type="button" class="btn btn-secondary" to="/veiculos">Voltar
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
            <button v-if="form === 'toggle' && veiculo.ativo === true" type="button" class="btn btn-danger"
              @click="onClickExcluir()">
              Excluir
            </button>
            <button v-if="form === 'toggle' && veiculo.ativo === false" type="button" class="btn btn-success"
              @click="onClickAtivar()">
              Ativar
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
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
