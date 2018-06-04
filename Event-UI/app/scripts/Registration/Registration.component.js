'use strict';

function registrationController(appService, $location, NgTableParams, $filter) {
  var ctrl = this;
  ctrl.event = new Object();
  ctrl.temp;
  ctrl.searchTrain;
  ctrl.regionDetails;
  ctrl.getSubDivision = new Object();
  ctrl.subDivision = [];
  ctrl.region = [];
  ctrl.getSubDivision.subDivisionName = undefined;
  ctrl.serviceUnit = [];
  ctrl.trains = [];
  ctrl.employee = [];
  ctrl.employeeDetails = [];
  ctrl.employeeSelected;
  ctrl.employeeToTable = [];
  ctrl.employeeToDelete;
  ctrl.regulation = [];
  ctrl.goToTab1 = function () {
    ctrl.current = 0;
  };
  ctrl.goToTab2 = function () {
    ctrl.current = 1;
  };
  ctrl.goToTab3 = function () {
    ctrl.current = 2;
  };
  ctrl.submitEvent = function () {
    console.log()
    if (ctrl.employeeToTable.length >= 1) {
      ctrl.event.subDivisionName = ctrl.getSubDivision.subDivisionName;
      ctrl.event.milePost = ctrl.regionDetails.milePost;
      ctrl.event.regionName = ctrl.regionDetails.regionName;
      ctrl.event.serviceUnitName = ctrl.regionDetails.serviceUnitName;

      appService.make({ endpoint: 'event/save', method: "POST", data: ctrl.event })
        .then(function success(response) {
          console.log(response.data);
          for(var i=0;i<ctrl.employeeToTable.length;i++){
            ctrl.employeeToTable[i].eventId=response.data;
          }
          appService.make({ endpoint: 'employee/saveEmployeeEventDetails', method: "POST", data: ctrl.employeeToTable})
          .then(function success(response) {
            alert('data added successfully');
          },
          function error(errors) {

          });
        },
        function error(errors) {

        });

     }
    else {
      alert('please select atleast one employee');
    }
  }
  ctrl.getTrainId=function(trainId){
      console.log(ctrl.trains[trainId].tId);
      ctrl.event.trainId=ctrl.trains[trainId].tId;
      ctrl.trainSection=ctrl.trains[trainId].trainSection;
      ctrl.trainId=ctrl.trains[trainId].trainId;
      ctrl.trainDate=ctrl.trains[trainId].trainDate;
  }
  ctrl.getRegion = function () {
    appService.make({ endpoint: 'subDivision/getMilePostAndRegion', method: "POST", data: ctrl.getSubDivision })
      .then(function success(response) {
        ctrl.regionDetails = response.data;
      },
      function error(errors) {

      });
  }
  ctrl.searchTrains = function () {
    appService.make({ endpoint: 'train/' + ctrl.searchTrain, method: "GET" })
      .then(function success(response) {
        ctrl.trains = response.data;
        ctrl.tableData = new NgTableParams({ count: 5 }, { dataset: ctrl.trains });

      },
      function error(errors) {

      });
  }
  ctrl.addEmployeeToTable = function () {
    if (ctrl.employeeToTable.length < 3) {
      for (var i = 0; i < ctrl.employeeDetails.length; i++) {
        if (ctrl.employeeSelected == ctrl.employeeDetails[i].employeeId) {
          ctrl.employeeToTable[ctrl.employeeToTable.length] = ctrl.employeeDetails[i];
          for (var j = 0; j < ctrl.employee.length; j++) {
            if (ctrl.employee[j] == ctrl.employeeSelected) {
              ctrl.employee.splice(j, 1);
            }
          }
          break;
        }
      }
    }
    else {
      alert('you cant add more than three members');
    }
  }
  ctrl.deleteEmployee = function (temp) {
    for (var i = 0; i < ctrl.employeeToTable.length; i++) {
      if (ctrl.employeeToTable[i].employeeId == temp) {
        ctrl.employeeToTable.splice(i, 1);
        ctrl.employee.push(temp);
        break;
      }
    }
  }
  ctrl.reset = function () {
    appService.make({ endpoint: 'subDivision/getSubDivisionNames', method: "GET" })
      .then(function success(response) {
        let subDivisions = response.data;
        for (var i = 0; i < subDivisions.length; i++) {
          ctrl.subDivision[i] = subDivisions[i].subDivisionName;
        }
      },
      function error(errors) {

      });
    appService.make({ endpoint: 'region/getAllRegions', method: "GET" })
      .then(function success(response) {
        var regions = response.data;
        for (var i = 0; i < regions.length; i++) {
          ctrl.region[i] = regions[i].regionName;
        }
      },
      function error(errors) {

      });
    appService.make({ endpoint: 'serviceUnit/getAllServiceUnits', method: "GET" })
      .then(function success(response) {
        var serviceUnit = response.data;
        for (var i = 0; i < serviceUnit.length; i++) {
          ctrl.serviceUnit[i] = serviceUnit[i].serviceUnitName;
        }
      },
      function error(errors) {

      });
    appService.make({ endpoint: 'employee/getAllEmployees', method: "GET" })
      .then(function success(response) {
        ctrl.employeeDetails = response.data;
        var temp = response.data;
        for (var i = 0; i < temp.length; i++) {
          ctrl.employee[i] = temp[i].employeeId;
        }
      },
      function error(errors) {

      });
    appService.make({ endpoint: 'regulation/getAllRegulations', method: "GET" })
      .then(function success(response) {
        var temp = response.data;
        for (var i = 0; i < temp.length; i++) {
          ctrl.regulation[i] = temp[i].regulationName;
        }
      },
      function error(errors) {

      });

  }
  ctrl.reset();
}

angular.module('registrationModule')
  .component('registrationComponent', {
    templateUrl: '../../views/Registration.html',
    controller: registrationController,
  })