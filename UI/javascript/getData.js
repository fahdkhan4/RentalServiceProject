var jwtToken;


function checkLoginCredentials(){

    email = document.getElementById("username").value;
    password = document.getElementById("password").value;

    var loginCredentials = {
        email:email,
        password:password
    }
    
    fetch("http://localhost:8081/login",{
        body:JSON.stringify(loginCredentials),
        method:"POST",
        headers:{
            "Content-type":"application/json"
        }
    })
    .then((response) => response.json())
    .then((jwttoken) => jwtToken = jwttoken.jwt )
    .catch((error)=> console.log(error))
    
    setTimeout(function(){
        if(jwtToken != null){
            localStorage.setItem("email",email)
            localStorage.setItem("jwtToken",jwtToken)
            window.open("/listing.html","_self")
        }
        else{
            alert("Wrong password")
        } 
        
    },300)
}





