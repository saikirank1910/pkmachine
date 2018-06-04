angular.module("myApp").controller("LoginController", ['$scope', '$location', '$http', '$route', '$sessionStorage', function ($scope, $location, $http, $route, $sessionStorage) {

	$scope.result;
	$scope.roleName;
	sessionStorage.loggedIn = false;
	$scope.forgotPassword = function () {

		$('#progress').show();
		$scope.userData = { 'emailid': $scope.email};

		$http({
			method: 'post',
			url: "Ask_Portal/ask/resetPassword",
			data: $scope.userData
		})
			.then(function successCallback(response) {


				$scope.responseMsg = response.data.responseMessage;

				if (angular.equals($scope.responseMsg, "Password sent to registered email")) {

					$('#progress').hide();
					$("#myModal").modal('hide');
					$('#successAlert').show();

				} else if (angular.equals($scope.responseMsg, "Please enter registered email")) {
					console.log("noemail");
					$('#progress').hide();
					$('#successAlert').hide();
					$('#unRegisteredAlert').show();


				} 

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
			});
	};

	$scope.validornot = function () {

		$scope.userData = { 'userName': $scope.username, 'password': $scope.password };
		$http({
			method: 'post',
			url: "/Ask_Portal/ask/checkCredentials",
			data: $scope.userData
		}).then(function successCallback(response) {
			$("#unameAlert").hide();
			$("#passAlert").hide();

			$scope.result = response.data.responseStr;
			$scope.uFullName = response.data.userFullName;
			$scope.roleName = response.data.roleName;

			if (angular.equals($scope.result, "Invalid UserName")) {
				$("#unameAlert").show();
			} else if (angular.equals($scope.result, "Invalid Password")) {
				$("#passAlert").show();
			} else if (angular.equals($scope.result, "Success")) {


				sessionStorage.userName = $scope.username;
				sessionStorage.empId = response.data.empId;
				sessionStorage.loggedIn = true;
				sessionStorage.userFullName = $scope.uFullName;

				if (angular.equals($scope.roleName.length, 1)) {
					$scope.redir(response.data.roleName[0]);
					sessionStorage.roleName = $scope.roleName;

				} else {
					sessionStorage.smeCheck = true;
					sessionStorage.adminCheck = true;
					$location.path("/viewPost");
				}

			}
		}, function errorCallback(response) {
			console.log("Unable to perform get request");
			sessionStorage.loggedIn = false;
		});
	};

	$scope.redir = function (roleName) {

		if (angular.equals(roleName, 'SME')) {
			sessionStorage.smeCheck = true;
			sessionStorage.adminCheck = false;
			sessionStorage.userRole = $scope.roleName;
			$location.path("/viewPost");

		} else if (angular.equals(roleName, 'EMPLOYEE')) {
			sessionStorage.smeCheck = false;
			sessionStorage.adminCheck = false;
			sessionStorage.userRole = $scope.roleName;
			$location.path("/viewPost");
		}
		else if (angular.equals(roleName, 'ADMIN')) {
			sessionStorage.adminCheck = true;
			sessionStorage.smeCheck = false;
			sessionStorage.userRole = $scope.roleName;
			$location.path("/viewPost");
		} else {
			sessionStorage.smeCheck = false;
			sessionStorage.adminCheck = false;
			$location.path("/viewPost");
		}
	};
}]);