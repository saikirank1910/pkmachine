angular.module("myApp").controller("PostQueryController", ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $scope.role = sessionStorage.roleName;
    $scope.empId = sessionStorage.empId;
    $http({
        method: 'get',
        url: "Ask_Portal/ask/getCategories",
    }).then(function successCallback(response) {
        $scope.userData = response.data;

    }, function errorCallback(response) {
        console.log("Unable to perform get request");
    });
    $('#categorySelect').on('change', function () {
        if ($('#categorySelect :selected').text() == 'OTHERS') {
            $("#otherbox").show();
        }
        else {
            $("#otherbox").hide();
        }
    });


    $scope.postingquery = function () {
        $('#postAlert').hide();
        $('#catAlert').hide();
        $('#selectAlert').hide();
        if ($('#categorySelect :selected').text() == 'OTHERS') {
            $scope.userDat = {
                'ctgryname': '', "other": $scope.oCategory,
                "pstdqry": $scope.PostingQuery, "empid": $scope.empId, "lastuptdby": $scope.empId, "ctgrycrtdby": $scope.empId
            };

        } else {
            $scope.userDat = {
                'ctgryname': $scope.categorySelect, 'pstdqry': $scope.PostingQuery,
                "empid": $scope.empId, "lastuptdby": $scope.empId, 'other': '', "ctgrycrtdby": ''
            };

        }


        $http({
            method: 'post',
            url: "/Ask_Portal/ask/postquery",
            data: $scope.userDat
        }).then(function successCallback(response) {
            $scope.responseMssg = response.data.responseMsg;
            console.log($scope.responseMssg);
            if (angular.equals($scope.responseMssg, "Query Successfully posted")) {

                $('#postAlert').show();
                $scope.PostingQuery = '';

            } else{
                $('#catAlert').show();     
            }

        }, function errorCallback(response) {
            $('#selectAlert').show();   
            console.log("Unable to perform request");

        });

    };

}]);


