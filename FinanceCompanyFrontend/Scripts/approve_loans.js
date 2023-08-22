//function to load data in Table body element
//"applicationNumber":1,"applyDate":"2020-09-04Z","customerId":1,"loanAmount":1000.0,"loanEmi":5670.0,"loanId":1,"loanStatus":"pending","loanTenure":5
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

window.addEventListener('DOMContentLoaded', () => {
    document.getElementById("download_button").addEventListener('click', () => {
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
})

function approve_loan(application_number) {
    status_ = "approved"

    const req = new XMLHttpRequest();
    req.open('POST', `http://localhost:8080/FinanceCompanyBackend/rest/manager/status/${application_number}/${status_}`, true);
    req.send();

    alert(`Loan Application Number : ${application_number} is Approved!`)
}

function reject_loan(application_number) {
    status_ = "rejected"

    const req = new XMLHttpRequest();
    req.open('POST', `http://localhost:8080/FinanceCompanyBackend/rest/manager/status/${application_number}/${status_}`, true);
    req.send();

    alert(`Loan Application Number : ${application_number} is Rejected!`)
}

const buttonPressed = e => {
    applicationNumber = e.target.id
    if (e.target.textContent == "Approve" && confirm(`Do you want to Approve Application Number : ${applicationNumber} ?`)) {
        approve_loan(applicationNumber)
    } else if (e.target.textContent == "Reject" && confirm(`Do you want to Reject Application Number : ${applicationNumber} ?`)) {
        reject_loan(applicationNumber)
    }
    location.reload()
}

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

                // const imgEle = document.createElement('img')
                // imgEle.src = c.products
                // imgEle.alt = "NA"
                // imgEle.style.width = "200px";
                // imgEle.style.margin = "2px";
                const parent = document.createElement("td")
                const view_doc = document.createElement("button")
                parent.appendChild(view_doc)
                view_doc.textContent = "Documents"
                view_doc.classList.add("btn")
                view_doc.classList.add("btn-outline-secondary")
                view_doc.setAttribute("type", "button")

                view_doc.addEventListener("click", () => {
                    display_modal(c.documents)
                })

                const big_parent = document.createElement("td")
                const approve_btn = document.createElement("button")
                const reject_btn = document.createElement("button")

                approve_btn.textContent = "Approve"
                approve_btn.classList.add("btn")
                approve_btn.classList.add("btn-success")
                approve_btn.setAttribute("id", c.applicationNumber)
                approve_btn.addEventListener("click", buttonPressed);

                reject_btn.textContent = "Reject"
                reject_btn.classList.add("btn")
                reject_btn.classList.add("btn-danger")
                reject_btn.setAttribute("id", c.applicationNumber)
                reject_btn.addEventListener("click", buttonPressed);


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
                row.appendChild(parent)

                if (c.loanStatus == "pending") {
                    div_ele = document.createElement("div")
                    div_ele.classList.add("btn-group")
                    div_ele.appendChild(approve_btn)
                    div_ele.appendChild(reject_btn)
                    big_parent.appendChild(div_ele)
                    row.appendChild(big_parent)
                }else{
                    div_ele = document.createElement("div")
                    div_ele.classList.add("btn-group")
                    div_ele.appendChild(approve_btn)
                    div_ele.appendChild(reject_btn)
                    big_parent.appendChild(div_ele)
                    approve_btn.classList.add("disabled")
                    reject_btn.classList.add("disabled")
                    row.appendChild(big_parent)
                }

                row.classList.add("table-light")
                row.setAttribute("id", c.applicationNumber)
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


window.addEventListener('DOMContentLoaded',()=>{
    document.getElementById("xMark").addEventListener('click',()=>{
        document.getElementById("modal_head").style.display = "none";
      })
})
window.addEventListener('DOMContentLoaded',()=>{
    document.getElementById("close").addEventListener('click',()=>{
        document.getElementById("modal_head").style.display = "none";
      })
      
})
//code which will be executed immediately afetr DOM content creation is completed and the page is loaded in the browser
window.addEventListener(
        'DOMContentLoaded',
        function () {
            getApplications()
        },
    )










