'use strict';

function LoginController(appService, $location) {
  var ctrl=this;
  ctrl.user=new Object();
  ctrl.authenticate=function(){
    appService.make({ endpoint: 'login/authenticate', method: "POST", data: ctrl.user })
    .then(function success(response) {
      if (response.data === 1) {
        $location.path('/registration');
      }
      else if(response.data === 2){
        $location.path('/search');
      }
    },
    function error(errors) {
      swal({
        type: 'error',
        title: "Oops!!!!",
        text: "Please Enter Valid Details.",
        timer: 2000,
        showConfirmButton: false
      });
    });
  }
};

angular.module('loginModule')
  .component('loginComponent', {
    templateUrl: '../../views/login.html',
    controller: LoginController,
  });