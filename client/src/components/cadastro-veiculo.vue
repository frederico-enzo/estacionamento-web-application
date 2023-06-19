<template>
    <div>
      <button class="btn b  btn-outline-primary" @click="exibirPopup">Veiculo</button>
      <div class="overlay" v-if="exibir">
        <div id="popup" v-show="exibir" class="popup-container">
          <div class="close"><button @click="fecharPopup">x</button></div>
          <h3>Veiculo</h3>
          <div class="form-conteiner">
              <form class="form">
                  <input class="form-control" type="text" placeholder="Placa">
                  <input class="form-control" type="text" placeholder="Modelo">
                  <input class="form-control" type="Number" placeholder="Ano">  
                  <select class="form-control" name="Tipo" id="Tipo">
                    <option value="CARRO">Carro</option>
                    <option value="VAN">Van</option>
                    <option value="MOTO">Moto</option>
                  </select>
                  <select class="form-control"  name="COR" id="Cor">
                    <option value="AZUL">Azul</option>
                    <option value="CINZA">Cinza</option>
                    <option value="MARRON">Marron</option>
                    <option value="PRETO">Preto</option>
                    <option value="PRATA">Prata</option>
                    <option value="BRANCO">Branco</option>
                    <option value="AMARELO">Amarelo</option>
                    <option value="VERDE">Verde</option>
                  </select>
                  <button type="button" class="btn btn-outline-success">Salvar</button>
              </form>
          </div>
       </div>
      </div>
    </div>
  </template>
  
  <script >
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
  .flex{
      display: flex;
      padding: 15px;
  }
  .flex input{
      width: 25%;
  }
  .close{
      padding: 5px;
      display: flex;
      justify-content: flex-end;
  }
  .close :nth-child(1){
      display: flex;
      height: 25px;
      justify-content: center;
      align-items: center;
      border-color:red;
      color: red;
      border-radius: 100px;
      padding-bottom:5px ;
  }
  .close :nth-child(1):hover{
      display: flex;
      width: 23px;
      height: 25px;
      justify-content: center;
      align-items: center;
      border: none;
      background: red;
      color: white;
      border-radius: 100px;
      padding-bottom:5px ;
  }
  h3{
      display: flex;
      justify-content: center;
  }
  .form{
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
    top: 38%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 400px;
    height: 380px;
    background-color: #ffffff;
    border: 1px solid #8e8e8e;
    border-radius: 10px;
  }
  
  </style>