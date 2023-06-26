<template>
  <NavBar />
  <div class="lestGo">
    <div class="table-tape">
      <table class="table table-bordered shadow">
        <thead>
          <tr>
            <th scope="col">Nome</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="marca in marcasList" :key="marca.id">
            <td>
              <input
                class="form-control"
                v-if="marca.editMode"
                v-model="marca.nome"
              />
              <span v-else>{{ marca.nome }} </span>
            </td>
            <td>
              <span v-if="marca.ativo" class="btn btn-success">...</span>
              <span v-else class="btn btn-danger">...</span>
            </td>

            <td>
              <button
                  @click="salvarEdicao(marca)"
                  v-if="marca.editMode"
                  type="button"
                  class="btn btn-success"
                >
                  Salvar
                </button>
                <button
                  @click="onClickEditar(marca.id)"
                  v-else
                  type="button"
                  class="btn btn-warning"
                >
                  Editar
                </button>
                -
              <button
                @click="onClickExcluir(marca.id)"
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
import { defineComponent } from "vue";

import NavBar from "../components/NavBar.vue";
import { MarcaClient } from "@/client/Marca.client";
import { Marca } from "@/Model/Marca";

export default defineComponent({
  name: "TableMarca",
  components: {
    NavBar,
  },
  data() {
    return {
      marca: new Marca(),
      marcasList: new Array<Marca>(),
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
      const marcaClient = new MarcaClient();
      marcaClient
        .findAll()
        .then((success: Marca[]) => {
          this.marcasList = success;
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
    onClickExcluir(id: number) {
      const marcaClient = new MarcaClient();
      marcaClient
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

    onClickEditar(id: number) {
      const marca = this.marcasList.find((marca) => marca.id === id);
      if (marca) {
        marca.editMode = true;
      }
    },
    salvarEdicao(marca: Marca) {
      const marcaClient = new MarcaClient();
      marcaClient
        .upDate(marca.id, marca) 
        .then(() => {
          marca.editMode = false;
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
  },
});
</script>


<style scoped>
#alert {
  margin: 15px 15px 15px 0px;
  width: 300px;
  height: 60px;
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
thead :nth-child(5) {
  border-radius: 0px 10px 0 0;
}

thead th {
  background: rgb(52, 108, 212);
  color: white;
  width: 10vw;
  text-align: center;
  vertical-align: middle;
}
.table-tape td {
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