angular.module('footer')
	.component('footerComponent',{
		template:'<footer><a href="https://www.prokarma.com" align="center">Contact Us</footer>',
		controller:FooterController
	});

	function FooterController(appService) {
		var self = this;

		/*appService.make({endpoint:"assets/products.json",method:"GET"})
			.then(function(response){
				console.log(response);
			},function(error){
				console.log(error);
			});*/

	}

	FooterController.$inject = ['appService'];