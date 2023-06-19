<template>
    <div class="table-tape">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Nome</th>>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td scope="row">Honda</td>
            <td><span class="btn btn-success">...</span></td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button>
            </td>
          </tr>
          <tr>
            <td scope="row">Fiat</td>

            <td><span class="btn btn-danger">...</span></td>
            <td>
              <button type="button" class="btn btn-warning">✏️</button>
            </td>
          </tr>
          <tr>
            <td scope="row">BMW</td>
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
    width: 800px;
    padding-top:60px ;
  
  }
  </style>