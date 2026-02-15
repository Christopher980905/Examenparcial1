import { Injectable } from '@angular/core';
import { Productos } from '../model/productos';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductosService {
   private baseUrl = "http://localhost:8081/api/productos"

  constructor(private http: HttpClient){ }

  findAll(): Observable<Productos[]>{
    return this.http.get<Productos[]>(this.baseUrl);
  }

  findOne(id: number): Observable<Productos>{
    return this.http.get<Productos>(`${this.baseUrl}/${id}`);
  }

  save(Productos:Productos): Observable<Productos>{
    return this.http.post<Productos>(this.baseUrl, Productos);

  }

  update(id: number, Productos: Productos): Observable<Productos>{
    return this.http.put<Productos>(`${this.baseUrl}/${id}`,Productos);

  }
  
  delete(id:number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  
}
