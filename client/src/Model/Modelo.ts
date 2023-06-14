import { Marca } from './Marca'
import {AbstractEntity} from './abstract-entity'

export class Modelo extends AbstractEntity{

    nome!: String
    marcaId!: Marca

}