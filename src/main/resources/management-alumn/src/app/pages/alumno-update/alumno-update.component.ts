import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-alumno-update',
  templateUrl: './alumno-update.component.html',
  styleUrls: ['./alumno-update.component.css']
})
export class AlumnoUpdateComponent implements OnInit {

  public idalumno?:string|null;

  constructor(private paramRoute:ActivatedRoute) { 
    this.idalumno =  this.paramRoute.snapshot.paramMap.get('id')
  }

  ngOnInit(): void {
    
  }

}
