var data = [{
    "message":"record found",
    "responseData":{
        "applicationNumber":1,
        "customerId":1,
        "loanType":"Car Loan",
        "loanAmount":1200399.74,
        "approvalStatus":false,
        "applyDate":"03/09/2023",
        "loanTenure":5,
        "loanEmi":10000
    },
    "statusCode":200
},
{
    "message":"record found",
    "responseData":{
        "applicationNumber":2,
        "customerId":2,
        "loanType":"Education Loan",
        "loanAmount":2000399.74,
        "approvalStatus":false,
        "applyDate":"01/11/2023",
        "loanTenure":4,
        "loanEmi":50000
    },
    "statusCode":200
}]

// get JSON array of all loan applications
function get_all_loans(){
    return data
}

function display_loans_menu() {

    data = get_all_loans()

    const selectElement = document.getElementById('loans')

    data.forEach(element => {
        const option = document.createElement('option')
        option.text = element.responseData.applicationNumber + " - " + element.responseData.loanType
        option.value = element.responseData.applicationNumber
        selectElement.options.add(option)
    })

}

// call function which retrives loan application by application number
function get_loan_by_application_number(application_number) {
    var response
    data.forEach(element => {
        if (element.responseData.applicationNumber == application_number) {
            response = element.responseData
        }
    });
    return response
}

function display_loan() {

    const selectElement = document.getElementById('loans')
    const allOptions = selectElement.options
    const index = selectElement.selectedIndex
    const selectedOption = allOptions[index]

    var application_number = selectedOption.value
    if(application_number == -1)
        return

    loan = get_loan_by_application_number(application_number)

    document.getElementById("customer_id").innerText = "Customer ID : " + loan.customerId;
    document.getElementById("loan_type").innerText = "Loan Type : " + loan.loanType;
    document.getElementById("loan_amount").innerText = "Loan Amount : " + loan.loanAmount;

}
