import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import {CustomerService} from '../customer.service'
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.scss']
})
export class CreateCustomerComponent implements OnInit {
  driverEditId;
  submitted = false;

  constructor(private Service:CustomerService, private router:Router,private route:ActivatedRoute, private toastr:ToastrService) { }

  ngOnInit(): void {
    this.driverEditId = this.route.snapshot.params.id;
    this.Service.getCurrentCustomer(this.route.snapshot.params.id).subscribe((result)=>{
      console.log(result);

      this.addCustomer = new FormGroup({
        firstName:new FormControl(result['firstName']),
    lastName:new FormControl(result['lastName']),
    mobileNumber:new FormControl(result['mobileNumber']),
    nicNumber:new FormControl(result['nicNumber']),
        address: new FormControl(result['address']),
        
   
      })
      
    })
  }

  addCustomer = new FormGroup({
    firstName:new FormControl('', Validators.required),
    lastName:new FormControl('', Validators.required),
    mobileNumber:new FormControl('', Validators.required),
    address:new FormControl('', Validators.required),
    nicNumber:new FormControl('', Validators.required),

  })
  get f() { return this.addCustomer.controls; }

  collectCustomerData() {
    this.submitted = true;

    if (this.addCustomer.invalid) {
      return;
    }
    //  console.log(this.addInst.value);

   if(this.driverEditId == undefined)
   {
     
    this.Service.saveCustomer(this.addCustomer.value).subscribe((result) => {

      console.log(result)
      this.toastr.success('Customer Successfully Added!!');
      this.addCustomer.reset();
      this.router.navigate(["customer/list-customer"]);

    },err=>{
      this.toastr.error("Some Thing Went wrong")
    })
   }
   else{

    this.Service.updateCustomer(this.driverEditId,this.addCustomer.value).subscribe((result) => {

      console.log(result)
      this.toastr.success('Customer Successfully Updated!!');
      this.addCustomer.reset();
      this.router.navigate(["customer/list-customer"]);

    },err=>{
      this.toastr.error("Some Thing Went wrong")
    })
     
   }
   
  }

}
