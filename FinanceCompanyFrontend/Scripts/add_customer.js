function send_to_customertable(data) {
    const req = new XMLHttpRequest()
    req.open('POST', 'http://localhost:8080/FinanceCompanyBackend/rest/clerk/addCustomer', true);
    req.setRequestHeader("Content-Type", "application/json");

    req.onload = () => {
        console.log(JSON.parse(req.response));
    };

    req.send(JSON.stringify(data));
}

function send_to_authtable(data) {
    const req = new XMLHttpRequest()
    req.open('POST', 'http://localhost:8080/FinanceCompanyBackend/rest/auth/signup', true);
    req.setRequestHeader("Content-Type", "application/json");

    // req.onload = function(){
    //     console.log(req.status, req.readyState);
    //     if (req.status === 200 && req.readyState === 4) {
    //         window.alert(req.responseText)
    //     }
    // };
    // req.onreadystatechange = function(){
    //     console.log(req.status, req.readyState);    
    //     if (req.status === 200 && req.readyState === 4) {
    //         window.alert(req.responseText)
    //     }
    // };

    req.onload = () => {
        console.log(JSON.parse(req.response));
    };

    req.send(JSON.stringify(data));
}

function upload_customer_data() {

    var first_name = document.getElementById("first_name").value;
    var last_name = document.getElementById("last_name").value
    var name = first_name + " " + last_name
    var gender = document.getElementById("gender").value
    var mobile = document.getElementById("mobile").value
    var email = document.getElementById("email").value
    var password = document.getElementById("password").value
    var password_ = document.getElementById("password_").value

    if (password != password_) {
        alert("Password Mismatch! Please Type Again")
    } else {

        console.log("Data Received : ", name, gender, mobile, email, password)

        data1 = {
            "customerName": name,
            "email": email,
            "gender": gender,
            "mobile": mobile
        }
        data2 = {
            "username": email,
            "password": password,
            "role": 3
        }

        send_to_customertable(data1)
        send_to_authtable(data2)

        alert("Customer Created!")
    }
}