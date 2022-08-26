import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingRoutingModule } from './booking-routing.module';
import { BookedCarComponent } from './booked-car/booked-car.component';
import { ListCarComponent } from './list-car/list-car.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { BookingInquiryComponent } from './booking-inquiry/booking-inquiry.component';
import { BookingReportsComponent } from './booking-reports/booking-reports.component';


@NgModule({
  declarations: [
    BookedCarComponent,
    ListCarComponent,
    BookingInquiryComponent,
    BookingReportsComponent,
    
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class BookingModule { }
