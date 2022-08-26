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
    // price will come in the query param of that asset
    rentedDays = "";

    //                                                                              Date and time will be splited
    var [checkin_dateValues,checkin_timeValues] = check_in.split(' ')
    var [checkout_dateValues,checkout_timeValues] = check_out.split(' ') 
                                                                                //  Date is splited in day,month,year
    var [checkin_Date,checkin_Month,checkin_Year] = checkin_dateValues.split('-')
    var [checkout_Date,checkout_Month,checkout_Year] = checkout_dateValues.split('-')
    //  2022 calender 
    var monthDays = {
        01:31,
        02:28,
        03:31,
        04:30,
        05:31,
        06:30,
        07:31,
        08:31,
        09:30,
        10:31,
        11:30,
        12:31,
    }
   
    if(checkin_Month == checkout_Month){
        if(checkin_Date < checkout_Date){
            rentedDays = checkout_Date - checkin_Date
        }
    }
    else if(checkin_Month != checkout_Month){
        //  if banda and 3 mahina ka bola to checkin_month sa 1 mahina ajaiga and checkout_date sa last month ka pata chale ga lakin bich wale month ka pata nahe chale ga 
        // For feburary has 28 days
                parseCheckInMonth = parseInt(checkin_Month)
                //  find the days in that month
                daysinCurrentMonth = monthDays[parseCheckInMonth]
                //  sub total days in that month with checkin_Date
                daysInfirstMonth = daysinCurrentMonth - parseInt(checkin_Date)
                //  
                rentedDays = daysInfirstMonth + parseInt(checkout_Date)
            
    }
    console.log(rentedDays);

    render = ""
    render += `<ul class="checkout-flex-list list-style-none checkout-border-top pt-3 mt-3">
                    <li class="list"> <span class="regular"> Checking In </span> <span class="strong">${check_in}</span> </li>
                    <li class="list"> <span class="regular"> Checking Out </span> <span class="strong">${check_out}</span> </li>
                    <li class="list"> <span class="regular"> Number of Rooms </span> <span class="strong">${room}</span> </li>
                    <li class="list"> <span class="regular"> Total Stay </span> <span class="strong">${rentedDays} Nights, ${rentedDays-1} Days </span> </li>
                    <li class="list"> <span class="regular"> Number of Person </span> <span class="strong">${guest}</span> </li>
                    <li class="list"> <span class="regular"> Number of Children </span> <span class="strong">${children}</span> </li>
                    </ul>`

    document.getElementById("sidebar_contents").innerHTML = render;
}

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
    },400)
}

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
            // fetch asset id from the query param
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
                window.open("/confirmation.html?bookingNumber="+data.id+"&customer_name="+userdetails[0].name, "_self")
            }
        })
        .catch((error) => console.log(error))
      
}
showChecking_In_Data();
showDataOfUser();

