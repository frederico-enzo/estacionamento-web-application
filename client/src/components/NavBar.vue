<template>
        <header class="header">
      <div class="logo">
        <img width="45" src="../assets/estacionamento.png" alt="" />
        <h3>PARKING</h3>
        <select class="form-select" v-model="selectedOption" @change="navigateToTable">
          <option value="Condutores">Condutor</option>
          <option value="veiculos">Veículo</option>
          <option value="marcas">Marca</option>
          <option value="modelos">Modelo</option>
        </select>
      </div>
      <nav>
        <ul class="nav-bar">
          <button class="btn btn-warning">Estacionar</button>
          <li><cadastroMarca /></li>
          <li><cadastroModelo /></li>
          <li><cadastroVeiculo /></li>
          <li><cadastroCondutor /></li>
        </ul>
      </nav>
      <cadastroConfiguracao/>
    </header>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import cadastroConfiguracao from "../components/cadastro-configuraçao.vue";
import cadastroVeiculo from "../components/cadastro-veiculo.vue";
import cadastroModelo from "../components/cadastro-modelo.vue";
import cadastroMarca from "../components/cadastro-marca.vue";
import cadastroCondutor from "../components/cadastro-condutor.vue";

export default defineComponent({
    name: "Navbar",
  components: {
    cadastroConfiguracao,
    cadastroVeiculo,
    cadastroModelo,
    cadastroMarca,
    cadastroCondutor,
  },
  data() {
    return {
      selectedOption: 'condutores',
    };
  },
  methods: {
    navigateToTable() {
      this.$router.push(`/${this.selectedOption}`);
    },
  },
});
</script>

<style scoped>
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