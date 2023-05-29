import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlumnoComponent } from './pages/alumno/alumno.component';
import { MateriaComponent } from './pages/materia/materia.component';
import { MateriaCreateComponent } from './pages/materia-create/materia-create.component';
import { AlumnoCreateComponent } from './pages/alumno-create/alumno-create.component';
import { AlumnoUpdateComponent } from './pages/alumno-update/alumno-update.component';
import { MateriaUpdateComponent } from './pages/materia-update/materia-update.component';
import { AsignarMateriaComponent } from './pages/asignar-materia/asignar-materia.component';

const routes: Routes = [
  {path:'alumno',component:AlumnoComponent},
  {path:'alumno/registrar',component:AlumnoCreateComponent},
  {path:'alumno/modificar/:id',component:AlumnoUpdateComponent},

  {path:'materia',component:MateriaComponent},
  {path:'materia/registrar',component:MateriaCreateComponent},
  {path:'materia/modificar/:id',component:MateriaUpdateComponent},
  
  {path:'asignar/materia',component:AsignarMateriaComponent},
  {path:'',redirectTo: '/alumno', pathMatch: 'full'},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
