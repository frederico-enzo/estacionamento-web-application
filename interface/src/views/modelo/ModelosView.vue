<template>
    <div class="container-fluid p-4">
        <div class="row mb-4">
            <div class="col-md-10 text-start">
                <p class="fs-4 fw-semibold">Modelo</p>
            </div>
            <div class="col-md-2 align-self-center">
                <div class="d-grid gap-2">
                    <router-link type="button" class="btn btn-primary" to="/modelos/formulario">Cadastrar</router-link>
                </div>
            </div>
        </div>
        <div v-if="List.length == 0" class="alert alert-secondary" role="alert">
            Nenhuma Modelo encontrada
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { Modelo } from '@/model/Modelo'
import { ModeloClient } from '@/service/Modelo.client'

export default defineComponent({
    name: 'VeiculosView',
    data() {
        return {
            List: new Array<Modelo>()
        }
    },
    mounted() {
        this.findAll()
    },
    methods: {
        findAll() {
            const client = new ModeloClient()
            client
                .findAll()
                .then(sucess => {
                    this.List = sucess
                })
                .catch(error => {
                    console.log("Error!!")
                })
        }
    }
})
</script>

<style scoped>
.container-fluid {
    background-color: #f7f7f8;
    border-radius: 10px;
    padding: 2rem;
}

.btn-primary {
    background-color: #0078d4;
    border: none;
    border-radius: 5px;
    font-weight: 500;
    transition: all 0.3s ease-in-out;
}

.btn-primary:hover {
    background-color: #005a9e;
}
</style>
