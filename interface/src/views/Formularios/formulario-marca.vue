<template>
    <NavBar/>
    <div class="overlay">
    <div
        id="popup"
        class="popup-container"
        :style="{ height: mensagem.ativo ? '320px' : '230px' }"
      >
      <br>
        <h3>Marca</h3>
        <div class="form-conteiner">
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
</template>

<script >
import axios from "axios";

import { Marca } from "@/Model/Marca";
import { MarcaClient } from "@/client/Marca.client";
import NavBar from "@/components/NavBar.vue";

export default {
  name: "formulario-marca",
  components: {
    NavBar,
  },
  data() {
    return {
      marca: new Marca(),
      mensagem: {
        ativo: false,
        titulo: "",
        mensagem: "",
        css: "",
      },
    };
  },

  methods: {
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
    redefinirTamanhoPopup() {
      this.mensagem.ativo = false;
      const popup = document.getElementById("popup");
      if (popup) {
        popup.style.height = "300px";
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