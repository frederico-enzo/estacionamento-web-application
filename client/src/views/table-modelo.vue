<template>
  <NavBar />
  <div class="lestGo">
    <div class="table-tape">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">Nome</th>
            <th scope="col">Marca</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="modelos in modelosList" :key="modelos.id">
            <td>{{ modelos.nome }}</td>
            <td>{{ modelos.marcaId.nome }}</td>
            <td v-if="modelos.ativo">
              <span class="btn btn-success">...</span>
            </td>
            <td v-if="!modelos.ativo">
              <span class="btn btn-danger">...</span>
            </td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button> -
              <button
                @click="onClickExcluir(modelos.id)"
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
    </div>
  </div>
</template>
  
<script lang="ts">
import { defineComponent } from "vue";

import NavBar from "../components/NavBar.vue";
import { ModeloClient } from "@/client/Modelo.client";
import { Modelo } from "@/Model/Modelo";

export default defineComponent({
  name: "TableModelo",
  components: {
    NavBar,
  },
  data() {
    return {
      modelosList: new Array<Modelo>(),
    };
  },
  mounted() {
    this.findAll();
  },

  methods: {
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
    onClickExcluir(id: number) {
      const modelosClient = new ModeloClient();
      modelosClient
        .excluir(id)
        .then(() => {
          this.findAll();
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
  },
});
</script>

<style scoped>
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