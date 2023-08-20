
function loadLoanDetails(loan_applications) {
    tblBody = document.getElementById("tableId")
    loan_applications
        .forEach(
            (c) => {
                const row = document.createElement("tr")
                row.className = "table-light"
                const application_number = document.createElement("th")
                const apply_date = document.createElement("td")
                const loan_amount = document.createElement("td")
                const loan_emi = document.createElement("td")
                const loan_id = document.createElement("td")
                const loan_status = document.createElement("td")
                const loan_tenure = document.createElement("td")

                const imgEle = document.createElement('img')
                imgEle.src = c.documents
                imgEle.alt = "Image Unavailable"
                imgEle.style.width = "200px";
                imgEle.style.margin = "2px";

                application_number.textContent = c.applicationNumber
                apply_date.textContent = c.applyDate;
                loan_amount.textContent = c.loanAmount;
                loan_emi.textContent = c.loanEmi;
                loan_id.textContent = c.loanId;
                loan_status.textContent = c.loanStatus;
                loan_tenure.textContent = c.loanTenure;


                row.appendChild(application_number)
                row.appendChild(loan_id)
                row.appendChild(apply_date)
                row.appendChild(loan_amount)
                row.appendChild(loan_tenure)
                row.appendChild(loan_emi)
                row.appendChild(loan_status)
                row.appendChild(imgEle)

                row.classList.add("table-light")
                row.setAttribute("id", c.applicationNumber)
                tblBody.appendChild(row)
            }
        )
}

function getLoans() {
    const req = new XMLHttpRequest()
    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            console.log(req.responseText)
            const serviceResponseObject = JSON.parse(req.responseText)
            loadLoanDetails(serviceResponseObject.responseData)
        }
    }
    // need to get the customer ID here from credentials
    customerId = 1
    document.getElementById("customerid_index").innerText += " " + customerId.toString()
    req.open('GET', `http://localhost:8080/FinanceCompanyBackend/rest/customer/getApplicationsCustomer/${customerId}`)
    req.send()
}


window
    .addEventListener(
        'DOMContentLoaded',
        function () {
            getLoans()
        }
    )










