import { Component, OnInit } from '@angular/core';
import { ManageGradeService } from 'src/app/module/home/services/management-grade.service';
import { Grade } from 'src/app/core/models/grade';
import { Materia } from 'src/app/core/models/materia';
import { Alumno } from 'src/app/core/models/alumno';


@Component({
  selector: 'app-home-table-controller',
  templateUrl: './home-table-controller.component.html',
  styleUrls: ['./home-table-controller.component.css']
})
export class HomeTableControllerComponent implements OnInit {
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

  constructor(private manageGradeService: ManageGradeService) { }

  ngOnInit(): void {
    this.update();
    this.updateMateria();
    this.updateAlumnos();
  }

  updateMateria() {
    this.list = [];
    this.manageGradeService.getMaterias()
      .subscribe(response => {
        console.log(response);
        this.materias = response;
      });
  }

  updateAlumnos() {
    this.list = [];
    this.manageGradeService.getAlumnos()
      .subscribe(response => {
        console.log(response);
        this.alumnos = response;
      });
  }


  update() {
    this.list = [];
    this.manageGradeService.getAll()
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
      this.manageGradeService.delete(grade).subscribe(response => {
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
      if (this.numCalificacion >= 0 && this.numCalificacion <= 10) {
        this.manageGradeService.create(this.selAlumno.id_t_usuarios, this.selMateria.id, this.numCalificacion)
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
      this.manageGradeService.change(this.grade.id_t_calificaciones, this.grade.calificacion)
        .subscribe(response => {
          this.update();
          this.isEdit = false;
        });
    } else {
      this.openAlert("Error", "La calificacion debe estar entre 0 y 10");
    }
  }


}
