//    User details Array
var userdetails = []
//    Fetching Booking Details from Query Params 
function showChecking_In_Data() {

    myKeyValues = window.location.search
    const urlparams = new URLSearchParams(myKeyValues)
    check_in = urlparams.get("check_in")
    check_out = urlparams.get("check_out")
    guest = urlparams.get("guest")
    children = urlparams.get("children")
    room = urlparams.get("room")


    // date_array = check_in.split("-")

    // console.log("Date"+date_array[0]);
    // console.log("Month "+date_array[1]);
    // console.log("Year "+date_array[2]);
    // stringma= date_array[2].toString()
    // time_year = stringma.split(" ")
    // console.log(time_year[0]);
    // console.log(time_year[1]);
    // var checkDate = new Date(check_in)
    // console.log("Check Date : "+checkDate);
    // var checkoutDate = new Date(check_out)
    // console.log("Checkout Date : "+checkoutDate);
    // var newdate = checkoutDate.getDate - checkDate.getDate()
    // console.log(newdate)    ;

    render = ""
    render += `<ul class="checkout-flex-list list-style-none checkout-border-top pt-3 mt-3">
                    <li class="list"> <span class="regular"> Checking In </span> <span class="strong">${check_in}</span> </li>
                    <li class="list"> <span class="regular"> Checking Out </span> <span class="strong">${check_out}</span> </li>
                    <li class="list"> <span class="regular"> Number of Rooms </span> <span class="strong">${room}</span> </li>
                    <li class="list"> <span class="regular"> Total Stay </span> <span class="strong"> 3 Nights, 1 Days </span> </li>
                    <li class="list"> <span class="regular"> Number of Person </span> <span class="strong">${guest}</span> </li>
                    <li class="list"> <span class="regular"> Number of Children </span> <span class="strong">${children}</span> </li>
                    </ul>`

    document.getElementById("sidebar_contents").innerHTML = render;
}
showChecking_In_Data();
//                                                      Fetching user data
function showDataOfUser() {

    getJwtToken = localStorage.getItem("jwtToken")
    email = localStorage.getItem("email")

    getJwtBearerToken = "Bearer " + getJwtToken
    

    fetch("http://localhost:8081/api/user/"+email, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": getJwtBearerToken
        }
    })

        .then((resource) => resource.json())
        .then((data) => userdetails.push(data))
        .catch((error) => console.log(error))

    setTimeout(function(){
        console.log("Show Data of User Method "+userdetails);
    render = ""
    for (let index = 0; index < userdetails.length; index++) {
        render += ` <form action="#">
                <div class="input-flex-item">
                    <div class="single-input mt-4">
                        <label class="label-title"> First Name </label>
                        <input class="form--control" type="text" name="name"
                            placeholder="Type First Name" value=${userdetails[index].name} >
                    </div>
                    <div class="single-input mt-4">
                        <label class="label-title"> CNIC </label>
                        <input class="form--control" type="text" name="cnic"
                            placeholder="Type CNIC" value=${userdetails[index].cnic}>
                    </div>
                </div>
                <div class="input-flex-item">
                    <div class="single-input mt-4">
                        <label class="label-title"> Mobile Number </label>
                        <input class="form--control" id="phone" type="tel"
                            placeholder="Type Mobile Number" value=${userdetails[index].number}>
                    </div>
                    <div class="single-input mt-4">
                        <label class="label-title"> Email Address </label>
                        <input class="form--control" type="text" name="email"
                            placeholder="Type Email" value=${userdetails[index].email}>
                    </div>
                </div>
                <div class="input-flex-item">
                    <div class="single-input mt-4">
                        <label class="label-title"> Address </label>
                        <input class="form--control" type="text" name="address"
                            placeholder="Type Address" value=${userdetails[index].address}>
                    </div>
                </div>
                <div class="single-input mt-4">
                    <label class="label-title"> Country </label>
                    <div class="banner-location-single-contents-dropdown">
                        <select class="js-select">
                            <option value="1">Bangladesh</option>
                            <option value="2">Pakistan</option>
                            <option value="3">America</option>
                            <option value="4">Russia</option>
                        </select>
                    </div>
                </div>
                <div class="input-flex-item">
                    <div class="single-input mt-4">
                        <label class="label-title"> City/Town </label>
                        <div class="banner-location-single-contents-dropdown">
                            <select class="js-select">
                                <option value="1">Dhaka</option>
                                <option value="2">Karachi</option>
                                <option value="3">Washington</option>
                                <option value="4">Mosco</option>
                            </select>
                        </div>
                    </div>
                    <div class="single-input mt-4">
                        <label class="label-title"> Zip Code </label>
                        <div class="banner-location-single-contents-dropdown">
                            <select class="js-select">
                                <option value="1">Rampura</option>
                                <option value="2">Farmgate</option>
                                <option value="3">Uttara</option>
                                <option value="4">Gulshan</option>
                            </select>
                        </div>
                    </div>
                </div>
            </form>`
    }
    document.getElementById("userDetails").innerHTML = render
    },300)
}
showDataOfUser()
//                                                                  Add Booking in the record
function addingBooking() {
    // token and user data and asset id
    //  if the payment went successfully then add the booking in the db
    myKeyValues = window.location.search
    const urlparams = new URLSearchParams(myKeyValues)
    start_date = urlparams.get("check_in")
    end_date = urlparams.get("check_out")

    jwtToken = localStorage.getItem("jwtToken")

    let AssetBookingDto = {
        asset: {
            id: 1
        },
        user: {
            id: userdetails[0].id
        },
        startDate: start_date,
        endDate: end_date,
        price: 2000.0
    }

    fetch("http://localhost:8081/api/assetbooking", {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + jwtToken,
            "Content-type": "application/json"
        },
        body: JSON.stringify(AssetBookingDto),
    })
        .then((response) => response.json())
        .then((data) => {
            if (data != null) {
                // return the booking number in the query param 
                window.open("http://127.0.0.1:5500/confirmation.html?bookingNumber="+data.id+"&customer_name="+userdetails[0].name, "_self")
            }
        })
        .catch((error) => console.log(error))
      
}

