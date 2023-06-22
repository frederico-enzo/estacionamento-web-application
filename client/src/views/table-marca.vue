<template>
  <NavBar/>
  <div class="lestGo">
    <div class="table-tape">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">Nome</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="marcas in marcasList" :key="marcas.id">
            <td>{{ marcas.nome }}</td>
            <td v-if="marcas.ativo"><span class="btn btn-success">...</span></td>
            <td v-if="!marcas.ativo"><span class="btn btn-danger">...</span></td>
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

import { defineComponent } from 'vue';

import NavBar from "../components/NavBar.vue";
import {MarcaClient} from '@/client/Marca.client';
import { Marca } from '@/Model/Marca';

export default defineComponent({
  name: "TableMarca",
  components: {
    NavBar,
  },
  data() {
    return {
        marcasList: new Array<Marca>()
    }
  },
  mounted() {
    this.findAll();
   /* setInterval(() => {
      this.findAll();
    }, 500);*/  
  },

  methods: {
    findAll() {
      const marcaClient = new MarcaClient();
      marcaClient.findAll()
        .then(sucess => {
          this.marcasList = sucess
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
});
</script>

<style scoped>
footer {
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
thead :nth-child(3) {
  border-radius: 0px 10px 0 0;
}

thead th {
  background: rgb(52, 108, 212);
  color: white;
  width: 10vw;
  text-align: center;
  vertical-align: middle;
}
.table-tape td {
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