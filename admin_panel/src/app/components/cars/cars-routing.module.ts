import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddCarComponent} from './add-car/add-car.component';
import {ListCarComponent} from './list-car/list-car.component'

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'list-cars',
        component: ListCarComponent,
        data: {
          title: "List Car ",
          breadcrumb: "List Car "
        }
      },
      {
        path: 'list-cars/:id',
        component: ListCarComponent,
        data: {
          title: "List of Owner Cars",
          breadcrumb: "List of Owner Cars"
        }
      },
      {
        path: 'add-cars/:id',
        component: AddCarComponent,
        data: {
          title: "Edit Car ",
          breadcrumb: "Edit Car "
        }
      },
      {
        path: 'add-cars',
        component: AddCarComponent,
        data: {
          title: "Add Car ",
          breadcrumb: "Add Car"
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsRoutingModule { }
