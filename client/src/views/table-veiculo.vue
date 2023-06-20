<template>
  <NavBar/>
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
        <tr v-for="veiculos in Veiculos" :key="veiculos.Placa">
          <td>{{ veiculos.Placa }}</td>
          <td>{{ veiculos.Modelo }}</td>
          <td>{{ veiculos.Ano }}</td>
          <td>{{ veiculos.Cor }}</td>
          <td>{{ veiculos.Tipo }}</td>
          <td><span class="btn btn-success">...</span></td>
          <td>
            <button type="button" class="btn btn-warning">✏️</button> -
            <button type="button" class="btn btn-outline-danger">
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
import NavBar from '../components/NavBar.vue';

export default {
  components: { NavBar },
  name: "TableVeiculo",
  data() {
    return {
      Veiculos: [
      {
          Placa: "IAN-0023",
          Modelo: "City",
          Ano: "2008",
          Cor: "Prata",
          Tipo: "Carro",

        },
        {
          Placa: "ANV-8042",
          Modelo: "VolksWagem",
          Ano: "2014",
          Cor: "Prata",
          Tipo: "Carro",
        },{
          Placa: "ANV-8042",
          Modelo: "VolksWagem",
          Ano: "2014",
          Cor: "Prata",
          Tipo: "Carro",
        },{
          Placa: "ANV-8042",
          Modelo: "VolksWagem",
          Ano: "2014",
          Cor: "Prata",
          Tipo: "Carro",
        },{
          Placa: "ANV-8042",
          Modelo: "VolksWagem",
          Ano: "2014",
          Cor: "Prata",
          Tipo: "Carro",
        },
      ],
    };
  },
};
</script>

<style scoped>
footer{
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
  width: 10vw ;
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