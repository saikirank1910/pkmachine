var week14 = angular.module('week14', []);
week14.controller('data', function($scope, $http) {
  $scope.userDetails = {};
  $scope.showAddTable = false;
  $scope.showEditTable = false;
  $scope.radioButtonValue;
  $scope.list=[];
  $http({
    method: 'GET',
    url: 'http://localhost:1910/week-14/assignment/Person/getList'
  }).then(function mySuccess(response) {
    $scope.list = response.data;
    $scope.statuscode = response.status;
  }, function myError(response) {
    $scope.myRes = response.statusText;

  });
  $scope.getRadioButtonIndex = function(radioButtonValue) {
    $scope.userDetails = $scope.list[$scope.radioButtonValue];
  }

  $scope.addToTable = function() {
    $scope.list.push($scope.userDetails);
    // $scope.userDetails = {};
  };
  $scope.addToDb = function() {
    $http({
        method: 'POST',
        url: 'http://localhost:1910/week-14/assignment/Person/addPerson',
        data: $scope.userDetails
      })
      .then(function mySuccess(response) {


      }, function myError(response) {

      });
    $scope.userDetails = {};
  };
  $scope.editTable = function() {
    $http({
        method: 'POST',
        url: 'http://localhost:1910/week-14/assignment/Person/editPerson',
        data: $scope.userDetails
      })
      .then(function mySuccess(response) {


      }, function myError(response) {

      });
    $scope.list[$scope.radioButtonValue] = $scope.userDetails
    $scope.userDetails = {};
  }
  $scope.deletePerson = function() {
    $http({
        method: 'POST',
        url: 'http://localhost:1910/week-14/assignment/Person/delete',
        data: $scope.userDetails
      })
      .then(function mySuccess(response) {


      }, function myError(response) {

      });
    $scope.list.splice($scope.radioButtonValue, 1);
    $scope.userDetails = {};
  }
});



week14.filter('unique', function() {
  return function(collection, keyname) {
    var output = [],
      keys = [];
    angular.forEach(collection, function(item) {
      var key = item[keyname];
      if (keys.indexOf(key) === -1) {
        keys.push(key);
        output.push(item);
      }
    });
    return output;
  };
});