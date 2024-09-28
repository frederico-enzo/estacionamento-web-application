<template>
  <div>
    <NavBar />
    <div class="overlay">
      <div
        id="popup"
        class="popup-container"
        :style="{ height: mensagem.ativo ? '540px' : '480px' }"
      >
        <br />
        <h3>Configuração</h3>
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
        <form action="/enviar-dados" method="POST">
          <label for="valorHora">Valor por Hora:</label>
          <input
            class="form-control"
            type="text"
            v-model="config.valorHora"
            name="valorHora"
            id="valorHora"
            required
            v-mask="'##.##'"
            placeholder="00.00"
         />

          <label for="valorMultaMinuto">Valor da Multa por Minuto:</label>
          <input
            class="form-control"
            type="text"
            v-model="config.valorMultaMinuto"
            name="valorMultaMinuto"
            id="valorMultaMinuto"
            required
            v-mask="'##.##'"
            placeholder="0.00"
        />

          <label for="inicioExpediente">Horário de Início do Expediente:</label>
          <input
            class="form-control"
            type="text"
            v-model="config.inicioExpediente"
            name="inicioExpediente"
            id="inicioExpediente"
            required
            v-mask="'##:##:##'"
            placeholder="HH:MM:SS"
         />

          <label for="fimExpediente">Horário de Término do Expediente:</label>
          <input
            class="form-control"
            type="text"
            v-model="config.fimExpediente"
            name="fimExpediente"
            id="fimExpediente"
            placeholder="HH:MM:SS"
            v-mask="'##:##:##'"
            required
          />

          <label for="tempoParaDesconto"
            >Tempo para Aplicar Desconto (HH:MM:SS):</label
          >
          <input
          class="form-control"

            type="text"
            v-model="config.tempoParaDesconto"
            name="tempoParaDesconto"
            id="tempoParaDesconto"
            required
            v-mask="'##:##:##'"
            placeholder="HH:MM:SS"
          />

          <br />
          <button
            @click="onClickCadastrar"
            type="button"
            class="btn btn-outline-success"
          >
            Salvar
          </button>
        </form>
      </div>
    </div>
  </div>
</template>
  
  <script>
import axios from "axios";
import NavBar from "@/components/NavBar.vue";
import { Configuracao } from "@/Model/Configuracao";
import { ConfiguracaoClient } from "@/client/Configuracao.client";

export default {
  name: "formulario-cofiguracao",
  components: {
    NavBar,
  },
  data() {
    return {
      config: new Configuracao(),
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
      const configuracaoClient = new ConfiguracaoClient();
      configuracaoClient
        .newMarca(this.config)
        .then((success) => {
          this.config = new Configuracao();
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
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 600px;
  height: 600px;
  background-color: #ffffff;
  border: 1px solid #8e8e8e;
  border-radius: 10px;
}
</style>