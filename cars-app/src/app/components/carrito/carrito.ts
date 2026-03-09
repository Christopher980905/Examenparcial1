import { Component, OnInit } from '@angular/core';
import { Carrito } from '../../model/carrito.model';
//import { CarritoService } from '../../services/carrito';
import { GuestCarritoService } from '../../services/guest-carrito';
//import { CheckoutService } from '../../services/checkout';
import { GuestCheckoutService } from '../../services/guest-checkout';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Pedidos } from '../../model/pedidos';

@Component({
  selector: 'app-carrito',
  standalone: false,
  templateUrl: './carrito.html',
  styleUrls: ['./carrito.css']
})
export class CarritoComponent implements OnInit {
  carrito?: Carrito;
  displayed = ['nombre', 'precio', 'cantidad', 'total', 'acciones'];
  loading = false;
  

  constructor(
    private carritoSrv: GuestCarritoService,
    private checkoutSrv: GuestCheckoutService,
    private snack: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.loading = true;
    this.carritoSrv.get().subscribe({
      next: c => { this.carrito = c; this.loading = false; },
      error: _ => this.loading = false
    });
  }

  updateCantidad(idItem: number, cantidad: number) {
    if (cantidad <= 0) return;
    this.carritoSrv.updateItem(idItem, cantidad).subscribe({
      next: c => { this.carrito = c; this.snack.open('Cantidad actualizada', 'OK'
                    , { duration: 1200 }); },
      error: err => Swal.fire('Error', err?.error?.message || 'No se pudo actualizar', 'error')
    });
  }

  remove(idItem: number) {
    Swal.fire({
      title: 'Quitar ítem',
      text: '¿Seguro que deseas quitar este ítem?',
      icon: 'warning', showCancelButton: true, confirmButtonText: 'Sí, quitar'
    }).then(res => {
      if (res.isConfirmed) {
        this.carritoSrv.removeItem(idItem).subscribe({
          next: _ => this.reload()
        });
      }
    });
  }

  clear() {
    Swal.fire({
      title: 'Vaciar carrito', text: 'Se eliminarán todos los ítems',
      icon: 'warning', showCancelButton: true, confirmButtonText: 'Vaciar'
    }).then(r => { if (r.isConfirmed) this.carritoSrv.clear().subscribe({ 
      next: _ => this.reload() }); });
  }

  checkout() {
    //const clienteId = Number(localStorage.getItem('clienteId') || 1);
    Swal.fire({ title: 'Procesando...', didOpen: () => Swal.showLoading(), allowOutsideClick: false });
    //this.checkoutSrv.checkout(clienteId).subscribe({
    this.checkoutSrv.checkout().subscribe({
      next: (fac: Pedidos) => {
        Swal.close();
        Swal.fire('¡Compra realizada!'
          , `Factura: ${fac.numFactura}\nTotal: $${fac.monto_pagar.toFixed(2)}`, 'success');
        this.reload();
      },
      error: (err: any) => {
        Swal.close();
        Swal.fire('Error', err?.error?.message || 'No se pudo procesar el checkout', 'error');
      }
    });
  }

 
}