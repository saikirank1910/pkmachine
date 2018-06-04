angular.module('header')
	.component('headerComponent',{
		template:'<h1>Ask Portal</h1>\n',
		controller:HeaderController
	});

	 function HeaderController(appService) {
	 	var self = this;

		/*appService.make({endpoint:"assets/products.json",method:"GET"})
			.then(function(response){
				console.log(response);
			},function(error){
				console.log(error);
			});*/

	}

	//  HeaderController.$inject = ['appService'];