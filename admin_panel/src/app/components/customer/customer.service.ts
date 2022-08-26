import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) { }

  Url = "http://localhost:8090/customer"

  saveCustomer(data) {
    return this.http.post(this.Url, data,
      {
      headers: {
        "Content-Type": "application/json"
      }
    })
  }
  getCustomerList() {
    return this.http.get(this.Url)
  }
  deleteCustomer(id) {
    return this.http.delete(`${this.Url}/${id}`)
  }

  getCurrentCustomer(id){
    return this.http.get(`${this.Url}/${id}`)

  }
  updateCustomer(id,data){
    return this.http.put(`${this.Url}/${id}`,data)

  }
}
