angular.module("myApp").controller("SmeController", ['$scope', '$http', '$sessionStorage', function ($scope, $http, $sessionStorage) {
  $scope.oneAtATime = true;
  $scope.Categories = [];
  $scope.sme = sessionStorage.smeCheck;


  $scope.EmpId = sessionStorage.empId;
  $scope.getCategory = { 'empId': $scope.EmpId };
  $http({
    method: 'post',
    url: "/Ask_Portal/ask/getCtrgyName",
    data: $scope.getCategory
  }).then(function successCallback(response) {
    $scope.smeCategory = response.data.ctrgyName;


  }, function errorCallback(response) {
    console.log("Unable to perform get request");
  });

  $scope.searchByCategory = function () {
    $("#noPostAlert").hide();
    $scope.userData = { 'empid': 0, 'category': $scope.categorySelect };

    $http({
      method: 'post',
      url: "/Ask_Portal/ask/getAll",
      data: $scope.userData
    })
      .then(function successCallback(response) {

        $scope.Categories = response.data;
        if (angular.equals($scope.Categories.length, 0)) {
          $("#noPostAlert").show();
        }
      }, function errorCallback(response) {
        console.log("Unable to perform get request");
      });

  }






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

        $("#postAlert").show();

        $scope.userData = { 'empid': 0, 'category': $scope.categorySelect };
        $http({
          method: 'post',
          url: "/Ask_Portal/ask/getAll",
          data: $scope.userData
        })
          .then(function successCallback(response) {

            $scope.Categories = response.data;
          }, function errorCallback(response) {
            console.log("Unable to perform get request");
          });

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