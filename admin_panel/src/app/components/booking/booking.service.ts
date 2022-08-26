import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';




@Injectable({
  providedIn: 'root'
})
export class BookingService {
  
  Url = "http://localhost:8090"
  constructor(private http:HttpClient) { }
  getCarList() {
    return this.http.get(`${this.Url}/car`)
  }
  getDriverList(){
    return this.http.get(`${this.Url}/driver`)
  }
  createBooking(data){
    return this.http.post(`${this.Url}/booking`,data)

  }
  getListBooking(){
    return this.http.get(`${this.Url}/booking`)
  }
  // deleteBooking(id){
  //   return this.http.delete(`${this.Url}/booking/${id}`)
  // }
  createCustomer(data){
    return this.http.post(`${this.Url}/customer`,data)

  }
  getCustomer(){
    return this.http.get(`${this.Url}/customer`)
  }
  getFilterCarWithBrand(checkInDate,checkOutDate,brand){
    return this.http.get(`${this.Url}/car/notBookedCarBrandFilter?checkInDate=${checkInDate}&checkOutDate=${checkOutDate}&brand=${brand}`)

  }
  getFilterCar(checkInDate,checkOutDate){
    return this.http.get(`${this.Url}/car/notBookedCar?checkInDate=${checkInDate}&checkOutDate=${checkOutDate}`)
  }
  searchBooking(nicNumber){
    return this.http.get(`${this.Url}/booking/searchBooking/${nicNumber}`)

  }
  getReport(checkInDate,checkOutDate):Observable<any>{
    return this.http.get(`${this.Url}/booking/bookingReport?checkInDate=${checkInDate}&checkOutDate=${checkOutDate}`)
  }
 
  downloadReport(startDate:any, endDate:any):Observable<any>{
    return this.http.get (`${this.Url}/booking/downloadpdf/${startDate}/${endDate}`,{ responseType: 'blob' });
    }
}
