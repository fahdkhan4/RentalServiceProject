import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import {CarOwnerService} from '../car-owner.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-create-car-owner',
  templateUrl: './create-car-owner.component.html',
  styleUrls: ['./create-car-owner.component.scss']
})
export class CreateCarOwnerComponent implements OnInit {

  constructor(private Service:CarOwnerService,
     private router:Router,
     private route:ActivatedRoute,
     private toastr:ToastrService) { }
  driverEditId;
  submitted = false;
  ngOnInit(): void {
    this.driverEditId = this.route.snapshot.params.id;
    this.Service.getCurrentCarOwner(this.route.snapshot.params.id).subscribe((result)=>{
      console.log(result);

      this.addCarOwner = new FormGroup({
        name: new FormControl(result['name']),
        number: new FormControl(result['number']),
        address: new FormControl(result['address']),
        
   
      })
      
    })
  }

  

  addCarOwner = new FormGroup({
    name: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    number: new FormControl('', Validators.required)

  })
  get f() { return this.addCarOwner.controls; }

  collectCarOwnerData() {
    this.submitted = true;

    if (this.addCarOwner.invalid) {
      return;
    }
    //  console.log(this.addInst.value);

   if(this.driverEditId == undefined)
   {
     
    this.Service.saveCarOwner(this.addCarOwner.value).subscribe((result) => {

      console.log(result)
      this.toastr.success('Car Successfully Added!!');
      this.addCarOwner.reset();
      this.router.navigate(["carOwner/list-carOwner"]);

    },err=>{
      this.toastr.error("Some Thing Went wrong")
    })
   }
   else{

    this.Service.updateCarOwner(this.driverEditId,this.addCarOwner.value).subscribe((result) => {

      console.log(result)
      this.toastr.success('Car Successfully Added!!');
      this.addCarOwner.reset();
      this.router.navigate(["carOwner/list-carOwner"]);

    },err=>{
      this.toastr.error("Some Thing Went wrong")
    })
     
   }
   
  }

}
