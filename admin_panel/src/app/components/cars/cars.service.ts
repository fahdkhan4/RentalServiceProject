import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CarsService {

  constructor(private http:HttpClient) { }

  Url = "http://localhost:8090/car"

  saveCar(data)  {
    return this.http.post(this.Url, data)
  }
  getCarList() {
    return this.http.get(this.Url)
  }
  deleteCar(id) {
    return this.http.delete(`${this.Url}/${id}`)
  }

  getCurrentCar(id){
    return this.http.get(`${this.Url}/${id}`)

  }
  updateCar(id,data){
    return this.http.put(`${this.Url}/${id}`,data)

  }
  public getImage(value):Observable<any>{
    return this.http.get(value,
    {responseType:'blob'});
  }

  getCarOfOwner(id){
   
    return this.http.get(`${this.Url}/ownerCars/${id}`);

  }
}
