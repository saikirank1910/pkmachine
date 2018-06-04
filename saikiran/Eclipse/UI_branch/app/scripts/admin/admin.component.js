angular.module('admin',['angular-growl'])
	.component('adminComponent', {
		templateUrl: '../views/AdminForm.html',
		controller: function (appService) {
			var self = this;
			self.userDetails = {};
			self.users = [];
			self.addUser = function (adminAddForm) {
				if (self.userDisabled && self.passwordDisabled) {
					appService.make({ endpoint: 'admin/editUserDetails', method: "POST", data: self.userDetails })
						.then(function success(response) {
							self.reset();
							self.refresh();
							adminAddForm.$setPristine();
							$("#userEdited").show();
       					 	setTimeout(function() { $("#userEdited").hide(); }, 2500);
						},
						function error(errors) {

						});
				} else {
					appService.make({ endpoint: 'admin/insertuserdetails', method: "POST", data: self.userDetails })
						.then(function success(response) {

							self.reset();
							self.refresh();
							adminAddForm.$setPristine();
							$("#userAdded").show();
							setTimeout(function() { $("#userAdded").hide(); }, 2500);	
						},
						function error(errors) {
						});
				}

			}
			self.refresh = function () {
				appService.make({ endpoint: 'admin/getAllUserDetails', method: "GET" })
					.then(function success(response) {
						self.users = response.data;
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
			self.userToDelete = function (user) {
				self.deleteUser = user;
			}
			self.toDelete = function (user) {

				appService.make({ endpoint: 'admin/deleteUserDetails', method: "POST", data: user })
					.then(function success(response) {

						self.refresh();
						self.deleteUser = '';
						$("#userDeleted").show();
       					 setTimeout(function() { $("#userDeleted").hide(); }, 2500);

					},
					function error(errors) {

					});
			};
			self.reset = function () {
				self.userDetails.firstName = '';
				self.userDetails.lastName = '';
				self.userDetails.email = '';
				self.userDetails.userName = '';
				self.userDetails.password = '';
				self.userDisabled = false;
				self.passwordDisabled = false;
			};
			self.toEdit = function (user) {
				self.userDetails.firstName = user.firstName;
				self.userDetails.lastName = user.lastName;
				self.userDetails.email = user.email;
				self.userDetails.userName = user.userName;
				self.userDetails.password = "*******"
				self.userDisabled = true;
				self.passwordDisabled = true;
			}
		},
	});