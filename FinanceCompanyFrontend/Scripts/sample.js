/*
var data = [{
    "message":"record found",
    "responseData":{
        "customerId" : 1,
        "customerName" : "Rahul",
        "customerPhone" : 9949380094
    },
    "statusCode":200
},
{
    "message":"record found",
    "responseData":{
        "customerId" : 2,
        "customerName" : "Abhishek",
        "customerPhone" : 8179173456
    },
    "statusCode":200
}]
*/

function get_all_customers() {
    url = "http://localhost:8080/FinanceCompanyBackend/rest/clerk/getcustomers"
    const req = new XMLHttpRequest()
    req.open('GET', url)
    let data = req.send();
    resObject = JSON.parse(req.responseText)
    console.log('hehe' + data);
    return resObject.responseData
    // return data
}

window
    .addEventListener(
        'DOMContentLoaded',
        function () {
            console.log(1);
            data = get_all_customers()
            console.log(data);
            var result = ""
            data.forEach(item => {
                response = item
                result += "Customer ID : " + response.customerId + " Customer Name : " + response.customerName + "<br>"
            }
            );
            this.document.getElementById("result").innerHTML = result
        }
    )


class Validation {
    constructor() { }
    isEmail(email) {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    }
    isPassword(password) {
        passwordRegExp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
        if (passwordRegExp.test(password)) { 
            return true; 
        } else { 
            return false; 
        }
    }
    isMobile(mobile) {
        return mobile.toString().length() == 10
    }
    validate(){

    }
}