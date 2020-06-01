window.onload=function(){
    console.log("in onload");
    document.getElementById("submitMe").addEventListener("click", getLogin, false);
    document.getElementById("submitMe").addEventListener("click", postLogin, false);
}

function getLogin(){
    console.log("in getLogin");
    let user = document.getElementById("usernameInput").value;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        console.log("in ORSC "+xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
            var un = JSON.parse(xhr.responseText);
            loadLogin(un);
        }
    }
    xhr.open("GET", "http://localhost:8080/MGCoTuition/login?user="+user, true);
    xhr.send();
}
function loadLogin(un){
    document.getElementById("user").innerHTML=un.user;
    document.getElementById("pass").innerHTML=un.pass;
}

function jsonBuilder(){
    var elements=document.getElementById("userLogin").elements;
    var obj={};
    for(var i=0; i<elements.length-1; i++){
        var item = elements.item(i);
        obj[item.name]=item.value;
        console.log(obj);
    }
    var json = JSON.stringify(obj);
    console.log(json);
    return json;
}
function postLogin(){
    console.log("in postLogin");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        console.log("in ORSC "+xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST", "http://localhost:8080/MGCoTuition/login", true);
    var payload = jsonBuilder();
    xhr.send(payload);
}