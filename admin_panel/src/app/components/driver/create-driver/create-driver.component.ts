import { Router,ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import {DriverService} from '../driver.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-driver',
  templateUrl: './create-driver.component.html',
  styleUrls: ['./create-driver.component.scss']
})
export class CreateDriverComponent implements OnInit {
 

  constructor(private driverService:DriverService, private router:Router,private route:ActivatedRoute,private toastr:ToastrService ) {
    
   }

    driverEditId;
  ngOnInit(): void {
    this.driverEditId = this.route.snapshot.params.id;
    this.driverService.getCurrentDriver(this.route.snapshot.params.id).subscribe((result)=>{
      console.log(result);

      this.addDriver = new FormGroup({
        name: new FormControl(result['name']),
        number: new FormControl(result['number']),
        address: new FormControl(result['address']),
        
   
      })
      
    })
  }

  submitted = false;

  addDriver = new FormGroup({
    name: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    number: new FormControl('', Validators.required)

  })
  get f() { return this.addDriver.controls; }

  collectDriverData() {
    this.submitted = true;

    if (this.addDriver.invalid) {
      return;
    }
    //  console.log(this.addInst.value);
debugger
   if(this.driverEditId == undefined)
   {
     
    this.driverService.saveDriver(this.addDriver.value).subscribe((result) => {

      console.log(result)
      this.toastr.success('Car Successfully Added!!');
      this.addDriver.reset();
      this.router.navigate(["driver/list-driver"]);

    },err=>{
      this.toastr.error("Some Thing Went wrong")
    })
   }
   else{

    this.driverService.updateDriver(this.driverEditId,this.addDriver.value).subscribe((result) => {

      console.log(result)
      this.toastr.success('Car Successfully Added!!');
      this.addDriver.reset();
      this.router.navigate(["driver/list-driver"]);

    },err=>{
      this.toastr.error("Some Thing Went wrong")
    })
     
   }
   
  }

}
