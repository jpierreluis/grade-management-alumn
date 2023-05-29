import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';

@Component({
  selector: 'app-alumno-table',
  templateUrl: './alumno-table.component.html',
  styleUrls: ['./alumno-table.component.css']
})
export class AlumnoTableComponent implements OnInit {
  
  public list: any[] = [];
  public isLoading = true;

  constructor(private router:Router,
    private alumnoService:AlumnoService) { }

  ngOnInit(): void {
    this.findAll()
  }

  goToCreate(): void {
    this.router.navigate(['alumno/registrar']);
  }

  findAll(): void {
    this.list = [];
    this.alumnoService.getAll().subscribe(response=>{
      this.isLoading = false
      this.list = response;
    },
    error=>{
      this.isLoading = false
    }); 
  }

  selectItem(id_t_usuarios:number):void{
    this.router.navigate(['alumno/modificar',id_t_usuarios]);
  }
}
