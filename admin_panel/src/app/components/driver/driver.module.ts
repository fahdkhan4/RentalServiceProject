import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DriverRoutingModule } from './driver-routing.module';
import { CreateDriverComponent } from './create-driver/create-driver.component';
import { ListDriverComponent } from './list-driver/list-driver.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';



@NgModule({
  declarations: [CreateDriverComponent, ListDriverComponent],
  imports: [
    CommonModule,
    DriverRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class DriverModule { }
