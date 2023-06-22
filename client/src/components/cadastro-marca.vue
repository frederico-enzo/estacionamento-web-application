<template>
  <div>
    <button class="btn b btn-outline-primary" @click="exibirPopup">
      Marca
    </button>
    <div class="overlay" v-if="exibir">
      <div id="popup" v-show="exibir" class="popup-container">
        <div class="close"><button @click="fecharPopup">x</button></div>
        <h3>Marca</h3>
        <div class="form-conteiner">
          <div v-if="mensagem.ativo" class="row">
            <div class="col-md-12 text-start">
              <div :class="mensagem.css" role="alert">
                <strong>{{ mensagem.titulo }}</strong> {{ mensagem.mensagem }}
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="alert"
                  aria-label="Close"
                ></button>
              </div>
            </div>
          </div>
        </div>
        <div class="form-corm">
          <form class="form">
            <input
              v-model="marca.nome"
              class="form-control"
              type="text"
              placeholder="Nome"
            />
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
  
  <script >
import axios from "axios";

import { Marca } from "@/Model/Marca";
import { MarcaClient } from "@/client/Marca.client";

export default {
  data() {
    return {
      marca: new Marca(),
      mensagem: {
        ativo: false,
        titulo: "",
        mensagem: "",
        css: "",
      },
      exibir: false,
    };
  },

  methods: {
    exibirPopup() {
      this.exibir = true;
    },
    fecharPopup() {
      this.exibir = false;
    },
    onClickCadastrar() {
      const marcaClient = new MarcaClient();
      marcaClient
        .newMarca(this.marca)
        .then((success) => {
          this.marca = new Marca();
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "Marca cadastrada com sucesso";
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
h1 {
  margin-bottom: 2 5px;
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
.form-corm {
  display: flex;
  flex-direction: column;
  justify-content: end;
}
#popup {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 10px;
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  height: 320px;
  background-color: #ffffff;
  border: 1px solid #8e8e8e;
  border-radius: 10px;
}
</style>