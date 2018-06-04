'use strict';

function LoginController(appService, $location) {
  console.log('In Login controller..');
  var _self = this;
  _self.submitLogin = function () {

    let User = new Object();
    User.userName = _self.userName;
    User.password = _self.password;
    appService.make({ endpoint: 'login/loginSuperAdmin', method: "POST", data: User })
      .then(function success(response) {
        if (response.data === 1) {
          $location.path('/selectOptionsForSuperAdmin');
          localStorage.setItem('superAdmin', true);
          swal({
            type: 'success',
            title: "Welcome..!!",
            text: User.userName,
            timer: 2000,
            showConfirmButton: false
          });
        }
        else {
          $location.path('/selectOptionsForAdmin');
          localStorage.setItem('admin', true);
          swal({
            type: 'success',
            title: "Welcome..!!",
            text: User.userName,
            timer: 2000,
            showConfirmButton: false
          });
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
  };
  _self.reset=function(){
    _self.userName='';
    _self.email='';
    _self.password='';
  };
  _self.forgotPassword = function () {

    let User = new Object();
    User.userName = _self.userName;
    User.email = _self.email;

    appService.make({ endpoint: 'login/forgotpassword', method: "POST", data: User })
      .then(function success(response) {
        _self.reset();
        swal({
          type: 'success',
          title: "Password sent to email ID",
          text: User.userName,
          timer: 2000,
          showConfirmButton: false
        });

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
  };
};

angular.module('loginModule')
  .component('loginComponent', {
    templateUrl: '../../views/login.html',
    controller: LoginController,
  });