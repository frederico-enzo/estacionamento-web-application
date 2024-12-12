<template>
    <div class="container w-75 p-4 rounded shadow-sm bg-light">
        <div class="row mb-4">
            <div class="col text-start">
                <h4 class="text-primary">Detalhes do Modelo</h4>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <p class="fw-bold text-secondary">Nome: <span class="fw-normal text-dark">{{ modelo.nome }}</span></p>
            </div>
            <div class="col">
                <p class="fw-bold text-secondary">Marca: <span class="fw-normal text-dark">{{ marca.nome }}</span></p>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col">
                <p class="fw-bold text-secondary">Status:
                    <span v-if="modelo.ativo" class="badge bg-success">Ativo</span>
                    <span v-else class="badge bg-danger">Desativado</span>
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
                <p class="fw-bold text-secondary">Data do Cadastro: <span class="fw-normal text-dark">{{
                    formatDate(modelo.cadastro) }}</span></p>
            </div>
            <div class="col">
                <p class="fw-bold text-secondary">Última Edição: <span class="fw-normal text-dark">{{
                    formatDate(modelo.edicao) }}</span></p>
            </div>
        </div>

        <div class="row">
            <div class="col d-flex justify-content-end">
                <router-link class="btn btn-secondary" to="/modelo">Voltar</router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { Modelo } from "@/model/Modelo";
import { ModeloClient } from "@/service/Modelo.client";
import { Marca } from "@/model/Marca";
import { MarcaClient } from "@/service/Marca.client";
import { defineComponent } from "vue";

export default defineComponent({
    name: "ModeloInfo",
    data() {
        return {
            modelo: new Modelo(),
            marca: new Marca()
        };
    },
    computed: {
        id() {
            return this.$route.query.id;
        },
    },
    mounted() {
        if (this.id !== undefined) {
            this.loadModelo(Number(this.id));
        }
    },
    methods: {
        formatDate(dateString: string | number | Date) {
            if (dateString) {
                const dateTime = new Date(dateString);
                return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString([], {
                    hour: "2-digit",
                    minute: "2-digit",
                })}`;
            }
            return "---";
        },
        loadModelo(id: number) {
            const modeloClient = new ModeloClient();
            modeloClient
                .findById(id)
                .then((success) => {
                    this.modelo = success;
                    this.loadMarca(this.modelo.marca.id);
                })
                .catch((error) => {
                    console.error("Erro ao carregar modelo:", error);
                });
        },
        loadMarca(id: number) {
            const marcaClient = new MarcaClient();
            marcaClient
                .findById(id)
                .then((success) => {
                    this.marca = success;
                })
                .catch((error) => {
                    console.error("Erro ao carregar marca:", error);
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

h4,
h5 {
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
