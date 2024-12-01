<template>
    <div class="container-fluid p-4">
        <div class="row mb-4">
            <div class="col-md-10 text-start">
                <p class="fs-4 fw-semibold">Marca</p>
            </div>
            <div class="col-md-2 align-self-center">
                <div class="d-grid gap-2">
                    <router-link type="button" class="btn btn-primary" to="/mascas/formulario">Cadastrar</router-link>
                </div>
            </div>
        </div>
        <div v-if="List.length == 0" class="alert alert-secondary" role="alert">
            Nenhuma Marca encontrada
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { Marca } from '@/model/Marca'
import { MarcaClient } from '@/service/Marca.client'

export default defineComponent({
    name: 'MarcasView',
    data() {
        return {
            List: new Array<Marca>()
        }
    },
    mounted() {
        this.findAll()
    },
    methods: {
        findAll() {
            const client = new MarcaClient()
            client
                .listarAll()
                .then(sucess => {
                    this.List = sucess
                })
                .catch(error => {
                    console.log("erro");
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
</style>
