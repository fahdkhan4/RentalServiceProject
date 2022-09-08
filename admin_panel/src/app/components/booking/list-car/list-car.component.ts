import { Component, OnInit } from '@angular/core';
import {BookingService} from '../booking.service';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CarsService } from '../../cars/cars.service';

@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html',
  styleUrls: ['./list-car.component.scss']
})
export class ListCarComponent implements OnInit {

  constructor(private sevice:CarsService,private router:Router) { }
  carList:any = [];
  url = "http://localhost:8090/"
  closeResult = '';
  assetData: any



  ngOnInit(): void {
    // this.sevice.getFilterCar(this.searchFilter.value.checkInDate,this.searchFilter.value.checkOutDate).subscribe(result=>{
    //   console.log(result);
    //   this.carList = result;
    // })
  }


  bookNow(carId){
    this.router.navigate(["/booking/booked-car/"],{ queryParams: {id:carId, p1: this.searchFilter.value.checkInDate, p2: this.searchFilter.value.checkOutDate}});
  }



  searchFilter = new FormGroup({
    checkInDate: new FormControl((new Date()).toISOString().substring(0,10)),
    checkOutDate: new FormControl((new Date()).toISOString().substring(0,10)),
    city: new FormControl(''),
    name: new FormControl(''),
    address: new FormControl(''),
    pricePerDay: new FormControl(''),
    type: new FormControl(''),
    image: new FormControl('')
  })


  submitFilters(){
    debugger;
    console.log(this.searchFilter)

     this.assetData = {
      name: this.searchFilter.value.name,
      location: this.searchFilter.value.address,
      endDate: this.searchFilter.value.checkInDate,
      pricePerDay: this.searchFilter.value.checkOutDate,
      type: this.searchFilter.value.type,
      city: this.searchFilter.value.city,
      user: {
        id: 3
      }
    }
    this.assetData = JSON.stringify(this.assetData);
    console.log(this.assetData);

    var formData = new FormData();
    debugger;
    for (const file of this.searchFilter.value.image.files) {
      
      formData.append("file",file)
  }

      formData.append('data',this.assetData);
      console.log(formData.get('image'));
      console.log(formData.get('data'));

    this.sevice.addAsset(formData).subscribe(result=>{
        console.log(result);
        this.carList = result;
      });
  }
}
