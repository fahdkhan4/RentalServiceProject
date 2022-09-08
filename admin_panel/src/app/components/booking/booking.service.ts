import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';




@Injectable({
  providedIn: 'root'
})
export class BookingService {

  Url = "http://localhost:8081";
  token = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYWJhckBnbWFpbC5jb20iLCJyb2xlcyI6IltST0xFX1NFUlZJQ0VfUFJPVklERVIsIFJPTEVfQ1VTVE9NRVJdIiwiZXhwIjoxNjYyNjg3NTE2LCJpYXQiOjE2NjI2NTE1MTZ9.PNFEUMKCsBPdoB18S1ObcrAFjCVYJ51pgOJm10P6Cro' ;
  constructor(private http:HttpClient) { }

  getAllAsset() {
    const headers = new HttpHeaders().set("Authorization", this.token);
    return this.http.get(`${this.Url}/api/admin/asset`,{headers})
  }

  getAllAssetType() {
    const headers = new HttpHeaders().set("Authorization", this.token);
    return this.http.get(`${this.Url}/api/admin/asset/type`,{headers})
  }

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
