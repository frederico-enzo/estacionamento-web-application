<template>
  <NavBar/>
  <div class="lestGo">
    <div class="table-tape">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Nome</th>
          <th scope="col">Status</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="marcas in Marcas" :key="marcas.nome">
          <td>{{ marcas.nome }}</td>
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
  </div>
  </div>

</template>
  
<script lang="ts">
import NavBar from './NavBar.vue';

export default {
  components: { NavBar },
  name: "TableMarca",
  data() {
    return {
      Marcas: [
      {
          nome: "Honda",
        },
        {
          nome: "VolksWagem",
        },
      ],
    };
  },
};
</script>

<style scoped>
thead :nth-child(1) {
  border-radius: 10px 0px 0 0;
}
thead :nth-child(3) {
  border-radius: 0px 10px 0 0;
}

thead th {
  background: rgb(52, 108, 212);
  color: white;
  width: 8vw ;

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