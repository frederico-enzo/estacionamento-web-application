<template>
    <div class="table-tape">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Placa</th>
            <th scope="col">Modelo</th>
            <th scope="col">Cor</th>
            <th scope="col">Tipo</th>
            <th scope="col">Ano</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td scope="col">WFQ-1505</td>
            <td scope="col">Civic</td>
            <td scope="row">Vermelho</td>
            <td scope="row">Carro</td>
            <td scope="row">2014</td>
            <td><span class="btn btn-success">...</span></td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button>
            </td>
          </tr>
          <tr>
            <td scope="col">TQW-0066</td>
            <td scope="col">Uno</td>
            <td scope="row">Verde</td>
            <td scope="row">Carro</td>
            <td scope="row">1997</td>
            <td><span class="btn btn-danger">...</span></td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button>
            </td>
          </tr>
          <tr>
            <td scope="col">JWB-8061</td>
            <td scope="col">X6</td>
            <td scope="row">Azul</td>
            <td scope="row">Carro</td>
            <td scope="row">2017</td>
            <td><span class="btn btn-success">...</span></td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <style scoped>
  .table-tape{
    width: 1000px;
    padding-top:60px ;
  
  }
  </style>