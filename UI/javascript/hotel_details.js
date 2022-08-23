//                                          Fetch the Checking in data from hotel_details.html
function checking_In(){
    
    check_in = document.getElementById("Check_in").value;
    check_out = document.getElementById("Check_out").value;
    guest = document.getElementById("Guest").value;
    children = document.getElementById("Children").value;
    room = document.getElementById("Room").value;

    var details = {
        check_in:check_in,
        check_out:check_out,
        guest:guest,
        children:children,
        room:room
    }
    //                                                          converting the object into query parameter
    const qs = new URLSearchParams(details).toString()
    window.open("http://127.0.0.1:5500/checkout.html?"+qs,"_self")
}