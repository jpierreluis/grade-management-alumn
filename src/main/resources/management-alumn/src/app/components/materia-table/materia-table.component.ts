import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-materia-table',
  templateUrl: './materia-table.component.html',
  styleUrls: ['./materia-table.component.css']
})
export class MateriaTableComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  goToCreate(): void {
    this.router.navigate(['materia/registrar']);
  }


}
