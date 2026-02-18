import { Categoria } from "./categoria.model"

//productos carros

export interface Productos{
    idProducto: number
    nombre: string
    precio: number
    stock: number
    fondo: string
    fecharegistro: Date
    categoria: Categoria
   

    [key: string]: any;
}