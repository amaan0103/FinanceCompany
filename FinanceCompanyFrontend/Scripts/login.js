

async function fetchData(data, role) {
    try {
        const headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("Role", { 1: "MANAGER", 2: "CLERK", 3: "CUSTOMER" }[role])
        console.log("this: " + { 1: "MANAGER", 2: "CLERK", 3: "CUSTOMER" }[role])
        const requestOptions = {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(data)
        };
        const response = await fetch('http://localhost:8080/FinanceCompanyBackend/rest/auth/login', requestOptions);
        console.log("after fetch")
        if (!response.ok) {
            throw new Error('Request failed');
        }
        const result = await response.json();
        return result;
    } catch (error) {
        console.error('Error Occured', error);
        throw error;
    }
}
async function upload_data() {
    const email = document.getElementById("email").value;
    const role = document.getElementById("role").value;
    const password = document.getElementById("password").value;
    var data = {
        "username": email,
        "password": password,
    }
    var post_response = await fetchData(data, role)
    if (post_response.statusCode == 200) {
        alert("Welcome!")
        customer_id = post_response.responseData.customerId
        // role_id = post_response.responseData.role
        localStorage.clear()
        localStorage.setItem('customer_id', customer_id)
        console.log(role);

        if(role==1){
            location.replaace("Pages/Manager/manager_home.html")
        }else if(role==2){
            location.replace("Pages/Clerk/clerk_home.html")
        }else{
            location.replace("Pages/Customer/customer_home.html")
        }

    } 
    else {
        alert("InValid Username or Password")
    }
}
window.addEventListener('DOMContentLoaded', () => {
    document.getElementById("loginButton").addEventListener('click', async () => {
        upload_data()
    })
})
