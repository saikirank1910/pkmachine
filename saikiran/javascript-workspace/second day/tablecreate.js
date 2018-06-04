function tableCreate() {
    var rows = Number(document.getElementById('rows').value);
	var columns = Number(document.getElementById('columns').value);
    var bdy = document.getElementsByTagName('body')[0];
    var tbl = document.createElement('table');
    var tblBody = document.createElement("tbody");
    tbl.setAttribute('border', '1');
    for (var i = 0; i < rows; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < columns; j++) {
            var td = document.createElement('td');
            var text = document.createTextNode(i+''+j);
            td.appendChild(text)
            tr.appendChild(td)
        }
        tblBody.appendChild(tr);
    }
    tbl.appendChild(tblBody);
    bdy.appendChild(tbl);
}