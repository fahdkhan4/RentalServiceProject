import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CreateCustomerComponent} from './create-customer/create-customer.component';
import {ListCustomerComponent} from './list-customer/list-customer.component'

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'list-customer',
        component: ListCustomerComponent,
        data: {
          title: "List Customer",
          breadcrumb: "List Customer"
        }
      },
      {
        path: 'create-customer/:id',
        component: CreateCustomerComponent,
        data: {
          title: "Edit Customer",
          breadcrumb: "Edit Customer"
        }
      },
      {
        path: 'create-customer',
        component: CreateCustomerComponent,
        data: {
          title: "Create Customer",
          breadcrumb: "Create Customer"
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
