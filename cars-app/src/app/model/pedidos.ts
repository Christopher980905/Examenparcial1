
import { Cliente } from "./cliente.model";
import { Pagos } from "./pagos";

//productos carros

export interface Pedidos{
    fecha_pedido: string
    estado: string
    total_neto: number
    iva: number
    monto_pagar: number

    cliente: Cliente
    pago: Pagos
   
    [key: string]: any;
}