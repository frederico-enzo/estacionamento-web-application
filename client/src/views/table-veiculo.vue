<template>
  <NavBar />

  <div class="lestGo">
    <div class="table-tape">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">Placa</th>
            <th scope="col">Modelo</th>
            <th scope="col">Ano</th>
            <th scope="col">Cor</th>
            <th scope="col">Tipo</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="veiculo in veiculoList" :key="veiculo.id">
            <td>{{ veiculo.placa }}</td>
            <td>{{ veiculo.modeloId.nome }}</td>
            <td>{{ veiculo.ano }}</td>
            <td>{{ veiculo.cor }}</td>
            <td>{{ veiculo.tipo }}</td>
            <td v-if="veiculo.ativo">
              <span class="btn btn-success">...</span>
            </td>
            <td v-if="!veiculo.ativo">
              <span class="btn btn-danger">...</span>
            </td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button> -
              <button
                type="button"
                class="btn btn-outline-danger"
                @click="onClickExcluir(veiculo.id)"
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
import NavBar from "../components/NavBar.vue";
import { Veiculo } from "@/Model/Veiculo";
import { VeiculoCliente } from "@/client/Veiculo.client";

export default {
  components: { NavBar },
  name: "TableVeiculo",
  data() {
    return {
      veiculo: new Veiculo(),
      veiculoList: new Array<Veiculo>(),
    };
  },
  mounted() {
    this.findAll();
  },
  methods: {
    findAll() {
      const veiculoClient = new VeiculoCliente();
      veiculoClient
        .findAll()
        .then((success: Veiculo[]) => {
          this.veiculoList = success;
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
    onClickExcluir(id: number) {
      const veiculoClient = new VeiculoCliente();
      veiculoClient
        .excluir(id)
        .then(() => {
          this.findAll(); 
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
  },
};
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
