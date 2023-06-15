import { AbstractEntity } from "./abstract-entity";

export class Configuracao extends AbstractEntity{

    valorHora!: BigInt
    valorMultaMinuto!: BigInt
    inicioExpediente!: Date
    fimExpediente!: Date
    tempoParaDesconto!: Date
    tempoDeDesconto!: Date
    gerarDesconto!: boolean
    vagasCarro!: BigInteger
    vagasMoto!: BigInteger
    vagosVan!: BigInteger

}