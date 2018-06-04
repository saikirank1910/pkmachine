angular.module("myApp").controller('EditProfileController', ['$scope', '$sessionStorage', '$http', function ($scope, $sessionStorage, $http) {

    $scope.logged = sessionStorage.loggedIn;
    var empId = sessionStorage.empId;

    $scope.model = {
        isDisabled: true
    };

    $http({
        method: 'GET',
        url: '/Ask_Portal/ask/editUser/' + empId,

    }).then(function successCallback(response) {


        $scope.employeId = response.data.empid;
        $scope.firstname = response.data.fstname;
        $scope.lastname = response.data.lastname;
        $scope.usremail = response.data.emailid;
        $scope.thePwd = response.data.uPwd;
        $scope.phone = response.data.phnNo;
        $scope.username = response.data.uName;
        $scope.reenter = response.data.uPwd;



    }, function errorCallback(response) {
        console.log("Unable to perform get request");
    });
    $scope.addToDb = function () {
        $('#profileProgressSubmit').show();
        $('#updateAlert').hide();
        $('#namesAlert').hide();
        $scope.userData = {
            'empid': $scope.employeId, 'fstname': $scope.firstname,
            'lastname': $scope.lastname, 'emailid': $scope.usremail, 'uName': $scope.username,
            'uPwd': $scope.thePwd, 'phnNo': $scope.phone
        };

        $http({
            method: 'post',
            url: "/Ask_Portal/ask/updateUser",
            data: $scope.userData
        })
            .then(function successCallback(response) {

                console.log(response.data);
                $scope.profileResponseMesg=response.data.responseMessage;
                if (angular.equals($scope.profileResponseMesg, "User Credentials Successfully updated")) {
                $('#profileProgressSubmit').hide();
                $('#updateAlert').toggle();
                }else{
                    $('#profileProgressSubmit').hide();
                    $('#namesAlert').show();
                    
                }

            }, function errorCallback(response) {
                console.log("fail");

            });
    };

}]);