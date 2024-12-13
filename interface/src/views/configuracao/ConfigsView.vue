<template>
    <div class="container-fluid">
        <div class="row mt-5 justify-content-center">
            <div class="col-md-8 text-center">
                <h2 class="fw-bold mb-4">Configurações do Sistema</h2>
            </div>
        </div>

        <AvisoComponent :ativo="mensagem.ativo" :sucesso="mensagem.status" :mensagem="mensagem.mensagem" />

        <div class="row mt-4 justify-content-center">
            <!-- Inputs de Vagas -->
            <div class="col-lg-3 col-md-6 mb-3">
                <div class="form-floating">
                    <input type="number" class="form-control" id="vagasCarros" placeholder="Vagas de Carro" v-model="configuracoes.qntdCarro" />
                    <label for="vagasCarros">Vagas de Carro</label>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-3">
                <div class="form-floating">
                    <input type="number" class="form-control" id="vagasMotos" placeholder="Vagas de Moto" v-model="configuracoes.qntdMoto" />
                    <label for="vagasMotos">Vagas de Moto</label>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-3">
                <div class="form-floating">
                    <input type="number" class="form-control" id="vagasVan" placeholder="Vagas de Van" v-model="configuracoes.qntdVan" />
                    <label for="vagasVan">Vagas de Van</label>
                </div>
            </div>
        </div>

        <!-- Inputs Financeiros -->
        <div class="row mt-3 justify-content-center">
            <div class="col-lg-4 col-md-6 mb-3">
                <div class="form-floating">
                    <input type="text" class="form-control" id="valorHora" placeholder="Valor da Hora" v-model="configuracoes.valorHora" />
                    <label for="valorHora">Valor da Hora Estacionada</label>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-3">
                <div class="form-floating">
                    <input type="text" class="form-control" id="valorMulta" placeholder="Valor da Multa" v-model="configuracoes.valorMulta" />
                    <label for="valorMulta">Valor Multa por Minuto</label>
                </div>
            </div>
        </div>

        <!-- Inputs de Horários -->
        <div class="row mt-3 justify-content-center">
            <div class="col-lg-4 col-md-6 mb-3">
                <div class="form-floating">
                    <input v-maska data-maska="##:##:##" type="text" class="form-control" id="horaAbertura" placeholder="Horário de Abertura" v-model="configuracoes.horaAbertura" />
                    <label for="horaAbertura">Horário de Abertura</label>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-3">
                <div class="form-floating">
                    <input v-maska data-maska="##:##:##" type="text" class="form-control" id="horaFechamento" placeholder="Horário de Fechamento" v-model="configuracoes.horaFechamento" />
                    <label for="horaFechamento">Horário de Fechamento</label>
                </div>
            </div>
        </div>

        <!-- Botões -->
        <div class="row mt-4 justify-content-center">
            <div class="col-md-3 d-grid">
                <router-link type="button" class="btn btn-secondary" to="/">Voltar</router-link>
            </div>
            <div class="col-md-3 d-grid">
                <button v-if="configuracoes.id === undefined" type="button" class="btn btn-primary" @click="onClickConfigurar()">
                    Configurar
                </button>
                <button v-if="configuracoes.id !== undefined" type="button" class="btn btn-warning" @click="onClickEditar()">
                    Editar
                </button>
            </div>
        </div>
    </div>
</template>


<script lang="ts">
import { MaskInput, vMaska } from "maska"
import AvisoComponent from '@/components/AvisoComponent.vue'
import { defineComponent } from 'vue'
import { Configuracao } from "@/model/Configuracao";
import { ConfiguracaoClient } from "@/service/Configuracao.client";

export default defineComponent({
    name: 'ConfigsView',
    data(): any {
        return {
            configuracoes: new Configuracao(),
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
    mounted() {
        new MaskInput("[data-maska]")
        this.getConfiguracao()
    },
    methods: {
        getConfiguracao() {
            const configuracaoClient = new ConfiguracaoClient()
            configuracaoClient.getConfiguracao().then(sucess => {
                this.configuracoes = sucess
                this.mensagem.ativo = false
            }).catch(error => {
                this.mensagem.mensagem = error.response.data
                this.mensagem.status = false
                this.mensagem.ativo = true
            })
        },
        onClickConfigurar() {
            const configuracaoClient = new ConfiguracaoClient()
            configuracaoClient
                .primeiraConfiguracao(this.configuracoes)
                .then(sucess => {
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
        onClickEditar() {
            const configuracaoClient = new ConfiguracaoClient()
            configuracaoClient
                .editar(this.configuracoes)
                .then(sucess => {
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
        }
    }
})
</script>

<style>
.container-fluid {
    padding: 20px;
}

.form-floating label {
    font-size: 0.9rem;
    color: #6c757d;
}

.btn {
    border-radius: 0.5rem;
}
</style>