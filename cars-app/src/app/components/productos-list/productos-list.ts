import { Component, OnInit } from '@angular/core';
import { Productos } from '../../model/productos';
import { ProductosService } from '../../services/productos';
import { GuestCarritoService } from '../../services/guest-carrito';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-productos-list',
  standalone: false,
  templateUrl: './productos-list.html',
  styleUrl: './productos-list.css',
})
export class ProductosListComponent implements OnInit {
 productos: Productos[] = [];
  loading = false;

  constructor(
    private productoService: ProductosService,
    private carritoSrv: GuestCarritoService,
    private snack: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loading = true;
    this.carritoSrv.createOrGet().subscribe(); // asegura carrito
    this.productoService.findAll().subscribe({
      next: res => { this.productos = res; this.loading = false; },
      error: _ => { this.loading = false; }
    });
  }

  add(producto: Productos) {
    this.carritoSrv.addItem(producto.idProducto, 1).subscribe({
      next: _ => this.snack.open('Agregado al carrito', 'OK', { duration: 1500 }),
      error: err => Swal.fire('Error', err?.error?.message || 'No se pudo agregar', 'error')
    });
  }
}
