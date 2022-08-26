import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BookedCarComponent} from './booked-car/booked-car.component';
import {ListCarComponent} from './list-car/list-car.component';
import {BookingInquiryComponent} from './booking-inquiry/booking-inquiry.component';
import {BookingReportsComponent} from './booking-reports/booking-reports.component'

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'list-car-Booking',
        component: ListCarComponent,
        data: {
          title: "Book Now ",
          breadcrumb: "Book Now"
        }
      },
      {
        path: 'booked-car',
        component: BookedCarComponent,
        data: {
          title: "Create Car Booking",
          breadcrumb: "Create Car Booking"
        }
      },
      {
        path: 'booking-inquiry',
        component:BookingInquiryComponent,
        data: {
          title: "Booking Inquiry",
          breadcrumb: "Booking Inquiry"
        }
      },
      {
        path: 'booking-reports',
        component:BookingReportsComponent,
        data: {
          title: "Booking Reports",
          breadcrumb: "Booking Reports"
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookingRoutingModule { }
