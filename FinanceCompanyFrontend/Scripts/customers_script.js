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


function get_all_customers() {
    return data
}

window
    .addEventListener(
        'DOMContentLoaded',
        function () {  
            console.log(1);
            data = get_all_customers()
            var result = ""
            data.forEach(item => {
                response = item.responseData
                result += "Customer ID : " + response.customerId + " Customer Name : " + response.customerName + "<br>"
            }
            );
            this.document.getElementById("result").innerHTML = result
        }
    )