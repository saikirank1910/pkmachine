'use strict';
(function (angular) {
  function adminPanelAssigneeController(appService) {
    var self = this;
    self.toBeAssigned = [];
    self.Registered = [];
    self.Assigned = [];
    self.forSecondRound = [];
    self.entry = {};
    self.panels = [];
    self.forSecondOpinion=[];
    self.refresh = function () {
      appService.make({ endpoint: "candidate/candidatesToBeAssigned", method: "GET" })
        .then(function (response) {
          self.toBeAssigned = response.data;
        }, function (error) {
        });

      appService.make({ endpoint: "candidate/candidatesRegistered", method: "GET" })
        .then(function (response) {
          self.Registered = response.data;
        }, function (error) {

        });
      appService.make({ endpoint: "candidate/candidateForsecondRound", method: "GET" })
        .then(function (response) {
          self.forSecondRound = response.data;
        }, function (error) {

        });
        appService.make({ endpoint: "candidate/candidatesForSecondOpinion", method: "GET" })
        .then(function (response) {
          self.forSecondOpinion = response.data;
        }, function (error) {

        });
      let verify = localStorage.getItem('admin');
      console.log(verify + " admin page");
      if (verify == "false") {
        window.location.href = 'http://172.16.201.248:9000';
      }
    }
    self.refresh();

    self.displayView = function (candidate) {
      self.entry = angular.copy(candidate);
      let tech = {};
      tech.technology = candidate.technologyName;
      appService.make({ endpoint: "panel/getPanel", method: "POST", data: tech })
        .then(function (response) {
          self.panels = response.data;
        }, function (error) {

        });
      $('#assignView').modal().show();
    }

    self.displayToFirstRound = function (candidate) {
      self.displayView(candidate);
      self.entry.round = 1;
    }
    self.displayToSecondRound = function (candidate) {
      self.displayView(candidate);
      self.entry.round = 2;
    }
    self.displayToSecondOpinion = function (candidate) {
      self.displayView(candidate);
      self.entry.round = 1;
    }

    self.assignPanel = function () {
      let panel = {};
      panel.candidateId = self.entry.cid;
      panel.modeOfInterview = self.mode;
      panel.round = self.entry.round;
      for (var i = 0; i < self.panels.length; i++) {
        if (self.panelName.panel_member === self.panels[i].panel_member) {
          panel.panelId = self.panels[i].panelid;
          panel.panelEmail = self.panels[i].email;
          panel.panelName = self.panels[i].panel_member;
        }
      }


      appService.make({ endpoint: "panelAssignee/savePanel", method: "POST", data: panel })
        .then(function (response) {
          self.refresh();
          $("#PanelAssigneeAdded").show();
            setTimeout(function() { $("#PanelAssigneeAdded").hide(); }, 2500);
        }, function (error) {

        });
      $('#assignView').modal('hide');
    }

  };

  adminPanelAssigneeController.$inject = ['appService'];
  angular.module('adminPanelAssigneeModule').component('adminPanelAssigneeModuleComponent', {
    templateUrl: '../views/PanelAssigneeForm.html',
    controller: adminPanelAssigneeController
  });
})(window.angular);