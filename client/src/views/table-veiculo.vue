<template>
  <NavBar />

  <div class="lestGo">
    <div class="table-tape">
      <div class="marca">
        <router-link :to="{name: 'formulario-veiculo'}" type="button" class="btn btn-warning ">Cadastrar</router-link>
      </div>
      <table class="table table-bordered shadow">
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
                v-if="veiculo.editMode"
                v-model="veiculo.placa"
                class="form-control"
                type="text"
                v-mask="'AAA-####'"
                :mask-reverse="true"
              />
              <span v-else>{{ veiculo.placa }}</span>
            </td>
            <td>
              <select
                v-if="veiculo.editMode"
                title="Modelos"
                v-model="veiculo.modeloId"
                class="form-control"
              >
                <option
                  v-for="modelo in modeloList"
                  :key="modelo.id"
                  :value="modelo"
                >
                  {{ modelo.nome }}
                </option>
              </select>
              <span v-else>{{ veiculo.modeloId.nome }}</span>
            </td>
            <td>
              <input
                class="form-control"
                v-if="veiculo.editMode"
                v-model="veiculo.ano"
                :mask-reverse="true"
                v-mask="'####'"
              />
              <span v-else>{{ veiculo.ano }}</span>
            </td>
            <td>
              <select
                v-if="veiculo.editMode"
                v-model="veiculo.cor"
                class="form-control"
                name="COR"
                id="Cor"
              >
                <option value="AZUL">Azul</option>
                <option value="CINZA">Cinza</option>
                <option value="MARRON">Marron</option>
                <option value="PRETO">Preto</option>
                <option value="PRATA">Prata</option>
                <option value="BRANCO">Branco</option>
                <option value="AMARELO">Amarelo</option>
                <option value="VERDE">Verde</option>
              </select>
              <span v-else>{{ veiculo.cor }}</span>
            </td>
            <td>
              <select
                v-if="veiculo.editMode"
                v-model="veiculo.tipo"
                class="form-control"
                name="Tipo"
                id="Tipo"
              >
                <option value="CARRO">Carro</option>
                <option value="VAN">Van</option>
                <option value="MOTO">Moto</option>
              </select>
              <span v-else>{{ veiculo.tipo }}</span>
            </td>
            <td>
              <span v-if="veiculo.ativo" class="btn btn-success">...</span>
              <span v-else class="btn btn-danger">...</span>
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
      <div v-if="mensagem.show" :class="mensagem.css" id="alert">
        {{ mensagem.mensagem }}
      </div>
    </div>
    <br />
  </div>
</template>

<script lang="ts">
import NavBar from "../components/NavBar.vue";
import { Veiculo } from "@/Model/Veiculo";
import { VeiculoCliente } from "@/client/Veiculo.client";
import { Modelo } from "@/Model/Modelo";
import { ModeloClient } from "@/client/Modelo.client";

export default {
  components: { NavBar },
  name: "TableVeiculo",
  data() {
    return {
      veiculo: new Veiculo(),
      veiculoList: new Array<Veiculo>(),
      modeloList: new Array<Modelo>(),
      mensagem: {
        show: false,
        mensagem: "",
        css: "",
      },
    };
  },
  mounted() {
    this.findAll();
    this.BuscarModelos();
  },
  methods: {
    BuscarModelos() {
      const modeloClient = new ModeloClient();
      modeloClient
        .findAll()
        .then((sucess) => {
          this.modeloList = sucess;
        })
        .catch((error) => {
          console.log(error);
        });
    },
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
          }, 5000);
        })
        .catch((error: Error) => {
          console.log(error);
          this.mensagem.show = true;
          this.mensagem.css = "alert alert-danger fade show";
          this.mensagem.mensagem = "Não foi possivel excluir o veiculo.";
          setTimeout(() => {
            this.mensagem.show = false;
          }, 5000);
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
  },
};
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
