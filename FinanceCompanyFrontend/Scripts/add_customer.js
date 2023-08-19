function generate_customer_id() {
    return Math.floor(Math.random() * 9000000000) + 1000000000;
}

document.getElementById('btnUpload').addEventListener(
    'click', function () {

        const first_name = document.getElementById("first_name").value;
        const last_name = document.getElementById("last_name").value
        const name = first_name + " " + last_name
        const gender = document.getElementById("gender").value
        const mobile = document.getElementById("mobile").value
        const email = document.getElementById("email").value


        reader.addEventListener(
            'load',
            function () {
                const data = {
                    "customerId": generate_customer_id(),
                    "customerName": name,
                    "email": email,
                    "gender": gender,
                    "mobile": mobile
                }

                const req = new XMLHttpRequest()
                req.onreadystatechange = function () {
                    if (req.status === 200 && req.readyState === 4) {
                        console.log(JSON.parse(req.responseText))
                    }
                }
                req.open('POST', 'http://localhost:8080/FinanceCompanyBackend/rest/clerk/addCustomer', true)
                req.setRequestHeader("Content-Type", "application/json")
                req.send(JSON.stringify(data))
            }
        )
        alert("success")
    })