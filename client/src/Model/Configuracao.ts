import { AbstractEntity } from "./abstract-entity";

export class Configuracao extends AbstractEntity {

    valorHora!: Number
    valorMultaMinuto!: Number
    inicioExpediente!: Date
    fimExpediente!: Date
    tempoParaDesconto!: Date
    tempoDeDesconto!: Date
    gerarDesconto!: boolean
    vagasCarro!: Number
    vagasMoto!: Number
    vagosVan!: Number
    editMode?: boolean;

}