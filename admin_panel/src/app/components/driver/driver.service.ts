import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  constructor(private http:HttpClient) { }
  driverUrl = "http://localhost:8090/driver"

  saveDriver(data) {
    return this.http.post(this.driverUrl, data,
      {
      headers: {
        "Content-Type": "application/json"
      }
    })
  }
  getDriverList() {
    return this.http.get(this.driverUrl)
  }
  deleteDriver(id) {
    return this.http.delete(`${this.driverUrl}/${id}`)
  }

  getCurrentDriver(id){
    return this.http.get(`${this.driverUrl}/${id}`)

  }
  updateDriver(id,data){
    return this.http.put(`${this.driverUrl}/${id}`,data)

  }
}
