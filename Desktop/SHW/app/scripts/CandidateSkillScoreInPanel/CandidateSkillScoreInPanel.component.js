angular.module('CandidateSkillScoreInPanel').component('candidateSkillScoreInPanelComponent', {
  templateUrl: '../views/CandidateSkillScoreInPanel.html',
  controller: function ($http, appService) {

    var ctrl = this;
    ctrl.accordion = 1;
    ctrl.evaluation = {};
    ctrl.evaluation.skillLevel;
    ctrl.addTechnology;
    ctrl.evaluation.candidateId = sessionStorage.getItem('cid');
    ctrl.evaluation.panelId = sessionStorage.getItem('pid');
    ctrl.personalTraits = [];
    ctrl.lists = [];
    ctrl.cid = sessionStorage.getItem('cid');
    ctrl.pid = sessionStorage.getItem('pid');
    ctrl.round = sessionStorage.getItem('round');
    ctrl.skillLevel = [];
    ctrl.allTechnologies = [];
    ctrl.accordionChange=function(temp){
      ctrl.accordion=temp+1;
      console.log(ctrl.accordion);
    }
    ctrl.displayDescription = function (description) {
      $("#" + description).toggle();
    }



    appService.make({ endpoint: 'skill/getSkillLevel', method: "GET" })
      .then(function success(response) {
        let temp = response.data;
        for (var j = 0; j < temp.length; j++) {
          ctrl.skillLevel[j] = temp[j].skillLevel;
        }
      },
      function error(errors) {

      });


    appService.make({ endpoint: "superadmin/getSubtechnnologies", method: "GET" })
      .then(function (response) {
        ctrl.allTechnologies = response.data;

      }, function (error) {
        console.log(error);
      });


    appService.make({ endpoint: 'superadmin/getPersonalTraits', method: "GET" })
      .then(function success(response) {
        ctrl.personalTraits = response.data;
        for (var i = 0; i < ctrl.personalTraits.length; i++) {
          ctrl.personalTraits[i].rating = 1;
        }
      },
      function error(errors) {

      });

    ctrl.submitRating = function () {
      let list = [];
      for (var j = 0; j < ctrl.lists.length; j++) {
        list[j] = new Object();
        list[j].pid = ctrl.pid;
        list[j].cid = ctrl.cid;
        list[j].skillId = ctrl.lists[j].id;
        list[j].ratingId = ctrl.lists[j].rating;
      }

      let traits = [];
      for (var j = 0; j < ctrl.personalTraits.length; j++) {
        traits[j] = new Object();
        traits[j].pid = ctrl.pid;
        traits[j].cid = ctrl.cid;
        traits[j].skillId = ctrl.personalTraits[j].traitId;
        traits[j].ratingId = ctrl.personalTraits[j].rating;
      }

      appService.make({ endpoint: 'panelEvaluation/submitEvaluation', method: "POST", data: ctrl.evaluation })
        .then(function success(response) {


        },
        function error(errors) {

        });


      appService.make({ endpoint: 'panelEvaluation/submitSkillsRating', method: "POST", data: list })
        .then(function success(response) {


        },
        function error(errors) {

        });


      appService.make({ endpoint: 'panelEvaluation/submitTraitsRating', method: "POST", data: traits })
        .then(function success(response) {

          window.location.href = 'http://172.16.201.248:9000/#!/submitted';
        },
        function error(errors) {

        });

    }

    $http({
      method: 'GET',
      url: 'http://172.16.201.248:1910/SHW-rest/rest/rating/id/' + ctrl.cid,
      headers: {
        'Content-Type': 'application/text'
      }

    })
      .then(function mySuccess(response) {
        ctrl.lists = response.data;
        for (var i = 0; i < ctrl.lists.length; i++) {
          ctrl.lists[i].rating = 1;
          var splitDescription = new Array();
          splitDescription = ctrl.lists[i].description.split(",");
          ctrl.lists[i].splitDescription = splitDescription;
        }
      }.bind(ctrl), function myError(response) {

      });

    ctrl.addToSkills = function () {
      for (var j = 0; j < ctrl.allTechnologies.length; j++) {
        if (ctrl.addTechnology == ctrl.allTechnologies[j].subTechnology) {
          var splitDescription = new Array();
          splitDescription = ctrl.allTechnologies[j].description.split(",");
          ctrl.allTechnologies[j].splitDescription = splitDescription;
          ctrl.allTechnologies[j].rating = 1;
          console.log(ctrl.allTechnologies[j]);
          ctrl.lists.push(ctrl.allTechnologies[j]);
          ctrl.addTechnology = "";
        }
      }
    }
  }
});