angular.module("selfAssessment")
  .component("selfAssessmentComponent", {
    templateUrl: "../views/assessment.html",
    controller: function ($http) {
      var ctrl = this;
      ctrl.lists = [];
 
      ctrl.cid = sessionStorage.getItem('cid');
   
      $http({
        method: 'GET',
        url: 'http://172.16.201.248:1910/SHW-rest/rest/rating/id/' + ctrl.cid,
        headers: {
          'Content-Type': 'application/text'
        }
      })
        .then(function mySuccess(response) {
          this.lists = response.data;
     
        }.bind(ctrl), function myError(response) {

        });
      ctrl.submitRating = function () {
        let list=[];
        for (var j = 0; j < ctrl.lists.length; j++) {
          list[j]=new Object();
          list[j].cid=ctrl.cid;
          list[j].technolgyId=ctrl.lists[j].id;
          list[j].ratingId=ctrl.lists[j].rating;
        }
    
        $http({
          method: 'POST',
          url: 'http://172.16.201.248:1910/SHW-rest/rest/rating/submit',
          data: list,
          headers: {
            'Content-Type': 'application/json'
          }
        })
          .then(function mySuccess(response) {
            this.lists = response.data;
        
            window.location.href='http://172.16.201.248:9000/#!/self';
          }.bind(ctrl), function myError(response) {

          });
      }

    }
  });
