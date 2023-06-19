<template>
  <div class="home">
    <header class="header">
      <div class="logo">
        <img width="65" src="../assets/estacionamento.png" alt="" />
        <h3>PARKINGLOT</h3>
      </div>
      <nav>
        <ul class="nav-bar">
          <li>Marcas</li>
          <li>Modelos</li>
          <li>Veículos</li>
          <li>Condutores</li>
        </ul>
      </nav>
      <cadastroConfiguracao />
    </header>
    <div class="conpass">
      <div class="conteiner-table">
        <main class="siza">
          <table class="table">
            <thead class="thead-dark">
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Placa</th>
                <th scope="col">CPF</th>
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

.conteiner-table {
  display: flex;
  align-items: center;
  justify-content: center;
}

.conpass {
  border-radius: 25px;
  width: 1500px;
  height: 700px;
  margin-top: 50px;
  display: flex;
  justify-content: center;
  margin-left: 10.5vw;
  background: rgb(255, 255, 255);
  border: solid 2px rgb(195, 195, 195);
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.siza {
  border: solid 2px grey;
  border-radius: 10px;
  width: 800px;
  display: flex;
  justify-content: center;
  background-color: #f9f9f9;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.header {
  width: 100vw;
  height: 100px;
  padding-left: 60px;
  padding-right: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 1px rgb(195, 195, 195);
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
  gap: 20px;
}

.nav-bar li {
  font-weight: bold;
  color: #333;
  transition: color 0.3s ease;
}

.nav-bar li:hover {
  color: #0078d4;
}

.table {
  width: 100%;
  border-collapse: collapse;
  text-align: center;
}

.table th {
  background-color: #333;
  color: #fff;
  padding: 10px;
}

.table td {
  padding: 10px;
  border-bottom: solid 1px #ccc;
}
</style>
