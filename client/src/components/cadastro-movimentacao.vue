<template>
  <div>
    <button class="btn b btn-warning" @click="exibirPopup">Estacionar</button>
    <div class="overlay" v-if="exibir">
      <div id="popup" v-show="exibir" class="popup-container">
        <div class="close"><button @click="fecharPopup">x</button></div>
        <h3>Entrada</h3>
        <div class="form-conteiner">
          <form class="form">
            <input
              class="form-control"
              type="text"
              placeholder="Condutor"
              list="Condutores"
              v-model="movimentacao.condutor"
            />
            <datalist id="Condutores">
              <option
                v-for="condutor in condutorList"
                :key="condutor.id"
                :value="condutor"
              >
                {{ condutor.nome }} - {{ condutor.cpf }}
              </option>
            </datalist>
            <input
              class="form-control"
              type="text"
              placeholder="Veiculo"
              list="Veiculos"
              v-model="movimentacao.veiculo"
            />
            <datalist id="Veiculos">
              <option
                v-for="veiculo in veiculoList"
                :key="veiculo.id"
                :value="veiculo"
              >
                {{ veiculo.placa }}
              </option>
            </datalist>
            <br />
            <button
              @click="onClickCadastrar()"
              type="button"
              class="btn btn-outline-success"
            >
              Cadastrar
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { format } from "date-fns";
import { Movimentacao } from "@/Model/Movimentacao";
import { MovimentacaoClient } from "@/client/Movimentacao.client";
import { Condutor } from "@/Model/Condutor";
import { CondutorClient } from "@/client/Condutor.client";
import { Veiculo } from "@/Model/Veiculo";
import { VeiculoCliente } from "@/client/Veiculo.client";

export default {
  data() {
    return {
      exibir: false,
      condutorList: new Array<Condutor>(),
      veiculoList: new Array<Veiculo>(),
      movimentacao: new Movimentacao(),
      mensagem: {
        ativo: false,
        titulo: "",
        mensagem: "",
        css: "",
      },
    };
  },
  mounted() {
    this.findCondutor();
    this.findVeiculo();
  },
  methods: {
    exibirPopup() {
      this.exibir = true;
    },
    fecharPopup() {
      this.exibir = false;
    },
    onClickCadastrar() {
      const now = new Date();
      const formattedHoraMinutos = format(now, "HH'h'mm'm'");
      const entradaDate = new Date(
        now.getFullYear(),
        now.getMonth(),
        now.getDate(),
        0,
        0,
        0
      );
      this.movimentacao.entrada = entradaDate;
     
      const movimentacaoClient = new MovimentacaoClient();
      movimentacaoClient
        .newMovimentacao(this.movimentacao)
        .then((success) => {
          this.movimentacao = new Movimentacao();
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "Modelo cadastrada com sucesso";
          this.mensagem.titulo = "";
          this.mensagem.css = "alert alert-success alert-dismissible fade show";
        })
        .catch((error) => {
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "";
          this.mensagem.titulo = "Error. ";
          this.mensagem.css = "alert alert-danger alert-dismissible fade show";
        });
    },
    findCondutor() {
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
    findVeiculo() {
      const veiculoClient = new VeiculoCliente();
      veiculoClient
        .findAll()
        .then((data: Veiculo[]) => {
          this.veiculoList = data;
        })
        .catch((error: Veiculo) => {
          console.log(error);
        });
    },
  },
};
</script>



  
  <style scoped>
.flex {
  display: flex;
  padding: 15px;
}
.flex input {
  width: 25%;
}
.close {
  padding: 5px;
  display: flex;
  justify-content: flex-end;
}
.close :nth-child(1) {
  display: flex;
  height: 25px;
  justify-content: center;
  align-items: center;
  border-color: red;
  color: red;
  border-radius: 100px;
  padding-bottom: 5px;
}
.close :nth-child(1):hover {
  display: flex;
  width: 23px;
  height: 25px;
  justify-content: center;
  align-items: center;
  border: none;
  background: red;
  color: white;
  border-radius: 100px;
  padding-bottom: 5px;
}
h3 {
  display: flex;
  justify-content: center;
}
.form {
  gap: 5px;
  padding: 15px;
  display: flex;
  flex-direction: column;
}
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 100;
  display: flex;
  justify-content: center;
  align-items: center;
}

#popup {
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  height: 280px;
  background-color: #ffffff;
  border: 1px solid #8e8e8e;
  border-radius: 10px;
}
</style>