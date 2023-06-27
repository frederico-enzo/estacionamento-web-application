<template>
  <NavBar />

  <div class="table-tape">
    <div class="marca">
        <router-link :to="{name: 'formulario-condutor'}" type="button" class="btn btn-warning ">Cadastrar</router-link>
      </div>
    <table class="table table-bordered shadow">
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
        <tr v-for="condutor in condutorList" :key="condutor.id">
          <td>
            <input
              v-if="condutor.editMode"
              v-model="condutor.nome"
              class="form-control"
              type="text"
              placeholder="Nome"
            />
            <span v-else>{{ condutor.nome }}</span>
          </td>
          <td>
            <input
              v-if="condutor.editMode"
              v-model="condutor.cpf"
              class="form-control"
              type="text"
              placeholder="CPF"
              v-mask="'###.###.###-##'"
              :mask-reverse="true"
              maxlength="14"
            />
            <span v-else>{{ condutor.cpf }}</span>
          </td>
          <td>
            <input
              v-if="condutor.editMode"
              v-model="condutor.telefone"
              v-mask="'(##)#####-####'"
              :mask-reverse="true"
              class="form-control"
              type="text"
              placeholder="Telefone"
              maxlength="14"
            />
            <span v-else>{{ condutor.telefone }}</span>
          </td>

          <td>{{ condutor.tempoDesconto }}</td>
          <td>{{ condutor.tempoPago }}</td>
          <td>
            <span v-if="condutor.ativo" class="btn btn-success">...</span>
            <span v-else class="btn btn-danger">...</span>
          </td>

          <td>
            <button
                @click="salvarEdicao(condutor)"
                v-if="condutor.editMode"
                type="button"
                class="btn btn-success"
              >
                Salvar
              </button>
              <button
                @click="onClickEditar(condutor.id)"
                v-else
                type="button"
                class="btn btn-warning"
              >
                Editar
              </button>
            -
            <button
              @click="onClickExcluir(condutor.id)"
              type="button"
              class="btn btn-outline-danger"
            >
              &#x274C;
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <footer>©Frederico 2023</footer>
    <div v-if="mensagem.show" :class="mensagem.css" id="alert">
      {{ mensagem.mensagem }}
    </div>
  </div>
</template>
  
<script lang="ts">
import NavBar from "../components/NavBar.vue";
import { CondutorClient } from "@/client/Condutor.client";
import { Condutor } from "@/Model/Condutor";
import { defineComponent } from "vue";

export default defineComponent({
  name: "TableCondutor",
  components: {
    NavBar,
  },
  data() {
    return {
      condutorList: new Array<Condutor>(),
      condutor: new Condutor(),
      mensagem: {
        show: false,
        mensagem: "",
        css: "",
      },
    };
  },
  mounted() {
    this.findAll();
  },
  methods: {
    onClickEditar(id: number) {
      const condutor = this.condutorList.find((condutor) => condutor.id === id);
      if (condutor) {
        condutor.editMode = true;
      }
    },
    async salvarEdicao(condutor: Condutor) {
      const condutorClient = new CondutorClient();
      try {
        await condutorClient.upDate(condutor.id, condutor);
        condutor.editMode = false;
        this.mensagem.show = true;
        this.mensagem.css = "alert alert-success fade show";
        this.mensagem.mensagem = "Veículo atualizado com sucesso.";
        setTimeout(() => {
          this.mensagem.show = false;
        }, 5000);
      } catch (error) {
        console.log(error);
        this.mensagem.show = true;
        this.mensagem.css = "alert alert-danger fade show";
        this.mensagem.mensagem = "Não foi possível atualizar o veículo.";
        setTimeout(() => {
          this.mensagem.show = false;
        }, 5000);
      }
    },
    findAll() {
      const condutorClient = new CondutorClient();
      condutorClient
        .findAll()
        .then((data: Condutor[]) => {
          this.condutorList = data;
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
    onClickExcluir(id: number) {
      const condutorClient = new CondutorClient();
      condutorClient
        .excluir(id)
        .then(() => {
          this.findAll();
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-success  fade show";
          this.mensagem.mensagem = "Veiculo excluido com sucesso.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 3500);
        })
        .catch((error: Error) => {
          console.log(error);
          this.mensagem.css = "alert alert-danger fade show";
          this.mensagem.mensagem = "Não foi possivel excluir o veiculo.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 3500);
        });
    },
  },
});
</script>

<style scoped>
#alert {
  margin: 15px 15px 15px 0px;
  width: 300px;
  height: 60px;
}
.marca{
  margin:  15px 0px 15px 15px ;
  display: flex;
  justify-content: end;
}
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
  padding-right: 250px;
  padding-left: 250px;
}
.table-tape td {
  text-align: center;
  vertical-align: middle;
}
</style>