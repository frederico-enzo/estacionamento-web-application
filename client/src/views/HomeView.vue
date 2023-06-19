<template>
  <div class="home">
    <header class="header">
      <div class="logo">
        <img width="45" src="../assets/estacionamento.png" alt="" />
        <h3>PARKING</h3>
      </div>
      <nav>
        <ul class="nav-bar">
          <li><cadastroMarca /></li>
          <li><cadastroModelo /></li>
          <li><cadastroVeiculo /></li>
          <li><cadastroCondutor /></li>
        </ul>
      </nav>
      <cadastroConfiguracao />
    </header>
    <div class="lestGo">
      <filtros />
    <!-- <tableMovimentacao/>  -->  
      <tableCondutor/>  
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import cadastroConfiguracao from "../components/cadastro-configuraçao.vue";
import cadastroVeiculo from "../components/cadastro-veiculo.vue";
import cadastroModelo from "../components/cadastro-modelo.vue";
import cadastroMarca from "../components/cadastro-marca.vue";
import cadastroCondutor from "../components/cadastro-condutor.vue";
import filtros from "../components/filtros.vue";
import tableMovimentacao from "../components/table-movimentaçao-lista.vue";
import tableMarca from "../components/table-marca.vue";
import tableVeiculo from "../components/table-veiculo.vue";
import tableCondutor from "../components/table-condutor.vue";


export default defineComponent({
  name: "HomeView",
  components: {
    cadastroConfiguracao,
    cadastroVeiculo,
    cadastroModelo,
    cadastroMarca,
    cadastroCondutor,
    filtros,
    tableMovimentacao,
    tableMarca,
    tableVeiculo,
    tableCondutor,
  },
});
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&family=Poppins:wght@500;800&display=swap");

.home {
  height: 100vh;
  background: linear-gradient(311.76deg, #d4e7fe -15.24%, #ffffff 78.85%);
}

.header {
  width: 100vw;
  height: 80px;
  padding-left: 60px;
  padding-right: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 1px rgb(195, 195, 195);
}
.lestGo {
  display: flex;
  padding-right: 150px;
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
</style>
