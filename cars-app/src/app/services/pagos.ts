import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pagos } from '../model/pagos';


@Injectable({
  providedIn: 'root',
})
export class PagosService {

private baseUrl = "http://localhost:8081/api/pagos"

  constructor(private http: HttpClient){ }

  findAll(): Observable<Pagos[]>{
    return this.http.get<Pagos[]>(this.baseUrl);
  }

  findOne(id: number): Observable<Pagos>{
    return this.http.get<Pagos>(`${this.baseUrl}/${id}`);
  }

  save(pagos:Pagos): Observable<Pagos>{
    return this.http.post<Pagos>(this.baseUrl, pagos);

  }

  update(id: number, pagos: Pagos): Observable<Pagos>{
    return this.http.put<Pagos>(`${this.baseUrl}/${id}`,pagos);

  }
  
  delete(id:number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  
}
