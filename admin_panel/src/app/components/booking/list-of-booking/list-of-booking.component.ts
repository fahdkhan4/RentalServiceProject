import { Component, OnInit } from '@angular/core';
import {BookingService} from '../booking.service'
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-list-of-booking',
  templateUrl: './list-of-booking.component.html',
  styleUrls: ['./list-of-booking.component.scss']
})
export class ListOfBookingComponent implements OnInit {
  collection:any=[];
  constructor(private service:BookingService,private router: Router,
    private route: ActivatedRoute,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.getListBooking();
  }

  getListBooking(){
    this.service.getListBooking().subscribe((result)=>{
      console.log(result);
      this.collection=result;
      
    })
  }

  deleteCar(item){
    // console.log(this.collection);
    

    this.service.deleteBooking(item).subscribe((result)=>{
     this.getListBooking();
     this.toastr.success("Booking Succesfully Deleted")
    //  console.log(result);
    
     
    })

  }

}
