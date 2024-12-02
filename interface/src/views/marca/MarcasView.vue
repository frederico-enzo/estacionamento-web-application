<template>
    <div class="container-fluid p-4">
        <div class="row mb-4">
            <div class="col-md-10 text-start">
                <p class="fs-4 fw-semibold">Marca</p>
            </div>
            <div class="col-md-2 align-self-center">
                <div class="d-grid gap-2">
                    <router-link type="button" class="btn btn-primary" to="/marca/formulario">Cadastrar</router-link>
                </div>
            </div>
        </div>
        <div v-if="List.length == 0" class="alert alert-secondary" role="alert">
            Nenhuma Marca encontrada
        </div>
        <div v-else>
            <table class="table table-striped table-hover rounded-3 shadow-sm">
                <thead class="table-light">
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center">Nome</th>
                        <th class="text-center">Status</th>
                        <th class="text-center">Opções</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in List" :key="item.id" class="hover-shadow">
                        <td class="align-middle text-center">{{ item.id }}</td>
                        <td class="align-middle text-center">{{ item.nome }}</td>
                        <td class="align-middle text-center">
                            <span v-if="item.ativo" class="badge bg-success">Ativo</span>
                            <span v-if="!item.ativo" class="badge bg-danger">Inativo</span>
                        </td>
                        <td class="align-middle text-center">
                            <BotoesAcoes listarRoute="marca.listar" editarRoute="marca.form.editar"
                                toggleRoute="marca.form.toggle" :isAtivo="item.ativo" :id="item.id">
                            </BotoesAcoes>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { Marca } from '@/model/Marca'
import { MarcaClient } from '@/service/Marca.client'
import BotoesAcoes from '@/components/BotoesAcoes.vue'

export default defineComponent({
    name: 'VeiculosView',
    data() {
        return {
            List: new Array<Marca>()
        }
    },
    components: {
        BotoesAcoes
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
