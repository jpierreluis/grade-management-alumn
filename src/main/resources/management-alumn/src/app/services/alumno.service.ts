import { Injectable } from '@angular/core';
import { URL_BACKEND } from '../utils/config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Grade } from '../models/grade';
import { Materia } from '../models/materia';
import { Alumno } from '../models/alumno';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService {

  private endPoind:string= URL_BACKEND + "/api/alumno/"

  constructor(private http:HttpClient) { }

  getElement(id:string):Observable<any>{
    return this.http.get<any>(`${this.endPoind}id?id=${id}`);
  }

  save(alumno:Alumno):Observable<any>{
    return this.http.post<any>(`${this.endPoind}`,alumno);
  }

  delete(id:string):Observable<any>{
    return this.http.delete<any>(`${this.endPoind}?id=${id}`);
  }

  getAll():Observable<Alumno[]>{
    return this.http.get<Alumno[]>(`${this.endPoind}`);
  }
}
