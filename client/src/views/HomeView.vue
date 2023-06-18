<template>
  <div class="home">
    <header class="header">
      <DIV class="logo">
        <img width="65" src="../assets/estacionamento.png" alt="" />
        <h3>PARKINGLOT</h3>
      </DIV>
      <nav>
        <li class="nav-bar">
          <ul>
            Marcas
          </ul>
          <ul>
            Modelos
          </ul>
          <ul>
            Veiculos
          </ul>
          <ul>
            Condutores
          </ul>
        </li>
      </nav>
      <cadastroConfiguracao />
    </header>
    <div class="conteiner-table">
      <main class="siza">
        <table class="table">
          <thead class="thead-dark">
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Placa</th>
              <th scope="col">Cpf</th>
              <th scope="col">Entrada</th>
              <th scope="col">Ação</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>KWD-1241</td>
              <td>132.456.789-12</td>
              <td>12-05-2023 16:40</td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>QAS-642</td>
              <td>666.124.789-45</td>
              <td>12-05-2023 14:00</td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td>BWE-1245</td>
              <td>132.333.361-02</td>
              <td>12-05-2023 15:10</td>
              
            </tr>
          </tbody>
        </table>
      </main>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import cadastroMarca from "../components/cadastro-marca.vue";
import cadastroConfiguracao from "../components/cadastro-configuraçao.vue";

export default defineComponent({
  name: "HomeView",
  components: {
    cadastroMarca,
    cadastroConfiguracao,
  },
});
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&family=Poppins:wght@500;800&display=swap");
.conteiner-table{
  width: 100vw;
  display: flex;
  justify-content: center;
}
.siza {
  margin-top: 50px;
  border: solid 2px grey;
  border-radius: 10px ;
  width: 800px;
  display: flex;
  justify-content: center;
}
.header {
  width: 100vw;
  height: 100px;
  padding-left: 60px;
  padding-right: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 1px black;
}
.logo {
  display: flex;
  align-items: center;
  gap: 10px;
}
ul {
  cursor: pointer;
}
.nav-bar {
  list-style: none;
  display: flex;
}
</style>
