angular.module("myApp").controller('AdminController', ['$scope', '$http', function ($scope, $http) {
    
    
    
    $http({
        method: 'get',
        url: "Ask_Portal/ask/getNames",
    }).then(function successCallback(response) {

        $scope.categorySelect = response.data.fullNames;
        console.log( $scope.categorySelect);
    }, function errorCallback(response) {
        console.log("Unable to perform get request");
    });


    $http({
        method: 'get',
        url: "Ask_Portal/ask/getCategories",
    }).then(function successCallback(response) {


        for (i = 0, len = response.data.length; i < len; i++) {
            respValue = response.data[i];
            if (respValue == 'others') {
                response.data.splice(i, 1);
            }
        }
        $scope.categories = response.data;

    }, function errorCallback(response) {
        console.log("Unable to perform get request");
    });
    $scope.selection = [];

    $scope.toggleSelection = function toggleSelection(employeeName) {
        var idx = $scope.selection.indexOf(employeeName);


        if (idx > -1) {
            $scope.selection.splice(idx, 1);
        }
        else {
            $scope.selection.push(employeeName);
        }

    };
  
    $scope.addAsSME = function () {
        $("#infoAlert").hide();
        $("#catAlert").hide();

        if ($scope.selection == "") {
            $("#catAlert").show();

        }
        else {
            $scope.smeData = { 'fullName': $scope.categorySelec, 'ctrgyList': $scope.selection }
            console.log($scope.smeData);
            $http({
                method: 'post',
                url: "Ask_Portal/ask/addEmpAsSme",
                data: $scope.smeData
            }).then(function successCallback(response) {

                $scope.respMeg = response.data.responseMsg;
                $("#infoAlert").show();

            }, function errorCallback(response) {
                console.log("Unable to perform get request");
            });

        }


    };
}]);