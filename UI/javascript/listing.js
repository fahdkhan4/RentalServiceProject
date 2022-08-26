
var jwtToken;

var pageNumber = 0;
var pageSize = 4;

let jsonAllData = [];

async function getData(url){
  var data;
  var token = JSON.parse(localStorage.getItem("token"));
try {
  const response = await fetch(url, {
    method: "GET",
    headers: {
      "Authorization": "Bearer " + token,
      "Content-type": "application/json",
    },
  });
  const value = await response.json();
  // data = value;
  return value;
} catch (json) {
  return json;
}
}

async function getDataWithBody(url, body){
  
  var data;
  var token = JSON.parse(localStorage.getItem("token"));
try {
  const response = await fetch(url, {
    
    method: "POST",
            body: JSON.stringify(body),
    headers: {
      "Authorization": "Bearer " + token,
      "Content-type": "application/json",
    },
  });
  const value = await response.json();
  data = value;
  return data;
} catch (json) {
  return json;
}
}

function checkLoginCredentials(){
    fetch("http://localhost:8081/login",{
        body:JSON.stringify({
          email: "babar@gmail.com",
          password: "babar123"
        }),
        method:"POST",
        headers:{
            "Content-type":"application/json"
        }
    })
    .then((response) => {
        return response.json();
    })
    .then((jwttoken) => {
        localStorage.setItem("token", JSON.stringify(jwttoken.jwt));
    })
    .catch((error)=> console.log(error))
}

function showCities(){
    var token = JSON.parse(localStorage.getItem("token"));
    let cities = getData("http://localhost:8081/api/asset/cities");
    cities.then(value => {
      cities = `<select id="selected" class="js-select select-style-two" name="state">`
      for(let i = 0; i< value.length; i++){
      cities += `<option value="${value[i]}">${value[i]}</option>`
    }
    cities+= `</select>`
    document.getElementById("city").innerHTML = cities;
    }).catch(err => {
      console.log(err);
    });
  }

function showAllAsset(flag){
  
    if(!flag && pageNumber!=0){
        pageNumber-=1;
    }

    allAssets = "";

    var token = JSON.parse(localStorage.getItem("token"));
    var assets = getData("http://localhost:8081/api/asset?pageNumber="+pageNumber+"&pageSize="+pageSize+"");
          
            assets.then(json => {
              debugger;
              jsonAllData  = json;
              for(let i = 0; i< json.length; i++){
                allAssets += `<div  class="col-md-6">
                <div class="hotel-view bg-white radius-20">
                <a onclick="assetAllDetails(${json[i].id})"  class="hotel-view-thumb hotel-view-grid-thumb bg-image" style="background-image: url(${json[i].image});">
                </a>
                <div class="hotel-view-contents">
                <div class="hotel-view-contents-header">
                <span class="hotel-view-contents-review"> <i class="las la-star"></i> 4.5 <span class="hotel-view-contents-review-count"> (380) </span> </span>
                 <h3 class="hotel-view-contents-title"> <a href="hotel_details.html"> 
                 ${json[i].name} </a> </h3>
                <div class="hotel-view-contents-location mt-2">
                <span class="hotel-view-contents-location-icon"> <i class="las la-map-marker-alt"></i> </span>
                <span class="hotel-view-contents-location-para">${json[i].address}</span>
                </div>
                </div>
                <div class="hotel-view-contents-bottom">
                <div class="hotel-view-contents-bottom-flex">
                <div class="hotel-view-contents-bottom-contents">
                <h4 class="hotel-view-contents-bottom-title"> ${json[i].pricePerDay} <sub>/Day</sub> </h4>
                </div>
                <div class="btn-wrapper">
                <a href="javascript:void(0)" class="cmn-btn btn-bg-1 btn-small"> Reserve Now </a>
                 </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                `
              }
              document.getElementById("allAssets").innerHTML = allAssets;
              if(flag){
                pageNumber+=1;
              }
            }).catch(err => {
              console.log(json);
            });     
}


function formatDate(date) {
  var date = new Date(date);

  // Get year, month, and day part from the date
  var year = date.toLocaleString("default", { year: "numeric" });
  var month = date.toLocaleString("default", { month: "2-digit" });
  var day = date.toLocaleString("default", { day: "2-digit" });
  
  // Generate yyyy-mm-dd date string
  var formattedDate = year + "-" + month + "-" + day;
  return formattedDate;
}


function assetByCriteria(){
  var assetByCriteria ="";
  let city = document.getElementById("selected").value;
  let startDate = document.getElementById("startDate").value;
  let endDate = document.getElementById("endDate").value;
  let startingPrice = document.getElementById("startingPrice").value;
  let endingPrice = document.getElementById("endingPrice").value;

  let startDate1 = startDate.replaceAll("-","/")
  let endDate1 = endDate.replaceAll("-","/")

  assetData = {
    city: city,
    startDate: startDate1,
    endDate: endDate1,
    startingPrice: startingPrice,
    endingPrice: endingPrice
  }
  console.log(assetData);

 pageNumber = 0;
 let assetCriterias = getDataWithBody("http://localhost:8081/api/asset/criteria?pageNumber="+pageNumber+"&pageSize="+pageSize+"", assetData);
 assetCriterias.then(json =>{
                jsonAllData = json;
                for(let i = 0; i< json.length; i++){
                  assetByCriteria += `<div  class="col-md-6">
                <div class="hotel-view bg-white radius-20">
                <a onclick = "onclick=assetAllDetails(${json[i].id})" href="hotel_details.html" class="hotel-view-thumb hotel-view-grid-thumb bg-image" style="background-image: url(${json[i].image});">
                </a>
                <div class="hotel-view-contents">
                <div class="hotel-view-contents-header">
                <span class="hotel-view-contents-review"> <i class="las la-star"></i> 4.5 <span class="hotel-view-contents-review-count"> (380) </span> </span>
                 <h3 class="hotel-view-contents-title"> <a href="hotel_details.html"> 
                 ${json[i].name} </a> </h3>
                <div class="hotel-view-contents-location mt-2">
                <span class="hotel-view-contents-location-icon"> <i class="las la-map-marker-alt"></i> </span>
                <span class="hotel-view-contents-location-para">${json[i].location}</span>
                </div>
                </div>
                <div class="hotel-view-contents-bottom">
                <div class="hotel-view-contents-bottom-flex">
                <div class="hotel-view-contents-bottom-contents">
                <h4 class="hotel-view-contents-bottom-title"> ${json[i].pricePerDay} <sub>/Day</sub> </h4>
                </div>
                <div class="btn-wrapper">
                <a href="javascript:void(0)" class="cmn-btn btn-bg-1 btn-small"> Reserve Now </a>
                 </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                `
              }
              document.getElementById("allAssets").innerHTML = assetByCriteria;
 }).catch(error=>{
  console.log(error);
 })  
}

function assetAllDetails(assetId){
  debugger;
  let matchData;
  for(let i = 0; i< jsonAllData.length; i++){
    if(jsonAllData[i].id == assetId){
      matchData = {
        assetId: jsonAllData[i].id,
        assetImage: jsonAllData[i].image,
        assetName: jsonAllData[i].name,
        asssetPricePerDay: jsonAllData[i].pricePerDay,
        assetAddress: jsonAllData[i].address
      }
      break;
    }
  }
  sessionStorage.setItem("matchData", JSON.stringify(matchData));
  window.location.href = 'hotel_details.html'+'?'+matchData;
}

checkLoginCredentials();


showCities();

showAllAsset(true);


