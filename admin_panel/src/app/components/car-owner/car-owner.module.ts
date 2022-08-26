import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarOwnerRoutingModule } from './car-owner-routing.module';
import { CreateCarOwnerComponent } from './create-car-owner/create-car-owner.component';
import { ListCarOwnerComponent } from './list-car-owner/list-car-owner.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';


@NgModule({
  declarations: [CreateCarOwnerComponent, ListCarOwnerComponent],
  imports: [
    CommonModule,
    CarOwnerRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot()
  ]
})
export class CarOwnerModule { }
