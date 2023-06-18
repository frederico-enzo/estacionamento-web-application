<template>
  <div class="home">
    <div class="side-left"> 
      <ul class="list">
        <li>Marca</li>
        <li>Modelo</li>
        <li>Veiculo</li>
        <li>Condutor</li>
      </ul>
    </div>
    <div class="side-right">
      <div class="title">      
        <h2>Estacionamento</h2>
      </div>
      <form>
        <div class="in-line">
          <input class="form-control" type="text" placeholder="Placa">
          <input class="form-control" type="text" placeholder="CPF">
          <input class="btn btn-success" type="submit" value="Enviar">
          <button class="btn"><img class="img-config" src="../assets/configuracoes-da-web.png" alt=""></button>
        </div>
      </form>
      <div>
        <h4>Em Aberto</h4>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>Id</th>
              <th>Veiculo</th>
              <th>Condutor</th>
              <th>Ativo</th>
              <th>Entrada</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>AWk-2121</td>
              <td>Joger Mendosa</td>
              <td>&#x2705;</td>
              <td>23/02/2023 - 12:30</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HomeView',
  components: {

  },
});
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&family=Poppins:wght@500;800&display=swap');
.container {
  margin-top: 20px;
}

.table-hover tbody tr:hover {
  background-color: #f5f5f5;
  cursor: pointer;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins';
}

.img-config {
  width: 30px;
  height: 30px;
}

.list {
  list-style-type: none;
}

.title {
  display: flex;
  justify-content: space-between;
  padding: 25px;
}

.in-line {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50vw;
  padding-left: 25px;
  gap: 15px;
}

.list li {
  cursor: pointer;
  margin-bottom: 7px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 160px;
  height: 50px;
  background: rgb(255, 255, 255);
  border-radius: 10px;
}

.list li:hover {
  background: #2037e7;
  color:  rgb(255, 255, 255);
}

.home {
  gap: 15px;
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.side-left {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; 
  padding-right: 25px ; 
  width: 15vw;
  height: 85vh;
  background:#1500ff; 
  border-radius: 40px; 
}

.side-right {
  width: 80vw;
  height: 85vh;
}
</style>
