import { Component, OnInit } from '@angular/core';
import {DriverService} from '../driver.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-list-driver',
  templateUrl: './list-driver.component.html',
  styleUrls: ['./list-driver.component.scss']
})
export class ListDriverComponent implements OnInit {
  
  constructor(private service:DriverService,private toastr:ToastrService) { }
  collection:any=[];
  ngOnInit(): void {
    this.getDriverList();
  }

  getDriverList(){
    this.service.getDriverList().subscribe((result)=>{
      this.collection=result;
      
    })
  }

  deleteDriver(item){
    // console.log(this.collection);
    
    this.service.deleteDriver(item).subscribe((result)=>{
     this.getDriverList();
    //  console.log(result);
    this.toastr.success("Booking Succesfully Deleted")
     
    })

  }

 

}
