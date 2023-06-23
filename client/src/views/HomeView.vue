<template>
  <NavBar />
  <div class="table-tape">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">Condutor - CPF</th>
          <th scope="col">Veiculo - Placa</th>
          <th scope="col">Entrada</th>
          <th scope="col">Status</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="entradas in MovimentacaoList" :key="entradas.id">
          <td>{{ entradas.condutor.cpf }}</td>
          <td>{{ entradas.veiculo.placa }}</td>
          <td>{{ entradas.entrada }}</td>
          <td><span class="btn btn-success">...</span></td>
          <td>
            <button type="button" class="btn btn-secondary">Finalizar</button> -
            <button type="button" class="btn btn-warning">✏️</button>
          </td>
        </tr>
      </tbody>
    </table>
    <footer>©Frederico 2023</footer>
  </div>
</template>
  
<script lang="ts">
import NavBar from "../components/NavBar.vue";
import { Movimentacao } from "@/Model/Movimentacao";
import { MovimentacaoClient } from "@/client/Movimentacao.client";
import { defineComponent } from "vue";

export default defineComponent({
  components: { NavBar },
  name: "TableMovimentacao",
  data() {
    return {
      MovimentacaoList:new Array<Movimentacao>(),
    };
  },
  mounted() {
    this.findAll();
  },
  methods: {
    findAll() {
      const movimentacaoClient = new MovimentacaoClient();
      movimentacaoClient
        .findAll()
        .then((data: Movimentacao[]) => {
          this.MovimentacaoList = data;
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
thead :nth-child(5) {
  border-radius: 0px 10px 0 0;
}

thead th {
  background: rgb(52, 108, 212);
  color: white;
  text-align: center;
  vertical-align: middle;
}
.table-tape td {
  text-align: center;
  vertical-align: middle;
}

.table-tape {
  padding-top: 5vw;

  padding-left: 25vw;
  padding-right: 25vw;
}
</style>