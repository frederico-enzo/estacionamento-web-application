<template>
    <NavBar/>
    <div class="overlay">
    <div
        id="popup"
        class="popup-container"
        :style="{ height: mensagem.ativo ? '420px' : '330px' }"
      >
      <br>
        <h3>Condutor</h3>
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
              v-model="condutor.nome"
              class="form-control"
              type="text"
              placeholder="Nome"
            />
            <input
              v-model="condutor.cpf"
              class="form-control"
              type="text"
              placeholder="CPF"
              v-mask="'###.###.###-##'"
              :mask-reverse="true"
              maxlength="14"
            />
            <input
              v-model="condutor.telefone"
              v-mask="'(##)#####-####'"
              :mask-reverse="true"
              class="form-control"
              type="text"
              placeholder="Telefone"
              maxlength="14"
            />
            <br />
            <button
              @click="onClickCadastrar(condutor)"
              type="button"
              class="btn btn-outline-success"
            >
              Salvar
            </button>
          </form>
      </div>
    </div>
</template>

<script>
import { Condutor } from "@/Model/Condutor";
import { CondutorClient } from "@/client/Condutor.client";
import NavBar from "@/components/NavBar.vue"

export default {
    components: {
    NavBar,
  },
  data() {
    return {
      exibir: false,
      condutor: new Condutor(),
      mensagem: {
        ativo: false,
        titulo: "",
        mensagem: "",
        css: "",
      },
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
      const condutorClient = new CondutorClient();
      condutorClient
        .newCondutor(this.condutor)
        .then(() => {
          this.condutor = new Condutor();
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "Condutor cadastrado com sucesso";
          this.mensagem.css = "alert alert-success alert-dismissible fade show";
        })
        .catch((error) => {
          this.mensagem.ativo = true;
          this.mensagem.mensagem = "Não foi possível cadastrar o condutor";
          this.mensagem.css = "alert alert-danger alert-dismissible fade show";
        });
    },
    redefinirTamanhoPopup() {
      this.mensagem.ativo = false;
      const popup = document.getElementById("popup");
      if (popup) {
        popup.style.height = "320px";
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
  background-color: rgba(134, 134, 134, 0.5);
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