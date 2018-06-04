var array = [];
var count = 0;
var flag = -1;
var flagtrid = 'tr' + count;
var tobeDeletedRow;
function addDataIntoTable() {
    let flagValidation = 0;
    count = localStorage.getItem("count");
    array = JSON.parse(localStorage.getItem("array"));
    var taskTitle = $('#addTaskTitle').val();
    var taskDescription = $('#addTaskDescription').val();
    var taskAssignee = $('#addTaskAssignee').val();
    var taskEstimatedHours = $('#addEstimatedHours').val();
    var taskStartDate = $('#addStartDate').val();
    var taskEndDate = $('#addEndDate').val();
    if (taskTitle == "" || taskDescription == "" || taskAssignee == "" || taskEstimatedHours == "" || taskStartDate == "" || taskEndDate == "") {
        flagValidation = 1;
    }
    if (flagValidation == 0) {
        temp = new Object();
        temp.taskTitle = taskTitle;
        temp.taskDescription = taskDescription;
        temp.taskAssignee = taskAssignee;
        temp.taskEstimatedHours = taskEstimatedHours;
        temp.taskStartDate = taskStartDate;
        temp.taskEndDate = taskEndDate;
        
        array.unshift(temp);
        
        var radioValue = "tr" + count;
        radio = $("<input/>").attr({ type: 'radio', name: 'taskDetails', value: radioValue, onClick: 'selectedRowToDelete(tr' + count + ')' });
        tr = $('<tr/>').attr({ id: 'tr' + count });
        td = $('<td/>');
        tr.append('<td>' + temp.taskTitle + '</td>');
        tr.append('<td>' + temp.taskDescription + '</td>');
        tr.append('<td>' + temp.taskAssignee + '</td>');
        tr.append('<td>' + temp.taskEstimatedHours + '</td>');
        tr.append('<td>' + temp.taskStartDate + '</td>');
        tr.append('<td>' + temp.taskEndDate + '</td>');
        radio.appendTo(td);
        td.appendTo(tr);
        $("#addDetails").append(tr);
        count++;
        localStorage.setItem("count", count);
        localStorage.setItem("array", JSON.stringify(array));
    }else{
        alert('Please enter all fields');
    }
}
function editDataInTableAndRemoveItFromJson() {
    count = localStorage.getItem("count");
    array = JSON.parse(localStorage.getItem("array"));
    var radios = document.getElementsByName("taskDetails");
    var value;

    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            value = radios[i].value;
            break;
        }
    }
    if (i == radios.length) {
        alert("please select a radio button");
        $("#id02").toggle();
    }
    //alert("Radio Button " + (i) + " is checked."); 
    //console.log('tr' + i);
    $("#editTaskTitle").val(array[i].taskTitle);
    $("#editTaskDescription").val(array[i].taskDescription);
    $("#editTaskAssignee").val(array[i].taskAssignee);
    $("#editEstimatedHours").val(array[i].taskEstimatedHours);
    $("#editStartDate").val(array[i].taskStartDate);
    $("#editEndDate").val(array[i].taskEndDate);
    flagtrid = 'tr' + i;
    flag = i;
}
function selectedRowToDelete(rowId) {
    tobeDeletedRow = rowId;
}

