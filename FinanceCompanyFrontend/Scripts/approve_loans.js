//function to load data in Table body element
//"applicationNumber":1,"applyDate":"2020-09-04Z","customerId":1,"loanAmount":1000.0,"loanEmi":5670.0,"loanId":1,"loanStatus":"pending","loanTenure":5

function approve_loan(application_number) {
    //need to finish
}

function reject_loan(application_number) {
    //need to finish
}

const buttonPressed = e => {
    console.log(pending_IDs)
    applicationNumber = e.target.id 
    if(e.target.textContent == "Approve"){
        approve_loan(applicationNumber)
    }else if(e.target.textContent == "Reject"){
        reject_loan(applicationNumber)
    }
    console.log("end of function");
    //endpoint - http://localhost:8080/FinanceCompanyBackend/rest/manager/status/{applicationNumber}/{status}
}

const pending_IDs = []

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

                const imgEle = document.createElement('img')
                imgEle.src = c.products
                imgEle.alt = "NA"
                imgEle.style.width = "200px";
                imgEle.style.margin = "2px";

                application_number.textContent = c.applicationNumber
                apply_date.textContent = c.applyDate;
                customer_id.textContent = c.customerId;
                loan_amount.textContent = c.loanAmount;
                loan_emi.textContent = c.loanEmi;
                loan_id.textContent = c.loanId;
                loan_status.textContent = c.loanStatus;
                loan_tenure.textContent = c.loanTenure;
                
                //<button type="button" class="btn btn-success">Success</button>
                const button = document.createElement("button")
                button.setAttribute("type", "button")
                button.setAttribute("id",c.applicationNumber)
                //button.setAttribute("onclick", "clicked()")
                button.classList.add("btn")
                if(c.loanStatus == "pending"){
                    button.classList.add("btn-success")
                    button.textContent = "Approve"
                    pending_IDs.push(c.applicationNumber)
                }else{
                    button.classList.add("btn-success")
                    button.textContent = "Reject"
                }

                row.appendChild(application_number)
                row.appendChild(customer_id)
                row.appendChild(loan_id)
                row.appendChild(apply_date)
                row.appendChild(loan_amount)
                row.appendChild(loan_tenure)
                row.appendChild(loan_emi)
                row.appendChild(loan_status)
                row.appendChild(imgEle)
                row.appendChild(button)    
                
                row.classList.add("table-light")
                row.setAttribute("id", c.applicationNumber)
                tblBody.appendChild(row)
            }
        )

        const buttons = document.getElementsByTagName("button");

        for (let button of buttons) {
          button.addEventListener("click", buttonPressed);
        }

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










