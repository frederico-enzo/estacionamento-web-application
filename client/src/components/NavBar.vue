<template>
        <header class="header">
      <div class="logo">
        <img width="45" src="../assets/estacionamento.png" alt="" />
        <h3>PARKING</h3>
      </div>
      <nav>
        <ul class="nav-bar">
          <li><router-link class="btn b btn-warning" to="/">Estacionar</router-link></li>
          <li><router-link class="btn b btn-outline-primary" to="/Condutores">Condutores</router-link></li>
          <li><router-link class="btn b btn-outline-primary" to="/veiculos">Veículo</router-link></li>
          <li><router-link class="btn b btn-outline-primary" to="/marcas">Marca</router-link></li>
          <li><router-link class="btn b btn-outline-primary" to="/modelos">Modelo</router-link></li>
          <li><router-link  to="/configuraçao"><img width="35" src="../assets/configuracoes-da-web.png" alt=""></router-link></li>
        </ul>
      </nav>
      <cadastroConfiguracao/>
    </header>
</template>
 
<script>

import cadastroConfiguracao from "@/components/cadastro-configuraçao.vue"

export default{
  components:{
    cadastroConfiguracao
  }
}


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