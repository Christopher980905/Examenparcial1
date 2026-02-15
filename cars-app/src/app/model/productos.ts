import { Categoria } from "./categoria.model"

//productos carros

export interface Productos{
    idProducto: number
    nombre: string
    precio: number
    stock: number
    fecharegistro: Date
    categoria: Categoria

    [key: string]: any;
}