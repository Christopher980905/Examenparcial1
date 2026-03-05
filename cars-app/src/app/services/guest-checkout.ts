// src/app/services/guest-checkout.service.ts
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environments';
import { Observable } from 'rxjs';
import { Pedidos } from '../model/pedidos';
import { getCartToken } from '../core/cart-token';

@Injectable({ providedIn: 'root' })
export class GuestCheckoutService {
  private base = `${environment.baseUrl}/guest/checkout`;
  constructor(private http: HttpClient) {}

  checkout(): Observable<Pedidos> {
    const params = new HttpParams().set('token', getCartToken());
    return this.http.post<Pedidos>(this.base, {}, { params });
  }
}
