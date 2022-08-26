import { Component, OnInit } from '@angular/core';
import {CarsService} from '../cars.service'
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';



@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html',
  styleUrls: ['./list-car.component.scss']
})
export class ListCarComponent implements OnInit {
  collection:any=[];
  carOwnerId;
  constructor(private service:CarsService,private router: Router,
    private route: ActivatedRoute,private toastr:ToastrService) { }

  // imgURL: any;
  // imagePath;
  // files;
  url = "http://localhost:8090/"
  ngOnInit(): void {
   
   this.getcarOwnerAndCarList();

  }

  getcarOwnerAndCarList(){
    this.carOwnerId = this.route.snapshot.params.id;
    if(this.carOwnerId != undefined){
      this.service.getCarOfOwner(this.carOwnerId).subscribe(result=>{
        this.collection = result;
      })
    }

    else{
      this.service.getCarList().subscribe((result)=>{
        this.collection=result;
        
      })
    }
  } 


  deleteCar(item){
    // console.log(this.collection);
    
    

    this.service.deleteCar(item).subscribe((result)=>{
     this.getcarOwnerAndCarList();
         //  console.log(result);
    this.toastr.success("Booking Succesfully Deleted")
     
    })

  }

}
