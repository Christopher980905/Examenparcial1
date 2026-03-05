import { Productos } from './productos';
export interface CarritoItem {
  idCarritoItem?: number;
  productos: Productos;
  cantidad: number;
  precioUnitario: number;
  total: number;
}