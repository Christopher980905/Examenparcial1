import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MovimientosInventario } from '../model/movimientos-inventario';

@Injectable({
  providedIn: 'root',
})
export class MovimientosInventarioService {

   private baseUrl = "http://localhost:8081/api/movimientosinventarios"

  constructor(private http: HttpClient){ }

  findAll(): Observable<MovimientosInventario[]>{
    return this.http.get<MovimientosInventario[]>(this.baseUrl);
  }

  findOne(id: number): Observable<MovimientosInventario>{
    return this.http.get<MovimientosInventario>(`${this.baseUrl}/${id}`);
  }

  save(MovimientosInventario:MovimientosInventario): Observable<MovimientosInventario>{
    return this.http.post<MovimientosInventario>(this.baseUrl, MovimientosInventario);

  }

  update(id: number, MovimientosInventario: MovimientosInventario): Observable<MovimientosInventario>{
    return this.http.put<MovimientosInventario>(`${this.baseUrl}/${id}`,MovimientosInventario);

  }
  
  delete(id:number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  
}
