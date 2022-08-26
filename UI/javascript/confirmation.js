
function showBookingConfirmationDetails() {
    searchQueryParam = window.location.search;
    const urlparams = new URLSearchParams(searchQueryParam)
    assetBooking_Id = urlparams.get("bookingNumber")
    customerName = urlparams.get("customer_name") 

    var render = ""
    render += `<p class="confirmation-contents-para"> Dear ${customerName}, Your reservation <a href="javascript:void(0)">
    #${assetBooking_Id} </a> has been confirmed. Please check your mail for reservation invoice. Thanks for
    reserving our Asset! </p>`

    document.getElementById("booking_confirmation").innerHTML = render
}

function generateInvoice(){
    
    searchQueryParam = window.location.search;
    const urlparams = new URLSearchParams(searchQueryParam)
    booking_id= urlparams.get("bookingNumber")
    console.log(booking_id);
    fetch(`http://localhost:8081/api/generateinvoice/${booking_id}`,{
        method:"GET"
    })
    .then(response => { 
        return response.blob()
    })
    .then( data=>{
        var a = document.createElement("a");
        a.href = window.URL.createObjectURL(data);
        a.download = `Booking #${booking_id} Invoice`;
        a.click();
    })
    .catch((error)=>console.log(error))
}

showBookingConfirmationDetails()