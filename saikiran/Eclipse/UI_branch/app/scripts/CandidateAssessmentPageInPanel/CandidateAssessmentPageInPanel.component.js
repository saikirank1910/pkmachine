angular.module('CandidateAssessmentPageInPanel').component('candidateAssessmentPageInPanelComponent',{
    templateUrl:'../views/CandidateAssessmentPageInPanel.html',
   
    controller:
        function ($http) {
            var ctrl = this;
            ctrl.lists=[];
            ctrl.cid = sessionStorage.getItem('cid');
       
            $http({
              method: 'GET',
              url: 'http://172.16.201.248:1910/SHW-rest/rest/rating/getSelfAssessment/' + ctrl.cid,
              headers: {
                'Content-Type': 'application/text'
              }
            })
              .then(function mySuccess(response) {
                let data = response.data;
                
                ctrl.lists=data;
            
           
              }.bind(ctrl), function myError(response) {
      
              });
            
              // let list=[];
              // for (var j = 0; j < ctrl.lists.length; j++) {
              //   list[j]=new Object();
              //   list[j].cid=ctrl.cid;
              //   list[j].technolgyId=ctrl.lists[j].id;
              //   list[j].ratingId=ctrl.lists[j].rating;
              // }
              // console.log(list);
              // $http({
              //   method: '',
              //   url: 'http://localhost:1910/SHW-rest/rest/rating/submit',
              //   data: list,
              //   headers: {
              //     'Content-Type': 'application/text'
              //   }
              // })
              //   .then(function mySuccess(response) {
              //     this.lists = response.data;
              //     console.log(this.lists);
              //   }.bind(ctrl), function myError(response) {
      
              //  });     
          }
    });