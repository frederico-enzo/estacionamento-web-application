import { AbstractEntity } from './abstract-entity'
import { Marca } from './Marca'

export class Modelo extends AbstractEntity {
  nome!: string
  marca!: Marca
}
