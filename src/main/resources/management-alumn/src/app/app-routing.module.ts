import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlumnoComponent } from './pages/alumno/alumno.component';
import { MateriaComponent } from './pages/materia/materia.component';
import { MateriaCreateComponent } from './pages/materia-create/materia-create.component';
import { AlumnoCreateComponent } from './pages/alumno-create/alumno-create.component';
import { AlumnoUpdateComponent } from './pages/alumno-update/alumno-update.component';

const routes: Routes = [
  {path:'alumno',component:AlumnoComponent},
  {path:'alumno/registrar',component:AlumnoCreateComponent},
  {path:'alumno/modificar/:id',component:AlumnoUpdateComponent},

  {path:'materia',component:MateriaComponent},
  {path:'materia/registrar',component:MateriaCreateComponent},

  {path:'',redirectTo: '/alumno', pathMatch: 'full'},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
