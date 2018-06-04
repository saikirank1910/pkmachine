angular.module('skillLevel').
    component('skillLevelComponent', {
        templateUrl: '../views/SkillLevel.html',
        controller: function (appService) {
            var self = this;
            self.skillLevel = [];
            self.skill = {};

            self.refresh = function () {
                appService.make({ endpoint: 'skill/getSkillLevel', method: "GET" })
                    .then(function success(response) {
                        self.skillLevel = response.data;
                    },
                    function error(errors) {
                    });
                    let verify = localStorage.getItem('superAdmin');
					console.log(verify + " superadmin page");
					if (verify=="false") {
						window.location.href= 'http://172.16.201.248:9000';
					}	
            };
            self.refresh();
            self.submit = function (skillLevel) {

                if (self.skill.skillId == null) {
                    appService.make({ endpoint: 'skill/addSkillLevel', method: "POST", data: self.skill })
                        .then(function success(response) {

                            self.reset();
                            self.refresh();
                            skillLevel.$setPristine();
                            $("#skillLevelAdded").show();
                            setTimeout(function() { $("#skillLevelAdded").hide(); }, 2500);
                        },
                        function error(errors) {

                        });
                } else {
                    appService.make({ endpoint: 'skill/editSkillLevel', method: "POST", data: self.skill })
                        .then(function success(response) {

                            self.reset();
                            self.refresh();
                            skillLevel.$setPristine();
                            $("#skillLevelUpdated").show();
                            setTimeout(function() { $("#skillLevelUpdated").hide(); }, 2500);
                        },
                        function error(errors) {

                        });
                }

            }
            self.usertoDelete = function (newSkillLevel) {
                self.deleteSkillLevel = newSkillLevel;
            };
            self.toDelete = function (newSkillLevel) {

                appService.make({ endpoint: 'skill/deleteSkillLevel/' + newSkillLevel.skillId, method: "GET" })
                    .then(function success(response) {
                        self.refresh();
                        $("#skillLevelDeleted").show();
                        setTimeout(function() { $("#skillLevelDeleted").hide(); }, 2500);
                    },
                    function error(errors) {
                    });
            };
            self.reset = function (skillLevel) {
                //skillLevel.$setPristine();
                self.skill.skillId = null;
                self.skill.skillLevel = '';
            };
            self.toEdit = function (newSkillLevel) {
                self.skill.skillId = newSkillLevel.skillId;
                self.skill.skillLevel = newSkillLevel.skillLevel;
            }
        },
    });