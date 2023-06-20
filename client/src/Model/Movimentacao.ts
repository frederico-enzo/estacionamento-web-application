import { Condutor } from "./Condutor";
import { Configuracao } from "./Configuracao";
import { Veiculo } from "./Veiculo";
import { AbstractEntity } from "./abstract-entity";

export class Movimentacao extends AbstractEntity{

    condutor!: Condutor
    veiculo!: Veiculo
    configuracao!: Configuracao
    entrada!: Date
    saida!: Date
    tempoDesconto!: Date
    tempo!: Date
    tempoMulta!: Date
    valorMulta!: BigInt
    valorTotal!: BigInt
    valorHora!: BigInt
    valorDesconte!: BigInt

}