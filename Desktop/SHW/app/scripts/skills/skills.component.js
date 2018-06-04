(function (angular) {
  function taskController(appService) {
    let verify = localStorage.getItem('superAdmin');
    var self = this;
    self.Skill = {};
    self.technologies = [];
    self.allTechnologies = [];
    self.refresh = function () {
      appService.make({ endpoint: "superadmin/getTechnnologies", method: "GET" })
        .then(function (response) {
          console.log("inside success", response);
          self.technologies = response.data;
        }, function (error) {
          console.log(error);
        });

      appService.make({ endpoint: "superadmin/getSubtechnnologies", method: "GET" })
        .then(function (response) {
          self.allTechnologies = response.data;
        }, function (error) {
          console.log(error);
        });
        let verify = localStorage.getItem('superAdmin');
					console.log(verify + " superadmin page");
					if (verify=="false") {
						window.location.href= 'http://172.16.201.248:9000';
					}	

    }
    self.refresh();
    self.submit = function (technologyAddForm) {
      technologyAddForm.$setPristine();
      if (self.Skill.id === null) {
        appService.make({ endpoint: "superadmin/addSubtechnology", method: "POST", data: self.Skill })
          .then(function (response) {
            self.refresh();
           
            $("#subTechnologyAdded").show();
            setTimeout(function() { $("#subTechnologyAdded").hide(); }, 2500);
          }, function (error) {
            console.log(error);
          });


      } else {
        for (var i = 0; i < self.allTechnologies.length; i++) {
          if (self.allTechnologies[i].id === self.Skill.id) {

            appService.make({ endpoint: "superadmin/editSubtechnology", method: "POST", data: self.Skill })
              .then(function (response) {
                self.refresh();
                
                $("#subTechnologyUpdated").show();
                setTimeout(function() { $("#subTechnologyUpdated").hide(); }, 2500);
              }, function (error) {
                console.log(error);
              });
            break;
          }
        }
      }
      self.reset();
    };
    self.addTechnology = function (addForm) {

      let tech = {};
      tech.name = self.newTech;

      appService.make({ endpoint: 'superadmin/addTechnology', method: "POST", data: tech })
        .then(function (response) {
          self.newTech = '';
          self.refresh();
          
          addForm.$setPristine();
          $('#subView').modal('hide');
          $("#technologyAdded").show();
          setTimeout(function() { $("#technologyAdded").hide(); }, 2500);
        }, function (error) {

        });
      self.newTech.name = '';
    };
    self.reset = function () {
      self.Skill = { id: null, technology: '', subTechnology: '', description: '' };
    };

    self.toDelete = function (newTechnology) {

      appService.make({ endpoint: 'superadmin/deleteSubtechnology/' + newTechnology.id, method: "GET" })
        .then(function (response) {
          self.refresh();
          $("#subTechnologyDeleted").show();
          setTimeout(function() { $("#subTechnologyDeleted").hide(); }, 2500);
        }, function (error) {

        });
    };
    self.resetTechnologyForm = function () {
      self.newTech = '';
      $("#subView").modal().hide();

    };
    self.skillToDelete = function (newSkillLevel) {
      self.deleteSkill = newSkillLevel;
  };
    self.toEdit = function (newTechnology) {

      for (var i = 0; i < self.allTechnologies.length; i++) {
        if (self.allTechnologies[i].id === newTechnology.id) {
          self.Skill = angular.copy(self.allTechnologies[i]);
          break;
        }
      }
    };
  }
  taskController.$inject = ['appService'];
  angular.module('skills').component('mySkills', {
    templateUrl: '../../views/addTechnology.html',
    controller: taskController,

  });
})(window.angular);
