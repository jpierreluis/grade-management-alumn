import { Component, OnInit } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';
import { Grade } from 'src/app/models/grade';
import { Materia } from 'src/app/models/materia';
import { AsignarMateriaService } from 'src/app/services/asignar-materia.service';

@Component({
  selector: 'app-asignar-materia',
  templateUrl: './asignar-materia.component.html',
  styleUrls: ['./asignar-materia.component.css']
})
export class AsignarMateriaComponent implements OnInit {
  public list: any[] = [];

  public materias: Materia[] = [];
  public selMateria: Materia = new Materia();

  public alumnos: Alumno[] = [];
  public selAlumno: Alumno = new Alumno();

  public isEdit = false;
  public grade: Grade = new Grade();

  public titlealert = "";
  public messagealert = "";
  public isAlert = false;

  public numCalificacion: number = 0;

  constructor(private asignarMateriaService: AsignarMateriaService) { }

  ngOnInit(): void {
    this.update();
    this.updateMateria();
    this.updateAlumnos();
  }

  updateMateria() {
    this.list = [];
    this.asignarMateriaService.getMaterias()
      .subscribe(response => {
        console.log(response);
        this.materias = response;
      });
  }

  updateAlumnos() {
    this.list = [];
    this.asignarMateriaService.getAlumnos()
      .subscribe(response => {
        console.log(response);
        this.alumnos = response;
      });
  }


  update() {
    this.list = [];
    this.asignarMateriaService.getAll()
      .subscribe(response => {
        this.list = response;
      });
  }

  isGrade(grade: any) {
    return grade.nombre != undefined;
  }

  clickToGrade(grade: Grade) {
    this.grade = grade;
    this.isEdit = true;
  }

  clickToDeleteGrade(grade: Grade) {
    if (confirm("¿Deseas continuar ?")) {
      this.asignarMateriaService.delete(grade).subscribe(response => {
        console.log(response);
        this.update();
      });
    }
  }

  clickToCancel() {
    this.isEdit = false;
    this.grade = new Grade();
  }

  closeAlert() {
    this.isAlert = false;
  }

  openAlert(title: string, message: string) {
    this.isAlert = true;
    this.titlealert = title;
    this.messagealert = message;
    setTimeout(()=>{
      this.closeAlert();
    },5000);
  }

  clickToSave() {
    if (this.selAlumno.nombre != "" && this.selMateria.nombre != "") {
      if (this.numCalificacion >= 0 && this.numCalificacion <= 10 && this.selAlumno.id_t_usuarios) {
        this.asignarMateriaService.create(this.selAlumno.id_t_usuarios, this.selMateria.id, this.numCalificacion)
          .subscribe(response => {
            this.update();
          },error=>{
            let resp = error.error;
            this.openAlert(resp.success,resp.msg);
          });
      } else {
        this.openAlert("Error", "La calificacion debe estar entre 0 y 10");
      }
    } else {
      this.openAlert("Error", "Selecciona un alumno y su materia");
    }
  }

  clickToChange() {
    if (this.grade.calificacion >= 0 && this.grade.calificacion <= 10) {
      this.asignarMateriaService.change(this.grade.id_t_calificaciones, this.grade.calificacion)
        .subscribe(response => {
          this.update();
          this.isEdit = false;
        });
    } else {
      this.openAlert("Error", "La calificacion debe estar entre 0 y 10");
    }
  }
}
