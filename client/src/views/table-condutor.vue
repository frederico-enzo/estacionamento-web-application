<template>
  <NavBar />

  <div class="table-tape">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">Nome</th>
          <th scope="col">CPF</th>
          <th scope="col">Telefone</th>
          <th scope="col">Tempo de Desconto</th>
          <th scope="col">Tempo Pago</th>
          <th scope="col">Status</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="condutor in condutorList" :key="condutor.id">
          <td>{{ condutor.nome }}</td>
          <td>{{ condutor.cpf }}</td>
          <td>{{ condutor.telefone }}</td>
          <td>{{ condutor.tempoDesconto }}</td>
          <td>{{ condutor.tempoPago }}</td>

          <td v-if="condutor.ativo">
            <span class="btn btn-success">...</span>
          </td>
          <td v-if="!condutor.ativo">
            <span class="btn btn-danger">...</span>
          </td>

          <td>
            <button type="button" class="btn btn-warning">✏️</button> -
            <button
              @click="onClickExcluir(condutor.id)"
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
</template>
  
<script lang="ts">
import NavBar from "../components/NavBar.vue";
import { CondutorClient } from "@/client/Condutor.client";
import { Condutor } from "@/Model/Condutor";
import { defineComponent } from "vue";

export default defineComponent({
  name: "TableCondutor",
  components: {
    NavBar,
  },
  data() {
    return {
      condutorList: new Array<Condutor>(),
    };
  },
  mounted() {
    this.findAll();
  },
  methods: {
    findAll() {
      const condutorClient = new CondutorClient();
      condutorClient
        .findAll()
        .then((data: Condutor[]) => {
          this.condutorList = data;
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
    onClickExcluir(id: number) {
      const condutorClient = new CondutorClient();
      condutorClient
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
thead :nth-child(7) {
  border-radius: 0px 10px 0 0;
}

thead th {
  background: rgb(52, 108, 212);
  color: white;
  text-align: center;
  vertical-align: middle;
}

.table-tape {
  padding: 100px;
  padding-right: 250px;
  padding-left: 250px;
}
.table-tape td {
  text-align: center;
  vertical-align: middle;
}
</style>