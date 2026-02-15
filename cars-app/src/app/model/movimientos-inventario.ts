import { Productos } from "./productos"



export interface MovimientosInventario{
    idMovimientoinventario: number
    tipo_movimiento: string
    editorial: string
    cantidad: number
    fecha: Date
    productos: Productos
   
    [key: string]: any;
}