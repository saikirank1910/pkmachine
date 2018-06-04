angular.module('panel')
	.component('panelComponent', {
		templateUrl: '../views/AddPanelForm.html',
		controller: function (appService,$location) {
			var ctrl = this;
			ctrl.PanelDetails = {};
			ctrl.allPanels = [];

			appService.make({ endpoint: "superadmin/getTechnnologies", method: "GET" })
				.then(function (response) {

					ctrl.technologies = response.data;

				}, function (error) {

				});
			ctrl.addPanelMember = function (panelAddForm) {

				if (ctrl.PanelDetails.panelid == null) {
					appService.make({ endpoint: 'panel/addPanel', method: "POST", data: ctrl.PanelDetails })
						.then(function success(response) {

							ctrl.refresh();
							ctrl.reset();
							panelAddForm.$setPristine();
							$("#panelAdded").show();
							setTimeout(function() { $("#panelAdded").hide(); }, 2500);
						},
						function error(errors) {

						});
				} else
					appService.make({ endpoint: 'panel/editPanelDetails', method: "POST", data: ctrl.PanelDetails })
						.then(function success(response) {

							ctrl.refresh();
							ctrl.reset();
							panelAddForm.$setPristine();
							$("#panelUpdated").show();
							setTimeout(function() { $("#panelUpdated").hide(); }, 2500);
						},
						function error(errors) {

						});
			}
			ctrl.refresh = function () {
				appService.make({ endpoint: 'panel/getAllPanelDetails', method: "GET" })
					.then(function success(response) {
						ctrl.allPanels = response.data;
					},
					function error(errors) {

					});
					let verify = localStorage.getItem('superAdmin');
					console.log(verify + " superadmin page");
					if (verify=="false") {
						window.location.href= 'http://172.16.201.248:9000';
					}	
			};
			ctrl.refresh();
			ctrl.toEdit = function (panel) {
				ctrl.PanelDetails.panelid = panel.panelid;
				ctrl.PanelDetails.panel_member = panel.panel_member;
				ctrl.PanelDetails.technology = panel.technology;
				ctrl.PanelDetails.email = panel.email;
			}

			ctrl.reset = function () {
				ctrl.PanelDetails.panelid = null;
				ctrl.PanelDetails.panel_member = '';
				ctrl.PanelDetails.technology = '';
				ctrl.PanelDetails.email = '';
			}

			ctrl.usertoDelete = function (panel) {
				ctrl.deletePanel = panel;
			}
			ctrl.toDelete = function (panel) {
				appService.make({ endpoint: 'panel/deletePanelDetails', method: "POST", data: panel })
					.then(function success(response) {
						ctrl.refresh();
						$("#panelDeleted").show();
						setTimeout(function() { $("#panelDeleted").hide(); }, 2500);
					},
					function error(errors) {

					});
			}



		}
	});