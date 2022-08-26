import { Component, OnInit } from '@angular/core';
import {BookingService} from '../booking.service';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html',
  styleUrls: ['./list-car.component.scss']
})
export class ListCarComponent implements OnInit {

  constructor(private sevice:BookingService,private router:Router) { }
  carList:any = [];
  url = "http://localhost:8090/"
  closeResult = '';
  
 

  ngOnInit(): void {
    this.sevice.getFilterCar(this.searchFilter.value.checkInDate,this.searchFilter.value.checkOutDate).subscribe(result=>{
      console.log(result);
      this.carList = result;
    })
  }

  bookNow(carId){
    this.router.navigate(["/booking/booked-car/"],{ queryParams: {id:carId, p1: this.searchFilter.value.checkInDate, p2: this.searchFilter.value.checkOutDate}});
  }

  clearFilter(){
    debugger
    this.searchFilter.patchValue({
      checkInDate:  ((new Date()).toISOString().substring(0,10)),
      checkOutDate: ((new Date()).toISOString().substring(0,10)),
      brand:''
    }) 
    console.log(this.searchFilter.value.checkInDate,this.searchFilter.value.checkOutDate)
    this.sevice.getFilterCar(this.searchFilter.value.checkInDate,this.searchFilter.value.checkOutDate).subscribe(result=>{
      console.log(result);
      this.carList = result;
    })
  }

  searchFilter = new FormGroup({
    checkInDate: new FormControl((new Date()).toISOString().substring(0,10)),
    checkOutDate: new FormControl((new Date()).toISOString().substring(0,10)),
    brand: new FormControl('')
  })
  

  submitFilters(){
    if(this.searchFilter.value.brand != ""){
      this.sevice.getFilterCarWithBrand(this.searchFilter.value.checkInDate,this.searchFilter.value.checkOutDate,this.searchFilter.value.brand).subscribe(result=>{
        this.carList = result;
      })
    }
    else{
      this.sevice.getFilterCar(this.searchFilter.value.checkInDate,this.searchFilter.value.checkOutDate).subscribe(result=>{
        this.carList = result;
      })
    }
   

  }



 

}
