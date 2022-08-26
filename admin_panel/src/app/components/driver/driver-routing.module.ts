import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CreateDriverComponent} from './create-driver/create-driver.component'
import {ListDriverComponent} from './list-driver/list-driver.component'

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'list-driver',
        component: ListDriverComponent,
        data: {
          title: "List Driver",
          breadcrumb: "List Driver"
        }
      },
      {
        path: 'create-driver/:id',
        component: CreateDriverComponent,
        data: {
          title: "Edit Driver",
          breadcrumb: "Edit Driver"
        }
      },
      {
        path: 'create-driver',
        component: CreateDriverComponent,
        data: {
          title: "Create Driver",
          breadcrumb: "Create Driver"
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DriverRoutingModule { }
