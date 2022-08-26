import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { BookingService } from '../booking.service';


@Component({
  selector: 'app-booked-car',
  templateUrl: './booked-car.component.html',
  styleUrls: ['./booked-car.component.scss']
})
export class BookedCarComponent implements OnInit {
  submitted = false;
  carId;
  bookedCarObj: booked_carClass = new booked_carClass();
  addCustomerObj: add_customerClass = new add_customerClass();
  driverList: any = [];
  driverSelect = false;
  customerId;
  checkInDate;
  checkOutDate;

  constructor(private router: Router,
    private route: ActivatedRoute, private service: BookingService, private toaster: ToastrService) { }

  ngOnInit(): void {
    debugger
    this.carId = this.route.snapshot.queryParams.id;
    this.checkInDate = this.route.snapshot.queryParams.p1;
    this.checkOutDate = this.route.snapshot.queryParams.p2;

    this.getDriverList();
  }
  getDriverList() {
    this.service.getDriverList().subscribe(result => {
      this.driverList = result;
    })
  }

  bookedCar = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    mobileNumber: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    nicNumber: new FormControl('', Validators.required),
    // checkInDate: new FormControl('', Validators.required),
    // checkOutDate: new FormControl('', Validators.required),
    driver: new FormControl('', Validators.required)

  })
  driverOption(value) {
    if (value == "yes") {
      this.driverSelect = true;

    }
    else if (value == "no") {
      debugger
      this.driverSelect = false;
      this.bookedCar.get('driver').clearValidators();
      this.bookedCar.get('driver').updateValueAndValidity();
    }
  }
  get f() { return this.bookedCar.controls; }

  bookedCarData() {
    debugger
    this.submitted = true;
    if (this.bookedCar.invalid) {
      return;
    }
    this.addCustomerObj.firstName = this.bookedCar.value.firstName;
    this.addCustomerObj.lastName = this.bookedCar.value.lastName;
    this.addCustomerObj.mobileNumber = this.bookedCar.value.mobileNumber;
    this.addCustomerObj.nicNumber = this.bookedCar.value.nicNumber;
    this.addCustomerObj.address = this.bookedCar.value.address;

    this.service.getCustomer().subscribe(result => {
      debugger
      let customers: any = [];
      customers = result;
      for (let item of customers) {
        if (item.nicNumber == this.addCustomerObj.nicNumber) {
          this.customerId = item.id;
        }
      }
      //call addUpdateBooking function
      this.addUpdateBooking(this.customerId,this.addCustomerObj);
    })
  }

  addUpdateBooking(customerId, customerObj: add_customerClass) {
    debugger;
    if (customerId) {
      //make booking call
      this.bookedCarObj.carId = this.carId;
      this.bookedCarObj.checkInDate = this.checkInDate;
      this.bookedCarObj.checkOutDate = this.checkOutDate;
      this.bookedCarObj.customerId = customerId;
      this.bookedCarObj.driverId = this.bookedCar.value.driver.id;
      this.makeBooking(this.bookedCarObj);
    } else {
      //create new customer then make booking call with that newly created customer id
      this.createNewCustomer(customerObj);
    }
  }

  createNewCustomer(customerObj: add_customerClass) {
    this.service.createCustomer(customerObj).subscribe(result => {
      if (result) {
        let returnCustomer: any;
        returnCustomer = result;
        this.customerId = returnCustomer.id;
        //make booking call
        this.bookedCarObj.carId = this.carId;
        this.bookedCarObj.checkInDate = this.checkInDate;
        this.bookedCarObj.checkOutDate = this.checkOutDate;
        this.bookedCarObj.customerId = returnCustomer.id;
        this.bookedCarObj.driverId = this.bookedCar.value.driver.id;
        this.makeBooking(this.bookedCarObj);
      }
    }, error => {
      this.toaster.error("Some Thing Went wrong")
    })
  }

  makeBooking(bookingCarObj: booked_carClass) {
    this.service.createBooking(bookingCarObj).subscribe(result => {
      console.log(result);
      this.toaster.success('Booking Successfully Added!!');
      this.bookedCar.reset();
      this.router.navigate(["/booking/booking-inquiry"]);
    }, err => {
      this.toaster.error("Some Thing Went wrong")
    })
  }

}



export class booked_carClass {

  checkInDate: string
  checkOutDate: string
  customerId: any
  driverId: any
  carId: any

}


export class add_customerClass {

  firstName: string
  lastName: string
  mobileNumber: any
  address: any
  nicNumber: any

}

