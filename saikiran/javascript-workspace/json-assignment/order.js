function createTable(){
    var text= '{"testArray":[ {  "Order": {  "Number":"SO43659", "Date":"2011-05-31T00:00:00" }, "AccountNumber":"AW29825", "Item": { "Price":2024.9940, "Quantity":1 } },   {  "Order": { "Number":"SO43661", "Date":"2011-06-01T00:00:00" },   "AccountNumber":"AW73565", "Item": { "Price":2024.9940, "Quantity":3 }  }  ]  }';
    obj = JSON.parse(text);
    var bdy = document.getElementById('boddy');
    var tbl = document.getElementById('tablee');
    for(var i=0;i<2;i++){		   
        var tr = document.createElement('tr');
            var td = document.createElement('td');
            var text = obj.testArray[i].Order.Number;
            console.log(text);
            text = document.createTextNode(text);
            td.appendChild(text);
            tr.appendChild(td);
            td = document.createElement('td');
            text = obj.testArray[i].Item.Price;
            console.log(text);
            text = document.createTextNode(text);
            td.appendChild(text);
            tr.appendChild(td);
        tbl.appendChild(tr);
    }
    bdy.appendChild(tbl);    
}