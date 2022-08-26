import { Component, OnInit } from '@angular/core';
import {CarOwnerService} from '../car-owner.service'
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-list-car-owner',
  templateUrl: './list-car-owner.component.html',
  styleUrls: ['./list-car-owner.component.scss']
})
export class ListCarOwnerComponent implements OnInit {

  constructor(private service:CarOwnerService,private toastr:ToastrService) { }
  collection:any=[];
  ngOnInit(): void {
 this.getCarOwnerList();
  }
  getCarOwnerList(){
    this.service.getCarOwnerList().subscribe((result)=>{
      this.collection=result;
      
    })
  }
  deleteCarOwner(item){
    // console.log(this.collection);

    this.service.deleteCarOwner(item).subscribe((result)=>{
     this.getCarOwnerList(); 
    //  console.log(result);
    this.toastr.success("Owner Succesfully Deleted")
     
    })

  }

}
