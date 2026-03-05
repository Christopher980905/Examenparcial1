import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environments';
import { Observable } from 'rxjs';
import { Carrito } from '../model/carrito.model';
import { getCartToken } from '../core/cart-token';

@Injectable({ providedIn: 'root' })
export class GuestCarritoService {
  private base = `${environment.baseUrl}/guest/cart`;
  constructor(private http: HttpClient) {}

  private paramsWithToken(): { params: HttpParams } {
    const token = getCartToken();
    return { params: new HttpParams().set('token', token) };
  }

  /** Crear o recuperar carrito guest */
  createOrGet(): Observable<Carrito> {
    return this.http.post<Carrito>(this.base, {}, this.paramsWithToken());
  }

  /** Obtener carrito */
  get(): Observable<Carrito> {
    return this.http.get<Carrito>(this.base, this.paramsWithToken());
  }

  /** Agregar item */
  addItem(productoId: number, cantidad: number): Observable<Carrito> {
    const body = { productoId, cantidad };
    return this.http.post<Carrito>(`${this.base}/items`, body, this.paramsWithToken());
  }

  /** Actualizar cantidad */
  updateItem(carritoItemId: number, cantidad: number): Observable<Carrito> {
    const body = { cantidad };
    return this.http.put<Carrito>(`${this.base}/items/${carritoItemId}`, body, this.paramsWithToken());
  }

  /** Remover item */
  removeItem(carritoItemId: number) {
    return this.http.delete<void>(`${this.base}/items/${carritoItemId}`, this.paramsWithToken());
  }

  /** Vaciar carrito */
  clear() {
    return this.http.delete<void>(`${this.base}/clear`, this.paramsWithToken());
  }
}
