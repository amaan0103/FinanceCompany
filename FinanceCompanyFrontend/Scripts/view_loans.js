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

const btn = document.getElementById('download_button');

btn.addEventListener('click', () => {
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
});

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

function loadLoanDetails(loan_applications) {
  tblBody = document.getElementById("tableId")
  loan_applications
    .forEach(
      (c) => {
        const row = document.createElement("tr")
        // change the style
        //row.classList.add("table-light")
        row.className = "table-light"
        const application_number = document.createElement("td")
        const apply_date = document.createElement("td")
        const customer_id = document.createElement("td")
        const loan_amount = document.createElement("td")
        const loan_emi = document.createElement("td")
        const loan_id = document.createElement("td")
        const loan_status = document.createElement("td")
        const loan_tenure = document.createElement("td")

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
        
        view_doc.addEventListener("click",()=>{
          display_modal(c.documents)
        })

        application_number.innerHTML = c.applicationNumber.toString().bold()
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
        row.appendChild(parent)

        row.classList.add("table-light")
        row.setAttribute("id", c.applicationNumber)
        tblBody.appendChild(row)
      }
    )
}

//function to get all the loans
function getLoans() {
  const req = new XMLHttpRequest()
  req.onreadystatechange = () => {
    if (req.status === 200 && req.readyState === 4) {
      //console.log(req.responseText)
      const serviceResponseObject = JSON.parse(req.responseText)
      //console.log(serviceResponseObject)
      loadLoanDetails(serviceResponseObject.responseData)
    }
  }
  req.open('GET', 'http://localhost:8080/FinanceCompanyBackend/rest/clerk/getApplications')
  req.send()
}

document.getElementById("close").addEventListener('click',()=>{
  document.getElementById("modal_head").style.display = "none";
})

document.getElementById("xMark").addEventListener('click',()=>{
  document.getElementById("modal_head").style.display = "none";
})

//code which will be executed immediately afetr DOM content creation is completed and the page is loaded in the browser
window
  .addEventListener(
    'DOMContentLoaded',
    function () {
      getLoans()
    }
  )

  function search() {
      var input, filter, table, tr, td, i, txtValue;
      input = document.getElementById("myInput");
      filter = input.value.toUpperCase();
      table = document.getElementById("myTable");
      tr = table.getElementsByTagName("tr");
  
      for (i = 1; i < tr.length; i++) {
          td = tr[i].getElementsByTagName("td")
          var found = false
          for (j = 0; j < td.length - 1; j++) {
              if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                  found = true;
              }
          }
          if (found) {
              tr[i].style.display = "";
              found = false;
          } else {
              tr[i].style.display = "none";
          }
      }
  }







