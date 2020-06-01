var user;
var localStorage;

window.onload=function(){
    console.log("in load");
    
    this.getLogin();   
    //document.getElementById("trmsLogin").addEventListener("click",getLogin,false);  
}


function getLogin(){
    console.log("in getLogin");
    
//    let uName=document.getElementById("username").value;
//    let uPassword=document.getElementById("password").value;
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        console.log("in ORSC"+xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
            user = JSON.parse(xhr.responseText);
            console.log("in getLogin()");
            console.log(user);
            loadLogin(user);
        }
    }
    xhr.open("GET","http://localhost:8080/TRMS/login",true);
    xhr.send();
}


function loadLogin(user){
	
	var name = user.firstName + " " + user.lastName;
	var title = user.position;
	var accBalance = user.accBalance;
    localStorage.setItem("fullName", name);
    localStorage.setItem("userID",user.userID);
    localStorage.setItem("fName",user.firstName);
    localStorage.setItem("lName",user.lastName);
    localStorage.setItem("title",user.position);
    localStorage.setItem("accBalance",user.accBalance);
    
    console.log("in loadLogin()");
    console.log(user);
    
    document.getElementById("welcome").innerHTML=("Welcome "+ name);
    document.getElementById("title").innerHTML=("Position: "+ title);
    document.getElementById("accBalance").innerHTML=("Balance: $"+ accBalance);
}


function jsonBuilder(){
    var elements=document.getElementById("loginForm").elements;
    var obj={};
    for(var i=0; i<elements.length-1;i++){
        var item=elements.item(i);
        obj[item.name]=item.value;
        console.log(obj);
    }
    var json=JSON.stringify(obj);
    console.log(json);
    return json;
}