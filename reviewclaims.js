var er;

window.onload=function(){
    console.log("in load");
    this.getER();

}

function getER(){
    console.log("in getER");

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        console.log("in ORSC"+xhr.readyState);
        if(xhr.readyState==4 && xhr.status==200){
            er=JSON.parse(xhr.responseText);
            console.log(er);
            loadER(er);
        }
    }
    xhr.open("GET","http://localhost:8080/TRMS/review",true);
    xhr.send();
}

function loadER(er){
    var col = [];
    for (var c = 0; c < er.length; c++) {
        for (var key in er[c]) {
            if (col.indexOf(key) === -1) {
                col.push(key);
            }
        }
    }
    console.log(col);
    var table = document.createElement("table");

        // Create table header row using the extracted headers above.
        var tr = table.insertRow(-1);                   // table row.

        for (var h = 0; h < col.length; h++) {
            var th = document.createElement("th");      // table header.
            th.innerHTML = col[h];
            tr.appendChild(th);
        }

        // add json data to the table as rows.
        for (var i = 0; i < er.length; i++) {

            tr = table.insertRow(-1);
            
            for (var j = 0; j < col.length; j++) {
                var tabCell = tr.insertCell(-1);
                console.log(er[i][col[j]]);
                if(j==7){
                	var d = er[i][col[j]];
                	var date = new Date(d).toDateString();
                	console.log(date);
                	tabCell.innerHTML = date;
                }
                else {
                tabCell.innerHTML = er[i][col[j]];
                }
            }
        }

        // Now, add the newly created table with json data, to a container.
        var divShowData = document.getElementById('showData');
        divShowData.innerHTML = "";
        divShowData.appendChild(table);
}