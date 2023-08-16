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

async function get_all_customers() {
    url = "http://localhost:8080/FinanceCompanyBackend/rest/clerk/getcustomers"
    const req = new XMLHttpRequest()
    await req.open('GET',url)
    let data = await req.send();
    req.responseType='json'
    console.log('hehe'+data);
    req.onload=()=>{
        data = req.response
        console.log(data)
        return data
    }
    
    // return data
}

window
    .addEventListener(
        'DOMContentLoaded',
        async function () {  
            console.log(1);
            data = await get_all_customers()
            console.log(data);
            var result = ""
            data.forEach(item => {
                response = item.responseData
                result += "Customer ID : " + response.customerId + " Customer Name : " + response.customerName + "<br>"
            }
            );
            this.document.getElementById("result").innerHTML = result
        }
    )