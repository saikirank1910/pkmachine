angular.module("myApp").controller("SearchController", ['$scope', '$http', function ($scope, $http) {

    $scope.oneAtATime = true;
    $scope.Categories = [];
    //$scope.sme = sessionStorage.smeCheck;


    $scope.EmpId = sessionStorage.empId;

    $('#searchby').on('change', function () {
        if (this.value == 'ByID') {
            $("#myForm1").hide();
            $("#myForm").show();

        }
        else if (this.value == 'ByCat') {
            $("#myForm").hide();
            $("#myForm1").show();
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
        }
        else {
            $("#myForm").hide();
            $("#myForm1").hide();
        }

    });
    $scope.searchByID = function () {
        $("#catAlert").hide();
        $scope.userData = { 'empid': $scope.searchByEmpId, 'category': '' };
        $http({
            method: 'post',
            url: "/Ask_Portal/ask/getAll",
            data: $scope.userData
        }).then(function successCallback(response) {


            $scope.Categories = response.data;
            if (angular.equals($scope.Categories.length, 0)) {
                $("#catAlert").show();
            }
            $("#searchedQueries").show();
        }, function errorCallback(response) {
            console.log("Unable to perform get request");
        });

    };
    $scope.searchByCategory = function () {
        $("#catAlert").hide();
        $scope.userData = { 'empid': 0, 'category': $scope.category };
        $http({
            method: 'post',
            url: "/Ask_Portal/ask/getAll",
            data: $scope.userData
        }).then(function successCallback(response) {


            $scope.Categories = response.data;
            if (angular.equals($scope.Categories.length, 0)) {
                $("#catAlert").show();
            }
            $("#searchedQueries").show();
        }, function errorCallback(response) {
            console.log("Unable to perform get request");
        });

    };

    $scope.PostReply = function (postId, post) {
        $("#postAlert").hide();
        $("#deletePostAlert").hide();
        $("#deleteReplyAlert").hide();

        $scope.sessionid = sessionStorage.empId;
        $scope.reply = { 'postId': postId, 'replyCreatedBy': $scope.sessionid, 'replyMsg': post };

        $http({
            method: 'post',
            url: "/Ask_Portal/ask/replyOnPost",
            data: $scope.reply
        })
            .then(function successCallback(response) {


                $scope.postresponse = response.data.replyStatus;
                if (angular.equals($scope.postresponse, "reply posted successfully")) {
                    $("#postAlert").show();
                  }

               if( $('#searchby').val() == 'ByID') {
                        $scope.userData = { 'empid': $scope.searchByEmpId, 'category': '' };
                        $http({
                            method: 'post',
                            url: "/Ask_Portal/ask/getAll",
                            data: $scope.userData
                        }).then(function successCallback(response) {

                            $scope.Categories = response.data;
                            $("#searchedQueries").show();
                        }, function errorCallback(response) {
                            console.log("Unable to perform get request");
                        });
                    } else {
                        $scope.userData = { 'empid': 0, 'category': $scope.category };
                        $http({
                            method: 'post',
                            url: "/Ask_Portal/ask/getAll",
                            data: $scope.userData
                        }).then(function successCallback(response) {

                            $scope.Categories = response.data;
                            $("#searchedQueries").show();
                        }, function errorCallback(response) {
                            console.log("Unable to perform get request");
                        });
                    }
                });

            };

 

    $scope.DeleteReply = function (replyId, reply) {
        $("#postAlert").hide();
        $("#deletePostAlert").hide();
        $("#deleteReplyAlert").hide();

        $scope.sessionid = sessionStorage.empId
        $scope.deletereply = { 'replyId': replyId, 'repliedBy': $scope.sessionid };
        $http({
            method: 'post',
            url: "/Ask_Portal/ask/updateReplyStatus",
            data: $scope.deletereply
        })
            .then(function successCallback(response) {

                $scope.deletereplyresponse = response.data.responseMsg;

                reply.hidden = true;

                $("#deleteReplyAlert").show();


            });
    };
    $scope.DeletePost = function (postindex, postId, event) {
        $("#postAlert").hide();
        $("#deletePostAlert").hide();
        $("#deleteReplyAlert").hide();

        $scope.sessionid = sessionStorage.empId;
        $scope.deletepost = { 'postId': postId, 'postedBy': $scope.sessionid };
        $http({
            method: 'post',
            url: "/Ask_Portal/ask/updatePostStatus",
            data: $scope.deletepost
        })
            .then(function successCallback(response) {

                $scope.deletepostresponse = response.data.responseMsg;

                if (event) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                $scope.Categories.splice(postindex, 1);
                $("#deletePostAlert").show();
               
            });

    };




}]);