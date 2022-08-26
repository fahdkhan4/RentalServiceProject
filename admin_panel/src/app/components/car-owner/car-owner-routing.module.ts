import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CreateCarOwnerComponent} from './create-car-owner/create-car-owner.component'
import {ListCarOwnerComponent} from './list-car-owner/list-car-owner.component'

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'list-carOwner',
        component: ListCarOwnerComponent,
        data: {
          title: "List Car Owner",
          breadcrumb: "List Car Owner"
        }
      },
      {
        path: 'create-carOwner/:id',
        component: CreateCarOwnerComponent,
        data: {
          title: "Edit Car Owner",
          breadcrumb: "Edit Car Owner"
        }
      },
      {
        path: 'create-carOwner',
        component: CreateCarOwnerComponent,
        data: {
          title: "Create Car Owner",
          breadcrumb: "Create Car Owner"
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarOwnerRoutingModule { }
