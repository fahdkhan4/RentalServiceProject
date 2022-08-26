function sendData(){
    
    username =  document.getElementById("fullname").value
    password = document.getElementById("createpassword").value
    email = document.getElementById("email").value
    cnic=document.getElementById("cnic").value
    type=document.getElementById("type").value
    gender=document.getElementById("gender").value
    address=document.getElementById("address").value
    number=document.getElementById("phone").value
    image = document.getElementById("image");

    var obj = {
        name:username,
        number:number,
        email:email,
        password:password,
        cnic:cnic,
        type:type,
        gender:gender,
       address:address
    }

    obj = JSON.stringify(obj);
    
    console.log(obj);
    var formData = new FormData();

    for (const file of image.files) {
        formData.append("file",file)
    }
    formData.append('data',obj);

    fetch("http://localhost:8081/api/user",{
        method:"POST",
        body:formData
    
    }).then((response)=>response.json())
    .then((data)=> console.log(data))
    .catch((error)=>console.log(error))

}