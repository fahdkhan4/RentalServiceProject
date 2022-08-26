import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CarsService } from '../cars.service'
import * as _ from 'lodash';
import { add_car } from './add-car';
import { CarOwnerService } from '../../car-owner/car-owner.service'
import { ToastrService } from 'ngx-toastr';



@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.scss']
})
export class AddCarComponent implements OnInit {
  carEditId;
  submitted = false;
  add_carData: add_car = new add_car();
  formData = new FormData();
  carOwner: any = [];
  imgURL: any;
  imagePath;
  getCarResult: any = [];
  url = "http://localhost:8090/";
  patchfile;
  imagePathGet
  imgFile
  constructor(
    private Service: CarsService,
     private router: Router,
      private route: ActivatedRoute,
       private carOwnerService: CarOwnerService,
       private toastr:ToastrService
       ) { }

  ngOnInit(): void {
    this.getCar();
    
  }

  getCar(){
    this.carOwnerService.getCarOwnerList().subscribe((result) => {
      this.carOwner = result;
    })
    this.carEditId = this.route.snapshot.params.id;
    if (this.carEditId != null) {
      this.Service.getCurrentCar(this.route.snapshot.params.id).subscribe((result) => {
        console.log(result);

        this.getCarResult = result;
        this.patchfile = this.url + this.getCarResult.image;

        this.Service.getImage(this.url + this.getCarResult.image).subscribe(e => {

          if (e) {
            this.add_carData.image = this.blobToFile(e, "abc.png");

          }
        })


        this.addCar = new FormGroup({

          name: new FormControl(result['name']),
          modelNumber: new FormControl(result['modelNumber']),
          color: new FormControl(result['color']),
          price: new FormControl(result['price']),
          owner: new FormControl(result['owner']),
          brand: new FormControl(result['brand']),
        })

        this.addCar.patchValue({
          image: this.patchfile
        })


      })
    }
  }

  handleCategoryBanner(file: File) {

    this.add_carData.image = file;
  }


  blobToFile(theBlob, fileName) {
    //A Blob() is almost a File() - it's just missing the two properties below which we will add
    theBlob.lastModifiedDate = new Date();
    theBlob.name = fileName;
    return theBlob;
  }



  preview(files) {
    if (files.length === 0)
      return;

    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {

      return;
    }

    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]);
    reader.onload = (_event) => {
      this.imgURL = reader.result;

    }
  }



  addCar = new FormGroup({
    name: new FormControl('', Validators.required),
    modelNumber: new FormControl('', Validators.required),
    color: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
    owner: new FormControl('', Validators.required),
    brand: new FormControl('', Validators.required)

  })
  get f() { return this.addCar.controls; }

  collectCarData() {
    this.submitted = true;

    if (this.addCar.invalid) {
      return;
    }
    //  console.log(this.addInst.value);

    if (this.carEditId == undefined) {
      debugger
      this.add_carData.name = this.addCar.value.name;
      this.add_carData.modelNumber = this.addCar.value.modelNumber;
      this.add_carData.color = this.addCar.value.color;
      this.add_carData.price = this.addCar.value.price;
      this.add_carData.ownerId = this.addCar.value.owner.id;
      this.add_carData.brand = this.addCar.value.brand;

      this.formData.append("name", this.add_carData.name);
      this.formData.append("modelNumber", this.add_carData.modelNumber);
      this.formData.append("color", this.add_carData.color);
      this.formData.append("price", this.add_carData.price);
      this.formData.append("ownerId", this.add_carData.ownerId);
      this.formData.append("brand", this.add_carData.brand);
      this.formData.append("image", this.add_carData.image[0]);


      this.Service.saveCar(this.formData).subscribe((result) => {

        console.log(result)
        this.toastr.success('Car Successfully Added!!');
        this.addCar.reset();
        this.router.navigate(["cars/list-cars"]);

      }, err => {
       this.toastr.error("Some Thing Went wrong")
      })
    }
    else {
      debugger
      this.add_carData.name = this.addCar.value.name;
      this.add_carData.modelNumber = this.addCar.value.modelNumber;
      this.add_carData.color = this.addCar.value.color;
      this.add_carData.price = this.addCar.value.price;
      this.add_carData.ownerId = this.addCar.value.owner.id;
      this.add_carData.brand = this.addCar.value.brand;

      this.formData.append("name", this.add_carData.name);
      this.formData.append("modelNumber", this.add_carData.modelNumber);
      this.formData.append("color", this.add_carData.color);
      this.formData.append("price", this.add_carData.price);
      this.formData.append("ownerId", this.add_carData.ownerId);
      this.formData.append("brand", this.add_carData.brand);
      this.formData.append("image", this.add_carData.image);


      this.Service.updateCar(this.carEditId, this.formData).subscribe((result) => {

        console.log(result)
        this.toastr.success('Car Successfully Added!!');
        this.addCar.reset();
        this.router.navigate(["cars/list-cars"]);

      }, err => {
        this.toastr.error("Some Thing Went wrong")
      })

    }

  }

}
