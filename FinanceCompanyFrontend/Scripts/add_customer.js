
function upload_customer_data() {
    var first_name = document.getElementById("first_name").value;
    var last_name = document.getElementById("last_name").value
    var name = first_name + " " + last_name
    var gender = document.getElementById("gender").value
    var mobile = document.getElementById("mobile").value
    var email = document.getElementById("email").value

    console.log(name, gender, mobile, email)
    data = {
        //"customerId": 0,
        "customerName": name,
        "email": email,
        "gender": gender,
        "mobile": mobile
    }

    const req = new XMLHttpRequest();
    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            window.alert(req.responseText)
        }
    }

    req.open('POST', 'http://localhost:8080/FinanceCompanyBackend/rest/clerk/addCustomer',true);
    req.setRequestHeader("Content-Type","application/json")
    req.send(JSON.stringify(data));
    

    alert("Customer Created")
}