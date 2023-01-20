import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponentComponent } from './shared/components/menu-component/menu-component.component';
import { FooterComponentComponent } from './shared/components/footer-component/footer-component.component';
import { HomePageControllerComponent } from './module/home/pages/home-page-controller/home-page-controller.component';
import { HomeTableControllerComponent } from './module/home/components/home-table-controller/home-table-controller.component';
import { HomeFormCreateControllerComponent } from './module/home/components/home-form-create-controller/home-form-create-controller.component';
import { FormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    AppComponent,
    MenuComponentComponent,
    FooterComponentComponent,
    HomePageControllerComponent,
    HomeTableControllerComponent,
    HomeFormCreateControllerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
