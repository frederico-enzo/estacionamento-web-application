<template>
  <NavBar/>
  <div class="table-tape">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">Condutor - CPF</th>
          <th scope="col">Veiculo - Placa</th>
          <th scope="col">Entrada</th>
          <th scope="col">Saida</th>
          <th scope="col">Tempo </th>
          <th scope="col">Tempo de Multa </th>
          <th scope="col">Valor da Hora</th>
          <th scope="col">Valor de Multa</th>
          <th scope="col">Valor Total </th>
          <th scope="col">valor de Desconte </th>
          <th scope="col">Status</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="entradas in Movimentacoes" :key="entradas.Condutor">
          <td>{{ entradas.Condutor }}</td>
          <td>{{ entradas.Veiculo }}</td>
          <td>{{ entradas.Entrada }}</td>
          <td>{{ entradas.Saida }}</td>
          <td>{{ entradas.tempo }}</td>
          <td>{{ entradas.tempoMulta }}</td>
          <td>{{ entradas.valorHora }}</td>
          <td>{{ entradas.valorMulta }}</td>
          <td>{{ entradas.valorTotal }}</td>
          <td>{{ entradas.valorDesconte }}</td>
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
import NavBar from '../components/NavBar.vue';

export default {
  components: { NavBar },
  name: "TableCondutor",
  data() {
    return {
      Movimentacoes: [
      {
          Condutor: "111.558.358-87",
          Veiculo: "AFA-1800",
          Entrada:  "16-02-2023 15:33",
          Saida: "16-02-2023 08:03",
          tempoPago: "23hrs",
          tempo: "0",
          tempoMulta: "0",
          valorMulta: "R$ 0.50",

          valorHora: "R$ 05.50",
          valorTotal: 0,
          valorDesconte: "0",
        },
        {
          Condutor: "904.187.191-58",
          Veiculo: "NSE-1097",
          Entrada:  "16-02-2023 08:03",
          Saida: "16-02-2023 08:03",
          tempoPago: "17hrs",
          tempo: "0",
          tempoMulta: "0",
          valorMulta: "R$ 0.50",
          valorHora: "R$ 05.50",
          valorTotal: 0,
          valorDesconte: "0",
        },
        {
          Condutor: "904.187.191-58",
          Veiculo: "NSE-1097",
          Entrada:  "16-02-2023 08:03",
          Saida: "16-02-2023 08:03",
          tempoPago: "17hrs",
          tempo: "0",
          tempoMulta: "0",
          valorMulta: "R$ 0.50",
          valorHora: "R$ 05.50",
          valorTotal: 0,
          valorDesconte: "0",
        },{
          Condutor: "904.187.191-58",
          Veiculo: "NSE-1097",
          Entrada:  "16-02-2023 08:03",
          Saida: "16-02-2023 08:03",
          tempoPago: "17hrs",
          tempo: "0",
          tempoMulta: "0",
          valorMulta: "R$ 0.50",
          valorHora: "R$ 05.50",
          valorTotal: 0,
          valorDesconte: "0",
        },{
          Condutor: "904.187.191-58",
          Veiculo: "NSE-1097",
          Entrada:  "16-02-2023 08:03",
          Saida: "16-02-2023 08:03",
          tempoPago: "17hrs",
          tempo: "0",
          tempoMulta: "0",
          valorMulta: "R$ 0.50",
          valorHora: "R$ 05.50",
          valorTotal: 0,
          valorDesconte: "0",
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
thead :nth-child(12) {
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
  padding: 100px;


}
</style>