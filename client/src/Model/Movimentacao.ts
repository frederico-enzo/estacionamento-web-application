import { Condutor } from "./Condutor";
import { Veiculo } from "./Veiculo";
import { AbstractEntity } from "./abstract-entity";

export class Movimentacao extends AbstractEntity{

    condutor!: Condutor
    veiculo!: Veiculo
        

}