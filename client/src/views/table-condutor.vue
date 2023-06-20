<template>
  <NavBar/>
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
        <tr v-for="condutor in data" :key="condutor.cpf">
          <td>{{ condutor.nome }}</td>
          <td>{{ condutor.cpf }}</td>
          <td>{{ condutor.telefone }}</td>
          <td>{{ condutor.tempoDesconto }}</td>
          <td>{{ condutor.tempoPago }}</td>

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
  
</template>
  
<script lang="ts">
import NavBar from '../components/NavBar.vue';

export default {
  components: { NavBar },
  name: "TableCondutor",
  data() {
    return {
      data: [
      {
          nome: "João",
          cpf: "138.882.295-87",
          telefone: "(45) 91912-1123",
          tempoDesconto: 0,
          tempoPago: "23hrs",
          status: "ativo",
        },
        {
          nome: "Frederico",
          cpf: "138.883.229-87",
          telefone: "(45) 99993-1123",
          tempoDesconto: 0,
          tempoPago: "23hrs",
          status: "ativo",
        },
        {
          nome: "Frederico",
          cpf: "138.883.229-87",
          telefone: "(45) 99993-1123",
          tempoDesconto: 0,
          tempoPago: "23hrs",
          status: "ativo",
        },
        {
          nome: "Frederico",
          cpf: "138.883.229-87",
          telefone: "(45) 99993-1123",
          tempoDesconto: 0,
          tempoPago: "23hrs",
          status: "ativo",
        },
        {
          nome: "Frederico",
          cpf: "138.883.229-87",
          telefone: "(45) 99993-1123",
          tempoDesconto: 0,
          tempoPago: "23hrs",
          status: "ativo",
        },
        {
          nome: "Frederico",
          cpf: "138.883.229-87",
          telefone: "(45) 99993-1123",
          tempoDesconto: 0,
          tempoPago: "23hrs",
          status: "ativo",
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

thead th {
  background: rgb(52, 108, 212);
  color: white;
  text-align: center;
  vertical-align: middle;
}

.table-tape {
  padding: 100px;
  padding-right:250px ;
  padding-left:250px ;

}
.table-tape td {
  text-align: center;
  vertical-align: middle;
}
</style>