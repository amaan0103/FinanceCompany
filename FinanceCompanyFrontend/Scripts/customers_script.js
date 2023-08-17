//AJAX => asynchronous JavaScript and XML
//function to load data in Table body element
function loadCustomerDetails(customers) {
    
    result = ""
    customers
        .forEach(
            (item) => {
                result += "Customer ID : " + item.customerId + " Customer Name : " + item.customerName + "<br>"
                
            }
        )
        this.document.getElementById("result").innerHTML = result
}
//function to load data in form element
function displayData(productObject) {
    document.getElementById('spanProduct').innerText = productObject.productName;
    document.getElementById('txtName').value = productObject.productName;
    document.getElementById('txtPrice').value = productObject.price.toString();
    document.getElementById('txtDesc').value = productObject.description;
    document.getElementById('txtId').value = productObject.productId.toString();
    document.getElementById('txtCatId').value = productObject.categoryId.toString();
}

//function to get all the products
function getCustomers() {
    const req = new XMLHttpRequest()
    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            //console.log(req.responseText)
            const serviceResponseObject = JSON.parse(req.responseText)
            //console.log(serviceResponseObject)
            loadCustomerDetails(serviceResponseObject.responseData)
        }
    }
    req.open('GET', 'http://localhost:8080/FinanceCompanyBackend/rest/clerk/getcustomers')
    req.send()
}
/*
const btnObject = document.getElementById('btnLoad')
//if (btnObject !== undefined) {
if (btnObject) {
    btnObject.addEventListener('click', getProductsData)
}
*/
//code which will be executed immediately afetr DOM content creation is completed and the page is loaded in the browser
window
    .addEventListener(
        'DOMContentLoaded',
        function () {
            getCustomers()
        }
    )










