import { AbstractEntity } from './abstract-entity'
import { Condutor } from './Condutor' 
import { Veiculo } from './Veiculo'

export class Movimentacao extends AbstractEntity {
  dataEntrada!: Date
  dataSaida!: Date
  tempoEstacionadoSegundos!: number
  tempoMultaSegundos!: number
  tempoDescontoSegundos!: number
  veiculo!: Veiculo
  condutor!: Condutor
  valorHora!: number
  valorMulta!: number
  valorTotal!: number
}
