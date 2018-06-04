var array = new Object();
var count=1;
function addDataIntoTable()
{   
    var taskTitle = document.getElementById('addTaskTitle').value;
    var taskDescription = document.getElementById('addTaskDescription').value;
    var taskAssignee = document.getElementById('addTaskAssignee').value;
    var taskEstimatedHours = document.getElementById('addEstimatedHours').value;
    var taskStartDate = document.getElementById('addStartDate').value;
    var taskEndDate = document.getElementById('addEndDate').value;
    
    array[count] = new Object();
    array[count].tasktitle = taskTitle;
    array[count].taskDescription = taskDescription ;
    array[count].taskAssignee = taskAssignee;
    array[count].taskEstimatedHours = taskEstimatedHours;
    array[count].taskStartDate = taskStartDate; 
    array[count].taskEndDate = taskEndDate;
    var jsonstring=JSON.stringify(array[count]);
    console.log(jsonstring);
  
    var bdy = document.getElementsByTagName('body')[0];
	var tbl = document.getElementById('addDetails');
		   
	var tr = document.createElement('tr');
    var td = document.createElement('td');
	var text = document.createTextNode(taskTitle);
	td.appendChild(text);
    tr.appendChild(td);
    tr.setAttribute("id",count);
    
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
    input.setAttribute("type","radio");
    input.setAttribute("name","taskDetails");
    td.appendChild(input);
    tr.appendChild(td);
    tbl.appendChild(tr);
    bdy.appendChild(tbl);
    count++;
}