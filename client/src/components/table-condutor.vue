<template>
    <div class="table-tape">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Nome</th>
            <th scope="col">Cpf</th>
            <th scope="col">Telefone</th>
            <th scope="col">Tempo de Desconto</th>
            <th scope="col">Tempo Pago</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td scope="col">João</td>
            <td scope="col">138.882.295-87</td>
            <td scope="row">(45) 91912-1123</td>
            <td scope="row">0</td>
            <td scope="row">23hrs</td>
            <td><span class="btn btn-success">...</span></td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button>
            </td>
          </tr>
          <tr>
            <td scope="col">Maria</td>
            <td scope="col">641.681.620-21</td>
            <td scope="row">(45) 81984-0560</td>
            <td scope="row">0</td>
            <td scope="row">03hrs</td>
            <td><span class="btn btn-danger">...</span></td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button>
            </td>
          </tr>
          <tr>
            <td scope="col">Pedro</td>
            <td scope="col">123.886.016.00</td>
            <td scope="row">(45) 91399-0584</td>
            <td scope="row">0</td>
            <td scope="row">29hrs</td>
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