<template>
  <div>
    <NavBar />
    <div class="overlay">
      <div
        id="popup"
        class="popup-container"
        :style="{ height: mensagem.ativo ? '420px' : '330px' }"
      >
        <br />
        <h3>Estacionar</h3>
        <div class="form-container">
          <div v-if="mensagem.ativo" class="row">
            <div class="col-md-12 text-start">
              <div :class="mensagem.css" role="alert">
                <strong>{{ mensagem.titulo }}</strong> {{ mensagem.mensagem }}
                <button
                  @click="redefinirTamanhoPopup"
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="alert"
                  aria-label="Close"
                ></button>
              </div>
            </div>
          </div>
        </div>
        <form class="form">
          <select v-model="movimentacao.condutor" class="form-control">
            <option
              v-for="condutor in condutorList"
              :key="condutor.id"
              :value="condutor"
            >
             Nome: {{ condutor.nome }} CPF: {{ condutor.cpf }}
            </option>
          </select>
          <select v-model="movimentacao.veiculo" class="form-control">
            <option
              v-for="veiculo in veiculoList"
              :key="veiculo.id"
              :value="veiculo"
            >
              Placa: {{ veiculo.placa }}
            </option>
          </select>
          

          <br />
          <button
            @click="onClickCadastrar"
            type="button"
            class="btn btn-outline-success"
          >
            Cadastrar
          </button>
        </form>
      </div>
    </div>
  </div>
</template>
  
  <script>
import NavBar from "@/components/NavBar.vue";
import { format } from "date-fns";
import { Movimentacao } from "@/Model/Movimentacao";
import { MovimentacaoClient } from "@/client/Movimentacao.client";
import { Condutor } from "@/Model/Condutor";
import { CondutorClient } from "@/client/Condutor.client";
import { VeiculoCliente } from "@/client/Veiculo.client";
import { ConfiguracaoClient } from "@/client/Configuracao.client";

export default {
  components: {
    NavBar,
  },
  data() {
    return {
      condutorList: [],
      veiculoList: [],
      configList: [],
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
    onClickCadastrar() {
      const now = new Date();
      const formattedHoraMinutos = format(now, "HH'h'mm'm'");
      this.movimentacao.entrada = formattedHoraMinutos;
      this.movimentacao.configuracao = { id: 1 };

      const movimentacaoClient = new MovimentacaoClient();
      movimentacaoClient
        .newMovimentacao(this.movimentacao)
        .then((success) => {
          this.movimentacao = new Movimentacao();
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "Movimentação cadastrada com sucesso";
          this.mensagem.titulo = "";
          this.mensagem.css = "alert alert-success alert-dismissible fade show";
        })
        .catch((error) => {
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "";
          this.mensagem.titulo = "Error.";
          this.mensagem.css = "alert alert-danger alert-dismissible fade show";
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
    redefinirTamanhoPopup() {
      this.mensagem.ativo = false;
      const popup = document.getElementById("popup");
      if (popup) {
        popup.style.height = "380px";
      }
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
  padding: 15px;
  display: flex;
  flex-direction: column;
}
.overlay {
  position: fixed;
  top: 80px;
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
  display: flex;
  flex-direction: column;
  padding: 10px;
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 600px;
  height: 300px;
  background-color: #ffffff;
  border: 1px solid #8e8e8e;
  border-radius: 10px;
}
</style>