angular.module("myApp")
  .directive('sideNavDirective', function () {
    return {
      templateUrl: '../views/dashBoard/sidenav.html'
    };
  })
  .controller('dashBoardController', ['$scope', '$location', '$sessionStorage', function ($scope, $location, $sessionStorage) {

    $scope.uname = sessionStorage.userName;
   
    $scope.sme = sessionStorage.smeCheck;
    $scope.admin = sessionStorage.adminCheck;
    $scope.uFullName = sessionStorage.userFullName;
   

    $scope.logOut = function () {

      delete sessionStorage.userName;
      delete sessionStorage.userRole;
      delete sessionStorage.smeCheck;
      delete sessionStorage.loggedIn;
      delete sessionStorage.empId;
      delete sessionStorage.adminCheck;
      delete sessionStorage.roleName;
      delete sessionStorage.userFullName;
      $location.path('/');
    
      console.log($sessionStorage.userName);

    }
  }]);