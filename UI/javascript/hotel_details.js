// //                                          Fetch the Checking in data from hotel_details.html
// function checking_In(){
    
//     check_in = document.getElementById("Check_in").value;
//     check_out = document.getElementById("Check_out").value;
//     guest = document.getElementById("Guest").value;
//     children = document.getElementById("Children").value;
//     room = document.getElementById("Room").value;

//     var details = {
//         check_in:check_in,
//         check_out:check_out,
//         guest:guest,
//         children:children,
//         room:room
//     }
//     //                                                          converting the object into query parameter
//     const qs = new URLSearchParams(details).toString()
//     window.open("http://localhost:8080/checkout.html?"+qs,"_self")
// }

async function getData(url){
    debugger;
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

function temp(){
  var assetDetails = "";
  let i = 0;
  debugger;
  var info = JSON.parse(sessionStorage.getItem("matchData"));
  debugger;
  let assetImages = getData("http://localhost:8081/api/image/allassetimages/"+info.assetId);
  assetImages.then(value=>{
    info.allAssetImages = value;
    assetDetails+=`
    <div class="details-left-wrapper">
    <div class="details-contents bg-white radius-10">
        <div class="details-contents-header">


            <div class="details-contents-thumb details-contents-main-thumb bg-image"
                style="background-image: url(${info.assetImage});">
            </div>

            <div class="details-contents-header-flex">`
            for(let j = 0; j< info.allAssetImages.length; j++){
debugger;
               assetDetails+= `
                <div class="details-contents-header-thumb">
                    <img src="${info.allAssetImages[j]}" alt="img">
                    <a href="javascript:void(0)" class="more-photos"> <i class="las la-plus"></i>
                        More Photos </a>
                </div>`
            }

            assetDetails+= `</div>
        </div>
        <div class="hotel-view-contents">
            <div class="hotel-view-contents-header">
                <span class="hotel-view-contents-review"> <i class="las la-star"></i> 4.5 <span
                        class="hotel-view-contents-review-count"> (380) </span> </span>
                <h3 class="hotel-view-contents-title"> ${info.assetName} </h3>
                <div class="hotel-view-contents-location mt-2">
                    <span class="hotel-view-contents-location-icon"> <i
                            class="las la-map-marker-alt"></i> </span>
                    <span class="hotel-view-contents-location-para">${info.assetAddress}</span>
                </div>
            </div>
           
            <div class="hotel-view-contents-bottom">
                <div class="hotel-view-contents-bottom-flex">
                    <div class="hotel-view-contents-bottom-contents">
                        <h4 class="hotel-view-contents-bottom-title">${info.asssetPricePerDay}<sub>/Day</sub> </h4>
                    </div>
                    <div class="btn-wrapper">
                        <a href="javascript:void(0)" class="cmn-btn btn-bg-1 btn-small"> Reserve Now
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="details-contents-tab">
            <ul class="tabs details-tab details-tab-border">
                <li class="active" data-tab="about"> About </li>
                <li data-tab="reviews"> Reviews </li>
            </ul>
            <div id="about" class="tab-content-item active">
                <div class="about-tab-contents">
                    <p class="about-tab-contents-para"> One morning, when Gregor Samsa woke from
                        troubled dreams, he found himself transformed in his bed into a horrible
                        vermin. He lay on his armour-like back. </p>
                    <p class="about-tab-contents-para mt-4"> He lifted his head a little he could
                        see his brown belly, slightly domed and divided by arches into stiff
                        sections. The bedding was hardly able to cover it and seemed ready to slide
                        off any moment. His many legs,
                        pitifully thin compared with the size of the rest of him. </p>
                    <p class="about-tab-contents-para mt-4">
                        So many legs pitifully thin compared with the size of the rest of him waved
                        about helplessly as he looked What's happened to me. </p>
                </div>
            </div>
            <div id="reviews" class="tab-content-item">




            </div>
        </div>
    </div>
    <div class="hotel-view bg-white radius-20 mt-4">
        
    </div>
</div>
`
    document.getElementById("allAssetDetails").innerHTML = assetDetails
    lameAsset = "";

    lameAsset+=` <div class="sticky-top">
    <div class="hotel-details-widget hotel-details-widget-padding widget bg-white radius-10">
        <div class="details-sidebar">
            <div class="details-sidebar-dropdown custom-form">
                <form action="#">
                    <!--  Check in and Check out details -->
                    <div class="single-input">
                        <label class="details-sidebar-dropdown-title"> Check In </label>
                        <input class="form--control date-picker" type="text" placeholder="Check in"
                            id="Check_in">
                    </div>
                    <div class="single-input mt-3">
                        <label class="details-sidebar-dropdown-title"> Check Out </label>
                        <input class="form--control date-picker" type="text" placeholder="Check out"
                            id="Check_out">
                    </div>
                </form>
            </div>
    
            <div class="details-sidebar-quantity pt-4">
                <div class="details-sidebar-quantity-flex">
                    <div class="details-sidebar-quantity-item">
                        <h6 class="details-sidebar-quantity-title"> Guest </h6>
                        <div class="details-sidebar-quantity-field">
                            <span class="minus"><i class="las la-minus"></i></span><input
                                class="quantity-input" type="number" value="4" id="Guest"><span
                                class="plus"><i class="las la-plus"></i></span>
                        </div>
                    </div>
                    <div class="details-sidebar-quantity-item">
                        <h6 class="details-sidebar-quantity-title"> Children </h6>
                        <div class="details-sidebar-quantity-field">
                            <span class="minus"><i class="las la-minus"></i></span><input
                                class="quantity-input" type="number" value="3" id="Children"><span
                                class="plus"><i class="las la-plus"></i></span>
                        </div>
                    </div>
                    <div class="details-sidebar-quantity-item">
                        <h6 class="details-sidebar-quantity-title"> Room </h6>
                        <div class="details-sidebar-quantity-field">
                            <span class="minus"><i class="las la-minus"></i></span><input
                                class="quantity-input" type="number" value="2" id="Room"><span
                                class="plus"><i class="las la-plus"></i></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="btn-wrapper mt-4">
                <!-- <a href="checkout.html" class="cmn-btn btn-bg-1 btn-small" onclick="checkDate()"> Go -->
                <!-- to Checkout </a>  -->
                <button class="cmn-btn btn-bg-1 btn-small" type="button" onclick="checking_In()">Go
                    to Checkout</button>
            </div>
        </div>
    </div>
    <div class="hotel-details-widget widget bg-white radius-10">
        <div class="hotel-view">
            <div class="hotel-view-thumb hotel-view-grid-thumb bg-image"
                style="background-image: url(${info.assetImage});">
            </div>
            <div class="hotel-view-contents">
                <div class="hotel-view-contents-header">
                    <span class="hotel-view-contents-review"> <i class="las la-star"></i> 4.5 <span
                            class="hotel-view-contents-review-count"> (380) </span> </span>
                    <h3 class="hotel-view-contents-title"> King Suite Room </h3>
                    <div class="hotel-view-contents-location mt-2">
                        <span class="hotel-view-contents-location-icon"> <i
                                class="las la-map-marker-alt"></i> </span>
                        <span class="hotel-view-contents-location-para">${info.assetAddress}</span>
                    </div>
                </div>
                <div class="hotel-view-contents-middle">
                    <div class="hotel-view-contents-flex">
                        <div class="hotel-view-contents-icon myTooltip" data-bs-toggle="tooltip"
                            data-bs-placement="top" title="Car Parking">
                            <i class="las la-parking"></i>
                        </div>
                        <div class="hotel-view-contents-icon myTooltip" data-bs-toggle="tooltip"
                            data-bs-placement="top" title="Free Wifi">
                            <i class="las la-wifi"></i>
                        </div>
                        <div class="hotel-view-contents-icon myTooltip" data-bs-toggle="tooltip"
                            data-bs-placement="top" title="Free Breakfast">
                            <i class="las la-coffee"></i>
                        </div>
                        <div class="hotel-view-contents-icon myTooltip" data-bs-toggle="tooltip"
                            data-bs-placement="top" title="Home Service">
                            <i class="las la-quidditch"></i>
                        </div>
                        <div class="hotel-view-contents-icon myTooltip" data-bs-toggle="tooltip"
                            data-bs-placement="top" title="Swimming Pool">
                            <i class="las la-swimming-pool"></i>
                        </div>
                        <div class="hotel-view-contents-icon myTooltip" data-bs-toggle="tooltip"
                            data-bs-placement="top" title="Reception">
                            <i class="las la-receipt"></i>
                        </div>
                        <div class="hotel-view-contents-icon myTooltip" data-bs-toggle="tooltip"
                            data-bs-placement="top" title="Gym">
                            <i class="las la-dumbbell"></i>
                        </div>
                        <div class="hotel-view-contents-icon">
                            <a class="hotel-view-contents-icon-more" href="javascript:void(0)"> +8
                                More </a>
                        </div>
                    </div>
                </div>
                <div class="hotel-view-contents-bottom">
                    <div class="hotel-view-contents-bottom-flex">
                        <div class="hotel-view-contents-bottom-contents">
                            <h4 class="hotel-view-contents-bottom-title">${info.asssetPricePerDay}<sub>/Day</sub>
                            </h4>
                        </div>
                        <div class="btn-wrapper">
                            <a href="javascript:void(0)" class="cmn-btn btn-bg-1 btn-small"> Reserve
                                Now </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div> 
    `
    document.getElementById("lameDetails").innerHTML = lameAsset

  }).catch(err=>{
    console.log(err);
  })
}

temp();

{/* <div class="review-tab-contents">
<div class="review-tab-contents-single">
    <div class="rating-wrap">
        <div class="ratings">
            <span class="hide-rating"></span>
            <span class="show-rating"></span>
        </div>
        <p> <span class="total-ratings">(167)</span></p>
    </div>
    <p class="about-review-para mt-3"> Lorem ipsum dolor sit amet, consectetur
        adipiscing elit. Sed a egestas leo. Aliquam ut ante lobortis tellus
        cursus pellentesque. Praesent feugiat tellus quis aliquet </p>
    <div class="review-tab-contents-author mt-4">
        <h4 class="review-tab-contents-author-name"> Sandra M. Hurt </h4>
        <p class="review-tab-contents-author-para mt-2"> TrustPilot </p>
    </div>
</div>
<div class="review-tab-contents-single">
    <div class="rating-wrap">
        <div class="ratings">
            <span class="hide-rating"></span>
            <span class="show-rating"></span>
        </div>
        <p> <span class="total-ratings">(236)</span></p>
    </div>
    <p class="about-review-para mt-3"> Lorem ipsum dolor sit amet, consectetur
        adipiscing elit. Sed a egestas leo. Aliquam ut ante lobortis tellus
        cursus pellentesque. Praesent feugiat tellus quis aliquet </p>
    <div class="review-tab-contents-author mt-4">
        <h4 class="review-tab-contents-author-name"> Robert Fox </h4>
        <p class="review-tab-contents-author-para mt-2"> Designer </p>
    </div>
</div>
</div> */}


















{/* <div class="details-contents-header-thumb">
<img src="assets/img/single-page/details_1.jpg" alt="img">
</div>
<div class="details-contents-header-thumb">
<img src="assets/img/single-page/details_2.jpg" alt="img">
<a href="javascript:void(0)" class="more-photos"> <i class="las la-plus"></i>
    More Photos </a>
</div> */}