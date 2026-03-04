import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductosComponent } from './components/productos/productos';
import { ClienteComponent } from './components/cliente/cliente';
import { CategoriaComponent } from './components/categoria/categoria';
import { PagosComponent } from './components/pagos/pagos';
import { MovimientosInventarioComponent } from './components/movimientos-inventario/movimientos-inventario';
import { CarritoComponent } from './components/carrito/carrito';
import { ProductosListComponent } from './components/productos-list/productos-list';

const routes: Routes = [

    {path: '', redirectTo: 'productoslist', pathMatch: 'full'},
    {path: 'productoslist', component: ProductosListComponent},
    {path: 'productos', component: ProductosComponent},
    {path: 'carrito', component: CarritoComponent},
    {path: 'clientes', component: ClienteComponent},
    {path: 'categorias', component: CategoriaComponent},
    {path: 'pagos', component: PagosComponent},
    {path: 'movimientos-inventarios',component: MovimientosInventarioComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
