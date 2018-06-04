angular.module('myApp').controller("viewPostController", ['$scope', '$http', '$sessionStorage', function ($scope, $http, $sessionStorage) {
  $scope.oneAtATime = true;
  $scope.Categories = [];
  //$scope.sme = sessionStorage.smeCheck;
  $scope.userData = { 'empid': 0, 'category': '' };

  $http({
    method: 'post',
    url: "/Ask_Portal/ask/getAll",
    data: $scope.userData
  })
    .then(function successCallback(response) {
      console.log(response.data);
      $scope.Categories = response.data;
    });
    // $scope.checkCat=function(CatName){
    //   var count=0;
    //   $scope.EmpId = sessionStorage.empId;


    //   $scope.getCategory = { 'empId': $scope.EmpId };
    //   $http({
    //     method: 'post',
    //     url: "/Ask_Portal/ask/getCtrgyName",
    //     data: $scope.getCategory
    //   }).then(function successCallback(response) {



    //   $scope.smeCategory = response.data.ctrgyName;
    //   console.log(response.data.ctrgyName);
       
    //   for(i=0;i<response.data.ctrgyName.length;i++){
    //     if(( $scope.sme==true)&&(response.data.ctrgyName[i]==CatName)){
    //       return true;
    //     }
    //   }
    
    //   }, function errorCallback(response) {
    //     console.log("Unable to perform get request");
    //   });
   
    // };
  

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
        $scope.userData = { 'empid': 0, 'category': '' };
        $http({
          method: 'post',
          url: "/Ask_Portal/ask/getAll",
          data: $scope.userData
        })
          .then(function successCallback(response) {

            $scope.Categories = response.data;
          });
      });

  }

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

        $scope.deletereplyresponses = response.data.responseMsg;

        reply.hidden = true;

        $("#deleteReplyAlert").show();

        $scope.userData = { 'empid': 0, 'category': '' };

        $http({
          method: 'post',
          url: "/Ask_Portal/ask/getAll",
          data: $scope.userData
        })
          .then(function successCallback(response) {

            $scope.Categories = response.data;
          })
      })


  }
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
        if (angular.equals($scope.deletepostresponse, "successfully deleted posted query")) {
          $("#deletePostAlert").show();
        }

        if (event) {
          event.preventDefault();
          event.stopPropagation();
        }
        $scope.Categories.splice(postindex, 1);
        $scope.userData = { 'empid': 0, 'category': '' };

        $http({
          method: 'post',
          url: "/Ask_Portal/ask/getAll",
          data: $scope.userData
        })
          .then(function successCallback(response) {
            c
            $scope.Categories = response.data;
          })
      })
  };
}]);