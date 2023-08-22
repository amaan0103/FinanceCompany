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
    if (interest)
        document.getElementById("interest").value = "Interest Rate : " + interest + " %"
    document.getElementById('doa').valueAsDate = new Date();
}

function calculate_emi() {
    var loanAmount = document.getElementById("loan_amount").value;
    var interestRate = get_interest(document.getElementById("loan_type").value)
    var loanDuration = document.getElementById("loan_tenure").value;
     
   var interestPerYear = (loanAmount * interestRate)/100; 
   var monthlyInterest = interestPerYear/12;
    
    var emi = monthlyInterest + (loanAmount/loanDuration);
    document.getElementById("loan_emi").value = emi
}

async function convertToBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = async () => {
            const base64String = reader.result;
            resolve(base64String);
        };
        reader.onerror = (error) => {
            reject(error);
        };
        reader.readAsDataURL(file);
    });
}


async function upload_loan_data() {

    var customer_id = this.document.getElementById("customer_id").value
    var loan_type = this.document.getElementById("loan_type").value
    var loan_id = { "personal": 1, "education": 2, "car": 3, "home": 4 }[loan_type]
    var loan_tenure = this.document.getElementById("loan_tenure").value
    var loan_amount = this.document.getElementById("loan_amount").value
    var loan_emi = this.document.getElementById("loan_emi").value

    const imgElement = this.document.getElementById('document')
    const file = imgElement.files[0]
    const document = await convertToBase64(file)

    const data = {
        "customerId": customer_id,
        "loanAmount": loan_amount,
        "loanId": loan_id,
        "loanStatus": "pending",
        "loanTenure": loan_tenure,
        "loanEmi": loan_emi,
        "documents": document
    }

    const req = new XMLHttpRequest()
    req.onreadystatechange = async () => {
        if (req.status === 200 && req.readyState === 4) {
            console.log(JSON.parse(req.responseText))
        }
        else {
            console.log(req.status)
        }
    }
    req.open('POST', 'http://localhost:8080/FinanceCompanyBackend/rest/customer/submitApplication', true)
    req.setRequestHeader("Content-Type", "application/json")
    req.send(JSON.stringify(data))

    alert("Loan Succesfully Applied!")
}