function editData() {
    count = localStorage.getItem("count");
    array = JSON.parse(localStorage.getItem("array"));
    var taskTitle = document.getElementById('editTaskTitle').value;
    var taskDescription = document.getElementById('editTaskDescription').value;
    var taskAssignee = document.getElementById('editTaskAssignee').value;
    var taskEstimatedHours = document.getElementById('editEstimatedHours').value;
    var taskStartDate = document.getElementById('editStartDate').value;
    var taskEndDate = document.getElementById('editEndDate').value;

    array[flag].taskTitle = taskTitle;
    array[flag].taskDescription = taskDescription;
    array[flag].taskAssignee = taskAssignee;
    array[flag].taskEstimatedHours = taskEstimatedHours;
    array[flag].taskStartDate = taskStartDate;
    array[flag].taskEndDate = taskEndDate;

    var tr = document.getElementById(flagtrid);
    tr.childNodes[0].firstChild.nodeValue = taskTitle;
    tr.childNodes[1].firstChild.nodeValue = taskDescription;
    tr.childNodes[2].firstChild.nodeValue = taskAssignee;
    tr.childNodes[3].firstChild.nodeValue = taskEstimatedHours;
    tr.childNodes[4].firstChild.nodeValue = taskStartDate;
    tr.childNodes[5].firstChild.nodeValue = taskEndDate;
    localStorage.setItem("count", count);
    localStorage.setItem("array", JSON.stringify(array));
}
function loadData() {
    if (localStorage.getItem("count") == null || localStorage.getItem("count") == undefined) {
        var jsonString = '{"localArray":[ {"taskTitle":"abcd","taskDescription":"building a flyover","taskAssignee":"saikiran","taskEstimatedHours":"20","taskStartDate":"2018-01-01","taskEndDate":"2018-01-06"},{"taskTitle":"pqrs","taskDescription":"constructing a SEZ","taskAssignee":"kartheek","taskEstimatedHours":"20","taskStartDate":"2018-01-01","taskEndDate":"2018-01-06"},{"taskTitle":"xyzz","taskDescription":"xyzz","taskAssignee":"patlalo","taskEstimatedHours":"20","taskStartDate":"2018-01-01","taskEndDate":"2018-01-06"}] }';
        var obj = JSON.parse(jsonString);
        var td, tr, radio;
        for (i = 0; i < obj.localArray.length; i++) {

            array[count] = new Object();
            array[count].taskTitle = obj.localArray[i].taskTitle;
            array[count].taskDescription = obj.localArray[i].taskDescription;
            array[count].taskAssignee = obj.localArray[i].taskAssignee;
            array[count].taskEstimatedHours = obj.localArray[i].taskEstimatedHours;
            array[count].taskStartDate = obj.localArray[i].taskStartDate;
            array[count].taskEndDate = obj.localArray[i].taskEndDate;


            var radioValue = "tr" + count;
            radio = $("<input/>").attr({ type: 'radio', name: 'taskDetails', value: radioValue, onClick: 'selectedRowToDelete(tr' + count + ')' });
            tr = $('<tr/>').attr({ id: 'tr' + count });
            td = $('<td/>');
            tr.append('<td>' + array[count].taskTitle + '</td>');
            tr.append('<td>' + array[count].taskDescription + '</td>');
            tr.append('<td>' + array[count].taskAssignee + '</td>');
            tr.append('<td>' + array[count].taskEstimatedHours + '</td>');
            tr.append('<td>' + array[count].taskStartDate + '</td>');
            tr.append('<td>' + array[count].taskEndDate + '</td>');
            radio.appendTo(td);
            td.appendTo(tr);
            $("#addDetails").append(tr);
            count++;
        }
        localStorage.setItem("count", count);
        localStorage.setItem("array", JSON.stringify(array));
    } else {
        var obj = JSON.parse(localStorage.getItem("array"));
        var bdy = document.getElementById('weekEnd');
        var tbl = document.getElementById('addDetails');
        for (var i = 0; i < obj.length; i++) {
            array[count] = new Object();
            array[count].taskTitle = obj[i].taskTitle;
            array[count].taskDescription = obj[i].taskDescription;
            array[count].taskAssignee = obj[i].taskAssignee;
            array[count].taskEstimatedHours = obj[i].taskEstimatedHours;
            array[count].taskStartDate = obj[i].taskStartDate;
            array[count].taskEndDate = obj[i].taskEndDate;
            var tr = document.createElement('tr');
            tr.setAttribute('id', 'tr' + count);
            var td = document.createElement('td');

            var text = obj[i].taskTitle;

            text = document.createTextNode(text);
            td.appendChild(text);
            tr.appendChild(td);


            td = document.createElement('td');
            text = obj[i].taskDescription;
            text = document.createTextNode(text);
            td.appendChild(text);
            tr.appendChild(td);

            td = document.createElement('td');
            text = obj[i].taskAssignee;
            text = document.createTextNode(text);
            td.appendChild(text);
            tr.appendChild(td);

            td = document.createElement('td');
            text = obj[i].taskEstimatedHours;
            text = document.createTextNode(text);
            td.appendChild(text);
            tr.appendChild(td);

            td = document.createElement('td');
            text = obj[i].taskStartDate;
            text = document.createTextNode(text);
            td.appendChild(text);
            tr.appendChild(td);

            td = document.createElement('td');
            text = obj[i].taskEndDate;
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
        localStorage.setItem("count", count);
        localStorage.setItem("array", JSON.stringify(array));
        count = localStorage.getItem("count");
        array = JSON.parse(localStorage.getItem("array"));
    }
    $("#id01").toggle();
    $("#id02").toggle();
    $("#id03").toggle();
    $("#id04").toggle();
}
function alphaOnly(event) {
    var key = event.keyCode;
    return ((key >= 65 && key <= 90) || (key >= 95 && key <= 122) || key == 8 || key == 32);
}