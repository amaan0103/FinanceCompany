//AJAX => asynchronous JavaScript and XML
//function to load data in Table body element
function loadLoanDetails(customers) {
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
                const loan_type = document.createElement("td")
                const interest = document.createElement("td")
            
                id.textContent = c.loanId;
                loan_type.textContent = c.loanType;
                interest.textContent = c.interestRate;
            
                
                row.appendChild(id)
                row.appendChild(loan_type)
                row.appendChild(interest)

                tblBody.appendChild(row)
            }
        )
}

//function to get all the products
function getLoanTypes() {
    const req = new XMLHttpRequest()
    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            //console.log(req.responseText)
            const serviceResponseObject = JSON.parse(req.responseText)
            //console.log(serviceResponseObject)
            loadLoanDetails(serviceResponseObject.responseData)
        }
    }
    req.open('GET', 'http://localhost:8080/FinanceCompanyBackend/rest/customer/getAllLoans')
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
            getLoanTypes()
        }
    )










