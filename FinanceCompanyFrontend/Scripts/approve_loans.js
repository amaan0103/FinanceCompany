//AJAX => asynchronous JavaScript and XML
//function to load data in Table body element
//"applicationNumber":1,"applyDate":"2020-09-04Z","customerId":1,"loanAmount":1000.0,"loanEmi":5670.0,"loanId":1,"loanStatus":"pending","loanTenure":5
function loadCustomerDetails(loan_applications) {
    tblBody = document.getElementById("tableId")
    loan_applications
        .forEach(
            (c) => {
                const row = document.createElement("tr")
                // change the style
                //row.classList.add("table-light")
                row.className = "table-light"
                const application_number = document.createElement("th")
                const apply_date = document.createElement("td")
                const customer_id = document.createElement("td")
                const loan_amount = document.createElement("td")
                const loan_emi = document.createElement("td")
                const loan_id = document.createElement("td")
                const loan_status = document.createElement("td")
                const loan_tenure = document.createElement("td")
            
                application_number.textContent = c.applicationNumber
                apply_date.textContent = c.applyDate;
                customer_id.textContent = c.customerId;
                loan_amount.textContent = c.loanAmount;
                loan_emi.textContent = c.loanEmi;
                loan_id.textContent = c.loanId;
                loan_status.textContent = c.loanStatus;
                loan_tenure.textContent = c.loanTenure;
                
                row.appendChild(application_number)
                row.appendChild(customer_id)
                row.appendChild(loan_id)
                row.appendChild(apply_date)
                row.appendChild(loan_amount)
                row.appendChild(loan_tenure)
                row.appendChild(loan_emi)
                row.appendChild(loan_status)    
                
                row.classList.add("table-light")
                tblBody.appendChild(row)
            }
        )
}

//function to get all the products
function getApplications() {
    const req = new XMLHttpRequest()
    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            //console.log(req.responseText)
            const serviceResponseObject = JSON.parse(req.responseText)
            //console.log(serviceResponseObject)
            loadCustomerDetails(serviceResponseObject.responseData)
        }
    }
    req.open('GET', 'http://localhost:8080/FinanceCompanyBackend/rest/manager/getApplications')
    req.send()
}

//function to display contents on the page
function display_on_page(ID, name, gender, mobile, email) {

}

//code which will be executed immediately afetr DOM content creation is completed and the page is loaded in the browser
window
    .addEventListener(
        'DOMContentLoaded',
        function () {
            getApplications()
        }
    )










