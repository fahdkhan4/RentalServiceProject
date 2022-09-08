import { Component, OnInit } from '@angular/core';
import {BookingService} from '../booking.service'
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-booking-reports',
  templateUrl: './booking-reports.component.html',
  styleUrls: ['./booking-reports.component.scss']
})
export class BookingReportsComponent implements OnInit {
  listBookingReport:any = [];
  allAssets:any = [];
  assetsType:any = [];
  totalProfit:any;
  constructor(private service:BookingService,private router: Router,
    private route: ActivatedRoute,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.assetType();
    this.getAllResult();

  }


  searchReportForm = new FormGroup({
    checkInDate: new FormControl(''),
    checkOutDate: new FormControl(''),
    status: new FormControl('Any'),
    type: new FormControl('')
  })

  assetType(){
     this.service.getAllAssetType().subscribe((assetsType)=>{
      this.assetsType = assetsType;
     });
  }

  downloadReport(){
     this.service.downloadReport(this.searchReportForm.value.checkInDate,this.searchReportForm.value.checkOutDate).subscribe((d)=>{
      console.log(d);
      if(d.size!=0){
        console.log("Blob", d);
        let url = window.URL.createObjectURL(d);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = new Date().toDateString();
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
        }
        else{
        this.toastr.error("Can't find Balance Sheet to this date");

        }
     })
  }

  getAllResult(){
    this.service.getAllAsset().subscribe((res)=>{
    //  console.log(res);
     debugger
       this.allAssets = res;
      console.log(this.allAssets)
     },err=>{
       this.toastr.error("Something went wrong")
     })
  }



  submitSearchResult(){
    console.log(this.searchReportForm.value.checkInDate,this.searchReportForm.value.checkOutDate,this.searchReportForm.value.type)
    this.service.getReport(this.searchReportForm.value.checkInDate,this.searchReportForm.value.checkOutDate).subscribe((res)=>{
     console.log(res);
     debugger
       this.listBookingReport = res.bookingReportDtoList;
       this.totalProfit = res.totalProfit;
      console.log(this.listBookingReport)
     },err=>{
       this.toastr.error("Something went wrong")
     })
  }

  clearFilter(){
      this.searchReportForm = new FormGroup({
      checkInDate: new FormControl(null),
      checkOutDate: new FormControl(null),
      status: new FormControl(null),
      type: new FormControl(null)
    })
  }

}
