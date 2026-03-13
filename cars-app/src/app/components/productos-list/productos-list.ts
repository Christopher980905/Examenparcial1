// import { Component, OnInit } from '@angular/core';
// import { Productos } from '../../model/productos';
// import { ProductosService } from '../../services/productos';
// import { GuestCarritoService } from '../../services/guest-carrito';
// import { MatSnackBar } from '@angular/material/snack-bar';
// import Swal from 'sweetalert2';

// @Component({
//   selector: 'app-productos-list',
//   standalone: false,
//   templateUrl: './productos-list.html',
//   styleUrl: './productos-list.css',
// })
// export class ProductosListComponent implements OnInit {
//  productos: Productos[] = [];
//   loading = false;

//   constructor(
//     private productoService: ProductosService,
//     private carritoSrv: GuestCarritoService,
//     private snack: MatSnackBar
//   ) {}

//   ngOnInit(): void {
//     this.loading = true;
//     console.log("***** 1 ***********");
//     this.carritoSrv.createOrGet().subscribe(); // asegura carrito
//     console.log("***** 2 ***********");
//     this.productoService.findAll().subscribe({
      
//       next: res => { this.productos = res; this.loading = false; },
//       error: _ => { console.error() }
//     });
//     console.log("***** 3 ***********");
//   }

//   add(producto: Productos) {
//     this.carritoSrv.addItem(producto.idProducto, 1).subscribe({
//       next: _ => this.snack.open('Agregado al carrito', 'OK', { duration: 1500 }),
//       error: err => Swal.fire('Error', err?.error?.message || 'No se pudo agregar', 'error')
//     });
//   }
// }


import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Productos } from '../../model/productos';
import { ProductosService } from '../../services/productos';
import { GuestCarritoService } from '../../services/guest-carrito';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-productos-list',
  standalone: false,
  templateUrl: './productos-list.html',
  styleUrls: ['./productos-list.css']
})
export class ProductosListComponent implements OnInit {
  productos: Productos[] = [];
  loading = false;

  constructor(
    private productoService: ProductosService,
    private carritoSrv: GuestCarritoService,
    private snack: MatSnackBar,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.loading = true;

    this.productoService.findAll()
      .pipe(finalize(() => this.loading = false))
      .subscribe({
        next: (res) => {
          console.log(res);
          this.productos = res ?? [];
          this.loading = false;
           this.cdr.detectChanges(); 
        },
        error: (err) => {
          console.error('Error al cargar productos:', err);
          this.productos = [];
          Swal.fire('Error', 'No se pudieron cargar los productos', 'error');
        }
      });
  }

  add(producto: Productos): void {
    this.carritoSrv.addItem(producto.idProducto, 1).subscribe({
      next: () => {
       /*  this.snack.open('Agregado al carrito', 'OK', { duration: 1500 }); */

        // descontar stock en pantalla
      if (producto.stock > 0) {
        producto.stock = producto.stock - 1;
      }

      this.snack.open('Agregado al carrito', 'OK', { duration: 1500 });
      this.cdr.detectChanges(); // refresca la vista



      },
      error: (err) => {
        Swal.fire('Error', err?.error?.message || 'No se pudo agregar', 'error');
      }
    });
  }
}