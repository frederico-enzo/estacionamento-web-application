<template>
  <NavBar />
  <div class="lestGo">
    <div class="table-tape">
      <div class="marca">
        <router-link :to="{name: 'formulario-modelos'}" type="button" class="btn btn-warning ">Cadastrar</router-link>
      </div>
      <table class="table table-bordered shadow">
        <thead>
          <tr>
            <th scope="col">Nome</th>
            <th scope="col">Marca</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="modelo in modelosList" :key="modelo.id">
            <td>
              <input
                v-if="modelo.editMode"
                class="form-control"
                v-model="modelo.nome"
              />
              <span v-else>{{ modelo.nome }}</span>
            </td>
            <td>
              <select
                v-if="modelo.editMode"
                v-model="modelo.marcaId"
                class="form-control"
              >
                <option
                  v-for="marca in marcasList"
                  :key="marca.id"
                  :value="marca"
                >
                  {{ marca.nome }}
                </option>
              </select>
              <span v-else>{{ modelo.marcaId.nome }} </span>
            </td>
            <td v-if="modelo.ativo">
              <span class="btn btn-success">...</span>
            </td>
            <td v-else>
              <span class="btn btn-danger">...</span>
            </td>
            <td>
              <button
                @click="salvarEdicao(modelo)"
                v-if="modelo.editMode"
                type="button"
                class="btn btn-success"
              >
                Salvar
              </button>
              <button
                v-else
                @click="onClickEditar(modelo.id)"
                type="button"
                class="btn btn-warning"
              >
                Editar
              </button>
              -
              <button
                @click="onClickExcluir(modelo.id)"
                type="button"
                class="btn btn-outline-danger"
              >
                &#x274C;
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
  </div>
</template>
  
<script lang="ts">
import { defineComponent, onUpdated } from "vue";

import NavBar from "../components/NavBar.vue";
import { ModeloClient } from "@/client/Modelo.client";
import { Modelo } from "@/Model/Modelo";
import { Marca } from "@/Model/Marca";
import { MarcaClient } from "@/client/Marca.client";
import { id } from "date-fns/locale";

export default defineComponent({
  name: "TableModelo",
  components: {
    NavBar,
  },
  data() {
    return {
      modelo: new Modelo(),
      marcasList: new Array<Marca>(),
      modelosList: new Array<Modelo>(),
      mensagem: {
        show: false,
        mensagem: "",
        css: "",
      },
    };
  },
  mounted() {
    this.findAll();
    this.carregarMarcas();
  },

  methods: {
    carregarMarcas() {
      const marcaClient = new MarcaClient();
      marcaClient
        .findAll()
        .then((sucess) => {
          this.marcasList = sucess;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    findAll() {
      const modelosClient = new ModeloClient();
      modelosClient
        .findAll()
        .then((sucess: Modelo[]) => {
          this.modelosList = sucess;
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
    onClickEditar(id: number) {
      const modelo = this.modelosList.find((modelo) => modelo.id === id);
      if (modelo) {
        modelo.editMode = true;
      }
    },
    salvarEdicao(modelo: Modelo) {
      const modeloClient = new ModeloClient();
      modeloClient
        .upDate(modelo.id, modelo)
        .then(() => {
          modelo.editMode = false;
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-success fade show";
          this.mensagem.mensagem = "Marca atualizada com sucesso.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 5000);
        })
        .catch((error: Error) => {
          console.log(error);
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-danger fade show";
          this.mensagem.mensagem = "Não foi possível atualizar a marca.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 3500);
        });
    },
    onClickExcluir(id: number) {
      const modelosClient = new ModeloClient();
      modelosClient
        .excluir(id)
        .then(() => {
          this.findAll();
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-success fade show";
          this.mensagem.mensagem = "Veiculo excluído com sucesso.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 5000);
        })
        .catch((error: Error) => {
          console.log(error);
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-danger fade show";
          this.mensagem.mensagem = "Não foi possível excluir o veiculo.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 5000);
        });
    },
  },
});
</script>

<style scoped>
#alert {
  margin: 15px 15px 15px 0px;
  width: 300px;
  height: 60px;
}
.marca{
  margin:  15px 0px 15px 15px ;
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
thead :nth-child(4) {
  border-radius: 0px 10px 0 0;
}
.table-tape td {
  text-align: center;
  vertical-align: middle;
}
thead th {
  background: rgb(52, 108, 212);
  color: white;
  width: 100px;
  text-align: center;
  vertical-align: middle;
}
.lestGo {
  display: flex;
  align-items: center;
  justify-content: center;
}

.table-tape {
  width: 50vw;
  padding: 100px;
}
</style>