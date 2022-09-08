import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CarsService {

  constructor(private http:HttpClient) { }

  Url = "http://localhost:8081"
  token = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYWJhckBnbWFpbC5jb20iLCJyb2xlcyI6IltST0xFX1NFUlZJQ0VfUFJPVklERVIsIFJPTEVfQ1VTVE9NRVJdIiwiZXhwIjoxNjYyNjg3NTE2LCJpYXQiOjE2NjI2NTE1MTZ9.PNFEUMKCsBPdoB18S1ObcrAFjCVYJ51pgOJm10P6Cro' ;

  addAsset(asset: FormData) {
    debugger;
    const headers = new HttpHeaders().set("Authorization", this.token);
    return this.http.post(`${this.Url}/api/asset/`,asset,{headers})
  }

  saveCar(data: FormData)  {
    return this.http.post(this.Url, data)
  }
  getCarList() {
    return this.http.get(this.Url)
  }
  deleteCar(id: any) {
    return this.http.delete(`${this.Url}/${id}`)
  }

  getCurrentCar(id: any){
    return this.http.get(`${this.Url}/${id}`)

  }
  updateCar(id: any,data: FormData){
    return this.http.put(`${this.Url}/${id}`,data)

  }
  public getImage(value: string):Observable<any>{
    return this.http.get(value,
    {responseType:'blob'});
  }

  getCarOfOwner(id: any){

    return this.http.get(`${this.Url}/ownerCars/${id}`);

  }
}
