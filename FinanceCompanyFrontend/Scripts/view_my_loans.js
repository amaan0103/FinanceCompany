function sortIntTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("myTable");
    switching = true;
    dir = "asc"; 
    while (switching) {
      switching = false;
      rows = table.rows;
      for (i = 1; i < (rows.length - 1); i++) {
        shouldSwitch = false;
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        if (dir == "asc") {
          if (Number(x.innerHTML) > Number(y.innerHTML)){
            shouldSwitch= true;
            break;
          }
        } else if (dir == "desc") {
          if (Number(x.innerHTML) < Number(y.innerHTML)) {
            shouldSwitch = true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        switchcount ++;      
      } else {
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }
      }
    }
}

function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("myTable");
    switching = true;
    dir = "asc"; 
    while (switching) {
      switching = false;
      rows = table.rows;
      for (i = 1; i < (rows.length - 1); i++) {
        shouldSwitch = false;
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        if (dir == "asc") {
          if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
            shouldSwitch= true;
            break;
          }
        } else if (dir == "desc") {
          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
            shouldSwitch = true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        switchcount ++;      
      } else {
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }
      }
    }
}

function loadLoanDetails(loan_applications) {
    tblBody = document.getElementById("tableId")
    loan_applications
        .forEach(
            (c) => {
                const row = document.createElement("tr")
                row.className = "table-light"
                const application_number = document.createElement("td")
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

                application_number.innerHTML = c.applicationNumber.toString().bold()
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
    
    // got customer ID from local Storage

    customerId = localStorage.getItem("customer_id")
    document.getElementById("customerid_index").innerText = "CUSTOMER ID : " + customerId
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










