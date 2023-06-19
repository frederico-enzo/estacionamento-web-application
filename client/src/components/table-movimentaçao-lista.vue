<template>
  <div class="table-tape">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Placa</th>
          <th scope="col">Cpf</th>
          <th scope="col">Entrada</th>
          <th scope="col">Status</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td scope="row">QSW-1501</td>
          <td>333.333.333-87</td>
          <td>12-05-2023 15:30</td>
          <td><span class="btn btn-success">...</span></td>
          <td>
            <button type="button" class="btn btn-primary">finalizar</button>
            <button type="button" class="btn btn-warning">✏️</button>
          </td>
        </tr>
        <tr>
          <td scope="row">FWS-0023</td>
          <td>222.222.222-21</td>
          <td>12-05-2023 08:47</td>
          <td><span class="btn btn-danger">...</span></td>
          <td>
            <button type="button" class="btn btn-primary">finalizar</button>
            <button type="button" class="btn btn-warning">✏️</button>
          </td>
        </tr>
        <tr>
          <td scope="row">GQJ-8068</td>
          <td>111.111.111-51</td>
          <td>12-05-2023 16:48</td>
          <td><span class="btn btn-success">...</span></td>
          <td>
            <button type="button" class="btn btn-primary">finalizar</button>
            <button type="button" class="btn btn-warning">✏️</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style>
.table-tape{
  width: 1100px;
  padding-top:60px ;

}
</style>