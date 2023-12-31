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
        if (Number(x.innerHTML) > Number(y.innerHTML)) {
          shouldSwitch = true;
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
      switchcount++;
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
          shouldSwitch = true;
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
      switchcount++;
    } else {
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}

async function downloadImage(
  imageSrc,
  nameOfDownload = 'my-image.png',
) {
  const response = await fetch(imageSrc);

  const blobImage = await response.blob();

  const href = URL.createObjectURL(blobImage);

  const anchorElement = document.createElement('a');
  anchorElement.href = href;
  anchorElement.download = nameOfDownload;

  document.body.appendChild(anchorElement);
  anchorElement.click();

  document.body.removeChild(anchorElement);
  window.URL.revokeObjectURL(href);
}

window.addEventListener('DOMContentLoaded', () =>
  document.getElementById('download_button').addEventListener('click', () => {
    downloadImage(
      document.getElementById("modal_body").src,
      'my-image.png',
    )
      .then(() => {
        console.log('The image has been downloaded');
      })
      .catch(err => {
        console.log('Error downloading image: ', err);
      });
  })
)

function display_modal(url) {
  const imgEle = document.createElement('img')
  imgEle.src = url;
  imgEle.alt = "Image Unavailable"
  imgEle.style.width = "200px";
  imgEle.style.margin = "2px";
  document.getElementById("modal_body").src = url;
  var modal = document.getElementById("modal_head");
  modal.style.display = "block";

}

const buttonPressed = e => {
  application_number = e.target.id
  if (confirm(`Do you want to Delete Application ID : ${application_number} ?`)) {

    const req = new XMLHttpRequest();
    req.open('DELETE', `http://localhost:8080/FinanceCompanyBackend/rest/customer/deleteApplication/${application_number}`, true);

    req.send();
    alert(`Loan Application ID : ${application_number} is Deleted!`)
  }
  location.reload()
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
        const x = document.createElement("td")
        const parent_del = document.createElement("td")
        const del_btn = document.createElement("button")
        parent_del.appendChild(del_btn)


        //const imgEle = document.createElement('img')
        //imgEle.src = c.documents;
        //imgEle.alt = "Image Unavailable"
        //imgEle.style.width = "200px";
        //imgEle.style.margin = "2px";
        //<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"

        const parent = document.createElement("td")
        const view_doc = document.createElement("button")
        parent.appendChild(view_doc)
        view_doc.textContent = "View Documents"
        view_doc.classList.add("btn")
        view_doc.classList.add("btn-outline-secondary")
        view_doc.setAttribute("type", "button")

        view_doc.addEventListener("click", () => {
          display_modal(c.documents)
        })

        application_number.innerHTML = c.applicationNumber.toString().bold()
        apply_date.textContent = c.applyDate;
        loan_amount.textContent = c.loanAmount;
        loan_emi.textContent = c.loanEmi;
        loan_id.textContent = c.loanId;
        loan_status.textContent = c.loanStatus;
        loan_tenure.textContent = c.loanTenure;

        del_btn.textContent = "WITHDRAW"
        del_btn.setAttribute("id", c.applicationNumber)
        del_btn.classList.add("btn")
        del_btn.classList.add("btn-danger")
        del_btn.addEventListener("click", buttonPressed);
        if (c.loanStatus.toLowerCase() != "pending") {
          del_btn.classList.add("disabled")
        }
        //del_btn.setAttribute("style","color:white;")

        row.appendChild(application_number)
        row.appendChild(loan_id)
        row.appendChild(apply_date)
        row.appendChild(loan_amount)
        row.appendChild(loan_tenure)
        row.appendChild(loan_emi)
        row.appendChild(loan_status)
        row.appendChild(parent)
        row.appendChild(parent_del)

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
      const serviceResponseObject = JSON.parse(req.responseText)
      loadLoanDetails(serviceResponseObject.responseData)
    }
  }
  // got the customer ID from localstorage
  customerId = localStorage.getItem("customer_id")
  document.getElementById("customerid_index").innerText += " " + customerId.toString()
  req.open('GET', `http://localhost:8080/FinanceCompanyBackend/rest/customer/getApplicationsCustomer/${customerId}`)
  req.send()
}

window.addEventListener('DOMContentLoaded', () =>
  document.getElementById("close").addEventListener('click', () => {
    document.getElementById("modal_head").style.display = "none";
  }))

window.addEventListener('DOMContentLoaded', () =>
  document.getElementById("xMark").addEventListener('click', () => {
    document.getElementById("modal_head").style.display = "none";
  }))

window
  .addEventListener(
    'DOMContentLoaded',
    function () {
      getLoans()
    }
  )










