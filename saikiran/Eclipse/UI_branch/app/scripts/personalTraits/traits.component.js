angular.module('traits')
	.component('traitsComponent', {
		templateUrl: '../views/PersonalTraits.html',
		controller: function (appService) {
			var self = this;
			self.trait = {};
			self.allTraits = [];
			self.skill = {};
			self.submit = function (personalTraits) {
				if (self.trait.traitId == null) {
					appService.make({ endpoint: 'superadmin/addPersonalTrait', method: "POST", data: self.trait })
						.then(function success(response) {

							self.reset();
							self.refresh();
							personalTraits.$setPristine();
							$("#traitsAdded").show();
							setTimeout(function() { $("#traitsAdded").hide(); }, 2500);
						},
						function error(errors) {

						});
				} else {
					appService.make({ endpoint: 'superadmin/editPersonalTrait', method: "POST", data: self.trait })
						.then(function success(response) {

							self.reset();
							self.refresh();
							personalTraits.$setPristine();
							$("#traitsUpdated").show();
							setTimeout(function() { $("#traitsUpdated").hide(); }, 2500);
							
						},
						function error(errors) {

							alert("Failed to login");
						});
				}

			}
			self.refresh = function () {
				appService.make({ endpoint: 'superadmin/getPersonalTraits', method: "GET" })
					.then(function success(response) {
						self.allTraits = response.data;
					},
					function error(errors) {
					});
				let verify = localStorage.getItem('superAdmin');
				console.log(verify + " superadmin page");
				if (verify == "false") {
					window.location.href = 'http://172.16.201.248:9000';
				}
			};
			self.refresh();
			self.usertoDelete = function (newTrait) {
				self.deleteTrait = newTrait;

			};
			self.toDelete = function (newTrait) {

				appService.make({ endpoint: 'superadmin/deletePersonalTrait', method: "POST", data: newTrait })
					.then(function success(response) {
						self.refresh();

						$("#traitsDeleted").show();
            			setTimeout(function() { $("#traitsDeleted").hide(); }, 2500);
					},
					function error(errors) {
					});
			};
			self.reset = function (personalTraits) {
				personalTraits.$setPristine();
				self.trait.traitId = null;
				self.trait.personalTrait = '';
			};
			self.toEdit = function (newTrait) {
				self.trait.traitId = newTrait.traitId;
				self.trait.personalTrait = newTrait.personalTrait;

			}
		},
	});