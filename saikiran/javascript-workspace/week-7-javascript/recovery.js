var array = [];
var count=0;
var falg=-1;
function addDataIntoTable()
{   
    var taskTitle = document.getElementById('addTaskTitle').value;
    var taskDescription = document.getElementById('addTaskDescription').value;
    var taskAssignee = document.getElementById('addTaskAssignee').value;
    var taskEstimatedHours = document.getElementById('addEstimatedHours').value;
    var taskStartDate = document.getElementById('addStartDate').value;
    var taskEndDate = document.getElementById('addEndDate').value;
    
    array[count] = new Object();
    array[count].taskTitle = taskTitle;
    array[count].taskDescription = taskDescription ;
    array[count].taskAssignee = taskAssignee;
    array[count].taskEstimatedHours = taskEstimatedHours;
    array[count].taskStartDate = taskStartDate; 
    array[count].taskEndDate = taskEndDate;
    var text = JSON.stringify(array[count]);
    // console.log(text);
    var bdy = document.getElementsByTagName('body')[0];
	var tbl = document.getElementById('addDetails');
		   
	var tr = document.createElement('tr');
    var td = document.createElement('td');
	var text = document.createTextNode(taskTitle);
	td.appendChild(text);
    tr.appendChild(td);
    tr.setAttribute('id', 'tr' + count);
    
    td = document.createElement('td');
    text = document.createTextNode(taskDescription);
    td.appendChild(text);
    tr.appendChild(td);

    td = document.createElement('td');
    text = document.createTextNode(taskAssignee);
    td.appendChild(text);
    tr.appendChild(td);

    td = document.createElement('td');
    text = document.createTextNode(taskEstimatedHours);
    td.appendChild(text);
    tr.appendChild(td);

    td = document.createElement('td');
    text = document.createTextNode(taskStartDate);
    td.appendChild(text);
    tr.appendChild(td);

    td = document.createElement('td');
    text = document.createTextNode(taskEndDate);
    td.appendChild(text);
    tr.appendChild(td);

    td = document.createElement('td');
    input = document.createElement('input');
    input.setAttribute("type", "radio");
    input.setAttribute("name", "taskDetails");
    var radioValue = "tr" + count;
    input.setAttribute("value", radioValue);
    input.setAttribute("onClick", "selectedRowToDelete(tr" + count + ")");
    td.appendChild(input);
    tr.appendChild(td);
   
    tbl.appendChild(tr);
    bdy.appendChild(tbl);
    count++;
}
function editDataInTableAndRemoveItFromJson()
{
    var radios = document.getElementsByName("taskDetails");
    var value;
   
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            value = radios[i].value;  
            break;    
        }
    }
    //alert("Radio Button " + (i) + " is checked."); 
    console.log(i);
    document.getElementById("editTaskTitle").value = array[i].taskTitle;
   flag=i;
}
var tobeDeletedRow;

function selectedRowToDelete(rowId) {
    tobeDeletedRow = rowId;
}

function deletetask() {
    var row = document.getElementById(tobeDeletedRow.id);
    row.parentNode.removeChild(row);
    
    var radios = document.getElementsByName("taskDetails");
    var value;
   
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            value = radios[i].value;      
            break;
        }
    }
    //alert("Radio Button " + (i) + " is checked.");
   
    if (i > -1) {
        array.splice(i-1, 1);
    }
}
function editData(){
    var taskTitle = document.getElementById('editTaskTitle').value;
    var taskDescription = document.getElementById('editTaskDescription').value;
    var taskAssignee = document.getElementById('editTaskAssignee').value;
    var taskEstimatedHours = document.getElementById('editEstimatedHours').value;
    var taskStartDate = document.getElementById('editStartDate').value;
    var taskEndDate = document.getElementById('editEndDate').value;
    
    array[flag].taskTitle = taskTitle;
    array[flag].taskDescription = taskDescription ;
    array[flag].taskAssignee = taskAssignee;
    array[flag].taskEstimatedHours = taskEstimatedHours;
    array[flag].taskStartDate = taskStartDate; 
    array[flag].taskEndDate = taskEndDate;
  
    

    tbl.appendChild(tr);
    bdy.appendChild(tbl);
}
 function loadData(){
    var jsonString='{"localArray":[ {"taskTitle":"abcd","taskDescription":"building a flyover","taskAssignee":"saikiran","taskEstimatedHours":"20","taskStartDate":"2018-01-01","taskEndDate":"2018-01-06"},{"taskTitle":"pqrs","taskDescription":"constructing a SEZ","taskAssignee":"kartheek","taskEstimatedHours":"20","taskStartDate":"2018-01-01","taskEndDate":"2018-01-06"},{"taskTitle":"xyzz","taskDescription":"xyzz","taskAssignee":"patlalo","taskEstimatedHours":"20","taskStartDate":"2018-01-01","taskEndDate":"2018-01-06"}] }';
    var obj = JSON.parse(jsonString);
    var bdy = document.getElementById('weekEnd');
    var tbl = document.getElementById('addDetails');
    for(var i=0;i<3;i++){	
        array[count] = new Object();
        array[count].taskTitle = obj.localArray[i].taskTitle;
        array[count].taskDescription =  obj.localArray[i].taskDescription;
        array[count].taskAssignee =  obj.localArray[i].taskAssignee;
        array[count].taskEstimatedHours =  obj.localArray[i].taskEstimatedHours;
        array[count].taskStartDate =  obj.localArray[i].taskStartDate; 
        array[count].taskEndDate =  obj.localArray[i].taskEndDate;
        var tr = document.createElement('tr');
        tr.setAttribute('id', 'tr' + count);
        var td = document.createElement('td');
            
        var text = obj.localArray[i].taskTitle;
       
        text = document.createTextNode(text);
        td.appendChild(text);
        tr.appendChild(td);
        
        
        td = document.createElement('td');
        text = obj.localArray[i].taskDescription;
        text = document.createTextNode(text);
        td.appendChild(text);
        tr.appendChild(td);

        td = document.createElement('td');
        text = obj.localArray[i].taskAssignee;
        text = document.createTextNode(text);
        td.appendChild(text);
        tr.appendChild(td);

        td = document.createElement('td');
        text = obj.localArray[i].taskEstimatedHours;
        text = document.createTextNode(text);
        td.appendChild(text);
        tr.appendChild(td);

        td = document.createElement('td');
        text = obj.localArray[i].taskStartDate;
        text = document.createTextNode(text);
        td.appendChild(text);
        tr.appendChild(td);

        td = document.createElement('td');
        text = obj.localArray[i].taskEndDate;
        text = document.createTextNode(text);
        td.appendChild(text);
        tr.appendChild(td);
        
        td = document.createElement('td');
        input = document.createElement('input');
        input.setAttribute("type", "radio");
        input.setAttribute("name", "taskDetails");
        var radioValue = "tr" + count;
        input.setAttribute("value", radioValue);
        input.setAttribute("onClick", "selectedRowToDelete(tr" + count + ")");
        td.appendChild(input);
        tr.appendChild(td);

        count++;   
        tbl.appendChild(tr);
    }
    bdy.appendChild(tbl);  

    var x = document.getElementById("id01");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }

    x = document.getElementById("id02");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}