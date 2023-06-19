<template>
    <div class="main">
        <h5>FILTROS</h5>
        <div class="box-one">
          <div class="form-check form-switch">
            <input
              class="form-check-input"
              type="checkbox"
              id="flexSwitchCheckDefault"
            />
            <label class="form-check-label" for="flexSwitchCheckDefault"
              >Marcas</label
            >
          </div>
          <div class="form-check form-switch">
            <input
              class="form-check-input"
              type="checkbox"
              id="flexSwitchCheckChecked"
              checked
            />
            <label class="form-check-label" for="flexSwitchCheckChecked"
              >Modelos</label
            >
          </div>
          <div class="form-check form-switch">
            <input
              class="form-check-input"
              type="checkbox"
              id="flexSwitchCheckChecked"
              checked
            />
            <label class="form-check-label" for="flexSwitchCheckChecked"
              >Condutores</label
            >
          </div>
          <div class="form-check form-switch">
            <input
              class="form-check-input"
              type="checkbox"
              id="flexSwitchCheckChecked"
              checked
            />
            <label class="form-check-label" for="flexSwitchCheckChecked"
              >Veiculos</label
            >
          </div>
          <div class="form-check form-switch">
            <input
              class="form-check-input"
              type="checkbox"
              id="flexSwitchCheckChecked"
              checked
            />
            <label class="form-check-label" for="flexSwitchCheckChecked"
              >Movimentações</label
            >
          </div>
        </div>
        
        <div class="box">
          <div class="form-check form-switch">
            <input
              class="form-check-input"
              type="checkbox"
              id="flexSwitchCheckChecked"
              checked
            />
            <label class="form-check-label" for="flexSwitchCheckChecked"
              >Todos</label
            >
          </div>
          <div class="form-check form-switch">
            <input
              class="form-check-input"
              type="checkbox"
              id="flexSwitchCheckChecked"
              checked
            />
            <label class="form-check-label" for="flexSwitchCheckChecked"
              >Ativos</label
            >
          </div>
        </div>
      </div>
</template>

<style scoped>
.main{
    width: 400px;
    margin-top:25px ;
    gap: 8px;
    display: flex;
    flex-direction: column;
    align-content: center;
    align-items: center;
}
.box-one{
  padding-left:30px ;
  border-radius: 15px;
  display: flex;
  flex-direction:column ;
  justify-content: center;
  width: 220px;
  height: 250px;
  border: solid 1px rgb(180, 180, 180);
}
.box{
  border-radius: 15px;
  display: flex;
  flex-direction:column ;
  align-items: center;
  justify-content: center;
  width: 220px;
  height: 100px;
  border: solid 1px rgb(180, 180, 180);
}

</style>