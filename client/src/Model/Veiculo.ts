import { Cor } from "./Cor";
import { Modelo } from "./Modelo";
import { Tipo } from "./Tipo";
import { AbstractEntity } from "./abstract-entity";

export class Veiculo extends AbstractEntity{

    placa!: String
    ano!: Number
    modeloId!: Modelo
    cor!: Cor
    tipo!: Tipo

}