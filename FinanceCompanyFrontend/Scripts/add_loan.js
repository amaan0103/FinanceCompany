function set_interest() {
    var value = document.getElementById("loan_type").value
    var interest;
    switch (value) {
        case "car":
            interest = 5;
            break;
        case "education":
            interest = 10;
            break;
        case "personal":
            interest = 15;
            break;
        case "others":
            interest = 20;
            break;
        default:
            break;
    }
    if(interest)
        document.getElementById("interest").value = "Interest Rate : " + interest + " %"
    document.getElementById('doa').valueAsDate = new Date();
}

function calculate_emi() {
    var value = document.getElementById("loan_type").value
    var interest;
    switch (value) {
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
    r = interest
    var tenure = n = document.getElementById("loan_tenure").value
    var amount = P = document.getElementById("loan_amount").value
    var emi = P * r * (Math.pow((1 + r),n) / (Math.pow((1 + r),n-1)))
    document.getElementById("loan_emi").value = emi
}