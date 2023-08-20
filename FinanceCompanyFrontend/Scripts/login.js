function upload_data(params) {
    const email = document.getElementById("email").value;
    const role = document.getElementById("role").value;
    const password = document.getElementById("password").value;

    console.log(email, role, password);
    data = {
        "username": email,
        "password": password,
        "role": role
    }

    const req = new XMLHttpRequest()
    req.open('', 'http://localhost:8080/FinanceCompanyBackend/rest/auth/login', true);
    req.setRequestHeader("Content-Type", "application/json");

    // need to get response from post and redirect them respectively

    req.onload = () => {
        alert(JSON.parse(req.response));
    };

    req.onreadystatechange = () => {
        alert(JSON.parse(req.response));
    };

    req.send(JSON.stringify(data));
}