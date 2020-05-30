window.onload = function(){
    getLoginInfo();
}
function getLoginInfo(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
            let login = JSON.parse(xhttp.responseText);
            console.log(login);
            setValues(login);
        }
    }
    xhttp.open("GET", 
                'http://localhost8080/TRMSProject/html/EmployeeJSON.do', true);
    xhttp.send();
}
function setValues(login){
    document.getElementById("name").innerText = "Name: " + (login.firstname +" " + login.lastname);
	document.getElementById("username").innerText = "Username: " +  login.username;
	document.getElementById("email").innerText = "Email: " + login.email;
	document.getElementById("balance").innerText = "Available Balance: " + login.balance;
	if(login.roleId == 1) {
		document.getElementById("role").innerText = "Head Benefit Coordinator";
	} else if(login.roleId == 2) {
		document.getElementById("role").innerText = "Benefit Coordinator";
	} else if(login.roleId == 3) {
		document.getElementById("role").innerText = "Department Head";
	} else if(login.roleId == 4) {
		document.getElementById("role").innerText = "Supervisor";
	} else {
		document.getElementById("role").innerText = "Employee";
	} 
}
function getReimbursement(form){
    document.getElementById()
}