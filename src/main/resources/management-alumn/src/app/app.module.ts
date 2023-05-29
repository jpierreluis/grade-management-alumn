import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { MenuHeaderComponent } from './layouts/menu-header/menu-header.component';
import { AlumnoComponent } from './pages/alumno/alumno.component';
import { SideSubMenuComponent } from './layouts/side-sub-menu/side-sub-menu.component';
import { MateriaComponent } from './pages/materia/materia.component';
import { AlumnoTableComponent } from './components/alumno-table/alumno-table.component';
import { MateriaTableComponent } from './components/materia-table/materia-table.component';
import { LoadingSpinnerComponent } from './components/loading-spinner/loading-spinner.component';
import { AlumnoCreateComponent } from './pages/alumno-create/alumno-create.component';
import { MateriaCreateComponent } from './pages/materia-create/materia-create.component';
import { AlumnoFormComponent } from './components/alumno-form/alumno-form.component';
import { MateriaFormComponent } from './components/materia-form/materia-form.component';
import { ContextualMenuComponent } from './components/contextual-menu/contextual-menu.component'
import { ReactiveFormsModule } from '@angular/forms';
import { MateriaUpdateComponent } from './pages/materia-update/materia-update.component';
import { AlumnoUpdateComponent } from './pages/alumno-update/alumno-update.component';
import { AsignarMateriaComponent } from './pages/asignar-materia/asignar-materia.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuHeaderComponent,
    AlumnoComponent,
    SideSubMenuComponent,
    MateriaComponent,
    AlumnoTableComponent,
    MateriaTableComponent,
    LoadingSpinnerComponent,
    AlumnoCreateComponent,
    MateriaCreateComponent,
    AlumnoFormComponent,
    MateriaFormComponent,
    ContextualMenuComponent,
    MateriaUpdateComponent,
    AlumnoUpdateComponent,
    AsignarMateriaComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
