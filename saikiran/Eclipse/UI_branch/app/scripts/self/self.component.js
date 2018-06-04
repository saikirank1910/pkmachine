angular.module('self')
	.component('selfComponent',{
		templateUrl:'../views/self.html',
		$routeConfig: [
			{path: '/selfAssessment', name: 'SelfAssessment', component: 'selfAssessmentComponent'}
		],
		controller:function(appService,$location) {   
			var _self=this;
			_self.selfAuthentication = function() {
                let tokenData=new Object();
                tokenData.email=this.email;
                tokenData.token=this.token;

				appService.make({endpoint:'candidate/login', method:"POST", data:tokenData})
				.then(function success(response){
					;	
						let cid=response.data;
						
						sessionStorage.setItem("cid", cid);
						$location.path('selfAssessment');
						}, 
						function error(errors) {
							
						});
		
			  }
	
			}
	});