angular.module('myApp').controller('UserprofileController', ['$scope', '$location', '$http', '$sessionStorage', function ($scope, $location, $http, $sessionStorage) {

    $scope.logged = sessionStorage.loggedIn;
    $scope.addToDb = function (isvalid) {

        $('#profileProgress').show();
        $('#namesAlert').hide();
        $('#unRegisteredAlert').hide();

        $scope.userData = {
            'empid': $scope.employeId, 'fstname': $scope.firstname,
            'lastname': $scope.lastname, 'emailid': $scope.usremail, 'uName': $scope.username,
            'uPwd': $scope.thePwd, 'phnNo': $scope.phone
        };


        $http({
            method: 'post',
            url: "/Ask_Portal/ask/addUser",

            data: $scope.userData
        })
            .then(function successCallback(response) {
                $scope.profileResponseMsg = response.data.responseMessage;

                if (angular.equals($scope.profileResponseMsg, "Registered Successfully! Please Login")) {

                    $('#profileProgress').hide();

                    swal({
                        title: "Success!",
                        text: "Registered Successfully",
                        type: "success",
                        timer: 2000,
                        confirmButtonText: "LOGIN"
                    }, function () {
                        window.location.href = "/";

                    });

                } else if (angular.equals($scope.profileResponseMsg, "UserName Already Exist! Please Try With Another")) {

                    $('#profileProgress').hide();
                    $('#unRegisteredAlert').show();


                } else if (angular.equals($scope.profileResponseMsg, "EmployeeID Already Registered! Please Login")) {

                    $('#profileProgress').hide();
                    $('#unRegisteredAlert').show();
                }
                else if (angular.equals($scope.profileResponseMsg, "This email already registered! Please try another email")) {

                    $('#profileProgress').hide();
                    $('#unRegisteredAlert').show();
                }
                else{
                    $('#profileProgress').hide();
                    $('#namesAlert').show();
                }
            }, function errorCallback(response) {
                console.log("Unable to to perform request");

            });
    };

}]);
