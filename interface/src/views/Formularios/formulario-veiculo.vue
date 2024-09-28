<template>
  <div>
    <NavBar />
    <div class="overlay">
      <div
        id="popup"
        class="popup-container"
        :style="{ height: mensagem.ativo ? '450px' : '380px' }"
      >
        <br />
        <h3>Veiculo</h3>
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
          <input
            class="form-control"
            type="text"
            v-model="veiculo.placa"
            v-mask="'AAA-####'"
            :mask-reverse="true"
            placeholder="Digite a placa"
          />
          <input
            v-model="veiculo.ano"
            class="form-control"
            :mask-reverse="true"
            v-mask="'####'"
            placeholder="Ano"
          />
          <div class="flex">
            <label> Modelos: </label>
            <select
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
          </div>
          <div class="flex">
            <label>Tipo:</label>
            <select
              v-model="veiculo.tipo"
              class="form-control"
              name="Tipo"
              id="Tipo"
            >
              <option value="CARRO">Carro</option>
              <option value="VAN">Van</option>
              <option value="MOTO">Moto</option>
            </select>
          </div>

          <div class="flex">
            <label>Cor:</label>
            <select
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
          </div>

          <button
            @click="CadastrarVeiculo"
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

<script lang="ts">
import { Veiculo } from "@/Model/Veiculo";
import { VeiculoCliente } from "@/client/Veiculo.client";
import NavBar from "@/components/NavBar.vue";
import { Modelo } from "@/Model/Modelo";
import { ModeloClient } from "@/client/Modelo.client";
import { defineComponent } from "vue";

export default defineComponent({
  components: {
    NavBar,
  },
  data() {
    return {
      exibir: false,
      veiculo: new Veiculo(),
      modeloList: [] as Modelo[],
      mensagem: {
        ativo: false,
        titulo: "",
        mensagem: "",
        css: "",
      },
    };
  },
  mounted() {
    this.BuscarModelos();
  },
  methods: {
    exibirPopup() {
      this.exibir = true;
    },
    fecharPopup() {
      this.exibir = false;
    },
    BuscarModelos() {
      const modeloClient = new ModeloClient();
      modeloClient
        .findAll()
        .then((success) => {
          this.modeloList = success;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    CadastrarVeiculo() {
      const veiculoClient = new VeiculoCliente();
      veiculoClient
        .newVeiculo(this.veiculo)
        .then((success) => {
          this.veiculo = new Veiculo();
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "Veículo cadastrado com sucesso";
          this.mensagem.titulo = "";
          this.mensagem.css =
            "alert alert-success alert-dismissible fade show";
        })
        .catch((error) => {
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "";
          this.mensagem.titulo = "Error. ";
          this.mensagem.css =
            "alert alert-danger alert-dismissible fade show";
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
});
</script>

<style scoped>
.flex {
  display: flex;
  padding: 0px;
  align-items: center;
}
.flex :nth-child(1) {
  padding-right: 15px;
}
.row {
  margin-left: 10px;
  margin-right: 10px;
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
  top: 35%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 600px;
  height: 300px;
  background-color: #ffffff;
  border: 1px solid #8e8e8e;
  border-radius: 10px;
}
</style>