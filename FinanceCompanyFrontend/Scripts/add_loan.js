function get_interest(loan_type) {
    var interest;
    switch (loan_type) {
        case "car":
            interest = 9;
            break;
        case "education":
            interest = 12.2;
            break;
        case "personal":
            interest = 10.25;
            break;
        case "home":
            interest = 8.5;
            break;
        default:
            break;
    }
    return interest;
}

function set_interest() {
    var value = document.getElementById("loan_type").value
    var interest = get_interest(value)
    if(interest)
        document.getElementById("interest").value = "Interest Rate : " + interest + " %"
    document.getElementById('doa').valueAsDate = new Date();
}

function calculate_emi() {
    var value = document.getElementById("loan_type").value
    var interest = get_interest(value)
    var tenure = n = document.getElementById("loan_tenure").value
    var amount = P = document.getElementById("loan_amount").value
    var emi = P * r * (Math.pow((1 + r),n) / (Math.pow((1 + r),n-1)))
    document.getElementById("loan_emi").value = emi
}

function upload_loan_data() {
    
    var customer_id = this.document.getElementById("customer_id").value
    var loan_type = this.document.getElementById("loan_type").value
    var interest = get_interest(loan_type)
    var doa = this.document.getElementById("doa").value
    var loan_tenure = this.document.getElementById("loan_tenure").value
    var loan_amount = this.document.getElementById("loan_amount").value
    var loan_emi = this.document.getElementById("loan_emi").value
    var document = this.document.getElementById("document").value

    console.log(customer_id, loan_type, interest, doa, loan_tenure, loan_amount, loan_emi, document)
    data = {
        "applicationNumber" : 11,
        "customerId" : customer_id,
        "loanAmount" : loan_amount,
        "loanId" : 2,
        "loanStatus" : "pending",
        "loanTenure" : loan_tenure,
        "loanEmi" : loan_emi
    }

    const req = new XMLHttpRequest();
    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            window.alert(req.responseText)
        }
    }

    req.open('POST', 'http://localhost:8080/FinanceCompanyBackend/rest/clerk/submitApplication',true);
    req.setRequestHeader("Content-Type","application/json")
    req.send(JSON.stringify(data));
    
    alert("Loan Applied")

}

document.getElementById('submitButton').addEventListener(
    'click', function () {
        const imgElement = document.getElementById('document')
        const file = imgElement.files[0]
        const reader = new FileReader()
        reader.addEventListener(
            'load',
            function () {
                console.log(reader.result)
                const data = {
                    productId: Number(document.getElementById('txtId').value),
                    productName: document.getElementById('txtName').value,
                    productImage: reader.result
                }
                const req = new XMLHttpRequest()
                req.onreadystatechange = function () {
                    if (req.status === 200 && req.readyState === 4) {
                        console.log(JSON.parse(req.responseText))
                    }
                }
                // update endpoint and all
                req.open('POST', 'http://localhost:8080/PmsApp/rest/products/upload', true)
                req.setRequestHeader("Content-Type", "application/json")
                req.send(JSON.stringify(data))
            }
        )
        reader.readAsDataURL(file)
    })
