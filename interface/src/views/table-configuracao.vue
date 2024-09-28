<template>
    <NavBar />
  
    <div class="lestGo">
      <div class="table-tape">
        <div class="marca">
          <router-link
            :to="{ name: 'formulario-configuracao' }"
            type="button"
            class="btn btn-warning"
            >Cadastrar</router-link
          >
        </div>
        <table class="table table-bordered shadow">
          <thead>
            <tr>
              <th scope="col">Valor Hora</th>
              <th scope="col">Valor Multa Minuto</th>
              <th scope="col">Início Expediente</th>
              <th scope="col">Fim Expediente</th>
              <th scope="col">Tempo para Desconto</th>
              <th scope="col">Status</th>
              <th scope="col">Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="config in configList" :key="config.id">
              <td>
                <span v-if="!config.editMode">{{ config.valorHora }}</span>
                <input v-else v-model="config.valorHora" class="form-control" />
              </td>
              <td>
                <span v-if="!config.editMode">{{ config.valorMultaMinuto }}</span>
                <input v-else v-model="config.valorMultaMinuto" class="form-control" />
              </td>
              <td>
                <span v-if="!config.editMode">{{ config.inicioExpediente }}</span>
                <input v-else v-model="config.inicioExpediente" class="form-control" />
              </td>
              <td>
                <span v-if="!config.editMode">{{ config.fimExpediente }}</span>
                <input v-else v-model="config.fimExpediente" class="form-control" />
              </td>
              <td>
                <span v-if="!config.editMode">{{ config.tempoParaDesconto }}</span>
                <input v-else v-model="config.tempoParaDesconto" class="form-control" />
              </td>
  
              <td>
                <span v-if="config.ativo" class="btn btn-success">...</span>
                <span v-else class="btn btn-danger">...</span>
              </td>
  
              <td>
                <button
                v-if="!config.editMode"

                  @click="onClickEditar(config.id)"
                  type="button"
                  class="btn btn-warning"
                >
                  Editar
                </button>
                <button
                  v-else
                  @click="salvarEdicao(config)"
                  type="button"
                  class="btn btn-success"
                >
                  Salvar
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        <footer>©Frederico 2023</footer>
        <div v-if="mensagem.show" :class="mensagem.css" id="alert">
          {{ mensagem.mensagem }}
        </div>
      </div>
      <br />
    </div>
  </template>
  
  <script lang="ts">
  import NavBar from "../components/NavBar.vue";
  import { ConfiguracaoClient } from "@/client/Configuracao.client";
  import { Configuracao } from "@/Model/Configuracao";
  
  export default {
    components: { NavBar },
    name: "TableVeiculo",
    data() {
      return {
        config: new Configuracao(),
        configList: new Array<Configuracao>(),
        mensagem: {
          show: false,
          mensagem: "",
          css: "",
        },
      };
    },
    mounted() {
      this.findAll();
    },
    methods: {
      findAll() {
        const configuracaoClient = new ConfiguracaoClient();
        configuracaoClient
          .findAll()
          .then((data: Configuracao[]) => {
            this.configList = data;
          })
          .catch((error: Error) => {
            console.log(error);
          });
      },
      onClickEditar(id: number) {
        const config = this.configList.find((config) => config.id === id);
        if (config) {
          config.editMode = true;
        }
      },
      async salvarEdicao(config: Configuracao) {
        const configuracaoClient = new ConfiguracaoClient();
        try {
          await configuracaoClient.upDate(config.id, config);
          config.editMode = false;
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-success fade show";
          this.mensagem.mensagem = "Configuração atualizada com sucesso.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 5000);
        } catch (error) {
          console.log(error);
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-danger fade show";
          this.mensagem.mensagem = "Não foi possível atualizar a configuração.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 5000);
        }
      },
    },
  };
  </script>
  
  
  
  <style scoped>
#alert {
  margin: 15px 15px 15px 0px;
  width: 350px;
  height: 60px;
}
.marca {
  margin: 15px 0px 15px 15px;
  display: flex;
  justify-content: end;
}
footer {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40px;
  background: rgb(52, 108, 212);
  color: white;
  border-radius: 0px 0px 10px 10px;
}
thead :nth-child(1) {
  border-radius: 10px 0px 0 0;
}
thead :nth-child(8) {
  border-radius: 0px 10px 0 0;
}
.table-tape td {
  text-align: center;
  vertical-align: middle;
}
thead th {
  background: rgb(52, 108, 212);
  color: white;
  width: 10vw;
  text-align: center;
  vertical-align: middle;
}
.lestGo {
  display: flex;
  align-items: center;
  justify-content: center;
}

.table-tape {
  padding: 100px;
}
</style>
  