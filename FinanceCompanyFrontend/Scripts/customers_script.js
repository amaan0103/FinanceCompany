//AJAX => asynchronous JavaScript and XML
//function to load data in Table body element
function loadCustomerDetails(customers) {
    tblBody = document.getElementById("tableId")
    customers
        .forEach(
            (c) => {
                const row = document.createElement("tr")
                // change the style
                //row.classList.add("table-light")
                row.className = "table-light"
                const id = document.createElement("th")
                id.scope = "row"
                const name = document.createElement("td")
                const gender = document.createElement("td")
                const phoneno = document.createElement("td")
                const email = document.createElement("td")
            
                id.textContent = c.customerId;
                name.textContent = c.customerName;
                gender.textContent = c.gender;
                phoneno.textContent = c.mobile;
                email.textContent = c.email;
                
                row.appendChild(id)
                row.appendChild(name)
                row.appendChild(gender)
                row.appendChild(phoneno)
                row.appendChild(email)

                tblBody.appendChild(row)
            }
        )
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

//function to display contents on the page
function display_on_page(ID, name, gender, mobile, email) {

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










