<template>
  <NavBar />
  <div class="table-tape">
    <div class="marca">
      <router-link
        :to="{ name: 'formulario-marca' }"
        type="button"
        class="btn btn-warning"
        >Cadastrar</router-link
      >
    </div>
    <table class="table table-bordered shadow">
      <thead>
        <tr>
          <th scope="col">Condutor - CPF</th>
          <th scope="col">Veiculo - Placa</th>
          <th scope="col">Entrada</th>
          <th scope="col">Status</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="entradas in MovimentacaoList" :key="entradas.id">
          <td>
            <select
              v-if="entradas.editMode"
              v-model="entradas.condutor"
              class="form-control"
            >
              <option
                v-for="condutor in condutorList"
                :key="condutor.id"
                :value="condutor"
              >
                Nome: {{ condutor.nome }} CPF: {{ condutor.cpf }}
              </option>
            </select>
            <span v-else>{{ entradas.condutor.cpf }}</span>
          </td>
          <td>
            <select
              v-if="entradas.editMode"
              v-model="entradas.veiculo"
              class="form-control"
            >
              <option
                v-for="veiculo in veiculoList"
                :key="veiculo.id"
                :value="veiculo"
              >
                Placa: {{ veiculo.placa }}
              </option>
            </select>
            <span v-else>{{ entradas.veiculo.placa }}</span>
          </td>
          <td>{{ entradas.entrada }}</td>
          <td v-if="entradas.ativo">
            <span class="btn btn-success">...</span>
          </td>
          <td v-if="!entradas.ativo">
            <span class="btn btn-danger">...</span>
          </td>

          <td>
            <button
              @click="salvarEdicao(entradas)"
              v-if="entradas.editMode"
              type="button"
              class="btn btn-success"
            >
              Salvar
            </button>
            <button
              @click="onClickEditar(entradas.id)"
              v-else
              type="button"
              class="btn btn-warning"
            >
              Editar
            </button>
            -
            <button type="button" class="btn btn-secondary">Finalizar</button>
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
import { Movimentacao } from "@/Model/Movimentacao";
import { MovimentacaoClient } from "@/client/Movimentacao.client";
import { defineComponent } from "vue";
import { CondutorClient } from "@/client/Condutor.client";
import { VeiculoCliente } from "@/client/Veiculo.client";
import { Veiculo } from "@/Model/Veiculo";
import { Condutor } from "@/Model/Condutor";

export default defineComponent({
  components: { NavBar },
  name: "TableMovimentacao",
  data() {
    return {
      condutorList: new Array<Condutor>(),
      veiculoList: new Array<Veiculo>(),
      MovimentacaoList: new Array<Movimentacao>(),
      mensagem: {
        show: false,
        mensagem: "",
        css: "",
      },
    };
  },
  mounted() {
    this.findAll();
    this.findCondutor();
    this.findVeiculo();
  },
  methods: {
    findAll() {
      const movimentacaoClient = new MovimentacaoClient();
      movimentacaoClient
        .findAll()
        .then((data: Movimentacao[]) => {
          this.MovimentacaoList = data;
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
    findCondutor() {
      const condutorClient = new CondutorClient();
      condutorClient
        .findAll()
        .then((data) => {
          this.condutorList = data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    onClickEditar(id: number) {
      const movimentacao = this.MovimentacaoList.find(
        (movimentacao) => movimentacao.id === id
      );
      if (movimentacao) {
        movimentacao.editMode = true;
      }
    },
    async salvarEdicao(entradas: Movimentacao) {
      if (!entradas) {
        return;
      }
      const movimentacaoClient = new MovimentacaoClient();
      try {
        await movimentacaoClient.upDate(entradas.id, entradas);
        entradas.editMode = false;
        this.mensagem.show = true;
        this.mensagem.css = "alert alert-success fade show";
        this.mensagem.mensagem = "Movimentação atualizada com sucesso.";
        setTimeout(() => {
          this.mensagem.show = false;
        }, 5000);
      } catch (error) {
        console.log(error);
        this.mensagem.show = true;
        this.mensagem.css = "alert alert-danger fade show";
        this.mensagem.mensagem = "Não foi possível atualizar a Movimentação.";
        setTimeout(() => {
          this.mensagem.show = false;
        }, 5000);
      }
    },

    findVeiculo() {
      const veiculoClient = new VeiculoCliente();
      veiculoClient
        .findAll()
        .then((data) => {
          this.veiculoList = data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
});
</script>

<style scoped>
#alert {
  margin: 15px 15px 15px 0px;
  width: 350px;
  height: 60px;
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
thead :nth-child(5) {
  border-radius: 0px 10px 0 0;
}
.marca {
  margin: 15px 0px 15px 15px;
  display: flex;
  justify-content: end;
}
thead th {
  background: rgb(52, 108, 212);
  color: white;
  text-align: center;
  vertical-align: middle;
}
.table-tape td {
  text-align: center;
  vertical-align: middle;
}

.table-tape {
  padding-top: 5vw;

  padding-left: 25vw;
  padding-right: 25vw;
}
</style>