import { Component, OnInit } from '@angular/core';
import {BookingService} from '../booking.service'
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormGroup, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-booking-inquiry',
  templateUrl: './booking-inquiry.component.html',
  styleUrls: ['./booking-inquiry.component.scss']
})
export class BookingInquiryComponent implements OnInit {
  collection:any=[];
  constructor(private service:BookingService,private router: Router,
    private route: ActivatedRoute,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.getListBooking();
  }
  searchBarBooking = new FormGroup({
    searchInput: new FormControl('')
  })

  getListBooking(){
    this.service.getListBooking().subscribe((result)=>{
      console.log(result);
      this.collection=result;
      
    })
  }

  // deleteCar(item){
  //   // console.log(this.collection);
    

  //   this.service.deleteBooking(item).subscribe((result)=>{
  //    this.getListBooking();
  //    this.toastr.success("Booking Succesfully Deleted")
  //   //  console.log(result);
    
     
  //   })

  // }
  submitSearchResult(){
      if(this.searchBarBooking.value.searchInput!=""){
        this.service.searchBooking(this.searchBarBooking.value.searchInput).subscribe((result)=>{
         this.collection = result
          // this.toastr.success("Booking Succesfully Deleted")
        },err=>{
          this.toastr.error("Something went wrong")
        })
      }
  }

}
