<template>
  <NavBar />

  <div class="lestGo">
    <div class="table-tape">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">Placa</th>
            <th scope="col">Modelo</th>
            <th scope="col">Ano</th>
            <th scope="col">Cor</th>
            <th scope="col">Tipo</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="veiculo in veiculoList" :key="veiculo.id">
            <td>
              <input
                class="form-control"
                v-if="veiculo.editMode"
                v-model="veiculo.placa"
              />
              <span v-else>{{ veiculo.placa }}</span>
            </td>
            <td>
              <input
                class="form-control"
                v-if="veiculo.editMode"
                v-model="veiculo.modeloId.nome"
              />
              <span v-else>{{ veiculo.modeloId.nome }}</span>
            </td>
            <td>
              <input
                class="form-control"
                v-if="veiculo.editMode"
                v-model="veiculo.ano"
              />
              <span v-else>{{ veiculo.ano }}</span>
            </td>
            <td>
              <input
                class="form-control"
                v-if="veiculo.editMode"
                v-model="veiculo.cor"
              />
              <span v-else>{{ veiculo.cor }}</span>
            </td>
            <td>
              <input
                class="form-control"
                v-if="veiculo.editMode"
                v-model="veiculo.tipo"
              />
              <span v-else>{{ veiculo.tipo }}</span>
            </td>

            <td v-if="veiculo.ativo">
              <span class="btn btn-success">...</span>
            </td>
            <td v-if="!veiculo.ativo">
              <span class="btn btn-danger">...</span>
            </td>
            <td>
              <button
                @click="salvarEdicao(veiculo)"
                v-if="veiculo.editMode"
                type="button"
                class="btn btn-success"
              >
                Salvar
              </button>
              <button
                @click="onClickEditar(veiculo.id)"
                v-else
                type="button"
                class="btn btn-warning"
              >
                Editar
              </button>
              -
              <button
                type="button"
                class="btn btn-outline-danger"
                @click="onClickExcluir(veiculo.id)"
              >
                &#x274C;
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <footer>©Frederico 2023</footer>
      <br />

      <div v-if="mensagem.show" :class="mensagem.css" id="alert">
        {{ mensagem.mensagem }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import NavBar from "../components/NavBar.vue";
import { Veiculo } from "@/Model/Veiculo";
import { VeiculoCliente } from "@/client/Veiculo.client";

export default {
  components: { NavBar },
  name: "TableVeiculo",
  data() {
    return {
      veiculo: new Veiculo(),
      veiculoList: new Array<Veiculo>(),
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
    findAll() {
      const veiculoClient = new VeiculoCliente();
      veiculoClient
        .findAll()
        .then((success: Veiculo[]) => {
          this.veiculoList = success;
        })
        .catch((error: Error) => {
          console.log(error);
        });
    },
    onClickExcluir(id: number) {
      const veiculoClient = new VeiculoCliente();
      veiculoClient
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
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-danger fade show";
          this.mensagem.mensagem = "Não foi possivel excluir o veiculo.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 3500);
        });
    },
    onClickEditar(id: number) {
      const veiculo = this.veiculoList.find((veiculo) => veiculo.id === id);
      if (veiculo) {
        veiculo.editMode = true;
      }
    },
    async salvarEdicao(veiculo: Veiculo) {
      const veiculoClient = new VeiculoCliente();
      try {
        await veiculoClient.upDate(veiculo.id, veiculo);
        veiculo.editMode = false;
        this.mensagem.show = true;
        this.mensagem.css = "alert alert-success fade show";
        this.mensagem.mensagem = "Veículo atualizado com sucesso.";
        setTimeout(() => {
          this.mensagem.show = false;
        }, 3500);
      } catch (error) {
        console.log(error);
        this.mensagem.show = true;
        this.mensagem.css = "alert alert-danger fade show";
        this.mensagem.mensagem = "Não foi possível atualizar o veículo.";
        setTimeout(() => {
          this.mensagem.show = false;
        }, 3500);
      }
    },
  },
};
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
thead :nth-child(7) {
  border-radius: 0px 10px 0 0;
}
.table-tape td {
  text-align: center;
  vertical-align: middle;
}
thead th {
  background: rgb(52, 108, 212);
  color: white;
  width: 10vw;
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
