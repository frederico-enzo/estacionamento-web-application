<template>
  <div class="container w-75 p-4 rounded shadow-sm bg-light">
    <div class="row mb-4">
      <div class="col text-start">
        <h4 class="text-primary">Detalhes do Condutor</h4>
      </div>
    </div>

    <div class="row mb-3">
      <div class="col">
        <p class="fw-bold text-secondary">Nome: <span class="fw-normal text-dark">{{ condutor.nome }}</span></p>
      </div>
      <div class="col">
        <p class="fw-bold text-secondary">Telefone: <span class="fw-normal text-dark">{{ condutor.telefone }}</span></p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <p class="fw-bold text-secondary">CPF: <span class="fw-normal text-dark">{{ condutor.cpf }}</span></p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <p class="fw-bold text-secondary">Total Tempo Pago: <span class="fw-normal text-dark">{{ segundosParaHoras(condutor.tempoPagoSegundos) }}h</span></p>
      </div>
      <div class="col">
        <p class="fw-bold text-secondary">Tempo de Desconto Disponível: <span class="fw-normal text-dark">{{ segundosParaHoras(condutor.tempoDescontoSegundos) }}h</span></p>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <p class="fw-bold text-secondary">Tempo de Desconto Usado: <span class="fw-normal text-dark">{{ segundosParaHoras(condutor.tempoDescontoUsadoSegundos) }}h</span></p>
      </div>
      <div class="col">
        <p class="fw-bold text-secondary">Total Tempo Estacionado: <span class="fw-normal text-dark">{{ segundosParaHoras(condutor.tempoDescontoUsadoSegundos + condutor.tempoPagoSegundos) }}h</span></p>
      </div>
    </div>
    <div class="row mb-4">
      <div class="col">
        <p class="fw-bold text-secondary">Status:
          <span v-if="condutor.ativo" class="badge bg-success">Ativo</span>
          <span v-if="!condutor.ativo" class="badge bg-danger">Desativado</span>
        </p>
      </div>
    </div>

    <hr class="my-4">

    <div class="row mb-4">
      <div class="col text-start">
        <h5 class="text-primary">Histórico</h5>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <p class="fw-bold text-secondary">Data do Cadastro: <span class="fw-normal text-dark">{{ formatDate(condutor.cadastro) }}</span></p>
      </div>
      <div class="col">
        <p class="fw-bold text-secondary">Última Edição: <span class="fw-normal text-dark">{{ formatDate(condutor.edicao) }}</span></p>
      </div>
    </div>

    <div class="row">
      <div class="col d-flex justify-content-end">
        <router-link class="btn btn-secondary" to="/condutor">Voltar</router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Condutor } from "@/model/Condutor";
import { CondutorClient } from "@/service/Condutor.client";
import { defineComponent } from "vue";

export default defineComponent({
  name: "VeiculoInfo",
  data() {
    return {
      condutor: new Condutor(),
    };
  },
  computed: {
    id() {
      return this.$route.query.id;
    },
  },
  mounted() {
    if (this.id !== undefined) {
      this.findById(Number(this.id));
    }
  },
  methods: {
    segundosParaHoras(segundos: number) {
      const horas = Math.floor(segundos / 3600);
      const minutos = Math.floor((segundos % 3600) / 60);
      return `${horas.toString().padStart(2, "0")}:${minutos.toString().padStart(2, "0")}`;
    },
    formatDate(dateString: string | number | Date) {
      if (dateString) {
        const dateTime = new Date(dateString);
        return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
        })}`;
      } else {
        return "---";
      }
    },
    findById(id: number) {
      const condutorClient = new CondutorClient();
      condutorClient
        .getCondutorById(id)
        .then((sucess) => {
          this.condutor = sucess;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
});
</script>

<style scoped lang="scss">
.container {
  background-color: #ffffff;
  border: 1px solid #dee2e6;
}

h4, h5 {
  font-weight: bold;
}

p {
  margin-bottom: 0.5rem;
}

.badge {
  font-size: 1rem;
}

hr {
  border-top: 2px solid #dee2e6;
}
</style>
