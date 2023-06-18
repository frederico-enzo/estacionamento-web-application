<template>
  <div>
    <button class="btn b" @click="exibirPopup"><img width="35" src="../assets/384e0b3361d99d9c370b4037115324b9-flat-vintage-car-icon-by-vexels.png" alt="">
</button>
    <div class="overlay" v-if="exibir">
        <div id="popup" v-show="exibir" class="popup-container">
          <div class="final">
            <h4>NOVA MARCA</h4>
          </div>
          <form class="popup-form" @submit.prevent="cadastrarItem">
            <div class="form-group">
              <label>NOME:</label>
              <input class="form-control" type="text" v-model="nome" required />
              <button class="btn btn-outline-secondary edt" type="submit">
                Enviar
              </button>
              <button
                class="btn btn-outline-secondary edt-two"
                @click.prevent="fecharPopup"
              >
                X
              </button>
            </div>
          </form>
        </div>
    </div>
  </div>
</template>
    
    <script>
import axios from "axios";

export default {
  data() {
    return {
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
  },
};
</script>
    
  
    <style scoped>
    .b{
        border: none;
    }
h4 {
  padding-left: 100px;
}
.final {
  display: flex;
  justify-content: space-between;
}
.center {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0;
  padding: 0;
}
.form-group {
  display: flex;
  align-items: center;
  gap: 10px;
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
article {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  height: 200px;
  background-color: #ffffff;
  border: 1px solid #8e8e8e;
  border-radius: 5px;
  padding: 20px;
}
.btn {
  width: 40px;
}

.popup-form {
  margin-bottom: 20px;
}
label {
  display: flex;
  align-items: center;
}
.form-group {
  margin-top: 25px;
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input[type="date"] {
  width: 100%;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.edt {
  margin: 10px;
  border-color: #8bbf32;
  border-color: #8bbf32;
  color: black;
  width: 150px;
}
.edt:hover {
  border: none;

  background-color: #8bbf32;
  color: #fff;
}
.edt-two {
  font-size: 13px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-color: #ff0000;
  color: #ff0000;
  width: 34px;
  border-radius: 10px;
}
.edt-two:hover {
  border: none;
  background-color: #bd3939;
  color: #fff;
}
</style>