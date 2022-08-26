import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../customer.service'
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-list-customer',
  templateUrl: './list-customer.component.html',
  styleUrls: ['./list-customer.component.scss']
})
export class ListCustomerComponent implements OnInit {

  constructor(private service:CustomerService,private toastr:ToastrService) { }
  collection:any=[];

  ngOnInit(): void {
 this.getCustomerList();
  }


  getCustomerList(){
    this.service.getCustomerList().subscribe((result)=>{
      this.collection=result;
      
    })
  }

  deleteCustomer(item){
    // console.log(this.collection);
 
    this.service.deleteCustomer(item).subscribe((result)=>{

      this.getCustomerList();
      this.toastr.success("Booking Succesfully Deleted")
     
    //  console.log(result);
    alert("Successfull deleted"+result)
     
    })

  }

}
