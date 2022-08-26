import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarOwnerService {

  constructor(private http:HttpClient) { }
  Url = "http://localhost:8090/owner"

  saveCarOwner(data) {
    return this.http.post(this.Url, data,
      {
      headers: {
        "Content-Type": "application/json"
      }
    })
  }
  getCarOwnerList() {
    return this.http.get(this.Url)
  }
  deleteCarOwner(id) {
    return this.http.delete(`${this.Url}/${id}`)
  }

  getCurrentCarOwner(id){
    return this.http.get(`${this.Url}/${id}`)

  }
  updateCarOwner(id,data){
    return this.http.put(`${this.Url}/${id}`,data)

  }
}
