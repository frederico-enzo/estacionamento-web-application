<template>
    <div class="container-fluid p-4">
        <div class="row mt-5">
            <div class="col-md-12 text-center">
                <p v-if="form == undefined" class="fs-5">Cadastrar Marca</p>
                <p v-if="form == 'editar'" class="fs-5">Editar Marca</p>
                <p v-if="form == 'toggle' && condutor.ativo" class="fs-5">Desativar Marca</p>
                <p v-if="form == 'toggle' && !condutor.ativo" class="fs-5">Ativar Marca</p>
            </div>
        </div>

        <AvisoComponent :ativo="mensagem.ativo" :sucesso="mensagem.status" :mensagem="mensagem.mensagem">
        </AvisoComponent>

        <div class="d-flex flex-column align-items-center justify-content-center gap-3">
            <div class="form-floating mb-3 col-8">
                <input id="nome" type="text" :disabled="form === 'toggle' ? '' : disabled"
                    class="form-control rounded-3 input-interativo" v-on:keyup.enter="onClickCadastrar()"
                    v-model="marca.nome" />
                <label for="nome" class="form-label">Nome da Marca</label>
            </div>
        </div>
        <div class="row d-flex justify-content-center gap-3">
            <div class="col-md-3">
                <div class="d-grid gap-2">
                    <router-link type="button" class="btn btn-secondary btn-lg rounded-3 btn-back-interativo"
                        to="/marca">Voltar</router-link>
                </div>
            </div>
            <div class="col-md-3">
                <div class="d-grid gap-2">
                    <button v-if="form === undefined" type="button"
                        class="btn btn-primary btn-lg rounded-3 btn-interativo"
                        @click="onClickCadastrar()">Cadastrar</button>
                    <button v-if="form === 'editar'" type="button"
                        class="btn btn-warning btn-lg rounded-3 btn-interativo" @click="onClickEditar()">Editar</button>
                    <button v-if="form === 'toggle' && marca.ativo === true" type="button"
                        class="btn btn-danger btn-lg rounded-3 btn-interativo"
                        @click="onClickExcluir()">Excluir</button>
                    <button v-if="form === 'toggle' && marca.ativo === false" type="button"
                        class="btn btn-success btn-lg rounded-3 btn-interativo" @click="onClickAtivar()">Ativar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">

import AvisoComponent from '@/components/AvisoComponent.vue'
import { defineComponent } from 'vue'
import { Marca } from "@/model/Marca"
import { MarcaClient } from "@/service/Marca.client"

export default defineComponent({
    name: 'MarcaFormulario',
    data(): any {
        return {
            marca: new Marca(),
            mensagem: {
                ativo: false as boolean,
                status: false as boolean,
                mensagem: '' as string
            }
        }
    },
    components: {
        AvisoComponent
    },
    computed: {
        id(): any {
            return this.$route.query.id
        },
        form(): any {
            return this.$route.query.form
        }
    },
    methods: {
        onClickCadastrar() {
            const client = new MarcaClient()
            client
                .cadastrarMarca(this.marca)
                .then(sucess => {
                    this.model = new Marca()
                    this.mensagem.mensagem = sucess
                    this.mensagem.status = true
                    this.mensagem.ativo = true
                })
                .catch(error => {
                    if (typeof (error.response.data) == 'object') {
                        this.mensagem.mensagem = Object.values(error.response.data)[0]
                    } else {
                        this.mensagem.mensagem = error.response.data
                    }
                    this.mensagem.status = false
                    this.mensagem.ativo = true
                })
        },
        findById(id: number) {
            const client = new MarcaClient()
            client
                .findById(id)
                .then(sucess => {
                    this.condutor = sucess
                })
                .catch(error => {
                    this.mensagem.mensagem = error.response.data
                    this.mensagem.status = false
                    this.mensagem.ativo = true
                })
        },
        onClickEditar() {
            const client = new MarcaClient()
            client
                .atualizarMarca(this.marca, this.marca.id)
                .then(sucess => {
                    this.mensagem.mensagem = sucess
                    this.mensagem.status = true
                    this.mensagem.ativo = true
                })
                .catch(error => {
                    this.mensagem.mensagem = error.response.data
                    this.mensagem.status = false
                    this.mensagem.ativo = true
                })
        },
        onClickExcluir() {
            if (confirm("Tem certeza que deseja desativar esse marca?")) {
                const client = new MarcaClient()
                client
                    .desativar(this.marca.id)
                    .then(sucess => {
                        this.condutor.ativo = false
                        this.mensagem.mensagem = sucess
                        this.mensagem.status = true
                        this.mensagem.ativo = true
                    })
                    .catch(error => {
                        this.mensagem.mensagem = error.response.data
                        this.mensagem.status = false
                        this.mensagem.ativo = true
                    })
            }
        },
    }
})
</script>

<style scoped>
.container-fluid {
    margin-top: 15vh;
    width: 50vw;
    height: 50vh;
    background-color: #f7f7f8;
    border-radius: 10px;
    padding: 2rem;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.input-interativo {
    transition: all 0.3s ease;
}

.input-interativo:focus {
    border-color: #0078d4;
    box-shadow: 0 0 5px rgba(0, 120, 212, 0.7);
    transform: scale(1.05);
}

.btn-back-interativo {
    border: none;
    border-radius: none;
    border-radius: 8px;
    transition: all 0.3s ease;
}

.btn-back-interativo:hover {
    border-radius: none;
    background-color: #0078d4;
    ;
    transform: scale(1.05);
}



.btn-interativo {
    border: none;
    border-radius: none;
    border-radius: 8px;
    transition: all 0.3s ease;
}

.btn-interativo:hover {
    border-radius: none;
    background-color: #009c56;
    transform: scale(1.05);
}

.v-mensagem {
    transition: opacity 0.3s ease-in-out;
}

.col-md-8 {
    width: 100%;
}

.mt-5 {
    margin-top: 2rem;
}
</style>