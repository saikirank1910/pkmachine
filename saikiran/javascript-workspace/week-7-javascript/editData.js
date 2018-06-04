function editDataInTable()
{
    var radios = document.getElementsByName("taskDetails");
    var value;
    console.log(radios.length);
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            value = radios[i].value;  
            break;    
        }
    }
    alert("Radio Button " + (i+1) + " is checked.");
    
}
function deleterow()
{
    var radios = document.getElementsByName("taskDetails");
    var value;
   
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            value = radios[i].value;      
            break;
        }
    }
    
    var indexOfRow=0;
    indexOfRow = i+1;
    array.splice(indexOfRow, 1);
}
