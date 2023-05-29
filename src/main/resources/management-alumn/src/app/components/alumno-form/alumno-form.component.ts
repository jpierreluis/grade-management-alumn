import { Component, Input, OnInit } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-alumno-form',
  templateUrl: './alumno-form.component.html',
  styleUrls: ['./alumno-form.component.css']
})
export class AlumnoFormComponent implements OnInit {
  @Input('id') id?:string;

  public alumno: Alumno = new Alumno();
  form: FormGroup;

  constructor(private alumnoService: AlumnoService,
    public fb: FormBuilder,
    private router: Router) {
    this.form = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(80)]],
      ap_paterno: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(80)]],
      ap_materno: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(80)]]
    });
    
  }

  ngOnInit(): void {
    if(this.id != undefined){
      this.alumnoService.getElement(this.id).subscribe(response=>{
        this.alumno = response.body;
      },error=>{
        
      });

    }
  }

  save(): void {
    if(this.id != undefined){
      this.alumno.id_t_usuarios = parseInt(this.id);
    }
    
    this.alumnoService.save(this.alumno).subscribe(response => {
      console.log(response);
      this.router.navigate(['alumno'])
    },error => {
        console.log(error);
      })
  }


}
