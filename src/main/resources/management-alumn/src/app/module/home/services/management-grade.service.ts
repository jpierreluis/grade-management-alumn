import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { URL_BACKEND } from '../../../shared/utilities/config';
import { Grade } from 'src/app/core/models/grade';
import { Materia } from 'src/app/core/models/materia';
import { Alumno } from 'src/app/core/models/alumno';


@Injectable({
  providedIn: 'root'
})
export class ManageGradeService {

  private endPoind:string= URL_BACKEND + "/api"

  constructor(private http:HttpClient) { }

  getElement(id:number):Observable<any>{
    return this.http.get<any>(`${this.endPoind}/${id}`);
  }

  delete(grade:Grade):Observable<void>{
    return this.http.delete<any>(this.endPoind + "/?id_t_calificaciones=" + grade.id_t_calificaciones);
  }

  getAll():Observable<any[]>{
    return this.http.get<any[]>(`${this.endPoind}/`);
  }

  create(idalumno:number,idmateria:number,calificacion:number):Observable<any>{
    return this.http.post<any>(`${this.endPoind}/?id_t_usuario=${idalumno}&id_t_materias=${idmateria}&calificacion=${calificacion}`,null);
  }

  change(idcalif:number,calificacion:number):Observable<any>{
    return this.http.put<any>(`${this.endPoind}/?id_t_calificaciones=${idcalif}&calificacion=${calificacion}`,null);
  }

  getMaterias():Observable<Materia[]>{
    return this.http.get<Materia[]>(`${this.endPoind}/materias`);
  }

  getAlumnos():Observable<Alumno[]>{
    return this.http.get<Alumno[]>(`${this.endPoind}/alumnos`);
  }


}
